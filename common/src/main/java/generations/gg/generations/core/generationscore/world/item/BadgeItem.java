package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.util.ElementType;
import net.minecraft.world.item.Item;

public class BadgeItem extends Item {

    public BadgeItem(ElementType type) { // FIXME: the TODO: is the element which is associated with the badge
        super(new Properties().stacksTo(1));
    }
}
