package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic

import earth.terrarium.common_storage_lib.item.impl.vanilla.WrappedVanillaContainer
import generations.gg.generations.core.generationscore.common.world.container.GenericChestContainer
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.ContainerHelper
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.ChestLidController
import net.minecraft.world.level.block.entity.ContainerOpenersCounter
import net.minecraft.world.level.block.entity.LidBlockEntity
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity
import net.minecraft.world.level.block.state.BlockState

@Suppress("deprecation")
class GenericChestBlockEntity @JvmOverloads constructor(
    arg: BlockPos,
    arg2: BlockState,
    private var width: Int = 9,
    private var height: Int = 1,
    private var defaultTranslation: String = "container.chest"
) : RandomizableContainerBlockEntity(GenerationsBlockEntities.GENERIC_CHEST.value(), arg, arg2), LidBlockEntity {
    private val openersCounter: ContainerOpenersCounter = object : ContainerOpenersCounter() {
        override fun onOpen(level: Level, pos: BlockPos, state: BlockState) {
            this@GenericChestBlockEntity.playSound(state, SoundEvents.CHEST_OPEN)
        }

        override fun onClose(level: Level, pos: BlockPos, state: BlockState) {
            this@GenericChestBlockEntity.playSound(state, SoundEvents.CHEST_CLOSE)
        }

        override fun openerCountChanged(level: Level, pos: BlockPos, state: BlockState, count: Int, openCount: Int) {
            this@GenericChestBlockEntity.signalOpenCount(level, pos, state, count, openCount)
        }

        override fun isOwnContainer(player: Player): Boolean {
            if (player.containerMenu is GenericChestContainer<*>) {
                val container = (player.containerMenu as GenericChestContainer<*>).getContainer()
                return container === this@GenericChestBlockEntity.items
            }
            return false
        }
    }

    private val chestLidController = ChestLidController()

    private var items: NonNullList<ItemStack> = NonNullList.withSize(width * height, ItemStack.EMPTY)

    override fun getContainerSize(): Int {
        return width * height
    }

    override fun getDefaultName(): Component {
        return Component.translatable(defaultTranslation)
    }

    public override fun loadAdditional(tag: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(tag, provider)
        this.width = tag.getInt("width")
        this.height = tag.getInt("height")
        this.defaultTranslation = tag.getString("defaultTranslation")
        this.items = NonNullList.withSize(this.containerSize, ItemStack.EMPTY)
        if (!this.tryLoadLootTable(tag)) ContainerHelper.loadAllItems(tag, this.items, provider)
    }

    override fun saveAdditional(tag: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(tag, provider)
        if (!this.trySaveLootTable(tag)) ContainerHelper.saveAllItems(tag, this.items, provider)
        tag.putInt("width", width)
        tag.putInt("height", height)
        tag.putString("defaultTranslation", defaultTranslation)
    }

    fun playSound(state: BlockState, sound: SoundEvent) {
        val vec3i = state.getValue(GenericChestBlock.FACING).normal
        val d = worldPosition.x.toDouble() + 0.5 + vec3i.x.toDouble() / 2.0
        val e = worldPosition.y.toDouble() + 0.5 + vec3i.y.toDouble() / 2.0
        val f = worldPosition.z.toDouble() + 0.5 + vec3i.z.toDouble() / 2.0
        checkNotNull(this.level)
        level!!.playSound(
            null, d, e, f, sound, SoundSource.BLOCKS, 0.5f,
            level!!.random.nextFloat() * 0.1f + 0.9f
        )
    }

    override fun triggerEvent(id: Int, type: Int): Boolean {
        if (id == 1) {
            chestLidController.shouldBeOpen(type > 0)
            return true
        }
        return super.triggerEvent(id, type)
    }


    override fun startOpen(player: Player) {
        if (!this.remove && !player.isSpectator) openersCounter.incrementOpeners(
            player, this.getLevel(), this.blockPos, this.blockState
        )
    }

    override fun stopOpen(player: Player) {
        if (!this.remove && !player.isSpectator) openersCounter.decrementOpeners(
            player, this.getLevel(), this.blockPos, this.blockState
        )
    }

    override fun getItems(): NonNullList<ItemStack> {
        return items
    }

//    override fun getItem(index: Int): ItemStack {
//        return if (index in 0 until items.size) items[index] else ItemStack.EMPTY
//    }

    override fun setItems(itemStacks: NonNullList<ItemStack>) {
        this.items = itemStacks
    }

    override fun getOpenNess(partialTicks: Float): Float {
        return chestLidController.getOpenness(partialTicks)
    }

    override fun createMenu(containerId: Int, inventory: Inventory): AbstractContainerMenu {
        return GenericChestContainer(containerId, inventory, WrappedVanillaContainer(this), width, height)
    }

    override fun setBlockState(blockState: BlockState) {
        super.setBlockState(blockState)
    }

    fun recheckOpen() {
        if (!this.remove) openersCounter.recheckOpeners(
            this.getLevel(),
            this.blockPos,
            this.blockState
        )
    }

    protected fun signalOpenCount(level: Level, pos: BlockPos, state: BlockState, eventId: Int, eventParam: Int) {
        level.blockEvent(pos, state.block, 1, eventParam)
    }

    //    @Override
    //    public int getHeight() {
    //        return height;
    //    }
    //
    //    @Override
    //    public int getWidth() {
    //        return width;
    //    }
    fun openScreen(player: Player) {
        GenericContainer.openScreen(WrappedVanillaContainer(this), width, height, components().get(DataComponents.CUSTOM_NAME) ?: Component.literal("Chest"), player)
    }

    companion object {
        private const val EVENT_SET_OPEN_COUNT = 1
        fun lidAnimateTick(level: Level?, pos: BlockPos?, state: BlockState?, blockEntity: GenericChestBlockEntity) {
            blockEntity.chestLidController.tickLid()
        }
    }
}
