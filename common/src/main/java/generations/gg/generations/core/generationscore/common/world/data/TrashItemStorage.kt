package generations.gg.generations.core.generationscore.common.world.data

import net.minecraft.world.Container
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

object TrashItemStorage : Container {
    override fun clearContent() {}

    override fun getContainerSize(): Int = 1

    override fun isEmpty(): Boolean = true

    override fun getItem(slot: Int): ItemStack = ItemStack.EMPTY

    override fun removeItem(slot: Int, amount: Int): ItemStack = ItemStack.EMPTY

    override fun removeItemNoUpdate(slot: Int): ItemStack = ItemStack.EMPTY

    override fun setItem(slot: Int, stack: ItemStack) {}

    override fun setChanged() {}

    override fun stillValid(player: Player): Boolean = true
}
