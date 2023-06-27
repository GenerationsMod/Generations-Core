package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf

class S2CSayDialoguePacket: GenerationsNetworkPacket<S2CSayDialoguePacket> {
    private val text: List<String>
    private val useNextArrow: Boolean

    override val id = ID
    constructor(text: List<String>, useNextArrow: Boolean) {
        this.text = text
        this.useNextArrow = useNextArrow
    }

    constructor(buf: FriendlyByteBuf) {
        text = buf.readList { obj: FriendlyByteBuf -> obj.readUtf() }
        useNextArrow = buf.readBoolean()
    }

    override fun encode(buf: FriendlyByteBuf) {
        buf.writeCollection(text) { obj, value -> obj.writeUtf(value) }
        buf.writeBoolean(useNextArrow)
    }

    companion object {
        val ID = id("say_dialogue")

        fun decode(buf : FriendlyByteBuf) = S2CSayDialoguePacket(buf.readList { obj: FriendlyByteBuf -> obj.readUtf() }, buf.readBoolean())
    }

    class Handler: ClientNetworkPacketHandler<S2CSayDialoguePacket> {
        override fun handle(packet: S2CSayDialoguePacket, client: Minecraft) {
            val dialogueScreen = Minecraft.getInstance().screen;
            if (dialogueScreen is DialogueScreen) dialogueScreen.activeInfo = DialogueScreen.SayActiveInfo(packet.text, packet.useNextArrow)
        }
    }
}
