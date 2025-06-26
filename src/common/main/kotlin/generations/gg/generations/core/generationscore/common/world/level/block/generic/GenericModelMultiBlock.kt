package generations.gg.generations.core.generationscore.common.world.level.block.generic

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.DirectionProperty

@Suppress("deprecation")
class GenericModelMultiBlock(properties: Properties, width: Int, height: Double, length: Int) :
    Block(properties) {
    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    public override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    public override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return if (mirror == Mirror.NONE) state else state.rotate(
            mirror.getRotation(
                state.getValue(
                    FACING
                )
            )
        )
    }


    companion object {
        // Work in progress, unsure what exactly I need to do here yet
        val FACING: DirectionProperty = HorizontalDirectionalBlock.FACING
    }
}
