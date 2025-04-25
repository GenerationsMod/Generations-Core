package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.Cobblemon.implementation
import generations.gg.generations.core.generationscore.common.network.packets.S2COpenMailEditScreenPacket
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.server.level.ServerPlayer
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class MailItem(@JvmField val type: MailType, arg: Properties) : Item(arg) {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val itemStack = player.getItemInHand(usedHand)
        if (!level.isClientSide() && player is ServerPlayer) implementation.networkManager.sendPacketToPlayer(
            player,
            S2COpenMailEditScreenPacket(usedHand)
        )
        player.awardStat(Stats.ITEM_USED[this])
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide())
    }

    companion object {
        fun getSealed(item: Item): ItemStack {
            return if (item is MailItem) ItemStack(item.type.sealed)
            else ItemStack.EMPTY
        }

        /**
         * Returns `true` if the book's NBT Tag List "pages" is valid.
         */
        @JvmStatic
        fun makeSureTagIsValid(compoundTag: CompoundTag?): Boolean {
            if (compoundTag == null) return false
            if (!compoundTag.contains("contents", Tag.TAG_STRING.toInt())) return false

            val contents = compoundTag.getString("contents")
            return contents.length <= Short.MAX_VALUE
        }
    }
}
