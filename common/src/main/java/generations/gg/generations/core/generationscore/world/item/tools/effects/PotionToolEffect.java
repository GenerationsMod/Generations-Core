package com.pokemod.pokemod.world.item.tools.effects;

import com.pokemod.pokemod.world.item.tools.ToolEffect;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public record PotionToolEffect(MobEffect potionEffect, int amplifier, int duration, int durabilityCost) implements ToolEffect {
    @Override
    public boolean use(Level world, Player player, InteractionHand usedHand) {
        MobEffectInstance currentEffect = player.getEffect(potionEffect);
        if (currentEffect != null && currentEffect.getAmplifier() > amplifier) return false;
        if (currentEffect != null && currentEffect.getAmplifier() == amplifier && currentEffect.getDuration() >= duration) return false;
        player.addEffect(new MobEffectInstance(potionEffect, duration, amplifier));
        player.getItemInHand(usedHand).hurtAndBreak(durabilityCost, player, owner -> owner.broadcastBreakEvent(usedHand));
        return true;
    }

    @Override
    public boolean useOn(UseOnContext context) {
        return false;
    }
}
