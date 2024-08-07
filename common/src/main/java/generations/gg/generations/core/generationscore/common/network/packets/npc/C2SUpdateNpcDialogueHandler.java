package generations.gg.generations.core.generationscore.common.network.packets.npc;

import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SUpdateNpcDialogueHandler implements ServerNetworkPacketHandler<C2SUpdateNpcDialoguePacket> {
    @Override
    public void handle(C2SUpdateNpcDialoguePacket packet, MinecraftServer server, ServerPlayer player) {
        PlayerNpcEntity npcEntity = (PlayerNpcEntity) player.level().getEntity(packet.entityId());
        if (npcEntity != null) {
            npcEntity.setDialogue(packet.dialogue());
        }
    }
}
