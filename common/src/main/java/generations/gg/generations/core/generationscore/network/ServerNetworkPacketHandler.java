package generations.gg.generations.core.generationscore.network;

import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public interface ServerNetworkPacketHandler<T extends GenerationsNetworkPacket<T>> {

    void handle(T packet, MinecraftServer server, ServerPlayer player);

    default void handleOnNettyThread(T packet, MinecraftServer server, ServerPlayer player) {
        server.execute(() -> handle(packet, server, player));
    }
}