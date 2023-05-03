package generations.gg.generations.core.generationscore.fabric;

import generations.gg.generations.core.generationscore.Generations_Core;
import net.fabricmc.api.ModInitializer;

public class Generations_CoreFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Generations_Core.init();
    }
}