package generations.gg.generations.core.generationscore.common.world.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.architectury.extensions.ItemExtension;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GenerationsArmorItem extends ArmorItem implements ItemExtension {
    public final Set<ArmorTickEffect> armorEffects = new HashSet<>();
    public final Set<CustomAttributeModifier> customAttributeModifiers = new HashSet<>();

    public GenerationsArmorItem(Holder<ArmorMaterial> armorMaterial, Type equipmentSlot, Properties properties, ArmorEffect... armorEffects) {
        super(armorMaterial, equipmentSlot, properties);

        for (ArmorEffect armorEffect : armorEffects) {
            if (armorEffect instanceof ArmorTickEffect effect) {
                this.armorEffects.add(effect);
            } else if (armorEffect instanceof CustomAttributeModifier effect) {
                this.customAttributeModifiers.add(effect);
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

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity entity, int slotId, boolean isSelected) {
        this.armorEffects.forEach(armorEffect -> armorEffect.inventoryTick(itemStack, world, entity, slotId, isSelected, this));
    }

    @Override
    public void tickArmor(ItemStack stack, Player player) {
        this.armorEffects.forEach(armorEffect -> armorEffect.onArmorTick(stack, player.level(), player, this));
    }
}
