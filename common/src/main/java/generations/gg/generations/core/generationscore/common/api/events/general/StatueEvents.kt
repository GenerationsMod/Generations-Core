package generations.gg.generations.core.generationscore.common.api.events.general

import com.cobblemon.mod.common.api.reactive.EventObservable

import net.minecraft.server.level.ServerPlayer

object StatueEvents {
    @JvmField var CAN_USE_CHISEL: EventObservable<CanUseChisel> = EventObservable()

    class CanUseChisel(val player: ServerPlayer, var canUse: Boolean)
}
