package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.orFalse
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.util.Codecs.codec
import generations.gg.generations.core.generationscore.common.util.Codecs.tagCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

class GenerationsMushroomBlock(arg: Properties, private val featureKey: ResourceKey<ConfiguredFeature<*, *>>) : BushBlock(arg), BonemealableBlock {
    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape = SHAPE

    override fun codec(): MapCodec<GenerationsMushroomBlock> = CODEC

    override fun mayPlaceOn(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean = state.isSolidRender(level, pos)

    fun growMushroom(level: ServerLevel, pos: BlockPos, state: BlockState, random: RandomSource) {
        level.removeBlock(pos, false)
        if(level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE)[featureKey].orFalse { it.place(level, level.chunkSource.generator, random, pos) }) level.setBlock(pos, state, 3)
    }

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean = true

    override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean =
        random.nextFloat().toDouble() < 0.4

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) = this.growMushroom(level, pos, state, random)

    companion object {
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0)
        val CODEC = Codecs.mapCodec { group(propertiesCodec(), Registries.CONFIGURED_FEATURE.codec().fieldOf("featureKey").forGetter(GenerationsMushroomBlock::featureKey)).apply(this, ::GenerationsMushroomBlock) }
    }
}
