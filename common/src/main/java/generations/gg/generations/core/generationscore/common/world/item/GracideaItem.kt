package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeatures
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

fun Pokemon.isSpecies(name: String): Boolean = species.resourceIdentifier.path == name

class GracideaItem(properties: Properties) : Item(properties), PokemonInteraction {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {

        //TODO: Use tagtag once pokemon species can be use dwith them.

        if(entity.pokemon.isSpecies("shaymin")) {

            val provider = entity.pokemon.getProviderOrNull<FlagSpeciesFeatureProvider>("sky") ?: return false
            val feature = provider.getOrCreate(entity.pokemon)

            if (!feature.enabled && entity.level().isDay) {
                feature.enabled = true
                feature.apply(entity)
                player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
                return true
            }
        }

        return false
    }
}

private fun FlagSpeciesFeatureProvider.create(enabled: Boolean): FlagSpeciesFeature = FlagSpeciesFeature(keys.first(), enabled)

fun FlagSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon): FlagSpeciesFeature = this.get(pokemon) ?: FlagSpeciesFeature(keys.first()).also { pokemon.features.add(it) }

//fun ChoiceSpeciesFeatureProvider.cycle(value: String): String = this.get(pokemon) ?: FlagSpeciesFeature(keys.first()).also { pokemon.features.add(it) }

inline fun <reified R : CustomPokemonPropertyType<*>> Pokemon.getProviderOrNull(id: String): R? {
    return SpeciesFeatures.getFeaturesFor(species).filterIsInstance<R>().firstOrNull { it.keys.contains(id) };
}



