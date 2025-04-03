/*
 * Copyright (C) 2023 Cobblemon Contributors
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import io.netty.buffer.Unpooled
import net.minecraft.network.RegistryFriendlyByteBuf

//Direct Copy of Cobblemon's class due to internal method.
abstract class DataRegistrySyncPacket<T, N : NetworkPacket<N>>(private val registryEntries: Collection<T>) : NetworkPacket<N> {

    var buffer: RegistryFriendlyByteBuf? = null
    internal val entries = arrayListOf<T>()

    override fun encode(buffer: RegistryFriendlyByteBuf) {
        val newBuffer = RegistryFriendlyByteBuf(Unpooled.buffer(), buffer.registryAccess())
        newBuffer.writeCollection(registryEntries) { _, entry -> encodeEntry(newBuffer, entry) }
        buffer.writeInt(newBuffer.readableBytes())
        buffer.writeBytes(newBuffer)
        // TODO (techdaan): should newBuffer be released here? I don't think writeBytes does that for us.
    }

    internal fun decodeBuffer(buffer: RegistryFriendlyByteBuf) {
        val size = buffer.readInt()
        val newBuffer = RegistryFriendlyByteBuf(buffer.readBytes(size), buffer.registryAccess())
        this.buffer = newBuffer
    }

    /**
     * Encodes an entry of type [T] to the [RegistryFriendlyByteBuf].
     *
     * @param buffer The [RegistryFriendlyByteBuf] being encoded to.
     * @param entry The entry of type [T].
     */
    abstract fun encodeEntry(buffer: RegistryFriendlyByteBuf, entry: T)

    /**
     * Attempts to decode this entry, if null it will be skipped.
     * Any errors that result in a null entry should be logged.
     *
     * @param buffer The [RegistryFriendlyByteBuf] being decoded from.
     * @return The entry of type [T].
     */
    abstract fun decodeEntry(buffer: RegistryFriendlyByteBuf): T?

    /**
     * Synchronizes the final product the final product with the backing registry.
     *
     * @param entries The processed entries.
     */
    abstract fun synchronizeDecoded(entries: Collection<T>)

}