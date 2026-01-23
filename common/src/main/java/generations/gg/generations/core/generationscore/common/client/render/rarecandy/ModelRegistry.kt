package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import com.mojang.blaze3d.pipeline.RenderCall
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.AngleProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CompiledModel.Companion.of
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import gg.generations.rarecandy.renderer.rendering.RareCandy
import gg.generations.rarecandy.renderer.rendering.RenderStage
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import java.util.concurrent.ConcurrentLinkedQueue

object ModelRegistry {
    val TASKS = ConcurrentLinkedQueue<Runnable>()
    private val modelsToLoad = mutableListOf<ResourceLocation>()
    private val models = mutableMapOf<ResourceLocation, CompiledModel>()
    private val modelsToUnload = mutableListOf<ResourceLocation>()

    @JvmStatic
    operator fun get(modelProvider: ModelContextProviders.ModelProvider): CompiledModel? {
        return modelProvider.model?.let(::get)
    }

    @JvmStatic
    fun clear() {
        runLater {
           modelsToUnload.clear()
           modelsToLoad.clear()

           for(model in models.values) {
               model.delete()
           }

           models.clear()
        }
    }

    fun ensureOnRenderThread(runnable: RenderCall) {
        if (RenderSystem.isOnRenderThread()) {
            runnable.execute()
        } else {
            RenderSystem.recordRenderCall(runnable)
        }
    }

    fun tick(time: Double) {
        var task = TASKS.poll()
        while (task != null) {
            task.run()
            task = TASKS.poll()
        }

        for ((key, model) in models) {
            model.update(time)
            model.takeIf({ it.empty })?.run { modelsToUnload.add(key) }
        }

        if(modelsToLoad.isEmpty() and modelsToUnload.isEmpty()) return

        ensureOnRenderThread {
            if(modelsToLoad.isNotEmpty()) {
                for (key in modelsToLoad) {
                    models[key] = of(key);
                }
                modelsToLoad.clear()
            }

            if(modelsToLoad.isNotEmpty()) {
                for (key in modelsToUnload) {
                    models[key]?.also {
                        it.delete()

                    }
                    models.remove(key)
                }

                modelsToUnload.clear()
            }
        }
    }

    @JvmStatic
    operator fun get(location: ResourceLocation): CompiledModel? {
        return try {
            if(modelsToLoad.contains(location)) null;
            else {
                models[location] ?: run { modelsToLoad += location }.let { null }
            }
        } catch (e: Exception) {
            null
        }
    }

    @JvmStatic
    fun init() {
        CompiledModel.init()
    }

    fun render(stage: RenderStage) {
        models.values.forEach {
            it.render(stage)
        }
    }

    fun prepForBER(stack: PoseStack, supplier: AngleProvider) {
        stack.translate(0.5f, 0.0f, 0.5f)
        if (supplier is ModelProvidingBlockEntity) {
            val block = supplier.blockState.block

            if (block is GenericRotatableModelBlock && block.shouldRotateSpecial()) {
                val forward: Direction = supplier.blockState.getValue(GenericRotatableModelBlock.FACING)
                val x: Int = block.getWidthValue(supplier.blockState)
                val z: Int = block.getLengthValue(supplier.blockState)
                val width: Float = block.width * 0.5f - x
                val length: Float = block.length * 0.5f - z
                when (forward) {
                    Direction.SOUTH -> stack.translate(width, 0f, -length)
                    Direction.EAST -> stack.translate(-length, 0f, -width)
                    Direction.NORTH -> stack.translate(-width, 0f, length)
                    Direction.WEST -> stack.translate(length, 0f, width)
                    else -> {}
                }
                stack.mulPose(Axis.YN.rotationDegrees(supplier.getAngle()))
            } else {
                stack.mulPose(Axis.YN.rotationDegrees(supplier.angle))
            }
        } else {
            stack.mulPose(Axis.YN.rotationDegrees(supplier.angle))
        }
    }

    fun runLater(runnable: Runnable) {
        TASKS += runnable
    }

}
