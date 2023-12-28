package generations.gg.generations.core.generationscore;

import net.minecraft.nbt.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SchemConverter {
    public static void main(String[] args) throws IOException {
        var path = Path.of("C:\\Users\\water\\Downloads\\schems");
        var out = Path.of("C:\\Users\\water\\Downloads\\schems\\out");
        if(Files.notExists(out)) Files.createDirectory(out);

        Files.newDirectoryStream(path, "*.schem").forEach(p -> {
            try {
                var nbt = NbtIo.readCompressed(Files.newInputStream(p));

                var tag = new CompoundTag();

                var list = nbt.getCompound("Palette");

                for (var key : list.getAllKeys()) {
                    tag.put(key.replace("pokemod:", "generations_core:"), IntTag.valueOf(list.getInt(key)));
                }

                nbt.put("Palette", tag);



                NbtIo.writeCompressed(nbt, Files.newOutputStream(out.resolve(p.getFileName().toString())));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
