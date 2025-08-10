package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.party
import com.cobblemon.mod.common.util.startWith
import generations.gg.generations.core.generationscore.common.util.applyCosmeticFeature
import generations.gg.generations.core.generationscore.common.util.removeCosmeticFeature
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

object GensInteractPokemonHandler : ServerNetworkPacketHandler<GensInteractPokemonPacket> {
    override fun handle(packet: GensInteractPokemonPacket, server: MinecraftServer, player: ServerPlayer) {
        val pokemonEntity = player.serverLevel().getEntity(packet.pokemonID)
        if (pokemonEntity is PokemonEntity && !pokemonEntity.isBattleClone()) {
            if (packet.mountShoulder) {
                if (!pokemonEntity.canSitOnShoulder() || player.party().none { it == pokemonEntity.pokemon }) {
                    return
                }
                pokemonEntity.tryMountingShoulder(player)
            } else if (packet.changeFormData.first) {
                if (packet.changeFormData.second == "revert") {
                    pokemonEntity.pokemon.removeCosmeticFeature()
                } else {
                    val feature: SpeciesFeature
                    if (packet.changeFormData.second != "ultra") {
                        feature = FlagSpeciesFeature(packet.changeFormData.second, true)
                    } else {
                        feature = StringSpeciesFeature("prism_fusion", packet.changeFormData.second)
                    }

                    pokemonEntity.pokemon.applyCosmeticFeature(feature)
                }
            } else {
                pokemonEntity.offerHeldItem(player, player.mainHandItem)
            }
        }
    }
}
