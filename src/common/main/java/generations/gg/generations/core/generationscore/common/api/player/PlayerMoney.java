package generations.gg.generations.core.generationscore.common.api.player;

import com.cobblemon.mod.common.api.net.NetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.shop.S2CSyncPlayerMoneyPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public interface PlayerMoney {
    CompletableFuture<Boolean> withdraw(BigDecimal amount);
    CompletableFuture<Boolean> deposit(BigDecimal amount);
    CompletableFuture<BigDecimal> balance();

    default void sync(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            balance().thenApply(S2CSyncPlayerMoneyPacket::new).thenAcceptBoth(CompletableFuture.completedFuture(serverPlayer), NetworkPacket::sendToPlayer);
        } else {
            throw new RuntimeException("Tried to sync a party from the client???");
        }
    }
}
