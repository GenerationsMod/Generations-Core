package generations.gg.generations.core.generationscore.common.world.item.armor.effects

import com.google.common.collect.ImmutableMultimap
import generations.gg.generations.core.generationscore.common.world.item.armor.CustomAttributeModifier
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.ItemStack

//TODO: Figure out how to get this working again.
class SpeedModifier(value: Float) : CustomAttributeModifier {
    //    private final AttributeModifier elementalBootsModifier;
    //    private final AttributeModifier elementalBootsModifier2x;

    override fun getAttributeModifiers(
        builder: ImmutableMultimap.Builder<Attribute?, AttributeModifier>,
        equipmentSlot: EquipmentSlot,
        itemStack: ItemStack,
        generationsArmorItem: GenerationsArmorItem?
    ) {
        //        if(equipmentSlot == generationsArmorItem.getType().getSlot()) builder.put(Attributes.MOVEMENT_SPEED, isDoubled(itemStack) ? this.elementalBootsModifier2x : this.elementalBootsModifier);
    }

    //    private boolean isDoubled(ItemStack itemStack) {
    //        CompoundTag armorEffectTagElement = itemStack.getTagElement("ArmorEffect");
    //        if (armorEffectTagElement == null) return false;
    //        return armorEffectTagElement.getBoolean("DoubleSpeed");
    //
}
