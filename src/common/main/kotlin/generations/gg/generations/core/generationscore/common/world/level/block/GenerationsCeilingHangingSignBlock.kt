package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsHangingSignBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.CeilingHangingSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType

class GenerationsCeilingHangingSignBlock(arg2: WoodType, arg: Properties) :
    CeilingHangingSignBlock(arg2, arg) {
    override fun newBlockEntity(arg: BlockPos, arg2: BlockState): BlockEntity {
        return GenerationsHangingSignBlockEntity(arg, arg2)
    }
}
