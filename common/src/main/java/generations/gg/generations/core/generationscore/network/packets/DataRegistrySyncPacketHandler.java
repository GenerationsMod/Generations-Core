package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import net.minecraft.network.FriendlyByteBuf;

public class DataRegistrySyncPacketHandler<P, T extends DataRegistrySyncPacket<P, T>> implements ClientNetworkPacketHandler<T> {
    public void handle(T packet) {
        packet.getRegistryEntries().clear();
        packet.getRegistryEntries().putAll(packet.getBuffer().readMap(
                FriendlyByteBuf::readResourceLocation,
                packet::decodeEntry));
        packet.getBuffer().release();
        packet.synchronizeDecoded(packet.getRegistryEntries());
    }
}