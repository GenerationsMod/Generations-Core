package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.dialogue.Dialogue
import com.cobblemon.mod.common.api.dialogue.Dialogues
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
    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        tooltipComponents.add(Component.translatable("item.generations_core.zygarde_cube.tooltip.lore1"))
        tooltipComponents.add(Component.translatable("item.generations_core.zygarde_cube.tooltip.lore2"))
        tooltipComponents.add(Component.translatable("item.generations_core.zygarde_cube.tooltip.lore3"))
        tooltipComponents.add(Component.translatable("item.generations_core.zygarde_cube.tooltip.lore4", stack.damageValue, stack.maxDamage))
//        LangTooltip.appendHoverText(stack, level, tooltipComponents, isAdvanced)
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if(player is ServerPlayer && usedHand == InteractionHand.MAIN_HAND) {
            val dialogue: Dialogue = Dialogues.dialogues[GenerationsCore.id("zygarde_cell")] ?: return InteractionResultHolder.fail(player.mainHandItem)
            player.openDialogue(dialogue)

            return InteractionResultHolder.success(player.mainHandItem)
        }

        return InteractionResultHolder.fail(player.mainHandItem)
    }

    companion object {
        const val FULL = 100
    }
}
