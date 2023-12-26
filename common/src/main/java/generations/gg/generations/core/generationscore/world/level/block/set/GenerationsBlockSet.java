package generations.gg.generations.core.generationscore.world.level.block.set;

import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;

import java.util.ArrayList;

public class GenerationsBlockSet {

    private static ArrayList<GenerationsBlockSet> blockSets = new ArrayList<>();

    private final Block baseBlock;
    private final SlabBlock slab;
    private final StairBlock stairs;
    private final WallBlock wall;
    private final BlockFamily blockFamily;

    public GenerationsBlockSet(String name, Block.Properties properties) {
        this(name, GenerationsBlocks.registerBlockItem(name, () -> new Block(properties)).get(), properties);
    }

    public GenerationsBlockSet(String name, Block baseBlock, Block.Properties properties) {
        this.baseBlock = baseBlock;
        slab = GenerationsBlocks.registerBlockItem(name + "_slab", () -> new SlabBlock(properties)).get();
        stairs = GenerationsBlocks.registerBlockItem(name + "_stairs", () -> new StairBlock(baseBlock.defaultBlockState(), properties)).get();
        wall = GenerationsBlocks.registerBlockItem(name + "_wall", () -> new WallBlock(properties)).get();
        blockFamily = new BlockFamily.Builder(baseBlock).slab(slab).stairs(stairs).wall(wall).recipeGroupPrefix(name).recipeUnlockedBy("has_" + name).getFamily();
        blockSets.add(this);
    }

    public Block getBaseBlock() {
        return baseBlock;
    }

    public SlabBlock getSlab() {
        return slab;
    }

    public StairBlock getStairs() {
        return stairs;
    }

    public WallBlock getWall() {
        return wall;
    }

    public BlockFamily getBlockFamily() {
        return blockFamily;
    }

    public static ArrayList<GenerationsBlockSet> getBlockSets() {
       return blockSets;
    }
}
