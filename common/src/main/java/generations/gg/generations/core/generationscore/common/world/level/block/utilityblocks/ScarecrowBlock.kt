package generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks

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

class ScarecrowBlock(props: Properties) :
    GenericRotatableModelBlock(
        props,
        model = GenerationsBlockEntityModels.SCARECROW,
        height = 1
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<ScarecrowBlock> {
        return CODEC
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return if (state.`is`(this) && getHeightValue(state) == 1) TOP else BOTTOM
    }

    companion object {
        private val BOTTOM: VoxelShape = Shapes.block()
        private val TOP: VoxelShape = Shapes.box(0.0, 0.0, 0.0, 1.0, 0.5, 1.0)
        val CODEC: MapCodec<ScarecrowBlock> = simpleCodec { props: Properties ->
            ScarecrowBlock(
                props
            )
        }
    }
}