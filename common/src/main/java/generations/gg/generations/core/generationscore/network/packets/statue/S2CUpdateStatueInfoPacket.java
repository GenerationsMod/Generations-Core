package generations.gg.generations.core.generationscore.network.packets.statue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record S2CUpdateStatueInfoPacket(int entityId) implements GenerationsNetworkPacket<S2CUpdateStatueInfoPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("client_update_statue_info");

    public static S2CUpdateStatueInfoPacket decode(FriendlyByteBuf buf) {
        return new S2CUpdateStatueInfoPacket(buf.readInt());
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