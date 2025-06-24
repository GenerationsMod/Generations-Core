package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.Holder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents

class S2CPlaySoundPacket(
    val soundEvent: Holder<SoundEvent> = Holder.direct(SoundEvents.EMPTY),
    val length: Int = 0,
    val play: Boolean = false,
    val trackId: Int = 0
) : NetworkPacket<S2CPlaySoundPacket> {

    override val id: ResourceLocation = ID

    override fun encode(buffer: RegistryFriendlyByteBuf) {
        SoundEvent.STREAM_CODEC.encode(buffer, soundEvent)
        buffer.writeVarInt(length)
        buffer.writeBoolean(play)
        buffer.writeVarInt(trackId)
    }

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("play_sound")

        fun decode(buf: RegistryFriendlyByteBuf): S2CPlaySoundPacket {
            val sound = SoundEvent.STREAM_CODEC.decode(buf)
            val len = buf.readVarInt()
            val play = buf.readBoolean()
            val trackId = buf.readVarInt()
            return S2CPlaySoundPacket(sound, len, play, trackId)
        }
    }
}
