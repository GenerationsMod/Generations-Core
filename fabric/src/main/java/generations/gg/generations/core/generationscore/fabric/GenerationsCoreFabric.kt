package generations.gg.generations.core.generationscore.fabric

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.NetworkManager
import com.cobblemon.mod.fabric.CobblemonFabric
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.GenerationsImplementation
import generations.gg.generations.core.generationscore.compat.VanillaCompat
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import net.minecraft.util.profiling.ProfilerFiller
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

/**
 * Fabric Main class and entry point for GenerationsCore.
 * @see ModInitializer
 *
 * @see GenerationsCore
 *
 * @author J.T. McQuigg, WaterPicker
 */
class GenerationsCoreFabric : ModInitializer, GenerationsImplementation {
    override fun onInitialize() {
        GenerationsCore.init(this)
        VanillaCompat.setup()

        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register { player, isLogin ->
            if (isLogin) {
                GenerationsCore.dataProvider.sync(player)
            }
        }
    }

    override val networkManager: NetworkManager = GenerationsFabricNetworkManager
    override fun registerResourceReloader(
        identifier: ResourceLocation,
        reloader: PreparableReloadListener,
        type: PackType,
        dependencies: Collection<ResourceLocation>,
    ) {
        ResourceManagerHelper.get(type).registerReloadListener(
            GenerationsReloadListener(
                identifier,
                reloader,
                dependencies
            )
        )
    }

    private class GenerationsReloadListener(private val identifier: ResourceLocation, private val reloader: PreparableReloadListener, private val dependencies: Collection<ResourceLocation>) : IdentifiableResourceReloadListener {

        override fun reload(
            preparationBarrier: PreparableReloadListener.PreparationBarrier,
            resourceManager: ResourceManager,
            profilerFiller: ProfilerFiller,
            profilerFiller2: ProfilerFiller,
            executor: Executor,
            executor2: Executor,
        ): CompletableFuture<Void> = reloader.reload(preparationBarrier, resourceManager, profilerFiller, profilerFiller2, executor, executor2)

        override fun getFabricId(): ResourceLocation = this.identifier

        override fun getName(): String = this.reloader.name

        override fun getFabricDependencies(): MutableCollection<ResourceLocation> = this.dependencies.toMutableList()
    }
}