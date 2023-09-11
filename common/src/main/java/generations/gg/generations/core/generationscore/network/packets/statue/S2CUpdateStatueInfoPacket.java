package generations.gg.generations.core.generationscore.network.packets.statue;

import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CCloseScreenPacket;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import static dev.architectury.utils.Env.CLIENT;

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

    public static class Handler implements ClientNetworkPacketHandler<S2CUpdateStatueInfoPacket> {
        public static final Handler INSTANCE = new Handler();

        @Override
        public void handle(S2CUpdateStatueInfoPacket packet) {
            EnvExecutor.runInEnv(CLIENT, () -> () -> {
                if (Minecraft.getInstance().level == null)
                    return;

                Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId());
                if (entity instanceof StatueEntity statueEntity)
                    statueEntity.updateStatueData();
            });
        }
    }
}