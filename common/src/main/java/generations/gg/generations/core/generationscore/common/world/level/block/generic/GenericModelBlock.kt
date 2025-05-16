package generations.gg.generations.core.generationscore.common.world.level.block.generic

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

abstract class GenericModelBlock<T> protected constructor(
    properties: Properties,
    protected val blockEntityFunction: RegistrySupplier<MutableBlockEntityType<T>>,
    private val baseBlockPosFunction: (BlockPos, BlockState) -> BlockPos = DEFAULT_BLOCK_POS_FUNCTION,
    protected val modelResource: ResourceLocation?
) : BaseEntityBlock(properties), SimpleWaterloggedBlock,
    VariantProvider where T : BlockEntity, T : ModelContextProviders.ModelProvider {
    init {
        this.registerDefaultState(createDefaultState())
    }

    protected open fun createDefaultState(): BlockState {
        return getStateDefinition().any().setValue(WATERLOGGED, false)
    }

    override fun setPlacedBy(
        level: Level,
        blockPos: BlockPos,
        blockState: BlockState,
        livingEntity: LivingEntity?,
        itemStack: ItemStack
    ) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack)
    }

    override fun getStateForPlacement(blockPlaceContext: BlockPlaceContext): BlockState? {
        return stateFromContext(blockPlaceContext)
    }

    fun stateFromContext(context: BlockPlaceContext): BlockState {
        return defaultBlockState().setValue(
            WATERLOGGED,
            context.level.getFluidState(context.clickedPos).type === Fluids.WATER
        )
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? =
        if (baseBlockPosFunction.invoke(pos, state) == pos) blockEntityFunction.get().create(pos, state) else null

    public override fun getSeed(state: BlockState, pos: BlockPos): Long = Mth.getSeed(getBaseBlockPos(pos, state))

    override fun getModel(): ResourceLocation? {
        return modelResource
    }

    fun getBaseBlockPos(pos: BlockPos, state: BlockState): BlockPos {
        return baseBlockPosFunction.invoke(pos, state)
    }

    public override fun getOcclusionShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape {
        return Shapes.empty()
    }

    fun getAssoicatedBlockEntity(level: BlockGetter, pos: BlockPos): Optional<T?> {
        return blockEntityFunction.toOptional().map { a: MutableBlockEntityType<T> ->
            a.getBlockEntity(
                level,
                getBaseBlockPos(pos, level.getBlockState(pos))
            )
        }
    }

    open fun computeRenderBoundingBox(level: Level, pos: BlockPos, state: BlockState): AABB {
        return AABB.encapsulatingFullBlocks(pos, pos.offset(1, 1, 1))
    }

    fun canRender(blockEntity: BlockEntity): Boolean {
        return canRender(blockEntity.level!!, blockEntity.blockPos, blockEntity.blockState)
    }

    open fun canRender(level: Level, blockPos: BlockPos, blockState: BlockState): Boolean {
        return true
    }

    public override fun getFluidState(blockState: BlockState): FluidState {
        return if (isWaterLogged(blockState)) {
            Fluids.WATER.getSource(false)
        } else {
            super.getFluidState(blockState)
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder.add(WATERLOGGED))
    }

    public override fun updateShape(
        blockState: BlockState,
        direction: Direction,
        blockState2: BlockState,
        levelAccessor: LevelAccessor,
        blockPos: BlockPos,
        blockPos2: BlockPos
    ): BlockState {
        if (isWaterLogged(blockState)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor))
        }

        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2)
    }

    val blockEntityType: BlockEntityType<T>
        get() = blockEntityFunction.get()

    override fun getVariant(): String? {
        return null
    }

    companion object {
        @JvmStatic
        protected val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        private val DEFAULT_BLOCK_POS_FUNCTION: (BlockPos, BlockState) -> BlockPos = { pos, state -> pos }

        fun isWaterLogged(state: BlockState): Boolean {
            return state.getValue(WATERLOGGED)
        }
    }
}