package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

open class PostBattleUpdatingWithItem(
    properties: Properties,
    species: SpeciesKey,
    lang: String,
    triPredicate: (PlayerBattleActor, ItemStack, BattleData) -> Boolean
) : PostBattleUpdatingItemImpl(properties, species, lang, triPredicate), LangTooltip {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) = addText(stack, context, tooltipComponents, tooltipFlag)
}
