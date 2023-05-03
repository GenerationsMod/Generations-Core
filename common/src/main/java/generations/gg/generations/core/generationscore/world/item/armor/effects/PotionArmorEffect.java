package com.pokemod.pokemod.world.item.armor.effects;

import com.pokemod.pokemod.world.item.armor.PokeModArmorItem;
import com.pokemod.pokemod.world.item.armor.ArmorEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public record PotionArmorEffect(MobEffect potionEffect, int amplifier) implements ArmorEffect {
    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player, PokeModArmorItem pokeModArmorItem) {
        if (world.isClientSide) return;
        if (ArmorEffect.isWearingFullSet(player, pokeModArmorItem.getMaterial())) return;
        MobEffectInstance currentEffect = player.getEffect(potionEffect);
        if (currentEffect != null && currentEffect.getAmplifier() > amplifier) return;
        if (currentEffect != null && currentEffect.getAmplifier() == amplifier && currentEffect.getDuration() >= 20) return;
        player.addEffect(new MobEffectInstance(potionEffect, 20, amplifier));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int slotId, boolean isSelected, PokeModArmorItem pokeModArmorItem) {

    }
}
