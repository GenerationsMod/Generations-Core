package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public record S2COpenDialogueEditorScreenPacket(ResourceLocation location) implements GenerationsNetworkPacket<S2COpenDialogueEditorScreenPacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeResourceLocation(location);
    }

    public static ResourceLocation ID = GenerationsCore.id("open_dialogue_editor_screen");

    public static S2COpenDialogueEditorScreenPacket decode(FriendlyByteBuf buf) {
        return new S2COpenDialogueEditorScreenPacket(buf.readResourceLocation());
    }


}
