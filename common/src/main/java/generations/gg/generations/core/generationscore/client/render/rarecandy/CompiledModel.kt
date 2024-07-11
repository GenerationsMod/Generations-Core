package generations.gg.generations.core.generationscore.client.render.rarecandy

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferUploader
import generations.gg.generations.core.generationscore.GenerationsCore
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import gg.generations.rarecandy.renderer.components.MeshObject
import gg.generations.rarecandy.renderer.components.MultiRenderObject
import generations.gg.generations.core.generationscore.client.render.rarecandy.loading.GenerationsModelLoader
import generations.gg.generations.core.generationscore.util.TaskQueue
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import gg.generations.rarecandy.renderer.storage.ObjectManager
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.Resource
import java.io.IOException
import java.io.InputStream
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
    var guiInstance: CobblemonInstance? = null

    @JvmOverloads
    constructor(
        name: ResourceLocation,
        stream: Resource?,
        supplier: Supplier<MeshObject>?,
        requiresVariantTexture: Boolean = true
    ) {
        this.name = name

        queue.addTask {
            renderObject = loader.compiledModelMethod(this, stream?.open(), supplier, name.toString(), requiresVariantTexture)
        }
    }

    constructor() {
        renderObject = null
        name = GenerationsCore.id("dummy")
    }

    fun renderGui(instance: ObjectInstance) {
        if (renderObject == null) return
        RenderSystem.enableDepthTest()
        BufferUploader.reset()
        instance.viewMatrix().set(RenderSystem.getModelViewMatrix())

        ObjectManager.render(renderObject, instance)
    }

    fun render(instance: ObjectInstance) {
        if (renderObject == null) return
        render(instance, ModelRegistry.worldRareCandy.objectManager)
    }

    private fun render(instance: ObjectInstance, objectManager: ObjectManager) {
        if (!renderObject!!.isReady) return
//        Minecraft.getInstance().profiler.push("create_model_instance")
        objectManager.add(renderObject!!, instance)
//        Minecraft.getInstance().profiler.pop()
    }

    fun delete() {
        try {
            RenderSystem.recordRenderCall { renderObject?.close() }
        } catch (_: IOException) {
        }
    }

    companion object {
        val loader =
            GenerationsModelLoader(
                2
            );

        val queue = TaskQueue()

        @JvmStatic
        fun of(pair: ResourceLocation, resource: Resource): CompiledModel {
            return try {
                CompiledModel(pair, resource, { AnimatedMeshObject() })
            } catch (e: Exception) {
                val path = pair.toString()
                if (path.endsWith(".smdx") || path.endsWith(".pqc")) throw RuntimeException("Tried reading a 1.12 .smdx or .pqc")
                throw RuntimeException("Failed to load $path", e)
            }
        }

    }
}