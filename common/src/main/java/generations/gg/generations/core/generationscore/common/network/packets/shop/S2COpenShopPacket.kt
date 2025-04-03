package generations.gg.generations.core.generationscore.common.network.packets.shop

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class S2COpenShopPacket(val entityId: Int, val pos: BlockPos?) : NetworkPacket<S2COpenShopPacket> {
    override val id: ResourceLocation = ID
    constructor(entityId: Int) : this(entityId, null)

    constructor(pos: BlockPos) : this(-1, pos)

    override fun encode(buf: RegistryFriendlyByteBuf) {
        if (entityId >= 0) {
            buf.writeBoolean(true)
            buf.writeInt(this.entityId)
        } else {
            buf.writeBoolean(false)
            buf.writeBlockPos(pos!!)
        }
    }

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("open_shop")

        fun decode(buf: RegistryFriendlyByteBuf): S2COpenShopPacket {
            return if (buf.readBoolean()) S2COpenShopPacket(buf.readInt()) else S2COpenShopPacket(buf.readBlockPos())
        }
    }
}