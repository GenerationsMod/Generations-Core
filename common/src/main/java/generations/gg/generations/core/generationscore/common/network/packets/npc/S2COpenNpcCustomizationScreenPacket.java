package generations.gg.generations.core.generationscore.common.network.packets.npc;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record S2COpenNpcCustomizationScreenPacket(int entityId) implements GenerationsNetworkPacket<S2COpenNpcCustomizationScreenPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("open_npc_customization");

    public S2COpenNpcCustomizationScreenPacket(FriendlyByteBuf buf) {
        this(buf.readInt());
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}