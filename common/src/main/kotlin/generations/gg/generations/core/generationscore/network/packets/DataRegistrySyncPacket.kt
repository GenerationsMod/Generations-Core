package generations.gg.generations.core.generationscore.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

abstract class DataRegistrySyncPacket<T, N : NetworkPacket<N>>(internal val registryEntries: MutableMap<ResourceLocation, T>) : NetworkPacket<N> {

    var buffer: FriendlyByteBuf? = null

    override fun encode(buffer: FriendlyByteBuf) {
        buffer.writeMap(this.registryEntries, FriendlyByteBuf::writeResourceLocation, this::encodeEntry)
    }

    internal fun decodeBuffer(buffer: FriendlyByteBuf) {
        this.buffer = buffer
        buffer.retain()
    }

    /**
     * Encodes an entry of type [T] to the [PacketByteBuf].
     *
     * @param buffer The [PacketByteBuf] being encoded to.
     * @param entry The entry of type [T].
     */
    abstract fun encodeEntry(buffer: FriendlyByteBuf, entry: T)

    /**
     * Attempts to decode this entry, if null it will be skipped.
     * Any errors that result in a null entry should be logged.
     *
     * @param buffer The [PacketByteBuf] being decoded from.
     * @return The entry of type [T].
     */
    abstract fun decodeEntry(buffer: FriendlyByteBuf): T?

    /**
     * Synchronizes the final product the final product with the backing registry.
     *
     * @param entries The processed entries.
     */
    abstract fun synchronizeDecoded(entries: Map<ResourceLocation, T>)
}