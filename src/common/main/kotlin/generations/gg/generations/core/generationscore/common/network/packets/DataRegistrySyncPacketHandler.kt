/*
 * Copyright (C) 2023 Cobblemon Contributors
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import net.minecraft.client.Minecraft
import net.minecraft.network.RegistryFriendlyByteBuf

class DataRegistrySyncPacketHandler<P, T : DataRegistrySyncPacket<P, T>> : ClientNetworkPacketHandler<T> {
    override fun handle(packet: T, client: Minecraft) {
        val buffer = requireNotNull(packet.buffer) { "Buffer missing on DataRegistrySyncPacket" }

        packet.entries.clear()
        packet.entries.addAll(buffer.readList { buf ->
            val entry = packet.decodeEntry(buf as RegistryFriendlyByteBuf)
            entry
        }.filterNotNull())
        buffer.release()
        packet.synchronizeDecoded(packet.entries)
    }
}