package generations.gg.generations.core.generationscore.common.network.packets;

import com.cobblemon.mod.common.util.MovingSoundInstance;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundSource;

public class S2CPlaySoundHandler implements ClientNetworkPacketHandler<S2CPlaySoundPacket> {
    @Override
    public void handle(S2CPlaySoundPacket packet, Minecraft minecraft) {
        if (packet.play()) {
            minecraft.getSoundManager().play(new MovingSoundInstance(packet.soundEvent(), SoundSource.RECORDS, () -> minecraft.player.position(), 1.0f, 1.0f, false, packet.length(), 0));
        } else {
            // Stop playing the sound
            Minecraft.getInstance().getSoundManager().stop(packet.soundEvent().getLocation(), SoundSource.RECORDS);
        }
    }
}
