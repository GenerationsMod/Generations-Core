package generations.gg.generations.core.generationscore.common.network.packets.npc;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record S2CUpdateNpcDisplayDataPacket(int entityId) implements GenerationsNetworkPacket<S2CUpdateNpcDisplayDataPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("update_npc_display_data");

    public S2CUpdateNpcDisplayDataPacket(FriendlyByteBuf buf) {
        this(buf.readInt());
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}