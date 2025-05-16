package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsHangingSignBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.WallHangingSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType

class GenerationsWallHangingSignBlock(arg: WoodType, arg2: Properties) :
    WallHangingSignBlock(arg, arg2) {
    override fun newBlockEntity(arg: BlockPos, arg2: BlockState): BlockEntity? {
        return GenerationsHangingSignBlockEntity(arg, arg2)
    }
}
