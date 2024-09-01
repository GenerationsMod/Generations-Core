package generations.gg.generations.core.generationscore.common.world.item.legends

import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.TechnicalMachineItem
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class JadeOrb(properties: Properties) : TechnicalMachineItem("dragonascent", properties), LangTooltip {
    override fun appendHoverText(stack: ItemStack, level: Level?, tooltipComponents: List<Component>, isAdvanced: TooltipFlag) = super<LangTooltip>.appendHoverText(stack, level, tooltipComponents, isAdvanced)
}
