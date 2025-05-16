package generations.gg.generations.core.generationscore.common.world.level.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.EnumProperty

class PillarBlock(properties: Properties) : Block(properties.noOcclusion()) {
    init {
        this.registerDefaultState(
            defaultBlockState()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(CONNECTED_TOP, false)
                .setValue(CONNECTED_BOTTOM, false)
        )
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val level = context.level
        val pos = context.clickedPos
        val axis = context.clickedFace.axis

        val topConnected = isConnected(level, pos, axis, true)
        val bottomConnected = isConnected(level, pos, axis, false)

        return defaultBlockState()
            .setValue(AXIS, axis)
            .setValue(CONNECTED_TOP, topConnected)
            .setValue(CONNECTED_BOTTOM, bottomConnected)
    }

    public override fun neighborChanged(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        block: Block,
        fromPos: BlockPos,
        flag: Boolean
    ) {
        if (!level.isClientSide) {
            val axis = state.getValue(AXIS)
            val topConnected = isConnected(level, pos, axis, true)
            val bottomConnected = isConnected(level, pos, axis, false)

            level.setBlock(
                pos,
                state.setValue(CONNECTED_TOP, topConnected).setValue(CONNECTED_BOTTOM, bottomConnected),
                3
            )
        }
    }

    private fun isConnected(level: Level, pos: BlockPos, axis: Direction.Axis, checkTop: Boolean): Boolean {
        val dir = if (checkTop) getPositiveDirection(axis) else getNegativeDirection(axis)
        val targetPos = pos.relative(dir)
        val targetState = level.getBlockState(targetPos)
        return targetState.block is PillarBlock && targetState.getValue(AXIS) === axis
    }

    private fun getPositiveDirection(axis: Direction.Axis): Direction {
        return when (axis) {
            Direction.Axis.X -> Direction.EAST
            Direction.Axis.Y -> Direction.UP
            Direction.Axis.Z -> Direction.SOUTH
        }
    }

    private fun getNegativeDirection(axis: Direction.Axis): Direction {
        return when (axis) {
            Direction.Axis.X -> Direction.WEST
            Direction.Axis.Y -> Direction.DOWN
            Direction.Axis.Z -> Direction.NORTH
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AXIS, CONNECTED_TOP, CONNECTED_BOTTOM)
    } //    @Override
    //    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
    //        return SHAPE;
    //    }

    companion object {
        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.AXIS
        val CONNECTED_TOP: BooleanProperty = BooleanProperty.create("connected_top")
        val CONNECTED_BOTTOM: BooleanProperty = BooleanProperty.create("connected_bottom")
    }
}
