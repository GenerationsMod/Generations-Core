package generations.gg.generations.core.generationscore.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import generations.gg.generations.core.generationscore.GenerationsCore;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

/**
 * The Config Loader for all Generations modules/extensions
 * @author WaterPicker
 * @author J.T. McQuigg
 */
public class ConfigLoader {

    /** The Gson instance for the Config Loader. */
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Duration.class, new DurationJsonAdapter()).setPrettyPrinting().create();

    /**
     * Loads a config file for a Generations module/extension.
     *
     * @param clazz      The class of the config file.
     * @param subfolder  The subfolder for the Generations module/extension.  This should be what comes after generations_ in the modid
     * @param name       The name of the config file.
     * @return The config file.
     */
    public static <T> T loaderConfig(@NotNull Class<T> clazz, String subfolder, String name) {
        try {
            Path configPath = GenerationsCore.CONFIG_DIRECTORY.resolve(Path.of("generations", subfolder, name + ".json"));
            T value = clazz.getConstructor().newInstance();

            if (Files.notExists(configPath)) Files.createDirectories(configPath.getParent());
            else if (Files.exists(configPath)) value = GSON.fromJson(Files.newBufferedReader(configPath), clazz);

            Files.writeString(configPath, GSON.toJson(value));
            GenerationsCore.LOGGER.info("Generations-" + subfolder + " config loaded!");
            return value;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.", e);
        }
    }
}
