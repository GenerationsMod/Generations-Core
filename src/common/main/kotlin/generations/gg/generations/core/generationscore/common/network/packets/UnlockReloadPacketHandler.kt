package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.GenerationsDataProvider

import net.minecraft.client.Minecraft

object UnlockReloadPacketHandler : ClientNetworkPacketHandler<S2CUnlockReloadPacket> {
    override fun handle(packet: S2CUnlockReloadPacket, minecraft: Minecraft) {
        GenerationsDataProvider.INSTANCE.canReload = true
    }
}
