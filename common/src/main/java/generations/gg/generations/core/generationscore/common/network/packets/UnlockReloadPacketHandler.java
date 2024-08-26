package generations.gg.generations.core.generationscore.common.network.packets;

import generations.gg.generations.core.generationscore.common.GenerationsDataProvider;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import net.minecraft.client.Minecraft;

public class UnlockReloadPacketHandler implements ClientNetworkPacketHandler<S2CUnlockReloadPacket> {
    public static final UnlockReloadPacketHandler INSTANCE = new UnlockReloadPacketHandler();

    public void handle(S2CUnlockReloadPacket packet, Minecraft minecraft) {
        GenerationsDataProvider.INSTANCE.canReload = true;
    }
}
