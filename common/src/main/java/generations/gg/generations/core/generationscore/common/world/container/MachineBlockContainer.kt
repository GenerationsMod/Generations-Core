package generations.gg.generations.core.generationscore.common.world.container

import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers.CreationContext
import generations.gg.generations.core.generationscore.common.world.container.slots.TypeSlot
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MachineBlockEntity
import net.minecraft.world.Container
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

class MachineBlockContainer(ctx: CreationContext<MachineBlockEntity>) :
    AbstractContainerMenu(GenerationsContainers.MACHINE_BLOCK, ctx.id) {
    private val be = ctx.blockEntity!!

    init {
        addSlot(TypeSlot(be.candies, 0, 92, 9, GenerationsItems.BUG_CANDY, "bug") { this.bakeTime })
        addSlot(TypeSlot(be.candies, 1, 114, 17, GenerationsItems.DARK_CANDY, "dark") { this.bakeTime })
        addSlot(TypeSlot(
            be.candies, 2, 136, 34, GenerationsItems.DRAGON_CANDY, "dragon"
        ) { this.bakeTime })
        addSlot(TypeSlot(
            be.candies, 3, 148, 55, GenerationsItems.ELECTRIC_CANDY, "electric"
        ) { this.bakeTime })
        addSlot(TypeSlot(be.candies, 4, 152, 80, GenerationsItems.FAIRY_CANDY, "fairy") { this.bakeTime })
        addSlot(TypeSlot(
            be.candies, 5, 148, 105, GenerationsItems.FIGHTING_CANDY, "fighting"
        ) { this.bakeTime })
        addSlot(TypeSlot(be.candies, 6, 136, 127, GenerationsItems.FIRE_CANDY, "fire") { this.bakeTime })
        addSlot(TypeSlot(
            be.candies, 7, 114, 143, GenerationsItems.FLYING_CANDY, "flying"
        ) { this.bakeTime })
        addSlot(TypeSlot(be.candies, 8, 92, 151, GenerationsItems.GHOST_CANDY, "ghost") { this.bakeTime })
        addSlot(TypeSlot(be.candies, 9, 68, 151, GenerationsItems.GRASS_CANDY, "grass") { this.bakeTime })
        addSlot(TypeSlot(
            be.candies, 10, 46, 143, GenerationsItems.GROUND_CANDY, "ground"
        ) { this.bakeTime })
        addSlot(TypeSlot(be.candies, 11, 24, 127, GenerationsItems.ICE_CANDY, "ice") { this.bakeTime })
        addSlot(TypeSlot(
            be.candies, 12, 12, 105, GenerationsItems.NORMAL_CANDY, "normal"
        ) { this.bakeTime })
        addSlot(TypeSlot(be.candies, 13, 8, 80, GenerationsItems.POISON_CANDY, "poison") { this.bakeTime })
        addSlot(TypeSlot(
            be.candies, 14, 12, 55, GenerationsItems.PSYCHIC_CANDY, "psychic"
        ) { this.bakeTime })
        addSlot(TypeSlot(be.candies, 15, 24, 33, GenerationsItems.ROCK_CANDY, "rock") { this.bakeTime })
        addSlot(TypeSlot(be.candies, 16, 46, 17, GenerationsItems.STEEL_CANDY, "steel") { this.bakeTime })
        addSlot(TypeSlot(be.candies, 17, 68, 9, GenerationsItems.WATER_CANDY, "water") { this.bakeTime })

        //        addSlot(new NullCandySlot((SimpleItemStorage) be.getCandies(), 80, 80, GenerationsItems.NULL_CANDY, this::getBakeTime));
        populate(ctx.playerInv, 173, 1, 3)
        populate(ctx.playerInv, 231, 0, 1)
    }

    private fun populate(container: Container, y: Int, startingRow: Int, rows: Int) {
        for (row in 0 until rows) {
            for (column in 0..8) {
                this.addSlot(Slot(container, column + (startingRow + row) * 9, 8 + column * 18, y + row * 18))
            }
        }
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        return ItemStack.EMPTY
    }

    override fun stillValid(player: Player): Boolean {
        return true
    }

    val bakeTime: Double
        get() = be.bakeTime / 100.0
}
