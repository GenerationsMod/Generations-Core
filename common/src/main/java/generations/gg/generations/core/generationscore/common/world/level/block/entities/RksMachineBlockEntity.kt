package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.server
import com.google.common.collect.Lists
import generations.gg.generations.core.generationscore.common.recipe.RksInput
import generations.gg.generations.core.generationscore.common.world.container.RksMachineContainer
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsCoreRecipeTypes
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.world.Container
import net.minecraft.world.ContainerHelper
import net.minecraft.world.Containers
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.ExperienceOrb
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.item.crafting.RecipeManager
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import java.util.*
import java.util.function.Consumer

open class RksMachineBlockEntity(pos: BlockPos, state: BlockState) :
    ModelProvidingBlockEntity(GenerationsBlockEntities.RKS_MACHINE, pos, state), MenuProvider,
    Container, Toggleable {
    private val dataAccess: ContainerData

    var processingTime: Int = 0
    var processTimeTotal: Int = 0
    var pokemon: Optional<Pokemon> = Optional.empty()

    private var isProcessing = false

    var inventory: NonNullList<ItemStack> = NonNullList.withSize(
            9,
            ItemStack.EMPTY
        )
    var output: ItemStack = ItemStack.EMPTY
    var lastRecipe: RecipeHolder<RksRecipe>? = null
        private set
    private val openContainers: MutableList<RksMachineContainer> = ArrayList()

    private val recipesUsed = Object2IntOpenHashMap<ResourceLocation>()

    init {
        this.dataAccess = object : ContainerData {
            override fun get(index: Int): Int {
                return when (index) {
                    0 -> this@RksMachineBlockEntity.processingTime
                    1 -> this@RksMachineBlockEntity.processTimeTotal
                    2 -> if (this@RksMachineBlockEntity.isProcessing) 1 else 0
                    3 -> if (pokemon.isPresent) 1 else 0
                    else -> 0
                }
            }

            override fun set(index: Int, value: Int) {
                when (index) {
                    2 -> this@RksMachineBlockEntity.isProcessing = value == 1
                }
                this@RksMachineBlockEntity.setChanged()
            }

            override fun getCount(): Int {
                return 4
            }
        }
    }

    override fun saveAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(nbt, provider)
        val inventoryTag = CompoundTag()
        ContainerHelper.saveAllItems(inventoryTag, inventory, provider)
        if (!output.isEmpty) inventoryTag.put("Output", output.save(provider))
        nbt.put(INVENTORY_TAG, inventoryTag)
        nbt.putInt(PROCESSING_TIME_TAG, this.processingTime)
        nbt.putInt(PROCESSING_TIME_TOTAL_TAG, this.processTimeTotal)
        nbt.putBoolean(IS_PROCESSING_TAG, this.isProcessing)
    }

    override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(nbt, provider)
        val inventoryTag = nbt.getCompound(INVENTORY_TAG)
        ContainerHelper.loadAllItems(inventoryTag, this.inventory, provider)
        this.output = ItemStack.CODEC.parse(NbtOps.INSTANCE, inventoryTag.getCompound("Output")).result().orElse(
            ItemStack.EMPTY
        )
        this.processingTime = nbt.getInt(PROCESSING_TIME_TAG)
        this.processTimeTotal = nbt.getInt(PROCESSING_TIME_TOTAL_TAG)
        this.isProcessing = nbt.getBoolean(IS_PROCESSING_TAG)
    }

    override fun getDisplayName(): Component {
        return Component.translatable(blockState.block.descriptionId)
    }

    override fun createMenu(syncId: Int, inv: Inventory, player: Player): AbstractContainerMenu? {
        return RksMachineContainer(syncId, inv, this, dataAccess)
    }

    //    @Override
    //    public int[] getSlotsForFace(Direction dir) {
    //        return (dir == Direction.DOWN && (!output.isEmpty() || getCurrentRecipe().isPresent())) ? OUTPUT_SLOTS : INPUT_SLOTS;
    //    }
    //
    //    @Override
    //    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction dir) {
    //        return slot > 0 && getItem(slot).isEmpty();
    //    }
    //
    //    @Override
    //    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
    //        return slot != 0 || !output.isEmpty() || getCurrentRecipe().isPresent();
    //    }
    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
        return slot != 0 && slot <= containerSize
    }

    override fun isEmpty(): Boolean {
        for (stack in this.inventory) {
            if (!stack.isEmpty) return false
        }
        return output.isEmpty
    }

    override fun getContainerSize(): Int {
        return 10
    }

    val isInputEmpty: Boolean
        get() {
            for (stack in this.inventory) {
                if (!stack.isEmpty) return false
            }
            return true
        }


    override fun getItem(slot: Int): ItemStack {
        if (slot > 0) return inventory[slot - 1]
        if (!output.isEmpty) return output
        return ItemStack.EMPTY
    }

    override fun removeItem(slot: Int, amount: Int): ItemStack {
        if (slot == 0) {
            return output.split(amount)
        }
        return ContainerHelper.removeItem(this.inventory, slot - 1, amount)
    }

    override fun removeItemNoUpdate(slot: Int): ItemStack {
        if (slot == 0) {
            val output = this.output
            this.output = ItemStack.EMPTY
            return output
        }
        return ContainerHelper.takeItem(this.inventory, slot - 1)
    }

    override fun setItem(slot: Int, stack: ItemStack) {
        if (slot == 0) {
            output = stack
            return
        }
        inventory[slot - 1] = stack
        setChanged()
    }

    override fun stillValid(player: Player): Boolean {
        return player.onPos.distSqr(this.worldPosition) <= 64.0
    }


    //    @Override
    //    public void fillStackedContents(@NotNull StackedContents finder) {
    //        for (ItemStack stack : this.inventory) finder.accountStack(stack);
    //    }
    fun setLastRecipe(recipe: RecipeHolder<*>): Boolean {
        if (recipe.value() is RksRecipe) {
            this.lastRecipe = recipe as RecipeHolder<RksRecipe>
            return true
        } else {
            return false
        }
    }

    override fun clearContent() {
        inventory.clear()
    }

    private val currentRecipe: Optional<RecipeHolder<RksRecipe>>
        get() {
            // No need to find recipes if the inventory is empty. Cannot craft anything.
            if (this.level == null || this.isEmpty) return Optional.empty()

            val lastRecipe = lastRecipe
            val manager = level!!.recipeManager

            if (lastRecipe != null) {
                val mapRecipe = getMappedRecipe(manager, lastRecipe.id())
                if (mapRecipe.map { obj: RecipeHolder<RksRecipe> -> obj.value() }
                        .filter { a: RksRecipe -> a.matches(craftingInput, level) }.isPresent
                ) {
                    return Optional.of(lastRecipe)
                }
            }
            return getMappedRecipe(manager)
        }

    private fun getMappedRecipe(manager: RecipeManager): Optional<RecipeHolder<RksRecipe>> {
        return manager.getRecipeFor(GenerationsCoreRecipeTypes.RKS, craftingInput, level)
    }

    private fun getMappedRecipe(manager: RecipeManager, id: ResourceLocation): Optional<RecipeHolder<RksRecipe>> {
        return manager.byKey(id).filter { a: RecipeHolder<*> -> a.value() is RksRecipe }
            .map { a: RecipeHolder<*> -> a as RecipeHolder<RksRecipe> }
    }

    private val result: Optional<ItemStack>
        get() {
            val maybe_result = currentRecipe.map { recipe: RecipeHolder<RksRecipe> ->
                recipe.value().assemble(
                    craftingInput, server()!!.registryAccess()
                )
            }

            return Optional.of(maybe_result.orElse(ItemStack.EMPTY))
        }

    protected fun canSmelt(result: ItemStack, recipe: RksRecipe): Boolean {
        if (recipe.matches(craftingInput, level!!)) {
            val outstack = output
            return if (outstack.isEmpty) {
                true
            } else if (!ItemStack.isSameItem(outstack, result)) {
                false
            } else {
                (outstack.count + result.count <= (if (recipe.isPokemonResult) 1 else outstack.maxStackSize))
            }
        } else {
            return false
        }
    }

    private fun getRecipeProcessingTime(): Int = currentRecipe.map(RecipeHolder<RksRecipe>::value).map(RksRecipe::processingTime).orElse(DEFAULT_PROCESSING_TIME)

    protected fun smelt(result: ItemStack, holder: RecipeHolder<RksRecipe>) {
//        if(recipe.isIncomplete()) return;

        val recipe = holder.value()


        if (!result.isEmpty && this.canSmelt(result, recipe)) {
            val outstack = output.copy()

            if (outstack.isEmpty) {
                output = result.copy()
            } else if (outstack.item === result.item) {
                output.grow(result.count)
            }

            if (!level!!.isClientSide()) {
                this.setLastRecipe(holder)
            }

            val remaining = recipe.getRemainingItems(this)
            val drops = NonNullList.create<ItemStack>()

            for (i in 0..8) {
                val current = inventory[i]
                val remainingStack = remaining[i]
                if (!current.isEmpty) current.shrink(1)
                if (!remainingStack.isEmpty) {
                    if (current.isEmpty) {
                        inventory[i] = remainingStack
                    } else if (ItemStack.matches(current, remainingStack)) {
                        current.grow(remainingStack.count)
                    } else {
                        drops.add(remainingStack)
                    }
                }
            }

            Containers.dropContents(level, worldPosition, drops)
        }
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener>? {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    override fun getUpdateTag(registries: HolderLookup.Provider): CompoundTag {
        return this.saveWithFullMetadata(registries)
    }

    fun awardUsedRecipesAndPopExperience(player: ServerPlayer) {
        val list = this.getRecipesToAwardAndPopExperience(player.serverLevel(), player.position())
        player.awardRecipes(list)
        recipesUsed.clear()
    }

    fun getRecipesToAwardAndPopExperience(level: ServerLevel, popVec: Vec3): List<RecipeHolder<*>> {
        val list = Lists.newArrayList<RecipeHolder<*>>()
        for (entry in recipesUsed.object2IntEntrySet()) {
            level.recipeManager.byKey(entry.key as ResourceLocation).ifPresent { recipe: RecipeHolder<*> ->
                list.add(recipe)
                createExperience(
                    level,
                    popVec,
                    entry.intValue,
                    (recipe.value() as RksRecipe).experience()
                )
            }
        }
        return list
    }

    override var isToggled: Boolean
        get() = isProcessing
        set(value) { isProcessing = value }

    fun addMenu(rksMachineContainer: RksMachineContainer) {
        openContainers.add(rksMachineContainer)
    }

    fun removeMenu(rksMachineContainer: RksMachineContainer) {
        openContainers.remove(rksMachineContainer)
    }

    override fun setChanged() {
        super.setChanged()
        openContainers.forEach(Consumer { obj: RksMachineContainer -> obj.broadcastChanges() })
    }

    val craftingInput: RksInput
        get() = RksInput.of(3, 3, this.inventory) //TODO: finish this.

    companion object {
        private val OUTPUT_SLOTS = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        private val INPUT_SLOTS = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        private const val DEFAULT_PROCESSING_TIME = 200
        private const val INVENTORY_TAG = "Inventory"
        private const val PROCESSING_TIME_TAG = "ProcessingTime"
        private const val PROCESSING_TIME_TOTAL_TAG = "ProcessingTimeTotal"
        private const val IS_PROCESSING_TAG = "IsProcessing"
        fun serverTick(level: Level, blockpos: BlockPos, blockstate: BlockState?, tile: RksMachineBlockEntity) {
            val flag1 = false

            if (tile.isToggled) {
                val result = tile.result.orElse(ItemStack.EMPTY)

                val recipe =
                    tile.currentRecipe

                if (recipe.isPresent && (!tile.isInputEmpty)) {
                    if (tile.canSmelt(result, recipe.get().value())) {
                        if (tile.processingTime <= 0) {
                            tile.processTimeTotal = tile.getRecipeProcessingTime()
                            tile.processingTime = 0
                        }
                    }
                    ++tile.processingTime

                    if (tile.processingTime % 60 == 0) {
                        level.playSound(
                            null,
                            blockpos,
                            GenerationsSounds.RKS_MACHINE,
                            SoundSource.BLOCKS,
                            1.0f,
                            1.0f
                        )
                    }

                    if (tile.processingTime >= tile.processTimeTotal) {
                        tile.smelt(result, recipe.get())
                        tile.processingTime = 0

                        tile.processTimeTotal = if (!tile.isInputEmpty) tile.getRecipeProcessingTime() else 0

                        tile.isToggled = false
                    }
                } else {
                    tile.processingTime = 0
                    tile.isToggled = false
                }
            } /* else if (tile.processingTime > 0) {
            tile.processingTime = Mth.clamp(tile.processingTime - 2, 0, tile.processTimeTotal);
        }*/

            tile.sync()
        }

        private fun createExperience(level: ServerLevel, popVec: Vec3, recipeIndex: Int, experience: Float) {
            var i = Mth.floor(recipeIndex.toFloat() * experience)
            val f = Mth.frac(recipeIndex.toFloat() * experience)
            if (f != 0.0f && Math.random() < f.toDouble()) {
                ++i
            }
            ExperienceOrb.award(level, popVec, i)
        }
    }
}
