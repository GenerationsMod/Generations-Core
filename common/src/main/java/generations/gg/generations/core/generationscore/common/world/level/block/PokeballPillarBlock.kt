package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class PokeballPillarBlock(properties: Properties) : GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties,
        GenerationsBlockEntities.GENERIC_MODEL_PROVIDING,
        model = GenerationsBlockEntityModels.POKEBALL_PILLAR,
        height = 1
    ) {
    override fun codec(): MapCodec<PokeballPillarBlock> {
        return CODEC
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return if (getHeightValue(state) == 0) LOWER else UPPER
    }

    companion object {
        private val UPPER: VoxelShape = Shapes.box(0.125, 0.0, 0.125, 0.875, 0.8125, 0.875)
        private val LOWER: VoxelShape = Shapes.join(
            Shapes.box(0.0, 0.0, 0.0, 1.0, 0.1875, 1.0),
            Shapes.box(0.125, 0.1875, 0.125, 0.875, 1.0, 0.875), BooleanOp.OR
        )
        val CODEC: MapCodec<PokeballPillarBlock> = simpleCodec(::PokeballPillarBlock)
    }
}
