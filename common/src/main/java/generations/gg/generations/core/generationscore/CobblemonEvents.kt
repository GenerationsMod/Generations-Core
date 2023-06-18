package generations.gg.generations.core.generationscore

import com.cobblemon.mod.common.api.battles.model.actor.ActorType
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.cobblemon.mod.common.pokemon.Pokemon
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem.BattleData

object CobblemonEvents {
    fun init() {
        BATTLE_VICTORY.subscribe {
            var data = mutableListOf<BattleData>()

            it.losers.forEach {
                var isNpc = it.type == ActorType.NPC

                it.pokemonList.map {
                    BattleData(isNpc, it.effectedPokemon, "")
                }.forEach { data.add(it) }
            }

            it.winners.filterIsInstance<PlayerBattleActor>().forEach { it ->
                it.entity?.let { player ->
                    player.inventory.items.filter { it.`is` { it.value() is PostBattleUpdatingItem } }.forEach {
                        (it.item as PostBattleUpdatingItem).afterBattle(player, it, data)
                    }

                    it.pokemonList.map { it.originalPokemon }.map { it.heldItem() }.filter { it.isEmpty.not() }.filter { it.`is` { it is PostBattleUpdatingItem } }.forEach { held ->
                        (held.item as PostBattleUpdatingItem).afterBattle(player, held, true, data)
                    }
                }

            }
        }
    }
}
