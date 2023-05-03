package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.Battle;
import com.pokemod.pokemod.api.battle.BattleController;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public interface PostBattleUpdatingItem {

    void onBattleFinish(ServerPlayer player, ItemStack stack, Battle<BattleController> battle);

    default void addDamage(ItemStack stack, int damage) {
        stack.setDamageValue(Math.toIntExact(Mth.clamp(stack.getDamageValue() + damage, 0, stack.getMaxDamage())));
    }
}
