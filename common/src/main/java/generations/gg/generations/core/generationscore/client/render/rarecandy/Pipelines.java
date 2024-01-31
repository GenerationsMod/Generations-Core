package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import gg.generations.rarecandy.pokeutils.BlendType;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import gg.generations.rarecandy.renderer.animation.AnimationController;
import gg.generations.rarecandy.renderer.loading.ITexture;
import gg.generations.rarecandy.renderer.model.material.PipelineRegistry;
import gg.generations.rarecandy.renderer.pipeline.Pipeline;
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

public class Pipelines {
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
                            .supplyUniform("viewMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().viewMatrix()))
                            .supplyUniform("modelMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().transformationMatrix()))
                            .supplyUniform("projectionMatrix", (ctx) -> ctx.uniform().uploadMat4f(MinecraftClientGameProvider.projMatrix))
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
                            })
                            .prePostDraw(material -> {
                                material.cullType().enable();
                                if(material.blendType() == BlendType.Regular) {
                                    RenderSystem.enableBlend();
                                    RenderSystem.defaultBlendFunc();
                                }
                            }, material -> {
                                material.cullType().disable();
                                if(material.blendType() == BlendType.Regular) {
                                    RenderSystem.disableBlend();
                                }
                            });


                    var BASE = new Pipeline.Builder(ROOT)
                            .configure(Pipelines::addDiffuse)
                            .configure(Pipelines::addLight);

                    Pipeline.Builder layered_base = new Pipeline.Builder(BASE)
                            .shader(read(register.resourceManager, "shaders/block/animated.vs.glsl"), read(register.resourceManager, "shaders/block/layered.fs.glsl"))
                            .configure(Pipelines::baseColors)
                            .configure(Pipelines::emissionColors);

                    Pipeline layered = new Pipeline.Builder(layered_base)
                            .supplyUniform("frame", ctx -> ctx.uniform().uploadInt(-1))
                            .supplyUniform("layer", ctx -> {
                                var texture = ctx.getTexture("layer");

                                if (texture == null || isStatueMaterial(ctx.instance().variant())) texture = ITextureLoader.instance().getDarkFallback();


                                texture.bind(3);
                                ctx.uniform().uploadInt(3);
                            }).supplyUniform("mask", ctx -> {
                                var texture = ctx.getTexture("mask");

                                if (texture == null || isStatueMaterial(ctx.instance().variant())) texture = ITextureLoader.instance().getDarkFallback();

                                texture.bind(4);
                                ctx.uniform().uploadInt(4);
                            })
                            .build();

                    Pipeline solid = new Pipeline.Builder(BASE)
                            .shader(read(manager, "shaders/block/animated.vs.glsl"), read(manager, "shaders/block/solid.fs.glsl"))
                            .build();

                    Pipeline masked = new Pipeline.Builder(BASE)
                            .shader(read(manager, "shaders/block/animated.vs.glsl"), read(manager, "shaders/block/masked.fs.glsl"))
                            .supplyUniform("mask", ctx -> {

                                var texture = ctx.getTexture("mask");

                                if(texture == null) texture = ITextureLoader.instance().getDarkFallback();

                                texture.bind(3);
                                ctx.uniform().uploadInt(3);
                            })
                            .supplyUniform("color", ctx -> {
                                Vector3f color;
                                if (ctx.instance() instanceof ModelContextProviders.TintProvider tintProvider && tintProvider.getTint() != null)
                                    color = tintProvider.getTint();
                                else if(ctx.object().getMaterial(ctx.instance().variant()).getValue("color") != null)
                                    color = (Vector3f) ctx.object().getMaterial(ctx.instance().variant()).getValue("color");
                                else color = ONE;
                                ctx.uniform().uploadVec3f(color);
                            })
                            .build();

                            Pipeline paradox = new Pipeline.Builder(layered_base)
                                    .supplyUniform("frame", ctx -> ctx.uniform().uploadInt((int) ((MinecraftClientGameProvider.getTimePassed() * 200) % 16)))
                                    .build();

                            var map = Map.of("masked", masked, "layered", layered, "paradox", paradox, "solid", solid);

                            return s -> map.getOrDefault(s, solid);
                });
    }

    private static ITexture getTexture(String variant) {
        return ITextureLoader.instance().getTexture(variant);
    }

    private static void addDiffuse(Pipeline.Builder builder) {
        builder.supplyUniform("diffuse", ctx -> {
            var variant = ctx.instance().variant();

            ITexture texture = isStatueMaterial(variant) ? getTexture(variant.substring(7)) : ctx.object().getVariant(ctx.instance().variant()).getDiffuseTexture();
            if (texture == null) texture = ITextureLoader.instance().getNuetralFallback();

            texture.bind(0);
            ctx.uniform().uploadInt(0);
        });
    }

    private static void emissionColors(Pipeline.Builder builder) {
        builder.supplyUniform("emiColor1", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor1") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("emiColor2", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor2") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("emiColor3", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor3") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("emiColor4", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor4") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("emiColor5", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor5") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("emiIntensity1", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity1") instanceof Float vec ? vec : 0.0f))
                .supplyUniform("emiIntensity2", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity2") instanceof Float vec ? vec : 0.0f))
                .supplyUniform("emiIntensity3", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity3") instanceof Float vec ? vec : 0.0f))
                .supplyUniform("emiIntensity4", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity4") instanceof Float vec ? vec : 0.0f))
                .supplyUniform("emiIntensity5", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity5") instanceof Float vec ? vec : 0.0f));
    }

    private static void baseColors(Pipeline.Builder builder) {
        builder.supplyUniform("baseColor1", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor1") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("baseColor2", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor2") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("baseColor3", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor3") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("baseColor4", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor4") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform("baseColor5", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor5") instanceof Vector3f vec ? vec : Pipelines.ONE));
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

            if(texture == null) {
                texture = ITextureLoader.instance().getDarkFallback();
            }

            texture.bind(2);
            ctx.uniform().uploadInt(2);
        }).supplyUniform("useLight", ctx -> ctx.uniform().uploadBoolean(ctx.getValue("useLight") instanceof Boolean bool ? bool : true));
    }


    private static boolean isStatueMaterial(String variant) {
        return variant != null && variant.startsWith("statue:") && ((GenerationsCoreClient.GenerationsTextureLoader) gg.generations.rarecandy.tools.TextureLoader.instance()).has(variant.substring(7));
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
}