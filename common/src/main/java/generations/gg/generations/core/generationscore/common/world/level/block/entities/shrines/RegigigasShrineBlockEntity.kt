package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import dev.architectury.registry.registries.RegistrySupplier
import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot
import earth.terrarium.common_storage_lib.item.util.ItemProvider
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.util.ExtendedsimpleItemContainer
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.legends.RegiOrbItem
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import java.util.*

class RegigigasShrineBlockEntity(pos: BlockPos, state: BlockState) : InteractShrineBlockEntity(GenerationsBlockEntities.REGIGIGAS_SHRINE.get(), pos, state), ItemProvider.Block {
    val container: RegigigasItemStackHandler

    init {
        container = RegigigasItemStackHandler()
    }

    override fun getItems(
        level: Level,
        blockPos: BlockPos,
        blockState: BlockState?,
        blockEntity: BlockEntity?,
        direction: Direction?
    ): CommonStorage<ItemResource> {
        return container
    }

    private fun getRegiItem(index: Int): RegiOrbItem? {
        return when (index) {
                    0 -> GenerationsItems.REGICE_ORB
                    1 -> GenerationsItems.REGIROCK_ORB
                    2 -> GenerationsItems.REGISTEEL_ORB
                    3 -> GenerationsItems.REGIDRAGO_ORB
                    4 -> GenerationsItems.REGIELEKI_ORB
                    else -> null
                }?.get().instanceOrNull<RegiOrbItem>()
    }

    inner class RegigigasItemStackHandler :
        ExtendedsimpleItemContainer(this@RegigigasShrineBlockEntity, 5) {
        override fun isResourceValid(index: Int, resource: ItemResource): Boolean {
            resource.item.instanceOrNull<RegiOrbItem>().run {
                return@run getRegiItem(index) == this
            }

            return false
        }

        override fun getLimit(index: Int, resource: ItemResource): Long {
            return 1
        }

        val isFull: Boolean
            get() = this.slots.map(SimpleItemSlot::getResource).none(ItemResource::isBlank)

        fun clear() { //TODO: work on
//            getItems().clear();
//            setChanged();
        }

        fun contains(item: Item): Boolean = this.slots.map { obj -> obj.resource }.any { stack: ItemResource -> stack.isOf(item) }
    } //    @Override
    //    public @NotNull CompoundTag getUpdateTag() {
    //        return getContainer().serialize(super.getUpdateTag());
    //    }

    companion object {
        @JvmStatic
        fun getRegiOrbIndex(item: RegiOrbItem): Int {
            return when (item.speciesId) {
                "regice" -> 0
                "regirock" -> 1
                "registeel" -> 2
                "regidrago" -> 3
                "regieleki" -> 4
                else -> -1
            }
        }
    }
}
