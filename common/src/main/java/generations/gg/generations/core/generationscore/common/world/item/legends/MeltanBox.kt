package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.party
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

fun <T> Iterable<T>?.anySafe(predicate: (T) -> Boolean): Boolean = this?.any(predicate) ?: false

class MeltanBox(settings: Properties) : PostBattleUpdatingWithItem(
    settings,
    LegendKeys.MELMETAL,
    "pixelmon.meltanbox.amountfull", { player, _, battle: PostBattleUpdatingItem.BattleData ->
        player.entity?.party()?.map(Pokemon::species)?.map(Species::resourceIdentifier)?.map(ResourceLocation::toString).anySafe { it == "cobblemon:meltan" } && battle.pokemon.types.anySafe { it == ElementalTypes.STEEL }
    }) {

    override fun interactLivingEntity(
        stack: ItemStack,
        player: Player,
        target: LivingEntity,
        hand: InteractionHand
    ): InteractionResult {
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS
        val level = player.level()
        if (level.isClientSide) return InteractionResult.CONSUME

        if (target is PokemonEntity) {
            if (target.pokemon.species.name != "Meltan") return InteractionResult.PASS

            if (stack.damageValue < stack.maxDamage) {
                stack.damageValue += 1
                target.remove(Entity.RemovalReason.DISCARDED)
                player.cooldowns.addCooldown(stack.item, 20)

                (player as? ServerPlayer)?.displayClientMessage(
                    Component.translatable("item.generations_core.meltan_box.tooltip.meltan_add"),
                    false
                )
                level.playSound(
                    null, target.blockPosition(),
                    GenerationsSounds.ZYGARDE_CELL.value(),
                    SoundSource.BLOCKS, 0.2f, 1.0f
                )

                return InteractionResult.SUCCESS
            } else {
                if (target.ownerUUID == player.uuid) {
                    val serverPlayer = player as ServerPlayer
                    val melmetal = PokemonSpecies.getByName("melmetal")
                    if (melmetal != null) {
                        target.pokemon.species = melmetal
                        stack.damageValue = 0
                    }

                    serverPlayer.displayClientMessage(
                        Component.translatable("item.generations_core.meltan_box.tooltip.meltan_convert"),
                        false
                    )

                    return InteractionResult.SUCCESS
                } else {
                    (player as? ServerPlayer)?.displayClientMessage(
                        Component.translatable("item.generations_core.meltan_box.tooltip.meltan_full"),
                        false
                    )
                    return InteractionResult.CONSUME
                }
            }
        }

        return InteractionResult.PASS
    }


//    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
//        val stack = player.getItemInHand(usedHand)
//
//        val remaining = stack.maxDamage - stack.damageValue
//        if (remaining == 0) {
//            if (!level.isClientSide) {
//                val serverPlayer = player as ServerPlayer
//                val party = serverPlayer.party()
//
//                var idx = -1
//                var meltan: Pokemon? = null
//
//                for (i in 0 until party.size()) {
//                    val pokemon = party.get(i)
//                    if (pokemon != null && pokemon.species.name == "Meltan") {
//                        idx = i
//                        meltan = pokemon
//                        break
//                    }
//                }
//
//                if (idx == -1) {
//                    serverPlayer.displayClientMessage(
//                        Component.translatable("item.generations_core.meltan_box.tooltip.meltan_none"), false
//                    )
//                    return InteractionResultHolder.pass(stack)
//                }
//
//                val melmetal = PokemonSpecies.getByName("melmetal")
//                println("Melmetal check: ${melmetal?.name}")
//                if (melmetal != null && meltan != null) {
//                    meltan.species = melmetal
//                    party.set(idx, meltan)
//                    stack.damageValue = 0
//                }
//
//                serverPlayer.displayClientMessage(
//                    Component.translatable("item.generations_core.meltan_box.tooltip.meltan_convert"), false
//                )
//            }
//            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide)
//        }
//
//        return InteractionResultHolder.pass(stack)
//    }

    companion object {
        const val FULL = 100
    }
}