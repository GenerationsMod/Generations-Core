package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.text.plus
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Species
import dev.architectury.registry.item.ItemPropertiesRegistry
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.getPokemon
import generations.gg.generations.core.generationscore.common.util.removeIfBelongs
import generations.gg.generations.core.generationscore.common.util.savePokemon
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

class TimeCapsule(properties: Properties) : PokemonStoringItem(properties) {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        val pokemon = entity.pokemon
        return if (pokemon.removeIfBelongs(player)) {
            stack.savePokemon(pokemon)
            player.level().playSound(
                null,
                entity,
                SoundEvents.ENDERMAN_TELEPORT,
                SoundSource.MASTER,
                1.0f,
                1.0f
            )
            player.cooldowns.addCooldown(this, 20)

            true
        } else {
            false
        }
    }

    override fun getName(stack: ItemStack): Component {
        return super.getName(stack).copy() + getPokemonText(stack)
    }

    override val isConsumed: Boolean
        get() = false


    companion object {
        fun ItemStack.getRenderablePokmon(): Pair<Species, Set<String>>? = this.getPokemon()?.let { it.species to it.aspects }

        fun registerItemProperty() {
            ItemPropertiesRegistry.register(
                GenerationsItems.TIME_CAPSULE.get(), GenerationsCore.id("has_pokemon")
            ) { itemStack: ItemStack, clientLevel: ClientLevel?, livingEntity: LivingEntity?, i: Int ->
                if (itemStack.getRenderablePokmon() == null) 0f else 1f
            }
        }
    }
}
