package generations.gg.generations.core.generationscore.common.world.container.slots

import net.minecraft.world.Container
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import java.util.function.Predicate

class PredicateSlotItemHandler(
    itemHandler: Container,
    index: Int,
    xPosition: Int,
    yPosition: Int,
    private val predicate: Predicate<ItemStack>,
) : Slot(itemHandler, index, xPosition, yPosition) {
    override fun mayPlace(stack: ItemStack): Boolean {
        return predicate.test(stack)
    }
}
