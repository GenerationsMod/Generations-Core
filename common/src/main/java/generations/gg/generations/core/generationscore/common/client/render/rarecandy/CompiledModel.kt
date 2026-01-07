package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry.worldRareCandy
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading.GenerationsModelLoader
import generations.gg.generations.core.generationscore.common.util.TaskQueue
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.components.MultiRenderObject
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import gg.generations.rarecandy.renderer.rendering.RareCandy
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.Resource

/**
 * Represents a compiled model which can be rendered
 */
class CompiledModel(
    val name: ResourceLocation,
    stream: Resource?
) {
    @JvmField
    var renderObject: MultiRenderObject? = null
    val map: List<String> = ArrayList()

    @JvmField
    var guiInstance: CobblemonInstance? = null

    init {
        RareCandy.runLater {
            if(GenerationsCore.CONFIG.client.logModelLoading) GenerationsCore.LOGGER.info("Loading PK: $name")
            renderObject = GenerationsModelLoader.compiledModelMethod(this, stream?.open(), name.toString())
        }
    }

    fun render(instance: ObjectInstance) {
        if (renderObject == null) return
//        if (!renderObject!!.isReady) return

//        if(GenerationsCore.CONFIG.client.useVanilla) {
//            renderVanilla(instance, source)
//        } else {
            renderRareCandy(instance, ModelRegistry.worldRareCandy)
//        }
    }

    private fun renderRareCandy(instance: ObjectInstance, renderer: RareCandy) {
        renderObject?.run {
            renderer.add(this, instance)
            instance.use()
        }
    }

    fun delete() {
        if(GenerationsCore.CONFIG.client.logModelLoading) println("Deleting GPU Resources for: $name")
        renderObject?.close()
    }

    fun getVariantId(variant: String?): Int {
        return renderObject?.variantNameToId[variant] ?: 0
    }

    fun getAnimation(animation: String?): Animation? {
        val model = renderObject ?: return null

        return model.animationNameToId[animation]?.let { model.animations[it] }
    }

    companion object {
        val queue = TaskQueue()

        @JvmStatic
        fun init() {}

        @JvmStatic
        fun of(key: ResourceLocation): CompiledModel {
            return try {
                val resource = Minecraft.getInstance().resourceManager.getResourceOrThrow(key)

                CompiledModel(key, resource)
            } catch (e: Exception) {
                val path = key.toString()
                if (path.endsWith(".smdx") || path.endsWith(".pqc")) throw RuntimeException("Tried reading a 1.12 .smdx or .pqc")
                throw RuntimeException("Failed to load $path", e)
            }
        }

    }
}