package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.util.MovingSoundInstance
import generations.gg.generations.core.generationscore.common.world.sound.WalkmonSoundManager
import net.minecraft.client.Minecraft
import net.minecraft.sounds.SoundSource

object S2CPlaySoundHandler : com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler<S2CPlaySoundPacket> {
    override fun handle(packet: S2CPlaySoundPacket, client: Minecraft) {
        val player = Minecraft.getInstance().player ?: return
        if(packet.play) {
            WalkmonSoundManager.playSound(player.uuid, packet.soundEvent, packet.length, packet.trackId)
        } else {
            WalkmonSoundManager.stopSound(player.uuid)
        }
    }
}
