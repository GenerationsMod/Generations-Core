package generations.gg.generations.core.generationscore.common.network.packets

import generations.gg.generations.core.generationscore.common.client.screen.mails.MailEditScreen
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen.WrittenMailAccess
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import net.minecraft.client.Minecraft
import kotlin.math.min

object S2COpenMailEditScreenPacketHandler : ClientNetworkPacketHandler<S2COpenMailEditScreenPacket> {
    override fun handle(packet: S2COpenMailEditScreenPacket, minecraft: Minecraft) {
        val itemStack = minecraft.player!!.getItemInHand(packet.hand)
        if (itemStack.`is`(GenerationsItemTags.CLOSED_POKEMAIL))
            minecraft.setScreen(MailViewScreen(WrittenMailAccess(itemStack)))
        else if (itemStack.`is`(GenerationsItemTags.POKEMAIL))
            minecraft.setScreen(MailEditScreen(minecraft.player, itemStack, packet.hand))
    }
}
