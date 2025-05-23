package generations.gg.generations.core.generationscore.forge.client;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient;
import generations.gg.generations.core.generationscore.common.client.screen.Overlays;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * This class is used to initialize the Forge client side of the mod.
 * @see FMLClientSetupEvent
 * @see GenerationsCoreClient
 * @author Joseph T. McQuigg, WaterPicker
 */
public class GenerationsCoreClientForge {

    /**
     * Initializes the client side of the Forge mod.
     * @param eventBus The event bus to register the client side of the mod to.
     */
    public static void init(IEventBus eventBus) {
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> {
            GenerationsCoreClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer);
            GenerationsCoreClient.registerEntityRenderers(event::registerEntityRenderer);
        });
        eventBus.addListener((EntityRenderersEvent.RegisterLayerDefinitions event) -> GenerationsCoreClient.registerLayerDefinitions(event::registerLayerDefinition));
        eventBus.addListener(GenerationsCoreClientForge::forgeClientSetup);
        eventBus.addListener(GenerationsCoreClientForge::registerGUILayers);
        MinecraftForge.EVENT_BUS.addListener(GenerationsCoreClientForge::renderHighlightedPath);
    }

    private static void renderHighlightedPath(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            GenerationsCoreClient.renderHighlightedPath(event.getPoseStack(), event.getRenderTick(), event.getCamera());
        } else if(event.getStage() == RenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES) {
            GenerationsCoreClient.renderRareCandy(Minecraft.getInstance().level);
        }
    }


    private static void forgeClientSetup(final FMLClientSetupEvent event) {
        GenerationsCoreClient.onInitialize(Minecraft.getInstance());
    }

    private static void registerGUILayers(final RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.HELMET.id(), GenerationsCore.id("overlays").toLanguageKey(), (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> Overlays.render(gui.getMinecraft(), guiGraphics, partialTick, screenWidth, screenHeight));
    }
}
