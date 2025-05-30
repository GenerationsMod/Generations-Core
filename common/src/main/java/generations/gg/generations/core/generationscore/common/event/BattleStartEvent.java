package generations.gg.generations.core.generationscore.common.event;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.events.battles.BattleStartedPostEvent;
import com.cobblemon.mod.common.api.events.battles.BattleStartedPreEvent;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor;
import kotlin.Unit;
import net.minecraft.server.level.ServerPlayer;

public class BattleStartEvent {
    public static Unit resetAspects(BattleStartedPreEvent battleEvent) {
        for (ServerPlayer player : battleEvent.getBattle().getPlayers()) {
            PlayerPartyStore playerParty = Cobblemon.INSTANCE.getStorage().getParty(player);
            for (Pokemon pokemon : playerParty) {
                if (pokemon.getSpecies().getName().equals("Ogerpon")) {
                    System.out.println("pass name check");
                    new FlagSpeciesFeature("embody-aspect", false).apply(pokemon);
                }
            }
        }
        return Unit.INSTANCE;
    }
}
