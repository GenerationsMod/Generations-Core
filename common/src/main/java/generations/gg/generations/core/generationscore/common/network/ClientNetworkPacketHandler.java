package generations.gg.generations.core.generationscore.common.network;

import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.client.Minecraft;

public interface ClientNetworkPacketHandler<T extends GenerationsNetworkPacket<T>> {

    void handle(T packet, Minecraft minecraft);

    default void handleOnNettyThread(T packet) {
        var client = Minecraft.getInstance();
        client.execute(() -> handle(packet, client));
    }
}