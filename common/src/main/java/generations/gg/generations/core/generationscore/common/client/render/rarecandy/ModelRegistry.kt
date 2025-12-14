package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import com.mojang.blaze3d.pipeline.RenderCall
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.AngleProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CompiledModel.Companion.of
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.rendering.RareCandy
import gg.generations.rarecandy.shaded.caffeine.cache.CacheLoader
import gg.generations.rarecandy.shaded.caffeine.cache.Caffeine
import gg.generations.rarecandy.shaded.caffeine.cache.RemovalCause
import net.minecraft.client.Minecraft
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import java.util.concurrent.TimeUnit
import java.util.function.BiConsumer

object ModelRegistry {
    private val modelsToLoad: MutableList<ResourceLocation> = mutableListOf()

    private val CACHE = mutableMapOf<ResourceLocation, CompiledModel>()
    private val TIMES = mutableMapOf<ResourceLocation, Double>()

    @JvmStatic
    operator fun get(modelProvider: ModelContextProviders.ModelProvider): CompiledModel? {
        return modelProvider.model?.let(::get)
    }

    @JvmStatic
    fun clear() {
       ensureOnRenderThread {
            CACHE.forEach { (_, value) -> value.delete() }

            CACHE.clear()
        }
    }

    fun ensureOnRenderThread(runnable: RenderCall) {
        if (RenderSystem.isOnRenderThread()) {
            runnable.execute()
        } else {
            RenderSystem.recordRenderCall(runnable)
        }
    }

    fun tick() {
        ensureOnRenderThread {
            val time = MinecraftClientGameProvider.getTimePassed();

            modelsToLoad.forEach {
                CACHE[it] = of(it);
                TIMES[it] = time
            }

            modelsToLoad.clear()

            val keys = TIMES.filter { time - it.value >= 5.0 }.map { it.key }

            for(key in keys) {
                CACHE[key]?.also {
                    it.delete()
                }

                CACHE.remove(key)
                TIMES.remove(key)
            }
        }
    }

    @JvmStatic
    operator fun get(location: ResourceLocation): CompiledModel? {
        return try {
            if(modelsToLoad.contains(location)) return null;
            else {
                CACHE[location]?.also { TIMES[location] = MinecraftClientGameProvider.getTimePassed() } ?: run { modelsToLoad += location }.let { null }
            }
        } catch (e: Exception) {
            null
        }
    }

    @JvmStatic
    fun init() {
        CompiledModel.init()
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

    @JvmStatic
    val worldRareCandy: RareCandy = RareCandy().also {
        Animation.animationModifier = BiConsumer { animation: Animation, s: String ->
            if (s == "gfb") animation.ticksPerSecond = 60000f // 60 fps. 1000 ticks per frame?
        }
    }
}
