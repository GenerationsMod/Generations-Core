package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import generations.gg.generations.core.generationscore.common.world.entity.block.SittableEntity.Companion.mount
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

interface SittableBlock {
    fun sit(state: BlockState, level: Level, pos: BlockPos, player: Player, hit: BlockHitResult?): Boolean? {
        if (!level.isClientSide && !player.isShiftKeyDown) {
            return mount(level, pos, offset, player, getYaw(state))
        }

        return null
    }

    val offset: Double

    fun getYaw(state: BlockState): Float
}
