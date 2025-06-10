package generations.gg.generations.core.generationscore.common.world.container

import dev.architectury.registry.menu.ExtendedMenuProvider
import dev.architectury.registry.menu.MenuRegistry
import earth.terrarium.common_storage_lib.item.util.ItemStorageData
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import earth.terrarium.common_storage_lib.storage.base.UpdateManager
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu

object GenericContainer {

    fun <T> openScreen(storage: T, width: Int, height: Int, title: Component, player: Player, lock: Int = -1) where T: CommonStorage<ItemResource>, T: UpdateManager<ItemStorageData> {

        if (!player.isLocalPlayer) MenuRegistry.openExtendedMenu(
            player as ServerPlayer,
            object : MenuProvider, ExtendedMenuProvider {
                override fun createMenu(id: Int, inventory: Inventory, player: Player): AbstractContainerMenu {
                    return GenericChestContainer(id, inventory, storage, width, height, lock)
                }

                override fun getDisplayName(): Component {
                    return title
                }

                override fun saveExtraData(buffer: FriendlyByteBuf) {
                    buffer.writeVarInt(width)
                    buffer.writeVarInt(height)
                    buffer.writeVarInt(lock)
                }
            }
        )
    }

//    override fun createMenu(i: Int, arg: Inventory, arg2: Player): AbstractContainerMenu? {
//        return GenericChestContainer(i, arg, this)
//    }

//    open class SimpleGenericContainer @JvmOverloads constructor(
//        override val width: Int,
//        override val height: Int,
//        items: List<Pair<Int, ItemStack>> = emptyList(),
//        val title: MutableComponent = Component.empty()
//    ) : SimpleItemStorage(width * height), GenericContainer {
//
//        init {
//            for (pair in items) {
//                val j = pair.first
//                if (j < this.containerSize) {
//                    this.setItem(j, pair.second)
//                }
//            }
//        }
//
//        override fun getDisplayName(): Component {
//            return title
//        }
//
//        override fun fromTag(containerNbt: ListTag) {
//            for (i in 0 until this.containerSize) {
//                this.setItem(i, ItemStack.EMPTY)
//            }
//
//            for (i in containerNbt.indices) {
//                val compoundTag = containerNbt.getCompound(i)
//                val j = compoundTag.getByte("Slot").toInt() and 255
//                if (j < this.containerSize) {
//                    this.setItem(j, ItemStack.of(compoundTag))
//                }
//            }
//        }
//
//        override fun createTag(): ListTag {
//            val listTag = ListTag()
//
//            for (i in 0 until this.containerSize) {
//                val itemStack = this.getItem(i)
//                if (!itemStack.isEmpty) {
//                    val compoundTag = CompoundTag()
//                    compoundTag.putByte("Slot", i.toByte())
//                    itemStack.save(compoundTag)
//                    listTag.add(compoundTag)
//                }
//            }
//
//            return listTag
//        }
//
//        public fun createItemList(): List<Pair<Int, ItemStack>> {
//            val list = ArrayList<Pair<Int, ItemStack>>()
//
//            for (i in 0 until this.containerSize) {
//                val itemStack = this.getItem(i)
//                if (!itemStack.isEmpty) {
//                    list.add(Pair.of(i, itemStack))
//                }
//            }
//
//            return list
//        }
//
//        companion object {
//            val SLOT_PAIR_CODEC: Codec<Pair<Int, ItemStack>> = Codec.PASSTHROUGH.flatXmap(
//                { dynamic: Dynamic<*> ->
//                    val slot = dynamic["Slot"].asInt(-1)
//                    if (slot == -1) return@flatXmap DataResult.error<Pair<Int, ItemStack>> { "Slot can't be -1" }
//                    dynamic.decode(ItemStack.CODEC).map<ItemStack>({ it.first }).map { a: ItemStack -> Pair.of(slot, a) }
//                },
//                { pair: Pair<Int, ItemStack> ->
//                    val tag = ItemStack.STRICT_CODEC.encodeStart(NbtOps.INSTANCE, pair.second).map { a -> a as CompoundTag }.getOrThrow()
//                    tag.putInt("Slot", pair.first)
//                    DataResult.success(Dynamic(NbtOps.INSTANCE, tag))
//                })
//
//            val SLOT_PAIR_CODEC_STREAM: StreamCodec<RegistryFriendlyByteBuf, Pair<Int, ItemStack>> = StreamCodec.composite<RegistryFriendlyByteBuf, Pair<Int, ItemStack>, Int, ItemStack>(StreamCodec.of(ByteBufCodecs.INT::encode, ByteBufCodecs.INT::decode), { it.first }, ItemStack.STREAM_CODEC, { it.second }, { a, b -> Pair.of(a, b) })
//
//            val CODEC: Codec<SimpleGenericContainer> =
//                RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<SimpleGenericContainer> ->
//                    instance.group(
//                        Codec.INT.fieldOf("width").forGetter { obj: SimpleGenericContainer -> obj.width },
//                        Codec.INT.fieldOf("height").forGetter { obj: SimpleGenericContainer -> obj.height },
//                        SLOT_PAIR_CODEC.listOf().optionalFieldOf("items", Collections.emptyList()).forGetter(SimpleGenericContainer::createItemList),
//                        TEXT_CODEC.optionalFieldOf("title", Component.empty()).forGetter({ it.title })
//                    ).apply(
//                        instance
//                    ) { width: Int, height: Int, items: List<Pair<Int, ItemStack>>, title: MutableComponent ->
//                        SimpleGenericContainer(
//                            width,
//                            height,
//                            items,
//                            title
//                        )
//                    }
//                }
//
//            val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, SimpleGenericContainer> = StreamCodec.composite(
//                ByteBufCodecs.INT,
//                { it.width },
//                ByteBufCodecs.INT,
//                { it.height },
//                SLOT_PAIR_CODEC_STREAM.apply(ByteBufCodecs.list()),
//                { it.createItemList() },
//                TEXT_STREAM_CODEC,
//                { it.title },
//                ::SimpleGenericContainer)
//        }
//    }
}
