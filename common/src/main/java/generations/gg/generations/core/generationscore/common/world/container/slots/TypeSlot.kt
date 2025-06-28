package generations.gg.generations.core.generationscore.common.world.container.slots

import com.mojang.datafixers.util.Pair
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.Container
import net.minecraft.world.inventory.InventoryMenu
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.util.function.DoubleSupplier

//Keeping around even with the defunct machine block because you never know when a type themeed slot would be useful
class TypeSlot(
    itemHandler: Container,
    index: Int,
    xPosition: Int,
    yPosition: Int,
    private val candy: Item,
    elementName: String,
    private val supplier: DoubleSupplier
) :
    Slot(itemHandler, index, xPosition, yPosition) {
    private val pair =
        Pair(
            InventoryMenu.BLOCK_ATLAS, id(
                "type_slots/$elementName"
            )
        )
    var originalX: Int = 0
    var originalY: Int = 0

    override fun isActive(): Boolean {
        return supplier.asDouble < 1.0f
    }

    override fun mayPlace(stack: ItemStack): Boolean {
        return stack.`is`(candy)
    }

    override fun getNoItemIcon(): Pair<ResourceLocation, ResourceLocation>? {
        return pair
    }

    companion object {
        @JvmStatic
        fun interpolateVectors(slot: TypeSlot, x1: Int, y1: Int, x2: Int, y2: Int, t: Double) {
            slot.x = Math.round((slot.originalX + (x2 - slot.originalX) * t).toFloat())
            slot.y = Math.round((slot.originalY + (y2 - slot.originalY) * t).toFloat())
        }
    }
}
