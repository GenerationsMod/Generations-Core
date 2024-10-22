package generations.gg.generations.core.generationscore.common.world.item.armor;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public interface CustomAttributeModifier extends ArmorEffect {

    void getAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, EquipmentSlot equipmentSlot, ItemStack itemStack, GenerationsArmorItem generationsArmorItem);
}
