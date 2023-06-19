package generations.gg.generations.core.generationscore

import com.cobblemon.mod.common.api.battles.model.actor.ActorType
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem.BattleData
class CobblemonEvents {
    companion object {
        @JvmStatic
        fun init() {
            BATTLE_VICTORY.subscribe {
                val data = mutableListOf<BattleData>()

                it.losers.forEach {
                    val isNpc = it.type == ActorType.NPC

                    it.pokemonList.map {
                        BattleData(isNpc, it.effectedPokemon, "")
                    }.forEach { data.add(it) }
                }

                it.winners.filterIsInstance<PlayerBattleActor>().forEach { actor ->

                    actor.entity?.let { player ->
                        player.inventory.items.filter { it.`is` { it.value() is PostBattleUpdatingItem } }.forEach {
                            (it.item as PostBattleUpdatingItem).afterBattle(actor, it, data)
                        }

                        actor.pokemonList.map { it.originalPokemon }.map { it.heldItem() }.filter { it.isEmpty.not() }
                            .filter { it.`is` { it is PostBattleUpdatingItem } }.forEach { held ->
                            (held.item as PostBattleUpdatingItem).afterBattle(actor, held, true, data)
                        }
                    }

                }
            }
        }
    }
}
