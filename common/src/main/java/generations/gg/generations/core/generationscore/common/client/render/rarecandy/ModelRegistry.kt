package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.AngleProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CompiledModel.Companion.of
import generations.gg.generations.core.generationscore.common.util.TimedCache
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import gg.generations.rarecandy.renderer.rendering.RareCandy
import gg.generations.rarecandy.shaded.caffeine.cache.CacheLoader
import gg.generations.rarecandy.shaded.caffeine.cache.Caffeine
import gg.generations.rarecandy.shaded.caffeine.cache.RemovalCause
import net.minecraft.client.Minecraft
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import java.time.Duration
import java.time.Instant
import java.util.concurrent.TimeUnit
import java.util.function.BiConsumer
import java.util.function.Function

object ModelRegistry {
    private const val DUMMY = "dummy"

//    val CACHE = TimedCache(Duration.ofMinutes(1), BiConsumer<ResourceLocation, CompiledModel>{
//        key, value ->
////        System.out.println("DELETING: $key")
//        RenderSystem.recordRenderCall { value.delete() }
//    }, Function {
//        val resourceManager = Minecraft.getInstance().resourceManager
//        try {
//            of(it, resourceManager.getResourceOrThrow(it))
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    });

    private val LOADER = Caffeine.newBuilder().executor(Minecraft.getInstance()).expireAfterAccess(2, TimeUnit.MINUTES)
        .removalListener { key: ResourceLocation, value: CompiledModel, cause: RemovalCause ->
            if(GenerationsCore.CONFIG.client.logModelLoading) GenerationsCore.LOGGER.info(String.format("%s was removed from cache. Deleting GPU Resouces next.", key, cause))
            RenderSystem.recordRenderCall { value.delete() }
        }
        .buildAsync<ResourceLocation, CompiledModel>(
            CacheLoader { key ->
                val resourceManager = Minecraft.getInstance().resourceManager
                try {
                    return@CacheLoader of(key, resourceManager.getResourceOrThrow(key))
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            })
    @JvmStatic
    operator fun get(modelProvider: ModelContextProviders.ModelProvider): CompiledModel? {
        return modelProvider.model?.let(::get)
    }

    @JvmStatic
    fun cache() = LOADER

    @JvmStatic
    fun clear() = LOADER.asMap().clear()

    fun tick() = /*CACHE.tick()*/ {}

    @JvmStatic
    operator fun get(location: ResourceLocation): CompiledModel? {
        return try {
            LOADER[location].getNow(null)
        } catch (e: Exception) {
            null
        }
    }

    @JvmStatic
    fun init() {
        CompiledModel.init()
    }

    @JvmStatic
    fun prepForBER(stack: PoseStack, supplier: AngleProvider) {
        stack.translate(0.5f, 0.0f, 0.5f)
        if (supplier is ModelProvidingBlockEntity) {
            val block = supplier.blockState.block

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
