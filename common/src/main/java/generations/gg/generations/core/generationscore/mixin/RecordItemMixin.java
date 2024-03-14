package generations.gg.generations.core.generationscore.mixin;

import generations.gg.generations.core.generationscore.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.world.level.block.entities.MeloettaMusicBoxBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.shrines.MeloettaMusicBoxBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecordItem.class)
public class RecordItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    public void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.is(GenerationsShrines.MELOETTA_MUSIC_BOX.get()) && !(Boolean)blockState.getValue(MeloettaMusicBoxBlock.HAS_RECORD)) {
            ItemStack itemStack = context.getItemInHand();
            if (!level.isClientSide) {
                Player player = context.getPlayer();
                if (level.getBlockEntity(blockPos) instanceof MeloettaMusicBoxBlockEntity jukeboxBlockEntity) {
                    jukeboxBlockEntity.setFirstItem(itemStack.copy());
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState));
                }

                itemStack.shrink(1);
                if (player != null) {
                    player.awardStat(Stats.PLAY_RECORD);
                }
            }

            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
        }
    }
}
