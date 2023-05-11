package generations.gg.generations.core.generationscore.forge;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.platform.forge.EventBuses;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.PokeModClient;
import generations.gg.generations.core.generationscore.forge.client.GenerationsCoreClientForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GenerationsCore.MOD_ID)
public class GenerationsCoreForge {
    public GenerationsCoreForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(GenerationsCore.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        GenerationsCore.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> GenerationsCoreClientForge::init);
    }
}