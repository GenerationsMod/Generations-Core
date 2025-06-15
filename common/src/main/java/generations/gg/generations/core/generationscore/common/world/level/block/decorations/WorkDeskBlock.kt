package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class WorkDeskBlock(properties: Properties) : GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
    properties = properties,
    model = GenerationsBlockEntityModels.WORK_DESK,
    width = 2,
    height = 1,
    length = 1
) {
    override val blockEntityType: BlockEntityType<GenericModelProvidingBlockEntity>
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPES.getShape(state)
    }

    override fun codec(): MapCodec<WorkDeskBlock> = CODEC

    companion object {
        private val SHAPES: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(2.78125, 0.0, -0.7124999999999999, 2.89375, 1.125, -0.6000000000000001),
                Shapes.box(0.10624999999999996, 0.0, 0.5874999999999999, 0.21875, 1.125, 0.7000000000000002),
                Shapes.box(2.78125, 0.0, 0.5874999999999999, 2.89375, 1.125, 0.7000000000000002),
                Shapes.box(0.10624999999999996, 0.0, -0.7124999999999999, 0.21875, 1.125, -0.6000000000000001),
                Shapes.box(0.050000000000000044, 1.11875, -0.7625000000000002, 2.95, 1.4124999999999999, 0.7625),
                Shapes.box(
                    0.1499999999999999,
                    0.14125,
                    -0.675,
                    2.8500000000000005,
                    1.1412499999999999,
                    0.6624999999999999
                )
            ), Direction.SOUTH, 3, 2, 2
        )
        val CODEC: MapCodec<WorkDeskBlock> = simpleCodec(::WorkDeskBlock)
    }
}