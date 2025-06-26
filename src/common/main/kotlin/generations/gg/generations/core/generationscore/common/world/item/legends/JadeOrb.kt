package generations.gg.generations.core.generationscore.common.world.item.legends

import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.TechnicalMachineItem
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class JadeOrb(properties: Properties) : TechnicalMachineItem("dragonascent", properties), LangTooltip {
    override fun addText(stack: ItemStack, level: TooltipContext, tooltipComponents: MutableList<Component>, isAdvanced: TooltipFlag) = super<LangTooltip>.addText(stack, level, tooltipComponents, isAdvanced)
}
