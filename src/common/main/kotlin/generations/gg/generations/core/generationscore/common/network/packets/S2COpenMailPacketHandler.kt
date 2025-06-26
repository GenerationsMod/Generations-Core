package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler

import generations.gg.generations.core.generationscore.common.client.screen.mails.MailEditScreen
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import net.minecraft.client.Minecraft

object S2COpenMailPacketHandler : ClientNetworkPacketHandler<S2COpenMailPacket> {
    override fun handle(packet: S2COpenMailPacket, minecraft: Minecraft) {
        val itemStack = minecraft.player!!.getItemInHand(packet.hand)
        if (itemStack.`is`(GenerationsItemTags.CLOSED_POKEMAIL)) minecraft.setScreen(
            MailViewScreen(
                MailViewScreen.WrittenMailAccess(itemStack)
            )
        ) else if (itemStack.`is`(GenerationsItemTags.POKEMAIL)) minecraft.setScreen(
            MailEditScreen(
                minecraft.player!!,
                itemStack,
                packet.hand
            )
        )
    }
}