package generations.gg.generations.core.generationscore.common.world.item.tools.effects;

import generations.gg.generations.core.generationscore.common.world.item.tools.ToolEffect;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public record TransformToolEffect(Block blockFrom, Block blockTo, int durabilityCost) implements ToolEffect {
    @Override
    public boolean use(Level world, Player player, InteractionHand usedHand) {
        if (blockFrom.defaultBlockState().canOcclude()) return false;
        BlockHitResult blockhitresult = getPlayerPOVHitResult(world, player);
        if (!world.mayInteract(player, blockhitresult.getBlockPos())) return false;
        if (!world.getBlockState(blockhitresult.getBlockPos()).getBlock().equals(blockFrom)) return false;
        world.setBlockAndUpdate(blockhitresult.getBlockPos(), blockTo.defaultBlockState());
        player.getItemInHand(usedHand).hurtAndBreak(durabilityCost, player, owner -> owner.broadcastBreakEvent(usedHand));
        return true;
    }

    @Override
    public boolean useOn(UseOnContext context) {
        if (!blockFrom.defaultBlockState().canOcclude()) return false;
        if (!context.getLevel().getBlockState(context.getClickedPos()).getBlock().equals(blockFrom)) return false;
        context.getLevel().setBlockAndUpdate(context.getClickedPos(), blockTo.defaultBlockState());
        context.getItemInHand().hurtAndBreak(1, Objects.requireNonNull(context.getPlayer()), (owner) -> owner.broadcastBreakEvent(context.getHand()));
        return true;
    }

    private static BlockHitResult getPlayerPOVHitResult(Level level, Player player) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3 = player.getEyePosition();
        float f2 = Mth.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = Mth.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 5d; //Objects.requireNonNull(player.getAttribute(ForgeMod.REACH_DISTANCE.get())).getValue(); TODO: figure out platform netural variant.
        Vec3 vec31 = vec3.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, ClipContext.Fluid.SOURCE_ONLY, player));
    }
}
