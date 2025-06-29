package generations.gg.generations.core.generationscore.fabric.datagen

import com.mojang.serialization.Lifecycle
import generations.gg.generations.core.generationscore.common.world.item.RecordSongs
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsPaintings
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderLookup
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import java.util.concurrent.CompletableFuture

class GenerationsDynamicRegistryProvider(
    output: FabricDataOutput,
    provider: CompletableFuture<HolderLookup.Provider>,
): FabricDynamicRegistryProvider(output, provider) {
    override fun getName(): String {
        return "Generations Dynamic Registries."
    }

    override fun configure(provider: HolderLookup.Provider, entries: Entries) {
        entries.addAll(provider.lookupOrThrow(Registries.JUKEBOX_SONG))
        entries.addAll(provider.lookupOrThrow(Registries.PAINTING_VARIANT))
    }

}
