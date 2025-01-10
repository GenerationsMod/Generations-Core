package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.interaction.PokemonEntityInteraction
import com.cobblemon.mod.common.api.pokemon.feature.ChoiceSpeciesFeatureProvider
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.feature.TagSeasonResolver
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.util.cycle
import generations.gg.generations.core.generationscore.common.util.getOrCreate
import generations.gg.generations.core.generationscore.common.util.getProviderOrNull
import generations.gg.generations.core.generationscore.common.util.isSpecies
import net.minecraft.server.level.ServerPlayer
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

object GenerationsCobblemonInteractions {
    fun registerCustomInteraction(customInteraction: PokemonInteraction) {
        customInteractions.add(customInteraction)
    }
    fun triggerCustomInteraction(pixelmonEntity: PokemonEntity, player: ServerPlayer, itemInHand: ItemStack): Boolean {
        return customInteractions.any { interaction: PokemonInteraction -> interaction.processInteraction(player, pixelmonEntity, itemInHand) }
    }

    interface PokemonInteraction : PokemonEntityInteraction {
        override val accepted: Set<PokemonEntityInteraction.Ownership>
            get() = setOf(PokemonEntityInteraction.Ownership.OWNER)

        val isConsumed: Boolean
            get() = true

        override fun consumeItem(player: ServerPlayer, stack: ItemStack, amount: Int) {
            if(isConsumed) super.consumeItem(player, stack, amount)
        }
    }

    fun process(entity: Entity, player: Player, stack: ItemStack): Boolean {
        return if (entity is PokemonEntity) {
            return triggerCustomInteraction(entity, player as ServerPlayer, stack)
        } else {
            false
        }
    }

    fun registerDefaultCustomInteractions() {
        registerCustomInteraction(KeldeoInteraction)
        registerCustomInteraction(MeloettaInteraction)
        registerCustomInteraction(TherianInteraction)
    }

    private val customInteractions: MutableList<PokemonInteraction> = ArrayList()

    object KeldeoInteraction: FlagFeatureChangeInteraction({ entity, stack -> stack.`is`(ItemTags.SWORDS) && entity.pokemon.isSpecies("keldeo") }, "resolute")
    object MeloettaInteraction: FlagFeatureChangeInteraction({ entity, stack -> stack.`is`(GenerationsItems.INERT_RELIC_SONG.get()) && entity.pokemon.isSpecies("meloetta") }, "piroette")
    object TherianInteraction: FlagFeatureChangeInteraction({ _, stack -> stack.`is`(GenerationsItems.REVEAL_GLASS.get()) }, "therian")

    open class FlagFeatureChangeInteraction(private val checkPredicate: (PokemonEntity, ItemStack) -> Boolean, private val key: String, override val isConsumed: Boolean = false) : PokemonInteraction {
        override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
            if (checkPredicate.invoke(entity, stack)) {

                val pokemon = entity.pokemon

                val provider = pokemon.getProviderOrNull<FlagSpeciesFeatureProvider>(key) ?: return false
                val feature = provider.getOrCreate(pokemon)
                feature.enabled = !feature.enabled
                pokemon.markFeatureDirty(feature)
                pokemon.updateAspects()

                player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
                return true
            }

            return false
        }
    }


}
