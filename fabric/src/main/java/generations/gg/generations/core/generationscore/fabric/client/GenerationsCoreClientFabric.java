package generations.gg.generations.core.generationscore.fabric.client;

import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;

public class GenerationsCoreClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GenerationsCoreClient.onInitialize(Minecraft.getInstance());
    }
}
