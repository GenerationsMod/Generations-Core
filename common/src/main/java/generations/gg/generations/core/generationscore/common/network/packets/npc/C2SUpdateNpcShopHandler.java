package generations.gg.generations.core.generationscore.common.network.packets.npc;

import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SUpdateNpcShopHandler implements ServerNetworkPacketHandler<C2SUpdateNpcShopPacket> {
    @Override
    public void handle(C2SUpdateNpcShopPacket packet, MinecraftServer server, ServerPlayer player) {
        PlayerNpcEntity npcEntity = (PlayerNpcEntity) player.level().getEntity(packet.entityId());

        if (npcEntity != null) {
            if (packet.shop() == null) {
                npcEntity.setOffers(null);
            } else {
                npcEntity.loadOffers(packet.shop());
            }
        }
    }
}
