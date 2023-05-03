package com.pokemod.pokemod.world.item.armor.effects;

import com.pokemod.pokemod.world.item.armor.PokeModArmorItem;
import com.pokemod.pokemod.world.item.armor.ArmorEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Map;

public record EnchantmentArmorEffect(Enchantment enchantment, int level) implements ArmorEffect {
    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player, PokeModArmorItem pokeModArmorItem) {
        if (world.isClientSide) return;
        if (ArmorEffect.isWearingFullSet(player, pokeModArmorItem.getMaterial())) {
            itemStack.removeTagKey("Enchantments");
            return;
        }
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
        if (enchantments.getOrDefault(enchantment, 0) >= level) return;
        enchantments.put(enchantment, level);
        EnchantmentHelper.setEnchantments(enchantments, itemStack);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int slotId, boolean isSelected, PokeModArmorItem pokeModArmorItem) {
        if (world.isClientSide) return;
        for (ItemStack armorSlot : entity.getArmorSlots()) {
            if (itemStack == armorSlot) return;
        }
        itemStack.removeTagKey("Enchantments");
    }
}
