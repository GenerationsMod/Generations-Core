package generations.gg.generations.core.generationscore.common.util

import generations.gg.generations.core.generationscore.common.world.item.components.SimpleContainer
import net.minecraft.world.item.ItemStack

open class ExtendedsimpleItemContainer(size: Int) : SimpleContainer(size) {
    fun updateStack(index: Int, consumer: (ItemStack) -> Unit) {
        val stack = this.getItem(index)

        consumer.invoke(stack)

        setItem(index, stack)
    }
}
