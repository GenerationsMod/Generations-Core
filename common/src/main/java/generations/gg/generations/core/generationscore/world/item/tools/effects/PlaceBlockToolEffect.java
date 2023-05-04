package generations.gg.generations.core.generationscore.world.item.tools.effects;

import generations.gg.generations.core.generationscore.world.item.tools.ToolEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

public record PlaceBlockToolEffect(Block block, int durabilityCost) implements ToolEffect {
    @Override
    public boolean use(Level world, Player player, InteractionHand usedHand) {
        return false;
    }

    @Override
    public boolean useOn(UseOnContext context) {
        BlockPos blockPosRelative = context.getClickedPos().relative(context.getClickedFace());
        if (!context.getLevel().getBlockState(blockPosRelative).isAir()) return false;
        context.getLevel().setBlockAndUpdate(blockPosRelative, block.defaultBlockState());
        context.getItemInHand().hurtAndBreak(durabilityCost, Objects.requireNonNull(context.getPlayer()), (owner) -> owner.broadcastBreakEvent(context.getHand()));
        return true;
    }
}
