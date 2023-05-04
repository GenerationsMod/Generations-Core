package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.world.item.Item;

public class BadgeItem extends Item {

    public BadgeItem(Properties properties/*ElementType type*/) { // FIXME: the TODO: is the element which is associated with the badge
        super(properties.stacksTo(1));
    }
}
