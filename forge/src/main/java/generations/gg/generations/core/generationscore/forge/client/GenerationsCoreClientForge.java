package generations.gg.generations.core.generationscore.forge.client;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import generations.gg.generations.core.generationscore.client.render.block.entity.*;
import generations.gg.generations.core.generationscore.client.render.entity.GenerationsBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.GenerationsChestBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.SittableEntityRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.TieredFishingHookRenderer;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
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
        eventBus.addListener(GenerationsCoreClientForge::registerEntityRenderers);
        ClientLifecycleEvent.CLIENT_SETUP.register(minecraft -> {
            GenerationsCoreClient.onInitialize(minecraft);
            ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
            ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
        });
    }

    /**
     * Registers the entity renderers.
     * @param event The EntityRenderersEvent.RegisterRenderers event.
     * @see EntityRenderersEvent.RegisterRenderers
     */
    private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GenerationsEntities.SEAT.get(), SittableEntityRenderer::new);
        event.registerEntityRenderer(GenerationsEntities.TIERED_FISHING_BOBBER.get(), TieredFishingHookRenderer::new);
        event.registerEntityRenderer(GenerationsEntities.BOAT_ENTITY.get(), GenerationsBoatRenderer::new);
        event.registerEntityRenderer(GenerationsEntities.CHEST_BOAT_ENTITY.get(), GenerationsChestBoatRenderer::new);
        event.registerEntityRenderer(GenerationsEntities.MAGMA_CRYSTAL.get(), ThrownItemRenderer::new);
    }

    /**
     * Registers the block entity renderers.
     * @param event The EntityRenderersEvent.RegisterRenderers event.
     * @see EntityRenderersEvent.RegisterRenderers
     */
    private static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GenerationsBlockEntities.POKE_DOLL.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.HEALER.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.CLOCK.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.BOX.get(), GeneralUseBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(GenerationsBlockEntities.TIMESPACE_ALTAR.get(), TimeSpaceAltarEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.ABUNDANT_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.CELESTIAL_ALTAR.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.LUNAR_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.REGIGIGAS_SHRINE.get(), RegigigasShrineBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.TAO_TRIO_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.TAPU_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.BREEDER.get(), BreederBlocEntityRenderer::new);

        event.registerBlockEntityRenderer(GenerationsBlockEntities.COOKING_POT.get(), CookingPotRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.WEATHER_TRIO.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.GENERIC_CHEST.get(), GenericChestRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.GENERIC_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.GENERIC_DYED_VARIANT.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.GENERIC_MODEL_PROVIDING.get(), GeneralUseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(GenerationsBlockEntities.VENDING_MACHINE.get(), GeneralUseBlockEntityRenderer::new);
    }
}
