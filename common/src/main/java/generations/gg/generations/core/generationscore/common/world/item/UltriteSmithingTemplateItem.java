package generations.gg.generations.core.generationscore.common.world.item;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class UltriteSmithingTemplateItem extends SmithingTemplateItem {
    private static final Component ULTRITE_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("ultrite_upgrade"))).withStyle(ChatFormatting.GRAY);
    private static final Component ULTRITE_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.ultrite_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
    private static final Component ULTRITE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.ultrite_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
    private static final Component ULTRITE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.ultrite_upgrade.base_slot_description")));
    private static final Component ULTRITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.ultrite_upgrade.additions_slot_description")));

    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");

    public UltriteSmithingTemplateItem() {
        super(ULTRITE_UPGRADE_APPLIES_TO, ULTRITE_UPGRADE_INGREDIENTS, ULTRITE_UPGRADE, ULTRITE_UPGRADE_BASE_SLOT_DESCRIPTION, ULTRITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createULTRITEUpgradeIconList(), createULTRITEUpgradeMaterialList());
    }

    private static List<ResourceLocation> createULTRITEUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createULTRITEUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
