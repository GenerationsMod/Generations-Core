package generations.gg.generations.core.generationscore.fabric;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.compat.VanillaCompat;
import net.fabricmc.api.ModInitializer;

/**
 * Fabric Main class and entry point for GenerationsCore.
 * @see ModInitializer
 * @see GenerationsCore
 * @author J.T. McQuigg, WaterPicker
 */
public class GenerationsCoreFabric extends GenerationsCore implements ModInitializer {
	@Override
	public void onInitialize() {
		GenerationsCore.init();
		VanillaCompat.setup();
	}
}