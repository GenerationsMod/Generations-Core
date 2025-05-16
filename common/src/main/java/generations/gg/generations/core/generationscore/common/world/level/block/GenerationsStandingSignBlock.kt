package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsSignBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.StandingSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType

class GenerationsStandingSignBlock(arg2: WoodType, arg: Properties) :
    StandingSignBlock(arg2, arg) {
    override fun newBlockEntity(arg: BlockPos, arg2: BlockState): BlockEntity? {
        return GenerationsSignBlockEntity(arg, arg2)
    }
}
