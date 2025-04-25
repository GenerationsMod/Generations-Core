package generations.gg.generations.core.generationscore.common.world.item

import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

open class TechnicalMachineItem(private val move: String, properties: Properties) : MoveTeachingItem(properties) {
    override fun getMoveString(itemStack: ItemStack): String? = move
    override fun getName(stack: ItemStack): Component = getMove(stack).let { Component.keybind("").append("${super.getName(stack).string} - ${it?.displayName?.string ?: "Blank"}") }
}
