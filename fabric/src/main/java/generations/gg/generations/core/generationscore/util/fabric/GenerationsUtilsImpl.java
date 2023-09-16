package generations.gg.generations.core.generationscore.util.fabric;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class GenerationsUtilsImpl {
    public static CompoundTag serializeStack(ItemStack itemStack) {
        return itemStack.save(new CompoundTag());
    }
}
