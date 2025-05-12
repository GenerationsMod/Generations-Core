package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.util.asResource
import com.google.gson.JsonObject
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import io.netty.buffer.ByteBuf
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class ItemIngredient(val itemKey: ResourceKey<Item>) : GenerationsIngredient {
    override val id = ID
    override fun matches(stack: ItemStack): Boolean {
        return stack.`is` { it.`is`(itemKey) }
    }

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.ITEM.get()

    override fun matchingStacks(): List<ItemStack> {
       return listOf(BuiltInRegistries.ITEM.get(itemKey)!!.defaultInstance)
    }

    companion object {
        val ID = "item"
        val CODEC = RecordCodecBuilder.mapCodec { it.group(ResourceKey.codec(Registries.ITEM).fieldOf("key").forGetter(ItemIngredient::itemKey)).apply(it, ::ItemIngredient) }
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ItemIngredient> = StreamCodec.composite(ResourceKey.streamCodec(Registries.ITEM), ItemIngredient::itemKey, ::ItemIngredient)
    }
}