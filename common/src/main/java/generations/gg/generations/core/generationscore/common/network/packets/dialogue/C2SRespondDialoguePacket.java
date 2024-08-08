package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.common.GenerationsCore.id;


public record C2SRespondDialoguePacket(String stringResponse) implements GenerationsNetworkPacket<C2SRespondDialoguePacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeUtf(stringResponse);
    }

    public static final ResourceLocation ID = GenerationsCore.id("respond_dialogue");
    public static C2SRespondDialoguePacket decode(FriendlyByteBuf buf) {
        return new C2SRespondDialoguePacket(buf.readUtf());
    }

}
