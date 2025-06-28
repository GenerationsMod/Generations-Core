package generations.gg.generations.core.generationscore.common.world.container

import com.cobblemon.mod.common.item.berry.BerryItem
import generations.gg.generations.core.generationscore.common.world.container.slots.CurryResultSlot
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryIngredient
import generations.gg.generations.core.generationscore.common.world.level.block.entities.Toggleable
import net.minecraft.tags.ItemTags
import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
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
    storage: Container = SimpleContainer(14),
    val data: ContainerData = SimpleContainerData(4)
) : AbstractContainerMenu(GenerationsContainers.COOKING_POT.value(), id), Toggleable {
    init {
        addSlot(Slot(storage, 0, 26, 8 + 11))
        addSlot(Slot(storage, 1, 44, 8 + 11))
        addSlot(Slot(storage, 2, 62, 8 + 11))
        addSlot(Slot(storage, 3, 80, 26 + 11))
        addSlot(Slot(storage, 4, 80, 44 + 11))
        addSlot(Slot(storage, 5, 62, 62 + 11))
        addSlot(Slot(storage, 6, 44, 62 + 11))
        addSlot(Slot(storage, 7, 26, 62 + 11))
        addSlot(Slot(storage, 8, 8, 44 + 11))
        addSlot(Slot(storage, 9, 8, 26 + 11))
        addSlot(Slot(storage, 10, 35, 35 + 11))
        addSlot(Slot(storage, 11, 53, 35 + 11))
        addSlot(Slot(storage, 12, 108, 57 + 11))
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
        if (index !in slots.indices) return ItemStack.EMPTY

        val slot = slots[index]
        if (!slot.hasItem()) return ItemStack.EMPTY

        val slotStack = slot.item
        val originalStack = slotStack.copy()
        val originalComponents = slotStack.components

        val moved = when (index) {
            13 -> moveItemStackTo(slotStack, 14, 50, true)

            in 0..13 -> moveItemStackTo(slotStack, 14, 50, false)

            else ->  when {
                isBerryOrMaxMushrooms(originalStack) -> moveItemStackTo(slotStack, 0, 10, false)
                isBowl(originalStack) -> moveItemStackTo(slotStack, 10, 11, false)
                isCurryIngredientOrMaxHoney(originalStack) -> moveItemStackTo(slotStack, 11, 12, false)
                isLog(originalStack) -> moveItemStackTo(slotStack, 12, 13, false)
                else -> false
            }
        }

        if (!moved) return ItemStack.EMPTY

        if (slotStack.isEmpty) {
            slot.set(ItemStack.EMPTY)
        } else {
            slot.setChanged()
        }

        if (ItemStack.isSameItemSameComponents(slotStack, originalStack)) {
            slotStack.applyComponents(originalComponents)
        }

        if (index == 13) {
            slot.onTake(playerIn, originalStack)
        }

        return originalStack
    }

    val isCooking: Boolean
        get() = data[1] == 1

    val cookTime: Int
        get() = data[0]

    override var isToggled: Boolean
        get() = data[2] == 1
        set(value) { setData(2, if (value) 1 else 0) }

    companion object {
        fun isBowl(obj: Any): Boolean {
            return getItem(obj) === Items.BOWL
        }

        fun isLog(stack: Any): Boolean {
            val item = getItem(stack)
            return item!!.builtInRegistryHolder().`is`(ItemTags.LOGS_THAT_BURN)
        }

        private fun getItem(obj: Any): Item? {
            return if (obj is ItemStack) obj.item
            else null
        }

        fun isBerryOrMaxMushrooms(stack: Any): Boolean {
            val item = getItem(stack)
            return item is BerryItem || item === GenerationsItems.MAX_MUSHROOMS
        }

        fun isCurryIngredientOrMaxHoney(stack: Any): Boolean {
            val item = getItem(stack)
            return item is CurryIngredient || item === GenerationsItems.MAX_HONEY
        }
    }
}
