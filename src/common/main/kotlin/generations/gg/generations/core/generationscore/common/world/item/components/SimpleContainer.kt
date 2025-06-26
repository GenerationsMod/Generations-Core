package generations.gg.generations.core.generationscore.common.world.item.components

import com.google.common.collect.Lists
import generations.gg.generations.core.generationscore.common.world.container.CloseableContainer
import net.minecraft.core.NonNullList
import net.minecraft.world.Container
import net.minecraft.world.ContainerHelper
import net.minecraft.world.ContainerListener
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.player.StackedContents
import net.minecraft.world.inventory.StackedContentsCompatible
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.util.stream.Collectors
import kotlin.math.min

open class SimpleContainer : Container, StackedContentsCompatible, CloseableContainer {
    private val size: Int
    val items: NonNullList<ItemStack>
    private val listeners = mutableListOf<ContainerListener>()

    constructor(size: Int) {
        this.size = size
        this.items = NonNullList.withSize(size, ItemStack.EMPTY)
    }

    constructor(vararg items: ItemStack) {
        this.size = items.size
        this.items = NonNullList.of(ItemStack.EMPTY, *items)
    }

    constructor(items: NonNullList<ItemStack>) {
        this.items = items
        this.size = items.size
    }

    fun addListener(listener: ContainerListener) {

        listeners.add(listener)
    }

    fun removeListener(listener: ContainerListener) {
        listeners.remove(listener)
    }

    override fun getItem(slot: Int): ItemStack {
        return if (slot >= 0 && slot < items.size) items[slot] else ItemStack.EMPTY
    }

    fun removeAllItems(): List<ItemStack> {
        val list =
            items.stream().filter { itemStack: ItemStack -> !itemStack.isEmpty }.collect(Collectors.toList())
        this.clearContent()
        return list
    }

    override fun removeItem(slot: Int, amount: Int): ItemStack {
        val itemStack = ContainerHelper.removeItem(this.items, slot, amount)
        if (!itemStack.isEmpty) {
            this.setChanged()
        }

        return itemStack
    }

    fun removeItemType(item: Item, amount: Int): ItemStack {
        val itemStack = ItemStack(item, 0)

        for (i in this.size - 1 downTo 0) {
            val itemStack2 = this.getItem(i)
            if (itemStack2.item == item) {
                val j = amount - itemStack.count
                val itemStack3 = itemStack2.split(j)
                itemStack.grow(itemStack3.count)
                if (itemStack.count == amount) {
                    break
                }
            }
        }

        if (!itemStack.isEmpty) {
            this.setChanged()
        }

        return itemStack
    }

    fun addItem(stack: ItemStack): ItemStack {
        if (stack.isEmpty) {
            return ItemStack.EMPTY
        } else {
            val itemStack = stack.copy()
            this.moveItemToOccupiedSlotsWithSameType(itemStack)
            if (itemStack.isEmpty) {
                return ItemStack.EMPTY
            } else {
                this.moveItemToEmptySlots(itemStack)
                return if (itemStack.isEmpty) ItemStack.EMPTY else itemStack
            }
        }
    }

    fun canAddItem(stack: ItemStack): Boolean {
        var bl = false

        for (itemStack in this.items) {
            if (itemStack.isEmpty || ItemStack.isSameItemSameComponents(
                    itemStack,
                    stack
                ) && itemStack.count < itemStack.maxStackSize
            ) {
                bl = true
                break
            }
        }

        return bl
    }

    override fun removeItemNoUpdate(slot: Int): ItemStack {
        val itemStack = items[slot]
        if (itemStack.isEmpty) {
            return ItemStack.EMPTY
        } else {
            items[slot] = ItemStack.EMPTY
            return itemStack
        }
    }

    override fun setItem(slot: Int, stack: ItemStack) {
        items[slot] = stack
        stack.limitSize(this.getMaxStackSize(stack))
        this.setChanged()
    }

    override fun getContainerSize(): Int {
        return this.size
    }

    override fun isEmpty(): Boolean {
        val var1: Iterator<ItemStack> = items.iterator()

        var itemStack: ItemStack
        do {
            if (!var1.hasNext()) {
                return true
            }

            itemStack = var1.next()
        } while (itemStack.isEmpty)

        return false
    }

    override fun setChanged() {
        if (this.listeners != null) {
            for (containerListener in listeners!!) {
                containerListener.containerChanged(this)
            }
        }
    }

    override fun stillValid(player: Player): Boolean {
        return true
    }

    override fun clearContent() {
        items.clear()
        this.setChanged()
    }

    override fun fillStackedContents(contents: StackedContents) {
        for (itemStack in this.items) {
            contents.accountStack(itemStack)
        }
    }

    override fun toString(): String {
        return items.stream().filter { itemStack: ItemStack -> !itemStack.isEmpty }.toList().toString()
    }

    private fun moveItemToEmptySlots(stack: ItemStack) {
        for (i in 0 until this.size) {
            val itemStack = this.getItem(i)
            if (itemStack.isEmpty) {
                this.setItem(i, stack.copyAndClear())
                return
            }
        }
    }

    private fun moveItemToOccupiedSlotsWithSameType(stack: ItemStack) {
        for (i in 0 until this.size) {
            val itemStack = this.getItem(i)
            if (ItemStack.isSameItemSameComponents(itemStack, stack)) {
                this.moveItemsBetweenStacks(stack, itemStack)
                if (stack.isEmpty) {
                    return
                }
            }
        }
    }

    private fun moveItemsBetweenStacks(stack: ItemStack, other: ItemStack) {
        val i = this.getMaxStackSize(other)
        val j = min(stack.count.toDouble(), (i - other.count).toDouble()).toInt()
        if (j > 0) {
            other.grow(j)
            stack.shrink(j)
            this.setChanged()
        }
    }

    override fun close() {
        listeners.clear()
    }
}