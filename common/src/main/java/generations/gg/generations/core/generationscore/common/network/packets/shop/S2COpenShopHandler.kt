package generations.gg.generations.core.generationscore.common.network.packets.shop

import generations.gg.generations.core.generationscore.common.client.screen.ShopScreen
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.world.entity.ShopOfferProvider
import net.minecraft.client.Minecraft

object S2COpenShopHandler : ClientNetworkPacketHandler<S2COpenShopPacket> {
    override fun handle(packet: S2COpenShopPacket, minecraft: Minecraft) {
        if (Minecraft.getInstance().level == null) return

        var entity = Minecraft.getInstance().level!!.getEntity(packet.entityId)

        if (packet.entityId != -1 && entity is ShopOfferProvider) Minecraft.getInstance().setScreen(ShopScreen(entity))
        else {
            var blockEntity = Minecraft.getInstance().level!!.getBlockEntity(packet.pos);

            if (blockEntity is ShopOfferProvider) {
                Minecraft.getInstance().setScreen(ShopScreen(blockEntity))
            }
        }
    }
}
