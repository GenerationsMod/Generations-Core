package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.EitherHolder
import net.minecraft.world.item.JukeboxPlayable
import net.minecraft.world.item.JukeboxSong

class S2CPlaySoundPacket(val soundEvent: SoundEvent,  val length: Int, val play: Boolean) : NetworkPacket<S2CPlaySoundPacket> {
    override val id: ResourceLocation = ID
    constructor(event: SoundEvent) : this(event, 0, false)

    constructor(record: JukeboxSong) : this(record.soundEvent.value(), record.lengthInTicks(), true)

    override fun encode(buf: RegistryFriendlyByteBuf) {
        SoundEvent.DIRECT_STREAM_CODEC.encode(buf, soundEvent)
        buf.writeVarInt(length)
        buf.writeBoolean(play)
    }

    companion object {
        var ID: ResourceLocation = GenerationsCore.id("play_sound")

        fun decode(buf: FriendlyByteBuf): S2CPlaySoundPacket {
            return S2CPlaySoundPacket(SoundEvent.DIRECT_STREAM_CODEC.decode(buf), buf.readVarInt(), buf.readBoolean())
        }
    }
}
