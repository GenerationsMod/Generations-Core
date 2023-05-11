package generations.gg.generations.core.generationscore.forge.client;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import generations.gg.generations.core.generationscore.client.PokeModClient;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWoodTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class GenerationsCoreClientForge {
    public static void init() {
        ClientLifecycleEvent.CLIENT_SETUP.register(minecraft -> {
            PokeModClient.onInitialize(minecraft);
            ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
            ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
        });
    }
}
