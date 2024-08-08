package generations.gg.generations.core.generationscore.common.api.events;

import com.cobblemon.mod.common.api.berry.Berry;
import dev.architectury.event.Event;
import dev.architectury.event.EventActor;
import dev.architectury.event.EventFactory;
import generations.gg.generations.core.generationscore.common.api.player.CurryDex;
import generations.gg.generations.core.generationscore.common.api.player.CurryDex;
import generations.gg.generations.core.generationscore.common.api.player.CurryDex;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class CurryEvents {
    public static final Event<EventActor<Cook>> COOK = EventFactory.createEventActorLoop();

    public static class Cook {

        private final CurryType mainIngredient;
        private final List<Berry> berries;
        private CurryData output;

        public Cook(CurryType mainIngredient, List<Berry> berries, CurryData output) {
            this.mainIngredient = mainIngredient;
            this.berries = berries;
            this.output = output;
        }

        public CurryType getMainIngredient() {
            return mainIngredient;
        }

        public List<Berry> getBerries() {
            return berries;
        }

        public CurryData getOutput() {
            return output;
        }

        public void setOutput(CurryData output) {
            this.output = output;
        }
    }


    public static final Event<EventActor<AddEntry>> ADD_ENTRY = EventFactory.createEventActorLoop();
    public static class AddEntry {
        private final ServerPlayer player;
        private final CurryData curry;
        private CurryDex.CurryDexEntry entry;

        public AddEntry(ServerPlayer player, CurryData curry, CurryDex.CurryDexEntry entry) {
            this.player = player;
            this.curry = curry;
            this.entry = entry;
        }

        public CurryDex.CurryDexEntry getEntry() {
            return entry;
        }

        public void setEntry(CurryDex.CurryDexEntry entry) {
            this.entry = entry;
        }

        public CurryData getCurry() {
            return curry;
        }

        public ServerPlayer getPlayer() {
            return player;
        }
    }
}
