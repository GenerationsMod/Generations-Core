package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import java.util.*

data class C2SEditMailPacket(val slot: Int, val contents: String, val title: Optional<String>) : NetworkPacket<C2SEditMailPacket> {

    override val id: ResourceLocation = ID

    override fun encode(buf: RegistryFriendlyByteBuf) {
        buf.writeVarInt(slot)
        buf.writeUtf(contents, 8192)
        buf.writeOptional(
            title
        ) { byteBuf: FriendlyByteBuf?, s: String? -> buf.writeUtf(s, 128) }
    }

    companion object {
        fun decode(buffer: RegistryFriendlyByteBuf): C2SEditMailPacket {
            return C2SEditMailPacket(
                buffer.readVarInt(),
                buffer.readUtf(8192),
                buffer.readOptional { arg: FriendlyByteBuf ->
                    arg.readUtf(
                        128
                    )
                })
        }

        var ID: ResourceLocation = GenerationsCore.id("edit_mail")
    }
}