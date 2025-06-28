package generations.gg.generations.core.generationscore.common.world.level.block.entities


import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.WorldlyContainer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState

class TrashCanBlockEntity(pos: BlockPos, state: BlockState) : ModelProvidingBlockEntity(GenerationsBlockEntities.TRASH_CAN, pos, state), WorldlyContainer {
    override fun clearContent() {}

    override fun getContainerSize(): Int = 1

    override fun isEmpty(): Boolean = true

    override fun getItem(slot: Int): ItemStack = ItemStack.EMPTY

    override fun removeItem(slot: Int, amount: Int) = ItemStack.EMPTY

    override fun removeItemNoUpdate(slot: Int): ItemStack = ItemStack.EMPTY

    override fun setItem(slot: Int, stack: ItemStack) {}

    override fun stillValid(player: Player): Boolean = true

    override fun getSlotsForFace(side: Direction): IntArray {
        return if(side == Direction.UP) IntArray(1) { 0 } else IntArray(0)
    }

    override fun canPlaceItemThroughFace(index: Int, itemStack: ItemStack, direction: Direction?): Boolean = direction == Direction.UP

    override fun canTakeItemThroughFace(index: Int, stack: ItemStack, direction: Direction): Boolean = false
}
