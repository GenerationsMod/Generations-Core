package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.util.getOrCreate
import generations.gg.generations.core.generationscore.common.util.getProviderOrNull
import generations.gg.generations.core.generationscore.common.util.isSpecies
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class GracideaItem(properties: Properties) : Item(properties), PokemonInteraction {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {

        //TODO: Use tagtag once pokemon species can be use dwith them.

        if(entity.pokemon.isSpecies("shaymin")) {

            val provider = entity.pokemon.getProviderOrNull<FlagSpeciesFeatureProvider>("sky") ?: return false
            val feature = provider.getOrCreate(entity.pokemon)

            val state = when {
                !feature.enabled && entity.level().isDay -> true
                feature.enabled -> false
                else -> null
            } ?: return false

            feature.enabled = state
            feature.apply(entity)
            player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
            return true
        }

        return false
    }
}



