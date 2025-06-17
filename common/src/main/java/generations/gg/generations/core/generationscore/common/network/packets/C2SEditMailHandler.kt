package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.util.extensions.asValue
import generations.gg.generations.core.generationscore.common.world.item.MailItem
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import generations.gg.generations.core.generationscore.common.world.item.components.MailContent
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Inventory
import java.util.*

//TODO: Fix data nonsense
object C2SEditMailHandler : ServerNetworkPacketHandler<C2SEditMailPacket> {
    override fun handle(packet: C2SEditMailPacket, server: MinecraftServer, player: ServerPlayer) {
        server.execute {
            handleEditMail(
                player,
                packet.slot,
                packet.contents,
                packet.title
            )
        }
    }

    fun handleEditMail(sender: ServerPlayer, slot: Int, contents: String, title: Optional<String>) {
        if (Inventory.isHotbarSlot(slot) || slot == 40) {
            title.ifPresentOrElse(
                { s: String -> sealMail(sender, slot, contents, s) },
                {
                    updateMailContents(
                        sender,
                        slot,
                        contents
                    )
                })
        }
    }

    private fun updateMailContents(sender: ServerPlayer, slot: Int, contents: String) {
        val itemStack = sender.inventory.getItem(slot)
        if (itemStack.`is`(GenerationsItemTags.POKEMAIL)) {
            itemStack.update(GenerationsDataComponents.MAIL_DATA.asValue(), MailContent()) {
                it.content = contents
                return@update it
            }
        }
    }

    private fun sealMail(sender: ServerPlayer, slot: Int, contents: String, title: String) {
        val itemStack = sender.inventory.getItem(slot)
        if (itemStack.`is`(GenerationsItemTags.POKEMAIL)) {
            val stack = MailItem.getSealed(itemStack.item)

            stack.update(GenerationsDataComponents.MAIL_DATA.asValue(), MailContent()) {
                it.content = contents
                it.author = sender.name.string
                it.title = title
                return@update it
            }

            sender.inventory.setItem(slot, stack)
        }
    }
}