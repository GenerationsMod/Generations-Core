package generations.gg.generations.core.generationscore.world.level.block.set;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;

public class GenerationsFullBlockSet extends GenerationsBlockSet {
    private static ArrayList<GenerationsFullBlockSet> fullBlockSets = new ArrayList<>();
    private final RegistrySupplier<ButtonBlock> button;
    private final RegistrySupplier<PressurePlateBlock> pressurePlate;
    public GenerationsFullBlockSet(String name, Block.Properties properties, BlockSetType type) {
        super(name, properties);
        button = GenerationsBlocks.registerBlockItem(name + "_button", () -> new ButtonBlock(properties, type, 20, false));
        pressurePlate = GenerationsBlocks.registerBlockItem(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, properties, type));
        GenerationsBlockSet.getBlockSets().remove(this);
        fullBlockSets.add(this);
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

    public void updateBlockFamily() {
        this.setBlockFamily(new BlockFamily.Builder(getBaseBlock()).slab(getSlab()).stairs(getStairs()).wall(getWall()).button(getButton()).pressurePlate(getPressurePlate()).getFamily());
    }

    public static void updateBlockFamilies() {
        for (GenerationsFullBlockSet blockSet : fullBlockSets)
            blockSet.updateBlockFamily();
    }

    public static ArrayList<GenerationsFullBlockSet> getFullBlockSets() {
        return fullBlockSets;
    }
}
