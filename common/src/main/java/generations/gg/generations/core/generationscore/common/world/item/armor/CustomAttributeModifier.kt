package generations.gg.generations.core.generationscore.common.world.item.armor

import com.google.common.collect.ImmutableMultimap
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.ItemStack

interface CustomAttributeModifier : ArmorEffect {
    fun getAttributeModifiers(
        builder: ImmutableMultimap.Builder<Attribute?, AttributeModifier>,
        equipmentSlot: EquipmentSlot,
        itemStack: ItemStack,
        generationsArmorItem: GenerationsArmorItem?
    )
}
