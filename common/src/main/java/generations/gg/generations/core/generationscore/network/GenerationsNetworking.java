package generations.gg.generations.core.generationscore.network;

import dev.architectury.networking.NetworkChannel;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.C2SEditMailPacket;
import generations.gg.generations.core.generationscore.network.packets.C2SToggleCookingPotPacket;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailEditScreenPacket;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailPacket;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Supplier;

public class GenerationsNetworking {
    public static NetworkChannel NETWORK = NetworkChannel.create(GenerationsCore.id("networking"));

    public static PacketProccessors PROCCESSORS;
    
    public static void init() {
        PROCCESSORS = unsafeRunForDist(() -> ClientPacketProccessors::new, () -> PacketProccessors::new);
        NETWORK.register(C2SToggleCookingPotPacket.class, C2SToggleCookingPotPacket::encode, C2SToggleCookingPotPacket::new, PROCCESSORS::process);
        NETWORK.register(C2SEditMailPacket.class, C2SEditMailPacket::encode, C2SEditMailPacket::new, PROCCESSORS::process);
        NETWORK.register(S2COpenMailEditScreenPacket.class, S2COpenMailEditScreenPacket::encode, S2COpenMailEditScreenPacket::new, PROCCESSORS::process);
        NETWORK.register(S2COpenMailPacket.class, S2COpenMailPacket::encode, S2COpenMailPacket::new, PROCCESSORS::process);
    }

    public static void sendPacket(Object packet) {
        NETWORK.sendToServer(packet);
    }

    public static void sendPacket(ServerPlayer player, Object packet) {
        NETWORK.sendToPlayer(player, packet);
    }


    //TODO: Move to a arch implmentation of DistExecutor in the future.
    public static <T> T unsafeRunForDist(Supplier<Supplier<T>> clientTarget, Supplier<Supplier<T>> serverTarget) {
        return switch (Platform.getEnvironment()) {
            case CLIENT -> clientTarget.get().get();
            case SERVER -> serverTarget.get().get();
            default -> throw new IllegalArgumentException("UNSIDED?");
        };
    }
}
