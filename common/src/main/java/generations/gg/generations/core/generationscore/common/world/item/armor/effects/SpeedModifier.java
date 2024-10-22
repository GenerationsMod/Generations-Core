package generations.gg.generations.core.generationscore.common.world.item.armor.effects;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import generations.gg.generations.core.generationscore.common.world.item.armor.CustomAttributeModifier;
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class SpeedModifier implements CustomAttributeModifier {
    private final AttributeModifier elementalBootsModifier;
    private final AttributeModifier elementalBootsModifier2x;

    public SpeedModifier(float value) {
        this.elementalBootsModifier = new AttributeModifier(UUID.fromString("10ae6bcc-5b15-41b1-ba51-b6101e178401"), Attributes.MOVEMENT_SPEED.getDescriptionId(), value, AttributeModifier.Operation.MULTIPLY_BASE);
        this.elementalBootsModifier2x = new AttributeModifier(UUID.fromString("de4f0383-fcf9-4ba7-8ffc-0767c1ead7b9"), Attributes.MOVEMENT_SPEED.getDescriptionId(), value * 2.0f, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public void getAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, EquipmentSlot equipmentSlot, ItemStack itemStack, GenerationsArmorItem generationsArmorItem) {
        if(equipmentSlot == generationsArmorItem.getType().getSlot()) builder.put(Attributes.MOVEMENT_SPEED, isDoubled(itemStack) ? this.elementalBootsModifier2x : this.elementalBootsModifier);
    }

    private boolean isDoubled(ItemStack itemStack) {
        CompoundTag armorEffectTagElement = itemStack.getTagElement("ArmorEffect");
        if (armorEffectTagElement == null) return false;
        return armorEffectTagElement.getBoolean("DoubleSpeed");
    }
}
