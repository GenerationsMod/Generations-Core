package generations.gg.generations.core.generationscore.fabric;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.compat.VanillaCompat;
import generations.gg.generations.core.generationscore.fabric.client.GenerationsCoreClientFabric;
import net.fabricmc.api.ModInitializer;

public class GenerationsCoreFabric extends GenerationsCore implements ModInitializer {
	@Override
	public void onInitialize() {
		GenerationsCore.init();
		VanillaCompat.setup();

	}
}