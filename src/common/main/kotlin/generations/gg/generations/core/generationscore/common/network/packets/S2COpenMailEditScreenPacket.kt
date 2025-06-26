package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand

class S2COpenMailEditScreenPacket(val hand: InteractionHand) : NetworkPacket<S2COpenMailEditScreenPacket> {
    override val id: ResourceLocation = ID;

    override fun encode(buffer: RegistryFriendlyByteBuf) {
        buffer.writeEnum(hand)
    }

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("open_mail_edit_screen")

        fun decode(buf: FriendlyByteBuf): S2COpenMailEditScreenPacket = S2COpenMailEditScreenPacket(buf.readEnum(InteractionHand::class.java))
    }
}