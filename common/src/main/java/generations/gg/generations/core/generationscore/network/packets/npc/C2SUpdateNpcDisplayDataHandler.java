package generations.gg.generations.core.generationscore.network.packets.npc;

import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SUpdateNpcDisplayDataHandler implements ServerNetworkPacketHandler<C2SUpdateNpcDisplayDataPacket> {
    @Override
    public void handle(C2SUpdateNpcDisplayDataPacket packet, MinecraftServer server, ServerPlayer player) {
        PlayerNpcEntity npcEntity = (PlayerNpcEntity) player.level().getEntity(packet.entityId);
        if (npcEntity != null) {
            npcEntity.setDisplayData(packet.npcDisplayData);
            GenerationsNetwork.INSTANCE.sendToAllTracking(new S2CUpdateNpcDisplayDataPacket(packet.entityId), npcEntity);
        }
    }
}
