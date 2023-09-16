package generations.gg.generations.core.generationscore.forge.client;

import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Consumer;

/**
 * This class is used to initialize the Forge client side of the mod.
 * @see FMLClientSetupEvent
 * @see GenerationsCoreClient
 * @author J.T. McQuigg, WaterPicker
 */
public class GenerationsCoreClientForge {

    /**
     * Initializes the client side of the Forge mod.
     * @param eventBus The event bus to register the client side of the mod to.
     */
    public static void init(IEventBus eventBus) {
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> {
            GenerationsCoreClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer);
            GenerationsCoreClient.registerEntityRenderers(event::registerEntityRenderer);
        });
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterLayerDefinitions>) event -> GenerationsCoreClient.registerLayerDefinitions(event::registerLayerDefinition));
        eventBus.addListener(GenerationsCoreClientForge::forgeClientSetup);
        MinecraftForge.EVENT_BUS.addListener(GenerationsCoreClientForge::renderHighlightedPath);
    }

    private static void renderHighlightedPath(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            GenerationsCoreClient.renderHighlightedPath(event.getPoseStack(), event.getRenderTick(), event.getCamera());
        }
    }


    private static void forgeClientSetup(final FMLClientSetupEvent event) {

        GenerationsCoreClient.onInitialize(Minecraft.getInstance());
        ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
        ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
    }
}
