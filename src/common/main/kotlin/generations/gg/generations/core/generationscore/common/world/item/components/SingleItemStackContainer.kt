package generations.gg.generations.core.generationscore.common.world.item.components

import generations.gg.generations.core.generationscore.common.world.container.CloseableContainer
import net.minecraft.world.Container
import net.minecraft.world.ContainerListener
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

open class SingleItemStackContainer(
    private var stack: ItemStack = ItemStack.EMPTY
) : Container, CloseableContainer {

    private val listeners: MutableList<ContainerListener> = mutableListOf()

    override fun getContainerSize(): Int = 1

    override fun isEmpty(): Boolean = stack.isEmpty

    override fun getItem(slot: Int): ItemStack =
        if (slot == 0) stack else ItemStack.EMPTY

    override fun removeItem(slot: Int, amount: Int): ItemStack {
        if (slot != 0 || stack.isEmpty || amount <= 0) return ItemStack.EMPTY
        return if (stack.count <= amount) {
            val result = stack
            stack = ItemStack.EMPTY
            setChanged()
            result
        } else {
            val split = stack.split(amount)
            setChanged()
            split
        }
    }

    override fun removeItemNoUpdate(slot: Int): ItemStack =
        if (slot == 0) {
            val result = stack
            stack = ItemStack.EMPTY
            result
        } else ItemStack.EMPTY

    override fun setItem(slot: Int, stack: ItemStack) {
        if (slot == 0) {
            this.stack = stack.copy()
            setChanged()
        }
    }

    override fun getMaxStackSize(): Int = 64

    override fun setChanged() = listeners.forEach { it.containerChanged(this) }

    override fun stillValid(player: Player): Boolean = true

    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean = slot == 0

    override fun canTakeItem(target: Container, slot: Int, stack: ItemStack): Boolean = slot == 0

    override fun clearContent() {
        stack = ItemStack.EMPTY
        setChanged()
    }

    fun addListener(listener: ContainerListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: ContainerListener) {
        listeners.remove(listener)
    }

    override fun close() {
        listeners.clear()
    }
}