package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager
import generations.gg.generations.core.generationscore.world.dialogue.nodes.ResponseTakingNode
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer


class C2SRespondDialoguePacket(private val stringResponse: String): NetworkPacket<C2SRespondDialoguePacket> {
    override val id: ResourceLocation = ID;

    override fun encode(buf: FriendlyByteBuf) {
        buf.writeUtf(stringResponse)
    }

    companion object {
        val ID: ResourceLocation = id("respond_dialogue")
        fun decode(buf: FriendlyByteBuf) = C2SRespondDialoguePacket(buf.readUtf())
    }

    class Handler: ServerNetworkPacketHandler<C2SRespondDialoguePacket> {
        override fun handle(packet: C2SRespondDialoguePacket, server: MinecraftServer, player: ServerPlayer) {
            val dialoguePlayer = DialogueManager.DIALOGUE_MAP[player]
            val node = dialoguePlayer?.currentNode;

            if (dialoguePlayer != null && node is ResponseTakingNode) {
                node.clientResponse(packet.stringResponse)
            }
        }
    }
}
