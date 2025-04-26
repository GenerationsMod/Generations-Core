package generations.gg.generations.core.generationscore.common.world.container

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.item.impl.vanilla.WrappedVanillaContainer
import earth.terrarium.common_storage_lib.item.util.ItemStorageData
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.base.UpdateManager
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot
import generations.gg.generations.core.generationscore.common.world.container.slots.LockedSlot
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack

open class GenericChestContainer<T>(
    containerId: Int,
    @JvmField val playerInventory: Inventory,
    private val container: T,
    val inventoryWidth: Int,
    val inventoryHeight: Int,
    protected val locked: Int = -1
) : AbstractContainerMenu(
        GenerationsContainers.GENERIC.get(),
        containerId
    ) where T: CommonStorage<ItemResource>, T: UpdateManager<ItemStorageData> {
    @JvmField
    val guiWidth: Int =  14 + this.inventoryWidth * 18
    @JvmField
    val guiHeight: Int = 110 + (inventoryHeight * 18)
    @JvmField
    val playerInventoryX: Int

    constructor(containerId: Int, playerInventory: Inventory, buf: FriendlyByteBuf) : this(
        containerId, playerInventory,
        SimpleItemStorage(buf.readVarInt()) as T,
        buf.readVarInt(),
        buf.readVarInt()
    )

    init {
//        container.startOpen(playerInventory.player)



        populate(container, 8, 16, 0, inventoryHeight, inventoryWidth)

        this.playerInventoryX = guiWidth / 2 - 80

        val playerStorage = WrappedVanillaContainer(playerInventory)

        populate(playerStorage, playerInventoryX, guiHeight - 82, 1, 3, 9)
        populate(playerStorage, playerInventoryX, guiHeight - 24, 0, 1, 9)
    }

    private fun <V> populate(container: V, x: Int, y: Int, startingRow: Int, rows: Int, columns: Int) where V : CommonStorage<ItemResource> {
        for (row in 0..<rows) {
            for (column in 0..<columns) {
                val slot = column + (startingRow + row) * columns
                val xSlot = x + column * 18
                val ySlot = y + row * 18

                this.addSlot(container, slot, xSlot, ySlot)
            }
        }
    }

    private fun addSlot(container: CommonStorage<ItemResource>, slot: Int, x: Int, y: Int) {
        this.addSlot(getSlot(container, slot, x, y))
    }

    open fun getSlot(container: CommonStorage<ItemResource>, slot: Int, x: Int, y: Int): MenuStorageSlot {
        return if (locked == slot) LockedSlot(container, slot, x, y) else MenuStorageSlot(container, slot, x, y)
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        var itemStack = ItemStack.EMPTY

        val slot = slots[index]

        if (slot.hasItem()) {
            val itemStack1 = slot.item
            itemStack = itemStack1.copy()

            if (if (index < container.size()) !this.moveItemStackTo(
                    itemStack1, container.size(),
                    slots.size, true
                ) else !this.moveItemStackTo(itemStack1, 0, container.size(), false)
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
//        container.stopOpen(player)
    }

    fun getContainer(): T = container

    open fun save(player: Player?) {}
}
