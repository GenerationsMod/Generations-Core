package generations.gg.generations.core.generationscore.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The Config Loader for all Generations modules/extensions
 * @author WaterPicker
 * @author J.T. McQuigg
 */
public class ConfigLoader {

    /** The Gson instance for the Config Loader. */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Loads a config file for a Generations module/extension.
     *
     * @param clazz      The class of the config file.
     * @param subfolder  The subfolder for the Generations module/extension.  This should be what comes after generations_ in the modid
     * @param name       The name of the config file.
     * @param configDirectory Path to the config file based on platform
     * @return The config file.
     */
    public static <T> T loaderConfig(@NotNull Class<T> clazz, String subfolder, String name, Path configDirectory) {
        try {
            Path configPath = configDirectory.resolve(Path.of("generations", subfolder, name + ".json"));
            T value = clazz.getConstructor().newInstance();

            if (Files.notExists(configPath)) Files.createDirectories(configPath.getParent());
            else if (Files.exists(configPath)) value = GSON.fromJson(Files.newBufferedReader(configPath), clazz);

            Files.writeString(configPath, GSON.toJson(value));
            return value;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.", e);
        }
    }
}
