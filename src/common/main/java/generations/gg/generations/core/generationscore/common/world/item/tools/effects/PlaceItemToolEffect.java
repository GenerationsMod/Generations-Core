package generations.gg.generations.core.generationscore.common.world.item.tools.effects;

import generations.gg.generations.core.generationscore.common.world.item.tools.ToolEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Objects;

public record PlaceItemToolEffect(BlockItem item, int durabilityCost) implements ToolEffect {
    @Override
    public boolean use(Level world, Player player, InteractionHand usedHand) {
        return false;
    }

    @Override
    public boolean useOn(UseOnContext context) {
        if (context.getLevel().isClientSide()) return false;
        if (!item.useOn(new UseOnContext(context.getLevel(), context.getPlayer(), context.getHand(), new ItemStack(item), new BlockHitResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside()))).consumesAction()) return false;
        context.getItemInHand().hurtAndBreak(durabilityCost, (ServerLevel) context.getLevel(), (ServerPlayer) Objects.requireNonNull(context.getPlayer()), (owner) -> {} /*owner.broadcastBreakEvent(context.getHand())*/);
        return true;
    }
}
