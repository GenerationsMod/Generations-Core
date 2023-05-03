package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.Battle;
import com.pokemod.pokemod.api.battle.BattleController;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.TriPredicate;

public class MeltanBox extends PostBattleUpdatingItemImpl {

    public MeltanBox(Properties settings, String speciesId, String lang, TriPredicate<ServerPlayer, ItemStack, Battle<BattleController>> predicate) {
        super(settings, speciesId, lang, predicate);
    }
}
