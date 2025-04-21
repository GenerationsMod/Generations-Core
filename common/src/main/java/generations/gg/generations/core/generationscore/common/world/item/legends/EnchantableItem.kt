package generations.gg.generations.core.generationscore.common.world.item.legends

import generations.gg.generations.core.generationscore.common.util.extensions.component
import generations.gg.generations.core.generationscore.common.util.extensions.getOrDefault
import generations.gg.generations.core.generationscore.common.util.extensions.set
import generations.gg.generations.core.generationscore.common.world.item.ItemWithLangTooltipImpl
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsItemComponents
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

abstract class EnchantableItem(arg: Properties) : ItemWithLangTooltipImpl(arg.component(GenerationsItemComponents.ENCHANTED, false)) {
    open fun neededEnchantmentLevel(player: ServerPlayer): Int {
        return 100
    }

    override fun isFoil(stack: ItemStack): Boolean {
        return isEnchanted(stack)
    }

    companion object {
        fun getEnchanted(item: Item): ItemStack {
            val stack = ItemStack(item)
            stack.set(GenerationsItemComponents.ENCHANTED, true)
            return stack
        }

        @JvmStatic
        fun setEnchanted(stack: ItemStack, enchanted: Boolean): ItemStack {
            stack.set(GenerationsItemComponents.ENCHANTED, enchanted)
            return stack
        }

        fun setUsed(stack: ItemStack, used: Boolean): ItemStack {
            stack.set(GenerationsItemComponents.USED, used)
            return stack
        }

        @JvmStatic
        fun isUsed(stack: ItemStack?): Boolean {
            return stack != null && !stack.isEmpty && stack.getOrDefault(GenerationsItemComponents.USED, false)
        }

        @JvmStatic
        fun isEnchanted(stack: ItemStack?): Boolean {
            return stack != null && !stack.isEmpty && stack.getOrDefault(GenerationsItemComponents.ENCHANTED, false)
        }
    }
}
