package com.pokemod.pokemod.world.item.armor.effects;

import com.pokemod.pokemod.world.item.armor.PokeModArmorItem;
import com.pokemod.pokemod.world.item.armor.ArmorEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public record UnbreakableArmorEffect() implements ArmorEffect {
    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player, PokeModArmorItem pokeModArmorItem) {
        if (world.isClientSide) return;
        if (itemStack.getOrCreateTag().getBoolean("Unbreakable")) return;
        itemStack.getOrCreateTag().putBoolean("Unbreakable", true);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int slotId, boolean isSelected, PokeModArmorItem pokeModArmorItem) {

    }
}
