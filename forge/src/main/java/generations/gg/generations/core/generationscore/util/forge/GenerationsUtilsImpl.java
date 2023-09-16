package generations.gg.generations.core.generationscore.util.forge;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class GenerationsUtilsImpl {
    public static CompoundTag serializeStack(ItemStack itemStack) {
        return itemStack.serializeNBT();
    }
}
