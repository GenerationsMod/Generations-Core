package generations.gg.generations.core.generationscore.common.world.container

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.item.impl.vanilla.PlayerContainer
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot
import generations.gg.generations.core.generationscore.common.world.container.slots.PredicateSlotItemHandler
import net.minecraft.core.Holder
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.ItemStack

abstract class SingleSlotContainer protected constructor(
    type: MenuType<*>,
    id: Int,
    handler: CommonStorage<ItemResource> = SimpleItemStorage(1),
) :
    AbstractContainerMenu(type, id) {
    init {
        this.addSlot(PredicateSlotItemHandler(
            handler, 0, 80, 35
        ) { stack: ItemStack -> this.isStackValidForSingleSlot(stack) })
    }

    //Needs to be applied after constructor
    fun applyPlayerInventory(playerInventory: Inventory) {
        var container = PlayerContainer(playerInventory);

        for (y in 0..2) {
            for (x in 0..8) {
                this.addSlot(container.toSlot( x + y * 9 + 9, 8 + x * 18, 84 + y * 18))
            }
        }

        for (x in 0..8) {
            this.addSlot(container.toSlot( x, 8 + x * 18, 142) /*else Slot(playerInventory, x, 8 + x * 18, 142)*/)
        }
    }

    protected open fun isStackValidForSingleSlot(stack: ItemStack): Boolean {
        return true
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

private fun CommonStorage<ItemResource>.toSlot(slot: Int, x: Int, y: Int): MenuStorageSlot {
    return MenuStorageSlot(this, slot, x, y)
}
