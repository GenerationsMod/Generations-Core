package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;

public record S2CSayDialoguePacket(List<String> text, boolean useNextArrow) implements GenerationsNetworkPacket<S2CSayDialoguePacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public S2CSayDialoguePacket(FriendlyByteBuf buf) {
        this(buf.readList(FriendlyByteBuf::readUtf), buf.readBoolean());
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeCollection(text, FriendlyByteBuf::writeUtf);
        buf.writeBoolean(useNextArrow);
    }

    public static final ResourceLocation ID = id("say_dialogue");

    public static S2CSayDialoguePacket decode(FriendlyByteBuf buf) {
        return new S2CSayDialoguePacket(buf.readList(FriendlyByteBuf::readUtf), buf.readBoolean());
    }

    public static class Handler implements ClientNetworkPacketHandler<S2CSayDialoguePacket> {
        @Override
        public void handle(S2CSayDialoguePacket packet, Minecraft client) {
            if (Minecraft.getInstance().screen instanceof DialogueScreen dialogueScreen) dialogueScreen.activeInfo = new DialogueScreen.SayActiveInfo(packet.text, packet.useNextArrow);
        }
    }
}
