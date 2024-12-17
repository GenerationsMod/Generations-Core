package generations.gg.generations.core.generationscore.common.world.level.block.generic;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.util.MathUtils;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
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

            return block.adjustBlockPos(pos, facing, x, y, z, false);
        }

        return pos;
    };

    protected int width;
    protected int height;
    protected int length;

    protected GenericRotatableModelBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, ResourceLocation model, int width, int height, int length) {
        super(materialIn, blockEntityFunction, baseBlockPosFunction, model);
        assignSize(width, height, length);
        reassignStateDefinition();
        this.registerDefaultState(createDefaultState());

    }

    protected GenericRotatableModelBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, ResourceLocation model) {
        this(materialIn, blockEntityFunction, baseBlockPosFunction, model, 0, 0, 0);
    }

    public GenericRotatableModelBlock(Properties properties, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model, int width, int height, int length) {
        super(properties, blockEntityFunction, DEFAULT_BLOCK_ROTATE_POS_FUNCTION, model);
        assignSize(width, height, length);
        reassignStateDefinition();
        this.registerDefaultState(createDefaultState());
    }

    protected GenericRotatableModelBlock(Properties properties, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
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

    protected void reassignStateDefinition() {
        StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder<>(this);
        this.createBlockStateDefinition(builder);
        this.stateDefinition = builder.create(Block::defaultBlockState, BlockState::new);
    }

    protected BlockState createDefaultState() {
        return setSize(super.createDefaultState().setValue(FACING, Direction.NORTH), getBaseX(), 0, getBaseZ());
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
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        var pos = context.getClickedPos();
        var level = context.getLevel();
        var dir = context.getHorizontalDirection().getOpposite();

        var state = super.getStateForPlacement(context);

        if(pos.getY() < level.getMaxBuildHeight() - height && isAreaClear(level, dir, pos)) {
            return setSize(state.setValue(FACING, dir), getBaseX(), 0, getBaseZ());
        } else {
            return null;
        }
    }

    public int getBaseX() {
        return 0;
    }

    public int getBaseZ() {
        return 0;
    }

    protected boolean isAreaClear(Level level, Direction dir, BlockPos pos) {

        for (int x = 0; x < width + 1; x++) {
            for (int z = 0; z < length + 1; z++) {
                for (int y = 0; y < height + 1; y++) {
                    if(!validPosition(x,y,z)) continue;

                    var blockPos = adjustBlockPos(pos, dir, x, y, z, true);

                    var state = level.getBlockState(blockPos);

                    if(!state.canBeReplaced() || state.is(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    protected BlockPos adjustBlockPos(BlockPos pos, Direction dir, int x, int y, int z, boolean positive) {
        return positive ? pos.relative(dir.getCounterClockWise(), adjustX(x)).relative(Direction.UP, y).relative(dir.getOpposite(), adjustZ(z)) : pos.relative(dir.getClockWise(), adjustX(x)).relative(Direction.DOWN, y).relative(dir, adjustZ(z));
    }

    protected boolean validPosition(int x, int y, int z) {
        return true;
    }

    protected boolean matches(BlockState state, int x, int y, int z) {
        return getWidthValue(state) == x && getHeightValue(state) == y && getLengthValue(state) == z;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var dir = state.getValue(FACING);
        var base = getBaseBlockPos(pos, state);

        return isAreaClear((Level) level, dir, base);
    }

    @Override
    public void playerWillDestroy(Level level, @NotNull BlockPos pos, @NotNull BlockState state, Player player) {
        pos = getBaseBlockPos(pos, state);
        var facing = state.getValue(FACING);

        for (int x = 0; x <= width; x++) {
            for (int z = 0; z <= length; z++) {
                for (int y = 0; y <= height; y++) {
                    if(!validPosition(x,y, z)) continue;


                    var blockPos = adjustBlockPos(pos, facing, x, y,z, true);

                    level.destroyBlock(blockPos, !player.isCreative() && x == 0 && y == 0 && z == 0);
                }
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }


    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, tool);
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder params) {
        if(getWidthValue(state) == getBaseX() && getLengthValue(state) == 0 && getHeightValue(state) == getBaseZ()) return super.getDrops(state, params);
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

//    @Override
//    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
//        if(canSurvive(state, level, currentPos)) return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
//        else return Blocks.AIR.defaultBlockState();
//    }

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
        return new AABB(pos, adjustBlockPos(getBaseBlockPos(pos, state), state.getValue(FACING), width + 1, height + 1, length + 1, true));
    }

    @Override
    public boolean canRender(Level level, BlockPos blockPos, BlockState blockState) {
        return getWidthValue(blockState) == getBaseX() || getHeightValue(blockState) == 0 || getLengthValue(blockState) == getBaseZ();
    }

    public float getAngle(BlockState state) {
        return state.getValue(FACING).toYRot();
    }

    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        for (int x = 0; x <= width; x++) {
            for (int z = 0; z <= length; z++) {
                for (int y = 0; y <= height; y++) {
                    if(!validPosition(x,y, z)) continue;

                    if(x == getBaseX() && y == 0 && z == getBaseZ()) continue;
                    var blockPos = adjustBlockPos(pos, state.getValue(FACING), x, y, z, true);
                    level.setBlock(blockPos, setSize(state.setValue(WATERLOGGED, level.getFluidState(blockPos).getType() == Fluids.WATER), x, y, z), 2);
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

    public int adjustX(int x) {
        return x - getBaseX();
    }

    public int adjustZ(int z) {
        return z - getBaseZ();
    }

    public enum Size {//TODO: THink of better name
        WIDTH, LENGTH, HEIGHT
    }

    public boolean shouldRotateSpecial() {
        return true;
    }
}