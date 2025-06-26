package generations.gg.generations.core.generationscore.common.api.events.general

import com.cobblemon.mod.common.api.reactive.EventObservable
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack

object CameraEvents {
    @JvmField val MODIFY_PHOTO: EventObservable<ModifyPhoto> = EventObservable()

    class ModifyPhoto(val player: ServerPlayer, val level: ServerLevel, var photo: ItemStack)
}
