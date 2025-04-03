package generations.gg.generations.core.generationscore.common.network.packets.shop

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.api.player.ClientPlayerMoney
import net.minecraft.client.Minecraft

object S2CSyncPlayerMoneyHandler : ClientNetworkPacketHandler<S2CSyncPlayerMoneyPacket> {
    override fun handle(packet: S2CSyncPlayerMoneyPacket, minecraft: Minecraft) {
        ClientPlayerMoney.balance = packet.balance
    }
}
