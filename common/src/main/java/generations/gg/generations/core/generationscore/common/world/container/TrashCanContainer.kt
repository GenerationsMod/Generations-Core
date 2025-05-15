package generations.gg.generations.core.generationscore.common.world.container

import generations.gg.generations.core.generationscore.common.util.ExtendedsimpleItemContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.item.ItemStack

class TrashCanContainer(id: Int, arg: Inventory?) :
    SingleSlotContainer(GenerationsContainers.TRASHCAN.get(), id, object : ExtendedsimpleItemContainer(null, 1) {
        override fun setItem(slot: Int, stack: ItemStack) {
        }

        override fun getItem(i: Int): ItemStack {
            return ItemStack.EMPTY
        }

        override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
            return ItemStack.EMPTY
        }

        override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
            return ItemStack.EMPTY
        }
    }) {
    init {
        applyPlayerInventory(arg)
    }
}
