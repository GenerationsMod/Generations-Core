package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.Cobblemon.storage
import com.cobblemon.mod.common.api.text.plus
import com.cobblemon.mod.common.api.text.text
import generations.gg.generations.core.generationscore.common.util.add
import generations.gg.generations.core.generationscore.common.util.extensions.remove
import generations.gg.generations.core.generationscore.common.util.getPokemon
import generations.gg.generations.core.generationscore.common.util.removePokemon
import generations.gg.generations.core.generationscore.common.util.setLore
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

abstract class PokemonStoringItem(properties: Properties) : Item(properties), PokemonInteraction {

    open fun getPokemonText(stack: ItemStack): Component {
        return stack.getPokemon()?.getDisplayName()?.let { " (".text() + it + ")".text() } ?: Component.empty()
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide && !player.cooldowns.isOnCooldown(this)) {
            val item = player.getItemInHand(usedHand)
            val pokemon = item.getPokemon()
            if (pokemon != null) {
                storage.getParty((player as ServerPlayer)).add(pokemon)
                item.shrink(1)
                item.removePokemon()
                item.setLore(mutableListOf<Component>())
                item.remove(DataComponents.ITEM_NAME)
                if(consumeOnRelease()) item.shrink(1)
                player.level().playSound(null, player, SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER, 1.0f, 1.0f)
                return InteractionResultHolder.sidedSuccess(item, false)
            }
        }
        return super.use(level, player, usedHand)
    }

    override fun isFoil(stack: ItemStack): Boolean = stack.getPokemon() != null

    protected open fun consumeOnRelease(): Boolean = false

    override val isConsumed: Boolean
        get() = false
}