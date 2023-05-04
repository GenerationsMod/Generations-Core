package generations.gg.generations.core.generationscore.world.level.block.generic;

import com.pokemod.pokemod.client.model.ModelContextProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;

@SuppressWarnings("deprecation")
public class GenericModelBlock<T extends BlockEntity & ModelContextProviders.ModelProvider> extends BaseEntityBlock {
    private static final BiFunction<BlockPos, BlockState, BlockPos> DEFAUL_BLOCK_POS_FUNCTION = (pos, state) -> pos;

    private final RegistryObject<BlockEntityType<T>> blockEntityFunction;
    private final BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction;
    private final ResourceLocation model;

    protected GenericModelBlock(Properties properties, RegistryObject<BlockEntityType<T>> blockEntityFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, ResourceLocation model) {
        super(properties);
        this.blockEntityFunction = blockEntityFunction;
        this.baseBlockPosFunction = baseBlockPosFunction;
        this.model = model;
        this.registerDefaultState(createDefaultState());
    }

    protected GenericModelBlock(Properties properties, RegistryObject<BlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        this(properties, blockEntityFunction, DEFAUL_BLOCK_POS_FUNCTION, model);
    }

    protected BlockState createDefaultState() {
        return this.getStateDefinition().any();
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

    protected BlockPos getBaseBlockPos(BlockPos pos, BlockState state) {
        return baseBlockPosFunction.apply(pos, state);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.empty();
    }

    public Optional<T> getAssoicatedBlockEntity(BlockGetter level, BlockPos pos) {
        return blockEntityFunction.map(a -> a.getBlockEntity(level, getBaseBlockPos(pos, level.getBlockState(pos))));
    }

    public AABB computeRenderBoundingBox(Level level, BlockPos pos, BlockState state) {
        return new AABB(pos, pos.offset(1, 1, 1));
    }


    public boolean canRender(Level level, BlockPos blockPos, BlockState blockState) {
        return true;
    }
}