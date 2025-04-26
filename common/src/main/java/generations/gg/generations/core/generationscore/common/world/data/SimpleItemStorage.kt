package generations.gg.generations.core.generationscore.common.world.data

import earth.terrarium.common_storage_lib.context.ItemContext
import earth.terrarium.common_storage_lib.data.DataManager
import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot
import earth.terrarium.common_storage_lib.item.util.ItemStorageData
import earth.terrarium.common_storage_lib.resources.ResourceStack
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.base.UpdateManager
import earth.terrarium.common_storage_lib.storage.util.TransferUtil
import net.minecraft.core.NonNullList
import net.minecraft.core.component.DataComponentType
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.block.entity.BlockEntity
import java.util.*
import java.util.function.Predicate

class SimpleItemStorage : CommonStorage<ItemResource>, UpdateManager<ItemStorageData> {
    protected val slots: NonNullList<SimpleItemSlot>
    private val onUpdate: Runnable
    private val save: Runnable

    constructor(size: Int) {
        this.slots = NonNullList.withSize(size, SimpleItemSlot { this.update() })
        this.onUpdate = Runnable {}
        this.save = Runnable {}
    }

    constructor(context: ItemContext, componentType: DataComponentType<ItemStorageData>, size: Int) {
        this.slots = NonNullList.withSize(size, SimpleItemSlot { this.update() })
        Objects.requireNonNull(context)
        this.onUpdate = Runnable { context.updateAll() }
        this.save = Runnable {
            context.set(
                componentType, ItemStorageData.of(
                    this
                )
            )
        }
        if (context.resource.has(componentType)) {
            this.readSnapshot(context.resource.get(componentType)!!)
        }
    }

    constructor(entityOrBlockEntity: BlockEntity, dataManager: DataManager<ItemStorageData>, size: Int) {
        this.slots = NonNullList.withSize(size, SimpleItemSlot { this.update() })
        this.onUpdate = Runnable {
            dataManager[entityOrBlockEntity] = ItemStorageData.of(this)
        }
        this.save = Runnable {}
        this.readSnapshot((dataManager[entityOrBlockEntity] as ItemStorageData))
    }

    constructor(entityOrBlockEntity: Entity, dataManager: DataManager<ItemStorageData>, size: Int) {
        this.slots = NonNullList.withSize(size, SimpleItemSlot { this.update() })
        this.onUpdate = Runnable {
            dataManager[entityOrBlockEntity] = ItemStorageData.of(this)
        }
        this.save = Runnable {}
        this.readSnapshot((dataManager[entityOrBlockEntity] as ItemStorageData))
    }

    fun filter(slot: Int, predicate: Predicate<ItemResource?>?): SimpleItemStorage {
        slots[slot] =
            SimpleItemSlot.Filtered(
                { this.update() },
                predicate
            )
        return this
    }

    override fun size(): Int {
        return slots.size
    }

    override fun get(index: Int): SimpleItemSlot {
        return slots[index]
    }

    override fun createSnapshot(): ItemStorageData {
        return ItemStorageData.of(this)
    }

    override fun readSnapshot(snapshot: ItemStorageData) {
        var i = 0
        while (i < slots.size && i < snapshot.stacks().size) {
            slots[i].readSnapshot(snapshot.stacks()[i])
            ++i
        }
    }

    override fun update() {
        onUpdate.run()
    }

    override fun insert(resource: ItemResource?, amount: Long, simulate: Boolean): Long {
        return TransferUtil.insertSlots(this, resource, amount, simulate)
    }

    override fun extract(resource: ItemResource?, amount: Long, simulate: Boolean): Long {
        return TransferUtil.extractSlots(this, resource, amount, simulate)
    }
}
