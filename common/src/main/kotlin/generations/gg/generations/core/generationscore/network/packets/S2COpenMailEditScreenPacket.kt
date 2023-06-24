package generations.gg.generations.core.generationscore.network.packets

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.client.screen.mails.MailEditScreen
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen.WrittenMailAccess
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.InteractionHand

data class S2COpenMailEditScreenPacket(@JvmField val hand: InteractionHand): NetworkPacket<S2COpenMailEditScreenPacket> {
    override val id = ID
    override fun encode(buf: FriendlyByteBuf) {
        buf.writeEnum(hand)
    }

    companion object {
        val ID = id("open_mail_edit_screen")
        fun decode(buf: FriendlyByteBuf) = S2COpenMailEditScreenPacket(buf.readEnum(InteractionHand::class.java))
    }

    object Handler: ClientNetworkPacketHandler<S2COpenMailEditScreenPacket> {
        override fun handle(packet: S2COpenMailEditScreenPacket, client: Minecraft) {
            client.execute {
                val itemStack = client.player!!.getItemInHand(packet.hand)
                if (itemStack.`is`(GenerationsItemTags.CLOSED_POKEMAIL)) client.setScreen(
                    MailViewScreen(
                        WrittenMailAccess(itemStack)
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