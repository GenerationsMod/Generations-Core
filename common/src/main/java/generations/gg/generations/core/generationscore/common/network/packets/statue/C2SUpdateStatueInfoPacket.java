package generations.gg.generations.core.generationscore.common.network.packets.statue;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record C2SUpdateStatueInfoPacket(int entityId, StatueEntity.StatueInfo statueInfo) implements GenerationsNetworkPacket<C2SUpdateStatueInfoPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("server_update_statue_info");

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        this.statueInfo.serializeToByteBuf(buf);
    }

    public static C2SUpdateStatueInfoPacket decode(FriendlyByteBuf buf) {
        return new C2SUpdateStatueInfoPacket(buf.readInt(), new StatueEntity.StatueInfo(buf));
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}