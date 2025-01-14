package generations.gg.generations.core.generationscore.common.client.render.rarecandy;

import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import com.google.gson.reflect.TypeToken;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.client.GenerationsTextureLoader;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.config.Config;
import gg.generations.rarecandy.pokeutils.BlendType;
import gg.generations.rarecandy.pokeutils.CullType;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import gg.generations.rarecandy.renderer.animation.AnimationController;
import gg.generations.rarecandy.renderer.animation.Transform;
import gg.generations.rarecandy.renderer.loading.ITexture;
import gg.generations.rarecandy.renderer.model.material.PipelineRegistry;
import gg.generations.rarecandy.renderer.pipeline.Pipeline;
import gg.generations.rarecandy.renderer.pipeline.UniformUploadContext;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL13C;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Pipelines {
    public static final RenderContext.Key<CobblemonInstance> INSTANCE = RenderContext.Companion.key(GenerationsCore.id("object_instance"), TypeToken.get(CobblemonInstance.class));
    private static final Vector3f ONE = new Vector3f(1, 1, 1);
    public static Event<Consumer<PipelineRegister>> REGISTER = EventFactory.createConsumerLoop(PipelineRegister.class);
    private static boolean useLegacy;

    public static class  PipelineRegister {
        private final ResourceManager resourceManager;
        private final Map<String, Function<String, Pipeline>> pipelines;

        public PipelineRegister(ResourceManager resourceManager, Map<String, Function<String, Pipeline>> pipelines) {
            this.resourceManager = resourceManager;
            this.pipelines = pipelines;
        }
        public void register(String name, Function<ResourceManager, Function<String, Pipeline>> function) {
            pipelines.put(name, function.apply(resourceManager));
        }
    }

    private static final Map<String, Function<String, Pipeline>> PIPELINE_MAP = new HashMap<>();


    private static boolean initialized = false;

    public static void toggleRendering() {
        useLegacy = !useLegacy;
    }

    /**
     * Called on first usage of RareCandy to reduce lag later on
     */
    public static void onInitialize(ResourceManager manager) {
        if(!initialized) {
            REGISTER.invoker().accept(new PipelineRegister(manager, PIPELINE_MAP));
            initialized = true;
            useLegacy = GenerationsCore.CONFIG.client.usePixelmonShading;
            PipelineRegistry.setFunction(Pipelines::getPipeline);
        }
    }

    private static final Vector4f TEMP = new Vector4f();

    public static void initGenerationsPipelines(PipelineRegister register) {
        register.register("main", manager -> createShaderMap(manager, true));
        register.register("legacy", manager -> createShaderMap(manager, false));
    }

    public static Pipeline.Builder createRoot(boolean pixelmonShading) {
        var builder = new Pipeline.Builder()
                .supplyUniform("viewMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().viewMatrix()))
                .supplyUniform("FogStart", ctx -> ctx.uniform().uploadFloat(RenderSystem.getShaderFogStart()))
                .supplyUniform("FogEnd", ctx -> ctx.uniform().uploadFloat(RenderSystem.getShaderFogEnd()))
                .supplyUniform("FogColor", ctx -> {
                    var color = RenderSystem.getShaderFogColor();
                    ctx.uniform().upload4f(color[0], color[1], color[2], color[3]);
                })
                .supplyUniform("ColorModulator", ctx -> {
                    var color = RenderSystem.getShaderColor();

                    ctx.uniform().upload4f(color[0], color[1], color[2], color[3]);
                })
                .supplyUniform("FogShape", ctx -> ctx.uniform().uploadInt(RenderSystem.getShaderFogShape().getIndex()))
                .supplyUniform("modelMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().transformationMatrix()))
                .supplyUniform("projectionMatrix", (ctx) -> ctx.uniform().uploadMat4f(RenderSystem.getProjectionMatrix()))
                .supplyUniform("boneTransforms", ctx -> {
                    var mats = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getTransforms() != null ? instance.getTransforms() : AnimationController.NO_ANIMATION : AnimationController.NO_ANIMATION;
                    ctx.uniform().uploadMat4fs(mats);
                })
                .supplyUniform("uvOffset", ctx -> {
                    Transform transform = ctx.object().getTransform(ctx.instance().variant());

                    if (ctx.instance() instanceof AnimatedObjectInstance instance) {
                        var t = instance.getTransform(ctx.getMaterial().getMaterialName());

                        if (t != null) {
                            transform = t;
                        }
                    }

                    ctx.uniform().uploadVec2f(transform.offset());
                }).supplyUniform("uvScale", ctx -> {
                    Transform transform = ctx.object().getTransform(ctx.instance().variant());

                    if (ctx.instance() instanceof AnimatedObjectInstance instance) {
                        var t = instance.getTransform(ctx.getMaterial().getMaterialName());

                        if (t != null) {
                            transform = t;
                        }
                    }

                    ctx.uniform().uploadVec2f(transform.scale());
                })
                .configure(Pipelines::addDiffuse)
                .configure(Pipelines::addLight);

        if (pixelmonShading) {
            builder.supplyUniform("Light0_Direction", ctx -> ctx.uniform().uploadVec3f(RenderSystem.shaderLightDirections[0])).supplyUniform("Light1_Direction", ctx -> ctx.uniform().uploadVec3f(RenderSystem.shaderLightDirections[1]));
        }
        builder.prePostDraw(material -> {
            if (material.cullType() != CullType.None) {
                RenderSystem.enableCull();
            }

//                               material.cullType().enable();
            if (material.blendType() == BlendType.Regular) {

//                            BlendRecord.push();

                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
            }
        }, material -> {
            if (material.cullType() != CullType.None) {
                RenderSystem.enableCull();
            }

//                                material.cullType().disable();
            if (material.blendType() == BlendType.Regular) {
                RenderSystem.disableBlend();
            }
        });

        return builder;
    }

    public static Function<String, Pipeline> createShaderMap(ResourceManager manager, boolean legacyShading) {
        var shaderMap = new HashMap<String, Pipeline>();

        var extension = legacyShading ? "legacy/" : "main/";

        var base = createRoot(legacyShading);

        var effects = List.of("cartoon", "galaxy", "paradox", "shadow", "sketch", "vintage", "passthrough");

        for (var effect : effects) {
            var solid = createSolid(manager, extension, base, effect);
            var masked = createMasked(manager, extension, base, effect);
            var layered = createLayered(manager, extension, base, effect);

            if(effect.equals("paradox")) {
                addParadox(solid, "solid");
                addParadox(masked, "masked");
                addParadox(layered, "layered");
            }

            var suffix = !effect.equals("passthrough") ? "_" + effect : "";

            shaderMap.put("solid" + suffix, solid.build());
            shaderMap.put("masked" + suffix, masked.build());
            shaderMap.put("layered" + suffix, layered.build());
        }

        var shader = shaderMap.get("solid");

        return s -> shaderMap.getOrDefault(s, shader);
    }

    private static Pipeline.Builder createSolid(ResourceManager manager, String extension, Pipeline.Builder base, String effect) {
        return new Pipeline.Builder(base)
                .shader(read(manager, "shaders/" + extension + "animated.vs.glsl"), read(manager, "shaders/" + extension + "solid.fs.glsl", "shaders/process/" + effect + ".lib.glsl"));
    }
    private static Pipeline.Builder createLayered(ResourceManager manager, String extension, Pipeline.Builder base, String effect) {
        return new Pipeline.Builder(base)
                .shader(read(manager, "shaders/" + extension + "animated.vs.glsl"), read(manager, "shaders/" + extension + "layered.fs.glsl", "shaders/process/" + effect + ".lib.glsl"))
                .configure(Pipelines::baseColors)
                .configure(Pipelines::emissionColors)
                .supplyUniform("layer", ctx -> {
                    var texture = ctx.getTexture("layer");

                    if (isStatueMaterial(ctx) || texture == null) {
                        texture = ITextureLoader.instance().getDarkFallback();
                    }

                    texture.bind(3);
                    ctx.uniform().uploadInt(3);
                }).supplyUniform("mask", ctx -> {
                    var texture = ctx.getTexture("mask");

                    if (isStatueMaterial(ctx) || texture == GenerationsTextureLoader.MissingTextureProxy.INSTANCE) {
                        texture = ITextureLoader.instance().getDarkFallback();
                    }

                    texture.bind(4);
                    ctx.uniform().uploadInt(4);
                });
    }

    private static Pipeline.Builder createMasked(ResourceManager manager, String extension, Pipeline.Builder base, String effect) {
        return new Pipeline.Builder(base)
                .shader(read(manager, "shaders/" + extension + "animated.vs.glsl"), read(manager, "shaders/" + extension + "masked.fs.glsl", "shaders/process/" + effect + ".lib.glsl"))
                .supplyUniform("mask", ctx -> {

                    var texture = ctx.getTexture("mask");

                    if (isStatueMaterial(ctx) || texture == GenerationsTextureLoader.MissingTextureProxy.INSTANCE) {
                        texture = ITextureLoader.instance().getDarkFallback();
                    }


                    texture.bind(3);
                    ctx.uniform().uploadInt(3);
                })
                .supplyUniform("color", ctx -> {
                    Vector3f color;
                    if (ctx.instance() instanceof ModelContextProviders.TintProvider tintProvider && tintProvider.getTint() != null)
                        color = tintProvider.getTint();
                    else if (!isStatueMaterial(ctx) && ctx.object().getMaterial(ctx.instance().variant()).getValue("color") != null)
                        color = (Vector3f) ctx.object().getMaterial(ctx.instance().variant()).getValue("color");
                    else color = ONE;
                    ctx.uniform().uploadVec3f(color);
                });
    }

    public static void addParadox(Pipeline.Builder builder, String shader) {
        var slot = switch (shader) {
            case "masked" -> 4;
            case "layered" -> 5;
            default -> 3;
        };

        builder.supplyUniform("frame", ctx -> {
            var i = (int) pingpong(MinecraftClientGameProvider.getTimePassed());
            ctx.uniform().uploadInt(i);
        }).supplyUniform("paradoxMask", ctx -> {

            var texture =  ITextureLoader.instance().getTexture("paradox_mask");

            texture.bind(slot);
            ctx.uniform().uploadInt(slot);
        });
    }


    public static double pingpong(double time) {
        return (int) (Math.sin(time * Math.PI * 2) * 7 + 7);
    }

    private static ITexture getTexture(String variant) {
        return ITextureLoader.instance().getTexture(variant);
    }

    private static void addDiffuse(Pipeline.Builder builder) {
        builder.supplyUniform("diffuse", ctx -> {
            ITexture texture = getTexture(ctx); //isStatueMaterial(variant) ? getTexture(variant.substring(7)) : ctx.object().getVariant(ctx.instance().variant()).getDiffuseTexture();

            if (texture == GenerationsTextureLoader.MissingTextureProxy.INSTANCE) {
                texture = ITextureLoader.instance().getNuetralFallback();
            }

            texture.bind(0);
            ctx.uniform().uploadInt(0);
        });
    }

    private static ITexture getTexture(UniformUploadContext ctx) {
        String material;
        if (ctx.instance() instanceof StatueInstance instance) {
            material = instance.getMaterial();
        }
        else {
            material = null;
        }

        if(material != null && ((GenerationsTextureLoader) ITextureLoader.instance()).has(material)) {
            return ITextureLoader.instance().getTexture(material);
        }
        return ctx.getTexture("diffuse");
    }

    private static void emissionColors(Pipeline.Builder builder) {
        builder.supplyUniform("emiColor1", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor1")))
                .supplyUniform("emiColor2", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor2")))
                .supplyUniform("emiColor3", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor3")))
                .supplyUniform("emiColor4", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor4")))
                .supplyUniform("emiColor5", ctx -> ctx.uniform().uploadVec3f(ctx.instance() instanceof ModelContextProviders.TintProvider tintProvider && tintProvider.getTint() != null ? tintProvider.getTint() : getColorValue(ctx, "emiColor5")))
                .supplyUniform("emiIntensity1", ctx -> ctx.uniform().uploadFloat(getFloatValue(ctx, "emiIntensity1")))
                .supplyUniform("emiIntensity2", ctx -> ctx.uniform().uploadFloat(getFloatValue(ctx, "emiIntensity2")))
                .supplyUniform("emiIntensity3", ctx -> ctx.uniform().uploadFloat(getFloatValue(ctx, "emiIntensity3")))
                .supplyUniform("emiIntensity4", ctx -> ctx.uniform().uploadFloat(getFloatValue(ctx, "emiIntensity4")))
                .supplyUniform("emiIntensity5", ctx -> ctx.uniform().uploadFloat(getFloatValue(ctx, "emiIntensity5", 1.0f)));
    }

    private static void baseColors(Pipeline.Builder builder) {
        builder.supplyUniform("baseColor1", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "baseColor1")))
                .supplyUniform("baseColor2", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "baseColor2")))
                .supplyUniform("baseColor3", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "baseColor3")))
                .supplyUniform("baseColor4", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "baseColor4")))
                .supplyUniform("baseColor5", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "baseColor5")));
    }

    private static Vector3f getColorValue(UniformUploadContext ctx, String id) {
        return !isStatueMaterial(ctx) && ctx.getValue(id) instanceof Vector3f vec ? vec : Pipelines.ONE;
    }

    private static float getFloatValue(UniformUploadContext ctx, String id) {
        return getFloatValue(ctx, id, 0.0f);
    }

    private static float getFloatValue(UniformUploadContext ctx, String id, float value) {
        return !isStatueMaterial(ctx) && ctx.getValue(id) instanceof Float vec ? vec : value;
    }

    private static void addLight(Pipeline.Builder builder) {
        builder.supplyUniform("lightmap", ctx -> {
//            ITextureLoader.instance().getNuetralFallback().bind(1);

            ((ITexture) Minecraft.getInstance().gameRenderer.lightTexture()).bind(1);
//            GL13C.glActiveTexture('蓀' + 1);
            ctx.uniform().uploadInt(1);
        }).supplyUniform("light", ctx -> {
            var light = ((BlockLightValueProvider) ctx.instance()).getLight();
            ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
        }).supplyUniform("emission", ctx -> {
            var texture = ctx.getTexture("emission");

            if (isStatueMaterial(ctx) || texture == GenerationsTextureLoader.MissingTextureProxy.INSTANCE) {
                texture = ITextureLoader.instance().getDarkFallback();
            }

            texture.bind(2);
            ctx.uniform().uploadInt(2);
        }).supplyUniform("useLight", ctx -> ctx.uniform().uploadBoolean(ctx.getValue("useLight") instanceof Boolean bool ? bool : true));
    }

    private static boolean isStatueMaterial(UniformUploadContext ctx) {
        return ctx.instance() instanceof StatueInstance instance && instance.getMaterial() != null && ((GenerationsTextureLoader) ITextureLoader.instance()).has(instance.getMaterial());
    }

    private static final String MAIN = "main";
    private static final String LEGACY = "legacy";

    public static Pipeline getPipeline(String name) {
        return PIPELINE_MAP.get(useLegacy ? LEGACY : MAIN).apply(name);
    }

    public static String read(ResourceManager manager, String name) {
        return read(manager, GenerationsCore.id(name));
    }

    public static String read(ResourceManager manager, ResourceLocation name) {
        try (var is = manager.getResource(name).orElseThrow().open()) {
            return new String(is.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read shader from resource location in shader: " + name, e);
        }
    }

    public static String read(ResourceManager manager, String name, String lib) {
        return read(manager, GenerationsCore.id(name), GenerationsCore.id(lib));
    }

    private static String read(ResourceManager manager, ResourceLocation name, ResourceLocation lib) {
        try (
                var nameStream = manager.getResource(name).orElseThrow().open();
                var libStream = manager.getResource(lib).orElseThrow().open();
        ) {
            String shaderContent = new String(nameStream.readAllBytes());

            String libContent = new String(libStream.readAllBytes());

            return shaderContent.replace("#process", libContent);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read shader or library file from location location: " + name + ", " + lib, e);
        }
    }

    private static class BlendRecord {
        public static boolean enabled;
        public static int srcRgb;
        public static int dstRgb;
        public static int srcAlpha;
        public static int dstAlpha;

        public static void push() {
            enabled = GlStateManager.BLEND.mode.enabled;
            srcRgb = GlStateManager.BLEND.srcRgb;
            dstRgb = GlStateManager.BLEND.dstRgb;
            srcAlpha = GlStateManager.BLEND.srcAlpha;
            dstAlpha = GlStateManager.BLEND.dstAlpha;
        }

        public static void pop() {
            if(enabled) RenderSystem.enableBlend();
            else RenderSystem.disableBlend();

            RenderSystem.blendFuncSeparate(srcRgb, dstRgb, srcAlpha, dstAlpha);
        }
    }
}