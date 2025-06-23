package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.Cobblemon.implementation
import generations.gg.generations.core.generationscore.common.network.packets.S2COpenMailPacket
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import generations.gg.generations.core.generationscore.common.world.item.components.MailContent
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.stats.Stats
import net.minecraft.util.StringUtil
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class ClosedMailItem(@JvmField val type: MailType, arg: Properties) : Item(arg) {
//    override fun getName(stack: ItemStack): Component {
//        val sealedMailData = stack.get(GenerationsDataComponents.SEALED_MAIL_DATA)
//        if (sealedMailData != null && !StringUtil.isNullOrEmpty(sealedMailData.title)) {
//            return sealedMailData.title.text()
//        }
//        return super.getName(stack)
//    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        val sealedMailData = stack.get(GenerationsDataComponents.MAIL_DATA.value())

        if (sealedMailData != null) {
            val string = sealedMailData.author
            if (!StringUtil.isNullOrEmpty(string)) {
                tooltipComponents.add(Component.translatable("book.byAuthor", string).withStyle(ChatFormatting.GRAY))
            }
        }
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val itemStack = player.getItemInHand(usedHand)
        if (player is ServerPlayer && itemStack.`is`(GenerationsItemTags.CLOSED_POKEMAIL)) {
            if (resolveBookComponents(itemStack, player.createCommandSourceStack(), player)) {
                player.containerMenu.broadcastChanges()
            }

            implementation.networkManager.sendPacketToPlayer(player, S2COpenMailPacket(usedHand))
        }
        player.awardStat(Stats.ITEM_USED[this])
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide())
    }

    override fun isFoil(stack: ItemStack): Boolean {
        return true
    }

    companion object {
        const val TITLE_LENGTH: Int = 16
        const val TITLE_MAX_LENGTH: Int = 32
        const val PAGE_EDIT_LENGTH: Int = 1024
        const val PAGE_LENGTH: Int = Short.MAX_VALUE.toInt()
        const val TAG_TITLE: String = "title"
        const val TAG_AUTHOR: String = "author"
        const val TAG_CONTENTS: String = "contents"
        const val TAG_RESOLVED: String = "resolved"


        fun resolveBookComponents(
            stack: ItemStack,
            resolvingSource: CommandSourceStack?,
            resolvingPlayer: Player?
        ): Boolean {
            val sealedMailData = stack.get(GenerationsDataComponents.MAIL_DATA.value())
            if (sealedMailData == null || sealedMailData.resolved) {
                return false
            }

            sealedMailData.resolved = true

            val s = sealedMailData.content
            val string = resolvePage(resolvingSource, resolvingPlayer, s)

            sealedMailData.content = string

            stack.set(GenerationsDataComponents.MAIL_DATA.value(), sealedMailData)

            return true
        }

        private fun resolvePage(
            resolvingSource: CommandSourceStack?,
            resolvingPlayer: Player?,
            resolvingPageContents: String
        ): String {
            return resolvingPageContents
            //TODO: Determine if we want Component Support
            /*        MutableComponent component;
        try {
            component = Component.Serializer.fromJsonLenient(resolvingPageContents);
            component = ComponentUtils.updateForEntity(resolvingSource, component, (Entity)resolvingPlayer, 0);
        }
        catch (Exception exception) {
            component = Component.literal(resolvingPageContents);
        }
        return Component.Serializer.toJson(component);*/
        }
    }
}


