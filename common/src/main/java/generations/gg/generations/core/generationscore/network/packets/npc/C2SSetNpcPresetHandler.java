package generations.gg.generations.core.generationscore.network.packets.npc;

import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SSetNpcPresetHandler implements ServerNetworkPacketHandler<C2SSetNpcPresetPacket> {
    @Override
    public void handle(C2SSetNpcPresetPacket packet, MinecraftServer server, ServerPlayer player) {
        PlayerNpcEntity npcEntity = (PlayerNpcEntity) player.level().getEntity(packet.entityId());
        if (npcEntity != null && player.hasPermissions(4)) {
            npcEntity.loadPreset(packet.preset());
        }
    }
}
