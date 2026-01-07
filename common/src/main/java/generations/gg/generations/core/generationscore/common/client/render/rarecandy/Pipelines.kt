package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.google.gson.reflect.TypeToken
import com.mojang.blaze3d.systems.RenderSystem
import generations.gg.generations.core.generationscore.common.GenerationsCore
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader
import gg.generations.rarecandy.renderer.components.MultiRenderObject
import gg.generations.rarecandy.renderer.model.material.MaterialUploader
import gg.generations.rarecandy.renderer.pipeline.Pipelines
import gg.generations.rarecandy.renderer.pipeline.compute.ComputePipeline
import gg.generations.rarecandy.renderer.pipeline.traditional.TraditionalPipeline
import gg.generations.rarecandy.renderer.pipeline.util.Scope
import gg.generations.rarecandy.renderer.pipeline.util.TextureIdSupplier
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import gg.generations.rarecandy.renderer.textures.BlankTexture
import gg.generations.rarecandy.renderer.textures.ITexture
import gg.generations.rarecandy.renderer.textures.ITexture.Type
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.renderer.RenderStateShard.COLOR_DEPTH_WRITE
import net.minecraft.client.renderer.RenderStateShard.DEFAULT_LINE
import net.minecraft.client.renderer.RenderStateShard.DEFAULT_TEXTURING
import net.minecraft.client.renderer.RenderStateShard.LEQUAL_DEPTH_TEST
import net.minecraft.client.renderer.RenderStateShard.LIGHTMAP
import net.minecraft.client.renderer.RenderStateShard.MAIN_TARGET
import net.minecraft.client.renderer.RenderStateShard.NO_COLOR_LOGIC
import net.minecraft.client.renderer.RenderStateShard.NO_CULL
import net.minecraft.client.renderer.RenderStateShard.NO_LAYERING
import net.minecraft.client.renderer.RenderStateShard.NO_LIGHTMAP
import net.minecraft.client.renderer.RenderStateShard.OVERLAY
import net.minecraft.client.renderer.RenderStateShard.TRANSLUCENT_TRANSPARENCY
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.opengl.GL42
import org.lwjgl.opengl.GL43C
import java.util.function.Supplier
import kotlin.math.sin


object Pipelines {

    lateinit var PARADOX: ComputePipeline
    lateinit var TRANSFORM: ComputePipeline
    lateinit var MATERIAL: ComputePipeline

    val INSTANCE: RenderContext.Key<CobblemonInstance> = RenderContext.key(
        GenerationsCore.id("object_instance"), TypeToken.get(
            CobblemonInstance::class.java
        )
    )
    private val ONE = Vector3f(1f, 1f, 1f)
    private val ZERO = Vector3f(0f, 0f, 0f)

    private var useLegacy = false

    @JvmStatic
    fun toggleRendering() {
        useLegacy = !useLegacy
    }

    private const val MAIN = "main"

    private val TEMP = Vector4f()

//    fun createShader(manager: ResourceManager): Pipeline = Pipeline.Builder()
//        .supplyBooleanUniform("legacy") { useLegacy }
//        .supplyEnumUniform("FogShape", RenderSystem.getShaderFogShape())
//        .supplyMat4("viewMatrix") { MatrixCache.viewMatrix  }
//        .supplyMat4("modelMatrix") { it.instance().transformationMatrix() }
//        .supplyMat4("projectionMatrix") { MatrixCache.projectionMatrix }
//        .supplyVec2("uvOffset") { it.transform.offset() ?: Transform.DEFAULT_OFFSET }
//        .supplyVec2("uvScale") { it.transform.scale() ?: Transform.DEFAULT_SCALE }
//        .supplyMat4s("boneTransforms") { ctx -> ctx.instance().instanceOrNull<AnimatedObjectInstance>()?.transforms ?: AnimationController.NO_ANIMATION }
//
//        .supplyColorArray("ColorModulator") { RenderSystem.getShaderColor() }
//        .supplyFloatUniform("FogStart") { RenderSystem.getShaderFogStart() }
//        .supplyFloatUniform("FogEnd") { RenderSystem.getShaderFogEnd() }
//        .supplyColorArray("FogColor") { RenderSystem.getShaderFogColor() }
//
//        .supplyTexture("diffuse", 0) { it.instance().instanceOrNull<StatueInstance>()?.material?.let { GenerationsTextureLoader.getTextureOrNull(it) } ?: it.getTextureOrOther({ it.material.images().diffuse }) { ITextureLoader.instance().nuetralFallback } }
//        .supplyTexture("mask", 1) { it.getTextureOrOther({ it.material.images().mask }) { ITextureLoader.instance().darkFallback } }
//        .supplyTexture("layer", 2) { it.getTextureOrOther({ it.material.images().layer }) { ITextureLoader.instance().darkFallback } }
//        .supplyTexture("lightmap", 3) { Minecraft.getInstance().gameRenderer.lightTexture() as ITexture }
//        .supplyTexture("emission", 4) { it.getTextureOrOther({ it.material.images().emission }) { ITextureLoader.instance().darkFallback } }
//        .supplyTexture("paradoxMask", 5) { ITextureLoader.instance().getTexture("paradox_mask") }
//
//        .supplyInt("colorMethod") { it.material.colorMethod }
//        .supplyInt("effect") { it.material.effect }
//
//        .supplyUniform("light") { ctx: UniformUploadContext ->
//            val light = (ctx.instance() as BlockLightValueProvider).light
//            ctx.uniform().upload2i(light and 0xFFFF, light shr 16 and 0xFFFF)
//        }
//
//        .supplyVec3("tint") { it.instance().instanceOrNull<CobblemonInstance>()?.tint?.takeIf { it != ZERO } ?: ONE }
//
//        .supplyInt("frame") { pingpong(MinecraftClientGameProvider.getTimePassed()).toInt() }
//
//        .supplyVec3("baseColor1") { ctx -> ctx.instance().instanceOrNull<TintProvider>()?.tint ?: ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor1 ?: ONE }
//        .supplyVec3("baseColor2") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor2 ?: ONE }
//        .supplyVec3("baseColor3") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor3 ?: ONE }
//        .supplyVec3("baseColor4") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor4 ?: ONE }
//        .supplyVec3("baseColor5") { ctx -> ctx.takeIf { !it.isStatueMaterial }?.material?.values()?.baseColor5 ?: ONE }
//        .supplyVec3("emiColor1") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor1 ?: ONE }
//        .supplyVec3("emiColor2") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor2 ?: ONE }
//        .supplyVec3("emiColor3") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor3 ?: ONE }
//        .supplyVec3("emiColor4") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor4 ?: ONE }
//        .supplyVec3("emiColor5") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiColor5 ?: ONE }
//        .supplyFloatUniform("emiIntensity1") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity1 ?: 0.0f }
//        .supplyFloatUniform("emiIntensity2") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity2 ?: 0.0f }
//        .supplyFloatUniform("emiIntensity3") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity3 ?: 0.0f }
//        .supplyFloatUniform("emiIntensity4") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity4 ?: 0.0f }
//        .supplyFloatUniform("emiIntensity5") { it.takeIf { !it.isStatueMaterial }?.material?.values()?.emiIntensity5 ?: 1.0f }
//        .supplyBooleanUniform("useLight") { it.material.values().useLight }
//
//        .supplyVec3("Light0_Direction") { RenderSystem.shaderLightDirections[0] }
//        .supplyVec3("Light1_Direction") { RenderSystem.shaderLightDirections[1] }
//
//        .supplyBooleanUniform("useTera") {
//            val isActive = it.instance().instanceOrNull<CobblemonInstance>()?.teraActive ?: false
//            return@supplyBooleanUniform isActive
//        }
//        .supplyVec3("teraTint") { it.instance().instanceOrNull<CobblemonInstance>()?.teraTint?.takeIf { it != ZERO } ?: ONE }
//
//        .prePostDraw({ material ->
//            if (material.cullType() != CullType.None) {
//                RenderSystem.enableCull()
//            } else {
//                RenderSystem.disableCull()
//            }
//
//            if (material.blendType() == BlendType.Regular) {
//                RenderSystem.enableBlend()
//                RenderSystem.defaultBlendFunc()
//            } }, {*//* material ->
//                if (material.blendType() == BlendType.Regular) {
//                    RenderSystem.disableBlend()
//                }
//            *//*})
//        .shader(manager, "shaders/animated.vs.glsl", "shaders/animated.fs.glsl")
//        .build()


    var instanceId: Int = 0

    lateinit var textures: Array<ITexture>

    lateinit var vao: RareCandyVertexArray


    @JvmStatic
    fun transformVertices(`object`: MultiRenderObject, instanceId: Int) {
        this.instanceId = instanceId
        System.out.println("Blep1")
        TRANSFORM.useProgram()
        System.out.println("Blep2")
        TRANSFORM.bindGlobal()
        System.out.println("Blep3")
        TRANSFORM.bindModel(`object`)
        System.out.println("Blep4")
        TRANSFORM.dispatch(
            GL43C.GL_SHADER_STORAGE_BARRIER_BIT,
            (`object`.maxVertex + 255) / 256,
            `object`.meshes.size,
            1
        )
    }

    @JvmStatic
    fun processMaterial(instance: ObjectInstance, `object`: MultiRenderObject, mesh: Int) {
        MATERIAL.useProgram()
        MATERIAL.bindDraw(instance, `object`, mesh)
        MATERIAL.dispatch(GL42.GL_SHADER_IMAGE_ACCESS_BARRIER_BIT, 64,64,1)
    }

    fun opengGl() {
        vao = RareCandyVertexArray()
        textures = Array(3) {
            BlankTexture(Type.RGBA_BYTE, 1024, 1024, ITexture.ComputeAccess.READ_WRITE)
        }
        MaterialUploader.setup()
    }

    /**
     * Called on first usage of RareCandy to reduce lag later on
     */
    @JvmStatic
    fun onInitialize(manager: ResourceManager) {
        opengGl()
        TRANSFORM = manager.shader("transform")
            .addSSBORange(Scope.MODEL, "SrcBuffer", 0, {ctx -> ctx.`object`.modelBuffer}, {ctx -> ctx.`object`.vertex })
            .addSSBORange(Scope.MODEL, "IndexBuffer", 1, {ctx -> ctx.`object`.modelBuffer }, { ctx -> ctx.`object`.index })
            .addSSBORange(Scope.MODEL, "DrawCommands", 2, { ctx -> ctx.`object`.modelBuffer }, { ctx -> ctx.`object`.draw})
            .addSSBO(Scope.MODEL, "InstanceBuffer", 3, { ctx -> ctx.`object`.instanceBuffer.bufferId })
            .addSSBO(Scope.MODEL, "TransformBuffer", 4, { ctx -> ctx.`object`.uvTransformBuffer.bufferId })
            .addSSBO(Scope.MODEL, "DstBuffer", 5, { ctx -> ctx.`object`.destBuffer })
            .addUniform(Scope.MODEL, "variantSize", { uniform, ctx -> uniform.uploadInt(ctx.`object`.meshes.size) })
            .addUniform(Scope.GLOBAL, "instanceId", { uniform, ctx ->
                System.out.println("I'm a retard")
                uniform.uploadInt(instanceId)
                System.out.println("wtf")})
            .build()
        PARADOX = manager.shader("paradox")
            .autoInt(Scope.GLOBAL, "frame", { pingpong(MinecraftClientGameProvider.getTimePassed()) })
            .autoSampler2D(Scope.GLOBAL, "sampler", 0) { ITextureLoader.instance().getTexture("paradox_mask").id }
            .autoImage2D(Scope.GLOBAL, "outputTexture", 0) { textures[0] }
            .build()
        MATERIAL = manager.shader("material")
            .autoSampler2D(Scope.DRAW, "diffuse", 0, createMaterialTextureProvider(0))
            .autoSampler2D(Scope.DRAW, "emission", 1, createMaterialTextureProvider(1))
            .autoSampler2D(Scope.DRAW, "layer", 2, createMaterialTextureProvider(2))
            .autoSampler2D(Scope.DRAW, "mask", 3, createMaterialTextureProvider(3))
            .autoSampler2D(Scope.DRAW, "paradoxTexture", 4, { textures[0].id })
            .addUBO(Scope.DRAW, "Material", 0, { it.`object`().getMaterial(it.mesh(), it.instance().variant()).bindMaterial() })
            .autoImage2D(Scope.DRAW, "solidTex", 0, { textures[1] })
            .autoImage2D(Scope.DRAW, "litTex", 1, { textures[2] })
            .build()

    }

    @JvmStatic
    fun commonOn() {
        TRANSLUCENT_TRANSPARENCY.setupRenderState()
        LEQUAL_DEPTH_TEST.setupRenderState()
        NO_CULL.setupRenderState()
        OVERLAY.setupRenderState()
        NO_LAYERING.setupRenderState()
        MAIN_TARGET.setupRenderState()
        DEFAULT_TEXTURING.setupRenderState()
        COLOR_DEPTH_WRITE.setupRenderState()
        DEFAULT_LINE.setupRenderState()
        NO_COLOR_LOGIC.setupRenderState()
    }

    @JvmStatic
    fun commonOff() {
        TRANSLUCENT_TRANSPARENCY.clearRenderState()
        LEQUAL_DEPTH_TEST.clearRenderState()
        NO_CULL.clearRenderState()
        OVERLAY.clearRenderState()
        NO_LAYERING.clearRenderState()
        MAIN_TARGET.clearRenderState()
        DEFAULT_TEXTURING.clearRenderState()
        COLOR_DEPTH_WRITE.clearRenderState()
        DEFAULT_LINE.clearRenderState()
        NO_COLOR_LOGIC.clearRenderState()
    }

    @JvmStatic
    fun emissiveOn() {
        RenderSystem.setShaderTexture(0, textures[2].id)
        RenderSystem.setShader(Supplier { GameRenderer.getRendertypeEntityTranslucentEmissiveShader() })
        NO_LIGHTMAP.setupRenderState()
    }

    @JvmStatic
    fun emissiveOff() {
        NO_LIGHTMAP.clearRenderState()
    }

    @JvmStatic
    fun solidOn() {
        RenderSystem.setShaderTexture(0, textures[2].id)
        RenderSystem.setShader(Supplier { GameRenderer.getRendertypeEntityTranslucentShader() })
        LIGHTMAP.setupRenderState()
    }

    @JvmStatic
    fun solidOff() {
        LIGHTMAP.clearRenderState()
    }
}

private fun createMaterialTextureProvider(index: Int): TextureIdSupplier {
    return TextureIdSupplier { ctx ->
        val variant = ctx.instance().variant()
        val imageIndex = ctx.`object`().getMaterial(ctx.mesh(), variant).images()[index]

        val name = ctx.`object`().images[imageIndex]
        ITextureLoader.instance().getTexture(name).id
    }
}


fun ResourceManager.shader(vertex: String, fragment: String): TraditionalPipeline.Builder = Pipelines.traditional(read(this, "shaders/$vertex.vs.glsl".id()), read(this, "shaders/$fragment.fs.glsl".id()))
fun ResourceManager.shader(compute: String): ComputePipeline.Builder = Pipelines.compute(read(this, "shaders/$compute.cs.glsl".id()))




fun pingpong(time: Double): Int = (sin(time * Math.PI * 2) * 7 + 7).toInt()

//private fun Pipeline.Builder.supplyColorArray(name: String, function: (UniformUploadContext) -> FloatArray): Pipeline.Builder = this.supplyUniform(name) {
//    val color = function.invoke(it)
//    it.uniform().upload4f(color[0], color[1], color[2], color[3])
//}

//private fun Pipeline.Builder.supplyMat4s(name: String, function: (UniformUploadContext) -> Array<Matrix4f>): Pipeline.Builder = this.supplyUniform(name) { it.uniform().uploadMat4fs(function.invoke(it)) }
//private fun Pipeline.Builder.supplyMat4(name: String, function: (UniformUploadContext) -> Matrix4f): Pipeline.Builder = this.supplyUniform(name) { it.uniform().uploadMat4f(function.invoke(it)) }
//private fun Pipeline.Builder.supplyVec2(name: String, function: (UniformUploadContext) -> Vector2f): Pipeline.Builder = this.supplyUniform(name) { it.uniform().uploadVec2f(function.invoke(it)) }
//private fun Pipeline.Builder.supplyVec3(name: String, function: (UniformUploadContext) -> Vector3f): Pipeline.Builder = this.supplyUniform(name) { it.uniform().uploadVec3f(function.invoke(it))}
//private fun Pipeline.Builder.supplyFloatUniform(name: String, function: (UniformUploadContext) -> Float): Pipeline.Builder = this.supplyUniform(name) { it.uniform().uploadFloat(function.invoke(it)) }
//private fun Pipeline.Builder.supplyEnumUniform(name: String, value: Enum<*>): Pipeline.Builder = this.supplyInt(name) { value.ordinal }
//private fun Pipeline.Builder.supplyInt(name: String, function: (UniformUploadContext) -> Int): Pipeline.Builder = this.also { this.supplyUniform(name) { it.uniform().uploadInt(function.invoke(it)) } }

public inline fun <reified T> Any?.instanceOrNull(): T? = this as? T

//private val UniformUploadContext.isStatueMaterial: Boolean
//    get() = statueMaterial != null

//private val UniformUploadContext.statueMaterial: String?
//    get() = this.instance().instanceOrNull<StatueInstance>()?.material?.takeIf { GenerationsTextureLoader.has(it) }

//private val UniformUploadContext.transform: Transform
//    get() = this.instance().instanceOrNull<AnimatedObjectInstance>()?.getTransform(this.material.materialName)?.takeIf { !it.isUnit } ?: this.`object`().getTransform(this.instance().variant())


//private fun UniformUploadContext.getTextureOrOther(function: (UniformUploadContext) -> String?, supplier: () -> ITexture): ITexture = GenerationsTextureLoader.getTexture(function.invoke(this))?.takeUnless { texture -> texture === GenerationsTextureLoader.MissingTextureProxy } ?: supplier.invoke()

private fun String.id(): ResourceLocation = GenerationsCore.id(this)

//private fun Pipeline.Builder.supplyTexture(name: String, slot: Int, function: (UniformUploadContext) -> ITexture): Pipeline.Builder = this.supplyUniform(name) {
//    function.invoke(it).bind(slot)
//    it.uniform().uploadInt(slot)
//}

//private fun Pipeline.Builder.supplyBooleanUniform(name: String, function: (UniformUploadContext) -> Boolean): Pipeline.Builder { return this.supplyUniform(name) { it.uniform().uploadBoolean(function.invoke(it)) } }

fun read(manager: ResourceManager, name: ResourceLocation): String {
    try {
        manager.getResource(name).orElseThrow().open().use { `is` ->
            return String(`is`.readAllBytes())
        }
    } catch (e: Exception) {
        throw RuntimeException("Failed to read shader from resource location in shader: $name", e)
    }
}
