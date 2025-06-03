package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.cobblemon.mod.common.api.berry.Berry
import com.cobblemon.mod.common.api.berry.Flavor
import com.cobblemon.mod.common.item.berry.BerryItem
import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.item.util.ItemProvider
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import generations.gg.generations.core.generationscore.common.GenerationsStorage
import generations.gg.generations.core.generationscore.common.api.events.CurryEvents
import generations.gg.generations.core.generationscore.common.api.events.CurryEvents.Cook
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
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
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import java.util.*
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.Stream


class CookingPotBlockEntity(pos: BlockPos, state: BlockState) : ModelProvidingBlockEntity(GenerationsBlockEntities.COOKING_POT.get(), pos, state),
    MenuProvider,
    ItemProvider.BlockEntity, VariantProvider, Toggleable {
    //0-9: berries
    //10: main ingredient
    //11: bowl
    //12: log
    //13: out
    private val handler = SimpleItemStorage(this, GenerationsStorage.ITEM_CONTENTS, 14).let {
        it.filter(0, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(1, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(2, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(3, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(4, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(5, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(6, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(7, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(8, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(9, CookingPotContainer::isBerryOrMaxMushrooms)
        it.filter(10, CookingPotContainer::isBowl)
        it.filter(11, CookingPotContainer::isCurryIngredientOrMaxHoney)
        it.filter(12, CookingPotContainer::isLog)

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

    private var customName: String? = null
    private var isCooking = false
    var cookTime: Int = 0
        private set

    fun hasCustomName(): Boolean = this.customName?.isNotEmpty() ?: false

    fun setCustomName(customName: String?) {
        this.customName = customName
    }

    override fun getDisplayName(): MutableComponent {
        return if (this.hasCustomName()) Component.nullToEmpty(this.customName).copy() else Component.translatable("container.cooking_pot")
    }

    override fun saveAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(nbt, provider)

        nbt.putBoolean("isCooking", isCooking)
        nbt.putInt("cookTime", this.cookTime)

        if (this.hasCustomName()) nbt.putString("customName", this.customName!!)
    }

    override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(nbt, provider)
        this.isCooking = nbt.getBoolean("isCooking")
        this.cookTime = nbt.getInt("cookTime")

        //        this.handler.deserializeNBT(nbt.getCompound("inventory"));
        if (nbt.contains("customName", 8)) this.setCustomName(nbt.getString("customName"))
    }

    fun serverTick() {
        val berries = (0..9).map { handler[it] }.toTypedArray()
        val bowl = handler[10]
        val mainIngredient = handler[11]
        val log = handler[12]

        val hasEverything = isCooking && berries.any { !it.isEmpty } && !bowl.isEmpty && !log.isEmpty && handler.getResource(13).isBlank

        if (hasEverything) {
            var hasInserted = false
            cookTime++

            if (cookTime >= 200) {
                val hasBerry = Stream.of(*berries).anyMatch { a: SimpleItemSlot -> !a.isEmpty }
                val hasMaxMushrooms = Stream.of<SimpleItemSlot>(*berries).anyMatch { a: SimpleItemSlot ->
                    a.resource.asHolder().`is`(GenerationsItems.MAX_MUSHROOMS.getKey())
                }

                if (hasBerry && !hasMaxMushrooms) {
                    val type: CurryType = mainIngredient.takeUnless { it.isEmpty }?.resource?.asHolder()?.value().instanceOrNull<CurryIngredient>()?.type
                        ?: CurryType.None
                    val berriesTypes = berries.filter { berry: SimpleItemSlot -> !berry.isEmpty && berry.resource.item is BerryItem }
                        .mapNotNull { a -> (a.resource.asItem() as BerryItem).berry() }.toList()

                    val event: Cook = Cook(type, berriesTypes, CurryData.create(type, berriesTypes))

                    if (!CurryEvents.COOK.invoker().act(event).isTrue()) {
                        val curry: ItemResource = ItemResource.of(GenerationsItems.CURRY.get())
                            .set(GenerationsDataComponents.CURRY_DATA.get(), event.output)

                        hasInserted = handler.insert(13, curry, 1, false) > 0
                    }
                } else if (hasMaxMushrooms && !hasBerry && (mainIngredient.isEmpty || mainIngredient.resource.item === GenerationsItems.MAX_HONEY.get())) {
                    val count = AtomicLong()
                    Stream.of<SimpleItemSlot>(*berries)
                        .filter { a: SimpleItemSlot -> a.resource.isOf(GenerationsItems.MAX_MUSHROOMS.get()) }
                        .forEach { b: SimpleItemSlot -> count.addAndGet(b.amount) }
                    if (count.get() > 2) {
                        var amountTaken = 0

                        // Take the mushrooms
                        for (itemStack in berries) {
                            val amountLeft = 3 - amountTaken
                            if (itemStack.amount > amountLeft) {
                                itemStack.amount = amountLeft.toLong()
                                amountTaken = 3
                                break
                            } else {
                                amountTaken += itemStack.amount.toInt()
                                itemStack.amount = 0
                            }
                        }

                        val maxSoupStack: ItemStack = ItemStack(GenerationsItems.MAX_SOUP.get())

                        if (!mainIngredient.isEmpty && mainIngredient.resource.item === GenerationsItems.MAX_HONEY.get()) {
//                            CompoundTag compound = new CompoundTag(); //TODO: Convert when we have use for it.
//                            compound.putBoolean("MaxSoupHoney", true);
//                            compound.putBoolean("GlowEffect", true);
//                            maxSoupStack.setTag(compound);
                            log.shrink(1)
                            mainIngredient.shrink(1)
                            bowl.shrink(1)
                            cookTime = 0
                        }

                        handler[13].insert(ItemResource.of(maxSoupStack), 1, false)
                    } else {
                        setCooking(false)
                    }
                }
            }

            if (hasInserted) {
                Arrays.stream<SimpleItemSlot>(berries).forEach { berry: SimpleItemSlot -> berry.shrink(1) }
                log.shrink(1)
                mainIngredient.shrink(1)
                bowl.shrink(1)
                cookTime = 0
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

    val ingredient: ItemResource
        get() = handler.getResource(11)

    val ouput: ItemResource
        get() = handler.getResource(13)

    fun getBerry(index: Int): ItemResource {
        return if (index in 0..9) handler.getResource(index) else ItemResource.BLANK
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket? {
        return ClientboundBlockEntityDataPacket.create(
            this
        ) { a: BlockEntity?, b: RegistryAccess? ->
            val compound = CompoundTag()
            compound.putBoolean("isCooking", isCooking)
            compound.putInt("cookTime", this.cookTime)
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
        return !handler.getResource(12).isBlank
    }

    override var isToggled: Boolean
        get() = isCooking()
        set(value) { setCooking(value) }

    override fun getItems(direction: Direction?): CommonStorage<ItemResource> {
        return handler
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
