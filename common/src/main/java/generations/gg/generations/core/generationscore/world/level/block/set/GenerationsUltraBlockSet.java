package generations.gg.generations.core.generationscore.world.level.block.set;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.state.properties.GenerationsBlockSetTypes;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Supplier;

@ApiStatus.Internal
public class GenerationsUltraBlockSet extends GenerationsFullBlockSet {

    public static ArrayList<GenerationsUltraBlockSet> ultraBlockSets = new ArrayList<>();
    public GenerationsUltraBlockSet(String name, RegistrySupplier<Block> baseBlock) {
        super(name, GenerationsBlocks.ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, baseBlock);
        GenerationsFullBlockSet.getFullBlockSets().remove(this);
        GenerationsBlockSet.getBlockSets().remove(this);
        ultraBlockSets.add(this);
    }

    @Override
    protected <T extends Block> RegistrySupplier<T> registerBlockItem(String name, @NotNull Supplier<T> blockSupplier) {
        return GenerationsBlocks.registerUltraBlock(name, blockSupplier);
    }

    public static void updateUltraBlockFamilies() {
        for (GenerationsFullBlockSet blockSet : ultraBlockSets)
            blockSet.updateBlockFamily();
    }

}
