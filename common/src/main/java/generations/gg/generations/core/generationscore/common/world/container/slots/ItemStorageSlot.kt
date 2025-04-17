package generations.gg.generations.core.generationscore.common.world.container.slots

import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.item.util.ItemStorageData
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import generations.gg.generations.core.generationscore.common.util.asResource
import generations.gg.generations.core.generationscore.common.util.grow
import net.minecraft.world.Container
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import kotlin.math.min

open class ItemStorageSlot(protected val storage: SimpleItemStorage, index: Int, x: Int, y: Int): Slot(null as? Container, index, x, y) {
    private val slot: SimpleItemSlot = storage.get(index)

    override fun getItem(): ItemStack {
        return slot.resource.toStack()
    }

    override fun set(stack: ItemStack) {
        slot.set(stack)
        setChanged()
    }

    val resource: ItemResource
        get() = slot.resource

    override fun setChanged() {
        storage.update()
    }

    override fun hasItem(): Boolean {
        return slot.isEmpty
    }

    override fun mayPlace(stack: ItemStack): Boolean {
        return slot.isResourceValid(stack.asResource())
    }

    override fun getMaxStackSize(): Int = 99

    override fun remove(amount: Int): ItemStack = slot.extract(slot.resource, amount.toLong(), false).takeIf { it > 0 }?.let { slot.resource.toStack(it.toInt()) } ?: ItemStack.EMPTY

    override fun safeInsert(stack: ItemStack, increment: Int): ItemStack {
        if(!stack.isEmpty && this.mayPlace(stack)) {
            val i = min(Math.min(increment, stack.count), this.getMaxStackSize(stack) - slot.amount.toInt())
            if (slot.isEmpty) {
                this.setByPlayer(stack.split(i))
            } else if(slot.resource.test(stack)) {
                stack.shrink(i)
                slot.grow(i)
                this.setByPlayer(slot.toItemStack())
            }
        }

        return stack;
    }
}