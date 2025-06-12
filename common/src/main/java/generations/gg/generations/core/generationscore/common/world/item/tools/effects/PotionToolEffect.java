package generations.gg.generations.core.generationscore.common.world.item.tools.effects;

import generations.gg.generations.core.generationscore.common.world.item.tools.ToolEffect;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public record PotionToolEffect(Holder<MobEffect> potionEffect, int amplifier, int duration, int durabilityCost) implements ToolEffect {
    @Override
    public boolean use(Level world, Player player, InteractionHand usedHand) {
        if (world.isClientSide()) return false;
        MobEffectInstance currentEffect = player.getEffect(potionEffect);
        if (currentEffect != null && currentEffect.getAmplifier() > amplifier) return false;
        if (currentEffect != null && currentEffect.getAmplifier() == amplifier && currentEffect.getDuration() >= duration) return false;
        player.addEffect(new MobEffectInstance(potionEffect, duration, amplifier));
        player.getItemInHand(usedHand).hurtAndBreak(durabilityCost, (ServerLevel) player.level(), (ServerPlayer) player, owner -> {}/*owner.broadcastBreakEvent(usedHand)*/);
        return true;
    }

    @Override
    public boolean useOn(UseOnContext context) {
        return false;
    }
}
