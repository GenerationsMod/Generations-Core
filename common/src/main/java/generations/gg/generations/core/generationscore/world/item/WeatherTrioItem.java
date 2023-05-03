package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.Battle;
import com.pixelmongenerations.mimikyu.util.ElementType;
import com.pokemod.pokemod.api.battle.BattleController;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WeatherTrioItem extends Item implements PostBattleUpdatingItem {

    private final ElementType type;

    public WeatherTrioItem(Item.Properties properties, ElementType type) {
        super(properties);
        this.type = type;
    }

    @Override
    public void onBattleFinish(ServerPlayer player, ItemStack stack, Battle<BattleController> battle) {
        // return pixelmonData.data().getFirst().getPokemonForm().types().contains(type);
    }
}
