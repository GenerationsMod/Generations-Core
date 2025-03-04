package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.api.dialogue.Dialogue
import com.cobblemon.mod.common.api.dialogue.Dialogues
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.openDialogue
import dev.architectury.event.EventResult
import dev.architectury.event.events.common.InteractionEvent
import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents
import generations.gg.generations.core.generationscore.common.tags.GenerationsBlockTags
import generations.gg.generations.core.generationscore.common.world.entity.ZygardeCellEntity
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.ZygardeCubeItem
import generations.gg.generations.core.generationscore.common.world.level.block.ElevatorBlock
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import generations.gg.generations.core.generationscore.common.world.level.block.entities.VendingMachineBlock
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.Entity

object GenerationsArchitecturyEvents {

    @JvmStatic
    fun init() {
        EntityEvents.JUMP.register {
            if (it is ServerPlayer) it.level().getBlockState(it.blockPosition()).block.takeIf { it is ElevatorBlock }
                .let { it as? ElevatorBlock }?.takeElevator(it.level(), it.blockPosition().below(), it, Direction.UP)
        }

        InteractionEvent.RIGHT_CLICK_BLOCK.register { player, hand, pos, _ ->

            if (player is ServerPlayer) {
                if (hand == InteractionHand.MAIN_HAND && player.level().getBlockState(pos)
                        .`is`(GenerationsShrines.TAPU_SHRINE.get()) && player.getItemInHand(hand)
                        .`is`(GenerationsItems.SPARKLING_STONE.get())
                ) {
                    val dialogue: Dialogue = Dialogues.dialogues[GenerationsCore.id("tapu_spawn")]
                        ?: return@register EventResult.interruptFalse()
                    player.getItemInHand(hand).shrink(1)
                    player.openDialogue(dialogue)

                    return@register EventResult.interruptTrue()
                } else if (hand == InteractionHand.MAIN_HAND && player.level().getBlockState(pos)
                        .`is`(GenerationsShrines.ABUNDANT_SHRINE.get()) && player.getItemInHand(hand)
                        .`is`(GenerationsItems.REVEAL_GLASS.get())
                ) {
                    val dialogue: Dialogue = Dialogues.dialogues[GenerationsCore.id("therian_spawn")]
                        ?: return@register EventResult.interruptFalse()

                    player.getItemInHand(hand).shrink(1)
                    player.openDialogue(dialogue)

                    return@register EventResult.interruptTrue()
                } else if(player.level().getBlockState(pos).block is VendingMachineBlock) { //TODO: upgrade to a vending machines tag.
//TODO: Restore shopss post release
                }
            }

            EventResult.pass()
        }

        InteractionEvent.INTERACT_ENTITY.register { player, entity, hand ->
            if(player !is ServerPlayer) return@register EventResult.pass()

            val stack = player.getItemInHand(hand)

            if(entity is PokemonEntity) {
                return@register if(GenerationsCobblemonInteractions.triggerCustomInteraction(entity, player, stack)) EventResult.interruptTrue() else EventResult.pass()
            }

            return@register EventResult.pass()
        }
    }
}
