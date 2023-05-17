package generations.gg.generations.core.generationscore.network;

import dev.architectury.networking.NetworkChannel;
import dev.architectury.networking.NetworkManager;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.C2SToggleCookingPotPacket;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class GenerationsNetworking {
    public static NetworkChannel NETWORK = NetworkChannel.create(GenerationsCore.id("networking"));
    
    public static void init() {
        NETWORK.register(C2SToggleCookingPotPacket.class, C2SToggleCookingPotPacket::encode, C2SToggleCookingPotPacket::new, new BiConsumer<C2SToggleCookingPotPacket, Supplier<NetworkManager.PacketContext>>() {
            @Override
            public void accept(C2SToggleCookingPotPacket c2SToggleCookingPotPacket, Supplier<NetworkManager.PacketContext> packetContextSupplier) {
                c2SToggleCookingPotPacket.process(packetContextSupplier);
            }
        });
    }

    public static void sendPacket(Object packet) {
        NETWORK.sendToServer(packet);
    }
}
