package generations.gg.generations.core.generationscore.fabric.networking

import com.cobblemon.mod.fabric.net.FabricPacketInfo
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork

object GenerationsFabricNetwork {
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
}