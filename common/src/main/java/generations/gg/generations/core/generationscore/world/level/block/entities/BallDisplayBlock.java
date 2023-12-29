package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Supplier;

@SuppressWarnings("deprecated")
public class BallDisplayBlock extends GenericRotatableModelBlock<BallDisplayBlockEntity> {
    private static final VoxelShape FULL = Shapes.box(0.25, 0, 0.25, 0.75, 0.625, 0.75);
    private static final VoxelShape EMPTY = Shapes.box(0.25, 0, 0.25, 0.75, 0.125, 0.75);
    private final String variant;
    private final DisplayState state;

    public BallDisplayBlock(Properties properties, DisplayState state) {
        super(properties, GenerationsBlockEntities.BALL_DISPLAY, GenerationsBlockEntityModels.BALL_DISPLAY);
        this.variant = state.name().toLowerCase(Locale.ROOT);
        this.state = state;
    }

    public String getVariant() {
        return variant;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return state == DisplayState.EMPTY ? EMPTY : FULL;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        var stack = player.getItemInHand(handIn);
        var block = ((BallDisplayBlock) state.getBlock()).state;
        var ball = DisplayState.getState(stack);

        if (!world.isClientSide() && ball != null && ball != block) {
            world.setBlockAndUpdate(pos, ball.block.get().get().defaultBlockState().setValue(BallDisplayBlock.FACING, state.getValue(BallDisplayBlock.FACING)));
            stack.shrink(1);
            player.addItem(block.ball.get().get().getDefaultInstance());
        }

        return InteractionResult.SUCCESS;
    }
    public enum DisplayState {
        EMPTY(() -> Items.AIR::asItem, () -> GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY),
        POKE(() -> CobblemonItems.POKE_BALL::asItem, () -> GenerationsDecorationBlocks.POKE_BALL_DISPLAY),
        GREAT(() -> CobblemonItems.GREAT_BALL::asItem, () -> GenerationsDecorationBlocks.GREAT_BALL_DISPLAY),
        ULTRA(() -> CobblemonItems.ULTRA_BALL::asItem, () -> GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY),
        MASTER(() -> CobblemonItems.MASTER_BALL::asItem, () -> GenerationsDecorationBlocks.MASTER_BALL_DISPLAY),
        CHERISH(() -> CobblemonItems.CHERISH_BALL::asItem, () -> GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY),
        DIVE(() -> CobblemonItems.DIVE_BALL::asItem, () -> GenerationsDecorationBlocks.DIVE_BALL_DISPLAY),
        DUSK(() -> CobblemonItems.DUSK_BALL::asItem, () -> GenerationsDecorationBlocks.DUSK_BALL_DISPLAY),
        FAST(() -> CobblemonItems.FAST_BALL::asItem, () -> GenerationsDecorationBlocks.FAST_BALL_DISPLAY),
        FRIEND(() -> CobblemonItems.FRIEND_BALL::asItem, () -> GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY),
        //GS(() -> CobblemonItems.GS_BALL::asItem, () -> GenerationsDecorationBlocks.GS_BALL_DISPLAY),
        HEAL(() -> CobblemonItems.HEAL_BALL::asItem, () -> GenerationsDecorationBlocks.HEAL_BALL_DISPLAY),
        HEAVY(() -> CobblemonItems.HEAVY_BALL::asItem, () -> GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY),
        LEVEL(() -> CobblemonItems.LEVEL_BALL::asItem, () -> GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY),
        LOVE(() -> CobblemonItems.LOVE_BALL::asItem, () -> GenerationsDecorationBlocks.LOVE_BALL_DISPLAY),
        LURE(() -> CobblemonItems.LURE_BALL::asItem, () -> GenerationsDecorationBlocks.LURE_BALL_DISPLAY),
        LUXURY(() -> CobblemonItems.LUXURY_BALL::asItem, () -> GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY),
        MOON(() -> CobblemonItems.MOON_BALL::asItem, () -> GenerationsDecorationBlocks.MOON_BALL_DISPLAY),
        NEST(() -> CobblemonItems.NEST_BALL::asItem, () -> GenerationsDecorationBlocks.NEST_BALL_DISPLAY),
        NET(() -> CobblemonItems.NET_BALL::asItem, () -> GenerationsDecorationBlocks.NET_BALL_DISPLAY),
        PARK(() -> CobblemonItems.PARK_BALL::asItem, () -> GenerationsDecorationBlocks.PARK_BALL_DISPLAY),
        PREMIER(() -> CobblemonItems.PREMIER_BALL::asItem, () -> GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY),
        QUICK(() -> CobblemonItems.QUICK_BALL::asItem, () -> GenerationsDecorationBlocks.QUICK_BALL_DISPLAY),
        REPEAT(() -> CobblemonItems.REPEAT_BALL::asItem, () -> GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY),
        SAFARI(() -> CobblemonItems.SAFARI_BALL::asItem, () -> GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY),
        SPORT(() -> CobblemonItems.SPORT_BALL::asItem, () -> GenerationsDecorationBlocks.SPORT_BALL_DISPLAY),
        TIMER(() -> CobblemonItems.TIMER_BALL::asItem, () -> GenerationsDecorationBlocks.TIMER_BALL_DISPLAY);
        private final Supplier<Supplier<Item>> ball;
        private final Supplier<Supplier<BallDisplayBlock>> block;

        DisplayState(Supplier<Supplier<Item>> ball, Supplier<Supplier<BallDisplayBlock>> block) {
            this.ball = ball;

            this.block = block;
        }

        public static DisplayState getState(ItemStack stack) {
            for (var state : DisplayState.values()) {
                if(stack.is(state.ball.get().get())) return state;
            }

            return null;
        }
    }
}
