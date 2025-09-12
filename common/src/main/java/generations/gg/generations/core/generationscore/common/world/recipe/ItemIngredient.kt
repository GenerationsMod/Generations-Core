package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.util.asResource
import com.google.gson.JsonObject
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import io.netty.buffer.ByteBuf
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.HolderSetCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import kotlin.jvm.optionals.getOrNull

class ItemIngredient(val predicate: ItemPredicate) : GenerationsIngredient {
    override val id = ID
    override fun matches(stack: ItemStack): Boolean {
        return predicate.test(stack)
    }

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.ITEM

    override fun matchingStacks(): List<ItemStack> {
       return predicate.items().getOrNull()?.map { it.value() }?.map { it.defaultInstance } ?: listOf()
    }

    companion object {
        val ID = "item"
        val CODEC = ItemPredicate.CODEC.xmap(::ItemIngredient, ItemIngredient::predicate)
        val MAP_CODEC = CODEC.fieldOf("predicate")
        val STREAM_CODEC = ByteBufCodecs.fromCodecWithRegistries(CODEC)
    }
}