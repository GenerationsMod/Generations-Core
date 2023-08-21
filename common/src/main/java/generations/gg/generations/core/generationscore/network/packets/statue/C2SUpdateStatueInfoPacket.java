package generations.gg.generations.core.generationscore.network.packets.statue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public record C2SUpdateStatueInfoPacket(int entityId, StatueEntity.StatueInfo statueInfo) implements GenerationsNetworkPacket<C2SUpdateStatueInfoPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("server_update_statue_info");

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        this.statueInfo.serializeToByteBuf(buf);
    }

    public static C2SUpdateStatueInfoPacket decode(FriendlyByteBuf buf) {
        return new C2SUpdateStatueInfoPacket(buf.readInt(), new StatueEntity.StatueInfo(buf));
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ServerNetworkPacketHandler<C2SUpdateStatueInfoPacket> {

        @Override
        public void handle(C2SUpdateStatueInfoPacket packet, MinecraftServer server, ServerPlayer player) {
            StatueEntity statueEntity = (StatueEntity) player.level().getEntity(packet.entityId());
            if (statueEntity != null) {
                statueEntity.setStatueInfo(packet.statueInfo());
                GenerationsNetwork.INSTANCE.sendToAllTracking(new S2CUpdateStatueInfoPacket(packet.entityId()), statueEntity);
            }

        }
    }
}