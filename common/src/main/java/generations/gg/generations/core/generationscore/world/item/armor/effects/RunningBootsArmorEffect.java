package generations.gg.generations.core.generationscore.world.item.armor.effects;

import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.armor.ArmorEffect;
import generations.gg.generations.core.generationscore.world.item.armor.PokeModArmorItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public record RunningBootsArmorEffect() implements ArmorEffect {
    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player, PokeModArmorItem pokeModArmorItem) {
        if (world.isClientSide) return;
        if (player.isCreative()) return;
        if (player.isSpectator()) return;
        if (player.isPassenger()) return;
        if (!player.isOnGround()) return;
        if (player.isVisuallyCrawling()) return;
        CompoundTag compound = itemStack.getTagElement("pos");
        if (compound == null) {
            compound = itemStack.getOrCreateTagElement("pos");
            compound.putDouble("x", player.position().x);
            compound.putDouble("z", player.position().z);
        }
        if (Math.sqrt(Math.pow(compound.getDouble("x") - player.position().x, 2.0D) + Math.pow(compound.getDouble("z") - player.position().z, 2.0D)) >= 2.0D) {
            itemStack.hurtAndBreak(1, player, owner -> owner.setItemSlot(EquipmentSlot.FEET, new ItemStack(GenerationsArmor.OLD_RUNNING_BOOTS.get())));
            compound.putDouble("x", player.position().x);
            compound.putDouble("z", player.position().z);
        }
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int slotId, boolean isSelected, PokeModArmorItem pokeModArmorItem) {

    }
}
