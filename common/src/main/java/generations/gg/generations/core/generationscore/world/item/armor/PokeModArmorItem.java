package com.pokemod.pokemod.world.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PokeModArmorItem extends ArmorItem {
    public final Set<ArmorEffect> armorEffects = new HashSet<>();
    public final Set<CustomAttributeModifier> customAttributeModifiers = new HashSet<>();

    public PokeModArmorItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
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

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack itemStack) {
        return ImmutableMultimap.<Attribute, AttributeModifier>builder()
                .putAll(super.getAttributeModifiers(equipmentSlot, itemStack))
                .putAll(customAttributeModifiers.stream().map(customAttributeModifier -> customAttributeModifier.getAttributeModifiers(equipmentSlot, itemStack, this).entries()).flatMap(Collection::stream).collect(Collectors.toList()))
                .build();
    }

    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player) {
        this.armorEffects.forEach(armorEffect -> armorEffect.onArmorTick(itemStack, world, player, this));
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity entity, int slotId, boolean isSelected) {
        this.armorEffects.forEach(armorEffect -> armorEffect.inventoryTick(itemStack, world, entity, slotId, isSelected, this));
    }
}
