package generations.gg.generations.core.generationscore.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class GenerationsCoreExpectPlatformImpl {
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
