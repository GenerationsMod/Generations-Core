package generations.gg.generations.core.generationscore.common.world.level.block.generic

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.world.level.block.asValue
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericChestBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.stats.Stats
import net.minecraft.util.RandomSource
import net.minecraft.world.Container
import net.minecraft.world.Containers
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.monster.piglin.PiglinAi
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.DoubleBlockCombiner.NeighborCombineResult
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.ChestBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Supplier

@Suppress("deprecation")
class GenericChestBlock(properties: Properties, private val width: Int, private val height: Int, val materialType: String) : AbstractChestBlock<GenericChestBlockEntity>(properties, Supplier<BlockEntityType<out GenericChestBlockEntity>> { GenerationsBlockEntities.GENERIC_CHEST.asValue() }),
    SimpleWaterloggedBlock {
    private val defaultTranslation = "container.$materialType"

    init {
        this.registerDefaultState(
            stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(
                WATERLOGGED, false
            )
        )
    }

    override fun combine(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        override: Boolean,
    ): DoubleBlockCombiner.NeighborCombineResult<out ChestBlockEntity> {
        return object : NeighborCombineResult<ChestBlockEntity> {
            override fun <T : Any?> apply(combiner: DoubleBlockCombiner.Combiner<in ChestBlockEntity, T>): T {
                return combiner.acceptNone()
            }

        }
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    public override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return defaultBlockState().setValue(FACING, context.horizontalDirection.opposite).setValue(
            WATERLOGGED, context.level.getFluidState(context.clickedPos).`is`(
                Fluids.WATER
            )
        )
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult,
    ): InteractionResult {
         if (level.isClientSide) {
            return InteractionResult.SUCCESS
        }
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is GenericChestBlockEntity) {
            blockEntity.openScreen(player)
            player.awardStat(Stats.OPEN_CHEST)
            PiglinAi.angerNearbyPiglins(player, true)
        }
        return InteractionResult.CONSUME
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): GenericChestBlockEntity? {
        return GenericChestBlockEntity(pos, state, width, height, defaultTranslation)
    }

    public override fun onRemove(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        newState: BlockState,
        isMoving: Boolean,
    ) {
        if (state.`is`(newState.block)) {
            return
        }
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is Container) {
            Containers.dropContents(level, pos, blockEntity as Container)
            level.updateNeighbourForOutputSignal(pos, this)
        }
        super.onRemove(state, level, pos, newState, isMoving)
    }

    public override fun hasAnalogOutputSignal(state: BlockState): Boolean {
        return true
    }

    public override fun getAnalogOutputSignal(state: BlockState, level: Level, pos: BlockPos): Int {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos))
    }

    public override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    public override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING, WATERLOGGED)
    }

    override fun codec(): MapCodec<GenericChestBlock> = CODEC

    override fun isPathfindable(state: BlockState, pathComputationType: PathComputationType): Boolean {
        return false
    }

    public override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is GenericChestBlockEntity) {
            blockEntity.recheckOpen()
        }
    }

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>,
    ): BlockEntityTicker<T>? {
        return if (level.isClientSide) createTickerHelper(
            blockEntityType, GenerationsBlockEntities.GENERIC_CHEST.asValue()
        ) { level: Level, pos: BlockPos, state: BlockState, blockEntity: GenericChestBlockEntity ->
            GenericChestBlockEntity.lidAnimateTick(
                level,
                pos,
                state,
                blockEntity
            )
        } else null
    }

    companion object {
        val CODEC: MapCodec<GenericChestBlock> = RecordCodecBuilder.mapCodec { it.group(
            propertiesCodec(),
            Codec.INT.fieldOf("width").forGetter { it.width },
            Codec.INT.fieldOf("height").forGetter { it.height },
            Codec.STRING.fieldOf("material").forGetter { it.materialType }
        ).apply(it, ::GenericChestBlock) }

        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        protected val SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0)
    }
}