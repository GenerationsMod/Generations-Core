package generations.gg.generations.core.generationscore.world.item

import com.cobblemon.mod.common.api.gui.drawPortraitPokemon
import com.cobblemon.mod.common.api.interaction.PokemonEntityInteraction
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeatures
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.GenerationsCore
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block

class DarkCrystalItem(block: Block?, properties: Properties?) : BlockItemWithLang(block, properties), PokemonEntityInteraction {
    override val accepted: Set<PokemonEntityInteraction.Ownership>
        get() = setOf(PokemonEntityInteraction.Ownership.WILD, PokemonEntityInteraction.Ownership.OWNER, PokemonEntityInteraction.Ownership.OWNED_ANOTHER)

    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        if(GenerationsCore.CONFIG.special.darkCrystalShadowPokemon) {

            if (entity.pokemon.aspects.contains("shadow")) {
                player.displayClientMessage(
                    "generations_core.special.shadow.already".asTranslated(entity.displayName.string),
                    false
                )
            } else {
                var properties = PokemonProperties.parse("shadow")
                if (entity.apply { properties.apply(this) }.pokemon.aspects.contains("shadow")) {
                    player.displayClientMessage(
                        "generations_core.special.shadow.success".asTranslated(entity.displayName.string),
                        false
                    )
                    consumeItem(player, stack)
                } else {
                    player.displayClientMessage(
                        "generations_core.special.shadow.failure".asTranslated(entity.displayName.string),
                        false
                    )
                }
            }

            return true
        } else {
            return false
        }
    }
}
