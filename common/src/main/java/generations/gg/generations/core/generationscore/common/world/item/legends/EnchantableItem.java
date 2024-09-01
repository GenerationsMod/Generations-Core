package generations.gg.generations.core.generationscore.common.world.item.legends;

import generations.gg.generations.core.generationscore.common.world.item.ItemWithLangTooltipImpl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class EnchantableItem extends ItemWithLangTooltipImpl {
    public EnchantableItem(Properties arg) {
        super(arg);
    }

    public int neededEnchantmentLevel(Player player) {
        return 100;
    }

    public static ItemStack getEnchanted(Item item) {
        ItemStack stack = new ItemStack(item);
        stack.getOrCreateTag().putBoolean("enchanted", true);
        return stack;
    }

    public static ItemStack setEnchanted(ItemStack stack, boolean enchanted) {
        stack.getOrCreateTag().putBoolean("enchanted", enchanted);
        return stack;
    }

    public static ItemStack setUsed(ItemStack stack, boolean used) {
        stack.getOrCreateTag().putBoolean("used", used);
        return stack;
    }

    public static boolean isUsed(ItemStack stack) {
        return stack != null && !stack.isEmpty() && stack.getOrCreateTag().getBoolean("used");
    }

    public static boolean isEnchanted(ItemStack stack) {
        return stack != null && !stack.isEmpty() && stack.getOrCreateTag().getBoolean("enchanted");
    }

    public boolean isFoil(@NotNull ItemStack stack) {
        return isEnchanted(stack);
    }
}
