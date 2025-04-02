package generations.gg.generations.core.generationscore.common.world.item

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.util.extensions.get
import generations.gg.generations.core.generationscore.common.util.extensions.set
import generations.gg.generations.core.generationscore.common.world.container.CalyrexSteedContainer
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer.SimpleGenericContainer
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsItemComponents
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import java.util.stream.IntStream

class CalyrexSteedItem(name: String, arg: Properties, private val speices: SpeciesKey) : Item(arg) {
    private val defaultTranslation = "container." + name + "_carrot"

    //    @Override
    //    public boolean isEdible() {
    //        return true;
    //    }
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            val carrots = getCarrots(player.getItemInHand(usedHand))
            if (!carrots.isFull) {
                getCarrots(player.getItemInHand(usedHand)).openScreen(player, player.inventory.selected)
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

    fun getCarrots(stack: ItemStack): CarrotHolder {
        return stack.get(GenerationsItemComponents.CARROT_HOLDER) ?: CarrotHolder(title = Component.translatable(defaultTranslation))
    }

    fun save(container: CarrotHolder, stack: ItemStack) {
        stack.set(GenerationsItemComponents.CARROT_HOLDER, container)
    }

    class CarrotHolder @JvmOverloads constructor(items: List<Pair<Int, ItemStack>> = emptyList(), title: MutableComponent = Component.empty()) : SimpleGenericContainer(9, 2, items, title) {

        override fun createMenu(i: Int, arg: Inventory, arg2: Player): AbstractContainerMenu {
            return CalyrexSteedContainer(i, arg, this, arg2.inventory.selected)
        }

        val isFull: Boolean
            get() = IntStream.range(0, containerSize).mapToObj { slot: Int ->
                this.getItem(
                    slot
                )
            }.allMatch { stack: ItemStack -> stack.`is`(Items.CARROT) && stack.count >= stack.maxStackSize }

        companion object {
            val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, CarrotHolder> = SLOT_PAIR_CODEC_STREAM.apply(ByteBufCodecs.list()).map(::CarrotHolder, CarrotHolder::createItemList)

            val CODEC: Codec<CarrotHolder> = SLOT_PAIR_CODEC.listOf().xmap(::CarrotHolder) { emptyList() }
        }
    }
}
