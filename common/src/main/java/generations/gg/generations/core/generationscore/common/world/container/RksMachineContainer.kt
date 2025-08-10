package generations.gg.generations.core.generationscore.common.world.container

import generations.gg.generations.core.generationscore.common.client.asValue
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.Toggleable
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.inventory.SimpleContainerData
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeHolder
import kotlin.math.min

class RksMachineContainer @JvmOverloads constructor(
    id: Int,
    protected var playerInventory: Inventory,
    protected var rksMachine: Container = SimpleContainer(10),
    private val data: ContainerData = SimpleContainerData(4)
) : AbstractContainerMenu(GenerationsContainers.RKS_MACHINE.value(), id), Toggleable {
    init {
        rksMachine.instanceOrNull<RksMachineBlockEntity>()?.addMenu(this)

        this.addSlot(
            ResultSlot(
                playerInventory.player, rksMachine, 0, 124, 35
            )
        )

        for (i in 0..2) {
            for (j in 0..2) {
                this.addSlot(Slot(this.rksMachine, j + i * 3 + 1, 30 + j * 18, 17 + i * 18))
            }
        }

        for (i in 2 downTo 0) {
            for (j in 8 downTo 0) {
                this.addSlot(Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
            }
        }

// Player hotbar (reversed add order, same visual positions)
        for (i in 8 downTo 0) {
            this.addSlot(Slot(playerInventory, i, 8 + i * 18, 142))
        }

        this.addDataSlots(data)
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        val slot = slots.getOrNull(index) ?: return ItemStack.EMPTY
        if (!slot.hasItem()) return ItemStack.EMPTY

        val slotStack = slot.item
        val returnStack = slotStack.copy()

        // slot index layout
        val machineFirst = 0
        val machineLast = 9           // 0 result, 1-9 inputs
        val playerFirst = 10
        val playerLast = slots.lastIndex

        if (index == 0 && isPokemonPresent) {
            // special Pokémon output handling
            slot.onTake(player, returnStack)
            slot.set(ItemStack.EMPTY)
            return ItemStack.EMPTY
        }

        if (index in machineFirst..machineLast) {
            // machine -> player inventory
            if (!moveItemStackTo(slotStack, playerFirst, playerLast + 1, true)) {
                return ItemStack.EMPTY
            }
        } else {
            // player inventory -> machine inputs only (skip result slot)
            if (!moveItemStackTo(slotStack, 1, machineLast + 1, false)) {
                return ItemStack.EMPTY
            }
        }

        if (slotStack.isEmpty) {
            slot.set(ItemStack.EMPTY)
        } else {
            slot.setChanged()
        }

        if (slotStack.count == returnStack.count) {
            return ItemStack.EMPTY
        }

        slot.onTake(player, slotStack)
        return returnStack
    }


    override fun stillValid(player: Player): Boolean {
        return rksMachine.stillValid(player)
    }

    fun getBurnProgress(pixels: Int): Int {
        val i = data[0]
        val j = data[1]
        return if (j != 0 && i != 0) i * pixels / j else 0
    }

    val isWeaving: Boolean
        get() = data[0] > 0

    override var isToggled: Boolean
        get() = data[2] == 1
        set(value) { setData(2, if (value) 1 else 0) }

    val isPokemonPresent: Boolean
        get() = data[3] == 1


    override fun removed(player: Player) {
        super.removed(player)

        rksMachine.instanceOrNull<RksMachineBlockEntity>()?.removeMenu(this)
    }

    fun close() {
    }

    class ResultSlot(private val player: Player, rksMachineBlockEntity: Container, slot: Int, x: Int, y: Int) :
        Slot(rksMachineBlockEntity, slot, x, y) {
        private var removeCount = 0

        override fun mayPlace(stack: ItemStack): Boolean {
            return false
        }

        override fun remove(amount: Int): ItemStack {
            if (this.hasItem()) {
                removeCount += min(
                    amount,
                    this.item.count
                )
            }

            return super.remove(amount)
        }

        override fun onTake(player: Player, stack: ItemStack) {
            container.instanceOrNull<RksMachineBlockEntity>()?.run {
                lastRecipe.instanceOrNull<RecipeHolder<RksRecipe>>()?.value?.process(
                    player,
                    this,
                    stack
                )
            }
            this.checkTakeAchievements(stack)
            super.onTake(player, stack)
        }

        override fun checkTakeAchievements(stack: ItemStack) {
            stack.onCraftedBy(player.level(), this.player, this.removeCount)
            if (player is ServerPlayer && container is RksMachineBlockEntity) (container as RksMachineBlockEntity).awardUsedRecipesAndPopExperience(
                player
            )
        }
    }

    companion object {
        const val INPUT1_SLOT: Int = 0
        const val INPUT2_SLOT: Int = 1
        const val INPUT3_SLOT: Int = 2
        const val OUTPUT_SLOT: Int = 3

        const val DATA_WEAVE_TIME: Int = 0
        const val DATA_WEAVE_TIME_TOAL: Int = 1
        const val NUM_DATA_VALUES: Int = 2
    }


}
