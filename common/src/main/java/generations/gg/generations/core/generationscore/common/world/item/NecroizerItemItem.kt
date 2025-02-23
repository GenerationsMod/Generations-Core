package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.moves.BenchedMove
import com.cobblemon.mod.common.api.moves.Moves
import com.cobblemon.mod.common.api.pokemon.feature.ChoiceSpeciesFeatureProvider
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.party
import generations.gg.generations.core.generationscore.common.util.*
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class NecroizerItemItem(private val properties: Properties, private val species: String, private val move: String, private val form: String) : Item(properties), PokemonInteraction {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        if(entity.pokemon.isSpecies("necrozma")) {
            val provider = entity.pokemon.getProviderOrNull<ChoiceSpeciesFeatureProvider>("necrozma_form") ?: return false
            val feature = provider.getOrCreate(entity.pokemon)

            when(feature.value) {
                "" -> {
                    if(entity.pokemon.hasEmbeddedPokemon()) return false

                    val pokemon = player.party().firstOrNull { it.isSpecies(species) } ?: return false
                    if(!entity.pokemon.embedPokemon(pokemon)) {
                        player.sendSystemMessage("${entity.displayName.string} failed to absorb ${pokemon.getDisplayName().string}.".text(), true)
                        return false
                    }

                    feature.value = form
                    feature.apply(entity)

                    Moves.getByName(move)?.run { entity.pokemon.benchedMoves.add(BenchedMove(this, 0)) }

                    player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
                    return true
                }
                form -> {
                    feature.value = ""
                    feature.apply(entity)
                    player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)


                    entity.pokemon.removeMove(move)

                    entity.pokemon.dembedPokemon()?.run { player.party().add(this) }
                    return true
                }
            }
        }

        return false
    }

    override val isConsumed: Boolean
        get() = false
}