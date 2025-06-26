package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.asValue
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MeloettaMusicBoxBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.JukeboxPlayable
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class MeloettaMusicBoxBlock(properties: Properties) :
    GenericRotatableModelBlock(
        properties = properties,
        model =GenerationsBlockEntityModels.MELOETTA_MUSIC_BOX
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.MELOETTA_MUSIC_BOX

    override fun codec(): MapCodec<MeloettaMusicBoxBlock> {
        return CODEC
    }

    override fun createDefaultState(): BlockState {
        return super.createDefaultState().setValue(HAS_RECORD, false)
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun setPlacedBy(level: Level, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
        super.setPlacedBy(level, pos, state, placer, stack)
        val customData = stack.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY)
        if (customData.contains("RecordItem")) {
            level.setBlock(pos, state.setValue(HAS_RECORD, true), 2)
        }
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (state.getValue(HAS_RECORD)) {
            val var8 = level.getBlockEntity(pos)
            if (var8 is MeloettaMusicBoxBlockEntity) {
                var8.popOutTheItem()
                return InteractionResult.sidedSuccess(level.isClientSide)
            }
        }

        return InteractionResult.PASS
    }

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        if (state.getValue(HAS_RECORD)) {
            return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION
        } else {
            val itemStack = player.getItemInHand(hand)
            val result = JukeboxPlayable.tryInsertIntoJukebox(level, pos, itemStack, player)
            return if (!result.consumesAction()) ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION else result
        }
    }

    public override fun onRemove(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        newState: BlockState,
        movedByPiston: Boolean
    ) {
        if (!state.`is`(newState.block)) {
            val blockEntity = level.getBlockEntity(pos)
            if (blockEntity is MeloettaMusicBoxBlockEntity) {
                blockEntity.popOutTheItem()
            }

            super.onRemove(state, level, pos, newState, movedByPiston)
        }
    }

    public override fun isSignalSource(state: BlockState): Boolean {
        return true
    }

    public override fun getSignal(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction): Int {
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is MeloettaMusicBoxBlockEntity) {
            if (blockEntity.songPlayer.isPlaying) {
                return 15
            }
        }

        return 0
    }

    public override fun hasAnalogOutputSignal(state: BlockState): Boolean {
        return true
    }

    public override fun getAnalogOutputSignal(state: BlockState, level: Level, pos: BlockPos): Int {
        val entity = level.getBlockEntity(pos)
        if (entity is MeloettaMusicBoxBlockEntity) {
            return entity.comparatorOutput
        }

        return 0
    }

    public override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(HAS_RECORD)
    }

    override fun <T : BlockEntity> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? = if (state.getValue(HAS_RECORD)) createTickerHelper(blockEntityType, GenerationsBlockEntities.MELOETTA_MUSIC_BOX.asValue<MeloettaMusicBoxBlockEntity>(), MeloettaMusicBoxBlockEntity.Companion::tick) else null

    companion object {
        private val SHAPE: VoxelShape = Shapes.box(0.25, 0.0, 0.25, 0.75, 0.375, 0.75)

        val HAS_RECORD: BooleanProperty = BlockStateProperties.HAS_RECORD
        val CODEC: MapCodec<MeloettaMusicBoxBlock> = simpleCodec { properties: Properties ->
            MeloettaMusicBoxBlock(
                properties
            )
        }
    }
}
