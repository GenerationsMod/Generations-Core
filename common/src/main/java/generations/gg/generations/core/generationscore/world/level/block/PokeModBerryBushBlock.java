package generations.gg.generations.core.generationscore.world.level.block;

import com.pokemod.pokemod.world.item.berry.BerryType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class PokeModBerryBushBlock extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    private final BerryType berryType;

    public PokeModBerryBushBlock(BerryType berryType, Properties arg) {
        super(arg);
        this.berryType = berryType;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    public BerryType getBerryType() {
        return berryType;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return new ItemStack(berryType.getBerry());
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if (state.getValue(AGE) == 0) {
            return SAPLING_SHAPE;
        }
        return state.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(state, level, pos, context);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        int i = state.getValue(AGE);
        if (i < 3 && level.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(5) == 0)) {
            level.setBlock(pos, state.setValue(AGE, i + 1), 2);
            ForgeHooks.onCropsGrowPost(level, pos, state);
        }
    }
    @Override
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        boolean flag;
        int i = state.getValue(AGE);
        boolean bl = flag = i == 3;
        if (!flag && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        }
        if (i > 1) {
            int j = 1 + level.random.nextInt(2);
            SweetBerryBushBlock.popResource(level, pos, new ItemStack(berryType.getBerry(), j + (flag ? 1 : 0)));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4f);
            level.setBlock(pos, state.setValue(AGE, 1), 2);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader arg, @NotNull BlockPos arg2, @NotNull BlockState arg3, boolean bl) {
        return arg3.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, BlockState state) {
        int i = Math.min(3, state.getValue(AGE) + 1);
        level.setBlock(pos, state.setValue(AGE, i), 2);
    }
}
