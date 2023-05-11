package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.pokemod.rarecandy.pipeline.Pipeline;
import com.pokemod.rarecandy.storage.AnimatedObjectInstance;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Pipelines {
    private static final Map<String, Function<String, Pipeline>> PIPELINE_MAP = new HashMap<>();
    public static final Vector3f GLOBAL_LIGHT = new Vector3f(0, 2, 0);

    private static boolean initalized = false;

//    private static final Pipeline.Builder BASE = new Pipeline.Builder()
//            .supplyUniform("viewMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().viewMatrix()))
//            .supplyUniform("modelMatrix", ctx -> ctx.uniform().uploadMat4f(ctx.instance().transformationMatrix()))
//            .supplyUniform("projectionMatrix", (ctx) -> ctx.uniform().uploadMat4f(MinecraftClientGameProvider.projMatrix))
//            .supplyUniform("diffuse", ctx -> {
//                ctx.object().getMaterial(ctx.instance().materialId()).getDiffuseTexture().bind(0);
//                ctx.uniform().uploadInt(0);
//            });
//    private static final Pipeline.Builder LIGHTING_BASE = new Pipeline.Builder(BASE)
//            .supplyUniform("lightPosition", ctx -> ctx.uniform().uploadVec3f(GLOBAL_LIGHT))
//            .supplyUniform("reflectivity", ctx -> ctx.uniform().uploadFloat(0.01f))
//            .supplyUniform("shineDamper", ctx -> ctx.uniform().uploadFloat(0.01f))
//            .supplyUniform("diffuseColorMix", ctx -> ctx.uniform().uploadFloat(0.1f))
//            .supplyUniform("intColor", ctx -> ctx.uniform().uploadInt(0xFFFFFFFF));
//
//    //    public static final Pipeline STAT_UP = new Pipeline.Builder(LIGHTING_BASE)
////            .shader(read(GenerationsCore.id("shaders/stat_up/stat_up.vs.glsl")), read(GenerationsCore.id("shaders/stat_up/stat_up.fs.glsl")))
////            .supplyUniform("time", ctx -> ctx.uniform().uploadFloat((float) MinecraftClientGameProvider.getTimePassed() * 3000))
////            .prePostDraw(RenderSystem::enableBlend, RenderSystem::disableBlend)
////            .build();
////    public static final Pipeline STAT_DOWN = new Pipeline.Builder(LIGHTING_BASE)
////            .shader(read(GenerationsCore.id("shaders/stat_down/stat_down.vs.glsl")), read(GenerationsCore.id("shaders/stat_down/stat_down.fs.glsl")))
////            .supplyUniform("time", ctx -> ctx.uniform().uploadFloat((float) MinecraftClientGameProvider.getTimePassed() * 3000))
////            .prePostDraw(RenderSystem::enableBlend, RenderSystem::disableBlend)
////            .build();
//    public static final Pipeline ANIMATED = new Pipeline.Builder(LIGHTING_BASE)
//            .shader(read(GenerationsCore.id("shaders/animated/animated.vs.glsl")), read(GenerationsCore.id("shaders/animated/animated.fs.glsl")))
//            .supplyUniform("boneTransforms", ctx -> ctx.uniform().uploadMat4fs(((AnimatedObjectInstance) ctx.instance()).getTransforms()))
//            .build();
//    public static final Pipeline PIXELMON = new Pipeline.Builder(BASE)
//            .shader(read(GenerationsCore.id("shaders/animated/animated.vs.glsl")), read(GenerationsCore.id("shaders/animated/animated.fs.glsl")))
//            .supplyUniform("lightPosition", ctx -> ctx.uniform().uploadVec3f(GLOBAL_LIGHT))
//            .supplyUniform("reflectivity", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).reflectivity())) // 0.3f with coloured light for totems etc
//            .supplyUniform("shineDamper", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).shineDamper())) // 0.3f for this one too.
//            .supplyUniform("intColor", ctx -> ctx.uniform().uploadInt(((PixelmonInstance) ctx.instance()).lightColor()))
//            .supplyUniform("diffuseColorMix", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).diffuseColorMix()))
//            .supplyUniform("boneTransforms", ctx -> ctx.uniform().uploadMat4fs(((AnimatedObjectInstance) ctx.instance()).getTransforms()))
//            .build();
//    public static final Pipeline BLOCK = new Pipeline.Builder(BASE)
//            .shader(read(GenerationsCore.id("shaders/fullbright/fullbright.vs.glsl")), read(GenerationsCore.id("shaders/fullbright/fullbright.fs.glsl")))
//            .build();
////    public static final Pipeline ANIMATED_BLOCK = new Pipeline.Builder(BASE)
////            .shader(read(GenerationsCore.id("shaders/animated_fullbright/animated_fullbright.vs.glsl")), read(GenerationsCore.id("shaders/animated_fullbright/animated_fullbright.fs.glsl")))
////            .supplyUniform("boneTransforms", ctx -> ctx.uniform().uploadMat4fs(((AnimatedObjectInstance) ctx.instance()).getTransforms()))
////            .build();


    /**
     * Called on first usage of RareCandy to reduce lag later on
     */
    public static void onInitialize(ResourceManager manager) {
        if(!initalized) {
            PIPELINE_MAP.clear();
//        registerPipeline("stat_up", STAT_UP);
//        registerPipeline("stat_down", STAT_DOWN);


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

//    public static final Pipeline STAT_UP = new Pipeline.Builder(LIGHTING_BASE)
//            .shader(read(GenerationsCore.id("shaders/stat_up/stat_up.vs.glsl")), read(GenerationsCore.id("shaders/stat_up/stat_up.fs.glsl")))
//            .supplyUniform("time", ctx -> ctx.uniform().uploadFloat((float) MinecraftClientGameProvider.getTimePassed() * 3000))
//            .prePostDraw(RenderSystem::enableBlend, RenderSystem::disableBlend)
//            .build();
//    public static final Pipeline STAT_DOWN = new Pipeline.Builder(LIGHTING_BASE)
//            .shader(read(GenerationsCore.id("shaders/stat_down/stat_down.vs.glsl")), read(GenerationsCore.id("shaders/stat_down/stat_down.fs.glsl")))
//            .supplyUniform("time", ctx -> ctx.uniform().uploadFloat((float) MinecraftClientGameProvider.getTimePassed() * 3000))
//            .prePostDraw(RenderSystem::enableBlend, RenderSystem::disableBlend)
//            .build();
            var ANIMATED = new Pipeline.Builder(LIGHTING_BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/animated/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/animated/animated.fs.glsl")))
                    .supplyUniform("boneTransforms", ctx -> ctx.uniform().uploadMat4fs(((AnimatedObjectInstance) ctx.instance()).getTransforms()))
                    .build();
            var PIXELMON = new Pipeline.Builder(BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/animated/animated.vs.glsl")), read(manager, GenerationsCore.id("shaders/animated/animated.fs.glsl")))
                    .supplyUniform("lightPosition", ctx -> ctx.uniform().uploadVec3f(GLOBAL_LIGHT))
                    .supplyUniform("reflectivity", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).reflectivity())) // 0.3f with coloured light for totems etc
                    .supplyUniform("shineDamper", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).shineDamper())) // 0.3f for this one too.
                    .supplyUniform("intColor", ctx -> ctx.uniform().uploadInt(((PixelmonInstance) ctx.instance()).lightColor()))
                    .supplyUniform("diffuseColorMix", ctx -> ctx.uniform().uploadFloat(((PixelmonInstance) ctx.instance()).diffuseColorMix()))
                    .supplyUniform("boneTransforms", ctx -> ctx.uniform().uploadMat4fs(((AnimatedObjectInstance) ctx.instance()).getTransforms()))
                    .build();
            var BLOCK = new Pipeline.Builder(BASE)
                    .shader(read(manager, GenerationsCore.id("shaders/fullbright/fullbright.vs.glsl")), read(manager, GenerationsCore.id("shaders/fullbright/fullbright.fs.glsl")))
                    .build();
//    public static final Pipeline ANIMATED_BLOCK = new Pipeline.Builder(BASE)
//            .shader(read(GenerationsCore.id("shaders/animated_fullbright/animated_fullbright.vs.glsl")), read(GenerationsCore.id("shaders/animated_fullbright/animated_fullbright.fs.glsl")))
//            .supplyUniform("boneTransforms", ctx -> ctx.uniform().uploadMat4fs(((AnimatedObjectInstance) ctx.instance()).getTransforms()))
//            .build();


            registerPipeline("animated", ANIMATED);
            registerPipeline("pixelmon", PIXELMON);
            registerPipeline("fullbright", BLOCK);
//        registerPipeline("animated_fullbright", ANIMATED_BLOCK);

            initalized = true;
        }
    }

    public static void registerPipeline(String name, Pipeline pipeline) {
        PIPELINE_MAP.put(name, materials -> pipeline);
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