package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.feature.ChoiceSpeciesFeatureProvider
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import generations.gg.generations.core.generationscore.common.util.getProviderOrNull

interface FormChanging {
    val provider: String

    fun process(pokemon : Pokemon, returned: Boolean): Boolean
}

interface FormChangingString : FormChanging {
    val value: String

    override fun process(pokemon: Pokemon, returned: Boolean): Boolean {
        var provider: ChoiceSpeciesFeatureProvider? = null
        val value: String

        if (returned) {
            provider = pokemon.getProviderOrNull<ChoiceSpeciesFeatureProvider>(this.provider)
            value = provider?.default ?: ""
        } else {
            provider = pokemon.getProviderOrNull<ChoiceSpeciesFeatureProvider>(this.provider)
            value = this.value
        }

        if (provider != null) {
            val feature = provider.get(pokemon)

            if (feature != null) {
                feature.value = value
                feature.apply(pokemon)

                pokemon.entity?.also { it ->
                    feature.apply(pokemon)
                    it.entityData.set(PokemonEntity.ASPECTS, pokemon.aspects)
                    return true
                }
            }
        }

        return false
    }
}

interface FormChangingToggle : FormChanging {
    override fun process(pokemon: Pokemon, returned: Boolean): Boolean {
        val provider: FlagSpeciesFeatureProvider? = pokemon.getProviderOrNull<FlagSpeciesFeatureProvider>(this.provider)

        val value: Boolean = !returned

        if (provider != null) {
            val feature = provider.get(pokemon)

            if (feature != null) {
                feature.enabled = value
                feature.apply(pokemon)

                pokemon.entity?.also { it ->
                    feature.apply(pokemon)
                    it.entityData.set(PokemonEntity.ASPECTS, pokemon.aspects)
                    return true
                }
            }
        }

        return false
    }
}

