package generations.gg.generations.core.generationscore.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;


/**
 * This class is used to provide a platform-specific implementation of the GenerationsCoreExpectPlatform interface.
 * @author J.T. McQuigg
 */
public class GenerationsCoreExpectPlatformImpl {

    /**
     * Returns the path to the directory where the mod's configuration files are stored based on Forge Platform.
     * @return the path to the directory where the mod's configuration files are stored
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
