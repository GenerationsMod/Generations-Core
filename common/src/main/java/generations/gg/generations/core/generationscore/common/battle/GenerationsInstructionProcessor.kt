package generations.gg.generations.core.generationscore.common.battle

import com.cobblemon.mod.common.Cobblemon
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
import com.cobblemon.mod.common.api.scheduling.afterOnServer
import com.cobblemon.mod.common.api.tags.CobblemonItemTags
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.cobblemon.mod.common.battles.actor.PokemonBattleActor
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon
import com.cobblemon.mod.common.entity.npc.NPCBattleActor
import com.cobblemon.mod.common.net.messages.client.battle.BattleHealthChangePacket
import com.cobblemon.mod.common.net.messages.client.battle.BattleInitializePacket
import com.cobblemon.mod.common.net.messages.client.battle.BattleTransformPokemonPacket
import com.cobblemon.mod.common.net.messages.client.pokemon.update.BenchedMovesUpdatePacket
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.getPlayer
import com.cobblemon.mod.common.util.server
import generations.gg.generations.core.generationscore.common.battle.ExpAllCalculator.calculateMultiplier
import generations.gg.generations.core.generationscore.common.battle.ExpAllCalculator.hasExpAll
import generations.gg.generations.core.generationscore.common.util.removeCosmeticFeature
import generations.gg.generations.core.generationscore.common.util.replaceMove
import java.util.UUID

import net.minecraft.server.level.ServerPlayer
import kotlin.compareTo
import kotlin.math.PI

object GenerationsInstructionProcessor {
    val capturedList = mutableListOf<UUID>()

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
                battlePokemon.effectedPokemon.applyBattleFeature(it)
                val active = battle.activePokemon.find {
                    it.battlePokemon?.uuid == battlePokemon.uuid || it.battlePokemon?.effectedPokemon?.uuid == battlePokemon.uuid
                }

                val updated = battlePokemon
                val pnx = active?.getPNX()
                if (pnx != null) {
                    (battle.playerUUIDs + battle.spectators).forEach { viewer ->
                        val isAlly = battle.isAllied(viewer, battlePokemon.actor)
                        val packet = BattleTransformPokemonPacket(pnx, updated, isAlly)

                        getPlayerFromUUID(viewer)
                            ?.let { CobblemonNetwork.sendPacketToPlayer(it, packet) }
                    }
                }
            }
        }
    }

    @JvmStatic
    fun processTerastallization(terastallizationEvent: TerastallizationEvent) {
        val battle = terastallizationEvent.battle
        val pokemon = terastallizationEvent.pokemon
        val teraCheck = FlagSpeciesFeature("terastal_active", true)
        val teraType = StringSpeciesFeature("tera_type", "tera_${pokemon.originalPokemon.teraType.id.path}")
        teraCheck.apply(pokemon.effectedPokemon)

        val active = battle.activePokemon.find {
            it.battlePokemon?.uuid == pokemon.uuid || it.battlePokemon?.effectedPokemon?.uuid == pokemon.uuid
        }
        val pnx = active?.getPNX()
        val updated = pokemon
        if (pnx != null) {
            (battle.playerUUIDs + battle.spectators).forEach { viewer ->
                val isAlly = battle.isAllied(viewer, pokemon.actor)
                val packet = BattleTransformPokemonPacket(pnx, updated, isAlly)

                getPlayerFromUUID(viewer)?.let { CobblemonNetwork.sendPacketToPlayer(it, packet) }
            }

            afterOnServer(2.5f) {
                teraType.apply(pokemon.effectedPokemon)
                (battle.playerUUIDs + battle.spectators).forEach { viewer ->
                    val isAlly = battle.isAllied(viewer, pokemon.actor)
                    val packet = BattleTransformPokemonPacket(pnx, updated, isAlly)

                    getPlayerFromUUID(viewer)?.let { CobblemonNetwork.sendPacketToPlayer(it, packet) }
                }
            }

        }

        battle.dispatchWaitingToFront(2.5f) { Unit }
    }

    @JvmStatic
    fun preBattleChanges(battleStartedPreEvent: BattleStartedPreEvent) {
        val battle = battleStartedPreEvent.battle
        for (actor in battle.actors) {
            for (battlePokemon in actor.pokemonList) {
                battlePokemon.originalPokemon.removeCosmeticFeature()
                if (battlePokemon.originalPokemon.species.name == "Zacian" || battlePokemon.originalPokemon.species.name == "Zamazenta") {
                    val hasBehemoth = battlePokemon.moveSet.any { it.template.name.contains("behemoth") }
                    if (!hasBehemoth) {
                        doggoMoveChanger(battlePokemon)
                    }
                }
                if (battlePokemon.originalPokemon.species.name == "Xerneas") {
                    val feature = FlagSpeciesFeature("active", true)
                    battlePokemon.originalPokemon.applyBattleFeature(feature)
                }
            }

            if (actor is NPCBattleActor) {
                actor.pokemonList.forEach { battlePokemon ->
                    battlePokemon.originalPokemon.heal()
                    battlePokemon.effectedPokemon.heal()
                }
            }
        }
    }

    @JvmStatic
    fun processBattleEnd(battle: PokemonBattle) {
        battle.actors.forEach { actor ->

            grantExp(battle, actor)

            if (!actor.getPlayerUUIDs().iterator().hasNext()) return@forEach
            actor.pokemonList.forEach { battlePokemon ->
                val tempAbility = battlePokemon.originalPokemon.ability
                val data = battlePokemon.effectedPokemon.persistentData

                battlePokemon.effectedPokemon.features.removeIf { it.name == "tera_type" }
                battlePokemon.effectedPokemon.features.removeIf { it.name == "terastal_active"}

                val name = if(data.contains("form_name")) data.getString("form_name") else ""
                battlePokemon.effectedPokemon.removeBattleFeature()

                if (battlePokemon.effectedPokemon.species.name.equals("Terapagos")) {
                    val pokemon = battlePokemon.effectedPokemon
                    StringSpeciesFeature("tera_form", "normal").apply(battlePokemon.effectedPokemon)
                    pokemon.updateAspects()
                }

                if (battlePokemon.effectedPokemon.species.name.equals("Necrozma")) {
                    val necroForm = battlePokemon.originalPokemon.persistentData.getString("necro_fusion")
                    if (!necroForm.isNullOrBlank()) {
                        val necroFeature = StringSpeciesFeature("prism_fusion", necroForm)
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

fun Pokemon.removeBattleFeature() {
    val data = this.persistentData

    if (data.contains("terastal")) {
        val name = data.getString("terastal")
        features.removeIf {it.name == name}
        data.remove("terastal")
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

fun grantExp(battle: PokemonBattle, actor: BattleActor) {
    val targetPokemon = actor.pokemonList
    val faintedOnly = targetPokemon.filter { it.health <= 0 }
    val oppositeSide = if (battle.side1.actors.contains(actor)) battle.side2 else battle.side1

    if (GenerationsInstructionProcessor.capturedList.contains(battle.battleId)) {
        if (actor is PlayerBattleActor) return

        oppositeSide.actors.forEach { opponent ->
            val player = opponent.uuid.getPlayer()

            if (player != null) {
                if (player.hasExpAll()) {
                    grantExpAll(opponent, targetPokemon, true)
                } else {
                    grantExpCapture(opponent, targetPokemon, true)
                }
            }
        }

        GenerationsInstructionProcessor.capturedList.remove(battle.battleId)
        return
    }

    if (faintedOnly.isEmpty()) return

    oppositeSide.actors.forEach { opponent ->
        val player = opponent.uuid.getPlayer()

        if (player != null && player.hasExpAll()) {
            grantExpAll(opponent, faintedOnly, true)
        }
    }
}



fun grantExpAll(opponent: BattleActor, faintedPokemonList: List<BattlePokemon>, conditionsMet: Boolean) {
    val opponentNonFaintedPokemonList = opponent.pokemonList.filter {it.health > 0}

    faintedPokemonList.forEach { faintedPokemon ->
        for (opponentPokemon in opponentNonFaintedPokemonList) {
            val multiplier = opponentPokemon.calculateMultiplier()
            val facedFainted = opponentPokemon.facedOpponents.contains(faintedPokemon)
            val experience = Cobblemon.experienceCalculator.calculate(opponentPokemon, faintedPokemon, multiplier)
            val grantedEvs = Cobblemon.evYieldCalculator.calculate(opponentPokemon, faintedPokemon)

            if (experience > 0 && conditionsMet) {
                opponent.awardExperience(opponentPokemon, experience)
                if (!facedFainted) {
                    grantedEvs.forEach(opponentPokemon.effectedPokemon.evs::add)
                }
            }
        }
    }
}

fun grantExpCapture(opponent: BattleActor, caughtPokemon: List<BattlePokemon>, conditionsMet: Boolean) {
    val opponentNonFaintedPokemonList = opponent.pokemonList.filter {it.health > 0}

    for (opponentPokemon in opponentNonFaintedPokemonList) {
        val facedFainted = opponentPokemon.facedOpponents.contains(caughtPokemon.first())
        val pokemon = opponentPokemon.effectedPokemon
        val multiplier = when {
            !facedFainted && pokemon.heldItem().`is`(CobblemonItemTags.EXPERIENCE_SHARE) -> Cobblemon.config.experienceShareMultiplier
            facedFainted -> 1.0
            else -> continue
        }

        val experience = Cobblemon.experienceCalculator.calculate(opponentPokemon, caughtPokemon.first(), multiplier)

        if (experience > 0 && conditionsMet) {
            opponent.awardExperience(opponentPokemon, experience)
        }

        Cobblemon.evYieldCalculator.calculate(opponentPokemon, caughtPokemon.first()).forEach { (stat, amount) ->
            pokemon.evs.add(stat, amount)
        }
    }
}

private fun Pokemon.applyBattleFeature(feature: SpeciesFeature) {
    if (feature.name.equals("terastal_active")) {
        this.persistentData.putString("terastal_active", feature.name)
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

    val speciesName = battlePokemon.originalPokemon.species.name.lowercase()

    val hasIronHead = battlePokemon.moveSet.any {move -> move.template.name.equals("ironhead")}
    val hasBehemoth = battlePokemon.moveSet.any { move ->
        move.template.name.lowercase() in listOf("behemothblade", "behemothbash")
    }
    if (effectedPokemon.aspects.contains("crowned")) {
        val benchedMoves = effectedPokemon.benchedMoves

        for (benchedMove in benchedMoves) {
            if (benchedMove.moveTemplate.name == "behemothblade" || benchedMove.moveTemplate.name == "behemothbash") {
                benchedMoves.remove(benchedMove)
            }
        }

        if (hasIronHead) {
            when (speciesName) {
                "zacian" -> effectedPokemon.replaceMove("ironhead", "behemothblade")
                "zamazenta" -> effectedPokemon.replaceMove("ironhead", "behemothbash")
            }
        } else if (hasBehemoth) {
            when (speciesName) {
                "zacian" -> {
                    afterOnServer(seconds = 1.0F) {
                        effectedPokemon.replaceMove("behemothblade", "ironhead")
                    }
                }
                "zamazenta" -> {
                    afterOnServer(seconds = 1.0F) {
                        effectedPokemon.replaceMove("behemothbash", "ironhead")
                    }
                }
            }
        }
    }
}


