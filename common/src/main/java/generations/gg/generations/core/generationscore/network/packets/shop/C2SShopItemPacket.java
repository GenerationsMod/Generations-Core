package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.player.PlayerMoneyHandler;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.util.ShopUtils;
import generations.gg.generations.core.generationscore.world.entity.ShopOfferProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class C2SShopItemPacket implements GenerationsNetworkPacket<C2SShopItemPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("shop_item");

    private final int npcId, price, amount;
    private final BlockPos pos;
    private final boolean isBuy;
    private final ItemStack itemStack;

    public C2SShopItemPacket(int npcId, ItemStack stack, int price, int amount, boolean isBuy) {
        this.npcId = npcId;
        this.pos = null;
        this.itemStack = stack;
        this.price = price;
        this.amount = amount;
        this.isBuy = isBuy;
    }

    public C2SShopItemPacket(BlockPos pos, ItemStack stack, int price, int amount, boolean isBuy) {
        this.pos = pos;
        this.npcId = -1;
        this.itemStack = stack;
        this.price = price;
        this.amount = amount;
        this.isBuy = isBuy;
    }

    public C2SShopItemPacket(FriendlyByteBuf buf) {
        if(buf.readBoolean()) {
            this.npcId = buf.readInt();
            this.pos = null;
        } else {
            this.npcId = -1;
            this.pos = buf.readBlockPos();
        }

        this.itemStack = buf.readItem();
        this.price = buf.readInt();
        this.amount = buf.readInt();
        this.isBuy = buf.readBoolean();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        if(this.npcId >= 0) {
            buf.writeBoolean(true);
            buf.writeInt(npcId);
        } else {
            buf.writeBoolean(false);
            buf.writeBlockPos(pos);
        }

        buf.writeItem(itemStack);
        buf.writeInt(price);
        buf.writeInt(amount);
        buf.writeBoolean(isBuy);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ServerNetworkPacketHandler<C2SShopItemPacket> {
        @Override
        public void handle(C2SShopItemPacket packet, MinecraftServer server, ServerPlayer player) {
            ShopOfferProvider offerProvider = null;

            if (packet.npcId != -1 && player.level().getEntity(packet.npcId) instanceof ShopOfferProvider provider)
                offerProvider = provider;
            else if (player.level().getBlockEntity(packet.pos) instanceof ShopOfferProvider provider) {
                offerProvider = provider;
            }

            if (offerProvider != null
                    && ShopUtils.validateItemForNpc(offerProvider, packet.itemStack, packet.price, packet.isBuy)) {
                if (packet.isBuy) {
                    ShopUtils.buy(player, packet.itemStack, packet.price, packet.amount);
                } else {
                    ShopUtils.sell(player, packet.itemStack, packet.price, packet.amount);
                }

                PlayerMoneyHandler.of(player).sync(player);
            }
        }
    }
}