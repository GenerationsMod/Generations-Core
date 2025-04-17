package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.item.MailItem
import net.minecraft.nbt.StringTag
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Inventory
import java.util.*

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
                itemStack.addTagElement("contents", StringTag.valueOf(contents))
            }
        }

        private fun sealMail(sender: ServerPlayer, slot: Int, contents: String, title: String) {
            val itemStack = sender.inventory.getItem(slot)
            if (itemStack.`is`(GenerationsItemTags.POKEMAIL)) {
                val itemStack1 = MailItem.getSealed(itemStack.item)
                val compoundTag = itemStack.getTag()
                if (compoundTag != null) {
                    itemStack1.setTag(compoundTag)
                }
                itemStack1.addTagElement("author", StringTag.valueOf(sender.name.string))
                itemStack1.addTagElement("contents", StringTag.valueOf(contents))
                sender.inventory.setItem(slot, itemStack1)
            }
        }
    }
}
