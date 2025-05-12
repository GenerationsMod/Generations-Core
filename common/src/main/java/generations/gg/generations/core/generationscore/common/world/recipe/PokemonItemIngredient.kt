package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.CobblemonItems
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.item.PokemonItem
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import generations.gg.generations.core.generationscore.common.util.Codecs.nullable
import generations.gg.generations.core.generationscore.common.util.Codecs.set
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.nullable
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.set
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack

class PokemonItemIngredient(val species: ResourceLocation?, val aspects: Set<String>?, val strict: Boolean = true) : GenerationsIngredient {
    override val id = ID

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.POKEMON_ITEM.get()

    override fun matches(stack: ItemStack): Boolean =
        if (stack.`is`(CobblemonItems.POKEMON_MODEL)) {
            (stack.item as PokemonItem).getSpeciesAndAspects(stack)?.let { ((species == null || it.first.resourceIdentifier == species) && (aspects == null || it.second.containsAllOrSome(true, aspects))) } ?: false
        } else false

    override fun matchingStacks(): List<ItemStack> = listOf(species?.let { PokemonSpecies.getByIdentifier(it) }?.let {
        PokemonItem.from(species = it, aspects = aspects ?: emptySet())
    } ?: ItemStack.EMPTY)

    companion object {
        val ID = "pokemon_item"
        val CODEC = RecordCodecBuilder.mapCodec { it.group(
            ResourceLocation.CODEC.nullable("species", PokemonItemIngredient::species),
            Codec.STRING.set().nullable("aspects", PokemonItemIngredient::aspects),
            Codec.BOOL.optionalFieldOf("strict", true).forGetter(PokemonItemIngredient::strict)
        ).apply(it, ::PokemonItemIngredient) }
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, PokemonItemIngredient> = StreamCodec.composite(ResourceLocation.STREAM_CODEC.nullable(), PokemonItemIngredient::species, ByteBufCodecs.STRING_UTF8.set().nullable(), PokemonItemIngredient::aspects, ByteBufCodecs.BOOL, PokemonItemIngredient::strict, ::PokemonItemIngredient).asRegistryFriendly();
    }
}

private fun <E> Collection<E>.containsAllOrSome(all: Boolean, aspects: Collection<E>): Boolean = if(all) this.all(aspects::contains) else this.any(aspects::contains)