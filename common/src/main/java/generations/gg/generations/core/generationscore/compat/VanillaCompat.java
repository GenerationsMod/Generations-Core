package generations.gg.generations.core.generationscore.compat;

import com.google.common.collect.ImmutableMap;
import dev.architectury.registry.CreativeTabOutput;
import dev.architectury.registry.CreativeTabRegistry;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class VanillaCompat {

	/**
	 * Registers blocks as flammable or strippable on setup
	 */
	public static void setup() {
		GenerationsWood.WOOD_BLOCKS.forEach(wood -> registerFlammable(wood.get(), 5, 5));

		registerStrippable(GenerationsWood.ULTRA_DARK_LOG.get(), GenerationsWood.STRIPPED_ULTRA_DARK_LOG.get());
		registerStrippable(GenerationsWood.ULTRA_DARK_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_DARK_WOOD.get());
		registerStrippable(GenerationsWood.ULTRA_JUNGLE_LOG.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG.get());
		registerStrippable(GenerationsWood.ULTRA_JUNGLE_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD.get());
		registerStrippable(GenerationsWood.GHOST_LOG.get(), GenerationsWood.STRIPPED_GHOST_LOG.get());
		registerStrippable(GenerationsWood.GHOST_WOOD.get(), GenerationsWood.STRIPPED_GHOST_WOOD.get());

		modifyTabs();
	}

	/**
	 * Register a block as Strippable.
	 * @param log The log block of wood
	 * @param stripped The stripped log block of wood
	 */
	private static void registerStrippable(@NotNull Block log, @NotNull Block stripped) {
		if (!log.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS))
			throw new IllegalArgumentException("Input block is missing required 'AXIS' property!");
		if (!stripped.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS))
			throw new IllegalArgumentException("Result block is missing required 'AXIS' property!");
		if (AxeItem.STRIPPABLES instanceof ImmutableMap)
			AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);

		AxeItem.STRIPPABLES.put(log, stripped);
	}

	/**
	 * Register a block as flammable.
	 * @param blockIn The block to register
	 * @param encouragement How much more likely the block is to catch fire compared to vanilla
	 * @param flammability How much more likely the block is to spread fire compared to vanilla
	 */
	private static void registerFlammable(Block blockIn, int encouragement, int flammability) {
		((FireBlock) Blocks.FIRE).setFlammable(blockIn, encouragement, flammability);
	}


	private static void modifyTabs() {
		var tabs = GenerationsCore.CONFIG.addItemsToVanillaTabs;

		for (CreativeModeTab tab : CreativeModeTabs.tabs()){
			switch (tab.getDisplayName().getString().toLowerCase()) {
				case "colored_blocks" -> {
					if(tabs.coloredBlocks)
						CreativeTabRegistry.modifyBuiltin(tab, (flags, output, permissions) -> {
							/*
							for (int i = 0; i <= 5; i++) {
								int finalI = i;
								GenerationsBlocks.POKEBRICKS.forEach(block -> addInOrder(output, finalI, block.get()));
							}

							for (int i = 0; i <= 5; i++) {
								int finalI = i;
								GenerationsBlocks.MARBLE.forEach(block -> addInOrder(output, finalI, block.get()));
							}

							 */

							for (int i = 0; i <= 5; i++) {
								int finalI = i;
								GenerationsBlocks.ULTRA_BLOCKS.forEach(block -> addInOrder(output, finalI, block.get()));
							}
						});
				}
				case "combat" -> {
					if(tabs.combat)
						CreativeTabRegistry.modifyBuiltin(tab, (flags, output, permissions) -> {
							GenerationsArmor.ARMOR.forEach(item -> output.acceptBefore(Items.TURTLE_HELMET.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
							GenerationsTools.TOOLS.forEach(item -> {
								if (item.get() instanceof AxeItem) output.acceptAfter(Items.NETHERITE_AXE.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
								else if (item.get() instanceof SwordItem) output.acceptAfter(Items.NETHERITE_SWORD.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							});
						});
				}
				case "tools_and_utilities" -> {
					if(tabs.toolsAndUtilities)
						CreativeTabRegistry.modifyBuiltin(tab, (flags, output, permissions) -> {
							GenerationsTools.TOOLS.forEach(item -> {
								if ((!(item.get() instanceof SwordItem)) && item.get() != GenerationsTools.DIAMOND_HAMMER.get() && item.get() != GenerationsTools.GOLDEN_HAMMER.get() && item.get() != GenerationsTools.IRON_HAMMER.get() && item.get() != GenerationsTools.STONE_HAMMER.get() && item.get() != GenerationsTools.WOODEN_HAMMER.get())
									output.acceptBefore(Items.BUCKET.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							});
							output.acceptAfter(Items.WOODEN_HOE.getDefaultInstance(), GenerationsTools.WOODEN_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.STONE_HOE.getDefaultInstance(), GenerationsTools.STONE_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.IRON_HOE.getDefaultInstance(), GenerationsTools.IRON_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.GOLDEN_HOE.getDefaultInstance(), GenerationsTools.GOLDEN_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.DIAMOND_HOE.getDefaultInstance(), GenerationsTools.DIAMOND_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.NETHERITE_HOE.getDefaultInstance(), GenerationsTools.NETHERITE_HAMMER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.MANGROVE_CHEST_BOAT.getDefaultInstance(), GenerationsItems.GHOST_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.GHOST_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.GHOST_CHEST_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.GHOST_CHEST_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.ULTRA_DARK_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.ULTRA_DARK_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.get().getDefaultInstance(), GenerationsItems.ULTRA_JUNGLE_CHEST_BOAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
						});
				}
				case "functional_blocks" -> {
					if(tabs.functionalBlocks)
						CreativeTabRegistry.modifyBuiltin(tab, (flags, output, permissions) -> {
							output.acceptAfter(Items.WARPED_HANGING_SIGN.getDefaultInstance(), GenerationsItems.GHOST_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.GHOST_SIGN.get().getDefaultInstance(), GenerationsItems.GHOST_HANGING_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.GHOST_HANGING_SIGN.get().getDefaultInstance(), GenerationsItems.ULTRA_DARK_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.ULTRA_DARK_SIGN.get().getDefaultInstance(), GenerationsItems.ULTRA_DARK_HANGING_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.ULTRA_DARK_HANGING_SIGN.get().getDefaultInstance(), GenerationsItems.ULTRA_JUNGLE_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsItems.ULTRA_JUNGLE_SIGN.get().getDefaultInstance(), GenerationsItems.ULTRA_JUNGLE_HANGING_SIGN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.FURNACE.getDefaultInstance(), GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get().asItem().getDefaultInstance(), GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.SMOKER.getDefaultInstance(), GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.get().asItem().getDefaultInstance(), GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.BLAST_FURNACE.getDefaultInstance(), GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.get().asItem().getDefaultInstance(), GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
						});
				}
				case "natural_blocks" -> {
					if(tabs.naturalBlocks)
						CreativeTabRegistry.modifyBuiltin(tab, (flags, output, permissions) -> {
							/*
							output.acceptAfter(Items.DEEPSLATE_COAL_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_COAL_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.DEEPSLATE_DIAMOND_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.DEEPSLATE_EMERALD_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_EMERALD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.DEEPSLATE_IRON_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_IRON_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.DEEPSLATE_LAPIS_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.DEEPSLATE_GOLD_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_GOLD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.DEEPSLATE_COPPER_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_COPPER_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							 */
							GenerationsOres.ORES.forEach(b -> output.acceptBefore(Items.NETHER_GOLD_ORE.getDefaultInstance(), b.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
							output.acceptAfter(Items.ACACIA_LOG.getDefaultInstance(), GenerationsWood.GHOST_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsWood.GHOST_LOG.get().asItem().getDefaultInstance(), GenerationsWood.ULTRA_DARK_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsWood.ULTRA_DARK_LOG.get().asItem().getDefaultInstance(), GenerationsWood.ULTRA_JUNGLE_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(Items.JACK_O_LANTERN.getDefaultInstance(), GenerationsBlocks.CURSED_PUMPKIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsBlocks.CURSED_PUMPKIN.get().asItem().getDefaultInstance(), GenerationsBlocks.CURSED_CARVED_PUMPKIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
							output.acceptAfter(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get().asItem().getDefaultInstance(), GenerationsBlocks.CURSED_JACK_O_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
						});
				}
				case "building_blocks" -> {
					if(tabs.buildingBlocks)
						CreativeTabRegistry.modifyBuiltin(tab, (flags, output, permissions) -> GenerationsWood.WOOD_BLOCKS.forEach(woodBlock -> output.acceptAfter(Items.STONE.getDefaultInstance(), woodBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)));
				}

			}
		}

	}


	private static void addInOrder(CreativeTabOutput event, int i, Block b) {
		if (b instanceof PressurePlateBlock){ if(i == 5)event.accept(b);}
		else if (b instanceof ButtonBlock){ if(i == 4)event.accept(b);}
		else if (b instanceof WallBlock){ if(i == 3)event.accept(b);}
		else if (b instanceof SlabBlock){ if(i == 2)event.accept(b);}
		else if (b instanceof StairBlock){ if(i == 1) event.accept(b);}
		else if (i == 0) {event.accept(b);}
	}
}
