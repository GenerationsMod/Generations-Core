package generations.gg.generations.core.generationscore.world.level.block.set;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;

import java.util.ArrayList;

/**
 * Makes it easier to create a set of blocks that are all related to each other.
 * @author Joseph T. McQuigg
 */
public class GenerationsBlockSet {

    private static ArrayList<GenerationsBlockSet> blockSets = new ArrayList<>();

    private final String name;
    private final RegistrySupplier<Block> baseBlock;
    private final RegistrySupplier<SlabBlock> slab;
    private final RegistrySupplier<StairBlock> stairs;
    private final RegistrySupplier<WallBlock> wall;
    private BlockFamily blockFamily;

    /**
     * Creates a new Generations block set.
     * @param name The name of the base block.
     * @param properties The properties of the blocks
     */
    public GenerationsBlockSet(String name, Block.Properties properties) {
        this(name, GenerationsBlocks.registerBlockItem(name, () -> new Block(properties)), properties);
    }

    /**
     * Creates a new Generations block set.
     * @param name The name of the base block.
     * @param baseBlock The base block.
     * @param properties The properties of the blocks
     */
    public GenerationsBlockSet(String name, RegistrySupplier<Block> baseBlock, Block.Properties properties) {
        this.name = name;
        this.baseBlock = baseBlock;
        slab = GenerationsBlocks.registerBlockItem(name + "_slab", () -> new SlabBlock(properties));
        stairs = GenerationsBlocks.registerBlockItem(name + "_stairs", () -> new StairBlock(baseBlock.get().defaultBlockState(), properties));
        wall = GenerationsBlocks.registerBlockItem(name + "_wall", () -> new WallBlock(properties));
        blockFamily = null;
        blockSets.add(this);
    }

    /**
     * Gets the name of the base block.
     * @return The name of the base block.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the base block.
     * @return The base block.
     */
    public Block getBaseBlock() {
        return baseBlock.get();
    }

    /**
     * Gets BaseBlock's RegistrySupplier.
     * @return The base block's RegistrySupplier.
     */
    public RegistrySupplier<Block> getBaseBlockSupplier() {
        return baseBlock;
    }

    /**
     * Gets the slab block.
     * @return The slab block.
     */
    public SlabBlock getSlab() {
        return slab.get();
    }

    /**
     * Gets the stairs block.
     * @return The stairs block.
     */
    public StairBlock getStairs() {
        return stairs.get();
    }

    /**
     * Gets the wall block.
     * @return The wall block.
     */
    public WallBlock getWall() {
        return wall.get();
    }

    /**
     * Gets the block family.
     * @return The block family.
     */
    public BlockFamily getBlockFamily() {
        return blockFamily;
    }

    /**
     * Sets the block family.
     * @param blockFamily The block family.
     */
    public void setBlockFamily(BlockFamily blockFamily) {
        this.blockFamily = blockFamily;
    }

    /**
     * Gets all of the block sets.
     * @return ArrayList of all of the block sets.
     */
    public static ArrayList<GenerationsBlockSet> getBlockSets() {
       return blockSets;
    }

    /**
     * Generates all of the block families.
     */
    public static void generateAllBlockFamilies() {
        for (GenerationsBlockSet blockSet : blockSets)
            blockSet.setBlockFamily(new BlockFamily.Builder(blockSet.getBaseBlock()).slab(blockSet.getSlab()).stairs(blockSet.getStairs()).wall(blockSet.getWall()).recipeGroupPrefix(blockSet.name).recipeUnlockedBy("has_" + blockSet.name).getFamily());
        GenerationsFullBlockSet.updateBlockFamilies();
    }
}
