package generations.gg.generations.core.generationscore.common.event

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.util.party
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor
import generations.gg.generations.core.generationscore.common.battle.removeBattleFeature
import net.minecraft.server.level.ServerPlayer

object PlayerJoinHandler {
    fun onJoin(player: ServerPlayer) {
        val party = player.party()

        party.forEach { pokemon ->
            pokemon.features.removeIf { it.name == "terastal_active"}
            pokemon.features.removeIf { it.name.contains("tera_")}

            if (pokemon.species.name.equals("Terapagos")) {
                if (pokemon.teraType != TeraTypes.STELLAR) {
                    pokemon.teraType = TeraTypes.STELLAR
                }

                StringSpeciesFeature("tera_form", "normal").apply(pokemon)
                pokemon.updateAspects()
            }

            if (pokemon.species.name.equals("Necrozma")) {
                val necroForm = pokemon.persistentData.getString("necro_fusion")
                if (!necroForm.isNullOrBlank()) {
                    val necroFeature = StringSpeciesFeature("prism_fusion", necroForm)
                    necroFeature.apply(pokemon)
                    pokemon.updateAspects()
                }
            }
            pokemon.removeBattleFeature()
        }
    }
}