package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.feature.ChoiceSpeciesFeatureProvider
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import generations.gg.generations.core.generationscore.common.util.getOrCreate
import generations.gg.generations.core.generationscore.common.util.getProviderOrNull
import net.minecraft.resources.ResourceLocation

interface FormChanging {
    val provider: String

    val species: ResourceLocation?

    fun process(pokemon : Pokemon, returned: Boolean): Boolean
}

interface FormChangingString : FormChanging {
    val value: String

    override fun process(pokemon: Pokemon, returned: Boolean): Boolean {
        if(pokemon.entity?.level()?.isClientSide == true) return false
        if(species != null && (species?.equals(pokemon.species.resourceIdentifier) == false)) return false

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
            val feature = provider.getOrCreate(pokemon)

            feature.value = value
            pokemon.markFeatureDirty(feature)
            pokemon.updateAspects()
            return true
        }

        return false
    }
}

interface FormChangingToggle : FormChanging {
    override fun process(pokemon: Pokemon, returned: Boolean): Boolean {
        if(pokemon.entity?.level()?.isClientSide == true) return false;
        val provider: FlagSpeciesFeatureProvider? = pokemon.getProviderOrNull<FlagSpeciesFeatureProvider>(this.provider)

        val value: Boolean = !returned

        if (provider != null) {
            val feature = provider.getOrCreate(pokemon)
            feature.enabled = value
            pokemon.markFeatureDirty(feature)
            pokemon.updateAspects()
            return true
        }

        return false
    }
}

