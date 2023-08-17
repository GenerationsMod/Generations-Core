package generations.gg.generations.core.generationscore.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public abstract class DataRegistrySyncPacket<T, N extends GenerationsNetworkPacket<N>> implements GenerationsNetworkPacket<N> {
    private final Map<ResourceLocation, T> registryEntries;

    public DataRegistrySyncPacket(Map<ResourceLocation, T> registryEntries) {
        this.registryEntries = registryEntries;
    }


    private FriendlyByteBuf buffer = null;

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeMap(this.registryEntries, FriendlyByteBuf::writeResourceLocation, this::encodeEntry);
    }

    public void decodeBuffer(FriendlyByteBuf buffer) {
        this.buffer = buffer;
        buffer.retain();
    }

    /**
     * Encodes an entry of type [T] to the [PacketByteBuf].
     *
     * @param buffer The [PacketByteBuf] being encoded to.
     * @param entry The entry of type [T].
     */
    public abstract void encodeEntry(FriendlyByteBuf buffer, T entry);

    /**
     * Attempts to decode this entry, if null it will be skipped.
     * Any errors that result in a null entry should be logged.
     *
     * @param buffer The [PacketByteBuf] being decoded from.
     * @return The entry of type [T].
     */
    public abstract T decodeEntry(FriendlyByteBuf buffer);

    /**
     * Synchronizes the final product the final product with the backing registry.
     *
     * @param entries The processed entries.
     */
    public abstract void synchronizeDecoded(Map<ResourceLocation, T> entries);

    public Map<ResourceLocation, T> getRegistryEntries() {
        return registryEntries;
    }

    public FriendlyByteBuf getBuffer() {
        return buffer;
    }
}