package generations.gg.generations.core.generationscore.common.world.level.block.set

import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsFullBlockSet.Companion.updateBlockFamilies
import net.minecraft.core.Holder
import net.minecraft.data.BlockFamily
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import org.jetbrains.annotations.ApiStatus

/**
 * Makes it easier to create a set of blocks that are all related to each other.
 * @author Joseph T. McQuigg
 */
open class GenerationsBlockSet(
    /**
     * Gets the name of the base block.
     * @return The name of the base block.
     */
    val name: String,
    properties: BlockBehaviour.Properties = BlockBehaviour.Properties.ofFullCopy(Blocks.STONE),
    /**
     * Gets BaseBlock's RegistrySupplier.
     * @return The base block's RegistrySupplier.
     */
    val baseBlockHolder: Holder<Block> = GenerationsBlocks.registerBlockItem<Block>(name, { Block(properties)} )
) {
    val slabHolder: Holder<Block>
    val stairsHolder: Holder<Block>
    val wallHolder: Holder<Block>
    /**
     * Gets the block family.
     * @return The block family.
     */
    /**
     * Sets the block family.
     * @param blockFamily The block family.
     */
    @JvmField
    var blockFamily: BlockFamily?

    /**
     * Creates a new Generations block set.
     * @param name The name of the base block.
     * @param baseBlock The base block.
     * @param properties The properties of the blocks
     */
    init {
        slabHolder = registerBlockItem("${name}_slab") { SlabBlock(properties) }
        stairsHolder = registerBlockItem("${name}_stairs") { StairBlock(baseBlock.defaultBlockState(), properties) }
        wallHolder = registerBlockItem("${name}_wall") { WallBlock(properties) }
        blockFamily = null
        blockSets.add(this)
    }

    protected open fun <T : Block> registerBlockItem(name: String, blockSupplier: () -> T): Holder<Block> = GenerationsBlocks.registerBlockItem(name, blockSupplier)

    val baseBlock: Block get() = baseBlockHolder.value()
    val slab: SlabBlock get() = slabHolder.value() as SlabBlock
    val stairs: StairBlock get() = stairsHolder.value() as StairBlock
    val wall: WallBlock get() = wallHolder.value() as WallBlock

    open val allBlocks: List<Block>
        /**
         * Returns a list of the full family
         * @return The full family
         */
        get() = listOf(
            baseBlock,
            slab,
            stairs,
            wall
        )

    companion object {
        /**
         * Gets all of the block sets.
         * @return ArrayList of all of the block sets.
         */
        @JvmField
        val blockSets: ArrayList<GenerationsBlockSet> = ArrayList()

        /**
         * Generates all of the block families.
         */
        @ApiStatus.Internal
        fun generateAllBlockFamilies() {
            for (blockSet: GenerationsBlockSet in blockSets) blockSet.blockFamily =
                BlockFamily.Builder(blockSet.baseBlock).slab(blockSet.slab)
                    .stairs(blockSet.stairs).wall(blockSet.wall).recipeGroupPrefix(blockSet.name)
                    .recipeUnlockedBy("has_" + blockSet.name).family
            updateBlockFamilies()
        }
    }
}
