package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.google.common.collect.Streams
import generations.gg.generations.core.generationscore.common.world.item.ItemWithLangTooltipImpl
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import net.minecraft.world.item.ItemStack

class SingleElmentPostUpdatingItem(properties: Properties, private val type: ElementalType) : ItemWithLangTooltipImpl(properties), PostBattleUpdatingItem, LangTooltip {
    override fun checkData(player: PlayerBattleActor, stack: ItemStack, pixelmonData: BattleData): Boolean {
        return pixelmonData.pokemon.types.any { elementalType: ElementalType -> elementalType == type }
    }
}
