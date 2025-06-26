package generations.gg.generations.core.generationscore.common.world.shop

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.network.packets.DataRegistrySyncPacket
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class ShopRegistrySyncPacket(shops: Collection<Shop>) : DataRegistrySyncPacket<Shop, ShopRegistrySyncPacket>(shops) {
    override fun encodeEntry(buffer: RegistryFriendlyByteBuf, entry: Shop) {
        entry.encode(buffer)
    }

    override fun decodeEntry(buffer: RegistryFriendlyByteBuf): Shop {
        return Shop.decode(buffer)
    }

    override fun synchronizeDecoded(entries: Collection<Shop>) {
        Shops.reload(entries.associateBy { it.id })
    }

    override val id = ID

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("shop_registry_sync")
        fun decode(buffer: RegistryFriendlyByteBuf) = ShopRegistrySyncPacket(emptyList()).apply { decodeBuffer(buffer) }
    }
}