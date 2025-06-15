package generations.gg.generations.core.generationscore.common.world.item.armor

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import net.minecraft.core.Holder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import java.util.function.Consumer

class GenerationsArmorItem(
    armorMaterial: Holder<ArmorMaterial?>,
    equipmentSlot: Type,
    properties: Properties,
    vararg armorEffects: ArmorEffect?
) :
    ArmorItem(armorMaterial, equipmentSlot, properties) {
    val armorEffects: MutableSet<ArmorTickEffect> = HashSet()
    val customAttributeModifiers: MutableSet<CustomAttributeModifier> = HashSet()

    init {
        for (armorEffect in armorEffects) {
            if (armorEffect is ArmorTickEffect) {
                this.armorEffects.add(armorEffect)
            } else if (armorEffect is CustomAttributeModifier) {
                customAttributeModifiers.add(armorEffect)
            }
        }
    }


    //    TODO: Figure out wtf I'm gonna do with mess being upended.
    //    @Override
    //    public ItemAttributeModifiers getDefaultAttributeModifiers() {
    //        var entries = customAttributeModifiers.stream().map()
    //
    //        return super.getDefaultAttributeModifiers();
    //    }
    //
    //    //Method for Forge
    //    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack itemStack) {
    //        var builder = ImmutableMultimap.<Attribute, AttributeModifier>builder();
    //        for (CustomAttributeModifier customAttributeModifier : customAttributeModifiers) {
    //            customAttributeModifier.getAttributeModifiers(builder, equipmentSlot, itemStack, this);
    //        }
    //
    //        builder.putAll(this.getDefaultAttributeModifiers(equipmentSlot));
    //
    //        return builder.build();
    //    }
    //
    //    //Method for Fabric
    //    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
    //        return getAttributeModifiers(slot, stack);
    //    }
    //
    //    @Override
    //    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
    //        return super.getDefaultAttributeModifiers(slot);
    //    }
    override fun inventoryTick(itemStack: ItemStack, world: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        val livingEntity = entity.instanceOrNull<LivingEntity>() ?: return

        armorEffects.forEach(Consumer { armorEffect: ArmorTickEffect ->
            armorEffect.inventoryTick(
                itemStack, world, livingEntity, slotId, isSelected,
                this
            )
        })
    }
}
