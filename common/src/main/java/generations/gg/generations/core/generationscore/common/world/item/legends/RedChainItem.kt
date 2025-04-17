package generations.gg.generations.core.generationscore.common.world.item.legends

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.util.add
import generations.gg.generations.core.generationscore.common.util.extensions.component
import generations.gg.generations.core.generationscore.common.util.extensions.get
import generations.gg.generations.core.generationscore.common.util.extensions.getOrDefault
import generations.gg.generations.core.generationscore.common.util.extensions.update
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsItemComponents
import net.minecraft.core.component.DataComponentType
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class RedChainItem(properties: Properties) : EnchantableItem(properties.component(GenerationsItemComponents.USES.componentType(), 0)), LangTooltip {
    override fun neededEnchantmentLevel(player: ServerPlayer): Int {
        val caught = GenerationsCore.CONFIG.caught
        return if (caught.capped(player, LegendKeys.DIALGA) || caught.capped(player, LegendKeys.GIRATINA) || caught.capped(player, LegendKeys.PALKIA)) super.neededEnchantmentLevel(player)
        else 0
    }

    override fun appendHoverText(stack: ItemStack, level: TooltipContext, tooltipComponents: MutableList<Component>, isAdvanced: TooltipFlag) {
        if (isAdvanced.isAdvanced) tooltipComponents.add("Remaining Uses: ${MAX_USES - getUses(stack)}")
    }

    override fun tooltipId(stack: ItemStack): String {
        return this.descriptionId + (if (isEnchanted(stack)) ".enchanted" else "") + ".tooltip"
    }

    companion object {
        const val MAX_USES: Int = 3

        @JvmStatic
        fun incrementUsage(stack: ItemStack) {
            setEnchanted(stack, false)
            stack.update(GenerationsItemComponents.USES.componentType(), 0) { it + 1 }
        }

        @JvmStatic
        fun getUses(stack: ItemStack): Int {
            return stack.getOrDefault(GenerationsItemComponents.USES.componentType(), 0)
        }
    }
}
