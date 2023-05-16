package generations.gg.generations.core.generationscore.forge.compat;


import generations.gg.generations.core.generationscore.compat.VanillaCompat;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.CreativeModeTabEvent;

/**
 * @author JT122406
 * This class is used to have better Compatibility with Vanilla Minecraft
 */
public class VanillaCompatForge {

    /**
     * Used to add items to the creative menu in a manner that fits Vanilla's order.
     * @param event net.minecraftforge.event.CreativeModeTabEvent.BuildContents
     */
    public static void buildContents(final CreativeModeTabEvent.BuildContents event) {
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        CreativeModeTab tab = event.getTab();
        if (tab == CreativeModeTabs.COLORED_BLOCKS) {
            for (int i = 0; i <= 5; i++) {
                int finalI = i;
                GenerationsBlocks.POKEBRICKS.forEach(block -> addInOrder(event, finalI, block.get()));
            }

            for (int i = 0; i <= 5; i++) {
                int finalI = i;
                GenerationsBlocks.MARBLE.forEach(block -> addInOrder(event, finalI, block.get()));
            }

            for (int i = 0; i <= 5; i++) {
                int finalI = i;
                GenerationsBlocks.ULTRA_BLOCKS.forEach(block -> addInOrder(event, finalI, block.get()));
            }

        } else if (tab == CreativeModeTabs.COMBAT) {
            GenerationsArmor.ARMOR.forEach(item -> entries.putBefore(Items.TURTLE_HELMET.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
            GenerationsTools.TOOLS.forEach(item -> {
                if (item.get() instanceof AxeItem)
                    entries.putAfter(Items.NETHERITE_AXE.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                else if (item.get() instanceof SwordItem)
                    entries.putAfter(Items.NETHERITE_SWORD.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            });
        } else if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES){
            GenerationsTools.TOOLS.forEach(item -> {
                if ((!(item.get() instanceof SwordItem))  && item.get() != GenerationsTools.DIAMOND_HAMMER.get() && item.get() != GenerationsTools.GOLDEN_HAMMER.get() && item.get() != GenerationsTools.IRON_HAMMER.get() && item.get() != GenerationsTools.STONE_HAMMER.get() && item.get() != GenerationsTools.WOODEN_HAMMER.get())
                    entries.putBefore(Items.BUCKET.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);   
            });
            entries.putAfter(Items.WOODEN_HOE.getDefaultInstance(), GenerationsTools.WOODEN_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.STONE_HOE.getDefaultInstance(), GenerationsTools.STONE_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.IRON_HOE.getDefaultInstance(), GenerationsTools.IRON_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.GOLDEN_HOE.getDefaultInstance(), GenerationsTools.GOLDEN_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.DIAMOND_HOE.getDefaultInstance(), GenerationsTools.DIAMOND_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.NETHERITE_HOE.getDefaultInstance(), GenerationsTools.NETHERITE_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.MANGROVE_CHEST_BOAT.getDefaultInstance(), GenerationsItems.GHOST_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.GHOST_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.GHOST_CHEST_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.GHOST_CHEST_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.ULTRA_DARK_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.ULTRA_DARK_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM.get().getDefaultInstance(),GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.ULTRA_JUNGLE_CHEST_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } else if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            entries.putAfter(Items.WARPED_HANGING_SIGN.getDefaultInstance(), GenerationsItems.GHOST_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.GHOST_SIGN.get().getDefaultInstance(), GenerationsItems.GHOST_HANGING_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.GHOST_HANGING_SIGN.get().getDefaultInstance(), GenerationsItems.ULTRA_DARK_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.ULTRA_DARK_SIGN.get().getDefaultInstance(), GenerationsItems.ULTRA_DARK_HANGING_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.ULTRA_DARK_HANGING_SIGN.get().getDefaultInstance(), GenerationsItems.ULTRA_JUNGLE_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsItems.ULTRA_JUNGLE_SIGN.get().getDefaultInstance(), GenerationsItems.ULTRA_JUNGLE_HANGING_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } else if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            entries.putAfter(Items.DEEPSLATE_COAL_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_COAL_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.DEEPSLATE_DIAMOND_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.DEEPSLATE_EMERALD_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_EMERALD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.DEEPSLATE_IRON_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_IRON_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.DEEPSLATE_LAPIS_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.DEEPSLATE_GOLD_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_GOLD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.DEEPSLATE_COPPER_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_COPPER_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            GenerationsOres.ORES.forEach(b -> entries.putBefore(Items.NETHER_GOLD_ORE.getDefaultInstance(), b.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
            entries.putAfter(Items.RAW_GOLD_BLOCK.getDefaultInstance(), GenerationsBlocks.RAW_ALUMINUM_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.ACACIA_LOG.getDefaultInstance(), GenerationsWood.GHOST_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsWood.GHOST_LOG.get().asItem().getDefaultInstance(), GenerationsWood.ULTRA_DARK_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsWood.ULTRA_DARK_LOG.get().asItem().getDefaultInstance(), GenerationsWood.ULTRA_JUNGLE_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(Items.JACK_O_LANTERN.getDefaultInstance(), GenerationsBlocks.CURSED_PUMPKIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsBlocks.CURSED_PUMPKIN.get().asItem().getDefaultInstance(), GenerationsBlocks.CURSED_CARVED_PUMPKIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get().asItem().getDefaultInstance(), GenerationsBlocks.CURSED_JACK_O_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } else if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            GenerationsWood.WOOD_BLOCKS.forEach(woodBlock -> entries.putAfter(Items.STONE.getDefaultInstance(), woodBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
        }
    }

    private static void addInOrder(CreativeModeTabEvent.BuildContents event, int i, Block b) {
        if (b instanceof PressurePlateBlock){ if(i == 5)event.accept(b);}
        else if (b instanceof ButtonBlock){ if(i == 4)event.accept(b);}
        else if (b instanceof WallBlock){ if(i == 3)event.accept(b);}
        else if (b instanceof SlabBlock){ if(i == 2)event.accept(b);}
        else if (b instanceof StairBlock){ if(i == 1) event.accept(b);}
        else if (i == 0) {event.accept(b);}
    }
}
