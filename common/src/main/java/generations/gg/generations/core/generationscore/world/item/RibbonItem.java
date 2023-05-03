package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.util.ElementType;
import net.minecraft.world.item.Item;

public class RibbonItem extends Item {

    public RibbonItem(ElementType element) { // FIXME: the TODO: is the element which is associated with the ribbon
        super(new Properties().stacksTo(1));
    }
}
