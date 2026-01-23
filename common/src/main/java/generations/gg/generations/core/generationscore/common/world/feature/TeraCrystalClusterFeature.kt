package generations.gg.generations.core.generationscore.common.world.feature

import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import net.minecraft.core.Direction
import net.minecraft.world.level.block.AmethystClusterBlock
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration

class TeraCrystalClusterFeature(codec: Codec<NoneFeatureConfiguration>) : Feature<NoneFeatureConfiguration>(codec) {
    override fun place(ctx: FeaturePlaceContext<NoneFeatureConfiguration>): Boolean {
        val world = ctx.level()
        val rand = ctx.random()
        val pos = ctx.origin()
        var placed = 0

        for (j in 0 until 64) {
            val direction = Direction.getRandom(rand)

            val state = GenerationsBlocks.TERA_CRYSTAL_CLUSTER.value()
                .defaultBlockState()
                .setValue(AmethystClusterBlock.FACING, direction)

            val blockPos = pos.offset(
                rand.nextInt(4) - rand.nextInt(4),
                rand.nextInt(4) - rand.nextInt(4),
                rand.nextInt(4) - rand.nextInt(4)
            )

            val ground = world.getBlockState(blockPos.relative(direction.opposite)).block

            if (world.isEmptyBlock(blockPos) && state.canSurvive(world, blockPos) && ground != Blocks.BEDROCK) {
                world.setBlock(blockPos, state, 2)
                placed++
            }
        }

        return placed > 4
    }
}
