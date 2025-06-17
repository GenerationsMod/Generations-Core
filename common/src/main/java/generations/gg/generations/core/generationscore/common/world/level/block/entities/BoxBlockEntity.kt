package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.world.level.block.asValue
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.BoxBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundSource
import net.minecraft.world.ContainerHelper
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.ContainerOpenersCounter
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity
import net.minecraft.world.level.block.state.BlockState

class BoxBlockEntity(pos: BlockPos, blockState: BlockState) :
    RandomizableContainerBlockEntity(GenerationsBlockEntities.BOX.asValue<BoxBlockEntity>(), pos, blockState) {
    private var items: NonNullList<ItemStack> = NonNullList.withSize(27, ItemStack.EMPTY)
    private val openersCounter: ContainerOpenersCounter = object : ContainerOpenersCounter() {
        override fun onOpen(level: Level, pos: BlockPos, state: BlockState) {
        }

        override fun onClose(level: Level, pos: BlockPos, state: BlockState) {
        }

        override fun openerCountChanged(level: Level, pos: BlockPos, state: BlockState, count: Int, openCount: Int) {
        }

        override fun isOwnContainer(player: Player): Boolean {
            if (player.containerMenu is ChestMenu) {
                val container = (player.containerMenu as ChestMenu).container
                return container === this@BoxBlockEntity
            }
            return false
        }
    }

    override fun saveAdditional(tag: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(tag, provider)
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items, provider)
        }
    }

    public override fun loadAdditional(tag: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(tag, provider)
        this.items = NonNullList.withSize(this.containerSize, ItemStack.EMPTY)
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items, provider)
        }
    }

    override fun getContainerSize(): Int {
        return 27
    }

    override fun getItems(): NonNullList<ItemStack> {
        return this.items
    }

    override fun setItems(itemStacks: NonNullList<ItemStack>) {
        this.items = itemStacks
    }

    override fun getDefaultName(): Component {
        return Component.translatable("container.box")
    }

    override fun createMenu(containerId: Int, inventory: Inventory): AbstractContainerMenu {
        return ChestMenu.threeRows(containerId, inventory, this)
    }

    override fun startOpen(player: Player) {
        if (!this.remove && !player.isSpectator) {
            openersCounter.incrementOpeners(player, this.getLevel(), this.blockPos, this.blockState)
        }
    }

    override fun stopOpen(player: Player) {
        if (!this.remove && !player.isSpectator) {
            openersCounter.decrementOpeners(player, this.getLevel(), this.blockPos, this.blockState)
        }
    }

    fun recheckOpen() {
        if (!this.remove) {
            openersCounter.recheckOpeners(this.getLevel(), this.blockPos, this.blockState)
        }
    }

    fun updateBlockState(state: BlockState, open: Boolean) {
        level!!.setBlock(this.blockPos, state.setValue(BoxBlock.OPEN, open), 3)
    }

    fun playSound(state: BlockState, sound: SoundEvent) {
        val vec3i = state.getValue(BoxBlock.FACING).normal
        val d = worldPosition.x.toDouble() + 0.5 + vec3i.x.toDouble() / 2.0
        val e = worldPosition.y.toDouble() + 0.5 + vec3i.y.toDouble() / 2.0
        val f = worldPosition.z.toDouble() + 0.5 + vec3i.z.toDouble() / 2.0
        level!!.playSound(
            null, d, e, f, sound, SoundSource.BLOCKS, 0.5f,
            level!!.random.nextFloat() * 0.1f + 0.9f
        )
    }
}


