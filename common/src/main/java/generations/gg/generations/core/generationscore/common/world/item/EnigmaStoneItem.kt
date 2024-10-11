package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.dialogue.Dialogue
import com.cobblemon.mod.common.api.dialogue.Dialogues
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.cobblemon.mod.common.util.openDialogue
import com.google.common.collect.Streams
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class EnigmaStoneItem(properties: Properties?) : ItemWithLangTooltipImpl(properties), PostBattleUpdatingItem {
    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (player is ServerPlayer) {
            if (hand == InteractionHand.MAIN_HAND) {
                if (player.mainHandItem.damageValue >= player.mainHandItem.maxDamage) {
                    val dialogue: Dialogue = Dialogues.dialogues[GenerationsCore.id("eon_spawn")] ?: return InteractionResultHolder.fail(player.mainHandItem)

                    player.openDialogue(dialogue)

                    return InteractionResultHolder.success(player.mainHandItem)
                } else {
                    player.sendSystemMessage(Component.literal("You need to defeat/capture " + (player.mainHandItem.maxDamage - player.mainHandItem.damageValue) + " Psychic/Dragon pokemon to use this stone."))
                }
            }
        }
        return super.use(level, player, hand)
    }

    override fun checkData(player: PlayerBattleActor, stack: ItemStack, pixelmonData: BattleData): Boolean {
        return Streams.stream<ElementalType>(pixelmonData.pokemon.species.types)
            .anyMatch { type: ElementalType -> type == ElementalTypes.PSYCHIC || type == ElementalTypes.DRAGON }
    }
}
