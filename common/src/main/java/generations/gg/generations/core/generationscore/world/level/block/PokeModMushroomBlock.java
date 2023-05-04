package generations.gg.generations.core.generationscore.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class PokeModMushroomBlock extends BushBlock implements BonemealableBlock {
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;

    public PokeModMushroomBlock(Properties arg, ResourceKey<ConfiguredFeature<?, ?>> featureKey) {
        super(arg);
        this.featureKey = featureKey;
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return state.isSolidRender(level, pos);
    }

    public void growMushroom(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        level.removeBlock(pos, false);
        if (!level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).get(featureKey).place(level, level.getChunkSource().getGenerator(), random, pos)) level.setBlock(pos, state, 3);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader arg, @NotNull BlockPos arg2, @NotNull BlockState arg3, boolean bl) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return (double)random.nextFloat() < 0.4;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        this.growMushroom(level, pos, state, random);
    }
}
