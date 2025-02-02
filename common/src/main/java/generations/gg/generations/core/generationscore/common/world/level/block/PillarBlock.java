package generations.gg.generations.core.generationscore.common.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class PillarBlock extends Block {
    public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty CONNECTED_TOP = BooleanProperty.create("connected_top");
    public static final BooleanProperty CONNECTED_BOTTOM = BooleanProperty.create("connected_bottom");

    public PillarBlock(BlockBehaviour.Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.defaultBlockState()
                .setValue(AXIS, Axis.Y)
                .setValue(CONNECTED_TOP, false)
                .setValue(CONNECTED_BOTTOM, false)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Axis axis = context.getClickedFace().getAxis();

        boolean topConnected = isConnected(level, pos, axis, true);
        boolean bottomConnected = isConnected(level, pos, axis, false);

        return this.defaultBlockState()
                .setValue(AXIS, axis)
                .setValue(CONNECTED_TOP, topConnected)
                .setValue(CONNECTED_BOTTOM, bottomConnected);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean flag) {
        if (!level.isClientSide) {
            Axis axis = state.getValue(AXIS);
            boolean topConnected = isConnected(level, pos, axis, true);
            boolean bottomConnected = isConnected(level, pos, axis, false);

            level.setBlock(pos, state.setValue(CONNECTED_TOP, topConnected).setValue(CONNECTED_BOTTOM, bottomConnected), 3);
        }
    }

    private boolean isConnected(Level level, BlockPos pos, Axis axis, boolean checkTop) {
        Direction dir = checkTop ? getPositiveDirection(axis) : getNegativeDirection(axis);
        BlockPos targetPos = pos.relative(dir);
        BlockState targetState = level.getBlockState(targetPos);
        return targetState.getBlock() instanceof PillarBlock && targetState.getValue(AXIS) == axis;
    }

    private Direction getPositiveDirection(Axis axis) {
        return switch (axis) {
            case X -> Direction.EAST;
            case Y -> Direction.UP;
            case Z -> Direction.SOUTH;
        };
    }

    private Direction getNegativeDirection(Axis axis) {
        return switch (axis) {
            case X -> Direction.WEST;
            case Y -> Direction.DOWN;
            case Z -> Direction.NORTH;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, CONNECTED_TOP, CONNECTED_BOTTOM);
    }

//    @Override
//    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
//        return SHAPE;
//    }
}
