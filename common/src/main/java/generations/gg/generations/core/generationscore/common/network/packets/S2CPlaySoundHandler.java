package generations.gg.generations.core.generationscore.common.network.packets;

import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class S2CPlaySoundHandler implements ClientNetworkPacketHandler<S2CPlaySoundPacket> {
    @Override
    public void handle(S2CPlaySoundPacket packet) {
        if (packet.play()) {
            // Start playing the sound
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forMusic(SoundEvent.createVariableRangeEvent(packet.soundLocation())));
        } else {
            // Stop playing the sound
            Minecraft.getInstance().getSoundManager().stop(packet.soundLocation(), SoundSource.RECORDS);
        }
    }
}
