package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class TapuShrineBlock(properties: Properties) :
    ShrineBlock<GenericShrineBlockEntity>(
        properties,
        GenerationsBlockEntities.GENERIC_SHRINE,
        GenerationsBlockEntityModels.TAPU_SHRINE
    ) {
    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    override fun codec(): MapCodec<TapuShrineBlock> = CODEC

    companion object {
        private val SHAPE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(
                Shapes.box(0.1875, 0.0, 0.1875, 0.8125, 0.109375, 0.8125),
                Shapes.box(0.21875, 0.0, 0.21875, 0.78125, 1.0, 0.78125), BooleanOp.OR
            )
        )
        val CODEC = simpleCodec(::TapuShrineBlock)
    }
}
