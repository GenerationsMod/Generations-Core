package generations.gg.generations.core.generationscore.common.network.packets

import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import java.util.*

class HeadPatPacket(val pokemonId: UUID) : GenerationsNetworkPacket<HeadPatPacket> {

    constructor(buffer: FriendlyByteBuf) : this(buffer.readUUID())

    override fun encode(buffer: FriendlyByteBuf) {
        buffer.writeUUID(pokemonId)
    }

    override fun getId(): ResourceLocation = ID

    companion object {
        val ID = GenerationsCore.id("interact_pokemon")!!
    }
}