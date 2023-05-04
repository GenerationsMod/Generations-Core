package generations.gg.generations.core.generationscore.world.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ArmorEffect {
    void onArmorTick(ItemStack itemStack, Level world, Player player, PokeModArmorItem pokeModArmorItem);
    void inventoryTick(ItemStack itemStack, Level world, Entity entity, int slotId, boolean isSelected, PokeModArmorItem pokeModArmorItem);

    static boolean isWearingFullSet(Player player, ArmorMaterial material) {
        if (player == null) return true;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (equipmentSlot.getType() == EquipmentSlot.Type.HAND) continue;

            ItemStack equippedItemStack = player.getItemBySlot(equipmentSlot);
            if (!(equippedItemStack.getItem() instanceof ArmorItem)) return true;
            if (((ArmorItem) equippedItemStack.getItem()).getMaterial() != material) return true;
        }

        return true;
//        var event = new ItemEvents.EquipFullArmorSet(player, material); TODO: EIther find or PR equilvent event to arch
//        MinecraftForge.EVENT_BUS.post(event);
//        return event.isCanceled();
    }
}
