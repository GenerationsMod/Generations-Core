package generations.gg.generations.core.generationscore.world.level.block.set;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

public class GenerationsFullBlockSet extends GenerationsBlockSet {
    private static ArrayList<GenerationsFullBlockSet> fullBlockSets = new ArrayList<>();
    private final RegistrySupplier<ButtonBlock> button;
    private final RegistrySupplier<PressurePlateBlock> pressurePlate;

    public GenerationsFullBlockSet(String name, Block.Properties properties, BlockSetType type, RegistrySupplier<Block> baseBlock) {
        super(name, baseBlock, properties);
        button = registerBlockItem(name + "_button", () -> new ButtonBlock(properties, type, 20, false));
        pressurePlate = registerBlockItem(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, properties, type));
        GenerationsBlockSet.getBlockSets().remove(this);
        fullBlockSets.add(this);
    }

    public GenerationsFullBlockSet(String name, Block.Properties properties, BlockSetType type) {
        super(name, properties);
        button = registerBlockItem(name + "_button", () -> new ButtonBlock(properties, type, 20, false));
        pressurePlate = registerBlockItem(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, properties, type));
        GenerationsBlockSet.getBlockSets().remove(this);
        fullBlockSets.add(this);
    }

    /**
     * Creates a new Generations block set with the default properties from Blocks#STONE.
     * @param name The name of the base block.
     * @param type The type of the block set.
     */
    public GenerationsFullBlockSet(String name, BlockSetType type) {
        this(name, BlockBehaviour.Properties.copy(Blocks.STONE), type);
    }

    /**
     * Creates a new Generations block set with the default properties from Blocks#STONE.
     * @param name The name of the base block.
     * @param type The type of the block set.
     * @param color The map color of the block set.
     */
    public GenerationsFullBlockSet(String name, DyeColor color, BlockSetType type) {
        this(name, BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(color), type);
    }

    /**
     * Gets the button.
     * @return The button.
     */
    public ButtonBlock getButton() {
        return button.get();
    }

    /**
     * Gets the pressure plate.
     * @return The pressure plate.
     */
    public PressurePlateBlock getPressurePlate() {
        return pressurePlate.get();
    }

    /**
     * Returns a list of the full family
     * @return The full family
     */
    @Override
    public List<Block> getAllBlocks() {
        return List.of(getBaseBlock(), getSlab(), getStairs(), getWall(), getButton(), getPressurePlate());
    }


    public void updateBlockFamily() {
        this.setBlockFamily(new BlockFamily.Builder(getBaseBlock()).slab(getSlab()).stairs(getStairs()).wall(getWall()).button(getButton()).pressurePlate(getPressurePlate()).recipeGroupPrefix(getName()).recipeUnlockedBy("has_" + getName()).getFamily());
    }

    @ApiStatus.Internal
    public static void updateBlockFamilies() {
        for (GenerationsFullBlockSet blockSet : fullBlockSets)
            blockSet.updateBlockFamily();
    }

    public static ArrayList<GenerationsFullBlockSet> getFullBlockSets() {
        return fullBlockSets;
    }
}
