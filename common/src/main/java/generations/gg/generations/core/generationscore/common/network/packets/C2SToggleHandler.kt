package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.world.level.block.entities.Toggleable
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

object C2SToggleHandler : ServerNetworkPacketHandler<C2STogglePacket> {
    override fun handle(packet: C2STogglePacket, minecraftServer: MinecraftServer, player: ServerPlayer) {
        var toggleable: Toggleable? = null

        if (packet.pos != null && player.level().getBlockEntity(packet.pos) is Toggleable) {
            toggleable = player.level().getBlockEntity(packet.pos) as Toggleable
        } else if (player.containerMenu is Toggleable) {
            toggleable = player.containerMenu as Toggleable
        }

        if (toggleable != null) toggleable.isToggled = !toggleable.isToggled
    }
}
