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
import net.minecraft.world.level.Level

class RotomCatalog(properties: Properties) : Item(properties) {
    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (player is ServerPlayer) {
            if (hand == InteractionHand.MAIN_HAND) {
                val dialogue: Dialogue = Dialogues.dialogues[GenerationsCore.id("rotom_catalog")] ?: return InteractionResultHolder.fail(player.mainHandItem)

                player.openDialogue(dialogue)

                return InteractionResultHolder.success(player.mainHandItem)
            }
        }
        return super.use(level, player, hand)
    }
}