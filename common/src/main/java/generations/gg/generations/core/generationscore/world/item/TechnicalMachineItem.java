package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.network.chat.Component;
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

    @Override
    public Component getName(net.minecraft.world.item.ItemStack stack) {
        var move = getMove(stack);
        return Component.keybind("").append(super.getName(stack)).append(Component.literal(" - ")).append((move != null ? move.getDisplayName() : Component.literal("Blank")));
    }
}
