package generations.gg.generations.core.generationscore.network.packets

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.mojang.authlib.minecraft.client.MinecraftClient
import net.minecraft.client.Minecraft

class DataRegistrySyncPacketHandler<P, T : DataRegistrySyncPacket<P, T>> : ClientNetworkPacketHandler<T> {
    override fun handle(packet: T, client: Minecraft) {
        packet.registryEntries.clear()
        packet.registryEntries.(packet.buffer!!.readList(packet::decodeEntry).filterNotNull())
        packet.buffer!!.release()
        packet.synchronizeDecoded(packet.registryEntries)
    }
}
