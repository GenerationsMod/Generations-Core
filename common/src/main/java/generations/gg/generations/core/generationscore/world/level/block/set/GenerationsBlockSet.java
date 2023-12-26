package generations.gg.generations.core.generationscore.world.level.block.set;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;

import java.util.ArrayList;

public class GenerationsBlockSet {

    private static ArrayList<GenerationsBlockSet> blockSets = new ArrayList<>();

    private final String name;
    private final RegistrySupplier<Block> baseBlock;
    private final RegistrySupplier<SlabBlock> slab;
    private final RegistrySupplier<StairBlock> stairs;
    private final RegistrySupplier<WallBlock> wall;
    private BlockFamily blockFamily;

    public GenerationsBlockSet(String name, Block.Properties properties) {
        this(name, GenerationsBlocks.registerBlockItem(name, () -> new Block(properties)), properties);
    }

    public GenerationsBlockSet(String name, RegistrySupplier<Block> baseBlock, Block.Properties properties) {
        this.name = name;
        this.baseBlock = baseBlock;
        slab = GenerationsBlocks.registerBlockItem(name + "_slab", () -> new SlabBlock(properties));
        stairs = GenerationsBlocks.registerBlockItem(name + "_stairs", () -> new StairBlock(baseBlock.get().defaultBlockState(), properties));
        wall = GenerationsBlocks.registerBlockItem(name + "_wall", () -> new WallBlock(properties));
        blockFamily = null;
        blockSets.add(this);
    }

    public String getName() {
        return name;
    }

    public Block getBaseBlock() {
        return baseBlock.get();
    }

    public SlabBlock getSlab() {
        return slab.get();
    }

    public StairBlock getStairs() {
        return stairs.get();
    }

    public WallBlock getWall() {
        return wall.get();
    }

    public BlockFamily getBlockFamily() {
        return blockFamily;
    }

    public void setBlockFamily(BlockFamily blockFamily) {
        this.blockFamily = blockFamily;
    }

    public static ArrayList<GenerationsBlockSet> getBlockSets() {
       return blockSets;
    }

    public static void generateAllBlockFamilies() {
        for (GenerationsBlockSet blockSet : blockSets)
            blockSet.setBlockFamily(new BlockFamily.Builder(blockSet.getBaseBlock()).slab(blockSet.getSlab()).stairs(blockSet.getStairs()).wall(blockSet.getWall()).recipeGroupPrefix(blockSet.name).recipeUnlockedBy("has_" + blockSet.name).getFamily());
    }
}
