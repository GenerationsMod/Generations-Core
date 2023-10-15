package generations.gg.generations.core.generationscore.network.packets.statue;

import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.client.screen.statue.StatueEditorScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.client.Minecraft;

import static dev.architectury.utils.Env.CLIENT;

public class S2COpenStatueEditorScreenHandler implements ClientNetworkPacketHandler<S2COpenStatueEditorScreenPacket> {
    public static final S2COpenStatueEditorScreenHandler INSTANCE = new S2COpenStatueEditorScreenHandler();


    @Override
    public void handle(S2COpenStatueEditorScreenPacket packet) {
        EnvExecutor.runInEnv(CLIENT, () -> () -> Minecraft.getInstance().tell(() -> {
            var statueEntity = (StatueEntity) Minecraft.getInstance().level.getEntity(packet.entityId());
            Minecraft.getInstance().setScreen(new StatueEditorScreen(statueEntity));
        }));
    }
}
