package generations.gg.generations.core.generationscore.common.world.container

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.container.slots.LockedSlot
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import java.io.Closeable

open class GenericChestContainer(
    containerId: Int,
    val playerInventory: Inventory,
    private val container: Container,
    val inventoryWidth: Int,
    val inventoryHeight: Int,
    lock: (Int) -> Boolean = { false }, val onClosed: () -> Unit = {}
) : AbstractContainerMenu(GenerationsContainers.GENERIC.value(), containerId) {
    @JvmField
    val guiWidth: Int = 14 + this.inventoryWidth.coerceAtLeast(9) * 18

    @JvmField
    val guiHeight: Int = 110 + (inventoryHeight * 18)

    @JvmField
    val playerInventoryX: Int

    init {
        populate(container, 8, 16, 0, inventoryHeight, inventoryWidth)
        this.playerInventoryX = guiWidth / 2 - 80
        populatePlayer(playerInventory, playerInventoryX, guiHeight - 82, 1, 3, 9, lock)
        populatePlayer(playerInventory, playerInventoryX, guiHeight - 24, 0, 1, 9, lock)
    }

    private fun populate(container: Container, x: Int, y: Int, startingRow: Int, rows: Int, columns: Int) {
        for (row in 0..< rows) {
            for (column in 0..< columns) {
                val slot = column + (startingRow + row) * columns
                val xSlot = x + column * 18
                val ySlot = y + row * 18

                this.addSlot(container, slot, xSlot, ySlot)
            }
        }
    }

    private fun populatePlayer(container: Inventory, x: Int, y: Int, startingRow: Int, rows: Int, columns: Int, lock: (Int) -> Boolean) {
        for (row in 0..<rows) {
            for (column in 0..<columns) {
                val slot = column + (startingRow + row) * columns
                val xSlot = x + column * 18
                val ySlot = y + row * 18

                this.addSlot(if(lock.invoke(slot)) LockedSlot(container, slot, xSlot, ySlot) else Slot(container, slot, xSlot, ySlot))
            }
        }
    }

//    fun close() {
//        container.instanceOrNull<CloseableContainer>()
//    }

    private fun addSlot(container: Container, slot: Int, x: Int, y: Int) {
        this.addSlot(Slot(container, slot, x, y))
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        var itemStack = ItemStack.EMPTY

        val slot = slots[index]

        if (slot.hasItem()) {
            val itemStack1 = slot.item
            itemStack = itemStack1.copy()

            if (if (index < container.containerSize) !this.moveItemStackTo(
                    itemStack1, container.containerSize,
                    slots.size, true
                ) else !this.moveItemStackTo(itemStack1, 0, container.containerSize, false)
            ) {
                return ItemStack.EMPTY
            }

            if (itemStack1.isEmpty) {
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

    override fun removed(player: Player) {
        super.removed(player)
        onClosed.invoke()
//        container.stopOpen(player)
    }

    open fun save(player: Player?) {}

    companion object {
        fun fromBuffer(
            containerId: Int,
            playerInventory: Inventory,
            buffer: FriendlyByteBuf
        ): GenericChestContainer {
            val row = buffer.readVarInt()
            val column = buffer.readVarInt()

            return GenericChestContainer(containerId, playerInventory, SimpleContainer(row * column), row, column)
        }
    }
}
