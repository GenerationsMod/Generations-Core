package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PpUpItem extends Item implements PixelmonInteractions.PixelmonInteraction {

    public PpUpItem(Properties arg) {
        this(arg, false);
    }

    public PpUpItem(Properties of, boolean max) {
        super(of);
    }

    @Override
    public InteractionResult interact(PixelmonEntity pixelmonEntity, Player player, ItemStack itemInHand) {
        return InteractionResult.PASS;
    }
}
