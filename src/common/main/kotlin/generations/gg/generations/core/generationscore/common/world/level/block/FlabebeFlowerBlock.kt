package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class FlabebeFlowerBlock(properties: Properties) : BushBlock(properties) {
    override fun codec(): MapCodec<FlabebeFlowerBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    companion object {
        private val SHAPE: VoxelShape = Shapes.box(0.1, 0.0, 0.1, 0.9, 0.8, 0.9)
        val CODEC = simpleCodec(::FlabebeFlowerBlock)
    }
}
