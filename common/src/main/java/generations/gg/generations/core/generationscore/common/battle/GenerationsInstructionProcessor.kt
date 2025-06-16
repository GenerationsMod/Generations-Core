package generations.gg.generations.core.generationscore.common.battle

import com.cobblemon.mod.common.CobblemonNetwork
import com.cobblemon.mod.common.api.abilities.Abilities
import com.cobblemon.mod.common.api.abilities.Ability
import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage
import com.cobblemon.mod.common.api.battles.model.PokemonBattle
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor
import com.cobblemon.mod.common.api.events.battles.BattleStartedPreEvent
import com.cobblemon.mod.common.api.events.battles.instruction.TerastallizationEvent
import com.cobblemon.mod.common.api.moves.Moves
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon
import com.cobblemon.mod.common.net.messages.client.battle.BattleInitializePacket
import com.cobblemon.mod.common.net.messages.client.battle.BattleTransformPokemonPacket
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.server
import generations.gg.generations.core.generationscore.common.util.replaceMove
import java.util.UUID

import net.minecraft.server.level.ServerPlayer

object GenerationsInstructionProcessor {

    @JvmStatic
    fun processFormeChangeInstruction(battle: PokemonBattle, message: BattleMessage) {
        val s1 = message.argumentAt(1) ?: return
        val s2 = s1.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (s2.isEmpty()) return
        val s3 = s2[0].lowercase().split("-".toRegex(), 2).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (s3.size < 2) return

        val battlePokemon = message.battlePokemon(0, battle) ?: return
        val effectedPokemon = battlePokemon.effectedPokemon

        val name = s3[1]

        val ability = battlePokemon.originalPokemon.ability.name
        battlePokemon.originalPokemon.persistentData.putString("original_ability", ability)

        if (effectedPokemon.form.name.equals("Dusk-Mane")) {
            battlePokemon.originalPokemon.persistentData.putString("necro_fusion", "dusk")
        } else if (effectedPokemon.form.name.equals("Dawn-Wings")) {
            battlePokemon.originalPokemon.persistentData.putString("necro_fusion", "dawn")
        }

        var pair: Pair<String, Any> = when(name) {
//            "ash" -> "ash" to true
            "mega" -> "mega" to true
            "mega-x" -> "mega_x" to true
            "mega-y" -> "mega_y" to true
            "primal" -> "primal" to true
            "stellar" -> "tera_form" to "stellar"
            "terastal" -> "tera_form" to "terastal"
            "ultra" -> "prism_fusion" to "ultra"
            "wellspring-tera", "hearthflame-tera", "cornerstone-tera", "teal-tera" -> "embody_aspect" to true
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
    }

    @JvmStatic
    fun processTerastallization(terastallizationEvent: TerastallizationEvent) {
        val battle = terastallizationEvent.battle
        val teraCheck = FlagSpeciesFeature("terastal_active", true)
        val pokemon = terastallizationEvent.pokemon
        terastallizationEvent.pokemon.effectedPokemon.applyBattleFeature(teraCheck)

        val active = battle.activePokemon.find {
            it.battlePokemon?.uuid == pokemon.uuid || it.battlePokemon?.effectedPokemon?.uuid == pokemon.uuid
        }
        val pnx = active?.getPNX()
        val updated = pokemon
        if (pnx != null) {
            (battle.playerUUIDs + battle.spectators).forEach { viewer ->
                val isAlly = battle.isAllied(viewer, pokemon.actor)
                println("UUID: " + viewer)
                val packet = BattleTransformPokemonPacket(pnx, updated, isAlly)
                getPlayerFromUUID(viewer)?.let { CobblemonNetwork.sendPacketToPlayer(it, packet) }
            }
        }

        battle.dispatchWaitingToFront(2.5f) { Unit }
    }

    @JvmStatic
    fun preBattleChanges(battleStartedPreEvent: BattleStartedPreEvent) {
        val battle = battleStartedPreEvent.battle
        for (actors in battle.actors) {
            for (battlePokemon in actors.pokemonList) {
                doggoMoveChanger(battlePokemon)
            }
        }
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

                if (battlePokemon.effectedPokemon.species.name.equals("Terapagos")) {
                    StringSpeciesFeature("tera_form", "normal").apply(battlePokemon.effectedPokemon)
                    StringSpeciesFeature("tera_form", "normal").apply(battlePokemon.originalPokemon)
                    battlePokemon.originalPokemon.updateAspects()
                }
                if (battlePokemon.effectedPokemon.species.name.equals("Necrozma")) {
                    val necroForm = battlePokemon.originalPokemon.persistentData.getString("necro_fusion")
                    if (!necroForm.isNullOrBlank()) {
                        val necroFeature = StringSpeciesFeature("prism_fusion", necroForm)
                        necroFeature.apply(battlePokemon.originalPokemon)
                        necroFeature.apply(battlePokemon.effectedPokemon)
                        battlePokemon.originalPokemon.updateAspects()
                    }
                }
                doggoMoveChanger(battlePokemon)
                battlePokemon.originalPokemon.restoreAbility(tempAbility)
            }
        }
    }
}

private fun Pokemon.removeBattleFeature() {
    val data = this.persistentData

    if (data.contains("terastal")) {
        val name = data.getString("terastal")
        features.removeIf {it.name == name}
        data.remove("terastal")
        data.remove("tera_type")
    }

    if (data.contains("form_name")) {
        val name = data.getString("form_name").also { data.remove("form_name") }
        val value = if(data.contains("form_value")) data.getString("form_value").also { data.remove("form_value") } else null

        if(value == null) {
            features.removeIf { it.name == name }
        } else {
            val feature = features.firstOrNull { it.name == name } ?: return

            if(feature is StringSpeciesFeature) {
                feature.value = value
            }
        }
    }

    updateAspects()
}

private fun Pokemon.applyBattleFeature(feature: SpeciesFeature) {
    if (feature.name.equals("terastal_active")) {
        this.persistentData.putString("terastal", feature.name)
        this.persistentData.putString("tera_type", this.teraType.id.path)
    } else {
        this.persistentData.putString("form_name", feature.name)
    }
    if(feature is StringSpeciesFeature) {
        this.persistentData.putString("form_value", feature.value)
        feature.apply(this)
    } else {
        (feature as FlagSpeciesFeature).apply(this)
    }
}

private fun Pokemon.restoreAbility(tempAbility: Ability) {
    val abilityId = this.persistentData.getString("original_ability")
    val template = Abilities.get(abilityId)

    if (template != null) {
        val ability = template.create()
        if (tempAbility.template != template) {
            this.updateAbility(ability)
        }
    }
}

private fun PokemonBattle.isAllied(uuid: UUID, actor: BattleActor?): Boolean {
    if (actor == null) return false
    return actors.any {
        it.getPlayerUUIDs().contains(uuid) && it.getSide() == actor.getSide()
    }
}

private fun getPlayerFromUUID(uuid: UUID): ServerPlayer? {
    return server()?.playerList?.getPlayer(uuid)
}

private fun doggoMoveChanger(battlePokemon: BattlePokemon) {
    val effectedPokemon = battlePokemon.effectedPokemon
    val ironHead = Moves.getByNameOrDummy("ironhead")
    val behemothBlade = Moves.getByNameOrDummy("behemothblade")
    val behemothBash = Moves.getByNameOrDummy("behemothbash")

    val speciesName = battlePokemon.originalPokemon.species.name.lowercase()

    val hasIronHead = battlePokemon.moveSet.any {move -> move.template.name.equals("ironhead")}
    val hasBehemoth = battlePokemon.moveSet.any { move ->
        move.template.name.lowercase() in listOf("behemothblade", "behemothbash")
    }
    if (effectedPokemon.aspects.contains("crowned")) {
        val benchedMoves = effectedPokemon.benchedMoves

        println("PRE-CLEAR")
        benchedMoves.forEach {
            println("benchedmove: " + it.moveTemplate.name)
        }

        benchedMoves.clear()

        println("POST-CLEAR")
        benchedMoves.forEach {
            println("benchedmove: " + it.moveTemplate.name)
        }
        if (hasIronHead) {
            when (speciesName) {
                "zacian" -> effectedPokemon.replaceMove("ironhead", "behemothblade")
                "zamazenta" -> effectedPokemon.replaceMove("ironhead", "behemothbash")
            }
        } else if (hasBehemoth) {
            when (speciesName) {
                "zacian" -> {
                    effectedPokemon.replaceMove("behemothblade", "ironhead")
                }
                "zamazenta" -> {
                    effectedPokemon.replaceMove("behemothbash", "ironhead")
                }
            }
        }
    }
}


