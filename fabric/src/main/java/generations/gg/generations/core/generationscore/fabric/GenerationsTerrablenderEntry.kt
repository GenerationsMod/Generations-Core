package generations.gg.generations.core.generationscore.fabric

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.biome.GenerationsTerrablender
import generations.gg.generations.core.generationscore.common.world.biome.surface.GenerationsSurfaceRules
import terrablender.api.SurfaceRuleManager
import terrablender.api.TerraBlenderApi

class GenerationsTerrablenderEntry : TerraBlenderApi {
    override fun onTerraBlenderInitialized() {
        if (!GenerationsCoreFabric.isInitialized) {
            GenerationsCore.init(GenerationsCoreFabric) // Or whichever platform impl
        }

        GenerationsTerrablender().registerBiomes()

        SurfaceRuleManager.addSurfaceRules(
            SurfaceRuleManager.RuleCategory.OVERWORLD,
            GenerationsCore.MOD_ID,
            GenerationsSurfaceRules.makeRules()
        )
    }
}