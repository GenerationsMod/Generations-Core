package generations.gg.generations.core.generationscore.common.network.packets.shop

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class C2SCloseShopPacket : NetworkPacket<C2SCloseShopPacket> {
    override val id: ResourceLocation = ID

    override fun encode(buffer: RegistryFriendlyByteBuf) {}

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("close_shop")
    }
}