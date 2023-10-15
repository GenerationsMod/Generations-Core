package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SEditMailHandler implements ServerNetworkPacketHandler<C2SEditMailPacket> {
    public void handle(C2SEditMailPacket packet, MinecraftServer server, ServerPlayer player) {
        server.execute(() -> C2SEditMailPacket.handleEditMail(
                player,
                packet.slot,
                packet.contents,
                packet.title
        ));
    }
}
