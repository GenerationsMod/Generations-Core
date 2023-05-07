package generations.gg.generations.core.generationscore.forge.client;

import generations.gg.generations.core.generationscore.world.level.block.GenerationsWoodTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class GenerationsCoreClientForge {

    private static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(GenerationsWoodTypes.ULTRA_JUNGLE);
            WoodType.register(GenerationsWoodTypes.ULTRA_DARK);
            WoodType.register(GenerationsWoodTypes.GHOST);

            Sheets.addWoodType(GenerationsWoodTypes.ULTRA_JUNGLE);
            Sheets.addWoodType(GenerationsWoodTypes.ULTRA_DARK);
            Sheets.addWoodType(GenerationsWoodTypes.GHOST);
        });

        ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
        ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
    }

}
