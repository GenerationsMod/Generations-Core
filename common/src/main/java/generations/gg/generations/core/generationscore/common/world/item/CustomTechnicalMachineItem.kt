package generations.gg.generations.core.generationscore.common.world.item

import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

class CustomTechnicalMachineItem(properties: Properties) : MoveTeachingItem(properties) {
    override fun getMoveString(itemStack: ItemStack?): String? {
        val tag = itemStack!!.tag
        if (tag != null) {
            if (tag.contains("move")) {
                return tag.getString("move")
            }
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
        val tag = itemStack.tag
        return formatWithZeros(if (tag != null && tag.contains("number")) tag.getInt("number") else 0)
    }

    private fun formatWithZeros(number: Int): String {
        val formattedNumber = Integer.toString(number)
        val zerosNeeded = 3 - formattedNumber.length
        return if (zerosNeeded > 0) "0".repeat(zerosNeeded) + formattedNumber else formattedNumber.substring(0, 3)
    }

    fun createTm(number: Int, move: String): ItemStack {
        val stack = GenerationsItems.CUSTOM_TM.get().defaultInstance
        val tag = stack.getOrCreateTag()
        tag.putInt("number", number)
        tag.putString("move", move)
        return stack
    }
}
