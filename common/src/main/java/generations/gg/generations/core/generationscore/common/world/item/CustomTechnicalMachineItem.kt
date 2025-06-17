package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.util.extensions.asValue
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import generations.gg.generations.core.generationscore.common.world.item.components.TmDetails
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

class CustomTechnicalMachineItem(properties: Properties) : MoveTeachingItem(properties) {
    override fun getMoveString(itemStack: ItemStack): String? {
        val tag = itemStack.get(GenerationsDataComponents.TM_DETAILS.asValue<TmDetails>())
        if (tag != null) {
            return tag.move
        }
        return null
    }

    override val isConsumed: Boolean
        get() = false

    override fun getName(stack: ItemStack): Component {
        val move = getMove(stack)
        return Component.literal("TM" + getNumber(stack) + " " + (move?.displayName?.toString() ?: "Blank"))
    }

    private fun getNumber(itemStack: ItemStack): String {
        val tag = itemStack.get(GenerationsDataComponents.TM_DETAILS.asValue<TmDetails>())
        return (tag?.number ?: 0).let(::formatWithZeros)
    }

    private fun formatWithZeros(number: Int): String {
        val formattedNumber = Integer.toString(number)
        val zerosNeeded = 3 - formattedNumber.length
        return if (zerosNeeded > 0) "0".repeat(zerosNeeded) + formattedNumber else formattedNumber.substring(0, 3)
    }

    fun createTm(move: String, number: Int): ItemStack {
        return GenerationsItems.CUSTOM_TM.value().defaultInstance.also { it.set(GenerationsDataComponents.TM_DETAILS.asValue(), TmDetails(move, number)) }
    }
}
