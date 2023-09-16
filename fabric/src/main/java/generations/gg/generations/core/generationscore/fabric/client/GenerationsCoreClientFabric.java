package generations.gg.generations.core.generationscore.fabric.client;

import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsMushroomBlock;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
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
//        System.loadLibrary("renderdoc");+
        WorldRenderEvents.AFTER_TRANSLUCENT.register(context -> GenerationsCoreClient.renderHighlightedPath(context.matrixStack(), Minecraft.getInstance().levelRenderer.ticks, context.camera()));
        GenerationsCoreClient.onInitialize(Minecraft.getInstance());
        registerRenderTypes();
        GenerationsCoreClient.registerEntityRenderers(EntityRendererRegistry::register);
        GenerationsCoreClient.registerBlockEntityRenderers(BlockEntityRenderers::register);
        GenerationsCoreClient.registerLayerDefinitions((a, b) -> EntityModelLayerRegistry.registerModelLayer(a, b::get));
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
}
