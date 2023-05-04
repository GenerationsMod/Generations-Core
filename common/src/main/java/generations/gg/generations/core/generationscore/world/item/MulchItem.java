package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.world.level.block.SoftSoilBlock.Mulch;
import net.minecraft.world.item.Item;

public class MulchItem extends Item {
    private final Mulch mulch;

    public MulchItem(Mulch mulch, Properties properties) {
        super(properties);
        this.mulch = mulch;
    }

    public Mulch getMulch() {
        return mulch;
    }
}
