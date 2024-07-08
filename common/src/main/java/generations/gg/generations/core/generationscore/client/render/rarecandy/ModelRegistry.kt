package generations.gg.generations.core.generationscore.client.render.rarecandy

import com.github.benmanes.caffeine.cache.CacheLoader
import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.RemovalCause
import com.github.benmanes.caffeine.cache.RemovalListener
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders.AngleProvider
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel.Companion.of
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.rendering.RareCandy
import net.minecraft.client.Minecraft
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import java.io.FileNotFoundException
import java.util.concurrent.TimeUnit
import java.util.function.BiConsumer

object ModelRegistry {
    private const val DUMMY = "dummy"
    private val LOADER = Caffeine.newBuilder().expireAfterAccess(1, TimeUnit.HOURS).removalListener { _: ResourceLocation?, value: CompiledModel?, _: RemovalCause? -> value!!.delete() }
        .buildAsync<ResourceLocation, CompiledModel?>(
            CacheLoader { key ->
                val resourceManager = Minecraft.getInstance().resourceManager
                try {
                    return@CacheLoader of(key!!, resourceManager.getResourceOrThrow(key))
                } catch (e: FileNotFoundException) {
                    throw RuntimeException(e)
                }
            })
    @JvmStatic
    operator fun get(modelProvider: ModelContextProviders.ModelProvider): CompiledModel? {
        return ModelRegistry[modelProvider.model]
    }

    @JvmStatic
    fun clear() = LOADER.asMap().clear();

    @JvmStatic
    operator fun get(location: ResourceLocation): CompiledModel? {
        return try {
            LOADER[location].getNow(null)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @JvmStatic
    fun prepForBER(stack: PoseStack, supplier: AngleProvider) {
        stack.translate(0.5f, 0.0f, 0.5f)
        if (supplier is ModelProvidingBlockEntity) {
            val block = supplier.blockState.block;

            if (block is GenericRotatableModelBlock<*> && block.shouldRotateSpecial()) {
                val forward: Direction = supplier.blockState.getValue(GenericRotatableModelBlock.FACING)
                val x: Int = block.getWidthValue(supplier.blockState)
                val z: Int = block.getLengthValue(supplier.blockState)
                val width: Float = block.width() * 0.5f - x
                val length: Float = block.length() * 0.5f - z
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