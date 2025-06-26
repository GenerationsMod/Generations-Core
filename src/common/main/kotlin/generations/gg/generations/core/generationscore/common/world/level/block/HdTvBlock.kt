package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class HdTvBlock(properties: Properties) : GenericRotatableModelBlock(
    properties,
    model = GenerationsBlockEntityModels.HDTV,
    width = 2,
    height = 1,
    length = 0
) {
    override val blockEntityType
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<HdTvBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }


    companion object {
        val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.375, 0.0, 0.4375, 2.65625, 0.125, 0.5625),
                Shapes.box(0.4375, 0.125, 0.4375, 2.59375, 0.25, 0.5625),
                Shapes.box(1.1875, 0.0, 0.375, 1.828125, 0.375, 0.6875),
                Shapes.box(0.0, 0.1875, 0.4375, 3.0, 1.921875, 0.625),
                Shapes.box(0.375, 0.375, 0.375, 2.625, 1.734375, 0.4375)
            ), Direction.SOUTH, 3, 2, 1
        )
        val CODEC = simpleCodec(::HdTvBlock)
    }
}
