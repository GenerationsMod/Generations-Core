package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class CustomTechnicalMachineItem extends MoveTeachingItem {
    public CustomTechnicalMachineItem(Properties properties) {
        super(properties);
    }

    @Override
    protected String getMoveString(ItemStack itemStack) {
        var tag = itemStack.getTag();

        if (tag != null) {
            if(tag.contains("move")) {
                return tag.getString("move");
            }
        }

        return null;
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

    private String getNumber(ItemStack itemStack) {
        var tag = itemStack.getTag();

        return formatWithZeros(tag != null && tag.contains("number") ? tag.getInt("number") : 0);
    }

    private String formatWithZeros(int number) {
        var formattedNumber = Integer.toString(number);
        var zerosNeeded = 3 - formattedNumber.length();

        return zerosNeeded > 0 ? "0".repeat(zerosNeeded) + formattedNumber : formattedNumber.substring(0, 3);
    }

    public ItemStack createTm(int number, String move) {
        var stack = GenerationsItems.CUSTOM_TM.get().getDefaultInstance();
        var tag = stack.getOrCreateTag();
        tag.putInt("number", number);
        tag.putString("move", move);
        return stack;
    }
}
