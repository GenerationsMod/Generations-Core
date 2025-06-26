package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.util.asIdentifierDefaultingNamespace
import com.cobblemon.mod.common.util.toJsonArray
import com.google.gson.JsonObject
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import generations.gg.generations.core.generationscore.common.util.getPokemon
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import net.minecraft.core.component.DataComponents

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

class TimeCapsuleIngredient(val key : SpeciesKey, val strictAspects: Boolean = false) : GenerationsIngredient {
    override val id = ID

    constructor(name: String, strictAspects: Boolean = false): this(
        SpeciesKey(
            name.asIdentifierDefaultingNamespace()
        ), strictAspects)

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.TIME_CAPSULE

    override fun matches(stack: ItemStack): Boolean {
        return if (stack.`is`(GenerationsItems.TIME_CAPSULE)) stack.getPokemon()?.takeIf {
            it.species.resourceIdentifier == key.species && if(key.aspects.isEmpty()) true else if (strictAspects) it.aspects == key.aspects else it.aspects.containsAny(key.aspects)
        } != null else false
    }
    override fun matchingStacks(): List<ItemStack> = listOf(PokemonSpecies.getByIdentifier(key.species)?.let { PokemonItem.from(it, key.aspects ?: emptySet(), 1) }?: Items.APPLE.defaultInstance.also { it.set(DataComponents.ITEM_NAME, Component.literal("Missing species: " + key.species )) } )

    companion object {
        val ID = "time_capsule"
        val CODEC = RecordCodecBuilder.mapCodec { it.group(
            SpeciesKey.CODEC.fieldOf("key",).forGetter(TimeCapsuleIngredient::key),
            Codec.BOOL.fieldOf("strict").forGetter(TimeCapsuleIngredient::strictAspects)
        ).apply(it, ::TimeCapsuleIngredient) }
        val STREAM_CODEC = StreamCodec.composite(SpeciesKey.STREAM_CODEC, TimeCapsuleIngredient::key, ByteBufCodecs.BOOL, TimeCapsuleIngredient::strictAspects, ::TimeCapsuleIngredient)
    }
}

private fun <E> Collection<E>.containsAny(set: Collection<E>?): Boolean = if(set == null) false else this.any(set::contains)

