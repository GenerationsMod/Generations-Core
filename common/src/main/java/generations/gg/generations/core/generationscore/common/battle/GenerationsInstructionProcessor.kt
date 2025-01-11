package generations.gg.generations.core.generationscore.common.battle

import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage
import com.cobblemon.mod.common.api.battles.model.PokemonBattle
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.mojang.datafixers.util.Unit
import java.util.stream.Stream

object GenerationsInstructionProcessor {
    @JvmStatic
    fun processDetailsChange(battle: PokemonBattle, message: BattleMessage) {
        val s1 = message.argumentAt(1) ?: return
        val s2 = s1.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (s2.isEmpty()) return
        val s3 = s2[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (s3.size < 2) return
        if (s3[1].equals("mega", ignoreCase = true)) {
            val battlePokemon = message.battlePokemon(0, battle) ?: return
            val megaStone = battlePokemon.heldItemManager.showdownId(battlePokemon) ?: return
            battle.dispatchGo {
                when {
                    megaStone.endsWith("x") -> "mega_x"
                    megaStone.endsWith("y") -> "mega_y"
                    else -> "mega"
                }.let { FlagSpeciesFeature(it, true) }.also {
                    it.apply(battlePokemon.originalPokemon)
                    it.apply(battlePokemon.effectedPokemon)
                }

                Unit.INSTANCE
            }
        }
    }

    @JvmStatic
    fun processBattleEnd(battle: PokemonBattle) {
        battle.actors.forEach { actor ->
            if (!actor.getPlayerUUIDs().iterator().hasNext()) return@forEach
            actor.pokemonList.forEach { battlePokemon ->
                sequenceOf("mega", "mega_x", "mega_y").map { FlagSpeciesFeature(it, false) }.forEach {
                    it.apply(battlePokemon.effectedPokemon)
                    it.apply(battlePokemon.originalPokemon)
                }
            }
        }
    }
}
