package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class S2CCloseScreenPacket : GenerationsNetworkPacket<S2CCloseScreenPacket> {
    override val id = ID
    override fun encode(buf: FriendlyByteBuf) {}

    companion object {
        val ID = GenerationsCore.id("close_screen")
        fun decode(buf: FriendlyByteBuf) = S2CCloseScreenPacket()
    }

    class Handler: ClientNetworkPacketHandler<S2CCloseScreenPacket> {
        override fun handle(packet: S2CCloseScreenPacket, client: Minecraft) {
            client.setScreen(null)
        }
    }
}