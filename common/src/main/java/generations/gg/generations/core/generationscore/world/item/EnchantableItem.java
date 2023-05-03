package com.pokemod.pokemod.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnchantableItem extends Item {
    public EnchantableItem(Properties arg) {
        super(arg);
    }

    public static ItemStack getEnchanted(Item item) {
        ItemStack stack = new ItemStack(item);
        stack.getOrCreateTag().putBoolean("enchanted", true);
        return stack;
    }

    public static ItemStack setEnchanted(ItemStack stack, boolean enchanted) {
        stack.getOrCreateTag().putBoolean("enchanted", enchanted);
        return stack;
    }

    public static boolean isEnchanted(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("enchanted");
    }

    public boolean isFoil(@NotNull ItemStack stack) {
        return isEnchanted(stack);
    }
}
