package generations.gg.generations.core.generationscore.forge;

import dev.architectury.platform.forge.EventBuses;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.forge.compat.VanillaCompatForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GenerationsCore.MOD_ID)
public class GenerationsCoreForge {
    public GenerationsCoreForge() {
		// Submit our event bus to let architectury register our content on the right time
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(GenerationsCore.MOD_ID, eventBus);
        GenerationsCore.init();
        eventBus.addListener(VanillaCompatForge::buildContents);
    }
}