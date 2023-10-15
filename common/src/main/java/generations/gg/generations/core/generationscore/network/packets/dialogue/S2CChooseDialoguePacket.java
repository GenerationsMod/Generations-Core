package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public record S2CChooseDialoguePacket(String text, List<String> options) implements GenerationsNetworkPacket<S2CChooseDialoguePacket> {

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeUtf(text);
        buf.writeCollection(options, FriendlyByteBuf::writeUtf);
    }

    public static final ResourceLocation ID = GenerationsCore.id("choose_dialogue");
    public static S2CChooseDialoguePacket decode(FriendlyByteBuf buf) {
        return new S2CChooseDialoguePacket(buf.readUtf(), buf.readList(FriendlyByteBuf::readUtf));
    }

}
