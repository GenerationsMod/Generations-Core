package generations.gg.generations.core.generationscore;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.actor.ActorType;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem.BattleData;
import kotlin.Unit;

import java.util.ArrayList;

class CobblemonEvents {
    public static void init() {
        com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.HIGH, event -> {
            var data = new ArrayList<BattleData>();

            event.getLosers().forEach(actor -> {
                var isNpc = actor.getType() == ActorType.NPC;
                actor.getPokemonList().stream().map(i -> new BattleData(isNpc, i.getEffectedPokemon(), "")).forEach(data::add);
            });

            event.getLosers().stream().filter(PlayerBattleActor.class::isInstance).map(PlayerBattleActor.class::cast).forEach(actor -> {
                if (actor.getEntity() != null) actor.getEntity().getInventory().items.stream().filter(a -> a.getItem() instanceof PostBattleUpdatingItem).forEach(stack -> ((PostBattleUpdatingItem) stack.getItem()).afterBattle(actor, stack, data));

                actor.getPokemonList().stream().map(BattlePokemon::getOriginalPokemon).map(Pokemon::heldItem).filter(it -> !it.isEmpty()).filter(it -> it.getItem() instanceof PostBattleUpdatingItem).forEach(held -> ((PostBattleUpdatingItem) held.getItem()).afterBattle(actor, held, true, data));
            });

            return Unit.INSTANCE;
        });
    }
    }
