package generations.gg.generations.core.generationscore.common.api.events.general

import com.cobblemon.mod.common.api.reactive.EventObservable
import dev.architectury.event.Event
import dev.architectury.event.EventFactory
import net.minecraft.world.entity.Entity

object EntityEvents {
    @JvmStatic fun jump(entity: Entity) = JUMP.post(Jump(entity))

    @JvmField val JUMP: EventObservable<Jump> = EventObservable()

    class Jump(val entity: Entity)
}
