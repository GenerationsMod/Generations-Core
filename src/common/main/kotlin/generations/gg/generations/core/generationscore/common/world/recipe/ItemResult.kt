package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.datafixers.util.Either
import com.mojang.serialization.Codec
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.util.function.Function

data class ItemResult(override val stack: ItemStack) : RksResult<ItemResult> {
    override fun type(): RksResultType<ItemResult> {
        return GenerationsRksTypes.ITEM.value() as RksResultType<ItemResult>
    }

    override val isPokemon: Boolean
        get() = false

    companion object {
        val CODEC = Codec.mapEither(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item"), ItemStack.CODEC.fieldOf("stack")).xmap(
                { it.map(Item::getDefaultInstance, Function.identity()) },
                { if (it.count > 1 || !it.components.isEmpty) Either.right(it) else Either.left(it.item) })
            .xmap({ item: ItemStack -> ItemResult(item) }, ItemResult::stack)

        val STREAM_CODEC = StreamCodec.composite(ItemStack.STREAM_CODEC, ItemResult::stack, ::ItemResult)
    }
}
