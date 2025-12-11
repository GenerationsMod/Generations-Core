package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.client.createPokemonInteractGui
import net.minecraft.client.Minecraft

object GensInteractPokemonUIPacketHandler: ClientNetworkPacketHandler<GensInteractPokemonUIPacket> {
    override fun handle(packet: GensInteractPokemonUIPacket, client: Minecraft) {
        client.setScreen(createPokemonInteractGui(
            packet.pokemonID,
            packet.canMountShoulder,
            packet.canGiveHeld,
            packet.canGiveCosmetic,
            packet.canRide,
            packet.changeFormData))
    }
}