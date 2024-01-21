package generations.gg.generations.core.generationscore.api.events.general;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.world.entity.Entity;

public class EntityEvents {
    public static final Event<Jump> JUMP = EventFactory.createLoop();

    public interface Jump {
        void jump(Entity entity);
    }
}
