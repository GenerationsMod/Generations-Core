package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.battles.model.actor.ActorType
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY
import com.cobblemon.mod.common.api.events.CobblemonEvents.FORME_CHANGE
import com.cobblemon.mod.common.api.events.CobblemonEvents.FRIENDSHIP_UPDATED
import com.cobblemon.mod.common.api.events.CobblemonEvents.HELD_ITEM_POST
import com.cobblemon.mod.common.api.events.CobblemonEvents.LOOT_DROPPED
import com.cobblemon.mod.common.api.events.CobblemonEvents.POKEMON_INTERACTION_GUI_CREATION
import com.cobblemon.mod.common.api.events.drops.LootDroppedEvent
import com.cobblemon.mod.common.api.storage.player.PlayerInstancedDataStoreType
import com.cobblemon.mod.common.api.storage.player.PlayerInstancedDataStoreTypes
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.cobblemon.mod.common.client.gui.interact.wheel.InteractWheelOption
import com.cobblemon.mod.common.client.gui.interact.wheel.Orientation
import com.cobblemon.mod.common.platform.events.PlatformEvents
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.cobblemonResource
import com.cobblemon.mod.common.util.giveOrDropItemStack
import generations.gg.generations.core.generationscore.common.api.player.Caught
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.network.packets.HeadPatPacket
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags.*
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.world.item.FormChanging
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks.SCARECROW
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.RegiShrineBlock
import net.minecraft.client.Minecraft
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootTable
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
            FORME_CHANGE.subscribe(Priority.NORMAL, {(a, b, c) -> GenerationsInstructionProcessor.processDetailsChange(a, b, c) })

//            CobblemonEvents.POKEMON_ENTITY_SPAWN.subscribe(Priority.HIGHEST) { it ->
//
//
//            }

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


                //Loot

                var table = event.pokemon.form.drops
                var drops = table.getDrops().toMutableList()
                var player = event.player

                LOOT_DROPPED.postThen(
                    event = LootDroppedEvent(table, player, null, drops),
                    ifSucceeded = { it.drops.forEach { it.drop(null, player.serverLevel(), player.position(), player) } }
                )
            }

            CobblemonEvents.BATTLE_STARTED_PRE.subscribe(Priority.HIGHEST)  {
                it.battle.actors.filterIsInstance<PlayerBattleActor>()
                    .mapNotNull { it.entity }.forEach {
                    val keyItems = Cobblemon.playerDataManager.getGenericData(it).keyItems

                    keyItems.removeAll(gimmackItems)

                    if(it.inventory.contains(KEY_STONES)) keyItems.add(cobblemonResource("key_stone"))
                    if(it.inventory.contains(DYNAMAX_BANDS)) keyItems.add(cobblemonResource("dynamax_band"))
                    if(it.inventory.contains(TERA_ORBS)) keyItems.add(cobblemonResource("tera_orb"))
                    if(it.inventory.contains(Z_RINGS)) keyItems.add(cobblemonResource("z_ring"))
                }
            }

            FRIENDSHIP_UPDATED.subscribe {
                var player = it.pokemon.getOwnerPlayer() ?: return@subscribe

                if(it.newFriendship >= Cobblemon.config.maxPokemonFriendship && it.pokemon.species.resourceIdentifier == LegendKeys.MANAPHY.species) {

                    if(it.pokemon.persistentData.getBoolean(DataKeys.GAVE_EGG)) return@subscribe

                    player.sendSystemMessage("Due to ${it.pokemon.getDisplayName().string} happiness reaching max, it gave you an egg.".text())

                    player.giveOrDropItemStack(GenerationsItems.PHIONE_EGG.get().defaultInstance, true)
                    it.pokemon.persistentData.putBoolean(DataKeys.GAVE_EGG, true)
                }
            }

            POKEMON_INTERACTION_GUI_CREATION.subscribe {

                it.addOption(Orientation.BOTTOM_LEFT, InteractWheelOption(
                    iconResource = GenerationsCore.id("textures/ui/interact/head_pat.png"),
                    tooltipText = "generations_core.ui.interact.head_pat", colour = { Vector3f(1F, 0F, 0F) }, onPress = {
                    HeadPatPacket(it.pokemonID).sendToServer()
                    Minecraft.getInstance().screen = null
                }))
            }

            HELD_ITEM_POST.subscribe {
                it.returned.item.instanceOrNull<FormChanging>()?.let { formChanging ->
                    if(formChanging.process(it.pokemon, true)) {
                        it.pokemon.getOwnerPlayer()?.sendSystemMessage("generations_core.ability.formchange".asTranslated(it.pokemon.getDisplayName().string))
                    }
                }

                it.received.item.instanceOrNull<FormChanging>()?.let { formChanging ->
                    if(formChanging.process(it.pokemon, false)) {
                        it.pokemon.getOwnerPlayer()?.sendSystemMessage("generations_core.ability.formchange".asTranslated(it.pokemon.getDisplayName().string))
                    }
                }
            }



//            CobblemonEvents.POKEMON_ENTITY_SPAWN.subscribe { it.entity.taskBuilder().infiniteIterations().identifier("castform") }

            LOOT_DROPPED.subscribe(Priority.HIGHEST) {

            }
        }
    }
}
