package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.world.item.ItemStack;

public class TechnicalMachineItem extends MoveTeachingItem {
    private final String move;

    public TechnicalMachineItem(String move, Properties properties) {
        super(properties);
        this.move = move;
    }

    @Override
    protected String getMoveString(ItemStack itemStack) {
        return move;
    }
}
