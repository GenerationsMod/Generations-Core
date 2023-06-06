package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.decorations.PokeDollBlock;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Supplier;

public class BallDisplayBlock extends GenericRotatableModelBlock<BallDisplayBlockEntity> {
    private static VoxelShape FULL = Shapes.box(0.25, 0, 0.25, 0.75, 0.625, 0.75);
    private static VoxelShape EMPTY = Shapes.box(0.25, 0, 0.25, 0.75, 0.125, 0.75);
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
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
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
        EMPTY(() -> () -> Items.AIR, () -> GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY),
        POKE(() -> GenerationsItems.POKE_BALL, () -> GenerationsDecorationBlocks.POKE_BALL_DISPLAY),
        GREAT(() -> GenerationsItems.GREAT_BALL, () -> GenerationsDecorationBlocks.GREAT_BALL_DISPLAY),
        ULTRA(() -> GenerationsItems.ULTRA_BALL, () -> GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY),
        MASTER(() -> GenerationsItems.MASTER_BALL, () -> GenerationsDecorationBlocks.MASTER_BALL_DISPLAY),
        CHERISH(() -> GenerationsItems.CHERISH_BALL, () -> GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY),
        DIVE(() -> GenerationsItems.DIVE_BALL, () -> GenerationsDecorationBlocks.DIVE_BALL_DISPLAY),
        DUSK(() -> GenerationsItems.DUSK_BALL, () -> GenerationsDecorationBlocks.DUSK_BALL_DISPLAY),
        FAST(() -> GenerationsItems.FAST_BALL, () -> GenerationsDecorationBlocks.FAST_BALL_DISPLAY),
        FRIEND(() -> GenerationsItems.FRIEND_BALL, () -> GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY),
        GS(() -> GenerationsItems.GS_BALL, () -> GenerationsDecorationBlocks.GS_BALL_DISPLAY),
        HEAL(() -> GenerationsItems.HEAL_BALL, () -> GenerationsDecorationBlocks.HEAL_BALL_DISPLAY),
        HEAVY(() -> GenerationsItems.HEAVY_BALL, () -> GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY),
        LEVEL(() -> GenerationsItems.LEVEL_BALL, () -> GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY),
        LOVE(() -> GenerationsItems.LOVE_BALL, () -> GenerationsDecorationBlocks.LOVE_BALL_DISPLAY),
        LURE(() -> GenerationsItems.LURE_BALL, () -> GenerationsDecorationBlocks.LURE_BALL_DISPLAY),
        LUXURY(() -> GenerationsItems.LUXURY_BALL, () -> GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY),
        MOON(() -> GenerationsItems.MOON_BALL, () -> GenerationsDecorationBlocks.MOON_BALL_DISPLAY),
        NEST(() -> GenerationsItems.NEST_BALL, () -> GenerationsDecorationBlocks.NEST_BALL_DISPLAY),
        NET(() -> GenerationsItems.NET_BALL, () -> GenerationsDecorationBlocks.NET_BALL_DISPLAY),
        PARK(() -> GenerationsItems.PARK_BALL, () -> GenerationsDecorationBlocks.PARK_BALL_DISPLAY),
        PREMIER(() -> GenerationsItems.PREMIER_BALL, () -> GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY),
        QUICK(() -> GenerationsItems.QUICK_BALL, () -> GenerationsDecorationBlocks.QUICK_BALL_DISPLAY),
        REPEAT(() -> GenerationsItems.REPEAT_BALL, () -> GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY),
        SAFARI(() -> GenerationsItems.SAFARI_BALL, () -> GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY),
        SPORT(() -> GenerationsItems.SPORT_BALL, () -> GenerationsDecorationBlocks.SPORT_BALL_DISPLAY),
        TIMER(() -> GenerationsItems.TIMER_BALL, () -> GenerationsDecorationBlocks.TIMER_BALL_DISPLAY);
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
