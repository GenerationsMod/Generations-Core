package generations.gg.generations.core.generationscore.forge.client;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * This class is used to initialize the Forge client side of the mod.
 * @see ClientLifecycleEvent#CLIENT_SETUP
 * @see GenerationsCoreClient
 * @author J.T. McQuigg, WaterPicker
 */
public class GenerationsCoreClientForge {
    public static void init(IEventBus eventBus) {
        eventBus.addListener(GenerationsCoreClientForge::registerBlockEntityRenderers);
        ClientLifecycleEvent.CLIENT_SETUP.register(minecraft -> {
            GenerationsCoreClient.onInitialize(minecraft);
            ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
            ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
        });
    }

    /**
     * Registers the block entity renderers.
     * @param event The EntityRenderersEvent.RegisterRenderers event.
     * @see EntityRenderersEvent.RegisterRenderers
     */
    private static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        BlockEntityRenderers.register(GenerationsBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new);
    }
}
