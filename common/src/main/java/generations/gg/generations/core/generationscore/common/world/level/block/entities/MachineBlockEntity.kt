package generations.gg.generations.core.generationscore.common.world.level.block.entities

import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot
import earth.terrarium.common_storage_lib.item.util.ItemProvider
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import generations.gg.generations.core.generationscore.common.util.ExtendedsimpleItemContainer
import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers.CreationContext
import generations.gg.generations.core.generationscore.common.world.container.MachineBlockContainer
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import java.util.stream.IntStream

class MachineBlockEntity(pos: BlockPos, state: BlockState) :
    SimpleBlockEntity(GenerationsBlockEntities.MACHINE_BLOCK.get(), pos, state), ItemProvider.BlockEntity,
    MenuProvider {
    @JvmField
    val candies: MachineBlockItemStackHandler = MachineBlockItemStackHandler()

    var bakeTime: Int = 0
        private set

    override fun getDisplayName(): Component {
        return Component.translatable("machine_block")
    }

    override fun getItems(direction: Direction?): CommonStorage<ItemResource> {
        return candies
    }

    override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        bakeTime = nbt.getInt("bakeTime")
    }

    override fun saveAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(nbt, provider)
        nbt.putInt("bakeTime", bakeTime)
    }

    fun tick() {
        candies.locked = candies.isFull

        if (candies.locked) {
            if (bakeTime < 100) {
                bakeTime++
            }
        } else {
            bakeTime = 0
        }

        sync()
    }

    override fun createMenu(i: Int, arg: Inventory, arg2: Player): AbstractContainerMenu? {
        return MachineBlockContainer(CreationContext(i, arg, this))
    }

    inner class MachineBlockItemStackHandler : ExtendedsimpleItemContainer(this@MachineBlockEntity, 18) {
        var locked: Boolean = false
        override fun insert(index: Int, resource: ItemResource, amount: Long, simulate: Boolean): Long {
            if (locked) return 0
            return super.insert(index, resource, amount, simulate)
        }

        override fun extract(index: Int, resource: ItemResource, amount: Long, simulate: Boolean): Long {
            if (locked) return 0
            return super.extract(index, resource, amount, simulate)
        }


        val isFull: Boolean
            get() = IntStream.range(0, this.size())
                .mapToObj { index: Int ->
                    candies[index]
                }
                .allMatch { count: SimpleItemSlot -> count.isEmpty }
    }

    companion object {
        @JvmStatic
        fun serverTick(level: Level?, pos: BlockPos?, state1: BlockState?, blockEntity: MachineBlockEntity) {
            blockEntity.tick()
        }
    }
}
