package generations.gg.generations.core.generationscore.fabric.networking

import com.cobblemon.mod.common.NetworkManager
import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.fabric.net.FabricPacketInfo
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.level.ServerPlayer

object GenerationsFabricNetwork: NetworkManager {
    fun registerMessages() {
        GenerationsNetwork.s2cPayloads.map { FabricPacketInfo(it) }.forEach { it.registerPacket(true) }
        GenerationsNetwork.c2sPayloads.map { FabricPacketInfo(it) }.forEach { it.registerPacket(false) }
    }

    fun registerClientHandlers() {
        GenerationsNetwork.s2cPayloads.map { FabricPacketInfo(it) }.forEach { it.registerClientHandler() }
    }

    fun registerServerHandlers() {
        GenerationsNetwork.c2sPayloads.map { FabricPacketInfo(it) }.forEach { it.registerServerHandler() }
    }

    override fun sendPacketToPlayer(player: ServerPlayer, packet: NetworkPacket<*>) {
        ServerPlayNetworking.send(player, packet)
    }

    override fun sendToServer(packet: NetworkPacket<*>) {
        ClientPlayNetworking.send(packet)
    }

}