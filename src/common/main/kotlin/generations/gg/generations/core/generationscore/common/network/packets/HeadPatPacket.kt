package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import java.util.*

class HeadPatPacket(val pokemonId: UUID) : NetworkPacket<HeadPatPacket> {
    override val id: ResourceLocation = ID
    constructor(buffer: RegistryFriendlyByteBuf) : this(buffer.readUUID())

    override fun encode(buffer: RegistryFriendlyByteBuf) {
        buffer.writeUUID(pokemonId)
    }

    companion object {
        val ID = GenerationsCore.id("interact_pokemon")!!
    }
}