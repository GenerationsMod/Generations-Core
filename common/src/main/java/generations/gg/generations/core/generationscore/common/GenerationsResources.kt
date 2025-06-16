package generations.gg.generations.core.generationscore.common

import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

object GenerationsResources {
    fun init() {
        val showdownPath = Path.of("./showdown/sim")
        val showdownFiles = listOf(
            "battle-actions.js" to "sim",
            "abilities.js" to "data",
            "moves.js" to "data"
        )
        Files.createDirectories(showdownPath)

        for ((file, subDir) in showdownFiles) {
            val targetPath = showdownPath.resolveSibling(subDir).resolve(file)
            GenerationsCore.LOGGER.info("Attempting to copy: $file")
            copyResourceIfChanged("assets/generations_core/showdown_data/$file", targetPath)
        }
    }

    private fun copyResourceIfChanged(resourcePath: String, destination: Path) {
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream(resourcePath)
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
