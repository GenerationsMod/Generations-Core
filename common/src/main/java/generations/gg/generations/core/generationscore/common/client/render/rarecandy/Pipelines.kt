package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.google.gson.reflect.TypeToken
import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import dev.architectury.event.Event
import dev.architectury.event.EventFactory
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.GenerationsTextureLoader
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.TintProvider
import gg.generations.rarecandy.pokeutils.BlendType
import gg.generations.rarecandy.pokeutils.CullType
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader
import gg.generations.rarecandy.renderer.animation.AnimationController
import gg.generations.rarecandy.renderer.animation.Transform
import gg.generations.rarecandy.renderer.loading.ITexture
import gg.generations.rarecandy.renderer.model.material.PipelineRegistry
import gg.generations.rarecandy.renderer.pipeline.Pipeline
import gg.generations.rarecandy.renderer.pipeline.UniformUploadContext
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import java.util.function.Consumer
import kotlin.math.sin

object Pipelines {
    val INSTANCE: RenderContext.Key<CobblemonInstance> = RenderContext.key(
        GenerationsCore.id("object_instance"), TypeToken.get(
            CobblemonInstance::class.java
        )
    )
    private val ONE = Vector3f(1f, 1f, 1f)
    private val ZERO = Vector3f(0f, 0f, 0f)


    @JvmField
    var REGISTER: Event<Consumer<PipelineRegister>> = EventFactory.createConsumerLoop(
        PipelineRegister::class.java
    )
    private var useLegacy = false

    private val PIPELINE_MAP: MutableMap<String, (String) -> Pipeline?> = HashMap()


    private var initialized = false

    @JvmStatic
    fun toggleRendering() {
        useLegacy = !useLegacy
    }

    private const val MAIN = "main"

    fun getPipeline(name: String): Pipeline {
        return PIPELINE_MAP[MAIN]!!.invoke(name)!!
    }

    /**
     * Called on first usage of RareCandy to reduce lag later on
     */
    @JvmStatic
    fun onInitialize(manager: ResourceManager) {
        if (!initialized) {
            REGISTER.invoker().accept(PipelineRegister(manager, PIPELINE_MAP))
            initialized = true
            useLegacy = GenerationsCore.CONFIG.client.usePixelmonShading
            PipelineRegistry.setFunction(::getPipeline)
        }
    }

    private val TEMP = Vector4f()

    @JvmStatic
    fun initGenerationsPipelines(register: PipelineRegister) {
        register.register("main") { manager ->
            val pipeline = createShader(manager)
            return@register { pipeline }
        }
    }

    class PipelineRegister(
        private val resourceManager: ResourceManager,
        private val pipelines: MutableMap<String, (String) -> Pipeline?>
    ) {
        fun register(name: String, function: (ResourceManager) -> (String) -> Pipeline?) {
            pipelines[name] = function.invoke(resourceManager)
        }
    }

    fun createShader(manager: ResourceManager): Pipeline = Pipeline.Builder()
        .supplyBooleanUniform("legacy") { useLegacy }
        .supplyEnumUniform("FogShape", RenderSystem.getShaderFogShape())
        .supplyMat4("viewMatrix") { RenderSystem.getModelViewMatrix() }
        .supplyMat4("modelMatrix") { it.instance().transformationMatrix() }
        .supplyMat4("projectionMatrix") { RenderSystem.getProjectionMatrix() }
        .supplyVec2("uvOffset") { it.transform.offset() ?: Transform.DEFAULT_OFFSET }
        .supplyVec2("uvScale") { it.transform.scale() ?: Transform.DEFAULT_SCALE }
        .supplyMat4s("boneTransforms") { ctx -> ctx.instance.instanceOrNull<AnimatedObjectInstance>()?.transforms ?: AnimationController.NO_ANIMATION }

        .supplyColorArray("ColorModulator") { RenderSystem.getShaderColor() }
        .supplyFloatUniform("FogStart") { RenderSystem.getShaderFogStart() }
        .supplyFloatUniform("FogEnd") { RenderSystem.getShaderFogEnd() }
        .supplyColorArray("FogColor") { RenderSystem.getShaderFogColor() }

        .supplyTexture("diffuse", 0) { it.instance.instanceOrNull<StatueInstance>()?.material?.let { GenerationsTextureLoader.getTextureOrNull(it) } ?: it.getTextureOrOther({ it.material.images().diffuse }) { ITextureLoader.instance().nuetralFallback } }
        .supplyTexture("mask", 1) { it.getTextureOrOther({ it.material.images().mask }) { ITextureLoader.instance().darkFallback } }
        .supplyTexture("layer", 2) { it.getTextureOrOther({ it.material.images().layer }) { ITextureLoader.instance().darkFallback } }
        .supplyTexture("lightmap", 3) { Minecraft.getInstance().gameRenderer.lightTexture() as ITexture }
        .supplyTexture("emission", 4) { it.getTextureOrOther({ it.material.images().emission }) { ITextureLoader.instance().darkFallback } }
        .supplyTexture("paradoxMask", 5) { ITextureLoader.instance().getTexture("paradox_mask") }

        .supplyInt("colorMethod") { it.material.colorMethod }
        .supplyInt("effect") { it.material.effect }

        .supplyUniform("light") { ctx: UniformUploadContext ->
            val light = (ctx.instance() as BlockLightValueProvider).light
            ctx.uniform().upload2i(light and 0xFFFF, light shr 16 and 0xFFFF)
        }

        .supplyVec3("tint") { /*it.instance.instanceOrNull<CobblemonInstance>()?.tint?.takeIf { it != ZERO } ?:*/ ONE }

        .supplyInt("frame") { pingpong(MinecraftClientGameProvider.getTimePassed()).toInt() }

        .supplyVec3("baseColor1") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor1 ?: ONE }
        .supplyVec3("baseColor2") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor2 ?: ONE }
        .supplyVec3("baseColor3") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor3 ?: ONE }
        .supplyVec3("baseColor4") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor4 ?: ONE }
        .supplyVec3("baseColor5") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor5 ?: ONE }
        .supplyVec3("emiColor1") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor1 ?: ONE }
        .supplyVec3("emiColor2") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor2 ?: ONE }
        .supplyVec3("emiColor3") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor3 ?: ONE }
        .supplyVec3("emiColor4") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor4 ?: ONE }
        .supplyVec3("emiColor5") { it.instance.instanceOrNull<TintProvider>()?.tint ?: it.material.values().emiColor5 }
        .supplyFloatUniform("emiIntensity1") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity1 ?: 0.0f }
        .supplyFloatUniform("emiIntensity2") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity2 ?: 0.0f }
        .supplyFloatUniform("emiIntensity3") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity3 ?: 0.0f }
        .supplyFloatUniform("emiIntensity4") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity4 ?: 0.0f }
        .supplyFloatUniform("emiIntensity5") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity5 ?: 1.0f }
        .supplyBooleanUniform("useLight") { it.material.values().useLight }

        .supplyVec3("Light0_Direction") { RenderSystem.shaderLightDirections[0] }
        .supplyVec3("Light1_Direction") { RenderSystem.shaderLightDirections[1] }

        .supplyBooleanUniform("useTera") {
            val isActive = it.instance.instanceOrNull<CobblemonInstance>()?.teraActive ?: false
            return@supplyBooleanUniform isActive
        }

        .prePostDraw({ material ->
            if (material.cullType() != CullType.None) {
                RenderSystem.enableCull()
            } else {
                RenderSystem.disableCull()
            }

            if (material.blendType() == BlendType.Regular) {
                RenderSystem.enableBlend()
                RenderSystem.defaultBlendFunc()
            } }, { material ->
                if (material.blendType() == BlendType.Regular) {
                    RenderSystem.disableBlend()
                }
            })
        .shader(manager, "shaders/animated.vs.glsl", "shaders/animated.fs.glsl")
        .build()
    }

    fun Pipeline.Builder.shader(manager: ResourceManager, vertex: String, fragment: String): Pipeline.Builder =
        shader(read(manager, vertex.id()), read(manager, fragment.id()))


    fun pingpong(time: Double): Double = (sin(time * Math.PI * 2) * 7 + 7).toInt().toDouble()

    private fun Pipeline.Builder.supplyColorArray(name: String, function: (UniformUploadContext) -> FloatArray): Pipeline.Builder = this.supplyUniform(name) {
        val color = function.invoke(it)
        it.uniform.upload4f(color[0], color[1], color[2], color[3])
    }

    private fun Pipeline.Builder.supplyMat4s(name: String, function: (UniformUploadContext) -> Array<Matrix4f>): Pipeline.Builder = this.supplyUniform(name) { it.uniform.uploadMat4fs(function.invoke(it)) }
    private fun Pipeline.Builder.supplyMat4(name: String, function: (UniformUploadContext) -> Matrix4f): Pipeline.Builder = this.supplyUniform(name) { it.uniform.uploadMat4f(function.invoke(it)) }
    private fun Pipeline.Builder.supplyVec2(name: String, function: (UniformUploadContext) -> Vector2f): Pipeline.Builder = this.supplyUniform(name) { it.uniform.uploadVec2f(function.invoke(it)) }
    private fun Pipeline.Builder.supplyVec3(name: String, function: (UniformUploadContext) -> Vector3f): Pipeline.Builder = this.supplyUniform(name) { it.uniform.uploadVec3f(function.invoke(it))}
    private fun Pipeline.Builder.supplyFloatUniform(name: String, function: (UniformUploadContext) -> Float): Pipeline.Builder = this.supplyUniform(name) { it.uniform.uploadFloat(function.invoke(it)) }
    private fun Pipeline.Builder.supplyEnumUniform(name: String, value: Enum<*>): Pipeline.Builder = this.supplyInt(name) { value.ordinal }
    private fun Pipeline.Builder.supplyInt(name: String, function: (UniformUploadContext) -> Int): Pipeline.Builder = this.also { this.supplyUniform(name) { it.uniform.uploadInt(function.invoke(it)) } }
    public inline fun <reified T> Any?.instanceOrNull(): T? = if(this is T) this else null

    private val UniformUploadContext.isStatueMaterial: Boolean
        get() = statueMaterial != null

    private val UniformUploadContext.statueMaterial: String?
        get() = this.instance.instanceOrNull<StatueInstance>()?.material?.takeIf { GenerationsTextureLoader.has(it) }

    private val UniformUploadContext.transform: Transform
        get() = this.instance.instanceOrNull<AnimatedObjectInstance>()?.getTransform(this.material.materialName)?.takeIf { !it.isUnit } ?: this.`object`().getTransform(this.instance.variant())


    private fun UniformUploadContext.getTextureOrOther(function: (UniformUploadContext) -> String?, supplier: () -> ITexture): ITexture = GenerationsTextureLoader.getTexture(function.invoke(this))?.takeUnless { texture -> texture === GenerationsTextureLoader.MissingTextureProxy } ?: supplier.invoke()

    private fun String.id(): ResourceLocation = GenerationsCore.id(this)

    private fun Pipeline.Builder.supplyTexture(name: String, slot: Int, function: (UniformUploadContext) -> ITexture): Pipeline.Builder = this.supplyUniform(name) {
        function.invoke(it).bind(slot)
        it.uniform().uploadInt(slot)
    }

    private fun Pipeline.Builder.supplyBooleanUniform(name: String, function: (UniformUploadContext) -> Boolean): Pipeline.Builder { return this.supplyUniform(name) { it.uniform.uploadBoolean(function.invoke(it)) } }

    private object BlendRecord {
        var enabled: Boolean = false
        var srcRgb: Int = 0
        var dstRgb: Int = 0
        var srcAlpha: Int = 0
        var dstAlpha: Int = 0

        fun push() {
            enabled = GlStateManager.BLEND.mode.enabled
            srcRgb = GlStateManager.BLEND.srcRgb
            dstRgb = GlStateManager.BLEND.dstRgb
            srcAlpha = GlStateManager.BLEND.srcAlpha
            dstAlpha = GlStateManager.BLEND.dstAlpha
        }

        fun pop() {
            if (enabled) RenderSystem.enableBlend()
            else RenderSystem.disableBlend()

            RenderSystem.blendFuncSeparate(srcRgb, dstRgb, srcAlpha, dstAlpha)
        }
    }

fun read(manager: ResourceManager, name: ResourceLocation): String {
    try {
        manager.getResource(name).orElseThrow().open().use { `is` ->
            return String(`is`.readAllBytes())
        }
    } catch (e: Exception) {
        throw RuntimeException("Failed to read shader from resource location in shader: $name", e)
    }
}

