package generations.gg.generations.core.generationscore.common.network.packets.statue;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record S2COpenStatueEditorScreenPacket(int entityId) implements GenerationsNetworkPacket<S2COpenStatueEditorScreenPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("open_statue_editor_screen");

    public static S2COpenStatueEditorScreenPacket decode(FriendlyByteBuf buf) {
        return new S2COpenStatueEditorScreenPacket(buf.readInt());
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(entityId());
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}