package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.thebombzen.jxlatte.JXLDecoder;
import com.thebombzen.jxlatte.JXLImage;
import com.thebombzen.jxlatte.JXLOptions;
import gg.generationsmod.rarecandy.FileLocator;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.tukaani.xz.XZInputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MinecraftPkFileLocator implements FileLocator {
    private final Map<String, byte[]> fileCache = new HashMap<>();
    private final ResourceLocation path;

    private String modelName;

    public MinecraftPkFileLocator(ResourceLocation path, InputStream is) {
        this.path = path;

        var glbCount = 0;


        try {
            var tarFile = getTarFile(Objects.requireNonNull(is, "Input Stream is null"));

            for (var entry : tarFile.getEntries()) {
                if(entry.getName().endsWith("/")) continue;

                if(entry.getName().endsWith("glb")) {
                    if(glbCount > 1) throw new RuntimeException("Too many glb files");
                    modelName = entry.getName();
                    glbCount++;
                }

                fileCache.put(entry.getName(), tarFile.getInputStream(entry).readAllBytes());
            }

            if(modelName == null) throw new RuntimeException("No glb.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TarFile getTarFile(InputStream inputStream) {
        try {
            var xzInputStream = new XZInputStream(inputStream);
            return new TarFile(xzInputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file.", e);
        }
    }

    @Override
    public List<String> getFiles() {
        return List.copyOf(fileCache.keySet());
    }

    @Override
    public byte[] getFile(String name) {
        return fileCache.get(name);
    }

    public String getModelName() {
        return modelName;
    }

    @Override
    public Path getPath() {
        return Path.of(path.getNamespace() + "-" + path.getPath());
    }

    public ResourceLocation getLocation() {
        return path;
    }

    public BufferedImage read(byte[] imageBytes) throws IOException {
        var options = new JXLOptions();
        options.hdr = JXLOptions.HDR_OFF;
        options.threads = 2;
        var reader = new JXLDecoder(new ByteArrayInputStream(imageBytes), options);
        var image = reader.decode();
        return image.asBufferedImage();
    }

    @Override
    public BufferedImage readImage(String name) {
        try {
            var is = new ByteArrayInputStream(getFile(name));
            var image = name.endsWith(".jxl") ? read(is.readAllBytes()) : ImageIO.read(is);
            int height = image.getHeight();
            int width = image.getWidth();

            // Mirror image if not square. TODO: maybe do this in the shader to save gpu memory and upload time in general
            if (height / width == 2) {
                var mirror = new BufferedImage(width * 2, height, BufferedImage.TYPE_INT_ARGB);
                for (int y = 0; y < height; y++) {
                    for (int lx = 0, rx = width * 2 - 1; lx < width; lx++, rx--) {
                        int p = image.getRGB(lx, y);
                        mirror.setRGB(lx, y, p);
                        mirror.setRGB(rx, y, p);
                    }
                }

                image = mirror;
            }

            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}