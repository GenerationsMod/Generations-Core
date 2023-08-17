package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class TechnicalMachineItem extends MoveTeachingItem {
    public TechnicalMachineItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isConsumed() {
        return false;
    }

    @Override
    public Component getName(net.minecraft.world.item.ItemStack stack) {
        var move = getMove(stack);
        return Component.literal("TM" + getNumber(stack) + " " + (move != null ? move.getDisplayName().toString() : "Blank"));
    }

    private Component getNumber(ItemStack itemStack) {
        var tag = itemStack.getTag();

        return Component.literal(formatWithZeros(tag != null && tag.contains("number") ? tag.getInt("number") : 0));
    }

    private String formatWithZeros(int number) {
        var formattedNumber = Integer.toString(number);
        var zerosNeeded = 3 - formattedNumber.length();

        return zerosNeeded > 0 ? "0".repeat(zerosNeeded) + formattedNumber : formattedNumber.substring(0, 3);
    }
}
