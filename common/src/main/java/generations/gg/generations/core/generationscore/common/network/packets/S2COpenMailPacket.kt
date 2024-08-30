package generations.gg.generations.core.generationscore.common.network.packets

import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand

@JvmRecord
data class S2COpenMailPacket(val hand: InteractionHand) : GenerationsNetworkPacket<S2COpenMailPacket> {
    override fun getId(): ResourceLocation {
        return ID
    }

    override fun encode(buf: FriendlyByteBuf) {
        buf.writeEnum(hand)
    }

    companion object {
        var ID = GenerationsCore.id("open_mail")
        fun decode(buf: FriendlyByteBuf): S2COpenMailPacket {
            return S2COpenMailPacket(buf.readEnum(InteractionHand::class.java))
        }
    }
}