package generations.gg.generations.core.generationscore.fabric.client;

import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import generations.gg.generations.core.generationscore.client.render.block.entity.*;
import generations.gg.generations.core.generationscore.client.render.entity.GenerationsBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.GenerationsChestBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.SittableEntityRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.TieredFishingHookRenderer;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsMushroomBlock;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.TrapDoorBlock;

/**
 * The client initializer for the fabric client portion of the mod.
 * @see ClientModInitializer
 * @see GenerationsCoreClient
 * @author J.T. McQuigg, WaterPicker
 */
@Environment(EnvType.CLIENT)
public class GenerationsCoreClientFabric implements ClientModInitializer {

    /**
     * Initializes the client.
     * @see ClientModInitializer#onInitializeClient()
     */
    @Override
    public void onInitializeClient() {
        GenerationsCoreClient.onInitialize(Minecraft.getInstance());
        registerRenderTypes();
        registerEntityRenderers();
        registerBlockEntityRenderers();
    }

    /**
     * Registers the render types for the blocks.
     * @see BlockRenderLayerMap
     */
    private static void registerRenderTypes(){
        BlockRenderLayerMap renderLayerMap = BlockRenderLayerMap.INSTANCE;
        GenerationsWood.WOOD_BLOCKS.forEach(object -> {
            Block block = object.get();
            if (block instanceof DoorBlock || block instanceof TrapDoorBlock)
                renderLayerMap.putBlock(block, RenderType.cutout());
        });
        GenerationsBlocks.BLOCKS.forEach(object -> {
            Block block = object.get();
            if (block instanceof DoorBlock || block instanceof GenerationsMushroomBlock)
                renderLayerMap.putBlock(block, RenderType.cutout());
            else if (block instanceof GlassBlock) renderLayerMap.putBlock(block, RenderType.cutoutMipped());
        });
        renderLayerMap.putBlock(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get(), RenderType.cutout());
    }

    /**
     * Registers the entity renderers.
     * @see EntityRendererRegistry
     * @see GenerationsEntities
     */
    private static void registerEntityRenderers() {
        EntityRendererRegistry.register(GenerationsEntities.SEAT.get(), SittableEntityRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.TIERED_FISHING_BOBBER.get(), TieredFishingHookRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.BOAT_ENTITY.get(), GenerationsBoatRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.CHEST_BOAT_ENTITY.get(), GenerationsChestBoatRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.MAGMA_CRYSTAL.get(), ThrownItemRenderer::new);
    }

    /**
     * Registers the block entity renderers.
     * @see BlockEntityRenderers
     * @see GenerationsBlockEntities
     */
    private static void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(GenerationsBlockEntities.POKE_DOLL.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.HEALER.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.CLOCK.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.BOX.get(), GeneralUseBlockEntityRenderer::new);

        BlockEntityRenderers.register(GenerationsBlockEntities.TIMESPACE_ALTAR.get(), TimeSpaceAltarEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.ABUNDANT_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.CELESTIAL_ALTAR.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.LUNAR_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.REGIGIGAS_SHRINE.get(), RegigigasShrineBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.TAO_TRIO_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.TAPU_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.BREEDER.get(), BreederBlocEntityRenderer::new);

        BlockEntityRenderers.register(GenerationsBlockEntities.COOKING_POT.get(), CookingPotRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.WEATHER_TRIO.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.GENERIC_CHEST.get(), GenericChestRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.GENERIC_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.GENERIC_DYED_VARIANT.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.GENERIC_MODEL_PROVIDING.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRenderers.register(GenerationsBlockEntities.VENDING_MACHINE.get(), GeneralUseBlockEntityRenderer::new);
    }
}
