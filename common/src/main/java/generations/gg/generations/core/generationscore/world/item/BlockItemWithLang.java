package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BlockItemWithLang extends BlockItem implements LangTooltip {
    public BlockItemWithLang(Block block, Properties properties) {
        super(block, properties);
    }
}
