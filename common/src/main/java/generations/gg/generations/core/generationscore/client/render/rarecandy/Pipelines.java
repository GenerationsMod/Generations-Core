package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import generations.gg.generations.core.generationscore.GenerationsCore;
import gg.generations.rarecandy.pokeutils.CullType;
import gg.generations.rarecandy.renderer.animation.AnimationController;
import gg.generations.rarecandy.renderer.model.material.Material;
import gg.generations.rarecandy.renderer.model.material.PipelineRegistry;
import gg.generations.rarecandy.renderer.pipeline.Pipeline;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13C;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Pipelines {
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
    public static final Vector3f GLOBAL_LIGHT = new Vector3f(0, 2, 0);

    private static boolean initalized = false;

    private static String activeShaderGroup = "animated_block";

    public static void setActiveShaderGroup(String group) {
        activeShaderGroup = PIPELINE_MAP.containsKey(group) ? group : "animated_block";
    }

    /**
     * Called on first usage of RareCandy to reduce lag later on
     */
    public static void onInitialize(ResourceManager manager) {
        if(!initalized) {
            REGISTER.invoker().accept(new PipelineRegister(manager, PIPELINE_MAP));
            initalized = true;
            PipelineRegistry.setFunction(s -> Pipelines.getPipeline(activeShaderGroup).apply(s));
        }
    }

    public static void initGenerationsPipelines(PipelineRegister register) {
        var BASE = new Pipeline.Builder()
                .supplyUniform("viewMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().viewMatrix()))
                .supplyUniform("modelMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().transformationMatrix()))
                .supplyUniform("projectionMatrix", (ctx) -> ctx.uniform().uploadMat4f(MinecraftClientGameProvider.projMatrix))
                .supplyUniform("diffuse", ctx -> {
                    ctx.object().getMaterial(ctx.instance().materialId()).getDiffuseTexture().bind(0);
                    ctx.uniform().uploadInt(0);
                });
        var LIGHTING_BASE = new Pipeline.Builder(BASE)
                .supplyUniform("lightPosition", ctx -> ctx.uniform().uploadVec3f(GLOBAL_LIGHT))
                .supplyUniform("reflectivity", ctx -> ctx.uniform().uploadFloat(0.01f))
                .supplyUniform("shineDamper", ctx -> ctx.uniform().uploadFloat(0.01f))
                .supplyUniform("diffuseColorMix", ctx -> ctx.uniform().uploadFloat(0.1f))
                .supplyUniform("intColor", ctx -> ctx.uniform().uploadInt(0xFFFFFFFF));

        register.register("animated", manager -> {
            var pipeline = new Pipeline.Builder(LIGHTING_BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/animated/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/animated/animated.fs.glsl")))
                    .supplyUniform("boneTransforms", ctx -> ctx.uniform().uploadMat4fs(((AnimatedObjectInstance) ctx.instance()).getTransforms()))
                    .build();
            return material -> pipeline;
        });

        register.register("pixelmon", manager -> {
            var pipeline = new Pipeline.Builder(BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/animated/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/animated/animated.fs.glsl")))
                    .supplyUniform("lightPosition", ctx -> ctx.uniform().uploadVec3f(GLOBAL_LIGHT))
                    .supplyUniform("reflectivity", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).reflectivity())) // 0.3f with coloured light for totems etc
                    .supplyUniform("shineDamper", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).shineDamper())) // 0.3f for this one too.
                    .supplyUniform("intColor", ctx -> ctx.uniform().uploadInt(((PixelmonInstance) ctx.instance()).lightColor()))
                    .supplyUniform("diffuseColorMix", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).diffuseColorMix()))
                    .supplyUniform("boneTransforms", ctx -> {
                        var matrices = ((AnimatedObjectInstance) ctx.instance()).getTransforms();

                        ctx.uniform().uploadMat4fs(matrices != null ? matrices : AnimationController.NO_ANIMATION);
                    })
                    .build();
            return material -> pipeline;
        });

        register.register("block", manager -> {
            var BLOCK_BASE = new Pipeline.Builder(BASE)
                        .supplyUniform("lightmap", ctx -> {
                            GL13C.glActiveTexture('蓀' + 1);
                            Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                            ctx.uniform().uploadInt(1);
                        })
                        .supplyUniform("light", ctx -> {
                            var light = ((BlockLightValueProvider) ctx.instance()).getLight();
                            ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
                        });

            var solid = new Pipeline.Builder(BLOCK_BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/block/static.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/solid.fs.glsl")))
                    .supplyUniform("lightmap", ctx -> {
                        GL13C.glActiveTexture('蓀' + 1);
                        Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                        ctx.uniform().uploadInt(1);
                    })
                    .supplyUniform("light", ctx -> {
                        var light = ((BlockLightValueProvider) ctx.instance()).getLight();
                        ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
                    })
                    .supplyUniform("boneTransforms", ctx -> {
                        var matrices = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getTransforms(): AnimationController.NO_ANIMATION;
                        ctx.uniform().uploadMat4fs(matrices);
                    })
                    .prePostDraw(material -> {
                        if (material.cullType() != CullType.None) {
                            RenderSystem.enableCull();
                            GL11.glFrontFace(material.cullType().getGlConstant());
                        }
                    }, material -> {
                        Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                        if (material.cullType() != CullType.None) {
                            RenderSystem.disableCull();
                        }
                    })
                    .build();

            var transparent = new Pipeline.Builder(BLOCK_BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/block/static.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/transparent.fs.glsl")))
                    .supplyUniform("lightmap", ctx -> {
                        GL13C.glActiveTexture('蓀' + 1);
                        Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                        ctx.uniform().uploadInt(1);
                    })
                    .supplyUniform("light", ctx -> {
                        var light = ((BlockLightValueProvider) ctx.instance()).getLight();
                        ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
                    })
                    .supplyUniform("boneTransforms", ctx -> {
                        var matrices = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getTransforms(): AnimationController.NO_ANIMATION;
                        ctx.uniform().uploadMat4fs(matrices);
                    })
                    .prePostDraw(material -> {
                        if (material.cullType() != CullType.None) {
                            RenderSystem.enableCull();
                            GL11.glFrontFace(material.cullType().getGlConstant());
                        }
                    }, material -> {
                        Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                        if (material.cullType() != CullType.None) {
                            RenderSystem.disableCull();
                        }
                    })
                    .build();

            return material -> material.equals("transparent") ? transparent : solid;
        });

        register.register("animated_block", manager -> {
            var BLOCK_BASE = new Pipeline.Builder(BASE)
                    .supplyUniform("lightmap", ctx -> {
                        GL13C.glActiveTexture('蓀' + 1);
                        Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                        ctx.uniform().uploadInt(1);
                    })
                    .supplyUniform("light", ctx -> {
                        var light = ((BlockLightValueProvider) ctx.instance()).getLight();
                        ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
                    })
                    .supplyUniform("boneTransforms", ctx -> {
                        var matrices = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getTransforms(): AnimationController.NO_ANIMATION;
                        ctx.uniform().uploadMat4fs(matrices);
                    })
                    .prePostDraw(material -> {
                        if (material.cullType() != CullType.None) {
                            RenderSystem.enableCull();
                            GL11.glFrontFace(material.cullType().getGlConstant());
                        }
                    }, material -> {
                        Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                        if (material.cullType() != CullType.None) {
                            RenderSystem.disableCull();
                        }
                    });

            var solid = new Pipeline.Builder(BLOCK_BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/block/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/solid.fs.glsl")))
                    .build();

            var transparent = new Pipeline.Builder(BLOCK_BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/block/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/transparent.fs.glsl")))
                    .build();

            return material -> material.equals("transparent") ? transparent : solid;
        });
    }

    public static Function<String, Pipeline> getPipeline(String name) {
        return PIPELINE_MAP.get(name);
    }

    public static String read(ResourceManager manager, ResourceLocation name) {
        try (var is = manager.getResource(name).orElseThrow().open()) {
            return new String(is.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read shader from resource location in shader: " + name, e);
        }
    }
}