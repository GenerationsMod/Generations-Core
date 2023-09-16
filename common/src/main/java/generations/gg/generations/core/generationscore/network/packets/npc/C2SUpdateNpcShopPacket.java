package generations.gg.generations.core.generationscore.network.packets.npc;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public class C2SUpdateNpcShopPacket implements GenerationsNetworkPacket<C2SUpdateNpcShopPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("update_npc_shop");

    private final int entityId;
    @Nullable
    private final ResourceLocation shop;

    public C2SUpdateNpcShopPacket(int entityId, @Nullable ResourceLocation shop) {
        this.entityId = entityId;
        this.shop = shop;
    }

    public C2SUpdateNpcShopPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.shop = buf.readNullable(FriendlyByteBuf::readResourceLocation);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeNullable(this.shop, FriendlyByteBuf::writeResourceLocation);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ServerNetworkPacketHandler<C2SUpdateNpcShopPacket> {
        @Override
        public void handle(C2SUpdateNpcShopPacket packet, MinecraftServer server, ServerPlayer player) {
            PlayerNpcEntity npcEntity = (PlayerNpcEntity) player.level().getEntity(packet.entityId);

            if (npcEntity != null) {
                if (packet.shop == null) {
                    npcEntity.setOffers(null);
                } else {
                    npcEntity.loadOffers(packet.shop);
                }
            }
        }
    }
}