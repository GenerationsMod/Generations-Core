package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class S2CUnlockReloadPacket : NetworkPacket<S2CUnlockReloadPacket> {
    override val id: ResourceLocation = ID

    override fun encode(buffer: RegistryFriendlyByteBuf) {}

    companion object {
        var ID: ResourceLocation = GenerationsCore.id("unlock_reload")

        fun decode(buffer: RegistryFriendlyByteBuf): S2CUnlockReloadPacket {
            return S2CUnlockReloadPacket()
        }
    }
}