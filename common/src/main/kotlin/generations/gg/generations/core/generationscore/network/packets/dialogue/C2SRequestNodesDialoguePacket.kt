package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

class C2SRequestNodesDialoguePacket(): GenerationsNetworkPacket<C2SRequestNodesDialoguePacket> {
    override val id = ID;

    override fun encode(buf: FriendlyByteBuf) {}

    companion object {
        val ID: ResourceLocation = id("request_nodes_dialogue")
        fun decode(buffer: FriendlyByteBuf) = C2SRequestNodesDialoguePacket()
    }

    class Handler: ServerNetworkPacketHandler<C2SRequestNodesDialoguePacket> {
        override fun handle(packet: C2SRequestNodesDialoguePacket, server: MinecraftServer, player: ServerPlayer) {
            if (DialogueManager.DIALOGUE_MAP.containsKey(player)) DialogueManager.DIALOGUE_MAP[player]!!.nextNode()
        }
    }
}
