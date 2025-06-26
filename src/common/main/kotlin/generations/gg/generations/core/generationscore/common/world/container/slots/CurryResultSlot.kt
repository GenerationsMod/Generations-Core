package generations.gg.generations.core.generationscore.common.world.container.slots

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import net.minecraft.world.Container
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import kotlin.math.min

class CurryResultSlot(
    private val player: Player,
    handler: Container,
    slotIndex: Int,
    xPosition: Int,
    yPosition: Int
) :
    Slot(handler, slotIndex, xPosition, yPosition) {
    private var removeCount = 0

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    override fun mayPlace(stack: ItemStack): Boolean {
        return false
    }

    override fun remove(amount: Int): ItemStack {
        if (this.hasItem()) {
            removeCount += min(amount, this.item.count)
        }
        return super.remove(amount)
    }

    override fun onTake(player: Player, stack: ItemStack) {
        this.checkTakeAchievements(player, stack)
        super.onTake(player, stack)
    }

    protected fun checkTakeAchievements(player: Player, stack: ItemStack) {
        stack.onCraftedBy(player.level(), player, this.removeCount)
        //TODO: Curries made stat?
        this.removeCount = 0
    }

    override fun setChanged() {
        super.setChanged()
    }
}
