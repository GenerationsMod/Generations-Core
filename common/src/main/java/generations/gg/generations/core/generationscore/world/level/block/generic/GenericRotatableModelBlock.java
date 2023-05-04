package generations.gg.generations.core.generationscore.world.level.block.generic;

import com.pokemod.pokemod.client.model.ModelContextProviders;
import com.teamwizardry.animation.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@SuppressWarnings("deprecation")
public class GenericRotatableModelBlock<T extends BlockEntity & ModelContextProviders.ModelProvider> extends GenericModelBlock<T> {
    private static final Map<Size, Map<Integer, IntegerProperty>> SIZE_PROPERTIES = new HashMap<>();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final BiFunction<BlockPos, BlockState, BlockPos> DEFAULT_BLOCK_ROTATE_POS_FUNCTION = (pos, state) -> {
        if(state.getBlock() instanceof GenericRotatableModelBlock<?> block) {
            var facing = state.getValue(FACING);
            var x = block.getWidthValue(state);
            var y = block.getHeightValue(state);
            var z = block.getLengthValue(state);

            return pos.relative(facing.getClockWise(), x).relative(Direction.DOWN, y).relative(facing, z);
        }

        return pos;
    };

    private int width;
    private int height;
    private int length;

    protected GenericRotatableModelBlock(Properties materialIn, RegistryObject<BlockEntityType<T>> blockEntityFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, ResourceLocation model, int width, int height, int length) {
        super(materialIn, blockEntityFunction, baseBlockPosFunction, model);
        assignSize(width, height, length);
        reassignStateDefinition();
        this.registerDefaultState(createDefaultState());

    }

    protected GenericRotatableModelBlock(Properties materialIn, RegistryObject<BlockEntityType<T>> blockEntityFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, ResourceLocation model) {
        this(materialIn, blockEntityFunction, baseBlockPosFunction, model, 0, 0, 0);
    }

    protected GenericRotatableModelBlock(Properties properties, RegistryObject<BlockEntityType<T>> blockEntityFunction, ResourceLocation model, int width, int height, int length) {
        super(properties, blockEntityFunction, DEFAULT_BLOCK_ROTATE_POS_FUNCTION, model);
        assignSize(width, height, length);
        reassignStateDefinition();
        this.registerDefaultState(createDefaultState());
    }

    protected GenericRotatableModelBlock(Properties properties, RegistryObject<BlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        this(properties, blockEntityFunction, DEFAULT_BLOCK_ROTATE_POS_FUNCTION, model, 0, 0, 0);
    }

    private void assignSize(int width, int height, int length) {
        this.width = width;
        if (width != 0) SIZE_PROPERTIES.computeIfAbsent(Size.WIDTH, value -> new HashMap<>()).computeIfAbsent(width, value -> IntegerProperty.create("width", 0, value));
        this.height = height;
        if(height != 0) SIZE_PROPERTIES.computeIfAbsent(Size.HEIGHT, value -> new HashMap<>()).computeIfAbsent(height, value -> IntegerProperty.create("height", 0, value));
        this.length = length;
        if(length != 0) SIZE_PROPERTIES.computeIfAbsent(Size.LENGTH, value -> new HashMap<>()).computeIfAbsent(length, value -> IntegerProperty.create("length", 0, value));
    }

    private void reassignStateDefinition() {
        StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder<Block, BlockState>(this);
        this.createBlockStateDefinition(builder);
        this.stateDefinition = builder.create(Block::defaultBlockState, BlockState::new);
    }

    protected BlockState createDefaultState() {
        return setSize(this.getStateDefinition().any().setValue(FACING, Direction.NORTH), 0, 0, 0);
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if(pos.getY() < level.getMaxBuildHeight() - height && isAreaClear(level, pos)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        } else {
            return null;
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var facing = state.getValue(FACING);
        var x = getWidthValue(state);
        var y = getHeightValue(state);
        var z = getLengthValue(state);

        var rightDir = facing.getClockWise();

        if(x == 0 && y == 0 && z == 0) {
            return needsSupport(level, pos.below()); //TODO: Do we want the blocks to require solid foundation?
        }

        return checkDirection(level, pos, rightDir, Size.WIDTH, x) && checkDirection(level, pos, Direction.DOWN, Size.HEIGHT, y) && checkDirection(level, pos, facing, Size.LENGTH, z);
    }

    private boolean checkDirection(LevelReader level, BlockPos pos, Direction dir, Size size, int value) {
        var forward = getValue(level, pos, dir, size);
//        var backward = getValue(level, pos, dir.getOpposite(), size);
//        var maxSize = getSize(dir.getAxis());

        return value == 0 || forward == value - 1;
    }

    private int getValue(LevelReader level, BlockPos pos, Direction direction, Size size) {
        var blockState = level.getBlockState(pos.relative(direction));
        return blockState.is(this) ? getValue(blockState, size) : -1;
    }

    public int getValue(BlockState blockState, Size size) {
        return switch (size) {
            case WIDTH -> getWidthValue(blockState);
            case HEIGHT -> getHeightValue(blockState);
            case LENGTH -> getLengthValue(blockState);
        };
    }

    private int getSize(Direction.Axis axis) {
        return switch (axis) {
            case X -> width;
            case Y -> height;
            case Z -> length;
        };
    }

    protected boolean needsSupport(LevelReader level, BlockPos pos) {
        return canSupportRigidBlock(level, pos);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if(canSurvive(state, level, currentPos)) return state;
        else return Blocks.AIR.defaultBlockState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        if (width != 0) builder.add(SIZE_PROPERTIES.get(Size.WIDTH).get(width));
        if (height != 0) builder.add(SIZE_PROPERTIES.get(Size.HEIGHT).get(height));
        if (length != 0) builder.add(SIZE_PROPERTIES.get(Size.LENGTH).get(length));
    }

    @Override
    public AABB computeRenderBoundingBox(Level level, BlockPos pos, BlockState state) {
        var facing = state.getValue(FACING);
        return new AABB(pos, pos.relative(facing.getCounterClockWise(), width + 1).relative(Direction.UP, height + 1).relative(facing.getOpposite(), length + 1));
    }

    @Override
    public boolean canRender(Level level, BlockPos blockPos, BlockState blockState) {
        return getWidthValue(blockState) == 0 || getHeightValue(blockState) == 0 || getLengthValue(blockState) == 0;
    }

    public float getAngle(BlockState state) {
        return state.getValue(FACING).toYRot();
    }

    protected boolean isAreaClear(Level level, BlockPos pos) {
        return BlockPos.betweenClosedStream(pos, pos.offset(width, height, length)).map(level::getBlockState).allMatch(BlockState::canBeReplaced);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        var facing = state.getValue(FACING);
        var rightDir = facing.getCounterClockWise();
        var backDir = facing.getOpposite();

        for (int x = 0; x <= width; x++) {
            for (int y = 0; y <= height; y++) {
                for (int z = 0; z <= length; z++) {
                    if(x == 0 && y == 0 && z == 0) continue;
                    level.setBlock(pos.relative(rightDir, x).relative(Direction.UP, y).relative(backDir, z), setSize(state, x, y, z), 2);
                }
            }
        }
    }

    public int getLengthValue(BlockState state) {
        return state.is(this) && length != 0 ? state.getValue(getLengthProperty()) : Integer.valueOf(0);
    }

    public BlockState setLengthValue(BlockState state, int value) {
        return state.is(this) && length > 0 && MathUtils.between(value, 0, length) ? state.setValue(getLengthProperty(), value) : state;
    }

    public int getHeightValue(BlockState state) {
        return state.is(this) && height != 0 ? state.getValue(getHeightProperty()) : Integer.valueOf(0);
    }

    public BlockState setHeighthValue(BlockState state, int value) {
        return state.is(this) && height > 0 && MathUtils.between(value, 0, height) ? state.setValue(getHeightProperty(), value) : state;
    }

    public int getWidthValue(BlockState state) {
        return state.is(this) && width != 0 ? state.getValue(getWidthProperty()) : Integer.valueOf(0);
    }

    public BlockState setWidthValue(BlockState state, int value) {
        return state.is(this) && width > 0 && MathUtils.between(value, 0, width) ? state.setValue(getWidthProperty(), value) : state;
    }

    public IntegerProperty getWidthProperty() {
        return SIZE_PROPERTIES.get(Size.WIDTH).get(width);
    }

    public IntegerProperty getHeightProperty() {
        return SIZE_PROPERTIES.get(Size.HEIGHT).get(height);
    }

    public IntegerProperty getLengthProperty() {
        return SIZE_PROPERTIES.get(Size.LENGTH).get(length);
    }

    public BlockState setSize(BlockState state, int x, int y, int z) {
        state = setWidthValue(state, x);
        state = setHeighthValue(state, y);
        state = setLengthValue(state, z);
        return state;
    }

    public enum Size {//TODO: THink of better name
        WIDTH, LENGTH, HEIGHT
    }
}