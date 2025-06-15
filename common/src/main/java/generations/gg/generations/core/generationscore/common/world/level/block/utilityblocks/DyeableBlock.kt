package generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks

import com.mojang.datafixers.Products
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.DyeItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.phys.BlockHitResult

fun <T: Any> T.applyIfTrue(predicate: (T) -> Boolean, action: (T) -> T): T {
    if(predicate.invoke(this)) return action.invoke(this)
    return this
}

abstract class DyeableBlock<T : ModelProvidingBlockEntity, V : DyeableBlock<T, V>> : GenericRotatableModelBlock<T> {
    @JvmField
    val color: DyeColor
    val map: Map<DyeColor, Block>

    constructor(
        color: DyeColor,
        function: Map<DyeColor, Block>,
        baseBlockPosFunction: (BlockPos, BlockState) -> BlockPos,
        arg: Properties,
        model: ResourceLocation,
        width: Int,
        height: Int,
        length: Int
    ) : super(arg, baseBlockPosFunction, model, width, height, length) {
        this.color = color
        this.map = function
    }

    constructor(
        color: DyeColor,
        function: Map<DyeColor, Block>,
        baseBlockPosFunction: (BlockPos, BlockState) -> BlockPos,
        arg: Properties,
        model: ResourceLocation
    ) : super(arg, baseBlockPosFunction, model) {
        this.color = color
        this.map = function
    }

    constructor(
        arg: Properties,
        color: DyeColor,
        function: Map<DyeColor, Block>,
        model: ResourceLocation,
        width: Int,
        height: Int,
        length: Int
    ) : super(arg, model = model, width = width, height = height, length = length) {
        this.color = color
        this.map = function
    }

    constructor(
        color: DyeColor,
        function: Map<DyeColor, Block>,
        arg: Properties,
        model: ResourceLocation
    ) : super(arg, model = model) {
        this.color = color
        this.map = function
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
        var state = state
        var pos: BlockPos = pos

        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            pos = getBaseBlockPos(pos, state)
            state = level.getBlockState(pos)
            val block = state.block

            return if (block is DyeableBlock<*, *> && !block.tryDyeColor(state, level, pos, player, hand, hitResult)) {
                block.serverUse(
                    stack,
                    state,
                    level as ServerLevel,
                    pos,
                    player as ServerPlayer,
                    hand,
                    hitResult
                )
            } else ItemInteractionResult.SUCCESS
        }
        return ItemInteractionResult.FAIL
    }

    fun getItemFromDyeColor(color: DyeColor): Item {
        return getBlockFromDyeColor(color).asItem()
    }

    fun getBlockFromDyeColor(color: DyeColor): Block = map[color]!!

    fun tryDyeColor(
        state: BlockState,
        world: Level,
        pos: BlockPos,
        player: Player,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): Boolean {
        val heldItem = player.mainHandItem

        val isEmpty = !heldItem.isEmpty
        val isDye = heldItem.item is DyeItem

        if (isEmpty && isDye) {
            val dyeColor = (heldItem.item as DyeItem).dyeColor

            val baseState = world.getBlockState(pos)

            if (javaClass.isInstance(baseState.block)) {
                val baseBlock: DyeableBlock<*, *> = javaClass.cast(baseState.block)


                if (baseBlock.color != dyeColor) {
                    val base = getBaseBlockPos(pos, state)

                    if (!player.isCreative) heldItem.shrink(1)

                    val newBlock = getBlockFromDyeColor(dyeColor).instanceOrNull<DyeableBlock<*, *>>() ?: return false //TODO: This is a stop gap due to codec(). Yea its that expansive.

                    val defaultState = newBlock.defaultBlockState().applyIfTrue({ it.hasProperty(FACING) }, { it.setValue(FACING, baseState.getValue(FACING)) } )

                    val dir = state.getValue(FACING)

                    for (x in 0 until width + 1) {
                        for (y in 0 until height + 1) {
                            for (z in 0 until length + 1) {
                                val adjustX = adjustX(x)
                                val adjustZ = adjustX(x)

                                val blockPos = base.relative(dir.counterClockWise, adjustX).relative(Direction.UP, y)
                                    .relative(dir, adjustZ)

                                val currentState = world.getBlockState(blockPos)

                                val stateX = baseBlock.getWidthValue(currentState)
                                val stateY = baseBlock.getHeightValue(currentState)
                                val stateZ = baseBlock.getLengthValue(currentState)

                                world.setBlock(
                                    blockPos, newBlock.setSize(
                                        defaultState.setValue(
                                            WATERLOGGED, currentState.getValue(
                                                WATERLOGGED
                                            )
                                        ), stateX, stateY, stateZ
                                    ), 2, 0
                                )
                            }
                        }
                    }
                    return true
                }
            }
        }

        return false
    }

    override fun getCloneItemStack(level: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(getItemFromDyeColor(color))

    }

    protected open fun serverUse(
        stack: ItemStack,
        state: BlockState,
        world: ServerLevel,
        pos: BlockPos,
        player: ServerPlayer,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
    }

    override fun getDrops(state: BlockState, params: LootParams.Builder): List<ItemStack> {
        return if (getWidthValue(state) == 0 || getHeightValue(state) == 0 || getLengthValue(state) == 0) super.getDrops(
            state,
            params
        ) else emptyList()
    }

    override fun getVariant(): String? {
        return color.serializedName
    }

    companion object {
        fun <T: DyeableBlock<*, *>> simpleDyedCodec(supplier: (Properties, DyeColor, Map<DyeColor, Block>) -> T): MapCodec<T> = RecordCodecBuilder.mapCodec<T> { dyedCodecBuilder(it).apply(it, supplier::invoke) }

        fun <T: DyeableBlock<*, *>> dyedCodecBuilder(instance: RecordCodecBuilder.Instance<T>): Products.P3<RecordCodecBuilder.Mu<T>, Properties, DyeColor, MutableMap<DyeColor, Block>> {
            return instance.group(
                propertiesCodec(),
                DyeColor.CODEC.fieldOf("color").forGetter { it.color },
                Codec.unboundedMap(DyeColor.CODEC, BuiltInRegistries.BLOCK.byNameCodec()).fieldOf("map").forGetter { it.map }
            )
        }
    }
}
