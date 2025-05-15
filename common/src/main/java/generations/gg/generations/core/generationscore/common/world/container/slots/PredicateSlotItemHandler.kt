package generations.gg.generations.core.generationscore.common.world.container.slots

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot
import net.minecraft.world.item.ItemStack
import java.util.function.Predicate

class PredicateSlotItemHandler(
    itemHandler: SimpleItemStorage,
    index: Int,
    xPosition: Int,
    yPosition: Int,
    private val predicate: Predicate<ItemStack>
) :
    MenuStorageSlot(itemHandler, index, xPosition, yPosition) {
    override fun mayPlace(stack: ItemStack): Boolean {
        return predicate.test(stack)
    }
}
