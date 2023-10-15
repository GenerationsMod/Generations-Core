package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.GenerationsDataProvider;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;

public class UnlockReloadPacketHandler implements ClientNetworkPacketHandler<S2CUnlockReloadPacket> {
    public static final UnlockReloadPacketHandler INSTANCE = new UnlockReloadPacketHandler();

    public void handle(S2CUnlockReloadPacket packet) {
        GenerationsDataProvider.INSTANCE.canReload = true;
    }
}
