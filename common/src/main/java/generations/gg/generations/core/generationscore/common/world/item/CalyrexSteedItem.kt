package generations.gg.generations.core.generationscore.common.world.item

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import generations.gg.generations.core.generationscore.common.GenerationsStorage
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level

class CalyrexSteedItem(name: String, arg: Properties, private val speices: SpeciesKey) : Item(arg) {
    private val defaultTranslation = "container." + name + "_carrot"

    //    @Override
    //    public boolean isEdible() {
    //        return true;
    //    }
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            val carrots = getCarrots(player.getItemInHand(usedHand))

            val isFull = (0..< 18).map(carrots::get).all { it.amount >=64 }

            if (!isFull) {

                (0..<18).forEach { carrots.filter(it) { it.item.equals(Items.CARROT) } }

                GenericContainer.openScreen(carrots, 9, 2, Component.translatable(defaultTranslation), player, player.inventory.selected)
                return InteractionResultHolder.success(player.getItemInHand(usedHand))
            }
            return super.use(level, player, usedHand)
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand))
    }

    override fun finishUsingItem(stack: ItemStack, level: Level, livingEntity: LivingEntity): ItemStack {
        if (!level.isClientSide()) {
            PokemonUtil.spawn(speices.createPokemon(70), level, livingEntity.position(), livingEntity.yRot)
        }

        return ItemStack.EMPTY
    }

    fun getCarrots(stack: ItemStack): SimpleItemStorage {
        return SimpleItemStorage(stack, GenerationsStorage.ITEM_CONTENTS, 18)
    }

//    fun save(container: CarrotHolder, stack: ItemStack) {
//        stack.set(GenerationsItemComponents.CARROT_HOLDER, container)
//    }
//
//    class CarrotHolder @JvmOverloads constructor(items: List<Pair<Int, ItemStack>> = emptyList(), title: MutableComponent = Component.empty()) : SimpleGenericContainer(9, 2, GenerationsStorage.CARROT_HOLDER, title) {
//
//        override fun createMenu(i: Int, arg: Inventory, arg2: Player): AbstractContainerMenu {
//            return CalyrexSteedContainer(i, arg, this, arg2.inventory.selected)
//        }
//
//        val isFull: Boolean
//            get() = IntStream.range(0, containerSize).mapToObj { slot: Int ->
//                this.getItem(
//                    slot
//                )
//            }.allMatch { stack: ItemStack -> stack.`is`(Items.CARROT) && stack.count >= stack.maxStackSize }
//
//        companion object {
//            val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, CarrotHolder> = SLOT_PAIR_CODEC_STREAM.apply(ByteBufCodecs.list()).map(::CarrotHolder, CarrotHolder::createItemList)
//
//            val CODEC: Codec<CarrotHolder> = SLOT_PAIR_CODEC.listOf().xmap(::CarrotHolder) { emptyList() }
//        }
//    }
}
