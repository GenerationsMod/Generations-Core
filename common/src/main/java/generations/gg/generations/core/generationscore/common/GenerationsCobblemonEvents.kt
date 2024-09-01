package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.battles.model.actor.ActorType
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY
import com.cobblemon.mod.common.api.events.CobblemonEvents.POKEMON_INTERACTION_GUI_CREATION
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeatures
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.cobblemon.mod.common.client.gui.interact.wheel.InteractWheelOption
import com.cobblemon.mod.common.client.gui.interact.wheel.Orientation
import com.cobblemon.mod.common.util.cobblemonResource
import com.cobblemon.mod.common.util.party
import dev.architectury.event.events.common.TickEvent
import generations.gg.generations.core.generationscore.common.api.player.Caught
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.network.packets.HeadPatPacket
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags.*
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks.SCARECROW
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack
import org.joml.Vector3f

class GenerationsCobblemonEvents {

    companion object {
        val gimmackItems: Set<ResourceLocation> = setOf(
            cobblemonResource("key_stone"),
            cobblemonResource("dynamax_band"),
            cobblemonResource("tera_orb"),
            cobblemonResource("z_ring")
        )


        @JvmStatic
        fun init() {
            CobblemonEvents.POKEMON_ENTITY_SPAWN.subscribe(Priority.HIGHEST) { it ->
                var amount = 0
                it.entity.level().getChunk(it.entity.blockPosition()).findBlocks({ it.`is`(SCARECROW.get())}) { pos, state -> amount += 1}
                if(amount > 0) it.cancel()
            }

            BATTLE_VICTORY.subscribe(Priority.HIGH) { event ->
                val data = mutableListOf<BattleData>()

                event.losers.forEach { actor1 ->
                    val isNpc = actor1.type == ActorType.NPC
                    actor1.pokemonList.stream()
                        .map { BattleData(isNpc, it.effectedPokemon, "") }.forEach(data::add)
                }

                event.winners.stream()
                    .filter { battleActor -> battleActor is PlayerBattleActor }
                    .map { battleActor -> battleActor as PlayerBattleActor }
                    .forEach { actor ->
                        actor.entity?.inventory?.items?.asSequence()
                            ?.filter { a -> a.item is PostBattleUpdatingItem }
                            ?.forEach { a -> (a.item as PostBattleUpdatingItem).afterBattle(actor, a, data) }

                        actor.pokemonList.asSequence().map { it.originalPokemon }.forEach { originalPokemon ->
                            val it: ItemStack = originalPokemon.heldItem()
                            if (!it.isEmpty)
                                if (it.item is PostBattleUpdatingItem) {
                                    (it.item as PostBattleUpdatingItem).afterBattle(
                                        actor,
                                        it,
                                        true,
                                        data
                                    )
                                    originalPokemon.swapHeldItem(it, false)
                                }
                        }
                    }
            }

            CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.HIGH) { event ->
                val speciesKey = SpeciesKey.fromPokemon(event.pokemon)
                Caught.get(event.player).accumulate(speciesKey)
            }

            CobblemonEvents.BATTLE_STARTED_PRE.subscribe(Priority.HIGHEST)  {
                it.battle.actors.filter { it is PlayerBattleActor }.map { it as PlayerBattleActor }.map { it.entity }.filterNotNull().forEach {
                    val keyItems = Cobblemon.playerData.get(it).keyItems

                    keyItems.removeAll(gimmackItems)

                    if(it.inventory.contains(KEY_STONES)) keyItems.add(cobblemonResource("key_stone"))
                    if(it.inventory.contains(DYNAMAX_BANDS)) keyItems.add(cobblemonResource("dynamax_band"))
                    if(it.inventory.contains(TERA_ORBS)) keyItems.add(cobblemonResource("tera_orb"))
                    if(it.inventory.contains(Z_RINGS)) keyItems.add(cobblemonResource("z_ring"))
                }
            }

            POKEMON_INTERACTION_GUI_CREATION.subscribe {
                it.addOption(Orientation.BOTTOM_LEFT, InteractWheelOption(
                    iconResource = GenerationsCore.id("textures/ui/interact/head_pat.png"),
                    "generations_core.ui.interact.head_pat", { Vector3f(1F, 0F, 0F) }, {
                    HeadPatPacket(it.pokemonID).sendToServer()
                    Minecraft.getInstance().screen = null
                }))
            }

            TickEvent.PLAYER_PRE.register { player ->
                if (player is ServerPlayer) {
                    player.party().forEach {pokemon ->
                        SpeciesFeatures.getFeaturesFor(pokemon.species).forEach { provider ->
                            if (provider is PlayerAspectProvider)
                        }
                    }
                }
            }

//            CobblemonEvents.POKEMON_ENTITY_SPAWN.subscribe { it.entity.taskBuilder().infiniteIterations().identifier("castform") }

            CobblemonEvents.LOOT_DROPPED.subscribe(Priority.HIGHEST) {

            }
        }
    }
}
