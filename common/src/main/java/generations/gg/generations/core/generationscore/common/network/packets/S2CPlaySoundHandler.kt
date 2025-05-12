package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.util.MovingSoundInstance
import net.minecraft.client.Minecraft
import net.minecraft.sounds.SoundSource

object S2CPlaySoundHandler : com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler<S2CPlaySoundPacket> {
    override fun handle(packet: S2CPlaySoundPacket, minecraft: Minecraft) {
        if (packet.play) {
            minecraft.soundManager.play(
                MovingSoundInstance(
                    packet.soundEvent,
                    SoundSource.RECORDS,
                    { minecraft.player!!.position() },
                    1.0f,
                    1.0f,
                    false,
                    packet.length,
                    0
                )
            )
        } else {
            // Stop playing the sound
            Minecraft.getInstance().soundManager.stop(packet.soundEvent.location, SoundSource.RECORDS)
        }
    }
}
