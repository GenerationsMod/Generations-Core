package generations.gg.generations.core.generationscore.world.item

import com.cobblemon.mod.common.api.text.plus
import com.cobblemon.mod.common.api.text.text
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

class TechnicalMachineItem(properties: Properties) : MoveTeachingItem(properties) {
    override val isConsumed: Boolean
        get() = false


    override fun getName(stack: ItemStack): Component {
        return "TM".text() + stack.getNumber() + " ".text() + (stack.getMove()?.displayName ?: "Blank".text())
    }
}

private fun ItemStack.getNumber(): Component {
    return (this.tag.takeIf { it?.contains("number") != null }?.getInt("number") ?: 0).formatWithZeros().text()
}

private fun Int.formatWithZeros(): String {
    val formattedNumber = this.toString()
    val zerosNeeded = 3 - formattedNumber.length

    return if (zerosNeeded > 0) "0".repeat(zerosNeeded) + formattedNumber else formattedNumber.substring(0, 3)
}
