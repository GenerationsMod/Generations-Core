package generations.gg.generations.core.generationscore.world.item.berry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class BerryItem extends ItemNameBlockItem {
    private final BerryType berryType;

    public BerryItem(BerryType berryType, RegistrySupplier<Block> block, Properties properties) {
        super(block.get(), properties);
        this.berryType = berryType;
    }

    public BerryType getBerry() {
        return berryType;
    }
}
