package generations.gg.generations.core.generationscore.common

import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

object GenerationsResources {
    fun init() {
        val showdownPath = Path.of("./showdown/sim")
        val showdownFiles = listOf(
            "battle-actions.js",
            "abilities.js",
            "moves.js"
        )
        Files.createDirectories(showdownPath)

        for (file in showdownFiles) {
            copyResourceIfChanged("/assets/generations_core/showdown_data/$file", showdownPath.resolve(file))
        }
    }

    private fun copyResourceIfChanged(resourcePath: String, destination: Path) {
        val inputStream: InputStream = javaClass.getResourceAsStream(resourcePath)
            ?: run {
                GenerationsCore.LOGGER.warn("Resource not found at: $resourcePath")
                return
            }

        val newContent = inputStream.readAllBytes()

        if (Files.exists(destination)) {
            val existingContent = Files.readAllBytes(destination)
            if (existingContent contentEquals newContent) {
                GenerationsCore.LOGGER.debug("File unchanged, skipping copy: $destination")
                return
            }
        }

        Files.createDirectories(destination.parent)
        Files.write(destination, newContent)
        GenerationsCore.LOGGER.info("Copied resource to: $destination")
    }
}
