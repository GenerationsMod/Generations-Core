package generations.gg.generations.core.generationscore.common.network.packets.shop

import generations.gg.generations.core.generationscore.common.client.screen.ShopScreen
import generations.gg.generations.core.generationscore.common.world.entity.ShopOfferProvider
import net.minecraft.client.Minecraft

object S2COpenShopHandler : com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler<S2COpenShopPacket> {
    override fun handle(packet: S2COpenShopPacket, client: Minecraft) {
        if (Minecraft.getInstance().level == null) return

        val entity = Minecraft.getInstance().level!!.getEntity(packet.entityId)

        if (packet.entityId != -1 && entity is ShopOfferProvider) Minecraft.getInstance().setScreen(ShopScreen(entity))
        else {
            val blockEntity = Minecraft.getInstance().level!!.getBlockEntity(packet.pos!!)

            if (blockEntity is ShopOfferProvider) {
                Minecraft.getInstance().execute { Minecraft.getInstance().setScreen(ShopScreen(blockEntity)) }
            }
        }
    }
}
