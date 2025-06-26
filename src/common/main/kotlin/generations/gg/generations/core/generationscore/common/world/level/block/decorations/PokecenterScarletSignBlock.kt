package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class PokecenterScarletSignBlock(materialIn: Properties) : GenericRotatableModelBlock(
        properties = materialIn,
        model = GenerationsBlockEntityModels.POKECENTER_SCARLET_SIGN
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<PokecenterScarletSignBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPES.getShape(state)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val direction = state.getValue(FACING)
        val blockPos = pos.relative(direction.opposite)
        val blockState = level.getBlockState(blockPos)
        return blockState.isFaceSturdy(level, blockPos, direction)
    }

    override fun shouldRotateSpecial(): Boolean {
        return false
    }

    companion object {
        val SHAPES: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.box(-1.0, -1.0, 0.0, 2.0, 2.0, 0.25),
            Direction.SOUTH
        )
        val CODEC: MapCodec<PokecenterScarletSignBlock> = simpleCodec(::PokecenterScarletSignBlock)
    }
}
