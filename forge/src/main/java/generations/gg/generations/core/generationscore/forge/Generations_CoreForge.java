package generations.gg.generations.core.generationscore.forge;

import dev.architectury.platform.forge.EventBuses;
import generations.gg.generations.core.generationscore.Generations_Core;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Generations_Core.MOD_ID)
public class Generations_CoreForge {
    public Generations_CoreForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Generations_Core.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
            Generations_Core.init();
    }
}