package generations.gg.generations.core.generationscore.common.world.item.armor.effects

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorTickEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.level.Level


//TODO: Figure out how to get this working again.
class SpeedModifier(val value: Double, val valueFullSet: Double = value) : ArmorTickEffect {
    val baseModifier = GenerationsCore.id("armor_speed_boost");

    fun ItemAttributeModifiers.replaceModifierValue(fullSet: Boolean): ItemAttributeModifiers {
        val attribute = Attributes.MOVEMENT_SPEED

        val updated = this.modifiers()
            .filter { entry: ItemAttributeModifiers.Entry -> !(entry.matches(attribute, baseModifier) && entry.slot == EquipmentSlotGroup.LEGS) }
            .toMutableList()

        val replacement = AttributeModifier(baseModifier, if(fullSet) valueFullSet else value, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)

        updated.add(ItemAttributeModifiers.Entry(attribute, replacement, EquipmentSlotGroup.LEGS))

        return ItemAttributeModifiers(updated, this.showInTooltip())
    }

    override fun inventoryTick(
        itemStack: ItemStack,
        world: Level,
        entity: LivingEntity,
        slotId: Int,
        isSelected: Boolean,
        generationsArmorItem: GenerationsArmorItem,
    ) {
        if(world.isClientSide()) return
        itemStack.update(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY, { it.replaceModifierValue(ArmorTickEffect.isWearingFullSet(entity, generationsArmorItem.material))})?: return
    }

//    override fun getAttributeModifiers(
//        builder: ImmutableMultimap.Builder<Attribute?, AttributeModifier>,
//        equipmentSlot: EquipmentSlot,
//        itemStack: ItemStack,
//        generationsArmorItem: GenerationsArmorItem?,
//    ) {


//        if(equipmentSlot == EquipmentSlot.LEGS && generationsArmorItem?.type?.slot == equipmentSlot) builder.put(Attributes.MOVEMENT_SPEED, itemStack ? this.elementalBootsModifier2x : this.elementalBootsModifier);
//    }

    //    private boolean isDoubled(ItemStack itemStack) {
    //        CompoundTag armorEffectTagElement = itemStack.getTagElement("ArmorEffect");
    //        if (armorEffectTagElement == null) return false;
    //        return armorEffectTagElement.getBoolean("DoubleSpeed");
    //
}
