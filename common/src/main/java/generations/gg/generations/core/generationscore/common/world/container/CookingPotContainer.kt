package generations.gg.generations.core.generationscore.common.world.container

import com.cobblemon.mod.common.item.berry.BerryItem
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot
import generations.gg.generations.core.generationscore.common.world.container.slots.CurryResultSlot
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryIngredient
import net.minecraft.tags.ItemTags
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.inventory.SimpleContainerData
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

class CookingPotContainer @JvmOverloads constructor(
    id: Int,
    playerInventory: Inventory,
    storage: SimpleItemStorage = SimpleItemStorage(14),
    data: ContainerData = SimpleContainerData(4)
) :
    AbstractContainerMenu(GenerationsContainers.COOKING_POT.get(), id) {
    init {
        addSlot(MenuStorageSlot(storage, 0, 26, 8 + 11))
        addSlot(MenuStorageSlot(storage, 1, 44, 8 + 11))
        addSlot(MenuStorageSlot(storage, 2, 62, 8 + 11))
        addSlot(MenuStorageSlot(storage, 3, 80, 26 + 11))
        addSlot(MenuStorageSlot(storage, 4, 80, 44 + 11))
        addSlot(MenuStorageSlot(storage, 5, 62, 62 + 11))
        addSlot(MenuStorageSlot(storage, 6, 44, 62 + 11))
        addSlot(MenuStorageSlot(storage, 7, 26, 62 + 11))
        addSlot(MenuStorageSlot(storage, 8, 8, 44 + 11))
        addSlot(MenuStorageSlot(storage, 9, 8, 26 + 11))
        addSlot(MenuStorageSlot(storage, 10, 35, 35 + 11))
        addSlot(MenuStorageSlot(storage, 11, 53, 35 + 11))
        addSlot(MenuStorageSlot(storage, 12, 108, 57 + 11))
        addSlot(CurryResultSlot(playerInventory.player, storage, 13, 142, 35 + 11))

        bindPlayerInventory(playerInventory)
        addDataSlots(data)
        sendAllDataToRemote()
    }

    private fun bindPlayerInventory(player: Inventory) {
        for (y in 0..2) {
            for (x in 0..8) {
                addSlot(Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18 + 11 + 9))
            }
        }

        for (x in 0..8) {
            addSlot(Slot(player, x, 8 + x * 18, 142 + 11 + 9))
        }
    }

    override fun stillValid(playerIn: Player): Boolean {
        return true //TODO: WOrry about this later...
    }


    override fun quickMoveStack(playerIn: Player, index: Int): ItemStack {
        val slot = slots[index]
        if (!slot.hasItem()) return ItemStack.EMPTY

        val slotStack = slot.item
        val originalStack = slotStack.copy() // Copy to retain original data

        // Store NBT for debugging before we move it
        val originalData = slotStack.components


        val moved = false

        // If the slot is the crafting output (slot 13), ensure crafting logic is applied before moving
        if (index == 13) {
//            System.out.println("  - Crafting Slot Detected! Running `onTake()` before moving.");
            slot.onTake(playerIn, slotStack)
        }

        if (index < 14) {
            this.moveItemStackTo(slotStack, 14, 49, false)
        } else if (index < 50) {
            if (isBerryOrMaxMushrooms(originalStack)) {
                this.moveItemStackTo(slotStack, 0, 10, false)
            } else if (isBowl(originalStack)) {
                this.moveItemStackTo(slotStack, 10, 11, false)
            } else if (isCurryIngredientOrMaxHoney(originalStack)) {
                this.moveItemStackTo(slotStack, 11, 12, false)
            } else if (isLog(originalStack)) {
                this.moveItemStackTo(slotStack, 12, 13, false)
            }
        }

        if (!ItemStack.matches(slotStack, originalStack)) {
            slotStack.applyComponents(originalData)
        }

        if (slotStack.count == 0) {
            slot.safeInsert(ItemStack.EMPTY)
        } else {
            slot.setChanged()
        }

        if (slotStack.count == originalStack.count) {
            return ItemStack.EMPTY
        }

        if (index == 13) {
            slot.onTake(playerIn, originalStack)
        }

        return originalStack
    }

    val isCooking: Boolean
        get() = false

    val cookTime: Int
        get() = 0

    companion object {
        fun isBowl(obj: Any): Boolean {
            return getItem(obj) === Items.BOWL
        }

        fun isLog(stack: Any): Boolean {
            val item = getItem(stack)
            return item!!.`arch$holder`().`is`(ItemTags.LOGS_THAT_BURN)
        }

        private fun getItem(obj: Any): Item? {
            return if (obj is ItemResource) obj.item
            else if (obj is ItemStack) obj.item
            else null
        }

        fun isBerryOrMaxMushrooms(stack: Any): Boolean {
            val item = getItem(stack)
            return item is BerryItem || item === GenerationsItems.MAX_MUSHROOMS.get()
        }

        fun isCurryIngredientOrMaxHoney(stack: Any): Boolean {
            val item = getItem(stack)
            return item is CurryIngredient || item === GenerationsItems.MAX_HONEY.get()
        }
    }
}
