package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.Cobblemon.storage
import com.cobblemon.mod.common.api.text.plus
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Species
import dev.architectury.registry.item.ItemPropertiesRegistry
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.*
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class TimeCapsule(properties: Properties) : PokemonStoringItem(properties) {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        val pokemon = entity.pokemon
        return if (pokemon.tradeable && pokemon.removeIfBelongs(player)) {
            stack.savePokemon(pokemon)

            var list = mutableListOf<Component>()
            list.add(pokemon)
            stack.setLore(list)
            stack.setHoverName(super.getName(stack).copy() + getPokemonText(stack))

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

    override val isConsumed: Boolean
        get() = false


    companion object {

        fun registerItemProperty() {
            ItemPropertiesRegistry.register(
                GenerationsItems.TIME_CAPSULE.get(), GenerationsCore.id("has_pokemon")
            ) { itemStack: ItemStack, clientLevel: ClientLevel?, livingEntity: LivingEntity?, i: Int ->
                if (itemStack.getRenderablePokemon() == null) 0f else 1f
            }
        }
    }
}
