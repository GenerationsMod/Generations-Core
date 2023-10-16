package generations.gg.generations.core.generationscore.network.packets.npc;

import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public class S2CUpdateNpcDisplayDataHandler implements ClientNetworkPacketHandler<S2CUpdateNpcDisplayDataPacket> {
    @Override
    public void handle(S2CUpdateNpcDisplayDataPacket packet) {
        EnvExecutor.runInEnv(Env.CLIENT, () -> () -> {
            if (Minecraft.getInstance().level == null)
                return;

            Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId());
            if (entity instanceof PlayerNpcEntity npcEntity)
                npcEntity.updateDisplayData();
        });
    }
}
