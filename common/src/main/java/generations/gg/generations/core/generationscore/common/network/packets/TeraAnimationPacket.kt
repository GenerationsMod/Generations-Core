package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.util.cobblemonResource
import net.minecraft.network.RegistryFriendlyByteBuf

class TeraAnimationPacket(val playAnimation: Boolean) : NetworkPacket<TeraAnimationPacket> {
    override val id = ID

    override fun encode(buffer: RegistryFriendlyByteBuf) {
        buffer.writeBoolean(playAnimation)
    }

    companion object {
        val ID = cobblemonResource("tera_animation")

        fun decode(buffer: RegistryFriendlyByteBuf): TeraAnimationPacket {
            val play = buffer.readBoolean()
            return TeraAnimationPacket(play)
        }
    }
}
