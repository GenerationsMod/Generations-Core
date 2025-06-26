package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.world.item.RecordSongs.MELOETTAS_RELIC_SONG
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class RelicSongItem(properties: Properties, private val notInert: Boolean) : Item(
    properties.jukeboxPlayable(
        MELOETTAS_RELIC_SONG
    )
),
    LangTooltip {
    override fun isFoil(stack: ItemStack): Boolean {
        return !notInert
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: TooltipContext, tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        addText(stack, level, tooltipComponents, isAdvanced)
    }
}
