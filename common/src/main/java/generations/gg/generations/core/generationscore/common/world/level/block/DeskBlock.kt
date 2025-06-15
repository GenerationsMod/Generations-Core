package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class DeskBlock(properties: Properties) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties = properties,
        model = GenerationsBlockEntityModels.DESK,
        width = 2,
        height = 1
    ) {
    override val blockEntityType: BlockEntityType<GenericModelProvidingBlockEntity>
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    override fun codec(): MapCodec<DeskBlock> = CODEC

    companion object {
        private val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.4375, 0.0, 0.025000000000000022, 0.65625, 0.125, 0.9375),
                Shapes.box(0.4375, 0.125, 0.025000000000000022, 0.65625, 1.0, 0.45),
                Shapes.box(1.71875, 0.0, 0.025000000000000022, 2.55, 1.0, 0.9375),
                Shapes.box(0.4375, 0.1875, 0.025000000000000022, 1.96875, 1.0, 0.1375),
                Shapes.box(0.35624999999999996, 0.96875, 0.006249999999999978, 2.625, 1.09375, 0.9562499999999999)
            ),
            Direction.SOUTH,
            3, 2, 1
        )

        val CODEC = simpleCodec(::DeskBlock)
    }
}
