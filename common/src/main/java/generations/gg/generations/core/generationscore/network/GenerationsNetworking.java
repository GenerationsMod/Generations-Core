package generations.gg.generations.core.generationscore.network;

import dev.architectury.networking.NetworkChannel;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.C2SEditMailPacket;
import generations.gg.generations.core.generationscore.network.packets.C2SToggleCookingPotPacket;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailEditScreenPacket;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailPacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.*;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Supplier;

public class GenerationsNetworking {
    public static NetworkChannel NETWORK = NetworkChannel.create(GenerationsCore.id("networking"));

    public static PacketProccessors PROCCESSORS;
    
    public static void init() {
        PROCCESSORS = unsafeRunForDist(() -> ClientPacketProccessors::new, () -> PacketProccessors::new);

        //Cooking Pot
        NETWORK.register(C2SToggleCookingPotPacket.class, C2SToggleCookingPotPacket::encode, C2SToggleCookingPotPacket::new, PROCCESSORS::process);

        //Mail
        NETWORK.register(C2SEditMailPacket.class, C2SEditMailPacket::encode, C2SEditMailPacket::new, PROCCESSORS::process);
        NETWORK.register(S2COpenMailEditScreenPacket.class, S2COpenMailEditScreenPacket::encode, S2COpenMailEditScreenPacket::new, PROCCESSORS::process);
        NETWORK.register(S2COpenMailPacket.class, S2COpenMailPacket::encode, S2COpenMailPacket::new, PROCCESSORS::process);

        //Dialogues
        NETWORK.register(C2SCloseDialoguePacket.class, C2SCloseDialoguePacket::encode, C2SCloseDialoguePacket::new, PROCCESSORS::process);
        NETWORK.register(C2SRequestNodesDialoguePacket.class, C2SRequestNodesDialoguePacket::encode, C2SRequestNodesDialoguePacket::new, PROCCESSORS::process);
        NETWORK.register(C2SRespondDialoguePacket.class, C2SRespondDialoguePacket::encode, C2SRespondDialoguePacket::new, PROCCESSORS::process);
        NETWORK.register(S2CChooseDialoguePacket.class, S2CChooseDialoguePacket::encode, S2CChooseDialoguePacket::new, PROCCESSORS::process);
        NETWORK.register(S2CHealDialoguePacket.class, S2CHealDialoguePacket::encode, S2CHealDialoguePacket::new, PROCCESSORS::process);
        NETWORK.register(S2COpenDialogueEditorScreenPacket.class, S2COpenDialogueEditorScreenPacket::encode, S2COpenDialogueEditorScreenPacket::new, PROCCESSORS::process);
        NETWORK.register(S2COpenDialogueMenuPacket.class, S2COpenDialogueMenuPacket::encode, S2COpenDialogueMenuPacket::new, PROCCESSORS::process);
        NETWORK.register(S2CSayDialoguePacket.class, S2CSayDialoguePacket::encode, S2CSayDialoguePacket::new, PROCCESSORS::process);
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
