package generations.gg.generations.core.generationscore.network.packets.npc;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.world.npc.display.NpcDisplayData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SUpdateNpcDisplayDataPacket implements GenerationsNetworkPacket<C2SUpdateNpcDisplayDataPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("update_npc_display_data");

    private final int entityId;
    private final NpcDisplayData npcDisplayData;

    public C2SUpdateNpcDisplayDataPacket(int entityId, NpcDisplayData npcDisplayData) {
        this.entityId = entityId;
        this.npcDisplayData = npcDisplayData;
    }

    public C2SUpdateNpcDisplayDataPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.npcDisplayData = new NpcDisplayData(buf);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        this.npcDisplayData.serializeToByteBuf(buf);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ServerNetworkPacketHandler<C2SUpdateNpcDisplayDataPacket> {
        @Override
        public void handle(C2SUpdateNpcDisplayDataPacket packet, MinecraftServer server, ServerPlayer player) {
            PlayerNpcEntity npcEntity = (PlayerNpcEntity) player.level().getEntity(packet.entityId);
            if (npcEntity != null) {
                npcEntity.setDisplayData(packet.npcDisplayData);
                GenerationsNetwork.INSTANCE.sendToAllTracking(new S2CUpdateNpcDisplayDataPacket(packet.entityId), npcEntity);
            }
        }
    }
}