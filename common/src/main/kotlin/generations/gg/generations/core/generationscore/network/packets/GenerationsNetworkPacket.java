package generations.gg.generations.core.generationscore.network.packets;

import com.cobblemon.mod.common.api.net.Encodable;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.Collection;
import java.util.function.Predicate;

import static com.cobblemon.mod.common.util.DistributionUtilsKt.server;

public interface GenerationsNetworkPacket<T> extends Encodable {
    ResourceLocation getId();
    default void sendToPlayer(ServerPlayer player) {
        GenerationsNetwork.INSTANCE.sendPacketToPlayer(player, this);
    }

    /**
     * TODO
     *
     * @param players
     */
    default void sendToPlayers(Iterable<ServerPlayer> players) {
        if ((players instanceof Collection<ServerPlayer> collection && !collection.isEmpty()) || players.iterator().hasNext()) {
            GenerationsNetwork.INSTANCE.sendPacketToPlayers(players, this);
        }
    }

    /**
     * TODO
     *
     */
    default void sendToAllPlayers() {
        GenerationsNetwork.INSTANCE.sendToAllPlayers(this);
    }

    /**
     * TODO
     *
     */
    default void sendToServer() {
        GenerationsNetwork.INSTANCE.sendPacketToServer(this);
    }

    // A copy from PlayerManager#sendToAround to work with our packets
    /**
     * TODO
     *
     * @param x
     * @param y
     * @param z
     * @param distance
     * @param worldKey
     * @param exclusionCondition
     */
    default void sendToPlayersAround(double x, double y, double z, double distance, ResourceKey<Level> worldKey, Predicate<ServerPlayer> exclusionCondition) {
        var server = server();
        if(server == null) return;
        server.getPlayerList().getPlayers().stream().filter(player -> {
            if (exclusionCondition.test(player))
                return false;
            var xDiff = x - player.getX();
            var yDiff = y - player.getY();
            var zDiff = z - player.getZ();
            return (xDiff * xDiff + yDiff * yDiff + zDiff) < distance * distance;
        }).forEach(player -> GenerationsNetwork.INSTANCE.sendPacketToPlayer(player, this));
    }
}