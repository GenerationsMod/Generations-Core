package generations.gg.generations.core.generationscore;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.actor.ActorType;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import generations.gg.generations.core.generationscore.api.player.Caught;
import generations.gg.generations.core.generationscore.config.Key;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem.BattleData;
import kotlin.Unit;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

class CobblemonEvents {
    public static void init() {
        com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.HIGH, event -> {
            var data = new ArrayList<BattleData>();

            event.getLosers().forEach(actor1 -> {
                var isNpc = actor1.getType() == ActorType.NPC;
                actor1.getPokemonList().stream().map(i -> new BattleData(isNpc, i.getEffectedPokemon(), "")).forEach(data::add);
            });

            event.getWinners().stream().filter(battleActor -> battleActor instanceof PlayerBattleActor).map(battleActor -> (PlayerBattleActor) battleActor).forEach(actor -> {
                if (actor.getEntity() != null) {
                    actor.getEntity().getInventory().items.stream().filter(a -> a.getItem() instanceof PostBattleUpdatingItem).forEach(a -> ((PostBattleUpdatingItem) a.getItem()).afterBattle(actor, a, data));
                }
                actor.getPokemonList().stream().map(BattlePokemon::getOriginalPokemon).forEach(originalPokemon -> {
                    ItemStack it = originalPokemon.heldItem();
                    if (!it.isEmpty()) {
                        if (it.getItem() instanceof PostBattleUpdatingItem) {
                            ((PostBattleUpdatingItem) it.getItem()).afterBattle(actor, it, true, data);
                            originalPokemon.swapHeldItem(it, false);
                        }
                    }
                });
            });

            return Unit.INSTANCE;
        });

        com.cobblemon.mod.common.api.events.CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.HIGH, event -> {
            var key = Key.fromPokemon(event.getPokemon());

            Caught.get(event.getPlayer()).accumulate(key);
            return Unit.INSTANCE;
        });
    }
}
