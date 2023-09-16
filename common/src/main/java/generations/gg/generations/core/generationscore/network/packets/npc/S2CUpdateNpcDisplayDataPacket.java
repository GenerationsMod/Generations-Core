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

public class S2CUpdateNpcDisplayDataPacket implements GenerationsNetworkPacket<S2CUpdateNpcDisplayDataPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("update_npc_display_data");

    private final int entityId;

    public S2CUpdateNpcDisplayDataPacket(int entityId) {
        this.entityId = entityId;
    }

    public S2CUpdateNpcDisplayDataPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ClientNetworkPacketHandler<S2CUpdateNpcDisplayDataPacket> {
        @Override
        public void handle(S2CUpdateNpcDisplayDataPacket packet) {
            EnvExecutor.runInEnv(Env.CLIENT, () -> () -> {
                if (Minecraft.getInstance().level == null)
                    return;

                Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId);
                if (entity instanceof PlayerNpcEntity npcEntity)
                    npcEntity.updateDisplayData();
            });
        }
    }
}