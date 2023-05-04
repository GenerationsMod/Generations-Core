package generations.gg.generations.core.generationscore.world.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class PokeModArmorItem extends ArmorItem {
    public final Set<ArmorEffect> armorEffects = new HashSet<>();
    public final Set<CustomAttributeModifier> customAttributeModifiers = new HashSet<>();

    public PokeModArmorItem(ArmorMaterial armorMaterial, Type equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    public PokeModArmorItem addArmorEffect(ArmorEffect armorEffect) {
        this.armorEffects.add(armorEffect);
        return this;
    }

    public PokeModArmorItem addCustomAttributeModifier(CustomAttributeModifier customAttributeModifier) {
        this.customAttributeModifiers.add(customAttributeModifier);
        return this;
    }

//    @Override TODO: Platform netural version.
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack itemStack) {
//        return ImmutableMultimap.<Attribute, AttributeModifier>builder()
//                .putAll(super.getAttributeModifiers(equipmentSlot, itemStack))
//                .putAll(customAttributeModifiers.stream().map(customAttributeModifier -> customAttributeModifier.getAttributeModifiers(equipmentSlot, itemStack, this).entries()).flatMap(Collection::stream).collect(Collectors.toList()))
//                .build();
//    }
//
//    @Override
//    public void onArmorTick(ItemStack itemStack, Level world, Player player) {
//        this.armorEffects.forEach(armorEffect -> armorEffect.onArmorTick(itemStack, world, player, this));
//    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity entity, int slotId, boolean isSelected) {
        this.armorEffects.forEach(armorEffect -> armorEffect.inventoryTick(itemStack, world, entity, slotId, isSelected, this));
    }
}
