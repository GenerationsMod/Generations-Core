package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;

public class C2SCloseDialoguePacket implements GenerationsNetworkPacket<C2SCloseDialoguePacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {

    }

    public static ResourceLocation ID = id("close_dialgoue");
    public static C2SCloseDialoguePacket decode(FriendlyByteBuf buf) {
        return new C2SCloseDialoguePacket();
    }

}
