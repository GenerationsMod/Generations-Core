package generations.gg.generations.core.generationscore.common;

import com.cobblemon.mod.common.api.dialogue.Dialogue
import com.cobblemon.mod.common.api.dialogue.Dialogues
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.cobblemonResource
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
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.Entity

object GenerationsArchitecturyEvents {

    @JvmStatic
    fun init() {
        EntityEvents.JUMP.register {
                if (it is ServerPlayer) it.level().getBlockState(it.blockPosition()).block.takeIf { it is ElevatorBlock }.let { it as? ElevatorBlock }?.takeElevator(it.level(), it.blockPosition().below(), it, Direction.UP)
        }

        InteractionEvent.RIGHT_CLICK_BLOCK.register { player, hand, pos, _ ->

            if (player is ServerPlayer) {
                if (player.level().getBlockState(pos).`is`(GenerationsBlockTags.TAPU_SUMMONING) && player.getItemInHand(hand).`is`(GenerationsItems.SPARKLING_STONE.get())) {


                    val dialogue: Dialogue? = Dialogues.dialogues[cobblemonResource("example")]

                    if(dialogue == null) EventResult.interruptFalse()

                    player.openDialogue(dialogue!!)

                    EventResult.interruptTrue()
                }
            }

            EventResult.pass()
        }

        InteractionEvent.INTERACT_ENTITY.register { player, entity, hand ->
            val stack = player.getItemInHand(hand);
            var result = GenerationsCobblemonInteractions.process(entity, player, stack);

                val item = stack.item
                when {
                    result.interruptsFurtherEvaluation() && item is GenerationsCobblemonInteractions.PixelmonInteraction && item.isConsumed -> stack.shrink(
                        1
                    )

                    stack.`is`(GenerationsItems.ZYGARDE_CUBE.get()) && entity is ZygardeCellEntity -> {
                        if (stack.damageValue != ZygardeCubeItem.FULL) {
                            stack.damageValue += 1;
                            player.displayClientMessage(
                                "item.generations_core.zygarde_cube.tooltip.cell_add".asTranslated(),
                                false
                            )
                            player.level().playSound(
                                null,
                                player.blockPosition(),
                                GenerationsSounds.ZYGARDE_CELL.get(),
                                SoundSource.BLOCKS,
                                0.5f,
                                1.0f
                            )
                            entity.remove(Entity.RemovalReason.DISCARDED);
                            result = EventResult.interruptTrue();
                        } else {
                            player.displayClientMessage(
                                "item.generations_core.zygarde_cube.tooltip.cell_full".asTranslated(),
                                false
                            );
                            result = EventResult.pass();
                        }
                    }
                }

                result
            }
        }
    }
