package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.cobblemon.mod.common.Cobblemon;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import gg.generations.rarecandy.arceus.model.pk.*;
import gg.generations.rarecandy.legacy.animation.AnimationController;
import gg.generations.rarecandy.legacy.pipeline.ITexture;
import gg.generations.rarecandy.legacy.pipeline.ShaderProgram;
import gg.generationsmod.rarecandy.model.config.pk.BlendType;
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

import static gg.generations.rarecandy.legacy.pipeline.ShaderProgram.Builder.UniformType.*;

public class Pipelines {
    private static final Vector3f ONE = new Vector3f(1, 1, 1);
    public static Event<Consumer<PipelineRegister>> REGISTER = EventFactory.createConsumerLoop(PipelineRegister.class);

    public static class PipelineRegister {
        private final ResourceManager resourceManager;
        private final Map<String, Function<String, ShaderProgram>> pipelines;

        public PipelineRegister(ResourceManager resourceManager, Map<String, Function<String, ShaderProgram>> pipelines) {
            this.resourceManager = resourceManager;
            this.pipelines = pipelines;
        }
        public void register(String name, Function<ResourceManager, Function<String, ShaderProgram>> function) {
            pipelines.put(name, function.apply(resourceManager));
        }
    }

    private static final Map<String, Function<String, ShaderProgram>> PIPELINE_MAP = new HashMap<>();
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
            var legacyShading = GenerationsCore.CONFIG.client.usePixelmonShading ? "legacy/" : "";
                    var ROOT = new ShaderProgram.Builder()
                            .supplyUniform(SHARED, "viewMatrix", ctx -> ctx.uniform().uploadMat4f(RenderSystem.getModelViewMatrix()))
                            .supplyUniform(INSTANCE, "modelMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().getTransform()))
                            .supplyUniform(SHARED, "projectionMatrix", (ctx) -> ctx.uniform().uploadMat4f(MinecraftClientGameProvider.projMatrix))
                            .supplyUniform(INSTANCE, "boneTransforms", ctx -> {
                                var mats = ctx.instance() instanceof MultiRenderObjectInstance<?> instance ? instance.getTransforms() != null ? instance.getTransforms() : AnimationController.NO_ANIMATION : AnimationController.NO_ANIMATION;
                                ctx.uniform().uploadMat4fs(mats);
                            })
                            .supplyUniform(INSTANCE, "uvOffset", ctx -> {
                                var offsets = ctx.instance() instanceof MultiRenderObjectInstance<?> instance ? instance.getOffset(ctx.material().name()) != null ? instance.getOffset(ctx.material().name()) : AnimationController.NO_OFFSET : AnimationController.NO_OFFSET;
                                ctx.uniform().uploadVec2f(offsets.offset());
                            }).supplyUniform(INSTANCE, "uvScale", ctx -> {
                                var offsets = ctx.instance() instanceof MultiRenderObjectInstance<?> instance ? instance.getOffset(ctx.material().name()) != null ? instance.getOffset(ctx.material().name()) : AnimationController.NO_OFFSET : AnimationController.NO_OFFSET;
                                ctx.uniform().uploadVec2f(offsets.scale());
                            });
                    
                    if(!legacyShading.isEmpty()) {
                          ROOT.supplyUniform(SHARED, "Light0_Direction", ctx -> ctx.uniform().uploadVec3f(RenderSystem.shaderLightDirections[0]))
                                  .supplyUniform(SHARED, "Light1_Direction", ctx -> ctx.uniform().uploadVec3f(RenderSystem.shaderLightDirections[1]));
                    }

                    ROOT.prePostMaterial(material -> {
                        if (material instanceof PkMaterial pkMaterial) {
                            pkMaterial.cullType().enable();
                            if (pkMaterial.blendType() == BlendType.Regular){
                                RenderSystem.enableBlend();
                                RenderSystem.defaultBlendFunc();
                            }
                        }
                    }, material -> {
                        if (material instanceof PkMaterial pkMaterial) {
                            pkMaterial.cullType().disable();
                            if (pkMaterial.blendType() == BlendType.Regular) {
                                RenderSystem.disableBlend();
                            }
                        }
                    });


                    var BASE = new ShaderProgram.Builder(ROOT)
                            .configure(Pipelines::addDiffuse)
                            .configure(Pipelines::addLight);

                    ShaderProgram.Builder layered_base = new ShaderProgram.Builder(BASE)
                            .shader(read(register.resourceManager, "shaders/block/" + legacyShading + "animated.vs.glsl"), read(register.resourceManager, "shaders/block/" + legacyShading + "layered.fs.glsl"))
                            .configure(Pipelines::baseColors)
                            .configure(Pipelines::emissionColors);

                    ShaderProgram layered = new ShaderProgram.Builder(layered_base)
                            .supplyUniform(SHARED, "frame", ctx -> ctx.uniform().uploadInt(-1))
                            .supplyUniform(MATERIAL, "layer", ctx -> {
                                var texture = ctx.material() instanceof PkMaterial material ? material.getTexture("layer") : null;

                                if (texture == null || isStatueMaterial(ctx.instance() instanceof MultiRenderObjectInstance<?> instance ? instance.getVariant() : null)) texture = ITextureLoader.instance().getDarkFallback();


                                texture.bind(3);
                                ctx.uniform().uploadInt(3);
                            }).supplyUniform(MATERIAL, "mask", ctx -> {
                                var texture = ctx.instance() instanceof PkMaterial material ? material.getTexture("mask") : null;

                                if (texture == null || isStatueMaterial(ctx.instance() instanceof MultiRenderObjectInstance<?> instance ? instance.getVariant() : null)) texture = ITextureLoader.instance().getDarkFallback();

                                texture.bind(4);
                                ctx.uniform().uploadInt(4);
                            })
                            .build();

                    ShaderProgram solid = new ShaderProgram.Builder(BASE)
                            .shader(read(manager, "shaders/block/" + legacyShading + "animated.vs.glsl"), read(manager, "shaders/block/" + legacyShading + "solid.fs.glsl"))
                            .build();

                    ShaderProgram masked = new ShaderProgram.Builder(BASE)
                            .shader(read(manager, "shaders/block/" + legacyShading + "animated.vs.glsl"), read(manager, "shaders/block/" + legacyShading + "masked.fs.glsl"))
                            .supplyUniform(MATERIAL, "mask", ctx -> {

                                var texture = ctx.instance() instanceof PkMaterial instance ? instance.getTexture("mask") : null;

                                if(texture == null) texture = ITextureLoader.instance().getDarkFallback();

                                texture.bind(3);
                                ctx.uniform().uploadInt(3);
                            })
                            .supplyUniform(MATERIAL, "color", ctx -> {
                                Vector3f color = ctx.instance() instanceof ModelContextProviders.TintProvider tintProvider && tintProvider.getTint() != null ? tintProvider.getTint() : ctx.instance().getMaterial() instanceof PkMaterial material && material.getValue("color") instanceof Vector3f vec ? vec : ONE;

                                ctx.uniform().uploadVec3f(color);
                            })
                            .build();

                            ShaderProgram paradox = new ShaderProgram.Builder(layered_base)
                                    .supplyUniform(SHARED, "frame", ctx -> ctx.uniform().uploadInt((int) ((MinecraftClientGameProvider.getTimePassed() * 200) % 16)))
                                    .build();

                            var map = Map.of("masked", masked, "layered", layered, "paradox", paradox, "solid", solid);

                            return s -> map.getOrDefault(s, solid);
                });
    }

    private static ITexture getTexture(String variant) {
        return ITextureLoader.instance().getTexture(variant);
    }

    private static void addDiffuse(ShaderProgram.Builder builder) {
        builder.supplyUniform(MATERIAL, "diffuse", ctx -> {
            var variant = ctx.instance() instanceof MultiRenderObjectInstance<?> instance ? instance.getVariant() : null;

            ITexture texture = isStatueMaterial(variant) ? getTexture(variant.substring(7)) : ctx.instance().getMaterial() instanceof PkMaterial material ? material.getDiffuseTexture() : null;
            if (texture == null) texture = ITextureLoader.instance().getNuetralFallback();

            texture.bind(0);
            ctx.uniform().uploadInt(0);
        });
    }

    private static void emissionColors(ShaderProgram.Builder builder) {
        builder.supplyUniform(MATERIAL, "emiColor1", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("emiColor1") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "emiColor2", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("emiColor2") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "emiColor3", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("emiColor3") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "emiColor4", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("emiColor4") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "emiColor5", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("emiColor5") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "emiIntensity1", ctx -> ctx.uniform().uploadFloat(ctx.material() instanceof PkMaterial material && material.getValue("emiIntensity1") instanceof Float vec ? vec : 0.0f))
                .supplyUniform(MATERIAL, "emiIntensity2", ctx -> ctx.uniform().uploadFloat(ctx.material() instanceof PkMaterial material && material.getValue("emiIntensity2") instanceof Float vec ? vec : 0.0f))
                .supplyUniform(MATERIAL, "emiIntensity3", ctx -> ctx.uniform().uploadFloat(ctx.material() instanceof PkMaterial material && material.getValue("emiIntensity3") instanceof Float vec ? vec : 0.0f))
                .supplyUniform(MATERIAL, "emiIntensity4", ctx -> ctx.uniform().uploadFloat(ctx.material() instanceof PkMaterial material && material.getValue("emiIntensity4") instanceof Float vec ? vec : 0.0f))
                .supplyUniform(MATERIAL, "emiIntensity5", ctx -> ctx.uniform().uploadFloat(ctx.material() instanceof PkMaterial material && material.getValue("emiIntensity5") instanceof Float vec ? vec : 0.0f));
    }

    private static void baseColors(ShaderProgram.Builder builder) {
        builder.supplyUniform(MATERIAL, "baseColor1", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("baseColor1") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "baseColor2", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("baseColor2") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "baseColor3", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("baseColor3") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "baseColor4", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("baseColor4") instanceof Vector3f vec ? vec : Pipelines.ONE))
                .supplyUniform(MATERIAL, "baseColor5", ctx -> ctx.uniform().uploadVec3f(ctx.material() instanceof PkMaterial material && material.getValue("baseColor5") instanceof Vector3f vec ? vec : Pipelines.ONE));
    }

    private static void addLight(ShaderProgram.Builder builder) {
        builder.supplyUniform(SHARED,"lightmap", ctx -> {
            ctx.uniform().uploadInt(1);
            GL13C.glActiveTexture('蓀' + 1);
            GL11C.glBindTexture(3553, ((ILightTexture) Minecraft.getInstance().gameRenderer.lightTexture()).getTextureId());
            RenderSystem.texParameter(3553, 10241, 9729);
            RenderSystem.texParameter(3553, 10240, 9729);
        }).supplyUniform(INSTANCE, "light", ctx -> {
            var light = ((BlockLightValueProvider) ctx.instance()).getLight();
            ctx.uniform().upload2i(light & 0xFFFF, light >> 16 & 0xFFFF);
        }).supplyUniform(MATERIAL, "emission", ctx -> {
            var texture = ctx.material() instanceof PkMaterial material ? material.getTexture("emission") : null;

            if(texture == null) {
                texture = ITextureLoader.instance().getDarkFallback();
            }

            texture.bind(2);
            ctx.uniform().uploadInt(2);
        }).supplyUniform(MATERIAL, "useLight", ctx -> ctx.uniform().uploadBoolean(ctx.instance().getMaterial() instanceof PkMaterial material && material.getValue("useLight") instanceof Boolean bool ? bool : true));
    }


    private static boolean isStatueMaterial(String variant) {
        return variant != null && variant.startsWith("statue:") && ((GenerationsCoreClient.GenerationsTextureLoader) TextureLoader.instance()).has(variant.substring(7));
    }

    public static ShaderProgram getPipeline(String name) {
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