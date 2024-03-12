package generations.gg.generations.core.generationscore.network.packets.statue;

import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.client.screen.statue.StatueEditorScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

import static dev.architectury.utils.Env.CLIENT;

public class S2CUpdateStatueInfoHandler implements ClientNetworkPacketHandler<S2CUpdateStatueInfoPacket> {
    public static final S2CUpdateStatueInfoHandler INSTANCE = new S2CUpdateStatueInfoHandler();

    @Override
    public void handle(S2CUpdateStatueInfoPacket packet) {
        EnvExecutor.runInEnv(CLIENT, () -> () -> {
            if (Minecraft.getInstance().level == null)
                return;

            Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId());
            if (entity instanceof StatueEntity statueEntity) {
                statueEntity.updateStatueData();

                if(Minecraft.getInstance().screen instanceof StatueEditorScreen screen) {
                    screen.getStatue().updateStatueData();
                }
            }
        });
    }
}
