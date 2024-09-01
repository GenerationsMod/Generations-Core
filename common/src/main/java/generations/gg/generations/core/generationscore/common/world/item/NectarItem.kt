package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class NectarItem(properties: Properties, form: String? = null): Item(properties), PokemonInteraction {
    private var aspect = form?.let(::setOf) ?: setOf()
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        var species = PokemonSpecies.getByName("oricorio");

        if(species != null) {
            val form = species.getForm(aspect);

            if (entity.pokemon.species == species) {
                if(entity.pokemon.form != form) {
                    entity.pokemon.form = form
                    player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
                    return true
                }
            }
        }

        return false
    }
}
