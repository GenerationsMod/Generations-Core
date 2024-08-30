package generations.gg.generations.core.generationscore.common.network.packets

import generations.gg.generations.core.generationscore.common.client.screen.mails.MailEditScreen
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen.WrittenMailAccess
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import net.minecraft.client.Minecraft

object S2COpenMailEditScreenPacketHandler : ClientNetworkPacketHandler<S2COpenMailEditScreenPacket> {
    override fun handle(packet: S2COpenMailEditScreenPacket, minecraft: Minecraft) {
        val itemStack = Minecraft.getInstance().player!!.getItemInHand(packet.hand)
        if (itemStack.`is`(GenerationsItemTags.CLOSED_POKEMAIL)) Minecraft.getInstance()
            .setScreen(MailViewScreen(WrittenMailAccess(itemStack))) else if (itemStack.`is`(GenerationsItemTags.POKEMAIL)) Minecraft.getInstance()
            .setScreen(
                MailEditScreen(
                    Minecraft.getInstance().player, itemStack,
                    packet.hand
                )
            )
    }
}
