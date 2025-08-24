package generations.gg.generations.core.generationscore.common.network.packets

import net.minecraft.client.Minecraft
import net.minecraft.server.level.ServerPlayer

object TeraAnimationPacketHandler {
    fun handle(packet: TeraAnimationPacket) {
        val player = Minecraft.getInstance().player ?: return

        if (packet.playAnimation) {
            // Trigger animation client-side
        } else {
            // Cancel/stop animation
        }
    }
}