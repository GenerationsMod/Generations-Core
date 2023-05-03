package generations.gg.generations.core.generationscore;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class GenerationsCoreExpectPlatform {
    @ExpectPlatform
    public static Path getConfigDirectory() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }
}
