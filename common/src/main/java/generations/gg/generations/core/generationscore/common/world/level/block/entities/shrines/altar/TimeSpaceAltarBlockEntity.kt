package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar

import earth.terrarium.common_storage_lib.item.impl.vanilla.VanillaDelegatingSlot
import earth.terrarium.common_storage_lib.item.impl.vanilla.WrappedVanillaContainer
import earth.terrarium.common_storage_lib.item.util.ItemProvider
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.util.ExtendedsimpleItemContainer
import generations.gg.generations.core.generationscore.common.util.asResource
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import generations.gg.generations.core.generationscore.common.world.item.legends.CreationTrioItem
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.InteractShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState

class TimeSpaceAltarBlockEntity(pos: BlockPos, state: BlockState) : InteractShrineBlockEntity(GenerationsBlockEntities.TIMESPACE_ALTAR, pos, state), ItemProvider.BlockEntity, ModelContextProviders.VariantProvider {
    private var handler: TimeSpaceAltarItemStackHandler = TimeSpaceAltarItemStackHandler()

    override fun getVariant(): String {
        return if (container.hasRedChain()) "red chain" else "none"
    }

    val container: TimeSpaceAltarItemStackHandler
        get() = handler

    val orb: CreationTrioItem?
        get() = container.getItem(0).item.instanceOrNull<CreationTrioItem>()



    inner class TimeSpaceAltarItemStackHandler : ExtendedsimpleItemContainer(2) {
        override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
            return if (slot == 0) {
                stack.item is CreationTrioItem
            } else {
                stack.`is`(GenerationsItems.RED_CHAIN.value()) && stack.get(GenerationsDataComponents.ENCHANTED.value()) == false
            }
        }

        override fun getMaxStackSize(): Int {
            return 1
        }


        fun hasRedChain(): Boolean {
            return !this.getItem(1).isEmpty
        }

        fun hasOrb(player: ServerPlayer?): Boolean {
            val orb = this.getItem(0)

            return !orb.isEmpty && GenerationsCore.CONFIG!!.caught.capped(
                player,
                (orb.item as CreationTrioItem).speciesId
            )
        }

        fun extract(index: Int, simulate: Boolean): ItemStack { //TODO: This probably really jank and I need to change this completley.
            val src = getItem(index);
            val stack = src
            setItem(index, ItemStack.EMPTY)

            return stack
        }

        fun extractItem(): ItemStack {
            for (i in 0..1) if (!this.getItem(i).isEmpty) return extract(1, false)

            return ItemStack.EMPTY
        }

        fun shouldSpawn(player: ServerPlayer): Boolean {
            return hasRedChain() && hasOrb(player)
        }

        fun dumpAllIntoPlayerInventory(player: ServerPlayer) {
            val size = containerSize;

            (0..size).forEach { slot ->
                val stack = getItem(slot)
                setItem(slot, ItemStack.EMPTY)
                player.inventory.placeItemBackInInventory(stack)
            }
        }

        fun insertItem(index: Int, stack: ItemStack, simulate: Boolean): ItemStack {
            this.addItem(stack).run { stack.shrink(this.count) }
            return stack
        }
    }

    override fun getItems(p0: Direction?): CommonStorage<ItemResource> {
        return WrappedVanillaContainer(handler);
    }
}
