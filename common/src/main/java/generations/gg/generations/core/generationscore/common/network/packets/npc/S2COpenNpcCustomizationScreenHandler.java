package generations.gg.generations.core.generationscore.common.network.packets.npc;

import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.common.client.screen.npc.CustomizeNpcScreen;
import generations.gg.generations.core.generationscore.common.client.screen.npc.CustomizeNpcScreen;
import generations.gg.generations.core.generationscore.common.client.screen.npc.CustomizeNpcScreen;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import net.minecraft.client.Minecraft;

public class S2COpenNpcCustomizationScreenHandler implements ClientNetworkPacketHandler<S2COpenNpcCustomizationScreenPacket> {
    @Override
    public void handle(S2COpenNpcCustomizationScreenPacket packet) {
        EnvExecutor.runInEnv(Env.CLIENT, () -> () -> {
            var npcEntity = (PlayerNpcEntity) Minecraft.getInstance().level.getEntity(packet.entityId());
            Minecraft.getInstance().setScreen(new CustomizeNpcScreen(npcEntity));
        });
    }
}
