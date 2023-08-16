package generations.gg.generations.core.generationscore.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.world.level.block.entities.CookingPotBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

class C2SToggleCookingPotPacket(val pos: BlockPos): GenerationsNetworkPacket<C2SToggleCookingPotPacket> {
    override val id = ID;
    constructor(byteBuf: FriendlyByteBuf) : this(byteBuf.readBlockPos())

    override fun encode(buffer: FriendlyByteBuf) {
        buffer.writeBlockPos(pos)
    }

    companion object {
        var ID = id("toggle_cooking_pot")

        fun decode(buffer: FriendlyByteBuf) = C2SToggleCookingPotPacket(buffer.readBlockPos())
    }

    class Handler: ServerNetworkPacketHandler<C2SToggleCookingPotPacket> {
        override fun handle(packet: C2SToggleCookingPotPacket, server: MinecraftServer, player: ServerPlayer) {
            server.execute {
                val te = player.level().getBlockEntity(packet.pos)
                if (te is CookingPotBlockEntity) te.isCooking = !te.isCooking
            }
        }

    }
}
