package generations.gg.generations.core.generationscore.common.world.biome

import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.resources.ResourceLocation
import terrablender.api.Regions

class GenerationsTerrablender {
    fun registerBiomes() {
        Regions.register(GenerationsOverworldRegion(ResourceLocation.fromNamespaceAndPath(GenerationsCore.MOD_ID, "overworld"), 5))
    }
}