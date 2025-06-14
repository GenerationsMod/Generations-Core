package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.BirdShrineBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class HoohShrineBlock(properties: Properties) : BirdShrineBlock(properties, GenerationsBlockEntityModels.HO_OH_SHRINE, 2, 1, 0, "rainbow_wing".generationsResource()) {

    override fun codec(): MapCodec<HoohShrineBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    override val baseX: Int
        get() = 1

    override fun validPosition(x: Int, y: Int, z: Int): Boolean {
        return x == baseX || y != 0
    }

    companion object {
        var SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.125, 1.0),
                Shapes.box(0.0625, 0.125, 0.0625, 0.9375, 0.58125, 0.9375),
                Shapes.box(0.4375, 0.5625, 0.4375, 0.5625, 1.01875, 0.625),
                Shapes.box(0.375, 0.9375, 0.4375, 0.625, 1.51875, 0.8125),
                Shapes.box(0.4375, 1.5, 0.5625, 0.5625, 1.7062499999999998, 0.75),
                Shapes.box(0.4375, 1.625, 0.5625, 0.5625, 1.8312499999999998, 1.0625),
                Shapes.box(0.41875, 1.8125, 0.5625, 0.58125, 2.08125, 0.9375),
                Shapes.box(0.625, 1.25, 0.3125, 0.875, 1.83125, 0.6875),
                Shapes.box(-0.3125, 1.3125, -0.0625, 0.1875, 2.14375, 0.4375),
                Shapes.box(0.8125, 1.3125, -0.0625, 1.3125, 2.14375, 0.4375),
                Shapes.box(0.125, 1.25, 0.3125, 0.375, 1.83125, 0.6875)
            ),
            Direction.SOUTH, 3, 2, 1, 1.0, 0.0
        )
        val CODEC = simpleCodec(::HoohShrineBlock)
    }
}
