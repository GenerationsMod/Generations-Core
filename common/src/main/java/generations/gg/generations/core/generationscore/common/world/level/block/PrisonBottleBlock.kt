package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.PrisonBottleStemBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class PrisonBottleBlock(properties: Properties) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties,
        GenerationsBlockEntities.GENERIC_MODEL_PROVIDING,
        model = GenerationsBlockEntityModels.PRISON_BOTTLE,
        width = 0,
        height = 2,
        length = 0
    ) {
    override fun getVariant(): String? {
        return "ring_6"
    }

    override fun codec(): MapCodec<PrisonBottleBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return PrisonBottleStemBlock.SHAPE.getShape(state)
    }

    companion object {
        val CODEC = simpleCodec(::PrisonBottleBlock)
    }
}
