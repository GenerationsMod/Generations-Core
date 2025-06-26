package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand

class S2COpenMailPacket(val hand: InteractionHand) : NetworkPacket<S2COpenMailPacket> {
    override val id: ResourceLocation = ID

    override fun encode(buf: RegistryFriendlyByteBuf) {
        buf.writeEnum(hand)
    }

    companion object {
        var ID = GenerationsCore.id("open_mail")
        fun decode(buf: RegistryFriendlyByteBuf): S2COpenMailPacket {
            return S2COpenMailPacket(buf.readEnum(InteractionHand::class.java))
        }
    }
}