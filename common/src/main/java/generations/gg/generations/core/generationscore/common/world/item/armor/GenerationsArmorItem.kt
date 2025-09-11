package generations.gg.generations.core.generationscore.common.world.item.armor

import com.google.common.base.Supplier
import com.google.common.base.Suppliers
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.level.Level
import java.util.function.Consumer

class GenerationsArmorItem(
    armorMaterial: Holder<ArmorMaterial>,
    equipmentSlot: Type,
    properties: Properties,
    additionalModfier: List<Pair<Holder<Attribute>, AttributeModifier>>,
    vararg armorEffects: ArmorEffect?,
) :
    ArmorItem(armorMaterial, equipmentSlot, properties) {
    private var attributeModifiers: Supplier<ItemAttributeModifiers> = Suppliers.memoize<ItemAttributeModifiers>(Supplier {
        val i = (material.value() as ArmorMaterial).getDefense(type)
        val f = (material.value() as ArmorMaterial).toughness()
        val builder = ItemAttributeModifiers.builder()
        val equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot())
        val resourceLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName())
        builder.add(
            Attributes.ARMOR,
            AttributeModifier(resourceLocation, i.toDouble(), AttributeModifier.Operation.ADD_VALUE),
            equipmentSlotGroup
        )
        builder.add(
            Attributes.ARMOR_TOUGHNESS,
            AttributeModifier(resourceLocation, f.toDouble(), AttributeModifier.Operation.ADD_VALUE),
            equipmentSlotGroup
        )
        val g = (material.value() as ArmorMaterial).knockbackResistance()
        if (g > 0.0f) {
            builder.add(
                Attributes.KNOCKBACK_RESISTANCE,
                AttributeModifier(resourceLocation, g.toDouble(), AttributeModifier.Operation.ADD_VALUE),
                equipmentSlotGroup
            )
        }

        additionalModfier.forEach {

            builder.add(it.first, it.second, EquipmentSlotGroup.bySlot(type.slot))}

        builder.build()
    })

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

    override fun getDefaultAttributeModifiers(): ItemAttributeModifiers? {
        return attributeModifiers.get();
    }
}
