package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.client.screen.ShopScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.entity.ShopOfferProvider;
import net.minecraft.client.Minecraft;

public class S2COpenShopHandler implements ClientNetworkPacketHandler<S2COpenShopPacket> {

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
