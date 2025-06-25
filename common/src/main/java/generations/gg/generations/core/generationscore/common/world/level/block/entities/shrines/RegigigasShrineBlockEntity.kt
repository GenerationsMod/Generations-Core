package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.components.SimpleContainer
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.RegigigasShrineBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.NonNullList
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState

class RegigigasShrineBlockEntity(pos: BlockPos, state: BlockState) : InteractShrineBlockEntity(GenerationsBlockEntities.REGIGIGAS_SHRINE, pos, state) {
    val container = RegigigasContainer()

//        .filter(0) { it.isOf(GenerationsItems.REGICE_ORB.value()) }
//        .filter(1) { it.isOf(GenerationsItems.REGIROCK_ORB.value()) }
//        .filter(2) { it.isOf(GenerationsItems.REGISTEEL_ORB.value()) }
//        .filter(3) { it.isOf(GenerationsItems.REGIDRAGO_ORB.value()) }
//        .filter(4) { it.isOf(GenerationsItems.REGIELEKI_ORB.value()) }

//    val container: NonNullList<ItemStack> = NonNullList.withSize(5, ItemStack.EMPTY)
}

class RegigigasContainer: SimpleContainer(5) {
    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
        return when(slot) {
            0 -> stack.`is`(GenerationsItems.REGICE_ORB)
            1 -> stack.`is`(GenerationsItems.REGIROCK_ORB)
            2 -> stack.`is`(GenerationsItems.REGISTEEL_ORB)
            3 -> stack.`is`(GenerationsItems.REGIDRAGO_ORB)
            4 -> stack.`is`(GenerationsItems.REGIELEKI_ORB)
            else -> false
        }
    }

    fun isFull(): Boolean = (0..< containerSize).map(this.items::get).none { it.isEmpty }
}
