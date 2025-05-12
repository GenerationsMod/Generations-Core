package generations.gg.generations.core.generationscore.common.config

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.Codecs.nullable
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.nullable
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.set
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import java.util.*
import java.util.stream.Collectors

@JvmRecord
data class SpeciesKey(@JvmField val species: ResourceLocation, @JvmField val aspects: Set<String>?) {
    @JvmOverloads
    constructor(species: String, aspects: Set<String> = setOf<String>()) : this(
        ResourceLocation.parse(species),
        aspects
    )


    fun createProperties(): PokemonProperties {
        val properties = PokemonProperties()
        properties.species = species.path
        if (aspects != null) properties.aspects = aspects
        return properties
    }


    fun createProperties(level: Int): PokemonProperties {
        val properties = createProperties()
        properties.level = level
        return properties
    }

    fun createPokemon(level: Int): Pokemon {
        val properties = createProperties()
        properties.level = level
        val pokemon = properties.create()
        //        pokemon.setAspects(aspects());
        return pokemon
    }


    override fun toString(): String {
        val setString = if (!aspects.isNullOrEmpty()) aspects.stream().collect(Collectors.joining(",", "[", "]")) else ""
        return species.toString() + setString
    }

    constructor(species: ResourceLocation?) : this(species!!, emptySet<String>())

    companion object {

        val CODEC = Codec.STRING.xmap(::fromString, SpeciesKey::toString)
        val STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            SpeciesKey::species,
            ByteBufCodecs.STRING_UTF8.set().asRegistryFriendly().nullable(),
            SpeciesKey::aspects, ::SpeciesKey
        )

        val NULLABLE_STREAM_CODEC = STREAM_CODEC.nullable()

        fun fromPokemon(pokemon: Pokemon): SpeciesKey {
            var aspects: Set<String>? = null

            if (pokemon.aspects.isNotEmpty()) {
                val trackedAspects = GenerationsCore.CONFIG.caught.trackedAspects
                aspects = pokemon.aspects.stream().filter { o: String -> trackedAspects.contains(o) }
                    .collect(Collectors.toSet())
            }

            return SpeciesKey(pokemon.species.resourceIdentifier, aspects)
        }

        @JvmStatic
        fun fromString(input: String): SpeciesKey {
            val parts = input.split("\\[|\\]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            val species = ResourceLocation.parse(parts[0])

            var aspects: Set<String>? = null
            if (parts.size == 2) {
                val values = parts[1]

                if(values.isEmpty()) {
                    aspects = values.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toSet()
                }
            }
            return SpeciesKey(species, aspects)
        }

        fun fromSpecies(species: Species): SpeciesKey {
            return SpeciesKey(species.resourceIdentifier)
        }
    }
}
