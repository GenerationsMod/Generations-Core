package generations.gg.generations.core.generationscore.common.world.level.block

import com.google.common.annotations.VisibleForTesting
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.item.FallingBlockEntity
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.entity.projectile.ThrownTrident
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.*
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*
import java.util.function.BiPredicate
import java.util.function.Predicate
import kotlin.math.max

class PointedChargeDripstoneBlock(arg: Properties) : Block(arg),
    Fallable, SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(
            stateDefinition.any().setValue(TIP_DIRECTION, Direction.UP).setValue(
                THICKNESS, DripstoneThickness.TIP
            ).setValue(WATERLOGGED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED)
    }

    public override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return isValidPointedDripstonePlacement(level, pos, state.getValue(TIP_DIRECTION))
    }

    public override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        level: LevelAccessor,
        currentPos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level))

        if (direction != Direction.UP && direction != Direction.DOWN) return state

        val direction2 = state.getValue(TIP_DIRECTION)
        if (direction2 == Direction.DOWN && level.blockTicks.hasScheduledTick(currentPos, this)) return state

        if (direction == direction2.opposite && !this.canSurvive(state, level, currentPos)) {
            if (direction2 == Direction.DOWN) level.scheduleTick(currentPos, this, 2)
            else level.scheduleTick(currentPos, this, 1)

            return state
        }
        return state.setValue(
            THICKNESS, calculateDripstoneThickness(
                level, currentPos, direction2, state.getValue(
                    THICKNESS
                ) == DripstoneThickness.TIP_MERGE
            )
        )
    }

    public override fun onProjectileHit(level: Level, state: BlockState, hit: BlockHitResult, projectile: Projectile) {
        val blockPos = hit.blockPos
        if (!level.isClientSide && projectile.mayInteract(
                level,
                blockPos
            ) && projectile is ThrownTrident && projectile.getDeltaMovement().length() > 0.6
        ) level.destroyBlock(blockPos, true)
    }

    override fun fallOn(level: Level, state: BlockState, pos: BlockPos, entity: Entity, fallDistance: Float) {
        if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == DripstoneThickness.TIP) entity.causeFallDamage(
            fallDistance + 2.0f,
            2.0f,
            entity.damageSources().stalagmite()
        )
        else super.fallOn(level, state, pos, entity, fallDistance)
    }

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        if (!canDrip(state)) return

        val f = random.nextFloat()
        if (f > 0.12f) return
        getFluidAboveStalactite(level, pos, state).filter { arg: FluidInfo ->
            f < 0.02f || canFillCauldron(
                arg.fluid
            )
        }.ifPresent { arg4: FluidInfo ->
            spawnDripParticle(
                level,
                pos,
                state,
                arg4.fluid
            )
        }
    }

    public override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (isStalagmite(state) && !this.canSurvive(state, level, pos)) level.destroyBlock(pos, true)
        else spawnFallingStalactite(state, level, pos)
    }

    public override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        maybeTransferFluid(state, level, pos, random.nextFloat())
        if (random.nextFloat() < 0.011377778f && isStalactiteStartPos(
                state,
                level,
                pos
            )
        ) growStalactiteOrStalagmiteIfPossible(state, level, pos, random)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        var blockPos: BlockPos
        val levelAccessor = context.level
        val direction2 = calculateTipDirection(
            levelAccessor,
            context.clickedPos.also { blockPos = it },
            context.nearestLookingVerticalDirection.opposite
        )
        if (direction2 == null) return null

        val dripstoneThickness = calculateDripstoneThickness(
            levelAccessor,
            blockPos,
            direction2,
            !context.isSecondaryUseActive
        )
            ?: return null

        return defaultBlockState().setValue(TIP_DIRECTION, direction2).setValue(THICKNESS, dripstoneThickness).setValue(
            WATERLOGGED, levelAccessor.getFluidState(blockPos).type === Fluids.WATER
        )
    }

    public override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    public override fun getOcclusionShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape {
        return Shapes.empty()
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        val dripstoneThickness = state.getValue(THICKNESS)
        val voxelShape =
            if (dripstoneThickness == DripstoneThickness.TIP_MERGE) TIP_MERGE_SHAPE else (if (dripstoneThickness == DripstoneThickness.TIP) (if (state.getValue(
                    TIP_DIRECTION
                ) == Direction.DOWN
            ) TIP_SHAPE_DOWN else TIP_SHAPE_UP) else (if (dripstoneThickness == DripstoneThickness.FRUSTUM) FRUSTUM_SHAPE else (if (dripstoneThickness == DripstoneThickness.MIDDLE) MIDDLE_SHAPE else BASE_SHAPE)))
        val vec3 = state.getOffset(level, pos)
        return voxelShape.move(vec3.x, 0.0, vec3.z)
    }

    public override fun isCollisionShapeFullBlock(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean {
        return false
    }

    public override fun getMaxHorizontalOffset(): Float {
        return 0.125f
    }

    override fun onBrokenAfterFall(level: Level, pos: BlockPos, fallingBlock: FallingBlockEntity) {
        if (!fallingBlock.isSilent) level.levelEvent(1045, pos, 0)
    }

    override fun getFallDamageSource(entity: Entity): DamageSource {
        return entity.damageSources().fallingStalactite(entity)
    }

    override fun isPathfindable(state: BlockState, pathComputationType: PathComputationType): Boolean {
        return false
    }

    @JvmRecord
    internal data class FluidInfo(val pos: BlockPos, val fluid: Fluid, val sourceState: BlockState)
    companion object {
        val TIP_DIRECTION: DirectionProperty = BlockStateProperties.VERTICAL_DIRECTION
        val THICKNESS: EnumProperty<DripstoneThickness> = BlockStateProperties.DRIPSTONE_THICKNESS
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        private const val MAX_SEARCH_LENGTH_WHEN_CHECKING_DRIP_TYPE = 11
        private const val DELAY_BEFORE_FALLING = 2
        private const val DRIP_PROBABILITY_PER_ANIMATE_TICK = 0.02f
        private const val DRIP_PROBABILITY_PER_ANIMATE_TICK_IF_UNDER_LIQUID_SOURCE = 0.12f
        private const val MAX_SEARCH_LENGTH_BETWEEN_STALACTITE_TIP_AND_CAULDRON = 11
        private const val WATER_TRANSFER_PROBABILITY_PER_RANDOM_TICK = 0.17578125f
        private const val LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK = 0.05859375f
        private const val MIN_TRIDENT_VELOCITY_TO_BREAK_DRIPSTONE = 0.6
        private const val STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE = 1.0f
        private const val STALACTITE_MAX_DAMAGE = 40
        private const val MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION = 6
        private const val STALAGMITE_FALL_DISTANCE_OFFSET = 2.0f
        private const val STALAGMITE_FALL_DAMAGE_MODIFIER = 2
        private const val AVERAGE_DAYS_PER_GROWTH = 5.0f
        private const val GROWTH_PROBABILITY_PER_RANDOM_TICK = 0.011377778f
        private const val MAX_GROWTH_LENGTH = 7
        private const val MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING = 10
        private const val STALACTITE_DRIP_START_PIXEL = 0.6875f
        private val TIP_MERGE_SHAPE: VoxelShape = box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0)
        private val TIP_SHAPE_UP: VoxelShape = box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0)
        private val TIP_SHAPE_DOWN: VoxelShape = box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0)
        private val FRUSTUM_SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0)
        private val MIDDLE_SHAPE: VoxelShape = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
        private const val MAX_HORIZONTAL_OFFSET = 0.125f
        private val REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK: VoxelShape = box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0)

        @VisibleForTesting
        fun maybeTransferFluid(state: BlockState, level: ServerLevel, pos: BlockPos, randChance: Float) {
            if (!(randChance > 0.17578125f) || !(randChance > 0.05859375f)) {
                if (isStalactiteStartPos(state, level, pos)) {
                    val optional = getFluidAboveStalactite(level, pos, state)
                    if (optional.isPresent) {
                        val fluid = optional.get().fluid
                        val f: Float
                        if (fluid === Fluids.WATER) f = 0.17578125f
                        else {
                            if (fluid !== Fluids.LAVA) return

                            f = 0.05859375f
                        }

                        if (!(randChance >= f)) {
                            val blockPos = findTip(state, level, pos, 11, false)
                            if (blockPos != null) if (optional.get().sourceState.`is`(Blocks.MUD) && fluid === Fluids.WATER) {
                                val blockState = Blocks.CLAY.defaultBlockState()
                                level.setBlockAndUpdate(optional.get().pos, blockState)
                                pushEntitiesUp((optional.get()).sourceState, blockState, level, (optional.get()).pos)
                                level.gameEvent(
                                    GameEvent.BLOCK_CHANGE,
                                    (optional.get()).pos,
                                    GameEvent.Context.of(blockState)
                                )
                                level.levelEvent(1504, blockPos, 0)
                            } else {
                                val blockPos2 = findFillableCauldronBelowStalactiteTip(level, blockPos, fluid)
                                if (blockPos2 != null) {
                                    level.levelEvent(1504, blockPos, 0)
                                    val i = blockPos.y - blockPos2.y
                                    val j = 50 + i
                                    val blockState2 = level.getBlockState(blockPos2)
                                    level.scheduleTick(blockPos2, blockState2.block, j)
                                }
                            }
                        }
                    }
                }
            }
        }

        private fun spawnFallingStalactite(state: BlockState, level: ServerLevel, pos: BlockPos) {
            val mutableBlockPos = pos.mutable()
            var blockState = state
            while (isStalactite(blockState)) {
                val fallingBlockEntity = FallingBlockEntity.fall(level, mutableBlockPos, blockState)
                if (isTip(blockState, true)) {
                    fallingBlockEntity.setHurtsEntities(
                        max((1 + pos.y - mutableBlockPos.y).toDouble(), 6.0) as Float,
                        40
                    )
                    break
                }
                mutableBlockPos.move(Direction.DOWN)
                blockState = level.getBlockState(mutableBlockPos)
            }
        }

        @VisibleForTesting
        fun growStalactiteOrStalagmiteIfPossible(
            state: BlockState,
            level: ServerLevel,
            pos: BlockPos,
            random: RandomSource
        ) {
            if (!canGrow(level.getBlockState(pos.above(1)), level.getBlockState(pos.above(2)))) return

            val blockPos = findTip(state, level, pos, 7, false) ?: return

            val blockState3 = level.getBlockState(blockPos)
            if (!canDrip(blockState3) || !canTipGrow(blockState3, level, blockPos)) return

            if (random.nextBoolean()) grow(level, blockPos, Direction.DOWN)
            else growStalagmiteBelow(level, blockPos)
        }

        private fun growStalagmiteBelow(level: ServerLevel, pos: BlockPos) {
            val mutableBlockPos = pos.mutable()
            for (i in 0..9) {
                mutableBlockPos.move(Direction.DOWN)
                val blockState = level.getBlockState(mutableBlockPos)
                if (!blockState.fluidState.isEmpty) {
                    return
                }
                if (isUnmergedTipWithDirection(blockState, Direction.UP) && canTipGrow(
                        blockState,
                        level,
                        mutableBlockPos
                    )
                ) {
                    grow(level, mutableBlockPos, Direction.UP)
                    return
                }
                if (isValidPointedDripstonePlacement(level, mutableBlockPos, Direction.UP) && !level.isWaterAt(
                        mutableBlockPos.below()
                    )
                ) {
                    grow(level, mutableBlockPos.below(), Direction.UP)
                    return
                }
                if (canDripThrough(level, mutableBlockPos, blockState)) continue
                return
            }
        }

        private fun grow(server: ServerLevel, pos: BlockPos, direction: Direction) {
            val blockPos = pos.relative(direction)
            val blockState = server.getBlockState(blockPos)
            if (isUnmergedTipWithDirection(blockState, direction.opposite)) createMergedTips(
                blockState,
                server,
                blockPos
            )
            else if (blockState.isAir || blockState.`is`(Blocks.WATER)) createDripstone(
                server,
                blockPos,
                direction,
                DripstoneThickness.TIP
            )
        }

        private fun createDripstone(
            level: LevelAccessor,
            pos: BlockPos,
            direction: Direction,
            thickness: DripstoneThickness
        ) {
            val blockState =
                GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.value().defaultBlockState().setValue(TIP_DIRECTION, direction)
                    .setValue(
                        THICKNESS, thickness
                    ).setValue(WATERLOGGED, level.getFluidState(pos).type === Fluids.WATER)
            level.setBlock(pos, blockState, 3)
        }

        private fun createMergedTips(state: BlockState, level: LevelAccessor, pos: BlockPos) {
            val blockPos2: BlockPos
            val blockPos: BlockPos
            if (state.getValue<Direction>(TIP_DIRECTION) == Direction.UP) {
                blockPos = pos
                blockPos2 = pos.above()
            } else {
                blockPos2 = pos
                blockPos = pos.below()
            }
            createDripstone(level, blockPos2, Direction.DOWN, DripstoneThickness.TIP_MERGE)
            createDripstone(level, blockPos, Direction.UP, DripstoneThickness.TIP_MERGE)
        }

        private fun spawnDripParticle(level: Level, pos: BlockPos, state: BlockState, fluid: Fluid) {
            val particleOptions = if (getDripFluid(
                    level,
                    fluid
                ).`is`(FluidTags.LAVA)
            ) ParticleTypes.DRIPPING_DRIPSTONE_LAVA else ParticleTypes.DRIPPING_DRIPSTONE_WATER
            val vec3 = state.getOffset(level, pos)
            level.addParticle(
                particleOptions,
                pos.x.toDouble() + 0.5 + vec3.x,
                ((pos.y + 1).toFloat() - 0.6875f).toDouble() - 0.0625,
                pos.z.toDouble() + 0.5 + vec3.z,
                0.0,
                0.0,
                0.0
            )
        }

        private fun findTip(
            state: BlockState,
            level: LevelAccessor,
            pos: BlockPos,
            maxIterations: Int,
            isTipMerge: Boolean
        ): BlockPos? {
            if (isTip(state, isTipMerge)) return pos

            val direction = state.getValue(TIP_DIRECTION)
            val biPredicate =
                BiPredicate { arg2: BlockPos, arg3: BlockState ->
                    arg3.`is`(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE) && arg3.getValue(
                        TIP_DIRECTION
                    ) == direction
                }
            return findBlockVertical(
                level, pos, direction.axisDirection, biPredicate,
                { arg: BlockState -> isTip(arg, isTipMerge) }, maxIterations
            ).orElse(null)
        }

        private fun calculateTipDirection(level: LevelReader, pos: BlockPos, dir: Direction): Direction? {
            val direction =
                if (isValidPointedDripstonePlacement(level, pos, dir)) dir
                else if (isValidPointedDripstonePlacement(
                        level,
                        pos,
                        dir.opposite
                    )
                ) dir.opposite
                else return null
            return direction
        }

        private fun calculateDripstoneThickness(
            level: LevelReader,
            pos: BlockPos,
            dir: Direction,
            isTipMerge: Boolean
        ): DripstoneThickness {
            val direction = dir.opposite
            val blockState = level.getBlockState(pos.relative(dir))
            if (isPointedDripstoneWithDirection(blockState, direction)) {
                if (isTipMerge || blockState.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE) return DripstoneThickness.TIP_MERGE

                return DripstoneThickness.TIP
            }
            if (!isPointedDripstoneWithDirection(blockState, dir)) return DripstoneThickness.TIP

            val dripstoneThickness = blockState.getValue(THICKNESS)
            if (dripstoneThickness == DripstoneThickness.TIP || dripstoneThickness == DripstoneThickness.TIP_MERGE) return DripstoneThickness.FRUSTUM

            val blockState2 = level.getBlockState(pos.relative(direction))
            if (!isPointedDripstoneWithDirection(blockState2, dir)) return DripstoneThickness.BASE

            return DripstoneThickness.MIDDLE
        }

        fun canDrip(state: BlockState): Boolean {
            return isStalactite(state) && state.getValue(THICKNESS) == DripstoneThickness.TIP && !state.getValue(
                WATERLOGGED
            )
        }

        private fun canTipGrow(state: BlockState, level: ServerLevel, pos: BlockPos): Boolean {
            val direction = state.getValue(TIP_DIRECTION)
            val blockPos = pos.relative(direction)
            val blockState = level.getBlockState(blockPos)
            if (!blockState.fluidState.isEmpty) return false

            if (blockState.isAir) return true

            return isUnmergedTipWithDirection(blockState, direction.opposite)
        }

        private fun findRootBlock(
            level: Level,
            pos: BlockPos,
            state: BlockState,
            maxIterations: Int
        ): Optional<BlockPos> {
            val direction = state.getValue(TIP_DIRECTION)
            val biPredicate =
                BiPredicate { arg2: BlockPos, arg3: BlockState ->
                    arg3.`is`(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE) && arg3.getValue(
                        TIP_DIRECTION
                    ) == direction
                }
            return findBlockVertical(
                level, pos, direction.opposite.axisDirection, biPredicate,
                { arg: BlockState -> !arg.`is`(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE) }, maxIterations
            )
        }

        private fun isValidPointedDripstonePlacement(level: LevelReader, pos: BlockPos, dir: Direction): Boolean {
            val blockPos = pos.relative(dir.opposite)
            val blockState = level.getBlockState(blockPos)
            return blockState.isFaceSturdy(level, blockPos, dir) || isPointedDripstoneWithDirection(blockState, dir)
        }

        private fun isTip(state: BlockState, isTipMerge: Boolean): Boolean {
            if (!state.`is`(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE)) return false

            val dripstoneThickness = state.getValue(THICKNESS)
            return dripstoneThickness == DripstoneThickness.TIP || isTipMerge && dripstoneThickness == DripstoneThickness.TIP_MERGE
        }

        private fun isUnmergedTipWithDirection(state: BlockState, dir: Direction): Boolean {
            return isTip(state, false) && state.getValue(TIP_DIRECTION) == dir
        }

        private fun isStalactite(state: BlockState): Boolean {
            return isPointedDripstoneWithDirection(state, Direction.DOWN)
        }

        private fun isStalagmite(state: BlockState): Boolean {
            return isPointedDripstoneWithDirection(state, Direction.UP)
        }

        private fun isStalactiteStartPos(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
            return isStalactite(state) && !level.getBlockState(pos.above())
                .`is`(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE)
        }

        private fun isPointedDripstoneWithDirection(state: BlockState, dir: Direction): Boolean {
            return state.`is`(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE) && state.getValue(TIP_DIRECTION) == dir
        }

        private fun findFillableCauldronBelowStalactiteTip(level: Level, pos: BlockPos, fluid: Fluid): BlockPos? {
            val predicate =
                Predicate { arg2: BlockState ->
                    arg2.block is AbstractCauldronBlock && (arg2.block as AbstractCauldronBlock).canReceiveStalactiteDrip(
                        fluid
                    )
                }
            val biPredicate =
                BiPredicate { arg2: BlockPos, arg3: BlockState -> canDripThrough(level, arg2, arg3) }
            return findBlockVertical(level, pos, Direction.DOWN.axisDirection, biPredicate, predicate, 11).orElse(null)
        }

        @JvmStatic
		fun findStalactiteTipAboveCauldron(level: Level, pos: BlockPos): BlockPos? {
            val biPredicate =
                BiPredicate { arg2: BlockPos, arg3: BlockState -> canDripThrough(level, arg2, arg3) }
            return findBlockVertical(
                level, pos, Direction.UP.axisDirection, biPredicate,
                { state: BlockState -> canDrip(state) }, 11
            ).orElse(null)
        }

        @JvmStatic
		fun getCauldronFillFluidType(level: ServerLevel, pos: BlockPos): Fluid {
            return getFluidAboveStalactite(level, pos, level.getBlockState(pos)).map { arg: FluidInfo -> arg.fluid }
                .filter { fluid: Fluid ->
                    canFillCauldron(
                        fluid
                    )
                }.orElse(Fluids.EMPTY)
        }

        private fun getFluidAboveStalactite(level: Level, pos: BlockPos, state: BlockState): Optional<FluidInfo> {
            if (!isStalactite(state)) {
                return Optional.empty()
            }
            return findRootBlock(level, pos, state, 11).map { arg2: BlockPos? ->
                val blockPos = arg2!!.above()
                val blockState = level.getBlockState(blockPos)
                val fluid = if (blockState.`is`(Blocks.MUD) && !level.dimensionType()
                        .ultraWarm()
                ) Fluids.WATER else level.getFluidState(blockPos).type
                FluidInfo(
                    blockPos,
                    fluid,
                    blockState
                )
            }
        }

        private fun canFillCauldron(fluid: Fluid): Boolean {
            return fluid === Fluids.LAVA || fluid === Fluids.WATER
        }

        private fun canGrow(dripstoneState: BlockState, state: BlockState): Boolean {
            return dripstoneState.`is`(GenerationsBlocks.CHARGE_DRIPSTONE_BLOCK) && state.`is`(Blocks.WATER) && state.fluidState.isSource
        }

        private fun getDripFluid(level: Level, fluid: Fluid): Fluid {
            if (fluid.isSame(Fluids.EMPTY)) return if (level.dimensionType().ultraWarm()) Fluids.LAVA else Fluids.WATER
            return fluid
        }

        private fun findBlockVertical(
            level: LevelAccessor,
            pos: BlockPos,
            axis: Direction.AxisDirection,
            positionalStatePredicate: BiPredicate<BlockPos, BlockState>,
            statePredicate: Predicate<BlockState>,
            maxIterations: Int
        ): Optional<BlockPos> {
            val direction = Direction.get(axis, Direction.Axis.Y)
            val mutableBlockPos = pos.mutable()
            for (i in 1 until maxIterations) {
                mutableBlockPos.move(direction)
                val blockState = level.getBlockState(mutableBlockPos)
                if (statePredicate.test(blockState)) {
                    return Optional.of(mutableBlockPos.immutable())
                }
                if (!level.isOutsideBuildHeight(mutableBlockPos.y) && positionalStatePredicate.test(
                        mutableBlockPos,
                        blockState
                    )
                ) continue
                return Optional.empty()
            }
            return Optional.empty()
        }

        private fun canDripThrough(level: BlockGetter, pos: BlockPos, state: BlockState): Boolean {
            if (state.isAir) return true

            if (state.isSolidRender(level, pos) || !state.fluidState.isEmpty) return false

            return !Shapes.joinIsNotEmpty(
                REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK,
                state.getCollisionShape(level, pos),
                BooleanOp.AND
            )
        }
    }
}
