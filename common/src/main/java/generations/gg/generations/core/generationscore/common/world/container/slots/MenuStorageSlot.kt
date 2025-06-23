//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package generations.gg.generations.core.generationscore.common.world.container.slots

import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.base.UpdateManager
import earth.terrarium.common_storage_lib.storage.util.ModifiableItemSlot
import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

open class MenuStorageSlot(private val storage: CommonStorage<ItemResource>, slotIndex: Int, x: Int, y: Int) :
    Slot(EMPTY, slotIndex, x, y) {
    private val storageSlot =
        storage[slotIndex]
    private var modifiable: ModifiableItemSlot

    init {
        if (storageSlot is ModifiableItemSlot) {
            this.modifiable = storageSlot
        } else {
            throw UnsupportedOperationException("Cannot create MenuStorageSlot from non-modifiable slot")
        }
    }

    override fun mayPlace(stack: ItemStack): Boolean {
        return !stack.isEmpty && storageSlot.isResourceValid(ItemResource.of(stack))
    }

    override fun getItem(): ItemStack {
        return storageSlot.resource.toStack(storageSlot.amount.toInt())
    }

    override fun set(stack: ItemStack) {
        modifiable.set(stack)
    }

    override fun onQuickCraft(oldStackIn: ItemStack, newStackIn: ItemStack) {
    }

    override fun getMaxStackSize(): Int {
        return storageSlot.getLimit(storageSlot.resource as ItemResource).toInt()
    }

    override fun getMaxStackSize(stack: ItemStack): Int {
        return modifiable.getMaxAllowed(ItemResource.of(stack))
    }

    override fun mayPickup(playerIn: Player): Boolean {
        return !modifiable.isEmpty
    }

    override fun remove(amount: Int): ItemStack {
        val resource = storageSlot.resource as ItemResource
        val extract = storageSlot.extract(resource, amount.toLong(), false)
        val itemStack = if (extract > 0L) resource.toStack(extract.toInt()) else ItemStack.EMPTY
        return itemStack
    }

    override fun setChanged() {
        super.setChanged()
        UpdateManager.batch(this.storageSlot)
    }

    companion object {
        val EMPTY: Container = SimpleContainer(0)
    }
}
