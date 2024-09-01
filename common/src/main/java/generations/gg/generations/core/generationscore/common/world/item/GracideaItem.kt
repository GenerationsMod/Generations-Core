package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeatures
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class GracideaItem(properties: Properties) : Item(properties), PokemonInteraction {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {

        var feature = SpeciesFeatures.getFeaturesFor(entity.pokemon.species).filterIsInstance<FlagSpeciesFeatureProvider>().filter { it.keys.contains("sky") }.firstOrNull()?.get(entity.pokemon) ?: return false

        if(!feature.enabled && entity.level().isDay) {
            feature.enabled = true
            entity.pokemon.updateAspects()
            player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
            return true
        }

        return false
    }
}
