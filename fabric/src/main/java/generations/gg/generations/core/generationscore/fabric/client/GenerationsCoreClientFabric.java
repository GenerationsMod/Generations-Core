package generations.gg.generations.core.generationscore.fabric.client;

import generations.gg.generations.core.generationscore.client.GenerationsCoreClient;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsMushroomBlock;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
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
public class GenerationsCoreClientFabric implements ClientModInitializer {

    /**
     * Initializes the client.
     * @see ClientModInitializer#onInitializeClient()
     */
    @Override
    public void onInitializeClient() {
        GenerationsCoreClient.onInitialize(Minecraft.getInstance());
        registerRenderTypes();
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
