package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.injectables.annotations.ExpectPlatform;
import generations.gg.generations.core.generationscore.world.item.MulchItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@SuppressWarnings("deprecation")
public class SoftSoilBlock extends Block {
    public static EnumProperty<Mulch> MULCH = EnumProperty.create("mulch", Mulch.class);
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    public static final int MAX_MOISTURE = 7;

    public SoftSoilBlock(Properties arg) {
        super(arg);
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
    }

    @Override
    public void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        int i = state.getValue(MOISTURE);
        if (state.getValue(MULCH) != Mulch.DAMP)
            isNearWater(level, pos);

        if (i < 7)
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
    }

//    @Override //TODO: Figurae out
//    public boolean canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction facing, IPlantable plantable) {
//        return plantable.getPlantType(world, pos) == PlantType.CROP || state.getValue(MULCH) != Mulch.NONE;
//    }

    @Override
    public void fallOn(Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float fallDistance) {
        if (!level.isClientSide && state.getValue(MULCH) != Mulch.STABLE && InteractionEvent.FARMLAND_TRAMPLE.invoker().trample(level, pos, Blocks.DIRT.defaultBlockState(), fallDistance, entity).isTrue()) {
            turnToNone(state, level, pos);
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    public static void turnToNone(BlockState state, Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, FarmBlock.pushEntitiesUp(state, Blocks.DIRT.defaultBlockState(), level, pos));
    }

    @ExpectPlatform
    private static boolean isNearWater(LevelReader level, BlockPos pos) {
        throw new RuntimeException();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE, MULCH);
    }

    @Override
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type) {
        return false;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if(!level.isClientSide() && stack.getItem() instanceof MulchItem) {
            level.setBlockAndUpdate(pos, state.setValue(MULCH, ((MulchItem) stack.getItem()).getMulch()));
            if(!player.isCreative())
                stack.shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    public enum Mulch implements StringRepresentable {
        NONE,
        GROWTH,
        DAMP,
        STABLE,
        GOOEY,
        RICH,
        SURPRISE,
        BOOST,
        AMAZE;

        private final String name;

        Mulch() {
            name = name().toLowerCase(Locale.ENGLISH);
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }
}
