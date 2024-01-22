package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.*;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.world.item.DyeColor;

import java.util.Map;

public class StreetLampBlock extends DyeableBlock<StreetLampBlockEntity, StreetLampBlock> {
    public StreetLampBlock(DyeColor color, Map<DyeColor, RegistrySupplier<DyeableBlock<StreetLampBlockEntity, StreetLampBlock>>> function, Properties properties) {
        super(color, function, GenerationsBlockEntities.STREET_LAMP, properties, GenerationsBlockEntityModels.STREET_LAMP, 0, 1, 0);
    }
}
