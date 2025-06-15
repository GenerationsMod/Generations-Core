package generations.gg.generations.core.generationscore.common.world.item.legends

import generations.gg.generations.core.generationscore.common.world.item.ItemWithLangTooltipImpl
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

abstract class EnchantableItem(arg: Properties) : ItemWithLangTooltipImpl(arg.component(GenerationsDataComponents.ENCHANTED, false)) {
    open fun neededEnchantmentLevel(player: ServerPlayer): Int {
        return 100
    }

    override fun isFoil(stack: ItemStack): Boolean {
        return isEnchanted(stack)
    }

    companion object {
        fun getEnchanted(item: Item): ItemStack {
            val stack = ItemStack(item)
            stack.set(GenerationsDataComponents.ENCHANTED, true)
            return stack
        }

        @JvmStatic
        fun setEnchanted(stack: ItemStack, enchanted: Boolean): ItemStack {
            stack.set(GenerationsDataComponents.ENCHANTED, enchanted)
            return stack
        }

        fun setUsed(stack: ItemStack, used: Boolean): ItemStack {
            stack.set(GenerationsDataComponents.USED, used)
            return stack
        }

        @JvmStatic
        fun isUsed(stack: ItemStack?): Boolean {
            return stack != null && !stack.isEmpty && stack.getOrDefault(GenerationsDataComponents.USED, false)
        }

        @JvmStatic
        fun isEnchanted(stack: ItemStack?): Boolean {
            return stack != null && !stack.isEmpty && stack.getOrDefault(GenerationsDataComponents.ENCHANTED, false)
        }
    }
}
