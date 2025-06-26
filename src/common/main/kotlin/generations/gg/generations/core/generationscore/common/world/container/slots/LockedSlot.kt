package generations.gg.generations.core.generationscore.common.world.container.slots

import earth.terrarium.common_storage_lib.context.ItemContext
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

class LockedSlot(storage: Inventory, slot: Int, x: Int, y: Int) : Slot(storage, slot, x, y) {
    override fun mayPickup(player: Player): Boolean {
        return false
    }

    override fun mayPlace(stack: ItemStack): Boolean {
        return false
    }
}
