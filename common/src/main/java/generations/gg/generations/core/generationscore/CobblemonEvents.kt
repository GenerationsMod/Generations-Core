package generations.gg.generations.core.generationscore;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.actor.ActorType;
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.util.subscribeOnServer
import generations.gg.generations.core.generationscore.api.player.Caught;
import generations.gg.generations.core.generationscore.config.Key;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem.BattleData;
import kotlin.Unit;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

class CobblemonEvents {
    companion object {
        @JvmStatic
        fun init() {
            BATTLE_VICTORY.subscribe(Priority.HIGH) { event ->
                val data = mutableListOf<BattleData>()

                event.losers.forEach { actor1 ->
                    val isNpc = actor1.type == ActorType.NPC;
                    actor1.pokemonList.stream()
                        .map { BattleData(isNpc, it.effectedPokemon, "") }.forEach(data::add);
                };

                event.winners.stream()
                    .filter { battleActor -> battleActor is PlayerBattleActor }
                    .map { battleActor -> battleActor as PlayerBattleActor }
                    .forEach { actor ->
                        actor.entity?.inventory?.items?.asSequence()
                            ?.filter { a -> a.item is PostBattleUpdatingItem }
                            ?.forEach { a -> (a.item as PostBattleUpdatingItem).afterBattle(actor, a, data) };

                        actor.pokemonList.asSequence().map { it.originalPokemon }.forEach { originalPokemon ->
                            val it: ItemStack = originalPokemon.heldItem();
                            if (!it.isEmpty) {
                                if (it.item is PostBattleUpdatingItem) {
                                    (it.item as PostBattleUpdatingItem).afterBattle(
                                        actor,
                                        it,
                                        true,
                                        data
                                    );
                                    originalPokemon.swapHeldItem(it, false);
                                }
                            }
                        }
                    }
            }

            com.cobblemon.mod.common.api.events.CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.HIGH) { event ->
                val key = Key.fromPokemon(event.pokemon);
                Caught.get(event.player).accumulate(key);
            }

            com.cobblemon.mod.common.api.events.CobblemonEvents.LOOT_DROPPED.subscribe(Priority.HIGHEST, {

            });
        }
    }
}
