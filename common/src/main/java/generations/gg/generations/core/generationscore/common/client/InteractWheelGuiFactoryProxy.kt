/*
 * Copyright (C) 2023 Cobblemon Contributors
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package generations.gg.generations.core.generationscore.common.client

import com.cobblemon.mod.common.CobblemonNetwork
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.events.pokemon.interaction.PokemonInteractionGUICreationEvent
import com.cobblemon.mod.common.client.CobblemonClient
import com.cobblemon.mod.common.client.gui.interact.battleRequest.BattleConfigureGUI
import com.cobblemon.mod.common.client.gui.interact.wheel.InteractWheelGUI
import com.cobblemon.mod.common.client.gui.interact.wheel.InteractWheelOption
import com.cobblemon.mod.common.client.gui.interact.wheel.Orientation
import com.cobblemon.mod.common.net.messages.client.PlayerInteractOptionsPacket
import com.cobblemon.mod.common.net.messages.server.battle.SpectateBattlePacket
import com.cobblemon.mod.common.net.messages.server.pokemon.interact.InteractPokemonPacket
import com.cobblemon.mod.common.net.messages.server.trade.AcceptTradeRequestPacket
import com.cobblemon.mod.common.net.messages.server.trade.OfferTradePacket
import com.cobblemon.mod.common.util.cobblemonResource
import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork
import generations.gg.generations.core.generationscore.common.network.packets.GensInteractPokemonPacket
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import java.util.*
import org.joml.Vector3f

fun createPokemonInteractGui(pokemonID: UUID, canMountShoulder: Boolean, changeFormData: Pair<Boolean, String>): InteractWheelGUI {
    var path = ""
    var aspect = changeFormData.second
    var ttt = "Transform Pokemon"

    if (changeFormData.first) {
        aspect = when {
            "mega" in aspect -> {
                path = "textures/ui/interact/icon_mega.png"
                aspect
            }
            "kyogre" in aspect -> {
                path = "textures/ui/interact/icon_primal_kyogre.png"
                "primal"
            }
            "groudon" in aspect -> {
                path = "textures/ui/interact/icon_primal_groudon.png"
                "primal"
            }
            aspect == "revert" -> {
                path = "textures/ui/interact/icon_revert.png"
                ttt = "Revert Form"
                aspect
            }
            aspect == "ultra" -> {
                path = "textures/ui/interact/icon_ultraburst.png"
                aspect
            }
            else -> aspect
        }
    }

    val fixedFormData = changeFormData.first to aspect
    val mountShoulder = InteractWheelOption(
        iconResource = cobblemonResource("textures/gui/interact/icon_shoulder.png"),
        tooltipText = "cobblemon.ui.interact.mount.shoulder",
        onPress = {
            if (canMountShoulder) {
                InteractPokemonPacket(pokemonID, true).sendToServer()
                closeGUI()
            }
        }
    )
    val giveItem = InteractWheelOption(
        iconResource = cobblemonResource("textures/gui/interact/icon_held_item.png"),
        tooltipText = "cobblemon.ui.interact.give.item",
        onPress = {
            InteractPokemonPacket(pokemonID, false).sendToServer()
            closeGUI()
        }
    )

    val changeForm = InteractWheelOption(
        iconResource = GenerationsCore.id(path),
        tooltipText = ttt,
        onPress = {
            GenerationsNetwork.sendToServer(
                GensInteractPokemonPacket(pokemonID, false, fixedFormData)
            )
            closeGUI()
        }
    )
    val options: Multimap<Orientation, InteractWheelOption> = ArrayListMultimap.create()
    options.put(Orientation.TOP_RIGHT, giveItem)
    if (canMountShoulder) {
        options.put(Orientation.TOP_LEFT, mountShoulder)
    }
    if (changeFormData.first) {
        options.put(Orientation.BOTTOM_RIGHT, changeForm)
    }
    CobblemonEvents.POKEMON_INTERACTION_GUI_CREATION.post(PokemonInteractionGUICreationEvent(pokemonID, canMountShoulder, options))
    return InteractWheelGUI(options, Component.translatable("cobblemon.ui.interact.pokemon"))
}

private fun closeGUI() {
    Minecraft.getInstance().setScreen(null)
}