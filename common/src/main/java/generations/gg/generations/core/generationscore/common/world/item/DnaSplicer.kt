package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.Cobblemon.storage
import com.cobblemon.mod.common.api.pokemon.feature.ChoiceSpeciesFeatureProvider
import com.cobblemon.mod.common.api.text.plus
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.party
import generations.gg.generations.core.generationscore.common.util.*
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.item.ItemStack
class DnaSplicer(properties: Properties): PokemonStoringItem(properties) {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {

        val pokemon = entity.pokemon
        val pokemonInStack = stack.getPokemon()

        if (pokemonInStack == null && (pokemon.isSpecies("zekrom") || pokemon.isSpecies("reshiram"))) {
            if (pokemon.removeIfBelongs(player)) {
                stack.savePokemon(pokemon)
                player.level().playSound(null, entity, SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER, 1.0f, 1.0f)

                player.sendSystemMessage("generations_core.pokemon.encoded".asTranslated(pokemon.getDisplayName().string))

                player.cooldowns.addCooldown(this, 20)

                return true
            } else {
                return false
            }
        } else if (pokemon.isSpecies("kyurem")) {

            val provider =
                entity.pokemon.getProviderOrNull<ChoiceSpeciesFeatureProvider>("kyurem_form") ?: return false
            val feature = provider.getOrCreate(entity.pokemon)

            if (pokemonInStack != null && !entity.pokemon.hasEmbeddedPokemon()) {
                if (feature.value.isBlank() || feature.value == "false") {

                    val form = if (pokemonInStack.isSpecies("zekrom")) {
                        "black"
                    } else if (pokemonInStack.isSpecies("reshiram")) {
                        "white"
                    } else {
                        null
                    } ?: return false

                    feature.value = form;
                    feature.apply(entity)

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
            } else {
                val dembeded: Pokemon =
                    entity.pokemon.dembedPokemon()?.also { player.party().add(it) } ?: return false

                feature.value = "false";
                feature.apply(entity)

                player.sendSystemMessage(
                    "generations_core.pokemon.defused".asTranslated(
                        pokemon.getDisplayName().string,
                        dembeded.getDisplayName().string
                    )
                )

                return true
            }
        }

        return false
    }

    override fun getName(stack: ItemStack): Component {
        return super.getName(stack).copy().withStyle(ChatFormatting.GRAY) + getPokemonText(stack)
    }

    override fun getPokemonText(stack: ItemStack): Component {

        var color = ChatFormatting.WHITE
        stack.getPokemon()?.run {
            if(this.isSpecies("zekrom")) color = ChatFormatting.DARK_GRAY
            if(this.isSpecies("reshiram")) color = ChatFormatting.WHITE
        }

        return stack.getPokemon()?.getDisplayName()?.let { " (".text() + it + ")".text() }?.withStyle(color) ?: Component.empty()
    }
}