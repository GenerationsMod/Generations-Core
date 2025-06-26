package generations.gg.generations.core.generationscore.common.world.item.legends

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.util.add
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class RedChainItem(properties: Properties) : EnchantableItem(properties.component(GenerationsDataComponents.USES.value(), 0)), LangTooltip {
    override fun neededEnchantmentLevel(player: ServerPlayer): Int {
        val caught = GenerationsCore.CONFIG.caught
        return if (caught.capped(player, LegendKeys.DIALGA) || caught.capped(player, LegendKeys.GIRATINA) || caught.capped(player, LegendKeys.PALKIA)) super.neededEnchantmentLevel(player)
        else 0
    }

    override fun addText(stack: ItemStack, level: TooltipContext, tooltipComponents: MutableList<Component>, isAdvanced: TooltipFlag) {
        if (isAdvanced.isAdvanced) tooltipComponents.add("Remaining Uses: ${MAX_USES - getUses(stack)}")
    }

    override fun tooltipId(stack: ItemStack?): String {
        return this.descriptionId + (if (isEnchanted(stack)) ".enchanted" else "") + ".tooltip"
    }

    companion object {
        const val MAX_USES: Int = 3

        @JvmStatic
        fun incrementUsage(stack: ItemStack) {
            setEnchanted(stack, false)
            stack.update(GenerationsDataComponents.USES.value(), 0) { it + 1 }
        }

        @JvmStatic
        fun getUses(stack: ItemStack): Int {
            return stack.getOrDefault(GenerationsDataComponents.USES.value(), 0)
        }
    }
}
