package generations.gg.generations.core.generationscore.common.world.container

import earth.terrarium.common_storage_lib.context.impl.PlayerContext
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import generations.gg.generations.core.generationscore.common.GenerationsStorage.IMBUED
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.MelodyFluteItem
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

class MelodyFluteContainer : SingleSlotContainer {
    private val slot: Int

    constructor(id: Int, playerInventory: Inventory, player: Player) : super(
        GenerationsContainers.MELODY_FLUTE.get(), id, SimpleItemStorage(player.inventory.getSelected(), IMBUED, 1)
    ) {
        slot = playerInventory.selected
        applyPlayerInventory(playerInventory)
    }

    constructor(id: Int, playerInventory: Inventory) : super(GenerationsContainers.MELODY_FLUTE.get(), id) {
        this.slot = -1
        applyPlayerInventory(playerInventory)
    }

    override fun isStackValidForSingleSlot(stack: ItemStack): Boolean {
        return MelodyFluteItem.isItem(GenerationsItems.ICY_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.ELEGANT_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.STATIC_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.BELLIGERENT_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.FIERY_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.SINISTER_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.RAINBOW_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.SILVER_WING, stack)
    }

    override fun isPlayerSlotLocked(slot: Int): Boolean {
        return this.slot == slot
    }

    override fun isValidSlotIndex(slotIndex: Int): Boolean {
        return super.isValidSlotIndex(slotIndex) || slotIndex == slot
    }
}
