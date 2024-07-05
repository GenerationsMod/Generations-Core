package generations.gg.generations.core.generationscore.world.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.architectury.extensions.ItemExtension;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerationsArmorItem extends ArmorItem implements ItemExtension {
    public final Set<ArmorTickEffect> armorEffects = new HashSet<>();
    public final Set<CustomAttributeModifier> customAttributeModifiers = new HashSet<>();

    public GenerationsArmorItem(ArmorMaterial armorMaterial, Type equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    public GenerationsArmorItem addArmorEffects(ArmorEffect... armorEffects) {
        for (ArmorEffect armorEffect : armorEffects) {
            if (armorEffect instanceof ArmorTickEffect effect) {
                this.armorEffects.add(effect);
            } else if (armorEffect instanceof CustomAttributeModifier effect) {
                this.customAttributeModifiers.add(effect);
            }
        }
        return this;
    }

    //Method for Forge
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack itemStack) {
        return ImmutableMultimap.<Attribute, AttributeModifier>builder()
                .putAll(super.getDefaultAttributeModifiers(equipmentSlot))
                .putAll(customAttributeModifiers.stream().map(customAttributeModifier -> customAttributeModifier.getAttributeModifiers(equipmentSlot, itemStack, this).entries()).flatMap(Collection::stream).collect(Collectors.toList()))
                .build();
    }

    //Method for Fabric
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        return getAttributeModifiers(slot, stack);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity entity, int slotId, boolean isSelected) {
        this.armorEffects.forEach(armorEffect -> armorEffect.inventoryTick(itemStack, world, entity, slotId, isSelected, this));
    }

    @Override
    public void tickArmor(ItemStack stack, Player player) {
        this.armorEffects.forEach(armorEffect -> armorEffect.onArmorTick(stack, player.level(), player, this));
    }
}
