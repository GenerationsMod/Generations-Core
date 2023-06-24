package generations.gg.generations.core.generationscore.network.packets

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.client.screen.mails.MailEditScreen
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen.WrittenMailAccess
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand

data class S2COpenMailPacket(val hand: InteractionHand): NetworkPacket<S2COpenMailPacket> {
    override val id = ID;
    override fun encode(buf: FriendlyByteBuf) {
        buf.writeEnum(hand)
    }

    companion object {
        val ID = id("open_mail")
        fun decode(buf: FriendlyByteBuf) = S2COpenMailPacket(buf.readEnum(InteractionHand::class.java))
    }

    class Handler: ClientNetworkPacketHandler<S2COpenMailPacket> {
        override fun handle(packet: S2COpenMailPacket, client: Minecraft) {
            client.execute {
                val itemStack = client.player!!.getItemInHand(packet.hand)
                if (itemStack.`is`(GenerationsItemTags.CLOSED_POKEMAIL)) client.setScreen(
                    MailViewScreen(
                        WrittenMailAccess(
                            itemStack
                        )
                    )
                ) else if (itemStack.`is`(GenerationsItemTags.POKEMAIL)) client.setScreen(
                    MailEditScreen(
                        client.player,
                        itemStack,
                        packet.hand
                    )
                )
            }
        }
    }
}