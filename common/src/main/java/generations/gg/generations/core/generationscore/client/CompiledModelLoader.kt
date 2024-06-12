package generations.gg.generations.core.generationscore.client

import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener

class CompiledModelLoader : ResourceManagerReloadListener {


    override fun onResourceManagerReload(resourceManager: ResourceManager) {
        GenerationsTextureLoader.initialize(resourceManager)
        ModelRegistry.LOADER.invalidateAll()
        ModelRegistry.LOADER.cleanUp()

    }
}
