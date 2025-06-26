package generations.gg.generations.core.generationscore.common.world.data

import earth.terrarium.common_storage_lib.context.ItemContext
import earth.terrarium.common_storage_lib.data.DataManager
import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot
import earth.terrarium.common_storage_lib.item.util.ItemStorageData
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.base.StorageSlot
import earth.terrarium.common_storage_lib.storage.base.UpdateManager
import earth.terrarium.common_storage_lib.storage.util.ModifiableItemSlot
import earth.terrarium.common_storage_lib.storage.util.TransferUtil
import net.minecraft.core.NonNullList
import net.minecraft.core.component.DataComponentType
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import java.util.*
import java.util.function.Predicate

object TrashItemStorage : CommonStorage<ItemResource>, UpdateManager<ItemStorageData> {
    val slot: StorageSlot<ItemResource> = object : StorageSlot<ItemResource>, ModifiableItemSlot {
        override fun insert(resource: ItemResource, count: Long, simulate: Boolean): Long = count

        override fun extract(resource: ItemResource, count: Long, simulate: Boolean): Long = 0

        override fun getLimit(resource: ItemResource): Long = 64

        override fun getResource(): ItemResource = ItemResource.BLANK

        override fun getAmount(): Long = 0

        override fun isResourceValid(resource: ItemResource): Boolean = true

        override fun setAmount(count: Long) {}

        override fun setResource(resource: ItemResource) {}

        override fun toItemStack(): ItemStack = ItemStack.EMPTY

        override fun getMaxAllowed(resource: ItemResource): Int = 64

        override fun isEmpty(): Boolean = true

    }

    override fun size(): Int {
        return 1
    }

    override fun get(index: Int): StorageSlot<ItemResource> {
        return slot
    }

    override fun createSnapshot(): ItemStorageData {
        return ItemStorageData.of(this)
    }

    override fun readSnapshot(snapshot: ItemStorageData) {}

    override fun update() {}

    override fun insert(resource: ItemResource, amount: Long, simulate: Boolean): Long {
        return TransferUtil.insertSlots(this, resource, amount, simulate)
    }

    override fun extract(resource: ItemResource, amount: Long, simulate: Boolean): Long {
        return TransferUtil.extractSlots(this, resource, amount, simulate)
    }
}
