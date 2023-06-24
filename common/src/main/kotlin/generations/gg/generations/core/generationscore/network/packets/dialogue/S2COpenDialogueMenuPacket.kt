package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf

class S2COpenDialogueMenuPacket(private val closable: Boolean) : NetworkPacket<S2COpenDialogueMenuPacket> {
    override val id = ID

    override fun encode(buf: FriendlyByteBuf) {
        buf.writeBoolean(closable)
    }

    companion object {
        val ID = GenerationsCore.id("open_dialogue_menu")
        fun decode(buf: FriendlyByteBuf) = S2COpenDialogueMenuPacket(buf.readBoolean())
    }

    class Handler: ClientNetworkPacketHandler<S2COpenDialogueMenuPacket> {
        override fun handle(packet: S2COpenDialogueMenuPacket, client: Minecraft) {
            client.setScreen(DialogueScreen(packet.closable));
        }
    }
}
