package generations.gg.generations.core.generationscore.world.level.block.generic;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;

@SuppressWarnings("deprecation")
public class GenericModelBlock<T extends BlockEntity & ModelContextProviders.ModelProvider> extends BaseEntityBlock implements SimpleWaterloggedBlock {
    protected static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");
    private static final BiFunction<BlockPos, BlockState, BlockPos> DEFAUL_BLOCK_POS_FUNCTION = (pos, state) -> pos;

    private final RegistrySupplier<BlockEntityType<T>> blockEntityFunction;
    private final BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction;
    private final ResourceLocation model;

    protected GenericModelBlock(Properties properties, RegistrySupplier<BlockEntityType<T>> blockEntityFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, ResourceLocation model) {
        super(properties);
        this.blockEntityFunction = blockEntityFunction;
        this.baseBlockPosFunction = baseBlockPosFunction;
        this.model = model;
        this.registerDefaultState(createDefaultState());
    }

    protected GenericModelBlock(Properties properties, RegistrySupplier<BlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        this(properties, blockEntityFunction, DEFAUL_BLOCK_POS_FUNCTION, model);
    }

    protected BlockState createDefaultState() {
        return this.getStateDefinition().any().setValue(WATERLOGGED, false);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return stateFromContext(blockPlaceContext);
    }

    public BlockState stateFromContext(BlockPlaceContext context) {
        return defaultBlockState().setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return baseBlockPosFunction.apply(pos, state).equals(pos) ? blockEntityFunction.get().create(pos, state) : null;
    }

    @Override
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(getBaseBlockPos(pos, state));
    }

    public ResourceLocation getModel() {
        return model;
    }

    public BlockPos getBaseBlockPos(BlockPos pos, BlockState state) {
        return baseBlockPosFunction.apply(pos, state);
    }

    @Override
    public @NotNull VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.empty();
    }

    public Optional<T> getAssoicatedBlockEntity(BlockGetter level, BlockPos pos) {
        return blockEntityFunction.toOptional().map(a -> a.getBlockEntity(level, getBaseBlockPos(pos, level.getBlockState(pos))));
    }

    public AABB computeRenderBoundingBox(Level level, BlockPos pos, BlockState state) {
        return new AABB(pos, pos.offset(1, 1, 1));
    }


    public boolean canRender(Level level, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public static boolean isWaterLogged(BlockState state) {
        return state.getValue(GenericModelBlock.WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        if(isWaterLogged(blockState)) {
            return Fluids.WATER.getSource(false);
        } else {
            return super.getFluidState(blockState);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(WATERLOGGED));
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if(isWaterLogged(blockState)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }
}