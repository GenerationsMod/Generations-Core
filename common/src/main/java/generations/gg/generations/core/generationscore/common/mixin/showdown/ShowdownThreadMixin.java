package generations.gg.generations.core.generationscore.common.mixin.showdown;

import com.cobblemon.mod.common.battles.ShowdownThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Mixin(ShowdownThread.class)
public class ShowdownThreadMixin {

    @Inject(method = "run", at = @At("HEAD"), remap = false)
    private void preShowdownLaunch(CallbackInfo ci) {
        Path path = Path.of("./showdown/sim");

        try {
            Files.createDirectories(path);
            copyResourceToDisk("/assets/generations_core/showdown/battle-actions.js", path.resolve("battle-actions.js"));
        } catch (IOException e) {
            System.out.println("Error during file transfer: " + e.getMessage());
        }
    }

    @Unique
    private void copyResourceToDisk(String resourcePath, Path destination) {
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                System.out.println("Resource not found");
                return;
            }

            byte[] newContent = inputStream.readAllBytes();

            if (Files.exists(destination)) {
                byte[] existingContent = Files.readAllBytes(destination);
                if (java.util.Arrays.equals(existingContent, newContent)) {
                    System.out.println("File already exists");
                    return;
                }
            }

            Files.createDirectories(destination.getParent());
            Files.write(destination, newContent);
            System.out.println("Successfully copied resource to: " + destination);
        } catch (IOException e) {
            System.out.println("Error copying resource");
        }
    }

}