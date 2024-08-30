package generations.gg.generations.core.generationscore.common.network.packets

import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf

class DataRegistrySyncPacketHandler<P, T : DataRegistrySyncPacket<P, T>?> : ClientNetworkPacketHandler<T> {
    override fun handle(packet: T, minecraft: Minecraft) {
        packet!!.registryEntries.clear()
        packet.registryEntries.putAll(packet.buffer.readMap({ obj: FriendlyByteBuf -> obj.readResourceLocation() }) { packet.decodeEntry(it) })
        packet.buffer.release()
        packet.synchronizeDecoded(packet.registryEntries)
    }
}