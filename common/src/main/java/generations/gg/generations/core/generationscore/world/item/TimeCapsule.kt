package generations.gg.generations.core.generationscore.world.item

import com.cobblemon.mod.common.Cobblemon.storage
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Pokemon.Companion.loadFromNBT
import com.cobblemon.mod.common.pokemon.RenderablePokemon
import dev.architectury.event.EventResult
import generations.gg.generations.core.generationscore.world.item.PixelmonInteractions.PixelmonInteraction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
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
import java.util.*
import java.util.function.Function
import kotlin.jvm.optionals.getOrNull

class TimeCapsule(properties: Properties) : Item(properties), PixelmonInteraction {
    override fun interact(entity: PokemonEntity, player: Player, itemInHandItemStack: ItemStack): EventResult {
        val pokemon = entity.pokemon
        return if (pokemon.belongsTo(player) && pokemon.storeCoordinates.get()?.remove() == true) {
            savePokemon(itemInHandItemStack, pokemon)
            player.level().playSound(
                null,
                entity,
                SoundEvents.ENDERMAN_TELEPORT,
                SoundSource.MASTER,
                1.0f,
                1.0f
            )
            player.cooldowns.addCooldown(this, 20)

            EventResult.interruptTrue()
        } else {
            EventResult.interruptFalse()
        }
    }

    override fun isConsumed(): Boolean {
        return false
    }

    private fun savePokemon(itemInHandItemStack: ItemStack, poke: Pokemon) {
        itemInHandItemStack.getOrCreateTag().put("pokemon", poke.saveToNBT(CompoundTag()))
    }

    private fun removePokemon(itemInHandItemStack: ItemStack) {
        itemInHandItemStack.getOrCreateTag().remove("pokemon")
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide && !player.cooldowns.isOnCooldown(this)) {
            val item = player.getItemInHand(usedHand)
            val pokemon = getPokemon(item)
            if (pokemon.isPresent) {
                storage.getParty((player as ServerPlayer)).add(pokemon.get())
                item.shrink(1)
                removePokemon(item)
                player.level().playSound(null, player, SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER, 1.0f, 1.0f)
                return InteractionResultHolder.sidedSuccess(item, false)
            }
        }
        return super.use(level, player, usedHand)
    }

    override fun getName(stack: ItemStack): Component {
        return getPokemon(stack).getOrNull()?.getDisplayName() ?: super.getName(stack)
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        getPokemon(stack).ifPresent {
            tooltipComponents += "Level ${it.level} | ${it.gender}".text()
            tooltipComponents += "${it.nature.name} ${it.mintedNature?.let { "(${it.name})" } ?: ""} | Ability: ${it.ability.name}".text()
            tooltipComponents += "Aspects: ${it.aspects}".text()
            tooltipComponents += "Ball: ${it.caughtBall.name.toLanguageKey()}".text()
            tooltipComponents += "Moves:".text()
            tooltipComponents += "${it.moveSet[0]?.displayName?.string ?: "n/a"} | ${it.moveSet[1]?.displayName?.string ?: "n/a"}".text()
            tooltipComponents += "${it.moveSet[2]?.displayName?.string ?: "n/a"} | ${it.moveSet[3]?.displayName?.string ?: "n/a"}".text()
        }

        super.appendHoverText(stack, level, tooltipComponents, isAdvanced)
    }

    companion object {
        fun getRenderablePokmon(stack: ItemStack): RenderablePokemon? {
            return getPokemon(stack).map { it.asRenderablePokemon() }
                .orElse(null)
        }

        fun getPokemon(stack: ItemStack): Optional<Pokemon> {
            if(stack.item is StatueSpawnerItem) {
                return Optional.ofNullable((stack.item as StatueSpawnerItem).pokemon)
            }

            return if (stack.hasTag() && stack.tag!!.contains("pokemon")) {
                Optional.of(
                    loadFromNBT(
                        stack.getTagElement("pokemon")!!
                    )
                )
            } else {
                Optional.empty()
            }
        }
    }
}
