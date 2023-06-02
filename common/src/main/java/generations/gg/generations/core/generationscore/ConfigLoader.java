package generations.gg.generations.core.generationscore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T loaderConfig(Class<T> clazz, String modid, String name) {


        try {
            var configPath = GenerationsCoreExpectPlatform.getConfigDirectory().resolve(Path.of(modid, name + ".json"));
            T value = clazz.getConstructor().newInstance();

            if(Files.notExists(configPath.getParent())) Files.createDirectory(configPath.getParent());

            if (Files.exists(configPath)) {
                var t = GSON.fromJson(Files.newBufferedReader(configPath), clazz);
                if(t != null) value = t;
            }

            var json = GSON.toJson(value);

            Files.writeString(configPath, json);

            return value;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.", e);
        }
    }
}
