package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.cobblemon.mod.common.api.berry.Berry
import com.cobblemon.mod.common.api.berry.Flavor
import com.cobblemon.mod.common.item.berry.BerryItem
import dev.architectury.registry.menu.ExtendedMenuProvider
import dev.architectury.registry.menu.MenuRegistry
import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.item.util.ItemProvider
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistryAccess
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import java.util.*
import java.util.concurrent.atomic.AtomicLong
import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.IntStream
import java.util.stream.Stream

class CookingPotBlockEntity(arg2: BlockPos?, arg3: BlockState?) :
    ModelProvidingBlockEntity(GenerationsBlockEntities.COOKING_POT.get(), arg2, arg3),
    MenuRegistry.ExtendedMenuTypeFactory<CookingPotContainer?>, ExtendedMenuProvider,
    ItemProvider.BlockEntity, VariantProvider, Toggleable {
    //0-9: berries
    //10: main ingredient
    //11: bowl
    //12: log
    //13: out
    private val handler = SimpleItemStorage(this, GenerationsStorage.item_contents, 14)

    private var customName: String? = null
    private var isCooking = false
    var cookTime: Int = 0
        private set

    fun hasCustomName(): Boolean {
        return this.customName != null && !customName!!.isEmpty()
    }

    fun setCustomName(customName: String?) {
        this.customName = customName
    }

    override fun getDisplayName(): MutableComponent {
        return if (this.hasCustomName()) Component.literal(this.customName) else Component.translatable("container.cooking_pot")
    }

    override fun saveAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(nbt, provider)

        nbt.putBoolean("isCooking", isCooking)
        nbt.putInt("cookTime", this.cookTime)

        if (this.hasCustomName()) nbt.putString("customName", this.customName)
    }

    override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(nbt, provider)
        this.isCooking = nbt.getBoolean("isCooking")
        this.cookTime = nbt.getInt("cookTime")

        //        this.handler.deserializeNBT(nbt.getCompound("inventory"));
        if (nbt.contains("customName", 8)) this.setCustomName(nbt.getString("customName"))
    }

    fun serverTick() {
        val berries = IntStream.rangeClosed(0, 9).mapToObj<SimpleItemSlot> { index: Int -> handler[index] }
            .toArray<SimpleItemSlot> { _Dummy_.__Array__() }
        val bowl = handler[10]
        val mainIngredient = handler[11]
        val log = handler[12]

        val hasEverything = isCooking && Stream.of(*berries)
            .anyMatch { a: SimpleItemSlot -> !a.isEmpty } && !bowl.isEmpty && !log.isEmpty && handler.getResource(13).isBlank
        if (hasEverything) {
            var hasInserted = false
            cookTime++

            if (cookTime >= 200) {
                val hasBerry = Stream.of(*berries).anyMatch { a: SimpleItemSlot -> !a.isEmpty }
                val hasMaxMushrooms = Stream.of<SimpleItemSlot>(*berries).anyMatch { a: SimpleItemSlot ->
                    a.resource.asHolder().`is`(GenerationsItems.MAX_MUSHROOMS.getKey())
                }

                if (hasBerry && !hasMaxMushrooms) {
                    val type: CurryType = if (!mainIngredient.isEmpty && mainIngredient.resource().asHolder()
                            .value() is CurryIngredient
                    ) ingredient.getType() else CurryType.None
                    val berriesTypes = Arrays.stream(berries)
                        .filter { berry: SimpleItemSlot -> !berry.isEmpty && berry.resource.item is BerryItem }
                        .map { a: SimpleItemSlot -> (a.resource.asItem() as BerryItem).berry() }.collect(
                            Collectors.toList()
                        ) //TDOO Simplify post kotlin conversion

                    val event: Cook = Cook(type, berriesTypes, CurryData(type, berriesTypes))

                    if (!CurryEvents.COOK.invoker().act(event).isTrue()) {
                        val curry: ItemResource = ItemResource.of(GenerationsItems.CURRY.get())
                            .set<CurryData>(GenerationsItemComponents.CURRY_DATA, event.getOutput())

                        hasInserted = handler.insert(13, curry, 1, false) > 0
                    }
                } else if (hasMaxMushrooms && !hasBerry && (mainIngredient.isEmpty || mainIngredient.resource.item === GenerationsItems.MAX_HONEY.get())) {
                    val count = AtomicLong()
                    Stream.of<SimpleItemSlot>(*berries)
                        .filter { a: SimpleItemSlot -> a.resource.isOf(GenerationsItems.MAX_MUSHROOMS.get()) }
                        .forEach { b: SimpleItemSlot -> count.addAndGet(b.amount()) }
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
        return if (index >= 0 && index < 10) handler.getResource(index) else ItemResource.BLANK
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

    //    @Override
    //    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
    //        this.isCooking = pkt.getTag().getBoolean("isCooking");
    //        this.cookTime = pkt.getTag().getInt("cookTime");
    //    }
    override fun create(i: Int, arg: Inventory, arg2: FriendlyByteBuf): CookingPotContainer? {
        return if (arg.player.level().getBlockEntity(arg2.readBlockPos()) is CookingPotBlockEntity) {
            CookingPotContainer(CreationContext<CookingPotBlockEntity>(i, arg, cookingPot))
        } else {
            null
        }
    }

    override fun saveExtraData(buf: FriendlyByteBuf) {
        buf.writeBlockPos(blockPos)
    }

    override fun createMenu(i: Int, arg: Inventory, arg2: Player): AbstractContainerMenu? {
        return CookingPotContainer(CreationContext<CookingPotBlockEntity>(i, arg, this))
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

    override fun setToggled(toggle: Boolean) {
        setCooking(toggle)
    }

    override fun isToggled(): Boolean {
        return isCooking()
    }

    override fun getItems(direction: Direction?): CommonStorage<ItemResource> {
        return handler
    }

    companion object {
        fun serverTick(level: Level?, pos: BlockPos?, state: BlockState?, blockEntity: CookingPotBlockEntity) {
            blockEntity.serverTick()
        }

        fun getDominantFlavor(vararg berries: Berry): Flavor? {
            val output: MutableMap<Flavor, Int> = HashMap()

            //        int[] output = new int[5];
            for (berryType in berries) for (flavor in Flavor.entries) output.compute(flavor) { flavor1: Flavor?, integer: Int? ->
                val i = berryType.flavor(
                    flavor1!!
                )
                if (integer == null) i else integer + i
            }

            if (output.values.stream().distinct().count() <= 1) return null

            return output.entries.stream().max(java.util.Map.Entry.comparingByValue<Flavor, Int>()).map<Flavor?>(
                Function<Map.Entry<Flavor, Int>, Flavor?> { java.util.Map.Entry.key }).orElse(null)
        }
    }
}
