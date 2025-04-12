package generations.gg.generations.core.generationscore.common.battle

import com.cobblemon.mod.common.api.abilities.Ability
import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage
import com.cobblemon.mod.common.api.battles.model.PokemonBattle
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.SynchronizedSpeciesFeature
import com.cobblemon.mod.common.battles.ShowdownActionRequest
import com.cobblemon.mod.common.battles.ShowdownMoveset
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.cobblemonResource
import com.cobblemon.mod.common.util.hasKeyItem
import com.mojang.datafixers.util.Unit
import generations.gg.generations.core.generationscore.common.util.getProviderOrNull

object GenerationsInstructionProcessor {
    private var originalAbility: Ability? = null

    @JvmStatic
    fun processDetailsChange(battle: PokemonBattle, message: BattleMessage) {
        val s1 = message.argumentAt(1) ?: return
        val s2 = s1.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (s2.isEmpty()) return
        val s3 = s2[0].lowercase().split("-".toRegex(), 2).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (s3.size < 2) return

        val battlePokemon = message.battlePokemon(0, battle) ?: return

        val name = s3[1]

        originalAbility = battlePokemon.originalPokemon.ability

        var pair: Pair<String, Any> = when(name) {
            "mega" -> "mega" to true
            "mega-x" -> "mega_x" to true
            "mega-y" -> "mega_y" to true
            "sunshine" -> "sunny" to true
            "school" -> "schooling" to true
            "primal" -> "primal" to true
            "ash" -> "ash" to true
            "wellspring-tera", "hearthflame-tera", "cornerstone-tera", "teal-tera" -> "embody-aspect" to true

            else -> name to true
        } ?: let {
            battlePokemon.originalPokemon.removeBattleFeature()
            battlePokemon.effectedPokemon.removeBattleFeature()
            return
        }

        pair.let {
            when (it.second) {
                is String -> it.let { StringSpeciesFeature(it.first, it.second as String) }
                is Boolean -> it.let { FlagSpeciesFeature(it.first, it.second as Boolean) }
                else -> null
            }
        }?.let {
            battle.dispatchGo {
                battlePokemon.entity
                battlePokemon.originalPokemon.applyBattleFeature(it)
                battlePokemon.effectedPokemon.applyBattleFeature(it)
            }
        }


//            ?.let {
//            battle.dispatchGo {
//                FlagSpeciesFeature(it, true).also {
//                    it.apply(battlePokemon.originalPokemon)
//                    it.apply(battlePokemon.effectedPokemon)
//                }
//            }
//        }
    }
    
    @JvmStatic
    fun processBattleEnd(battle: PokemonBattle) {
        battle.actors.forEach { actor ->
            if (!actor.getPlayerUUIDs().iterator().hasNext()) return@forEach
            actor.pokemonList.forEach { battlePokemon ->
                val tempAbility = battlePokemon.originalPokemon.ability
                val data = battlePokemon.effectedPokemon.persistentData
                val name = if(data.contains("form_name")) data.getString("form_name") else ""
                battlePokemon.originalPokemon.removeBattleFeature()
                battlePokemon.effectedPokemon.removeBattleFeature()

                battlePokemon.originalPokemon.restoreAbility(tempAbility, originalAbility, name)

//                sequenceOf("mega", "mega_x", "mega_y", "primal", "stellar", "terastal", "hero", "hangry", "meteor", "blade", "pirouette", "sunny", "schooling", "ash", "busted").forEach { name ->
//                    battlePokemon.effectedPokemon.features.removeIf { it.name == name }
//                    battlePokemon.originalPokemon.features.removeIf { it.name == name }
//                }
//
//                battlePokemon.effectedPokemon.updateAspects()
            }
        }
    }


}

private fun Pokemon.removeBattleFeature() {
    val data = this.persistentData
    val name = if(data.contains("form_name")) data.getString("form_name").also { data.remove("form_name") } else return
    val value = if(data.contains("form_value")) data.getString("form_value").also { data.remove("form_value") } else null

    if(value == null) {
        features.removeIf { it.name == name }
    } else {
        val feature = features.firstOrNull { it.name == name } ?: return

        if(feature is StringSpeciesFeature) {
            feature.value = value
        }
    }

    updateAspects()
}

private fun Pokemon.applyBattleFeature(feature: SpeciesFeature) {
    this.persistentData.putString("form_name", feature.name)
    if(feature is StringSpeciesFeature) {
        this.persistentData.putString("form_value", feature.value)
        feature.apply(this)
    } else {
        (feature as FlagSpeciesFeature).apply(this)
    }
}

private fun Pokemon.restoreAbility(tempAbility: Ability, originalAbility: Ability?, name: String) {
    if (name == "mega" || name == "mega_x" || name == "mega_y") {
        if (tempAbility != originalAbility) {
            originalAbility?.let { ability ->
                this.updateAbility(ability)
            } ?: run {
                println("Original Ability is null")
            }
        }
    }
}