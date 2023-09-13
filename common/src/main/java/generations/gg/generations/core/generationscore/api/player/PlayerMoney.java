package generations.gg.generations.core.generationscore.api.player;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.shop.S2CSyncPlayerMoneyPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public interface PlayerMoney {
    boolean withdraw(int amount);
    boolean deposit(int amount);
    int balance();

    default void sync(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            GenerationsCore.implementation.getNetworkManager().sendPacketToPlayer(serverPlayer, new S2CSyncPlayerMoneyPacket(balance()));
        } else {
            throw new RuntimeException("Tried to sync a party from the client???");
        }
    }
}
