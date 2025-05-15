package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockAnimatedObjectInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericModelBlock
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4f

abstract class ModelProvidingBlockEntity(
    type: BlockEntityType<out ModelProvidingBlockEntity>,
    pos: BlockPos,
    state: BlockState
) : SimpleBlockEntity(type, pos, state), ModelContextProviders.ModelProvider, VariantProvider {
    var objectInstance: Array<ObjectInstance?>? = null
    private var boundingBox: AABB? = null

    override fun getModel(): ResourceLocation = blockState.block.instanceOrNull<GenericModelBlock<*>>()?.model ?: GenerationsBlockEntityModels.DEFAULT

    val renderBoundingBox: AABB
        get() {
            if (boundingBox == null) boundingBox = blockState.block.instanceOrNull<GenericModelBlock<*>>()?.computeRenderBoundingBox(
                    getLevel()!!,
                    blockPos,
                    blockState
                ) ?: defaultAABB(blockPos) else boundingBox

            return boundingBox as AABB
        }

    override fun getVariant(): String? = blockState.block.instanceOrNull<VariantProvider>()?.variant

    fun generateInstance(): ObjectInstance {
        return if (isAnimated) BlockAnimatedObjectInstance(Matrix4f(), Matrix4f(), null) else BlockObjectInstance(
            Matrix4f(), Matrix4f(), null
        )
    }

    companion object {
        fun defaultAABB(pos: BlockPos): AABB {
            return AABB(Vec3.atLowerCornerOf(pos), Vec3.atLowerCornerOf(pos.offset(1, 1, 1)))
        }
    }
}
