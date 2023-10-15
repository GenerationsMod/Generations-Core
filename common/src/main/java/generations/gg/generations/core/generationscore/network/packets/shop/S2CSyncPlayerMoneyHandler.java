package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.api.player.ClientPlayerMoney;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;

public class S2CSyncPlayerMoneyHandler implements ClientNetworkPacketHandler<S2CSyncPlayerMoneyPacket> {
    @Override
    public void handle(S2CSyncPlayerMoneyPacket packet) {
        ClientPlayerMoney.balance = packet.balance;
    }
}
