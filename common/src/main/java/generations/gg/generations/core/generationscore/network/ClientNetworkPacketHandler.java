package generations.gg.generations.core.generationscore.network;

import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.client.Minecraft;

public interface ClientNetworkPacketHandler<T extends GenerationsNetworkPacket<T>> {

    void handle(T packet, Minecraft client);

    default void handleOnNettyThread(T packet) {
        var client = Minecraft.getInstance();
        client.execute(() -> handle(packet, client));
    }
}