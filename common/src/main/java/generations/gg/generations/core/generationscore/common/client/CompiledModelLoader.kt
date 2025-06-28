package generations.gg.generations.core.generationscore.common.client

import generations.gg.generations.core.generationscore.common.client.model.SpriteRegistry
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener

class CompiledModelLoader : ResourceManagerReloadListener {
    override fun onResourceManagerReload(resourceManager: ResourceManager) {
        GenerationsTextureLoader.initialize(resourceManager)
        SpriteRegistry.onResourceManagerReload(resourceManager)
        ModelRegistry.clear()
    }
}
