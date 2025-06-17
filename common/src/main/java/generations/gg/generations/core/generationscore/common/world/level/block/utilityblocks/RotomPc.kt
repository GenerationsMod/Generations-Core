package generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.DefaultPcBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.PcBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class RotomPc(arg: Properties) : PcBlock<DefaultPcBlockEntity>(DefaultPcBlockEntity::class.java, arg, GenerationsBlockEntityModels.ROTOM_PC, 0, 2, 0
    ) {
    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return CODEC
    }

    override val blockEntityType
        get() = GenerationsBlockEntities.PC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    override fun getTicker(): BlockEntityTicker<PcBlockEntity> {
        return DefaultPcBlockEntity.TICKER
    }

    companion object {
        val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.015625, 0.0, 0.28125, 0.978125, 2.0, 0.5625),
                Shapes.box(0.015625, 0.6875, 0.0, 0.978125, 0.8125, 0.28125),
                Shapes.box(0.05937500000000001, 0.875, 0.0, 0.07500000000000001, 1.625, 0.28125),
                Shapes.box(0.390625, 0.90625, 0.21875, 0.603125, 1.0625, 0.296875),
                Shapes.box(0.015625, 1.9375, 0.21875, 0.978125, 2.5, 0.4375),
                Shapes.box(0.1875, 1.25, 0.5625, 0.8125, 1.5, 0.625)
            ),
            Direction.NORTH, 1, 3, 1
        )

        val CODEC: MapCodec<RotomPc> = simpleCodec(::RotomPc)
    }
}
