package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.moves.BenchedMove
import com.cobblemon.mod.common.api.moves.Moves
import com.cobblemon.mod.common.api.pokemon.feature.ChoiceSpeciesFeatureProvider
import com.cobblemon.mod.common.api.pokemon.feature.IntSpeciesFeatureProvider
import com.cobblemon.mod.common.api.text.plus
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.party
import generations.gg.generations.core.generationscore.common.util.*
import net.minecraft.ChatFormatting
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3

class ReinsOfUnityItem(properties: Properties): PokemonStoringItem(properties) {
    private fun getFormAndMove(pokemon: Pokemon): Pair<String, String>? {
        return when {
            pokemon.isSpecies("spectrier") -> "shadow" to "astralbarrage"
            pokemon.isSpecies("glastrier") -> "ice" to " glaciallance"
            else -> null
        }
    }

    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {

        val pokemon = entity.pokemon
        val pokemonInStack = stack.getPokemon()

        if (pokemonInStack == null) {
            if(pokemon.isSpecies("spectrier") || pokemon.isSpecies("glastrier")) {
                if (pokemon.removeIfBelongs(player)) {
                    stack.savePokemon(pokemon)
                    val list = mutableListOf<Component>()
                    list.add(pokemon)
                    stack.setLore(list)
                    stack.set(DataComponents.ITEM_NAME, super.getName(stack).copy().append(getPokemonText(stack)))

                    player.level().playSound(null, entity, SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER, 1.0f, 1.0f)

                    player.sendSystemMessage("generations_core.pokemon.encoded".asTranslated(pokemon.getDisplayName().string))

                    player.cooldowns.addCooldown(this, 20)

                    return true
                }
            } else if(pokemon.isSpecies("calyrex")) {
                val dembeded: Pokemon = entity.pokemon.dembedPokemon()?.also { player.party().add(it) } ?: return false

                val move = getFormAndMove(dembeded)?.second?.also { entity.pokemon.removeMove(it) } ?: return false

                val provider = entity.pokemon.getProviderOrNull<ChoiceSpeciesFeatureProvider>("king_steed") ?: return false
                val feature = provider.getOrCreate(entity.pokemon)

                feature.value = "false"
                feature.apply(entity)

                player.sendSystemMessage(
                    "generations_core.pokemon.defused".asTranslated(
                        dembeded.getDisplayName().string,
                        pokemon.getDisplayName().string
                    )
                )

                return true
            }
        } else if (pokemon.isSpecies("calyrex")) {

            val provider = entity.pokemon.getProviderOrNull<ChoiceSpeciesFeatureProvider>("king_steed") ?: return false
            val feature = provider.getOrCreate(entity.pokemon)

            if (!entity.pokemon.hasEmbeddedPokemon()) {
                if (feature.value.isBlank() || feature.value == "false") {

                    val data = getFormAndMove(pokemonInStack) ?: return false

                    val form = data.first
                    val move = data.second

                    feature.value = form
                    feature.apply(entity)

                    Moves.getByName(move)?.run { entity.pokemon.benchedMoves.add(BenchedMove(this, 0)) }

                    pokemon.embedPokemon(pokemonInStack, needsToBeInWorld = false)
                    stack.removePokemon()

                    player.sendSystemMessage(
                        "generations_core.pokemon.fused".asTranslated(
                            pokemon.getDisplayName().string,
                            pokemonInStack.getDisplayName().string
                        )
                    )

                    return true
                }
            }
        }

        return false
    }



    override fun getPokemonText(stack: ItemStack): Component {

        var color = ChatFormatting.WHITE
        stack.getPokemon()?.run {
            if(this.isSpecies("spectrier")) color = ChatFormatting.DARK_PURPLE
        }

        return stack.getPokemon()?.getDisplayName()?.let { " (".text() + it + ")".text() }?.withStyle(color) ?: Component.empty()
    }
}