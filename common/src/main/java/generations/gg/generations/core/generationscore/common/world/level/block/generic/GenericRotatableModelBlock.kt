package generations.gg.generations.core.generationscore.common.world.level.block.generic

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.util.extensions.between
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.phys.AABB

abstract class GenericRotatableModelBlock<T>(properties: Properties, blockEntityFunction: RegistrySupplier<BlockEntityType<T>>, posFunction: (BlockPos, BlockState) -> BlockPos = DEFAULT_BLOCK_ROTATE_POS_FUNCTION, model: ResourceLocation? = null, val width: Int = 0, val height: Int = 0, val length: Int = 0) : GenericModelBlock<T>(properties, blockEntityFunction, posFunction, model) where T : BlockEntity, T : ModelContextProviders.ModelProvider {

    init {
        assignSize(width, height, length)
        reassignStateDefinition()
        this.registerDefaultState(createDefaultState())

        super.modelResource
    }

    private fun assignSize(width: Int, height: Int, length: Int) {
        val function: (Size, Int) -> Unit = { size, value -> if(value != 0) SIZE_PROPERTIES.computeIfAbsent(size) { mutableMapOf() }.computeIfAbsent(value) { IntegerProperty.create(size.name.lowercase(), 0, value) } }

        function.invoke(Size.WIDTH, width)
        function.invoke(Size.HEIGHT, height)
        function.invoke(Size.LENGTH, length)
    }

    protected fun reassignStateDefinition() {
        val builder = StateDefinition.Builder<Block, BlockState>(this)
        this.createBlockStateDefinition(builder)
        this.stateDefinition = builder.create(Block::defaultBlockState, ::BlockState)
    }

    override fun createDefaultState(): BlockState {
        return setSize(
            super.createDefaultState().setValue(FACING, Direction.NORTH),
            baseX, 0,
            baseZ
        )
    }

    public override fun mirror(state: BlockState, mirrorIn: Mirror): BlockState = state.rotate(mirrorIn.getRotation(state.getValue(FACING)))

    public override fun rotate(state: BlockState, direction: Rotation): BlockState = state.setValue(FACING, direction.rotate(state.getValue(FACING)))

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val pos: BlockPos = context.clickedPos
        val level: Level = context.level
        val dir: Direction = context.horizontalDirection.opposite

        return super.getStateForPlacement(context)?.takeIf { pos.y < level.maxBuildHeight - height && isAreaClear(level, dir, pos) }?.let {
            setSize(
                it.setValue(FACING, dir),
                baseX, 0,
                baseZ
            )
        }
    }

    open val baseX: Int
        get() = 0

    val baseZ: Int
        get() = 0

    private fun isAreaClear(level: LevelReader, dir: Direction, pos: BlockPos): Boolean {
        for (x in 0 until width + 1) {
            for (z in 0 until length + 1) {
                for (y in 0 until height + 1) {
                    if (!validPosition(x, y, z)) continue

                    val blockPos: BlockPos = adjustBlockPos(pos, dir, x, y, z, true)

                    val state: BlockState = level.getBlockState(blockPos)

                    if (!state.canBeReplaced() || state.`is`(this)) {
                        return false
                    }
                }
            }
        }

        return true
    }

    protected fun adjustBlockPos(pos: BlockPos, dir: Direction, x: Int, y: Int, z: Int, positive: Boolean): BlockPos {
        return if (positive) pos.relative(dir.counterClockWise, adjustX(x)).relative(Direction.UP, y)
            .relative(dir.opposite, adjustZ(z)) else pos.relative(dir.clockWise, adjustX(x)).relative(
            Direction.DOWN, y
        ).relative(dir, adjustZ(z))
    }

    protected open fun validPosition(x: Int, y: Int, z: Int): Boolean {
        return true
    }

    protected fun matches(state: BlockState, x: Int, y: Int, z: Int): Boolean {
        return getWidthValue(state) == x && getHeightValue(state) == y && getLengthValue(state) == z
    }

    public override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val dir: Direction = state.getValue(FACING)
        val base: BlockPos = getBaseBlockPos(pos, state)

        return isAreaClear(level, dir, base)
    }

    override fun playerWillDestroy(level: Level, pos: BlockPos, state: BlockState, player: Player): BlockState {
        var position: BlockPos = getBaseBlockPos(pos, state)
        val facing: Direction = state.getValue(FACING)

        for (x in 0..width) {
            for (z in 0..length) {
                for (y in 0..height) {
                    if (!validPosition(x, y, z)) continue


                    val blockPos: BlockPos = adjustBlockPos(position, facing, x, y, z, true)



                    level.destroyBlock(blockPos, !player.isCreative && x == baseX && y == 0 && z == baseZ)
                }
            }
        }

        return super.playerWillDestroy(level, position, state, player)
    }


    override fun playerDestroy(
        level: Level,
        player: Player,
        pos: BlockPos,
        state: BlockState,
        blockEntity: BlockEntity?,
        tool: ItemStack
    ) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, tool)
    }

    public override fun getDrops(state: BlockState, params: LootParams.Builder): List<ItemStack> {
        if (getWidthValue(state) == baseX && getLengthValue(state) == 0 && getHeightValue(state) == baseZ) return super.getDrops(
            state,
            params
        )
        else return emptyList()
    }

    private fun getValue(level: LevelReader, pos: BlockPos, direction: Direction, size: Size): Int {
        val blockState: BlockState = level.getBlockState(pos.relative(direction))
        return if (blockState.`is`(this)) getValue(blockState, size) else -1
    }

    fun getValue(blockState: BlockState, size: Size): Int {
        return when (size) {
            Size.WIDTH -> getWidthValue(blockState)
            Size.HEIGHT -> getHeightValue(blockState)
            Size.LENGTH -> getLengthValue(blockState)
        }
    }

    fun getSizeProperty(size: Size): IntegerProperty? = when(size) {
        Size.WIDTH -> widthProperty
        Size.LENGTH -> lengthProperty
        Size.HEIGHT -> heightProperty
    }

    private fun getSize(axis: Direction.Axis): Int {
        return when (axis) {
            Direction.Axis.X -> width
            Direction.Axis.Y -> height
            Direction.Axis.Z -> length
        }
    }

    //    @Override
    //    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
    //        if(canSurvive(state, level, currentPos)) return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    //        else return Blocks.AIR.defaultBlockState();
    //    }
    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(FACING)
        if (width != 0) builder.add(
            SIZE_PROPERTIES.get(Size.WIDTH)!!
                .get(width)
        )
        if (height != 0) builder.add(
            SIZE_PROPERTIES.get(Size.HEIGHT)!!
                .get(height)
        )
        if (length != 0) builder.add(
            SIZE_PROPERTIES.get(Size.LENGTH)!!
                .get(length)
        )
    }

    override fun computeRenderBoundingBox(level: Level, pos: BlockPos, state: BlockState): AABB {
        return AABB.encapsulatingFullBlocks(
            pos, adjustBlockPos(
                getBaseBlockPos(pos, state), state.getValue(
                    FACING
                ), width + 1, height + 1, length + 1, true
            )
        )
    }

    override fun canRender(level: Level, blockPos: BlockPos, blockState: BlockState): Boolean {
        return getWidthValue(blockState) == baseX && getHeightValue(blockState) == 0 && getLengthValue(blockState) == baseZ
    }

    fun getAngle(state: BlockState): Float {
        return state.getValue(FACING).toYRot()
    }

    override fun setPlacedBy(level: Level, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
        for (x in 0..width) {
            for (z in 0..length) {
                for (y in 0..height) {
                    if (!validPosition(x, y, z)) continue

                    if (x == baseX && y == 0 && z == baseZ) continue
                    val blockPos: BlockPos = adjustBlockPos(pos, state.getValue(FACING), x, y, z, true)
                    level.setBlock(
                        blockPos,
                        setSize(
                            state.setValue(WATERLOGGED, level.getFluidState(blockPos).type === Fluids.WATER),
                            x,
                            y,
                            z
                        ),
                        2
                    )
                }
            }
        }
    }

    fun getLengthValue(state: BlockState): Int {
        return if (state.`is`(this) && length != 0) state.getValue(lengthProperty) else 0
    }

    fun setLengthValue(state: BlockState, value: Int): BlockState {
        return if (state.`is`(this) && length > 0 && value.between(0, length)) state.setValue(
            lengthProperty, value
        ) else state
    }

    fun getHeightValue(state: BlockState): Int {
        return if (state.`is`(this) && height != 0) state.getValue(heightProperty) else 0
    }

    fun setHeighthValue(state: BlockState, value: Int): BlockState {
        return if (state.`is`(this) && height > 0 && value.between(0, height)) state.setValue(
            heightProperty, value
        ) else state
    }

    fun getWidthValue(state: BlockState): Int {
        return if (state.`is`(this) && width != 0) state.getValue(widthProperty) else 0
    }

    fun setWidthValue(state: BlockState, value: Int): BlockState {
        return if (state.`is`(this) && width > 0 && value.between(0, width)) state.setValue(
            widthProperty, value
        ) else state
    }

    val widthProperty: IntegerProperty?
        get() = SIZE_PROPERTIES[Size.WIDTH]?.get(width)

    val heightProperty: IntegerProperty?
        get() = SIZE_PROPERTIES[Size.HEIGHT]?.get(height)

    val lengthProperty: IntegerProperty?
        get() = SIZE_PROPERTIES[Size.LENGTH]?.get(length)

    fun setSize(state: BlockState, x: Int, y: Int, z: Int): BlockState {
        var state: BlockState = state
        state = setWidthValue(state, x)
        state = setHeighthValue(state, y)
        state = setLengthValue(state, z)
        return state
    }

    fun adjustX(x: Int): Int {
        return x - baseX
    }

    fun adjustZ(z: Int): Int {
        return z - baseZ
    }

    enum class Size {
        //TODO: THink of better name
        WIDTH, LENGTH, HEIGHT
    }

    open fun shouldRotateSpecial(): Boolean {
        return true
    }

    public override fun getSoundType(state: BlockState): SoundType {
        return if ((getWidthValue(state) == baseX && getHeightValue(state) == 0 && getLengthValue(state) == baseZ)) super.getSoundType(
            state
        ) else SoundType.EMPTY
    }

    companion object {
        private val SIZE_PROPERTIES: MutableMap<Size, MutableMap<Int, IntegerProperty>> = mutableMapOf()

        @JvmField
        val FACING: DirectionProperty = HorizontalDirectionalBlock.FACING

        private val DEFAULT_BLOCK_ROTATE_POS_FUNCTION: (BlockPos, BlockState) -> BlockPos = { pos, state ->
            val block = state.block
                if (block is GenericRotatableModelBlock<*>) {
                    val facing: Direction = state.getValue(FACING)
                    val x: Int = block.getWidthValue(state)
                    val y: Int = block.getHeightValue(state)
                    val z: Int = block.getLengthValue(state)

                    block.adjustBlockPos(pos, facing, x, y, z, false)
                } else pos
            }
    }
}