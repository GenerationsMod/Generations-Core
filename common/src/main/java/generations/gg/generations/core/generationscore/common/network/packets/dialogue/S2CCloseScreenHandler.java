package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import net.minecraft.client.Minecraft;

public class S2CCloseScreenHandler implements ClientNetworkPacketHandler<S2CCloseScreenPacket> {
    public static final S2CCloseScreenHandler INSTANCE = new S2CCloseScreenHandler();

    public void handle(S2CCloseScreenPacket packet, Minecraft minecraft) {
        Minecraft.getInstance().setScreen(null);
    }
}
