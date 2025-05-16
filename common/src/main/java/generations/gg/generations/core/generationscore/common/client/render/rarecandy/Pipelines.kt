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
import gg.generations.rarecandy.renderer.model.material.Material
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
import java.io.IOException
import java.util.function.Consumer
import java.util.function.Function
import kotlin.math.sin

object Pipelines {
    val INSTANCE: RenderContext.Key<CobblemonInstance> = RenderContext.key(
        GenerationsCore.id("object_instance"), TypeToken.get(
            CobblemonInstance::class.java
        )
    )
    private val ONE = Vector3f(1f, 1f, 1f)

    @JvmField
    var REGISTER: Event<Consumer<PipelineRegister>> = EventFactory.createConsumerLoop(
        PipelineRegister::class.java
    )
    private var useLegacy = false

    private val PIPELINE_MAP: MutableMap<String, Function<String, Pipeline?>> = HashMap()


    private var initialized = false

    @JvmStatic
    fun toggleRendering() {
        useLegacy = !useLegacy
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
        register.register(
            "main"
        ) { manager: ResourceManager -> createShaderMap(manager, true) }
        register.register(
            "legacy"
        ) { manager: ResourceManager -> createShaderMap(manager, false) }
    }

    fun createRoot(): Pipeline.Builder {
        val builder = Pipeline.Builder()
            .supplyUniform(
                "viewMatrix"
            ) { ctx: UniformUploadContext -> ctx.uniform().uploadMat4f(ctx.instance().viewMatrix()) }
            .supplyFloatUniform("FogStart") { RenderSystem.getShaderFogStart() }
            .supplyFloatUniform("FogEnd") { RenderSystem.getShaderFogEnd() }
            .supplyColorArray("FogColor") { RenderSystem.getShaderFogColor() }
            .supplyColorArray("ColorModulator") { RenderSystem.getShaderColor() }
            .supplyEnumUniform("FogShape", RenderSystem.getShaderFogShape())
            .supplyMat4("modelMatrix") { it.instance().transformationMatrix() }
            .supplyMat4("projectionMatrix") { RenderSystem.getProjectionMatrix() }
            .supplyMat4s("boneTransforms") { ctx -> ctx.instance.instanceOrNull<AnimatedObjectInstance>()?.transforms ?: AnimationController.NO_ANIMATION }
            .supplyVec2("uvOffset") { it.transform.offset() ?: Transform.DEFAULT_OFFSET }
            .supplyVec2("uvScale") { it.transform.scale() ?: Transform.DEFAULT_SCALE }
            .supplyTexture("diffuse", 0) {
                it.getTextureOrOther("diffuse") { ITextureLoader.instance().nuetralFallback }
            }
            .configure(::addLight)
            .supplyVec3("Light0_Direction") {
                RenderSystem.shaderLightDirections[0]
            }
            .supplyVec3("Light1_Direction") {
                RenderSystem.shaderLightDirections[1]
            }
            .supplyVec3("cobblemonTint") { it.instance.instanceOrNull<CobblemonInstance>()?.tint ?: ONE }
            .prePostDraw({ material: Material ->
                if (material.cullType() != CullType.None) {
                    RenderSystem.enableCull()
                } else {
                    RenderSystem.disableCull()
                }
                //                               material.cullType().enable();
                if (material.blendType() == BlendType.Regular) {
                    //                            BlendRecord.push();

                    RenderSystem.enableBlend()
                    RenderSystem.defaultBlendFunc()
                }
            }, { material: Material ->
//            if (material.cullType() != CullType.None) {
//                RenderSystem.enableCull();
//            }

//                                material.cullType().disable();
                if (material.blendType() == BlendType.Regular) {
                    RenderSystem.disableBlend()
                }
            })

        return builder
    }

    private fun createShaderMap(manager: ResourceManager, legacyShading: Boolean): Function<String, Pipeline?> {
        val shaderMap = HashMap<String, Pipeline>()

        val extension = if (legacyShading) "legacy" else "main"

        val base = createRoot()

        val effects = listOf("galaxy", "pastel", "paradox", "shadow", "sketch", "vintage", "passthrough")
        val colors = listOf("solid", "masked", "layered")

        for (effect in effects) {
            for (color in colors) {

                println("Current shader: $extension $color $effect")

                val builder = Pipeline.Builder(base).shader(manager, extension, effect, color)

                when (color) {
                    "masked" -> builder.masked()
                    "layered" -> builder.layered()
                }

                if (effect == "paradox" || effect == "galaxy") {
                    builder.paradox(color)
                }

                val suffix = if (effect != "passthrough") "_$effect" else ""

                shaderMap["$color$suffix"] = builder.build()
            }
        }

        val shader = shaderMap["solid"]

        return Function { s: String ->
            return@Function shaderMap.getOrDefault(s, shader)
        }
    }

    private fun Pipeline.Builder.layered(): Pipeline.Builder {
        return this
            .configure(::baseColors)
            .configure(::emissionColors)
            .supplyTexture("layer", 3) {
                ctx -> ctx.getTexture("layer").takeIf { !ctx.isStatueMaterial } ?: ITextureLoader.instance().darkFallback
            }
            .supplyTexture("mask", 4) {
                it.getTextureOrOther("mask") {
                    ITextureLoader.instance().darkFallback
                }
            }
    }

    private fun Pipeline.Builder.masked(): Pipeline.Builder {
        return this
            .supplyTexture("mask", 3) {
                it.getTextureOrOther("mask") { ITextureLoader.instance().darkFallback }
            }
            .supplyVec3("color") {
                var color = it.instance.instanceOrNull<TintProvider>()?.getTint() ?: it.getColorValue("color")


                color
            }
    }

    fun Pipeline.Builder.paradox(shader: String) {
        val slot = when (shader) {
            "masked" -> 4
            "layered" -> 5
            else -> 3
        }

        this.supplyInt("frame") {
            pingpong(MinecraftClientGameProvider.getTimePassed()).toInt()
        }
        .supplyTexture("paradoxMask", slot) {
            ITextureLoader.instance().getTexture("paradox_mask")
        }
    }

    fun pingpong(time: Double): Double {
        return (sin(time * Math.PI * 2) * 7 + 7).toInt().toDouble()
    }

    private fun emissionColors(builder: Pipeline.Builder) = builder
        .supplyVec3("emiColor1") {
            it.getColorValue("emiColor1")
        }
        .supplyVec3("emiColor2") {
            it.getColorValue("emiColor2")
        }
        .supplyVec3("emiColor3") {
            it.getColorValue("emiColor3")
        }
        .supplyVec3("emiColor4") {
            it.getColorValue("emiColor4")
        }
        .supplyVec3("emiColor5") {
            it.instance.instanceOrNull<TintProvider>()?.tint ?: it.getColorValue("emiColor5")
        }
        .supplyFloatUniform("emiIntensity1") {
            it.getFloatValue("emiIntensity1")
        }
        .supplyFloatUniform("emiIntensity2") {
            it.getFloatValue("emiIntensity2")
        }
        .supplyFloatUniform("emiIntensity3") {
            it.getFloatValue("emiIntensity3")
        }
        .supplyFloatUniform("emiIntensity4") {
            it.getFloatValue("emiIntensity4")
        }
        .supplyFloatUniform("emiIntensity5") {
            it.getFloatValue("emiIntensity5", 1.0f)
        }


    private fun baseColors(builder: Pipeline.Builder) = builder
        .supplyVec3("baseColor1") {
            it.getColorValue("baseColor1")
        }
        .supplyVec3("baseColor2") {
            it.getColorValue("baseColor2")
        }
        .supplyVec3("baseColor3") {
            it.getColorValue("baseColor3")
        }
        .supplyVec3("baseColor4") {
            it.getColorValue("baseColor4")
        }
        .supplyVec3("baseColor5") {
            it.getColorValue("baseColor5")
        }


    private fun UniformUploadContext.getColorValue(id: String): Vector3f {
        return (if (!this.isStatueMaterial) this.value<Vector3f>(id) else null) ?: ONE
    }

    private fun UniformUploadContext.getFloatValue(id: String, value: Float = 0.0f): Float {
        return (if (!this.isStatueMaterial) this.value<Float>(id) else null) ?: value
    }

    private fun UniformUploadContext.getTextureOrOther(name: String, function: () -> ITexture): ITexture = this.getTexture(name)?.takeUnless { texture -> this.isStatueMaterial || texture === GenerationsTextureLoader.MissingTextureProxy } ?: function.invoke()

    private fun addLight(builder: Pipeline.Builder) {
        builder
            .supplyTexture("lightmap", 1) { Minecraft.getInstance().gameRenderer.lightTexture() as ITexture }
            .supplyUniform("light") { ctx: UniformUploadContext ->
                val light = (ctx.instance() as BlockLightValueProvider).light
                ctx.uniform().upload2i(light and 0xFFFF, light shr 16 and 0xFFFF)
            }
            .supplyTexture("emission", 2) { it.getTextureOrOther("emission") { ITextureLoader.instance().darkFallback } }
            .supplyBooleanUniform("useLight") { it.value<Boolean>("useLight") ?: true }
    }


    private const val MAIN = "main"
    private const val LEGACY = "legacy"

    fun getPipeline(name: String): Pipeline {
        return PIPELINE_MAP[if (useLegacy) LEGACY else MAIN]!!.apply(name)!!
    }

    fun read(manager: ResourceManager, name: String): String {
        return read(manager, GenerationsCore.id(name))
    }

    fun read(manager: ResourceManager, name: String, lib: String): String {
        return read(manager, GenerationsCore.id(name), GenerationsCore.id(lib))
    }

    private fun read(manager: ResourceManager, name: ResourceLocation, lib: ResourceLocation): String {
        try {
            manager.getResource(name).orElseThrow().open().use { nameStream ->
                manager.getResource(lib).orElseThrow().open().use { libStream ->
                    val shaderContent = String(nameStream.readAllBytes())
                    val libContent = String(libStream.readAllBytes())
                    return shaderContent.replace("#process", libContent)
                }
            }
        } catch (e: IOException) {
            throw RuntimeException("Failed to read shader or library file from location location: $name, $lib", e)
        }
    }

    class PipelineRegister(
        private val resourceManager: ResourceManager,
        private val pipelines: MutableMap<String, Function<String, Pipeline?>>
    ) {
        fun register(name: String, function: Function<ResourceManager, Function<String, Pipeline?>>) {
            pipelines[name] = function.apply(resourceManager)
        }
    }

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
}

private fun Pipeline.Builder.supplyMat4s(name: String, function: (UniformUploadContext) -> Array<Matrix4f>): Pipeline.Builder {
    return this.supplyUniform(name) {
        it.uniform.uploadMat4fs(function.invoke(it))
    }
}

private fun Pipeline.Builder.supplyColorArray(name: String, function: (UniformUploadContext) -> FloatArray): Pipeline.Builder {
    return this.supplyUniform(name) {
        var color = function.invoke(it)
        it.uniform.upload4f(color[0], color[1], color[2], color[3])
    }
}

private fun Pipeline.Builder.supplyMat4(name: String, function: (UniformUploadContext) -> Matrix4f): Pipeline.Builder {
    return this.supplyUniform(name) {
        it.uniform.uploadMat4f(function.invoke(it))
    }
}

private fun Pipeline.Builder.supplyVec2(name: String, function: (UniformUploadContext) -> Vector2f): Pipeline.Builder = this.supplyUniform(name) { it.uniform.uploadVec2f(function.invoke(it)) }

private fun Pipeline.Builder.supplyVec3(name: String, function: (UniformUploadContext) -> Vector3f): Pipeline.Builder = this.supplyUniform(name) { it.uniform.uploadVec3f(function.invoke(it))}

private fun Pipeline.Builder.supplyFloatUniform(name: String, function: (UniformUploadContext) -> Float): Pipeline.Builder {
    return this.supplyUniform(name) { it.uniform.uploadFloat(function.invoke(it))}
}

private inline fun <reified T> UniformUploadContext.value(name: String): T? {
    return this.getValue(name).instanceOrNull<T>()
}

private fun Pipeline.Builder.supplyEnumUniform(name: String, value: Enum<*>): Pipeline.Builder = this.supplyInt(name) { value.ordinal }

private fun Pipeline.Builder.supplyInt(name: String, function: () -> Int): Pipeline.Builder = this.also { this.supplyUniform(name) { it.uniform.uploadInt(function.invoke()) } }

public inline fun <reified T> Any?.instanceOrNull(): T? {
    return if(this is T) this else null
}

private val UniformUploadContext.isStatueMaterial: Boolean
    get() {
        return statueMaterial != null
    }

private val UniformUploadContext.statueMaterial: String?
    get() {
        return this.instance.instanceOrNull<StatueInstance>()?.material?.takeIf { GenerationsTextureLoader.has(it) }
    }

private val UniformUploadContext.transform: Transform
    get() {
        return this.instance.instanceOrNull<AnimatedObjectInstance>()?.getTransform(this.material.materialName)?.takeIf { !it.isUnit } ?: this.`object`().getTransform(this.instance.variant())
    }

private fun Pipeline.Builder.shader(
    manager: ResourceManager,
    extension: String,
    effect: String,
    color: String,
): Pipeline.Builder {
    var shader = read(manager, "shaders/animated.fs.glsl", "process" shader effect, "color" shader color)

    return this.shader(
        read(manager, "shaders/animated.vs.glsl", "vert" shader extension),
        shader
    )
}

fun read(manager: ResourceManager, shaderSrc: String, vararg pairs: Pair<String, ResourceLocation>): String {
    var shader = read(manager, shaderSrc.id())

    for (pair in pairs) {
        shader = shader.replace("#${pair.first}", read(manager, pair.second))
    }

    return shader
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

private infix fun String.shader(option: String): Pair<String, ResourceLocation> = this to "shaders/$this/$option.lib.glsl".id()

private fun String.id(): ResourceLocation = GenerationsCore.id(this)

private fun Pipeline.Builder.supplyTexture(name: String, slot: Int, function: (UniformUploadContext) -> ITexture): Pipeline.Builder {
    return this.supplyUniform(name, {
        function.invoke(it).bind(slot)
        it.uniform().uploadInt(slot)
    })
}

private fun Pipeline.Builder.supplyBooleanUniform(name: String, function: (UniformUploadContext) -> Boolean): Pipeline.Builder {
    return this.supplyUniform(name) {
        it.uniform.uploadBoolean(function.invoke(it))
    }
}
