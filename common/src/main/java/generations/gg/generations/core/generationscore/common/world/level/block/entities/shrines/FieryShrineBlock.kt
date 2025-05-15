package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.BirdShrineBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class FieryShrineBlock(properties: Properties) : BirdShrineBlock(
    properties = properties,
    model = GenerationsBlockEntityModels.MOLTRES_SHRINE,
    imbuedItems = arrayOf(GenerationsItems.FIERY_WING, GenerationsItems.SINISTER_WING)
) {
    override fun codec(): MapCodec<FieryShrineBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPES.getShape(state)
    }

    companion object {
        var SHAPES: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.or(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.084375, 1.0),
                Shapes.box(0.0625, 0.0625, 0.0625, 0.9375, 0.1875, 0.9375),
                Shapes.box(0.125, 0.1875, 0.375, 0.875, 0.25, 0.625),
                Shapes.box(0.15625, 0.25, 0.41875, 0.84375, 0.34375, 0.5875),
                Shapes.box(0.34375, 0.3125, 0.41875, 0.65625, 0.78125, 0.5875),
                Shapes.box(0.46875, 0.78125, 0.35, 0.53125, 0.875, 0.575),
                Shapes.box(0.46875, 0.6125, 0.54375, 0.53125, 0.6937500000000001, 0.60625),
                Shapes.box(0.03125, 0.3125, 0.29375, 0.34375, 0.78125, 0.4625),
                Shapes.box(0.65625, 0.3125, 0.35624999999999996, 0.96875, 0.78125, 0.4625),
                Shapes.box(0.65625, 0.3125, 0.41874999999999996, 0.71875, 0.78125, 0.49687499999999996),
                Shapes.box(0.28125, 0.3125, 0.41874999999999996, 0.34375, 0.78125, 0.49687499999999996)
            ), Direction.SOUTH
        )

        val CODEC = simpleCodec(::FieryShrineBlock)
    }
}
