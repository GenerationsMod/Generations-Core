package generations.gg.generations.core.generationscore.fabric.client;

import generations.gg.generations.core.generationscore.client.PokeModClient;
import net.fabricmc.api.ClientModInitializer;

public class GenerationsCoreClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PokeModClient.onInitialize();
    }
}
