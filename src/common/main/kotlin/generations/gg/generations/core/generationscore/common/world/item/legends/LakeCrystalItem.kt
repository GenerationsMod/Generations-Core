package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.google.common.collect.Streams
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents.ENCHANTED
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class LakeCrystalItem(properties: Properties, private val pokemonProperties: SpeciesKey) : EnchantableItem(properties), PostBattleUpdatingItem, LangTooltip {

    override fun neededEnchantmentLevel(player: ServerPlayer): Int {
        return 0
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && GenerationsCore.CONFIG.caught.capped(
                player as ServerPlayer,
                pokemonProperties
            )
        ) {
            val stack = player.getItemInHand(usedHand)

            if (!isEnchanted(stack) && stack.damageValue >= stack.maxDamage) {
                PokemonUtil.spawn(pokemonProperties.createPokemon(70), level, player.getOnPos(), player.getYRot())
                stack.set(ENCHANTED.value(), true)
                return InteractionResultHolder.success(stack)
            }
        }

        return super.use(level, player, usedHand)
    }

    override fun checkData(player: PlayerBattleActor, stack: ItemStack, pixelmonData: BattleData): Boolean {
        return !isEnchanted(stack) && Streams.stream<ElementalType>(pixelmonData.pokemon.types)
            .anyMatch { type: ElementalType -> type == ElementalTypes.PSYCHIC }
    }

    override fun tooltipId(stack: ItemStack?): String {
        return this.descriptionId + (if (isEnchanted(stack)) ".enchanted" else "") + ".tooltip"
    }
}
