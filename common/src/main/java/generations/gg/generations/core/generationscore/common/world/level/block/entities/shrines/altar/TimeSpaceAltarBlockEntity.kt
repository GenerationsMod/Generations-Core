package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar

import earth.terrarium.common_storage_lib.item.util.ItemProvider
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.util.ExtendedsimpleItemContainer
import generations.gg.generations.core.generationscore.common.util.asResource
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsItemComponents
import generations.gg.generations.core.generationscore.common.world.item.legends.CreationTrioItem
import generations.gg.generations.core.generationscore.common.world.item.legends.RedChainItem
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.InteractShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState

class TimeSpaceAltarBlockEntity(pos: BlockPos, state: BlockState) : InteractShrineBlockEntity(GenerationsBlockEntities.TIMESPACE_ALTAR.get(), pos, state), ItemProvider.BlockEntity, ModelContextProviders.VariantProvider {
    private var handler: TimeSpaceAltarItemStackHandler = TimeSpaceAltarItemStackHandler()

    override fun getVariant(): String {
        return if (container.hasRedChain()) "red chain" else "none"
    }

    val container: TimeSpaceAltarItemStackHandler
        get() = handler

    val orb: CreationTrioItem?
        get() = container.getResource(0).item.instanceOrNull<CreationTrioItem>()



    inner class TimeSpaceAltarItemStackHandler : ExtendedsimpleItemContainer(this@TimeSpaceAltarBlockEntity, 2) {
        override fun isResourceValid(index: Int, resource: ItemResource): Boolean {
            return if (index == 0) {
                resource.item is CreationTrioItem
            } else {
                resource.isOf(GenerationsItems.RED_CHAIN.get()) && resource.get(GenerationsItemComponents.ENCHANTED.get()) == false
            }
        }

        override fun getLimit(index: Int, resource: ItemResource?): Long {
            return 1
        }

        fun hasRedChain(): Boolean {
            return !this[1].isEmpty
        }

        fun hasOrb(player: ServerPlayer?): Boolean {
            val orb = this[0]

            return !orb.isEmpty && GenerationsCore.CONFIG.caught.capped(
                player,
                (orb.resource.item as CreationTrioItem).speciesId
            )
        }

        fun extract(index: Int, simulate: Boolean): ItemStack { //TODO: This probably really jank and I need to change this completley.
            val src = get(index);
            val stack = src.toItemStack()
            src.set(ItemStack.EMPTY)

            return stack
        }

        fun extractItem(): ItemStack {
            for (i in 0..1) if (!this[i].isEmpty) return extract(1, false)

            return ItemStack.EMPTY
        }

        fun shouldSpawn(player: ServerPlayer): Boolean {
            return hasRedChain() && hasOrb(player)
        }

        fun dumpAllIntoPlayerInventory(player: ServerPlayer) {
            val size = size();

            (0..size).map(::get).forEach { slot ->
                val stack = slot.toItemStack()
                slot.set(ItemStack.EMPTY)
                player.inventory.placeItemBackInInventory(stack)
            }
        }

        fun insertItem(index: Int, stack: ItemStack, simulate: Boolean): ItemStack {
            this.get(index).insert(stack.asResource(), stack.count.toLong(), simulate).run { stack.shrink(this.toInt()) }
            return stack
        }
    }

    override fun getItems(p0: Direction?): CommonStorage<ItemResource> {
        return handler;
    }
}
