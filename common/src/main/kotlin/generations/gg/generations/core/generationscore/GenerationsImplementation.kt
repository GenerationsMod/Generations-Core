package generations.gg.generations.core.generationscore

import com.cobblemon.mod.common.NetworkManager
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener

interface GenerationsImplementation {
    public abstract val networkManager: NetworkManager

    fun registerResourceReloader(identifier: ResourceLocation, reloader: PreparableReloadListener, type: PackType, dependencies: Collection<ResourceLocation>)
}
