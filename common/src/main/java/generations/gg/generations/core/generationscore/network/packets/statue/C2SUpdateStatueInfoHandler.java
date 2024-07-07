package generations.gg.generations.core.generationscore.network.packets.statue;

import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SUpdateStatueInfoHandler implements ServerNetworkPacketHandler<C2SUpdateStatueInfoPacket> {

    @Override
    public void handle(C2SUpdateStatueInfoPacket packet, MinecraftServer server, ServerPlayer player) {
        StatueEntity statueEntity = (StatueEntity) player.level().getEntity(packet.entityId());
        if (statueEntity != null) {
            statueEntity.setStatueInfo(packet.statueInfo());
            GenerationsNetwork.INSTANCE.sendToAllTracking(new S2CUpdateStatueInfoPacket(packet.entityId(), packet.statueInfo()), player);
        }
    }
}
