package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ShopScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.entity.ShopOfferProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class S2COpenShopPacket implements GenerationsNetworkPacket<S2COpenShopPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("open_shop");
    private final int entityId;
    private final BlockPos pos;

    public S2COpenShopPacket(int entityId) {
        this.entityId = entityId;
        this.pos = null;
    }

    public S2COpenShopPacket(BlockPos pos) {
        this.entityId = -1;
        this.pos = pos;
    }

    public S2COpenShopPacket(FriendlyByteBuf buf) {
        if (buf.readBoolean()) {
            this.entityId = buf.readInt();
            this.pos = null;
        } else {
            this.entityId = -1;
            this.pos = buf.readBlockPos();
        }
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        if (entityId >= 0) {
            buf.writeBoolean(true);
            buf.writeInt(this.entityId);
        } else {
            buf.writeBoolean(false);
            buf.writeBlockPos(pos);
        }
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ClientNetworkPacketHandler<S2COpenShopPacket> {

        @Override
        public void handle(S2COpenShopPacket packet) {
            if (Minecraft.getInstance().level == null)
                return;

            if (packet.entityId != -1 && Minecraft.getInstance().level.getEntity(packet.entityId) instanceof ShopOfferProvider shopOfferProvider)
                Minecraft.getInstance().setScreen(new ShopScreen(shopOfferProvider));
            else if (Minecraft.getInstance().level.getBlockEntity(packet.pos) instanceof ShopOfferProvider shopOfferProvider) {
                Minecraft.getInstance().setScreen(new ShopScreen(shopOfferProvider));
            }
        }
    }
}