package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import net.minecraft.core.HolderSet
import net.minecraft.core.component.DataComponentPredicate
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.resources.HolderSetCodec
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

class ItemIngredient(val items: HolderSet<Item>) : GenerationsIngredient {
    constructor(item: ItemLike) : this(HolderSet.direct(item.asItem().builtInRegistryHolder()))

    val stacks = items.map { ItemStack(it) }

    override val id = ID
    override fun matches(stack: ItemStack): Boolean =this.items.contains(stack.itemHolder)

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.ITEM

    override fun matchingStacks(): List<ItemStack> = stacks

    companion object {
        val ID = "item"
        val MAP_CODEC = RecordCodecBuilder.mapCodec<ItemIngredient> { builder -> builder.group(
            HolderSetCodec.create(Registries.ITEM, BuiltInRegistries.ITEM.holderByNameCodec(), false).fieldOf("items").forGetter { it.items },
        ).apply(builder, ::ItemIngredient) }
        val CODEC = MAP_CODEC.codec()
        val STREAM_CODEC = ByteBufCodecs.fromCodecWithRegistries(CODEC)
    }
}