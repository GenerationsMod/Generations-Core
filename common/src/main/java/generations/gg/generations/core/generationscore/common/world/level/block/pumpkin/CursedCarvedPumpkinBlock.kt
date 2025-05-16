package generations.gg.generations.core.generationscore.common.world.level.block.pumpkin

import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CarvedPumpkinBlock
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.pattern.BlockInWorld
import net.minecraft.world.level.block.state.pattern.BlockPattern
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate
import net.minecraft.world.level.block.state.properties.DirectionProperty
import java.util.function.Predicate

/**
 * @author JT122406
 * @see CarvedPumpkinBlock
 * Custom Cursed Carved Pumpkin Block
 */
open class CursedCarvedPumpkinBlock(arg: Properties) : CarvedPumpkinBlock(arg) {
    private var snowGolemBase: BlockPattern? = null
    private var snowGolemFull: BlockPattern? = null
    private var ironGolemBase: BlockPattern? = null
    private var ironGolemFull: BlockPattern? = null
    public override fun onPlace(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        oldState: BlockState,
        isMoving: Boolean
    ) {
        if (!oldState.`is`(state.block)) this.trySpawnGolem(level, pos)
    }

    override fun canSpawnGolem(level: LevelReader, pos: BlockPos): Boolean {
        return orCreateSnowGolemBase.find(level, pos) != null || orCreateIronGolemBase.find(level, pos) != null
    }

    private fun trySpawnGolem(level: Level, pos: BlockPos) {
        var blockPatternMatch =
            orCreateSnowGolemFull.find(level, pos)
        var i: Int
        val var6: Iterator<ServerPlayer>
        var j: Int
        if (blockPatternMatch != null) {
            i = 0
            while (i < orCreateSnowGolemFull.height) {
                val blockInWorld = blockPatternMatch.getBlock(0, i, 0)
                level.setBlock(blockInWorld.pos, Blocks.AIR.defaultBlockState(), 2)
                level.levelEvent(2001, blockInWorld.pos, getId(blockInWorld.state))
                ++i
            }

            val snowGolem = EntityType.SNOW_GOLEM.create(level)
            val blockPos = blockPatternMatch.getBlock(0, 2, 0).pos
            checkNotNull(snowGolem)
            snowGolem.moveTo(
                blockPos.x.toDouble() + 0.5,
                blockPos.y.toDouble() + 0.05,
                blockPos.z.toDouble() + 0.5,
                0.0f,
                0.0f
            )
            level.addFreshEntity(snowGolem)
            var6 = level.getEntitiesOfClass(ServerPlayer::class.java, snowGolem.boundingBox.inflate(5.0)).iterator()

            while (var6.hasNext()) CriteriaTriggers.SUMMONED_ENTITY.trigger(var6.next(), snowGolem)


            j = 0
            while (j < orCreateSnowGolemFull.height) {
                level.blockUpdated(blockPatternMatch.getBlock(0, j, 0).pos, Blocks.AIR)
                ++j
            }
        } else {
            blockPatternMatch = orCreateIronGolemFull.find(level, pos)
            if (blockPatternMatch != null) {
                i = 0
                while (i < orCreateIronGolemFull.width) {
                    for (k in 0 until orCreateIronGolemFull.height) {
                        val blockInWorld3 = blockPatternMatch.getBlock(i, k, 0)
                        level.setBlock(blockInWorld3.pos, Blocks.AIR.defaultBlockState(), 2)
                        level.levelEvent(2001, blockInWorld3.pos, getId(blockInWorld3.state))
                    }
                    ++i
                }

                val blockPos2 = blockPatternMatch.getBlock(1, 2, 0).pos
                val ironGolem = checkNotNull(EntityType.IRON_GOLEM.create(level))
                ironGolem.isPlayerCreated = true
                ironGolem.moveTo(
                    blockPos2.x.toDouble() + 0.5,
                    blockPos2.y.toDouble() + 0.05,
                    blockPos2.z.toDouble() + 0.5,
                    0.0f,
                    0.0f
                )
                level.addFreshEntity(ironGolem)
                var6 = level.getEntitiesOfClass(ServerPlayer::class.java, ironGolem.boundingBox.inflate(5.0)).iterator()

                while (var6.hasNext()) CriteriaTriggers.SUMMONED_ENTITY.trigger(var6.next(), ironGolem)

                j = 0
                while (j < orCreateIronGolemFull.width) {
                    for (l in 0 until orCreateIronGolemFull.height) level.blockUpdated(
                        blockPatternMatch.getBlock(
                            j,
                            l,
                            0
                        ).pos, Blocks.AIR
                    )
                    ++j
                }
            }
        }
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return defaultBlockState().setValue(FACING, context.horizontalDirection.opposite)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    private val orCreateSnowGolemBase: BlockPattern
        get() {
            if (this.snowGolemBase == null) snowGolemBase =
                BlockPatternBuilder.start().aisle(" ", "#", "#").where(
                    '#',
                    BlockInWorld.hasState(
                        BlockStatePredicate.forBlock(
                            Blocks.SNOW_BLOCK
                        )
                    )
                ).build()

            return snowGolemBase!!
        }

    private val orCreateSnowGolemFull: BlockPattern
        get() {
            if (this.snowGolemFull == null) snowGolemFull =
                BlockPatternBuilder.start().aisle("^", "#", "#").where(
                    '^',
                    BlockInWorld.hasState(CURSED_PUMPKINS_PREDICATE)
                ).where(
                    '#',
                    BlockInWorld.hasState(
                        BlockStatePredicate.forBlock(
                            Blocks.SNOW_BLOCK
                        )
                    )
                ).build()

            return snowGolemFull!!
        }


    private val orCreateIronGolemBase: BlockPattern
        get() {
            if (this.ironGolemBase == null) this.ironGolemBase =
                BlockPatternBuilder.start().aisle("~ ~", "###", "~#~").where(
                    '#', BlockInWorld.hasState(
                        BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)
                    )
                ).where(
                    '~'
                ) { blockInWorld: BlockInWorld -> blockInWorld.state.isAir }.build()

            return ironGolemBase!!
        }

    private val orCreateIronGolemFull: BlockPattern
        get() {
            if (this.ironGolemFull == null) this.ironGolemFull =
                BlockPatternBuilder.start().aisle("~^~", "###", "~#~").where(
                    '^', BlockInWorld.hasState(
                        CURSED_PUMPKINS_PREDICATE
                    )
                ).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where(
                    '~'
                ) { blockInWorld: BlockInWorld -> blockInWorld.state.isAir }.build()

            return ironGolemFull!!
        }

    init {
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH))
    }

    companion object {
        val FACING: DirectionProperty = HorizontalDirectionalBlock.FACING
        private val CURSED_PUMPKINS_PREDICATE =
            Predicate { arg: BlockState? ->
                arg != null && (arg.`is`(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get()) || arg.`is`(
                    GenerationsBlocks.CURSED_JACK_O_LANTERN.get()
                ))
            }
    }
}