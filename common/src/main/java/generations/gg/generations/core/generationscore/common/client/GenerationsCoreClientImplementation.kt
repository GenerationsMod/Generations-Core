package generations.gg.generations.core.generationscore.common.client

import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.PreparableReloadListener

interface GenerationsCoreClientImplementation {
    fun registerResourceReloader(
        identifier: ResourceLocation,
        reloader: PreparableReloadListener,
        dependencies: Collection<ResourceLocation>,
    )
}
