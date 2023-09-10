package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class S2CCloseScreenPacket implements GenerationsNetworkPacket<S2CCloseScreenPacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {

    }

    public static final ResourceLocation ID = GenerationsCore.id("close_screen");
    public static S2CCloseScreenPacket decode(FriendlyByteBuf buf) {
        return new S2CCloseScreenPacket();
    }


    public static class Handler implements ClientNetworkPacketHandler<S2CCloseScreenPacket> {
        public static final Handler INSTANCE = new Handler();

        public void handle(S2CCloseScreenPacket packet) {
            Minecraft.getInstance().setScreen(null);
        }
    }
}