package generations.gg.generations.core.generationscore.common.world.item

import net.minecraft.locale.Language
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.TooltipContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

interface LangTooltip {
    public fun addText(
        stack: ItemStack,
        level: TooltipContext,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        val tooltipId = tooltipId(stack)
        if (Language.getInstance().has(self().getDescriptionId(stack))) tooltipComponents.add(
            Component.translatable(
                tooltipId
            )
        )
    }

    fun tooltipId(): String {
        return tooltipId(null)
    }

    fun tooltipId(stack: ItemStack?): String {
        return self().getDescriptionId(stack ?: ItemStack.EMPTY) + ".tooltip"
    }

    private fun self(): Item {
        return this as Item
    }
}
