package generations.gg.generations.core.generationscore.network

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.GenerationsDataProvider
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf

class S2CUnlockReloadPacket : NetworkPacket<S2CUnlockReloadPacket> {
    companion object {
        val ID = id("unlock_reload")

        fun decode(buffer: FriendlyByteBuf) = S2CUnlockReloadPacket()
    }

    override val id = ID
    override fun encode(buffer: FriendlyByteBuf) {}

     object UnlockReloadPacketHandler : ClientNetworkPacketHandler<S2CUnlockReloadPacket> {
         override fun handle(packet: S2CUnlockReloadPacket, client: Minecraft) {
             GenerationsDataProvider.canReload = true
         }
     }
}