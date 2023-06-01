package generations.gg.generations.core.generationscore.forge.client;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Consumer;

/**
 * This class is used to initialize the Forge client side of the mod.
 * @see ClientLifecycleEvent#CLIENT_SETUP
 * @see GenerationsCoreClient
 * @author J.T. McQuigg, WaterPicker
 */
public class GenerationsCoreClientForge {
    public static void init(IEventBus eventBus) {
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> {
            GenerationsCoreClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer);
            GenerationsCoreClient.registerEntityRenderers(event::registerEntityRenderer);
        });
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterLayerDefinitions>) event -> GenerationsCoreClient.registerLayerDefinitions(event::registerLayerDefinition));

        ClientLifecycleEvent.CLIENT_SETUP.register(minecraft -> {
            GenerationsCoreClient.onInitialize(minecraft);
            ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
            ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
        });
    }
}
