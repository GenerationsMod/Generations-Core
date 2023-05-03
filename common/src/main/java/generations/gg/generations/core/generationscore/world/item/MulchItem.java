package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.world.level.block.SoftSoilBlock.Mulch;
import net.minecraft.world.item.Item;

public class MulchItem extends Item {
    private final Mulch mulch;

    public MulchItem(Mulch mulch, Item.Properties properties) {
        super(properties);
        this.mulch = mulch;
    }

    public Mulch getMulch() {
        return mulch;
    }
}
