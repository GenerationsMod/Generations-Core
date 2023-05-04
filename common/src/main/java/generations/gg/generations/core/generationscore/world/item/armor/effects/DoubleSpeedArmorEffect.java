package generations.gg.generations.core.generationscore.world.item.armor.effects;

import generations.gg.generations.core.generationscore.world.item.armor.ArmorEffect;
import generations.gg.generations.core.generationscore.world.item.armor.PokeModArmorItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public record DoubleSpeedArmorEffect() implements ArmorEffect {
    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player, PokeModArmorItem pokeModArmorItem) {
        if (world.isClientSide) return;
        if (ArmorEffect.isWearingFullSet(player, pokeModArmorItem.getMaterial())) {
            CompoundTag armorEffectTagElement = itemStack.getTagElement("ArmorEffect");
            if (armorEffectTagElement == null) return;
            armorEffectTagElement.remove("DoubleSpeed");
            if (!armorEffectTagElement.isEmpty()) return;
            itemStack.removeTagKey("ArmorEffect");
            return;
        }
        CompoundTag armorEffectTagElement = itemStack.getOrCreateTagElement("ArmorEffect");
        if (armorEffectTagElement.getBoolean("DoubleSpeed")) return;
        armorEffectTagElement.putBoolean("DoubleSpeed", true);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int slotId, boolean isSelected, PokeModArmorItem pokeModArmorItem) {
        if (world.isClientSide) return;
        for (ItemStack armorSlot : entity.getArmorSlots()) {
            if (itemStack == armorSlot) return;
        }
        CompoundTag armorEffectTagElement = itemStack.getTagElement("ArmorEffect");
        if (armorEffectTagElement == null) return;
        armorEffectTagElement.remove("DoubleSpeed");
        if (!armorEffectTagElement.isEmpty()) return;
        itemStack.removeTagKey("ArmorEffect");
    }
}
