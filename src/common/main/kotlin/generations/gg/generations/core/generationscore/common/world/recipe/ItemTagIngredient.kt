package generations.gg.generations.core.generationscore.common.world.recipe

import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import generations.gg.generations.core.generationscore.common.util.Codecs.mapCodec
import generations.gg.generations.core.generationscore.common.util.Codecs.tagCodec
import generations.gg.generations.core.generationscore.common.util.Codecs.tagStreamCodec
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class ItemTagIngredient(val itemKey: TagKey<Item>) : GenerationsIngredient {
    override val id = ID

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.ITEM_TAG

    override fun matches(stack: ItemStack): Boolean {
        return stack.`is` { it.`is`(itemKey) }
    }

    override fun matchingStacks(): List<ItemStack> {
        return BuiltInRegistries.ITEM.getTagOrEmpty(itemKey).map { it.value() }.map { it.defaultInstance }.toList()
    }

    companion object {
        val ID = "item_tag"
        val CODEC = mapCodec("item_tag", Registries.ITEM.tagCodec(), ItemTagIngredient::itemKey, ::ItemTagIngredient)
        val STREAM_CODEC = StreamCodec.composite<RegistryFriendlyByteBuf, ItemTagIngredient, TagKey<Item>>(Registries.ITEM.tagStreamCodec(), ItemTagIngredient::itemKey, ::ItemTagIngredient)
    }
}