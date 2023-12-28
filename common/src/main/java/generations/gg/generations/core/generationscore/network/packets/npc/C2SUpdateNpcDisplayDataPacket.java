package generations.gg.generations.core.generationscore.network.packets.npc;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.npc.display.NpcDisplayData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record C2SUpdateNpcDisplayDataPacket(int entityId, NpcDisplayData npcDisplayData) implements GenerationsNetworkPacket<C2SUpdateNpcDisplayDataPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("update_npc_display_data");

    public C2SUpdateNpcDisplayDataPacket(FriendlyByteBuf buf) {
        this(buf.readInt(), new NpcDisplayData(buf));
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        this.npcDisplayData.serializeToByteBuf(buf);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}