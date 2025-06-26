package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class BushBlock(properties: Properties) :
    GenericRotatableModelBlock(
        properties,
        model = GenerationsBlockEntityModels.BUSH,
        width = 0,
        height = 1,
        length = 0
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<BushBlock> {
        return CODEC
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return if (getHeightValue(state) == 0) LOWER else UPPER
    }

    companion object {
        private val UPPER: VoxelShape = Shapes.box(0.125, 0.0, 0.125, 0.875, 0.5, 0.875)
        private val LOWER: VoxelShape = Shapes.box(0.125, 0.0, 0.125, 0.875, 1.0, 0.875)

        val CODEC: MapCodec<BushBlock> = simpleCodec { properties: Properties ->
            BushBlock(properties)
        }
    }
}
