package generations.gg.generations.core.generationscore.common.world.container

import generations.gg.generations.core.generationscore.common.world.data.TrashItemStorage
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.item.ItemStack

class TrashCanContainer(id: Int, arg: Inventory) : SingleSlotContainer(GenerationsContainers.TRASHCAN.value(), id, TrashItemStorage) {
    init {
        applyPlayerInventory(arg)
    }
}
