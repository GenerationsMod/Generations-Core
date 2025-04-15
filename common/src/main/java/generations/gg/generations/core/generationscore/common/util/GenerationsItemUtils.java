package generations.gg.generations.core.generationscore.common.util;

import generations.gg.generations.core.generationscore.common.world.item.GenericRotatableBlockItem;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class GenerationsItemUtils {

    public static <T extends Block> BlockItem generateBlockItem(T t, Item.Properties properties) {
        if(t instanceof GenericRotatableModelBlock<?> block) {
            return new GenericRotatableBlockItem(block, properties);
        } else {
            return new BlockItem(t, properties);
        }
    }
}
