package generations.gg.generations.core.generationscore.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class GenerationsCoreExpectPlatformImpl {
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
