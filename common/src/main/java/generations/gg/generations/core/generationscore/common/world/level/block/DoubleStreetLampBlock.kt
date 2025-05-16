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
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class DoubleStreetLampBlock(materialIn: Properties) : GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        materialIn,
        GenerationsBlockEntities.GENERIC_MODEL_PROVIDING,
        model = GenerationsBlockEntityModels.DOUBLE_STREET_LAMP,
        width = 2,
        height = 4,
        length = 0
    ) {
    override val baseX: Int
        get() = 1

    override fun validPosition(x: Int, y: Int, z: Int): Boolean {
        return when (y) {
            0, 1, 2 -> x != 0 && x != 2
            else -> true
        }
    }

    override fun codec(): MapCodec<DoubleStreetLampBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    companion object {
        private val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.175, 1.0),
                Shapes.box(0.1875, 0.0, 0.1875, 0.8125, 1.14375, 0.8125),
                Shapes.box(0.1875, 0.0, 0.1875, 0.8125, 1.25, 0.8125),
                Shapes.box(0.421875, 0.0, 0.421875, 0.578125, 3.875, 0.578125),
                Shapes.box(0.234375, 3.625, 0.234375, 0.765625, 4.0, 0.765625),
                Shapes.box(-0.515625, 3.8125, 0.421875, 1.515625, 4.3125, 0.578125),
                Shapes.box(1.046875, 3.0625, 0.234375, 1.578125, 3.875, 0.765625),
                Shapes.box(0.859375, 3.1875, 0.046875, 1.765625, 3.8125, 0.953125),
                Shapes.box(-0.765625, 3.1875, 0.046875, 0.140625, 3.8125, 0.953125),
                Shapes.box(-0.578125, 3.0625, 0.234375, -0.046875, 3.875, 0.765625)
            ),
            Direction.SOUTH, 3, 5, 1, 1.0, 0.0
        )

        val CODEC = simpleCodec(::DoubleStreetLampBlock)
    }
}
