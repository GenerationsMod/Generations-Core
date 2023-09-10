package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record S2COpenDialogueMenuPacket(boolean closable) implements GenerationsNetworkPacket<S2COpenDialogueMenuPacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeBoolean(closable);
    }

    public static final ResourceLocation ID = GenerationsCore.id("open_dialogue_menu");
    public static S2COpenDialogueMenuPacket decode(FriendlyByteBuf buf) {
        return new S2COpenDialogueMenuPacket(buf.readBoolean());
    }

    public static class Handler implements ClientNetworkPacketHandler<S2COpenDialogueMenuPacket> {
        public static final Handler INSTANCE = new Handler();

        @Override
        public void handle(@NotNull S2COpenDialogueMenuPacket packet) {
            Minecraft.getInstance().setScreen(new DialogueScreen(packet.closable));
        }
    }
}
