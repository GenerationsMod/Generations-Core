package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.network.packets.C2SToggleCookingPotPacket
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

class C2SCloseDialoguePacket : NetworkPacket<C2SCloseDialoguePacket> {
    override val id = ID

    override fun encode(buf: FriendlyByteBuf) {}

    companion object {
        val ID = id("close_dialgoue")
        fun decode(buf: FriendlyByteBuf) = C2SCloseDialoguePacket()
    }

    class Handler: ServerNetworkPacketHandler<C2SCloseDialoguePacket> {
        override fun handle(packet: C2SCloseDialoguePacket, server: MinecraftServer, player: ServerPlayer) {
            if (DialogueManager.DIALOGUE_MAP.containsKey(player)) DialogueManager.DIALOGUE_MAP[player]!!.discard()
        }

    }
}
