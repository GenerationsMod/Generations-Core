package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.stream.Stream

class LitwickCandlesBlock(properties: Properties) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties = properties,
        model = GenerationsBlockEntityModels.LITWICK_CANDLES
    ) {
    override val blockEntityType: BlockEntityType<GenericModelProvidingBlockEntity>
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<LitwickCandleBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    companion object {
        private val SHAPE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Stream.of(
                Shapes.box(0.140625, 0.0, 0.25, 0.484375, 0.4375, 0.59375),
                Shapes.box(0.203125, 0.1875, 0.3125, 0.421875, 0.65625, 0.53125),
                Shapes.box(0.5, 0.0, 0.15625, 0.96875, 0.328125, 0.625),
                Shapes.box(0.5625, 0.25, 0.21875, 0.90625, 0.578125, 0.5625),
                Shapes.box(0.3125, 0.0, 0.515625, 0.65625, 0.25, 0.859375),
                Shapes.box(0.34375, 0.0, 0.546875, 0.625, 0.5125, 0.828125)
            )
                .reduce(Shapes.empty()) { a: VoxelShape?, b: VoxelShape? -> Shapes.join(a, b, BooleanOp.OR) },
            Direction.SOUTH
        )

        val CODEC: MapCodec<LitwickCandleBlock> = simpleCodec(::LitwickCandleBlock)
    }
}
