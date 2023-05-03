package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.PokeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RegiKeyItem extends Item {
    private final ResourceLocation speciesId;

    public RegiKeyItem(Item.Properties properties, String speciesId) {
        super(properties);
        this.speciesId = PokeMod.id(speciesId);
    }

    public ResourceLocation getSpeciesId() {
        return speciesId;
    }
}
