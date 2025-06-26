package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.google.common.collect.Streams
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.ItemWithLangTooltipImpl
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.Biomes

class TimeGlassItem(arg: Properties) : ItemWithLangTooltipImpl(arg), PostBattleUpdatingItem, LangTooltip {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(usedHand)
        if (!level.isClientSide() && GenerationsCore.CONFIG!!.caught.capped(
                player as ServerPlayer,
                LegendKeys.CELEBI
            )
        ) {
            val damage = stack.damageValue
            if (damage >= stack.maxDamage) {
                if (level.getBiome(player.getOnPos()).`is`(Biomes.FLOWER_FOREST)) {
                    PokemonUtil.spawn(LegendKeys.CELEBI.createPokemon(70), level, player.getOnPos(), player.getYRot())
                    player.getItemInHand(usedHand).shrink(1)
                } else player.displayClientMessage(
                    Component.translatable("generations_core.timeglass.wrongbiome"),
                    true
                )
            } else {
                player.displayClientMessage(Component.translatable("generations_core.timeglass.amount", damage), true)
            }

            return InteractionResultHolder.success(stack)
        }


        return super.use(level, player, usedHand)
    }

    override fun checkData(player: PlayerBattleActor, stack: ItemStack, pixelmonData: BattleData): Boolean {
        return pixelmonData.pokemon.species.types.any { type: ElementalType -> type == ElementalTypes.PSYCHIC || type == ElementalTypes.GRASS }
    }
}
