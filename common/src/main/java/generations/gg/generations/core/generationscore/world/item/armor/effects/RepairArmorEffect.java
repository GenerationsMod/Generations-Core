package generations.gg.generations.core.generationscore.world.item.armor.effects;

import generations.gg.generations.core.generationscore.world.item.armor.ArmorEffect;
import generations.gg.generations.core.generationscore.world.item.armor.PokeModArmorItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public record RepairArmorEffect() implements ArmorEffect {
    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player, PokeModArmorItem pokeModArmorItem) {
        if (world.isClientSide) return;
        if (!itemStack.isDamaged()) return;
        itemStack.setDamageValue(0);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int slotId, boolean isSelected, PokeModArmorItem pokeModArmorItem) {

    }
}
