package generations.gg.generations.core.generationscore.common.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import generations.gg.generations.core.generationscore.common.GenerationsCore
import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration

/**
 * The Config Loader for all Generations modules/extensions
 * @author WaterPicker
 * @author Joseph T. McQuigg
 */
object ConfigLoader {
    /** The Gson instance for the Config Loader.  */
    private val GSON: Gson = GsonBuilder().registerTypeAdapter(Duration::class.java, DurationJsonAdapter())
        .registerTypeAdapter(Config.Caught::class.java, Config.Caught.Adapter()).setPrettyPrinting().create()

    /** Config Directory  */
    private var CONFIG_DIRECTORY: Path? = null

    /**
     * Sets the config directory for Generations modules/extensions.
     *
     * @param configDirectory The config directory.
     */
    @JvmStatic
    fun setConfigDirectory(configDirectory: Path) {
        CONFIG_DIRECTORY = configDirectory
    }

    /**
     * Loads or Creates a config file for a Generations module/extension.
     *
     * @param clazz      The class of the config file.
     * @param subfolder  The subfolder for the Generations module/extension.  This should be what comes after generations_ in the modid
     * @param name       The name of the config file.
     * @return The config file.
     */
    fun <T> loadConfig(clazz: Class<T>, subfolder: String, name: String): T {
        try {
            val configPath = CONFIG_DIRECTORY!!.resolve(
                Path.of(
                    "generations", subfolder,
                    "$name.json"
                )
            )
            var value = clazz.getConstructor().newInstance()

            if (Files.notExists(configPath)) Files.createDirectories(configPath.parent)
            else if (Files.exists(configPath)) value = GSON.fromJson(Files.newBufferedReader(configPath), clazz)

            Files.writeString(configPath, GSON.toJson(value))
            GenerationsCore.LOGGER.info("Generations-$subfolder config loaded!")
            return value
        } catch (e: Exception) {
            throw RuntimeException("Failed to load config.", e)
        }
    }
}
