package generations.gg.generations.core.generationscore.client.render.rarecandy

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferUploader
import generations.gg.generations.core.generationscore.GenerationsCore
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import gg.generations.rarecandy.renderer.components.MeshObject
import gg.generations.rarecandy.renderer.components.MultiRenderObject
import gg.generations.rarecandy.renderer.loading.GenerationsModelLoader
import gg.generations.rarecandy.renderer.loading.ModelLoader
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import gg.generations.rarecandy.renderer.rendering.RenderStage
import gg.generations.rarecandy.renderer.storage.ObjectManager
import gg.generations.rarecandy.tools.TextureLoader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.Resource
import org.joml.Matrix4f
import java.io.IOException
import java.io.InputStream
import java.util.function.Consumer
import java.util.function.Supplier

/**
 * Represents a compiled model which can be rendered
 */
class CompiledModel {
    @JvmField
    var renderObject: MultiRenderObject<MeshObject>? = null
    val name: ResourceLocation
    val map: List<String> = ArrayList()

    @JvmField
    var guiInstance: PixelmonInstance? = null

    @JvmOverloads
    constructor(
        name: ResourceLocation,
        stream: InputStream?,
        supplier: Supplier<MeshObject>?,
        requiresVariantTexture: Boolean = true
    ) {
        this.name = name

        renderObject = loader.compiledModelMethod(this, stream, supplier, name.toString(), requiresVariantTexture)
    }

    constructor() {
        renderObject = null
        name = GenerationsCore.id("dummy")
    }

    fun renderGui(instance: ObjectInstance, projectionMatrix: Matrix4f) {
        if (renderObject == null) return
        RenderSystem.enableDepthTest()
        BufferUploader.reset()
        RenderSystem.applyModelViewMatrix()
        instance.viewMatrix().set(RenderSystem.getModelViewMatrix())
        render(instance, projectionMatrix, ModelRegistry.getGuiRareCandy().objectManager)
    }

    fun render(instance: ObjectInstance, projectionMatrix: Matrix4f) {
        if (renderObject == null) return
        render(instance, projectionMatrix, ModelRegistry.getWorldRareCandy().objectManager)
    }

    private fun render(instance: ObjectInstance, projectionMatrix: Matrix4f, objectManager: ObjectManager) {
        if (!renderObject!!.isReady) return
        Minecraft.getInstance().profiler.push("create_model_instance")
        MinecraftClientGameProvider.projMatrix = projectionMatrix
        objectManager.add(renderObject!!, instance)
        Minecraft.getInstance().profiler.pop()
    }

    fun delete() {
        try {
            renderObject!!.close()
        } catch (e: IOException) {
        }
    }

    companion object {
        val loader = GenerationsModelLoader(2);
        @JvmStatic
        fun of(pair: ResourceLocation, resource: Resource): CompiledModel {
            return try {
                val `is` = resource.open()
                CompiledModel(pair, `is`, { AnimatedMeshObject() })
            } catch (e: Exception) {
                val path = pair.toString()
                if (path.endsWith(".smdx") || path.endsWith(".pqc")) throw RuntimeException("Tried reading a 1.12 .smdx or .pqc")
                throw RuntimeException("Failed to load $path", e)
            }
        }

        val DUMMY = CompiledModel()
    }
}