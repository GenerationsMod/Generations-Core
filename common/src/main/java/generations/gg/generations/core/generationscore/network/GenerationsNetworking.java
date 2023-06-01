package generations.gg.generations.core.generationscore.network;

import dev.architectury.networking.NetworkChannel;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.C2SEditMailPacket;
import generations.gg.generations.core.generationscore.network.packets.C2SToggleCookingPotPacket;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailEditScreenPacket;
import net.minecraft.server.level.ServerPlayer;

public class GenerationsNetworking {
    public static NetworkChannel NETWORK = NetworkChannel.create(GenerationsCore.id("networking"));
    
    public static void init() {
        NETWORK.register(C2SToggleCookingPotPacket.class, C2SToggleCookingPotPacket::encode, C2SToggleCookingPotPacket::new, C2SToggleCookingPotPacket::process);
        NETWORK.register(C2SEditMailPacket.class, C2SEditMailPacket::encode, C2SEditMailPacket::new, C2SEditMailPacket::process);
        NETWORK.register(S2COpenMailEditScreenPacket.class, S2COpenMailEditScreenPacket::encode, S2COpenMailEditScreenPacket::new, S2COpenMailEditScreenPacket::process);
    }

    public static void sendPacket(Object packet) {
        NETWORK.sendToServer(packet);
    }

    public static void sendPacket(ServerPlayer player, Object packet) {
        NETWORK.sendToPlayer(player, packet);
    }
}
