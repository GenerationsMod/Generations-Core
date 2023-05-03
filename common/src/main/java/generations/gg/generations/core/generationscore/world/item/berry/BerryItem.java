package com.pokemod.pokemod.world.item.berry;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class BerryItem extends ItemNameBlockItem {
    private final BerryType berryType;

    public BerryItem(BerryType berryType, RegistryObject<Block> block, Properties properties) {
        super(block.get(), properties);
        this.berryType = berryType;
    }

    public BerryType getBerry() {
        return berryType;
    }
}
