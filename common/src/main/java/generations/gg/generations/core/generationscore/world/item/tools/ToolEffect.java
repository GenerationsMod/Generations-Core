package com.pokemod.pokemod.world.item.tools;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public interface ToolEffect {
    boolean use(Level world, Player player, InteractionHand usedHand);
    boolean useOn(UseOnContext context);
}
