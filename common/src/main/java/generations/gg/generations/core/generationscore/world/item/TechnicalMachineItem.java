package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.PokeMod;
import net.minecraft.world.item.Item;

public class TechnicalMachineItem extends MoveTeachingItem implements PixelmonInteractions.PixelmonInteraction {
    public TechnicalMachineItem(String move, Item.Properties properties) {
        super(PokeMod.id(move), properties);
    }

    @Override
    public boolean isConsumed() {
        return false;
    }
}
