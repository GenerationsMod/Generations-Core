package generations.gg.generations.core.generationscore.world.item.armor.effects;

import generations.gg.generations.core.generationscore.world.item.armor.ArmorEffect;
import generations.gg.generations.core.generationscore.world.item.armor.GenerationsArmorItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Map;

public record EnchantmentArmorEffect(Enchantment enchantment, int level) implements ArmorEffect {
    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player, GenerationsArmorItem generationsArmorItem) {
        if (world.isClientSide) return;
        if (ArmorEffect.isWearingFullSet(player, generationsArmorItem.getMaterial())) {
            itemStack.removeTagKey("Enchantments");
            return;
        }
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
        if (enchantments.getOrDefault(enchantment, 0) >= level) return;
        enchantments.put(enchantment, level);
        EnchantmentHelper.setEnchantments(enchantments, itemStack);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int slotId, boolean isSelected, GenerationsArmorItem generationsArmorItem) {
        if (world.isClientSide) return;
        for (ItemStack armorSlot : entity.getArmorSlots()) {
            if (itemStack == armorSlot) return;
        }
        itemStack.removeTagKey("Enchantments");
    }
}
