package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.dialogue.Dialogue
import com.cobblemon.mod.common.api.dialogue.Dialogues
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.openDialogue
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class ZygardeCubeItem(properties: Properties) : Item(properties), LangTooltip {
    override fun addText(
        stack: ItemStack,
        level: TooltipContext,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        tooltipComponents.add("item.generations_core.zygarde_cube.tooltip.lore1".asTranslated())
        tooltipComponents.add("item.generations_core.zygarde_cube.tooltip.lore2".asTranslated())
        tooltipComponents.add("item.generations_core.zygarde_cube.tooltip.lore3".asTranslated())
        tooltipComponents.add("item.generations_core.zygarde_cube.tooltip.lore4".asTranslated(stack.damageValue, stack.maxDamage))
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(usedHand)

        if(player is ServerPlayer) {
            val dialogue: Dialogue = Dialogues.dialogues[GenerationsCore.id("zygarde_cube")] ?: return InteractionResultHolder.fail(stack)
            player.openDialogue(dialogue)

            return InteractionResultHolder.success(stack)
        }

        return InteractionResultHolder.pass(stack)
    }

    companion object {
        const val FULL = 100
    }
}