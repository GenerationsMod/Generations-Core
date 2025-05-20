package generations.gg.generations.core.generationscore.common.world.level.block.deco

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.generateRotationalVoxelShape
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class ShelfBlock(props: Properties) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        props,
        GenerationsBlockEntities.GENERIC_MODEL_PROVIDING,
        model = GenerationsBlockEntityModels.SHELF,
        width = 1) {

    override fun codec(): MapCodec<ShelfBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPES.getShape(state)
    }

    companion object {
        private val SHAPES = generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(1.1875, 0.0, 0.07500000000000001, 1.31875, 0.8125, 0.8999999999999999),
                Shapes.box(-0.31875, 0.0, 0.07500000000000001, 1.31875, 0.103125, 0.8999999999999999),
                Shapes.box(-0.3187500000000001, 0.0, 0.07500000000000001, 1.3187499999999999, 0.8125, 0.84375),
                Shapes.box(-0.31875, 0.41875, 0.07500000000000001, 1.31875, 0.528125, 0.871875),
                Shapes.box(-0.375, 0.75625, 0.04999999999999999, 1.375, 0.875, 0.9437500000000001),
                Shapes.box(-0.3187500000000001, 0.0, 0.07500000000000001, -0.1875, 0.8125, 0.8999999999999999)
            ).move(0.5, 0.0, 0.0),
            Direction.SOUTH, 2, 1, 1
        )
        val CODEC = simpleCodec(::ShelfBlock)
    }
}