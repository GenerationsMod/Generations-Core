package generations.gg.generations.core.generationscore.network;

import dev.architectury.networking.NetworkChannel;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.C2SToggleCookingPotPacket;

public class GenerationsNetworking {
    public static NetworkChannel NETWORK = NetworkChannel.create(GenerationsCore.id("networking"));
    
    public static void init() {
        NETWORK.register(C2SToggleCookingPotPacket.class, C2SToggleCookingPotPacket::encode, C2SToggleCookingPotPacket::new, C2SToggleCookingPotPacket::process);
    }

    public static void sendPacket(Object packet) {
        NETWORK.sendToServer(packet);
    }
}
