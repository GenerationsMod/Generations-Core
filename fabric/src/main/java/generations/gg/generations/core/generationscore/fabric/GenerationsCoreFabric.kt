package generations.gg.generations.core.generationscore.fabric

import com.cobblemon.mod.common.NetworkManager
import com.cobblemon.mod.fabric.CobblemonFabric
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.GenerationsImplementation
import generations.gg.generations.core.generationscore.compat.VanillaCompat
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
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
    }

    override val networkManager: NetworkManager = GenerationsFabricNetworkManager
}