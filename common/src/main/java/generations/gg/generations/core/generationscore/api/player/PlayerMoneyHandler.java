package generations.gg.generations.core.generationscore.api.player;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PlayerMoneyHandler {
    public static final Map<Player, PlayerMoney> MONEY_CACHE = new HashMap<>();


    private static Function<Player, PlayerMoney> function = PlayerPokeDollars::new;

    public static void setFunction(Function<Player, PlayerMoney> function) {
        PlayerMoneyHandler.function = function;
    }

    public static PlayerMoney of(Player player) {
        return MONEY_CACHE.computeIfAbsent(player, function);
    }
}
