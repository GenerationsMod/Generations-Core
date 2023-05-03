package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.Battle;
import com.pokemod.pokemod.api.battle.BattleController;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SacredAshItem extends Item implements PostBattleUpdatingItem {

    public SacredAshItem(Item.Properties properties) {
        super(properties);
    }

    public static boolean isFullyCharged(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage();
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return isFullyCharged(stack);
    }

    @Override
    public void onBattleFinish(ServerPlayer player, ItemStack stack, Battle<BattleController> battle) {
        // return pixelmonData.data().getSecond().equals(PokeMod.id("sacred_fire"));
    }
}
