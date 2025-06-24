package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.cobblemon.mod.common.api.berry.Berry
import com.cobblemon.mod.common.api.berry.Flavor
import com.cobblemon.mod.common.item.berry.BerryItem
import com.mojang.serialization.Codec
import earth.terrarium.common_storage_lib.item.impl.vanilla.VanillaDelegatingSlot
import earth.terrarium.common_storage_lib.item.impl.vanilla.WrappedVanillaContainer
import earth.terrarium.common_storage_lib.item.util.ItemProvider
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import generations.gg.generations.core.generationscore.common.GenerationsStorage
import generations.gg.generations.core.generationscore.common.SimpleItemSlot
import generations.gg.generations.core.generationscore.common.api.events.CurryEvents
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.util.TEXT_CODEC
import generations.gg.generations.core.generationscore.common.util.shrink
import generations.gg.generations.core.generationscore.common.world.container.CookingPotContainer
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryIngredient
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistryAccess
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.Container
import net.minecraft.world.ContainerHelper
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemContainerContents
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.Stream


class CookingPotBlockEntity(pos: BlockPos, state: BlockState) : ModelProvidingBlockEntity(GenerationsBlockEntities.COOKING_POT, pos, state),
    MenuProvider,
    ItemProvider.BlockEntity, VariantProvider, Toggleable {
    //0-9: berries
    //10: main ingredient
    //11: bowl
    //12: log
    //13: out
    private val handler = object : SimpleContainer(14) {
        override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
            return when(slot) {
                10 -> CookingPotContainer.isBowl(stack)
                11 -> CookingPotContainer.isCurryIngredientOrMaxHoney(stack)
                12 -> CookingPotContainer.isBowl(stack)
                else -> CookingPotContainer.isBerryOrMaxMushrooms(stack)
            }
        }

        override fun setChanged() {
            super.setChanged()
            this@CookingPotBlockEntity.sync()
        }
    }

    private val data: ContainerData = object : ContainerData {
        override fun get(index: Int): Int = when(index) {
            0 -> this@CookingPotBlockEntity.cookTime
            1 -> if(this@CookingPotBlockEntity.isCooking) 1 else 0
            2 -> if (this@CookingPotBlockEntity.isToggled) 1 else 0
            else -> 0
        }

        override fun set(index: Int, value: Int) = when(index) {
            0 -> this@CookingPotBlockEntity.cookTime = value
            1 -> this@CookingPotBlockEntity.isCooking = value == 1
            2 -> this@CookingPotBlockEntity.isToggled = value == 1
            else -> {}
        }

        override fun getCount(): Int = 3
    }

    public var customName: Component? = null
    private var isCooking = false
    var cookTime: Int = 0
        private set

    fun hasCustomName(): Boolean = this.customName != null

    override fun getDisplayName(): MutableComponent {
        return this.customName?.copy() ?: Component.translatable("container.cooking_pot")
    }

    override fun saveAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(nbt, provider)

        nbt.putBoolean("IsCooking", isCooking)
        nbt.putInt("CookTime", this.cookTime)

        if(customName != null) {
            nbt.put("CustomName", ComponentSerialization.CODEC.toNbt(customName))
        }
        handler.save(nbt, provider)
    }

    override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(nbt, provider)
        this.isCooking = nbt.getBoolean("IsCooking")
        this.cookTime = nbt.getInt("CookTime")

        if(nbt.contains("CustomName")) customName = TEXT_CODEC.fromNbt(nbt.get("CustomName")!!)
        handler.load(nbt, provider)

    }

    fun serverTick() {
        val berries = (0..9).map { handler.getItem(it) }.toTypedArray()
        val bowl = handler.getItem(10)
        val mainIngredient = handler.getItem(11)
        val log = handler.getItem(12)

        val hasEverything = isCooking && berries.any { !it.isEmpty } && !bowl.isEmpty && !log.isEmpty && handler.getItem(13).isEmpty

        if (hasEverything) {
            var hasInserted = false
            cookTime++

            if (cookTime >= 200) {
                val hasBerry = Stream.of(*berries).anyMatch { a -> !a.isEmpty }
                val hasMaxMushrooms = berries.any { a ->
                    a.itemHolder.`is`(GenerationsItems.MAX_MUSHROOMS.unwrapKey().get())
                }

                if (hasBerry && !hasMaxMushrooms) {
                    val type: CurryType = mainIngredient.takeUnless { it.isEmpty }?.itemHolder?.value().instanceOrNull<CurryIngredient>()?.type ?: CurryType.None
                    val berriesTypes = berries.filter { berry -> !berry.isEmpty && berry.item is BerryItem }
                        .mapNotNull { a -> a.instanceOrNull<BerryItem>()?.berry() }.toList()

                    CurryEvents.COOK.postThen(CurryEvents.Cook(type, berriesTypes, CurryData.create(type, berriesTypes)), ifSucceeded = {
                        event ->
                            val curry = GenerationsItems.CURRY.value().defaultInstance.also { it.set(GenerationsDataComponents.CURRY_DATA.value(), event.output) }

                            hasInserted = handler.insert(13, curry, 1, false) > 0

                    })
                } else if (hasMaxMushrooms && !hasBerry && (mainIngredient.isEmpty || mainIngredient.item === GenerationsItems.MAX_HONEY.value())) {
                    val count = AtomicInteger()
                    berries
                        .filter { a -> a.`is`(GenerationsItems.MAX_MUSHROOMS.value()) }
                        .forEach { b -> count.addAndGet(b.count) }
                    if (count.get() > 2) {
                        var amountTaken = 0

                        // Take the mushrooms
                        for (itemStack in berries) {
                            val amountLeft = 3 - amountTaken
                            if (itemStack.count > amountLeft) {
                                itemStack.count = amountLeft
                                amountTaken = 3
                                break
                            } else {
                                amountTaken += itemStack.count
                                itemStack.count = 0
                            }
                        }

                        val maxSoupStack: ItemStack = ItemStack(GenerationsItems.MAX_SOUP)

                        if (!mainIngredient.isEmpty && mainIngredient.item === GenerationsItems.MAX_HONEY.value()) {
//                            CompoundTag compound = new CompoundTag(); //TODO: Convert when we have use for it.
//                            compound.putBoolean("MaxSoupHoney", true);
//                            compound.putBoolean("GlowEffect", true);
//                            maxSoupStack.setTag(compound);
                            log.shrink(1)
                            mainIngredient.shrink(1)
                            bowl.shrink(1)
                            cookTime = 0
                        }

                        handler.insert(13, maxSoupStack, 1, false)
                    } else {
                        setCooking(false)
                    }
                }
            }

            if (hasInserted) {
                berries.forEach { berry -> berry.shrink(1) }
                log.shrink(1)
                mainIngredient.shrink(1)
                bowl.shrink(1)
                cookTime = 0
                setChanged()
            }
        } else {
            setCooking(false)
        }

        sync()
    }

    fun setCooking(isCooking: Boolean) {
        this.isCooking = isCooking
        this.cookTime = 0
        sync()
    }

    fun isCooking(): Boolean {
        return isCooking
    }

    val ingredient: ItemStack
        get() = handler.getItem(11)

    val ouput: ItemStack
        get() = handler.getItem(13)

    fun getBerry(index: Int): ItemStack {
        return if (index in 0..9) handler.getItem(index) else ItemStack.EMPTY
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket? {
        return ClientboundBlockEntityDataPacket.create(
            this
        ) { a: BlockEntity?, b: RegistryAccess ->
            val compound = CompoundTag()
            compound.putBoolean("isCooking", isCooking)
            compound.putInt("cookTime", this.cookTime)
            handler.save(compound, b);

            compound
        }
    }

    override fun createMenu(id: Int, playerInventory: Inventory, player: Player): AbstractContainerMenu {
        return CookingPotContainer(id, playerInventory, handler, data)
    }

    override fun getVariant(): String {
        return if (hasLogs()) {
            if (isCooking()) "lit" else "unlit"
        } else {
            "no_logs"
        }
    }

    fun hasLogs(): Boolean {
        return !handler.getItem(12).isEmpty
    }

    override var isToggled: Boolean
        get() = isCooking()
        set(value) { setCooking(value) }

    override fun getItems(direction: Direction?): CommonStorage<ItemResource> {
        return WrappedVanillaContainer(handler)
    }

    override fun applyImplicitComponents(componentInput: DataComponentInput) {
        super.applyImplicitComponents(componentInput)
        componentInput.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(handler.items)
        customName = componentInput.get(DataComponents.CUSTOM_NAME)
    }

    override fun collectImplicitComponents(components: DataComponentMap.Builder) {
        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(handler.items))
        if(customName != null) components.set(DataComponents.CUSTOM_NAME, customName)
    }

    override fun removeComponentsFromTag(tag: CompoundTag) {
        tag.remove("CustomName")
        tag.remove("Items")

    }

    companion object {
        fun getDominantFlavor(berries: List<Berry>): Flavor? {
            val output = Flavor.entries.associateWith { flavor ->
                berries.sumOf { it.flavor(flavor) }
            }

            return output.takeIf { it.values.toSet().size > 1 }
                ?.maxByOrNull { it.value }
                ?.key
        }
    }

}

fun <A> Codec<A>.toNbt(value: A): Tag {
    return this.encodeStart(NbtOps.INSTANCE, value).orThrow
}

fun <A> Codec<A>.fromNbt(value: Tag): A {
    return this.parse(NbtOps.INSTANCE, value).orThrow
}

fun SimpleContainer.save(compound: CompoundTag, b: HolderLookup.Provider) {
    ContainerHelper.saveAllItems(compound, items, b)
}

fun SimpleContainer.load(compound: CompoundTag, b: HolderLookup.Provider) {
    ContainerHelper.loadAllItems(compound, items, b)
}

fun Container.insert(slot: Int, stack: ItemStack, amount: Int, simulated: Boolean): Int {
    require(!stack.isEmpty) { "Cannot insert an empty ItemStack." }
    require(amount > 0) { "Amount must be positive." }
    require(slot in 0 until this.containerSize) { "Slot index $slot out of bounds (0..${this.containerSize - 1})" }

    val target = this.getItem(slot)

    if (target.isEmpty) {
        // Slot is empty; can accept up to maxStackSize
        val insertAmount = minOf(amount, stack.maxStackSize)
        if (!simulated) {
            val toInsert = stack.copy()
            toInsert.count = insertAmount
            this.setItem(slot, toInsert)
            this.setChanged()
        }
        return insertAmount
    }

    if (!ItemStack.isSameItemSameComponents(stack, target)) return 0
    val space = target.maxStackSize - target.count
    if (space <= 0) return 0

    val insertAmount = minOf(amount, space)
    if (!simulated) {
        target.grow(insertAmount)
        this.setChanged()
    }
    return insertAmount
}