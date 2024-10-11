package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.interaction.PokemonEntityInteraction
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

object GenerationsCobblemonInteractions {
    fun registerCustomInteraction(customInteraction: PokemonInteraction) {
        customInteractions.add(customInteraction)
    }
    private fun triggerCustomInteraction(pixelmonEntity: PokemonEntity, player: Player, itemInHand: ItemStack): Boolean {
        return customInteractions
            .map { interaction: PokemonInteraction -> interaction.processInteraction(player as ServerPlayer, pixelmonEntity, itemInHand) }
            .firstOrNull { it }
            ?: false
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
            if (stack.item is PokemonInteraction) {
                (stack.item as PokemonInteraction).processInteraction(player as ServerPlayer, entity, stack)
            } else {
                triggerCustomInteraction(entity, player, stack)
            }
        } else {
            false
        }
    }

    private val customInteractions: MutableList<PokemonInteraction> = ArrayList()
}
