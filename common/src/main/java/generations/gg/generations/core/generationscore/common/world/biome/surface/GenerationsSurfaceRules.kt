package generations.gg.generations.core.generationscore.common.world.biome.surface

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.SurfaceRules
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.biome.GenerationsBiomes
import net.minecraft.world.level.levelgen.placement.CaveSurface

object GenerationsSurfaceRules {

    fun makeRules(): SurfaceRules.RuleSource {
        return SurfaceRules.sequence(
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(GenerationsBiomes.TERASTAL_CAVES),
                SurfaceRules.state(GenerationsBlocks.TERA_INFUSED_DEEPSLATE_SET.baseBlock.defaultBlockState())
            )
        )
    }

    private fun makeStateRule(block: Block): RuleSource {
        return SurfaceRules.state(block.defaultBlockState())
    }
}
