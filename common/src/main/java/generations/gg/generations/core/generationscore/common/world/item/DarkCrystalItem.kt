package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.interaction.PokemonEntityInteraction
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block

class DarkCrystalItem(block: Block, properties: Properties) : BlockItemWithLang(block, properties), PokemonEntityInteraction {
    override val accepted: Set<PokemonEntityInteraction.Ownership>
        get() = setOf(PokemonEntityInteraction.Ownership.WILD, PokemonEntityInteraction.Ownership.OWNER, PokemonEntityInteraction.Ownership.OWNED_ANOTHER)

    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        if(GenerationsCore.CONFIG.special.darkCrystalShadowPokemon) {

            if (entity.pokemon.aspects.contains("shadow")) {
                player.displayClientMessage(
                    "generations_core.special.shadow.already".asTranslated(entity.name.string),
                    false
                )
            } else {
                val properties = PokemonProperties.parse("shadow")
                if (entity.apply { properties.apply(this) }.pokemon.aspects.contains("shadow")) {
                    player.displayClientMessage(
                        "generations_core.special.shadow.success".asTranslated(entity.name.string),
                        false
                    )
                    consumeItem(player, stack)
                } else {
                    player.displayClientMessage(
                        "generations_core.special.shadow.failure".asTranslated(entity.name.string),
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
