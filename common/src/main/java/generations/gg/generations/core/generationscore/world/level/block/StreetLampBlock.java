package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.*;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.world.item.DyeColor;

import java.util.function.Function;

public class StreetLampBlock extends DyeableBlock<StreetLampBlockEntity, StreetLampBlock> {
    public StreetLampBlock(Function<DyeColor, DyedBlockItem<StreetLampBlockEntity, StreetLampBlock>> function, Properties properties) {
        super(function, GenerationsBlockEntities.STREET_LAMP, properties, GenerationsBlockEntityModels.STREET_LAMP, 0, 1, 0);
    }
}
