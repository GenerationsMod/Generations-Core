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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class BallDisplayBlock extends GenericRotatableModelBlock<BallDisplayBlockEntity> {
    private final String variant;

    public BallDisplayBlock(Properties properties, String variant) {
        super(properties, GenerationsBlockEntities.BALL_DISPLAY, GenerationsBlockEntityModels.BALL_DISPLAY);
        this.variant = variant;
    }

    public String getVariant() {
        return variant;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        var block = getBlockToSwap(player.getItemInHand(handIn).getItem());
        var ball = getBallToSwap(state.getBlock());

        if (!world.isClientSide())
            if ((player.isShiftKeyDown() && ball.isEmpty()) || !block.equals(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get())) {

                world.setBlockAndUpdate(pos, (block != null ? block.defaultBlockState().setValue(BallDisplayBlock.FACING, state.getValue(BallDisplayBlock.FACING)) : Blocks.AIR.defaultBlockState()));
                player.setItemInHand(handIn, ball);
            }

        return InteractionResult.SUCCESS;
    }


    public ItemStack getBallToSwap(Block block) {
        Item ball;

        if(block.equals(GenerationsDecorationBlocks.POKE_BALL_DISPLAY.get())) ball = GenerationsItems.POKE_BALL.get();
        else if(block.equals(GenerationsDecorationBlocks.GREAT_BALL_DISPLAY.get())) ball = GenerationsItems.GREAT_BALL.get();
        else if(block.equals(GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY.get())) ball = GenerationsItems.ULTRA_BALL.get();
        else if(block.equals(GenerationsDecorationBlocks.MASTER_BALL_DISPLAY.get())) ball = GenerationsItems.MASTER_BALL.get();
        else return ItemStack.EMPTY;

        return new ItemStack(ball);
    }

    public Block getBlockToSwap(Item ball) {
        if(ball.equals(GenerationsItems.POKE_BALL.get())) return GenerationsDecorationBlocks.POKE_BALL_DISPLAY.get();
        else if(ball.equals(GenerationsItems.GREAT_BALL.get())) return GenerationsDecorationBlocks.GREAT_BALL_DISPLAY.get();
        else if(ball.equals(GenerationsItems.ULTRA_BALL.get())) return GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY.get();
        else if(ball.equals(GenerationsItems.MASTER_BALL.get())) return GenerationsDecorationBlocks.MASTER_BALL_DISPLAY.get();
        else return GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get();
    }
}
