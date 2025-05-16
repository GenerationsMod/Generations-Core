package generations.gg.generations.core.generationscore.common.world.container.slots

import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot
import net.minecraft.world.item.ItemStack
import java.util.function.Predicate

class PredicateSlotItemHandler(
    itemHandler: CommonStorage<ItemResource>,
    index: Int,
    xPosition: Int,
    yPosition: Int,
    private val predicate: Predicate<ItemStack>,
) :
    MenuStorageSlot(itemHandler, index, xPosition, yPosition) {
    override fun mayPlace(stack: ItemStack): Boolean {
        return predicate.test(stack)
    }
}
