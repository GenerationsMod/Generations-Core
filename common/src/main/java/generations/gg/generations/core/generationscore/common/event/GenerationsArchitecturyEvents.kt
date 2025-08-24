package generations.gg.generations.core.generationscore.common.event

import com.cobblemon.mod.common.CobblemonEntities
import com.cobblemon.mod.common.api.dialogue.Dialogue
import com.cobblemon.mod.common.api.dialogue.Dialogues
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.openDialogue
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.api.events.TriState
import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents
import generations.gg.generations.core.generationscore.common.api.events.general.InteractionEvents
import generations.gg.generations.core.generationscore.common.battle.applyBattleFeature
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.biome.GenerationsBiomes
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.ElevatorBlock
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.entities.VendingMachineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.RegiShrineBlock
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand

object GenerationsArchitecturyEvents {

    @JvmStatic
    fun init() {
        EntityEvents.JUMP.subscribe {
            val player = it.entity.instanceOrNull<ServerPlayer>() ?: return@subscribe
            player.serverLevel().getBlockState(player.blockPosition()).block.instanceOrNull<ElevatorBlock>()?.takeElevator(player.serverLevel(), player.blockPosition().below(), player, Direction.UP)
        }

        CobblemonEvents.ENTITY_SPAWN.subscribe { //TODO: add exceptions and maybe a scarcrow tag
            val entity = it.entity
            val level = it.ctx.world
            val pos = entity.blockPosition()
            val biome = level.getBiome(pos).value()
            if(entity.type == CobblemonEntities.POKEMON || entity.type == GenerationsEntities.ZYGARDE_CELL) {

                val list = RegiShrineBlock.Companion.searchForBlock(
                    level,
                    pos,
                    GenerationsCore.CONFIG.blocks.scarecrowRadius.x,
                    GenerationsCore.CONFIG.blocks.scarecrowRadius.y,
                    GenerationsCore.CONFIG.blocks.scarecrowRadius.z,
                    1
                ) { world, pos -> world.getBlockState(pos).`is`(GenerationsUtilityBlocks.SCARECROW.value()) }
                if (list.isNotEmpty()) {
                    it.cancel()
                }

                val biomeRegistry = level.registryAccess().registryOrThrow(Registries.BIOME)
                if (biome == biomeRegistry.get(GenerationsBiomes.TERASTAL_CAVES)) {
                    if (entity.type == CobblemonEntities.POKEMON) {
                        val pokemonEntity = entity as PokemonEntity
                        val pokemon = pokemonEntity.pokemon

                        val random = (0..4).random()
                        if (random == 0) {
                            pokemon.teraType = TeraTypes.random(true)
                            val teraCheck = FlagSpeciesFeature("terastal_active", true)
                            pokemon.applyBattleFeature(teraCheck)
                        }
                    }
                }
            }
        }

        InteractionEvents.RIGHT_CLICK_BLOCK.subscribe { event ->
            val player = event.player
            val hand = event.hand
            val pos = event.pos

            if (player is ServerPlayer) {
                if (hand == InteractionHand.MAIN_HAND && player.level().getBlockState(pos)
                        .`is`(GenerationsShrines.TAPU_SHRINE) && player.getItemInHand(hand)
                        .`is`(GenerationsItems.SPARKLING_STONE)
                ) {
                    val dialogue: Dialogue = Dialogues.dialogues[GenerationsCore.id("tapu_spawn")]
                        ?: run {
                            event.result = TriState.FALSE
                            return@subscribe
                        }
                    player.getItemInHand(hand).shrink(1)
                    player.openDialogue(dialogue)

                    event.result = TriState.TRUE
                    return@subscribe
                } else if (hand == InteractionHand.MAIN_HAND && player.level().getBlockState(pos)
                        .`is`(GenerationsShrines.ABUNDANT_SHRINE) && player.getItemInHand(hand)
                        .`is`(GenerationsItems.REVEAL_GLASS)
                ) {
                    val dialogue: Dialogue = Dialogues.dialogues[GenerationsCore.id("therian_spawn")]
                        ?: run {
                            event.result = TriState.FALSE
                            return@subscribe
                        }

                    player.getItemInHand(hand).shrink(1)
                    player.openDialogue(dialogue)

                    event.result = TriState.TRUE
                    return@subscribe
                } else if(player.level().getBlockState(pos).block is VendingMachineBlock) { //TODO: upgrade to a vending machines tag.
                    //TODO: Restore shopss post release
                }
            }
        }

        InteractionEvents.ENTITY_INTERACT.subscribe {
            val player = it.player
            val hand = it.hand
            val entity = it.entity
            if(player !is ServerPlayer) return@subscribe

            val stack = player.getItemInHand(hand)

            if(entity is PokemonEntity) {
                if(GenerationsCobblemonInteractions.triggerCustomInteraction(entity, player, stack)) {
                    TriState.TRUE
                }
                return@subscribe
            }

            return@subscribe
        }

    }
}