package generations.gg.generations.core.generationscore.world.level.block.generic;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.util.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    protected GenericRotatableModelBlock(Properties materialIn, RegistrySupplier<BlockEntityType<T>> blockEntityFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, ResourceLocation model, int width, int height, int length) {
        super(materialIn, blockEntityFunction, baseBlockPosFunction, model);
        assignSize(width, height, length);
        reassignStateDefinition();
        this.registerDefaultState(createDefaultState());

    }

    protected GenericRotatableModelBlock(Properties materialIn, RegistrySupplier<BlockEntityType<T>> blockEntityFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, ResourceLocation model) {
        this(materialIn, blockEntityFunction, baseBlockPosFunction, model, 0, 0, 0);
    }

    public GenericRotatableModelBlock(Properties properties, RegistrySupplier<BlockEntityType<T>> blockEntityFunction, ResourceLocation model, int width, int height, int length) {
        super(properties, blockEntityFunction, DEFAULT_BLOCK_ROTATE_POS_FUNCTION, model);
        assignSize(width, height, length);
        reassignStateDefinition();
        this.registerDefaultState(createDefaultState());
    }

    protected GenericRotatableModelBlock(Properties properties, RegistrySupplier<BlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
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
        StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder<>(this);
        this.createBlockStateDefinition(builder);
        this.stateDefinition = builder.create(Block::defaultBlockState, BlockState::new);
    }

    protected BlockState createDefaultState() {
        return setSize(super.createDefaultState().setValue(FACING, Direction.NORTH), 0, 0, 0);
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
        var pos = context.getClickedPos();
        var level = context.getLevel();
        var dir = context.getHorizontalDirection().getOpposite();

        if(pos.getY() < level.getMaxBuildHeight() - height && isAreaClear(level, dir, pos)) {
            return this.defaultBlockState().setValue(FACING, dir);
        } else {
            return null;
        }
    }

    protected boolean isAreaClear(Level level, Direction dir, BlockPos pos) {
        var widthDir = dir.getCounterClockWise();
        var heightDir = Direction.UP;

        return BlockPos.betweenClosedStream(pos, pos.relative(widthDir, width).relative(heightDir, height).relative(dir, length)).map(level::getBlockState).allMatch(BlockState::canBeReplaced);
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
        var backward = getValue(level, pos, dir.getOpposite(), size);
        var maxSize = getSize(dir.getAxis());

        return value == 0 || forward == value - 1 || backward > maxSize;
    }

    @Override
    public void playerWillDestroy(Level level, @NotNull BlockPos pos, @NotNull BlockState state, Player player) {
        level.destroyBlock(getBaseBlockPos(pos, state), true);

        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder params) {
        if(getWidthValue(state) == 0 && getLengthValue(state) == 0 && getHeightValue(state) == 0) return super.getDrops(state, params);
        else return Collections.emptyList();
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
    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if(canSurvive(state, level, currentPos)) return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
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

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        var facing = state.getValue(FACING);
        var rightDir = facing.getCounterClockWise();
        var backDir = facing.getOpposite();

        for (int x = 0; x <= width; x++) {
            for (int y = 0; y <= height; y++) {
                for (int z = 0; z <= length; z++) {
                    if(x == 0 && y == 0 && z == 0) continue;
                    var blockPos = pos.relative(rightDir, x).relative(Direction.UP, y).relative(backDir, z);
                    level.setBlock(blockPos, setSize(state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER), x, y, z), 2);
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

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int length() {
        return length;
    }

    public enum Size {//TODO: THink of better name
        WIDTH, LENGTH, HEIGHT
    }

    public boolean shouldRotateSpecial() {
        return true;
    }
}