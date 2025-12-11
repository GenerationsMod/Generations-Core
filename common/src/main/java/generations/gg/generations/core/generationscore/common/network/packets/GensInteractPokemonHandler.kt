package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.common.util.applyCosmeticFeature
import generations.gg.generations.core.generationscore.common.util.removeCosmeticFeature
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

object GensInteractPokemonHandler : ServerNetworkPacketHandler<GensInteractPokemonPacket> {
    override fun handle(packet: GensInteractPokemonPacket, server: MinecraftServer, player: ServerPlayer) {
        val pokemonEntity = player.serverLevel().getEntity(packet.pokemonID)
        if (pokemonEntity is PokemonEntity && !pokemonEntity.isBattleClone()) {
            if (packet.changeFormData == "revert") {
                pokemonEntity.pokemon.removeCosmeticFeature()
            } else {
                val feature: SpeciesFeature =
                    if (packet.changeFormData != "ultra") FlagSpeciesFeature(packet.changeFormData, true)
                    else StringSpeciesFeature("prism_fusion", packet.changeFormData)

                pokemonEntity.pokemon.applyCosmeticFeature(feature)
            }
        }
    }
}
