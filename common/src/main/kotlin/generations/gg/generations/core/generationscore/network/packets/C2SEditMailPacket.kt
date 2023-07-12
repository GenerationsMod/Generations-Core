package generations.gg.generations.core.generationscore.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.world.item.MailItem
import net.minecraft.nbt.StringTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Inventory
import java.util.*

class C2SEditMailPacket(val slot: Int, val contents: String, val title: Optional<String>): GenerationsNetworkPacket<C2SEditMailPacket> {
    override val id: ResourceLocation = ID
    override fun encode(buf: FriendlyByteBuf) {
        buf.writeVarInt(slot)
        buf.writeUtf(contents, 8192)
        buf.writeOptional(title) { byteBuf: FriendlyByteBuf?, s: String? -> buf.writeUtf(s, 128) }
    }

    companion object {
        var ID = GenerationsCore.id("edit_mail")

        fun decode(buffer: FriendlyByteBuf) = C2SEditMailPacket(buffer.readVarInt(), buffer.readUtf(8192), buffer.readOptional<String> { arg: FriendlyByteBuf -> arg.readUtf(128) })
    }

    class Handler: ServerNetworkPacketHandler<C2SEditMailPacket> {
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
                title.ifPresentOrElse({ s: String -> sealMail(sender, slot, contents, s) }) {
                    updateMailContents(
                        sender,
                        slot,
                        contents
                    )
                }
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
                val compoundTag = itemStack.tag
                if (compoundTag != null) {
                    itemStack1.tag = compoundTag
                }
                itemStack1.addTagElement("author", StringTag.valueOf(sender.name.string))
                itemStack1.addTagElement("contents", StringTag.valueOf(contents))
                sender.inventory.setItem(slot, itemStack1)
            }
        }
    }
}