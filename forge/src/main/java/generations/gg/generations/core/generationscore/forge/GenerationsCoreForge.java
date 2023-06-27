package generations.gg.generations.core.generationscore.forge;

import dev.architectury.platform.forge.EventBuses;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.compat.VanillaCompat;
import generations.gg.generations.core.generationscore.forge.client.GenerationsCoreClientForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Forge Main class for GenerationsCore.
 * @see Mod
 * @see GenerationsCore
 * @author J.T. McQuigg, WaterPicker
 */
@Mod(GenerationsCore.MOD_ID)
public class GenerationsCoreForge {
    public GenerationsCoreForge() {
		// Submit our event bus to let architectury register our content on the right time
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(GenerationsCore.MOD_ID, eventBus);
        GenerationsCore.init(ModList.get().isLoaded("cobblemon"));
        eventBus.addListener(this::onInitialize);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> GenerationsCoreClientForge.init(eventBus));
    }

    /**
     * Should initialize everything where a specific event does not cover it.
     */
    private void onInitialize(final FMLCommonSetupEvent event) {
        event.enqueueWork(VanillaCompat::setup);
    }
}