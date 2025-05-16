package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.CobblemonEntities
import com.cobblemon.mod.common.api.dialogue.Dialogue
import com.cobblemon.mod.common.api.dialogue.Dialogues
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.openDialogue
import dev.architectury.event.EventResult
import dev.architectury.event.events.common.EntityEvent
import dev.architectury.event.events.common.InteractionEvent
import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.ElevatorBlock
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks.SCARECROW
import generations.gg.generations.core.generationscore.common.world.level.block.entities.VendingMachineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.RegiShrineBlock
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand

object GenerationsArchitecturyEvents {

    @JvmStatic
    fun init() {
        EntityEvents.JUMP.subscribe {
            val player = it.entity.instanceOrNull<ServerPlayer>() ?: return@subscribe
            player.serverLevel().getBlockState(player.blockPosition()).block.instanceOrNull<ElevatorBlock>()?.takeElevator(player.serverLevel(), player.blockPosition().below(), player, Direction.UP)
        }

        EntityEvent.ADD.register { entity, level -> //TODO: add exceptions and maybe a scarcrow tag
            if(entity.type == CobblemonEntities.POKEMON || entity.type == GenerationsEntities.ZYGARDE_CELL) {

                val list = RegiShrineBlock.searchForBlock(
                    level,
                    entity.blockPosition(),
                    GenerationsCore.CONFIG!!.blocks.scarecrowRadius.x,
                    GenerationsCore.CONFIG!!.blocks.scarecrowRadius.y,
                    GenerationsCore.CONFIG!!.blocks.scarecrowRadius.z,
                    1
                ) { world, pos -> world.getBlockState(pos).`is`(SCARECROW.get()) }
                if (list.isNotEmpty()) {
                    EventResult.interruptTrue()
                }
            }

            EventResult.pass()
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
