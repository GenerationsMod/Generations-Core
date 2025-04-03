package generations.gg.generations.core.generationscore.common.network.packets.shop

import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.api.player.PlayerMoneyHandler
import generations.gg.generations.core.generationscore.common.util.ShopUtils
import generations.gg.generations.core.generationscore.common.world.entity.ShopOfferProvider
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

class C2SShopItemHandler : ServerNetworkPacketHandler<C2SShopItemPacket> {
    override fun handle(packet: C2SShopItemPacket, server: MinecraftServer, player: ServerPlayer) {
        var offerProvider: ShopOfferProvider? = null

        if (packet.npcId != -1 && player.level().getEntity(packet.npcId) is ShopOfferProvider) offerProvider = player.level().getEntity(packet.npcId) as ShopOfferProvider
        else if (player.level().getBlockEntity(packet.pos!!) is ShopOfferProvider) {
            offerProvider = player.level().getBlockEntity(packet.pos) as ShopOfferProvider
        }

        if (offerProvider != null
            && ShopUtils.validateItemForNpc(offerProvider, packet.itemStack, packet.price, packet.isBuy)
        ) {
            if (packet.isBuy) {
                ShopUtils.buy(player, packet.itemStack, packet.price, packet.amount)
            } else {
                ShopUtils.sell(player, packet.itemStack, packet.price, packet.amount)
            }

            PlayerMoneyHandler.of(player).sync(player)
        }
    }
}
