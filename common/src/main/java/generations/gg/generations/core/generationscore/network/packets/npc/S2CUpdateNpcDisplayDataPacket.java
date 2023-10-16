package generations.gg.generations.core.generationscore.network.packets.npc;

import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

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