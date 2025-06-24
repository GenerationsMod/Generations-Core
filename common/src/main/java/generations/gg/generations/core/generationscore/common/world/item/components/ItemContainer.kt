package generations.gg.generations.core.generationscore.common.world.item.components

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.world.container.CloseableContainer
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.MelodyFluteItem
import net.minecraft.core.NonNullList
import net.minecraft.core.component.DataComponents
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.JukeboxPlayable

open class ItemContainer(items: NonNullList<ItemStack>, count: Int = items.size): SimpleContainer(items) {

    companion object {
    }
}

class DiscContainer(
    var currentSlot: Int = 0,
    val rows: Int = 1,
    items: NonNullList<ItemStack> = NonNullList.withSize(rows * 9, ItemStack.EMPTY)
) : ItemContainer(items, rows * 9) {

    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
        return stack.has(DataComponents.JUKEBOX_PLAYABLE)
    }

    fun next(): JukeboxPlayable? {
        println("[DiscContainer.next] === ENTER ===")
        println("[DiscContainer.next] currentSlot = $currentSlot, containerSize = $containerSize")

        val size = containerSize
        if (size == 0) {
            println("[DiscContainer.next] Container is empty. Returning null.")
            return null
        }

        var index = (currentSlot + 1) % size
        var attempts = 0

        while (attempts < size) {
            val stack = getItem(index)
            println("[DiscContainer.next] Attempt $attempts — index=$index, stack=$stack")

            if (!stack.isEmpty) {
                val playable = stack.get(DataComponents.JUKEBOX_PLAYABLE)
                if (playable != null) {
                    println("[DiscContainer.next] Found playable at index $index: $playable")
                    currentSlot = index
                    return playable
                } else {
                    println("[DiscContainer.next] Stack at $index is not playable.")
                }
            } else {
                println("[DiscContainer.next] Stack at $index is empty.")
            }

            index = (index + 1) % size
            attempts++
        }

        println("[DiscContainer.next] No playable found after scanning all slots.")
        return null
    }

    companion object {
        val CODEC = Codecs.codec3(
            "currentSlot", Codec.INT, DiscContainer::currentSlot,
            "rows", Codec.INT, DiscContainer::rows,
            "items", Codecs.ITEM_STACK_LIST_CODEC, DiscContainer::items,
            ::DiscContainer
        )

        val STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, DiscContainer::currentSlot,
            ByteBufCodecs.VAR_INT, DiscContainer::rows,
            ByteBufCodecs.collection({ NonNullList.createWithCapacity(it) }, ItemStack.OPTIONAL_STREAM_CODEC),
            DiscContainer::items,
            ::DiscContainer
        )
    }
}

class CarrotHolder(items: NonNullList<ItemStack> = NonNullList.withSize(18, ItemStack.EMPTY)): ItemContainer(items, 18) {
    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean = stack.`is`(Items.CARROT) //TODO: REplace with general generations:carrots tag

    companion object {
        val CODEC = Codecs.codec1("items", Codecs.ITEM_STACK_LIST_CODEC, CarrotHolder::items, ::CarrotHolder)

        val STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.collection({ NonNullList.createWithCapacity(it) }, ItemStack.OPTIONAL_STREAM_CODEC), CarrotHolder::items, ::CarrotHolder)
    }
}

class MelodFluteContainer(imbued: ItemStack = ItemStack.EMPTY): SingleItemStackContainer(imbued) {
    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
        return MelodyFluteItem.isItem(GenerationsItems.ICY_WING, stack) ||
                    MelodyFluteItem.isItem(GenerationsItems.ELEGANT_WING, stack) ||
                    MelodyFluteItem.isItem(GenerationsItems.STATIC_WING, stack) ||
                    MelodyFluteItem.isItem(GenerationsItems.BELLIGERENT_WING, stack) ||
                    MelodyFluteItem.isItem(GenerationsItems.FIERY_WING, stack) ||
                    MelodyFluteItem.isItem(GenerationsItems.SINISTER_WING, stack) ||
                    MelodyFluteItem.isItem(GenerationsItems.RAINBOW_WING, stack) ||
                    MelodyFluteItem.isItem(GenerationsItems.SILVER_WING, stack)
    }

    fun getImbued(): ItemStack = getItem(0)

    companion object {
        val CODEC = Codecs.codec1("imbued", ItemStack.OPTIONAL_CODEC, MelodFluteContainer::getImbued, ::MelodFluteContainer)

        val STREAM_CODEC = StreamCodec.composite(ItemStack.OPTIONAL_STREAM_CODEC, MelodFluteContainer::getImbued, ::MelodFluteContainer)
    }
}