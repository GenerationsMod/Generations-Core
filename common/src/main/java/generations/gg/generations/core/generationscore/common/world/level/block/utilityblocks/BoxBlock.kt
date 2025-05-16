package generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BoxBlockEntity
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
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
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class BoxBlock(properties: Properties) : BaseEntityBlock(properties) {
    init {
        this.registerDefaultState(
            stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(
                OPEN, false
            )
        )
    }

    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return CODEC
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hit: BlockHitResult
    ): InteractionResult {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS
        }

        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is BoxBlockEntity) {
            val open = state.getValue(OPEN)

            if (player.isShiftKeyDown) {
                if (open) {
                    blockEntity.playSound(state, GenerationsSounds.BOX_CLOSED.get())
                    blockEntity.updateBlockState(state, false)
                } else {
                    blockEntity.playSound(state, GenerationsSounds.BOX_OPEN.get())
                    blockEntity.updateBlockState(state, true)
                }
            } else if (open) {
                player.openMenu(blockEntity)
                player.awardStat(Stats.OPEN_BARREL)
                PiglinAi.angerNearbyPiglins(player, true)
            }
        }
        return InteractionResult.CONSUME
    }

    public override fun onRemove(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        newState: BlockState,
        movedByPiston: Boolean
    ) {
        if (state.`is`(newState.block)) {
            return
        }
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is Container) {
            Containers.dropContents(level, pos, blockEntity as Container)
            level.updateNeighbourForOutputSignal(pos, this)
        }
        super.onRemove(state, level, pos, newState, movedByPiston)
    }

    public override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is BoxBlockEntity) {
            blockEntity.recheckOpen()
        }
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return BoxBlockEntity(pos, state)
    }

    public override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    //    @Override
    //    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
    //        if (stack.getDisplayName().hasCustomHoverName() && level.getBlockEntity(pos) instanceof BoxBlockEntity boxBlockEntity) {
    //            boxBlockEntity.setCustomName(stack.getHoverName());
    //        }
    //    }
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
        builder.add(FACING, OPEN)
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return if (state.getValue(OPEN)) OPEN_SHAPE.getShape(state)
        else CLOSED_SHAPE
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return defaultBlockState().setValue(FACING, context.horizontalDirection.opposite)
    }

    companion object {
        val OPEN_SHAPE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.or(
                Shapes.box(0.125, 0.0, 0.125, 0.875, 0.0625, 0.875),
                Shapes.box(0.875, 0.0, 0.125, 0.9375, 0.875, 0.875),
                Shapes.box(0.0625, 0.0, 0.875, 0.9375, 0.875, 0.9375),
                Shapes.box(0.0625, 0.0, 0.125, 0.125, 0.875, 0.875),
                Shapes.box(0.0625, 0.0, 0.0625, 0.9375, 0.875, 0.125)
            )
        )
        val CLOSED_SHAPE: VoxelShape = Shapes.box(0.0625, 0.0, 0.0625, 0.9375, 0.875, 0.9375)

        val CODEC: MapCodec<BoxBlock> = simpleCodec(::BoxBlock)

        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val OPEN: BooleanProperty = BlockStateProperties.OPEN
    }
}

