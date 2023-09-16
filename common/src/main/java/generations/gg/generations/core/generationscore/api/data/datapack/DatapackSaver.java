package generations.gg.generations.core.generationscore.api.data.datapack;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

/**
 * Used to save generated datapack entries created in game. Used mainly for the dialogue and npc editors.
 */
public class DatapackSaver {
    public static final String DATAPACK_NAME = "generations_core_generated";
    private static final String DATAPACK_DESC = "Datapack for data generated from in game ui's. You can also store your own configs here.";

    public static void savePokemodData(MinecraftServer server, Function<Path, Path> pathFinder, String data) {
        try {
            var targetFile = pathFinder.apply(getDatapackFolder(server).resolve("data"));
            Files.createDirectories(targetFile.getParent());
            System.out.println(targetFile);
            Files.writeString(targetFile, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getDatapackFolder(MinecraftServer server) {
        try {
            var datapackFolder = server.getWorldPath(LevelResource.DATAPACK_DIR).resolve(DATAPACK_NAME);
            Files.createDirectories(datapackFolder);

            if (!Files.exists(datapackFolder.resolve("pack.mcmeta"))) {
                Files.writeString(datapackFolder.resolve("pack.mcmeta"), """
                        {
                          "pack": {
                            "pack_format": $VER,
                            "description": "$DESC"
                          }
                        }
                        """.replace("$VER", "0").replace("$DESC", DATAPACK_DESC));
            }

            return datapackFolder;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}