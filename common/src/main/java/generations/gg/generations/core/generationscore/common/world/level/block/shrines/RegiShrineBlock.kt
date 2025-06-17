package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.tags.GenerationsBlockTags
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.world.item.legends.RegiKeyItem
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.UnownBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*
import java.util.function.BiPredicate
import java.util.function.Consumer
import java.util.stream.Collectors
import java.util.stream.IntStream

class RegiShrineBlock(properties: Properties, model: ResourceLocation, speciesKey: SpeciesKey) : ShrineBlock(properties, model) {
    private val species: SpeciesKey
    private val list: List<String>

    override val blockEntityType
        get() = GenerationsBlockEntities.GENERIC_SHRINE

    override fun codec(): MapCodec<RegiShrineBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    init {
        val cipher = "-" + speciesKey.species.path.uppercase(Locale.getDefault()) + "-"
        list = IntStream.range(0, cipher.length - 2).boxed().map { a: Int -> getSubSequence(cipher, a) }.collect(
            Collectors.toList()
        )
        this.species = speciesKey
    }

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult,
    ): ItemInteractionResult {
        if (!level.isClientSide() && stack.item.instanceOrNull<RegiKeyItem>()?.speciesKey == species && GenerationsCore.CONFIG!!.caught.capped(
                player as ServerPlayer,
                species
            )
        ) {
            val blockPos = searchForBlock(
                level, pos, 15, 1
            ) { level1: Level, pos1: BlockPos? -> level1.getBlockState(pos1).block is UnownBlock }

            if (blockPos.isNotEmpty()) {
                val list = checkForUnownSequence(level, blockPos[0])
                if (list.isNotEmpty() && level.getBlockEntity(pos) is ShrineBlockEntity) {

                    list.forEach(Consumer { a: BlockPos? -> level.destroyBlock(a, false) })
                    player.getItemInHand(hand).shrink(1)
                }
            }

            return ItemInteractionResult.SUCCESS
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

    private fun checkForUnownSequence(world: Level, pos: BlockPos): List<BlockPos> {
        val getIndex = { facing: Direction, blockPos: BlockPos -> getSymbolSequence(world, facing, blockPos) }
        return checkIfInSequence(Direction.Axis.Z, pos, getIndex) ?: checkIfInSequence(Direction.Axis.X, pos, getIndex) ?: emptyList()
    }

    private fun checkIfInSequence(
        axis: Direction.Axis,
        pos: BlockPos,
        getSymbol: (Direction, BlockPos) -> String,
    ): List<BlockPos>? {
        var facing = Direction.get(Direction.AxisDirection.POSITIVE, axis)
        var sequence = getSymbol.invoke(facing, pos)

        if (!list.contains(sequence)) {
            sequence = StringBuilder(sequence).reverse().toString()

            if (!list.contains(sequence)) {
                return null
            } else {
                facing = facing.opposite
            }
        }

        val base = pos.relative(facing, list.indexOf(sequence))
        val positions: MutableList<BlockPos> = ArrayList()

        for (i in list.indices) {
            val f = base.relative(facing.opposite, i)

            if (list.contains(getSymbol.invoke(facing, f))) {
                positions.add(f)
            }
        }

        return if (positions.isNotEmpty() && positions.size == list.size) positions else null
    }

    private fun getSubSequence(cipher: String, i: Int): String {
        return cipher.substring(i, i + 3)
    }

    companion object {
        private val SHAPE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.3125, 1.0),
                Shapes.box(0.125, 0.3125, 0.125, 0.875, 0.8125, 0.875),
                BooleanOp.OR
            ), Direction.NORTH
        )

        private fun getSymbolSequence(world: Level, facing: Direction, pos: BlockPos): String {
            return getSymbol(world, pos.relative(facing)) + getSymbol(world, pos) + getSymbol(
                world,
                pos.relative(facing.opposite)
            )
        }

        val CODEC: MapCodec<RegiShrineBlock> = Codecs.mapCodec { group(
            propertiesCodec(),
            ResourceLocation.CODEC.fieldOf("model").forGetter { it.modelResource!! },
            SpeciesKey.CODEC.fieldOf("species").forGetter { it.species }
        ).apply(this, ::RegiShrineBlock) }

        fun searchForBlock(
            world: Level,
            pos: BlockPos,
            radius: Int,
            amount: Int,
            block: BiPredicate<Level, BlockPos>,
        ): List<BlockPos> {
            return searchForBlock(world, pos, radius, radius, radius, amount, block)
        }

        fun searchForBlock(
            world: Level,
            pos: BlockPos,
            xRadius: Int,
            yRadius: Int,
            zRadius: Int,
            amount: Int,
            block: BiPredicate<Level, BlockPos>,
        ): List<BlockPos> {
            val states: MutableList<BlockPos> = ArrayList()

            for (x in -xRadius until xRadius) {
                for (y in -yRadius until yRadius) {
                    for (z in -zRadius until zRadius) {
                        val blockPos = pos.offset(x, y, z)
                        if (block.test(world, blockPos)) {
                            states.add(blockPos)
                            if (states.size == amount) return states
                        }
                    }
                }
            }

            return states
        }


        private fun isPillar(level: Level, pos: BlockPos): Boolean {
            val block = level.getBlockState(pos)

            return block.`is`(GenerationsBlockTags.REGI_STANDS) && level.getBlockState(pos.above()).block is UnownBlock
        }

        private fun getSymbol(level: Level, pos: BlockPos): String {
            return  if(isPillar(level, pos)) symbolFromState(level.getBlockState(pos.above())) ?: "-" else "-"
        }

        private fun symbolFromState(state: BlockState): String? = state.block.instanceOrNull<UnownBlock>()?.glyph
    }
}
