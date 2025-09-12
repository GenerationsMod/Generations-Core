package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.datafixers.util.Function3
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.core.HolderSet
import net.minecraft.core.component.DataComponentPredicate
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.resources.HolderSetCodec
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.util.function.Function

class DataComponentIngredient(val items: HolderSet<Item>, val components: DataComponentPredicate, val strict: Boolean) : GenerationsIngredient {
    val stacks = items.map { ItemStack(it, 1, components.asPatch()) }
    
    override val id = ID
    override fun matches(stack: ItemStack): Boolean {
        if (strict) {
            for (stack2 in this.stacks) {
                if (ItemStack.isSameItemSameComponents(stack, stack2)) return true
            }
            return false
        } else {
            return this.items.contains(stack.itemHolder) && this.components.test(stack)
        }
    }

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.DATA_COMPONENT

    override fun matchingStacks(): List<ItemStack> {
        return stacks
    }

    companion object {
        val ID = "data_component"
        val MAP_CODEC = RecordCodecBuilder.mapCodec<DataComponentIngredient> { builder -> builder.group(
            HolderSetCodec.create(Registries.ITEM, BuiltInRegistries.ITEM.holderByNameCodec(), false).fieldOf("items").forGetter { it.items },
            DataComponentPredicate.CODEC.fieldOf("components").forGetter { it.components },
            Codec.BOOL.optionalFieldOf("strict", false).forGetter { it.strict }
        ).apply(builder, ::DataComponentIngredient) }
        val CODEC = MAP_CODEC.codec()
        val STREAM_CODEC = ByteBufCodecs.fromCodecWithRegistries(CODEC)
    }
}