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
import gg.generations.rarecandy.pokeutils.BlendType;
import gg.generations.rarecandy.pokeutils.CullType;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import gg.generations.rarecandy.renderer.animation.AnimationController;
import gg.generations.rarecandy.renderer.loading.ITexture;
import gg.generations.rarecandy.renderer.model.material.PipelineRegistry;
import gg.generations.rarecandy.renderer.pipeline.Pipeline;
import gg.generations.rarecandy.renderer.pipeline.UniformUploadContext;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL13C;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Pipelines {
    public static final RenderContext.Key<CobblemonInstance> INSTANCE = RenderContext.Companion.key(GenerationsCore.id("object_instance"), TypeToken.get(CobblemonInstance.class));
    private static final Vector3f ONE = new Vector3f(1, 1, 1);
    public static Event<Consumer<PipelineRegister>> REGISTER = EventFactory.createConsumerLoop(PipelineRegister.class);
    public static class PipelineRegister {
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

    /**
     * Called on first usage of RareCandy to reduce lag later on
     */
    public static void onInitialize(ResourceManager manager) {
        if(!initialized) {
            REGISTER.invoker().accept(new PipelineRegister(manager, PIPELINE_MAP));
            initialized = true;
            PipelineRegistry.setFunction(Pipelines::getPipeline);
        }
    }

    public static void initGenerationsPipelines(PipelineRegister register) {

        register.register("pokemon", 
                manager -> {

                    var ROOT = new Pipeline.Builder()
//                            .supplyUniform("cameraMatrix", ctx -> ctx.uniform().uploadMat4f(RenderSystem.getInverseViewRotationMatrix()))
                            .supplyUniform("modelMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().transformationMatrix()))
                            .supplyUniform("projectionMatrix", (ctx) -> ctx.uniform().uploadMat4f(RenderSystem.getProjectionMatrix()))
                            .supplyUniform("boneTransforms", ctx -> {
                                var mats = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getTransforms() != null ? instance.getTransforms() : AnimationController.NO_ANIMATION : AnimationController.NO_ANIMATION;
                                ctx.uniform().uploadMat4fs(mats);
                            })
                            .supplyUniform("uvOffset", ctx -> {
                                var offsets = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getOffset(ctx.getMaterial().getMaterialName()) != null ? instance.getOffset(ctx.getMaterial().getMaterialName()) : AnimationController.NO_OFFSET : AnimationController.NO_OFFSET;
                                ctx.uniform().uploadVec2f(offsets.offset());
                            }).supplyUniform("uvScale", ctx -> {
                                var offsets = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getOffset(ctx.getMaterial().getMaterialName()) != null ? instance.getOffset(ctx.getMaterial().getMaterialName()) : AnimationController.NO_OFFSET : AnimationController.NO_OFFSET;
                                ctx.uniform().uploadVec2f(offsets.scale());
                            }).supplyUniform("legacyShading", ctx -> ctx.uniform().uploadBoolean(GenerationsCore.CONFIG.client.usePixelmonShading))
                            .supplyUniform("Light0_Direction", ctx -> ctx.uniform().uploadVec3f(RenderSystem.shaderLightDirections[0])).supplyUniform("Light1_Direction", ctx -> ctx.uniform().uploadVec3f(RenderSystem.shaderLightDirections[1]))
                            .prePostDraw(material -> {
                                if(material.cullType() != CullType.None) {
                                    RenderSystem.enableCull();
                                }

                                if(material.blendType() == BlendType.Regular) {
                                    BlendRecord.push();
                                }
                                }, material -> {
                                if(material.cullType() != CullType.None) {
                                    RenderSystem.enableCull();
                                }

                                if(material.blendType() == BlendType.Regular) {
                                    BlendRecord.pop();
                                }
                            });


                    var BASE = new Pipeline.Builder(ROOT)
                            .configure(Pipelines::addDiffuse)
                            .configure(Pipelines::addLight)
                            .configure(Pipelines::addFog);

                    Pipeline.Builder layered_base = new Pipeline.Builder(BASE)
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
                            })
                            .shader(read(register.resourceManager, "shaders/block/animated.vs.glsl"), read(register.resourceManager, "shaders/block/layered.fs.glsl"));

                    Pipeline layered = new Pipeline.Builder(layered_base)
                            .supplyUniform("frame", ctx -> ctx.uniform().uploadInt(-1))
                            .build();

                    Pipeline solid = new Pipeline.Builder(BASE)
                            .shader(read(manager, "shaders/block/animated.vs.glsl"), read(manager, "shaders/block/solid.fs.glsl"))
                            .build();

                    Pipeline masked = new Pipeline.Builder(BASE)
                            .shader(read(manager, "shaders/block/animated.vs.glsl"), read(manager, "shaders/block/masked.fs.glsl"))
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
                                else if(!isStatueMaterial(ctx) && ctx.object().getMaterial(ctx.instance().variant()).getValue("color") != null)
                                    color = (Vector3f) ctx.object().getMaterial(ctx.instance().variant()).getValue("color");
                                else color = ONE;
                                ctx.uniform().uploadVec3f(color);
                            })
                            .build();

                            Pipeline paradox = new Pipeline.Builder(layered_base)
                                    .supplyUniform("frame", ctx -> ctx.uniform().uploadInt((int) pingpong(MinecraftClientGameProvider.getTimePassed())))
                                    .build();

                            var map = Map.of("masked", masked, "layered", layered, "paradox", paradox, "solid", solid);

                            return s -> map.getOrDefault(s, solid);
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
            ITexture texture = getTexture(ctx);

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
        return ctx.object().getVariant(ctx.instance().variant()).getDiffuseTexture();
    }

    private static void emissionColors(Pipeline.Builder builder) {
        builder.supplyUniform("emiColor1", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor1")))
                .supplyUniform("emiColor2", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor2")))
                .supplyUniform("emiColor3", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor3")))
                .supplyUniform("emiColor4", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor4")))
                .supplyUniform("emiColor5", ctx -> ctx.uniform().uploadVec3f(getColorValue(ctx, "emiColor5")))
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

    private static void addFog(Pipeline.Builder builder) {
        builder.supplyUniform("FogShape", ctx -> ctx.uniform().uploadInt(RenderSystem.getShaderFogShape().ordinal()));
        builder.supplyUniform("FogStart", ctx -> ctx.uniform().uploadFloat(RenderSystem.getShaderFogStart()));
        builder.supplyUniform("FogEnd", ctx -> ctx.uniform().uploadFloat(RenderSystem.getShaderFogEnd()));
        builder.supplyUniform("FogColor", colorConsumer(RenderSystem::getShaderFogColor));
    }

    private static Consumer<UniformUploadContext> colorConsumer(Supplier<float[]> supplier) {
        return ctx -> {
            var color = supplier.get();
            ctx.uniform().upload4f(color[0], color[1], color[2], color[3]);
        };
    }

    private static void addLight(Pipeline.Builder builder) {
        builder.supplyUniform("lightmap", ctx -> {
            ctx.uniform().uploadInt(1);
            GL13C.glActiveTexture('蓀' + 1);
            GL11C.glBindTexture(3553, ((ILightTexture) Minecraft.getInstance().gameRenderer.lightTexture()).getTextureId());
            RenderSystem.texParameter(3553, 10241, 9729);
            RenderSystem.texParameter(3553, 10240, 9729);
        }).supplyUniform("light", ctx -> {
            var light = ((BlockLightValueProvider) ctx.instance()).getLight();
            ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
        }).supplyUniform("emission", ctx -> {
            var texture = ctx.object().getVariant(ctx.instance().variant()).getTexture("emission");


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

    public static Pipeline getPipeline(String name) {
        return PIPELINE_MAP.get("pokemon").apply(name);
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

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
        }

        public static void pop() {
            if(enabled) RenderSystem.enableBlend();
            else RenderSystem.disableBlend();

            RenderSystem.blendFuncSeparate(srcRgb, dstRgb, srcAlpha, dstAlpha);
        }
    }
}