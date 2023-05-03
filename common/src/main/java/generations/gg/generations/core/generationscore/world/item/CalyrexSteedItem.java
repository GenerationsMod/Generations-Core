package com.pokemod.pokemod.world.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class CalyrexSteedItem extends Item {
    private final ResourceLocation speices;
    private final RegistryObject<Item> unenchanted;

    public CalyrexSteedItem(Properties arg, ResourceLocation speices, RegistryObject<Item> unenchanted) {
        super(arg);
        this.speices = speices;
        this.unenchanted = unenchanted;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    public Item getUnEnchanted() {
        return unenchanted.get();
    }

    public ResourceLocation getSpeices() {
        return speices;
    }
}
