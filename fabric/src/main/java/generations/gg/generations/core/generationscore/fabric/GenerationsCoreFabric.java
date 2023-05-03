package generations.gg.generations.core.generationscore.fabric;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.fabricmc.api.ModInitializer;

public class GenerationsCoreFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GenerationsCore.init();
    }
}