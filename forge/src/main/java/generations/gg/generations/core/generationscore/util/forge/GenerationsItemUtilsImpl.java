package generations.gg.generations.core.generationscore.util.forge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class GenerationsItemUtilsImpl {
    public static boolean canEquip(ItemStack carried, EquipmentSlot equipmentslottype, Entity entity) {
        return carried.canEquip(equipmentslottype, entity);
    }
}
