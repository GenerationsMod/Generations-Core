package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.client.screen.dialgoue.configure.ConfigureDialogueScreen
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph
import generations.gg.generations.core.generationscore.world.dialogue.Dialogues
import generations.gg.generations.core.generationscore.world.dialogue.nodes.SayNode
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation


class S2COpenDialogueEditorScreenPacket(private val location: ResourceLocation) : NetworkPacket<S2COpenDialogueEditorScreenPacket> {

    override val id = ID

    override fun encode(buf: FriendlyByteBuf) {
        buf.writeResourceLocation(location)
    }

    companion object {
        val ID = GenerationsCore.id("open_dialogue_editor_screen")
        fun decode(buf: FriendlyByteBuf) = S2COpenDialogueEditorScreenPacket(buf.readResourceLocation())
    }

    class Handler : ClientNetworkPacketHandler<S2COpenDialogueEditorScreenPacket> {
        override fun handle(packet: S2COpenDialogueEditorScreenPacket, client: Minecraft) {
            client.setScreen(
                ConfigureDialogueScreen(
                    Dialogues.instance().getOrElse(
                        packet.location, DialogueGraph(
                            SayNode(
                                listOf("Hi @p!"), null
                            )
                        )
                    )
                )
            )
        }
    }
}
