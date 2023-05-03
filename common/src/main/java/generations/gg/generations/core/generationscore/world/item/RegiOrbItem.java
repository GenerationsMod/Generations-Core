package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.PokeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RegiOrbItem extends Item {
    private final ResourceLocation speciesId;

    public RegiOrbItem(Properties arg, String speciesId) {
        super(arg);
        this.speciesId = PokeMod.id(speciesId);
    }

    public ResourceLocation getSpeciesId() {
        return speciesId;
    }
}
