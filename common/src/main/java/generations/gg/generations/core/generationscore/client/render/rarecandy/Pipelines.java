package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import generations.gg.generations.core.generationscore.GenerationsCore;
import gg.generations.rarecandy.pokeutils.BlendType;
import gg.generations.rarecandy.pokeutils.CullType;
import gg.generations.rarecandy.pokeutils.reader.TextureLoader;
import gg.generations.rarecandy.renderer.animation.AnimationController;
import gg.generations.rarecandy.renderer.animation.GfbAnimationInstance;
import gg.generations.rarecandy.renderer.model.material.PipelineRegistry;
import gg.generations.rarecandy.renderer.pipeline.Pipeline;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import gg.generations.rarecandy.tools.gui.GuiPipelines;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL13C;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Pipelines {
    private static final Vector3f ONE = new Vector3f(0, 0, 0);
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
        var ROOT = new Pipeline.Builder()
                .supplyUniform("viewMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().viewMatrix()))
                .supplyUniform("modelMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().transformationMatrix()))
                .supplyUniform("projectionMatrix", (ctx) -> ctx.uniform().uploadMat4f(MinecraftClientGameProvider.projMatrix))
                .supplyUniform("boneTransforms", ctx -> {
                    var mats = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getTransforms() != null ? instance.getTransforms() : AnimationController.NO_ANIMATION : AnimationController.NO_ANIMATION;
                    ctx.uniform().uploadMat4fs(mats);
                })
                .supplyUniform("uvOffset", ctx -> {
                    if (ctx.instance() instanceof AnimatedObjectInstance instance) {
                        if (instance.currentAnimation instanceof GfbAnimationInstance gfbAnimation) {
                            ctx.uniform().uploadVec2f(gfbAnimation.getEyeOffset(ctx.getMaterial().getMaterialName()));
                        }
                        else ctx.uniform().uploadVec2f(AnimationController.NO_OFFSET);
                    }
                    else ctx.uniform().uploadVec2f(AnimationController.NO_OFFSET);
                });


        var BASE = new Pipeline.Builder(ROOT)
                .supplyUniform("diffuse", ctx -> {
                    var texture = ctx.object().getVariant(ctx.instance().variant()).getDiffuseTexture();

                    if(texture == null) {
                        System.out.printf("Error! Can't find %s!%n", "diffuse");
                        texture = TextureLoader.instance().getNuetralFallback();
                    }

                    texture.bind(0);
                    ctx.uniform().uploadInt(0);
                })
                .prePostDraw(material -> {
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

//        var LIGHTING_BASE = new Pipeline.Builder(BASE)
//                .supplyUniform("lightPosition", ctx -> ctx.uniform().uploadVec3f(GLOBAL_LIGHT))
//                .supplyUniform("reflectivity", ctx -> ctx.uniform().uploadFloat(0.01f))
//                .supplyUniform("shineDamper", ctx -> ctx.uniform().uploadFloat(0.01f))
//                .supplyUniform("diffuseColorMix", ctx -> ctx.uniform().uploadFloat(0.1f))
//                .supplyUniform("intColor", ctx -> ctx.uniform().uploadInt(0xFFFFFFFF));
//
//        register.register("animated", manager -> {
//            var pipeline = new Pipeline.Builder(LIGHTING_BASE)
//                    .shader(read(manager, GenerationsCore.id("shaders/animated/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/animated/animated.fs.glsl")))
//                    .supplyUniform("boneTransforms", ctx -> ctx.uniform().uploadMat4fs(((AnimatedObjectInstance) ctx.instance()).getTransforms()))
//                    .build();
//            return material -> pipeline;
//        });

//        register.register("pixelmon", manager -> {
//            var pipeline = new Pipeline.Builder(BASE)
//                    .shader(read(manager, GenerationsCore.id("shaders/animated/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/animated/animated.fs.glsl")))
//                    .supplyUniform("lightPosition", ctx -> ctx.uniform().uploadVec3f(GLOBAL_LIGHT))
//                    .supplyUniform("reflectivity", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).reflectivity())) // 0.3f with coloured light for totems etc
//                    .supplyUniform("shineDamper", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).shineDamper())) // 0.3f for this one too.
//                    .supplyUniform("intColor", ctx -> ctx.uniform().uploadInt(((PixelmonInstance) ctx.instance()).lightColor()))
//                    .supplyUniform("diffuseColorMix", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).diffuseColorMix()))
//                    .supplyUniform("boneTransforms", ctx -> {
//                        var matrices = ((AnimatedObjectInstance) ctx.instance()).getTransforms();
//
//                        ctx.uniform().uploadMat4fs(matrices != null ? matrices : AnimationController.NO_ANIMATION);
//                    })
//                    .build();
//            return material -> pipeline;
//        });

//        register.register("block", manager -> {
//            var BLOCK_BASE = new Pipeline.Builder(BASE)
//                        .supplyUniform("lightmap", ctx -> {
//                            GL13C.glActiveTexture(GL_TEXTURE0 + 2);
//                            ctx.uniform().uploadInt(2);
//                        })
//                        .supplyUniform("light", ctx -> {
//                            var light = ((BlockLightValueProvider) ctx.instance()).getLight();
//                            ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
//                        });
//
//
//
//            var solid = new Pipeline.Builder(BLOCK_BASE)
//                    .shader(read(manager, GenerationsCore.id("shaders/block/static.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/solid.fs.glsl")))
//                    .prePostDraw(t -> RenderSystem.enableBlend(), t1 -> Minecraft.getInstance().gameRenderer.lightTexture().turnOffLightLayer())
//                    .build();
//
//            var transparent = new Pipeline.Builder(BLOCK_BASE)
//                    .shader(read(manager, GenerationsCore.id("shaders/block/static.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/transparent.fs.glsl")))
//                    .supplyUniform("lightmap", ctx -> {
//                        GL13C.glActiveTexture('蓀' + 2);
//                        Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
//                        ctx.uniform().uploadInt(2);
//                    })
//                    .supplyUniform("light", ctx -> {
//                        var light = ((BlockLightValueProvider) ctx.instance()).getLight();
//                        ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
//                    })
//                    .supplyUniform("boneTransforms", ctx -> {
//                        var matrices = ctx.instance() instanceof AnimatedObjectInstance instance ? instance.getTransforms(): AnimationController.NO_ANIMATION;
//                        ctx.uniform().uploadMat4fs(matrices);
//                    })
//                    .prePostDraw(material -> {
//                        if (material.cullType() != CullType.None) {
//                            RenderSystem.enableCull();
//                            GL11.glFrontFace(material.cullType().getGlConstant());
//                        }
//                    }, material -> {
//                        Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
//                        if (material.cullType() != CullType.None) {
//                            RenderSystem.disableCull();
//                        }
//                    })
//                    .build();
//
//            return material -> material.equals("transparent") ? transparent : solid;
//        });

        register.register("animated_block",
                manager -> {
                    var LIGHT = new Pipeline.Builder(BASE)
                            .supplyUniform("lightmap", ctx -> {
                                ctx.uniform().uploadInt(1);
                                GL13C.glActiveTexture('蓀' + 1);
                                GL11C.glBindTexture(3553, ((ILightTexture) Minecraft.getInstance().gameRenderer.lightTexture()).getTextureId());
                                RenderSystem.texParameter(3553, 10241, 9729);
                                RenderSystem.texParameter(3553, 10240, 9729);
                            })
                            .supplyUniform("light", ctx -> {
                                var light = ((BlockLightValueProvider) ctx.instance()).getLight();
                                ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
                            });

                    var solid = new Pipeline.Builder(LIGHT)
                            .shader(read(manager, GenerationsCore.id("shaders/block/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/solid.fs.glsl")))
                            .build();

                    var masked = new Pipeline.Builder(LIGHT)
                            .shader(read(manager, GenerationsCore.id("shaders/block/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/masked.fs.glsl")))
                            .supplyUniform("mask", ctx -> {
                                ctx.object().getVariant(ctx.instance().variant()).getTexture("mask").bind(2);
                                ctx.uniform().uploadInt(2);
                            })
                            .supplyUniform("color", ctx -> {
                                var color = (Vector3f) ctx.object().getMaterial(ctx.instance().variant()).getValue("color");

                                ctx.uniform().uploadVec3f(color);
                            })
                            .build();

                    var transparent = new Pipeline.Builder(LIGHT)
                            .shader(read(manager, GenerationsCore.id("shaders/block/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/block/transparent.fs.glsl")))
                            .prePostDraw(material -> {
                                RenderSystem.enableBlend();
                                RenderSystem.defaultBlendFunc();
                            }, material -> RenderSystem.disableCull())
                            .build();

                    var layered = new Pipeline.Builder(LIGHT)
                            .shader(read(manager, "shaders/block/animated.vs.glsl"), read(manager, "shaders/block/layered.fs.glsl"))
                            .supplyUniform("baseColor1", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor1") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("baseColor2", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor2") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("baseColor3", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor3") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("baseColor4", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor4") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("baseColor5", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("baseColor5") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("emiColor1", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor1") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("emiColor2", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor2") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("emiColor3", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor3") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("emiColor4", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor4") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("emiColor5", ctx -> ctx.uniform().uploadVec3f(ctx.getValue("emiColor5") instanceof Vector3f vec ? vec : Pipelines.ONE))
                            .supplyUniform("emiIntensity1", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity1") instanceof Float vec ? vec : 0.0f))
                            .supplyUniform("emiIntensity2", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity2") instanceof Float vec ? vec : 0.0f))
                            .supplyUniform("emiIntensity3", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity3") instanceof Float vec ? vec : 0.0f))
                            .supplyUniform("emiIntensity4", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity4") instanceof Float vec ? vec : 0.0f))
                            .supplyUniform("emiIntensity5", ctx -> ctx.uniform().uploadFloat(ctx.getValue("emiIntensity5") instanceof Float vec ? vec : 0.0f))
                            .supplyUniform("layer", ctx -> {
                                var texture = ctx.getTexture("layer");

                                if (texture == null) texture = TextureLoader.instance().getDarkFallback();


                                texture.bind(2);
                                ctx.uniform().uploadInt(2);
                            }).supplyUniform("mask", ctx -> {
                                var texture = ctx.getTexture("mask");

                                if (texture == null) texture = TextureLoader.instance().getDarkFallback();

                                texture.bind(3);
                                ctx.uniform().uploadInt(3);
                            })
                            .build();

                    var unlit = new Pipeline.Builder(BASE)
                            .shader(read(manager, "shaders/block/animated.vs.glsl"), read(manager, "shaders/block/unlit.fs.glsl"))
                            .build();


                    return (Function<String, Pipeline>) s -> switch (s) {
                        case "transparent" -> transparent;
                        case "masked" -> masked;
                        case "unlit" -> unlit;
                        case "layered" -> layered;
                        default -> solid;
                    };
                });
    }

    public static Function<String, Pipeline> getPipeline(String name) {
        return PIPELINE_MAP.get(name);
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