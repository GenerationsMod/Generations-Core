package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen
import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen.ChooseActiveInfo
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf


class S2CChooseDialoguePacket(private val text: String, private val options: List<String>) : GenerationsNetworkPacket<S2CChooseDialoguePacket> {
    override val id = ID

    override fun encode(buf: FriendlyByteBuf) {
        buf.writeUtf(text)
        buf.writeCollection(options) { obj, value -> obj.writeUtf(value) }
    }

    companion object {
        val ID = GenerationsCore.id("choose_dialogue")
        fun decode(buf: FriendlyByteBuf) = S2CChooseDialoguePacket(buf.readUtf(), buf.readList { obj -> obj.readUtf() })
    }

    class Handler : ClientNetworkPacketHandler<S2CChooseDialoguePacket> {
        override fun handle(packet: S2CChooseDialoguePacket, client: Minecraft) {
            val dialogueScreen = client.screen;

            if (dialogueScreen is DialogueScreen) {
                dialogueScreen.activeInfo = ChooseActiveInfo(packet.text, packet.options)
                dialogueScreen.init(
                    client,
                    dialogueScreen.width,
                    dialogueScreen.height
                ) // reinitialize to get the options widget to pop up
            }
        }

    }
}
