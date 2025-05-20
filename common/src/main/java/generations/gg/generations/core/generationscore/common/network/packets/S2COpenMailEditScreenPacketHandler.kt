package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailEditScreen
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen.WrittenMailAccess
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import net.minecraft.client.Minecraft

object S2COpenMailEditScreenPacketHandler : ClientNetworkPacketHandler<S2COpenMailEditScreenPacket> {
    override fun handle(packet: S2COpenMailEditScreenPacket, client: Minecraft) {
        val itemStack = client.player!!.getItemInHand(packet.hand)
        if (itemStack.`is`(GenerationsItemTags.CLOSED_POKEMAIL))
            client.setScreen(MailViewScreen(WrittenMailAccess(itemStack)))
        else if (itemStack.`is`(GenerationsItemTags.POKEMAIL))
            client.setScreen(MailEditScreen(client.player!!, itemStack, packet.hand))
    }
}
