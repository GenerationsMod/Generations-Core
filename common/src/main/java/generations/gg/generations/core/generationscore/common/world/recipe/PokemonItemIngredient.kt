package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.CobblemonItems
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.item.PokemonItem
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import generations.gg.generations.core.generationscore.common.util.Codecs.set
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.nullable
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.optional
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.set
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import java.util.*
import kotlin.jvm.optionals.getOrNull

class PokemonItemIngredient(val species: Optional<ResourceLocation>, val aspects: Optional<Set<String>>, val strict: Boolean = false) : GenerationsIngredient {
    override val id = ID

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.POKEMON_ITEM

    override fun matches(stack: ItemStack): Boolean =
        if (stack.`is`(CobblemonItems.POKEMON_MODEL)) {
            val data = (stack.item as PokemonItem).getSpeciesAndAspects(stack) ?: return false

            when {
                species.isEmpty || data.first.resourceIdentifier == species.get() -> aspects.isEmpty || data.second.containsAllOrSome(strict, aspects.get())
                else -> false
            }
        } else false

    override fun matchingStacks(): List<ItemStack> = listOf(species.getOrNull()?.let { PokemonSpecies.getByIdentifier(it) }?.let {
        PokemonItem.from(species = it, aspects = aspects.getOrNull() ?: emptySet())
    } ?: ItemStack.EMPTY)

    companion object {
        val ID = "pokemon_item"
        val CODEC = RecordCodecBuilder.mapCodec { it.group(
            ResourceLocation.CODEC.optionalFieldOf("species").forGetter(PokemonItemIngredient::species),
            Codec.STRING.set().optionalFieldOf("aspects").forGetter(PokemonItemIngredient::aspects),
            Codec.BOOL.optionalFieldOf("strict", true).forGetter(PokemonItemIngredient::strict)
        ).apply(it, ::PokemonItemIngredient) }
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, PokemonItemIngredient> = StreamCodec.composite(ResourceLocation.STREAM_CODEC.optional(), PokemonItemIngredient::species, ByteBufCodecs.STRING_UTF8.set().optional(), PokemonItemIngredient::aspects, ByteBufCodecs.BOOL, PokemonItemIngredient::strict, ::PokemonItemIngredient).asRegistryFriendly();
    }
}

private fun <E> Collection<E>.containsAllOrSome(all: Boolean, aspects: Collection<E>): Boolean = if(all) this.all(aspects::contains) else this.any(aspects::contains)