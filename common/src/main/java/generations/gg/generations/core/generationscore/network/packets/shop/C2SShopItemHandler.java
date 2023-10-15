package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.api.player.PlayerMoneyHandler;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.util.ShopUtils;
import generations.gg.generations.core.generationscore.world.entity.ShopOfferProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SShopItemHandler implements ServerNetworkPacketHandler<C2SShopItemPacket> {
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
