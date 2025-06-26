package generations.gg.generations.core.generationscore.common.network.packets.statue

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class S2COpenStatueEditorScreenPacket(val entityId: Int) : NetworkPacket<S2COpenStatueEditorScreenPacket> {
    override val id: ResourceLocation = ID
    override fun encode(buf: RegistryFriendlyByteBuf) {
        buf.writeInt(entityId)
    }

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("open_statue_editor_screen")

        fun decode(buf: RegistryFriendlyByteBuf): S2COpenStatueEditorScreenPacket {
            return S2COpenStatueEditorScreenPacket(buf.readInt())
        }
    }
}