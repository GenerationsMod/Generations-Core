package generations.gg.generations.core.generationscore.common.world.level.block

import com.cobblemon.mod.common.api.reactive.EventObservable
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class ElevatorBlock : Block(Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8f)) {
    public override fun entityInside(state: BlockState, level: Level, pos: BlockPos, entity: Entity) {
        if (entity is ServerPlayer && entity.isShiftKeyDown()) this.takeElevator(
            level,
            entity.blockPosition().below(),
            entity,
            Direction.DOWN
        )
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    public override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        level: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        return if (!state.canSurvive(level, pos)) Blocks.AIR.defaultBlockState() else super.updateShape(
            state,
            direction,
            neighborState,
            level,
            pos,
            neighborPos
        )
    }

    fun takeElevator(world: BlockGetter, pos: BlockPos, player: ServerPlayer, direction: Direction) {
        ElevatorEvents.USE_ELEVATOR.post(
            ElevatorEvents.UseElevator(
                player,
                world,
                this,
                direction,
                pos,
                findNearestElevator(world, pos, direction)
            ), then = {
                it.destination?.above()?.run {
                    player.serverLevel().playSound(
                        null,
                        this,
                        GenerationsSounds.ELEVATOR.value(),
                        SoundSource.BLOCKS,
                        0.5f,
                        1.0f
                    )
                    player.teleportTo(player.position().x(), this.y - 0.5, player.position().z())
                }
            })
    }

    private fun findNearestElevator(world: BlockGetter, pos: BlockPos, direction: Direction): BlockPos? {
        val range = GenerationsCore.CONFIG.blocks.elevatorRange

        if (range < 3) return null

        for (i in 2 until range) {
            val current = pos.relative(direction, i)

            if (world.getBlockState(current).block is ElevatorBlock && world.getBlockState(current.above()).isAir && world.getBlockState(
                    current.above(2)
                ).isAir
            ) return current
        }

        return null
    }

    interface ElevatorEvents {
        data class UseElevator(
            val player: ServerPlayer,
            val blockGetter: BlockGetter,
            val block: ElevatorBlock,
            val direction: Direction,
            val origin: BlockPos,
            var destination: BlockPos?
        )

        companion object {
            val USE_ELEVATOR = EventObservable<UseElevator>()
        }
    }

    companion object {
        protected val SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)
    }
}
