package generations.gg.generations.core.generationscore.util.fabric;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;

public class GenerationsItemUtilsImpl {
    public static boolean canEquip(ItemStack carried, EquipmentSlot equipmentslottype, Entity entity) {
        return Mob.getEquipmentSlotForItem(carried) == equipmentslottype;
    }
}
