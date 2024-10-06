package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.feature.ChoiceSpeciesFeatureProvider
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.util.getOrCreate
import generations.gg.generations.core.generationscore.common.util.getProviderOrNull
import generations.gg.generations.core.generationscore.common.util.isSpecies
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class NectarItem(properties: Properties, private var form: String): Item(properties), PokemonInteraction {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {

        if(entity.pokemon.isSpecies("oricorio")) {
            val provider = entity.pokemon.getProviderOrNull<ChoiceSpeciesFeatureProvider>("oricorio_style") ?: return false
            val feature = provider.getOrCreate(entity.pokemon)

            if(feature.value != form) {
                feature.value = form;
                feature.apply(entity)
                player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
                return true
            }
        }

        return false
    }
}
