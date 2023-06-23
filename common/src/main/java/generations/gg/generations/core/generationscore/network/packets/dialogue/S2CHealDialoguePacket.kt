package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf

// TODO delay being able to go to the next node through this packet
class S2CHealDialoguePacket(private val text: List<String>, private val useNextArrow: Boolean) : NetworkPacket<S2CHealDialoguePacket> {
    override val id = ID

    override fun encode(buf: FriendlyByteBuf) {
        buf.writeCollection(text) { obj, value -> obj.writeUtf(value) }
        buf.writeBoolean(useNextArrow)
    }

    companion object {
        val ID = GenerationsCore.id("say_dialogue")

        fun decode(buf : FriendlyByteBuf) = S2CHealDialoguePacket(buf.readList { obj: FriendlyByteBuf -> obj.readUtf() }, buf.readBoolean())
    }

    class Handler: ClientNetworkPacketHandler<S2CHealDialoguePacket> {
        override fun handle(packet: S2CHealDialoguePacket, client: Minecraft) {
            val dialogueScreen = Minecraft.getInstance().screen;
            if (dialogueScreen is DialogueScreen) dialogueScreen.activeInfo = DialogueScreen.SayActiveInfo(packet.text, packet.useNextArrow)
        }
    }
}
