package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
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

}
