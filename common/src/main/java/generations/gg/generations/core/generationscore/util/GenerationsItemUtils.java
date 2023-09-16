package generations.gg.generations.core.generationscore.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class GenerationsItemUtils {
    @ExpectPlatform
    public static boolean canEquip(ItemStack carried, EquipmentSlot equipmentslottype, Entity entity) {
        throw new RuntimeException();
    }
}
