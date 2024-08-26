package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.client.screen.dialgoue.display.DialogueScreen;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// TODO delay being able to go to the next node through this packet
public record S2CHealDialoguePacket(List<String> text, boolean useNextArrow) implements GenerationsNetworkPacket<S2CHealDialoguePacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeCollection(text, FriendlyByteBuf::writeUtf);
        buf.writeBoolean(useNextArrow);
    }

    public static final ResourceLocation ID = GenerationsCore.id("say_dialogue");

    public static S2CHealDialoguePacket decode(FriendlyByteBuf buf) {
        return new S2CHealDialoguePacket(buf.readList(FriendlyByteBuf::readUtf), buf.readBoolean());
    }

    public static class Handler implements ClientNetworkPacketHandler<S2CHealDialoguePacket> {
        public static final Handler INSTANCE = new Handler();

        public void handle(S2CHealDialoguePacket packet, Minecraft minecraft) {
            if (Minecraft.getInstance().screen instanceof DialogueScreen dialogueScreen)
                dialogueScreen.activeInfo = new DialogueScreen.SayActiveInfo(packet.text, packet.useNextArrow);
        }
    }
}
