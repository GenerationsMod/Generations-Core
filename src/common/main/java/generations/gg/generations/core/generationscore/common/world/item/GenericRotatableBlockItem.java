package generations.gg.generations.core.generationscore.common.world.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class GenericRotatableBlockItem extends BlockItem {
    public GenericRotatableBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean mustSurvive() {
        return false;
    }
}
