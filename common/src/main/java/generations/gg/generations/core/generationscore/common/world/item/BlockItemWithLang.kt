package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlockItem
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item.TooltipContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.block.Block

open class BlockItemWithLang(block: Holder<Block>, properties: Properties) : GenerationsBlockItem(block, properties), LangTooltip {
    override fun appendHoverText(stack: ItemStack, context: TooltipContext, tooltipComponents: MutableList<Component>, isAdvanced: TooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, isAdvanced)
        addText(stack, context, tooltipComponents, isAdvanced)
    }
}