package generations.gg.generations.core.generationscore.common.world.container

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.item.impl.vanilla.WrappedVanillaContainer
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot
import generations.gg.generations.core.generationscore.common.world.container.slots.LockedSlot
import generations.gg.generations.core.generationscore.common.world.container.slots.PredicateSlotItemHandler
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

abstract class SingleSlotContainer protected constructor(
    type: MenuType<out SingleSlotContainer?>?,
    id: Int,
    protected val handler: SimpleItemStorage = SimpleItemStorage(1)
) :
    AbstractContainerMenu(type, id) {
    init {
        this.addSlot(PredicateSlotItemHandler(
            handler, 0, 80, 35
        ) { stack: ItemStack? -> this.isStackValidForSingleSlot(stack) })
    }

    //Needs to be applied after constructor
    fun applyPlayerInventory(playerInventory: Inventory) {
        val storage = WrappedVanillaContainer(playerInventory)

        for (y in 0..2) {
            for (x in 0..8) {
                this.addSlot(getSlot(storage, x + y * 9 + 9, 8 + x * 18, 84 + y * 18))
            }
        }

        for (x in 0..8) {
            this.addSlot(getSlot(storage, x, 8 + x * 18, 142))
        }
    }

    protected open fun isStackValidForSingleSlot(stack: ItemStack?): Boolean {
        return true
    }

    protected fun getSlot(inventory: CommonStorage<ItemResource?>, i: Int, x: Int, y: Int): Slot {
        return if (isPlayerSlotLocked(i)) {
            LockedSlot(inventory, i, x, y)
        } else {
            MenuStorageSlot(inventory, i, x, y)
        }
    }

    protected open fun isPlayerSlotLocked(slot: Int): Boolean {
        return false
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        var itemStack = ItemStack.EMPTY

        val slot = slots[index]

        if (slot.hasItem()) {
            val itemStack2 = slot.item
            itemStack = itemStack2.copy()

            if (if (index < 1) !this.moveItemStackTo(itemStack2, 1, slots.size, true) else !this.moveItemStackTo(
                    itemStack2,
                    0,
                    1,
                    false
                )
            ) {
                return ItemStack.EMPTY
            }
            if (itemStack2.isEmpty) {
                slot.set(ItemStack.EMPTY)
            } else {
                slot.setChanged()
            }
        }
        return itemStack
    }


    override fun stillValid(player: Player): Boolean {
        return true
    }
}
