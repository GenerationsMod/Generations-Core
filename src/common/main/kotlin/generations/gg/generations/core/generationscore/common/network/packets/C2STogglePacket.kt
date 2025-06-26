package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class C2STogglePacket(val pos: BlockPos?) : NetworkPacket<C2STogglePacket> {
    override val id: ResourceLocation = ID
    constructor() : this(null)

    override fun encode(buf: RegistryFriendlyByteBuf) {
        buf.writeNullable(pos, FriendlyByteBuf::writeBlockPos)
    }

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("toggle_cooking_pot")

        fun decode(buffer: RegistryFriendlyByteBuf): C2STogglePacket {
            return C2STogglePacket(buffer.readNullable(FriendlyByteBuf::readBlockPos))
        }
    }
}
