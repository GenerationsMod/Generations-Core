package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.entities.Toggleable
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

object C2SToggleHandler : ServerNetworkPacketHandler<C2STogglePacket> {
    override fun handle(packet: C2STogglePacket, minecraftServer: MinecraftServer, player: ServerPlayer) {
        player.containerMenu.instanceOrNull<Toggleable>()?.run {
            isToggled = !isToggled
        }
    }
}
