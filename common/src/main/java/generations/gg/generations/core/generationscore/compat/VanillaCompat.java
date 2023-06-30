package generations.gg.generations.core.generationscore.compat;

import dev.architectury.hooks.item.tool.AxeItemHooks;
import dev.architectury.registry.CreativeTabOutput;
import dev.architectury.registry.CreativeTabRegistry;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.item.TechnicalMachineItem;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

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
	private static void registerStrippable(Block log, Block stripped) {
		AxeItemHooks.addStrippable(log, stripped);
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

		if(tabs.coloredBlocks)
			CreativeTabRegistry.modify(CreativeModeTabs.COLORED_BLOCKS, (flags, output, permissions) -> {
				for (int i = 0; i <= 5; i++) {
					int finalI = i;
					GenerationsBlocks.POKEBRICKS.forEach(block -> addInOrder(output, finalI, block.get()));
				}

				for (int i = 0; i <= 5; i++) {
					int finalI = i;
					GenerationsBlocks.MARBLE.forEach(block -> addInOrder(output, finalI, block.get()));
				}

				for (int i = 0; i <= 5; i++) {
					int finalI = i;
					GenerationsBlocks.ULTRA_BLOCKS.forEach(block -> addInOrder(output, finalI, block.get()));
				}
			});

		if(tabs.combat)
			CreativeTabRegistry.modify(CreativeModeTabs.COMBAT, (flags, output, permissions) -> {
				GenerationsArmor.ARMOR.forEach(item -> output.acceptBefore(Items.TURTLE_HELMET.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
				GenerationsTools.TOOLS.forEach(item -> {
					if (item.get() instanceof AxeItem) output.acceptAfter(Items.NETHERITE_AXE.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
					else if (item.get() instanceof SwordItem) output.acceptAfter(Items.NETHERITE_SWORD.getDefaultInstance(), item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				});
			});

		if(tabs.toolsAndUtilities)
			CreativeTabRegistry.modify(CreativeModeTabs.TOOLS_AND_UTILITIES, (flags, output, permissions) -> {
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

		if(tabs.functionalBlocks)
			CreativeTabRegistry.modify(CreativeModeTabs.FUNCTIONAL_BLOCKS, (flags, output, permissions) -> {
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

		if(tabs.naturalBlocks)
			CreativeTabRegistry.modify(CreativeModeTabs.NATURAL_BLOCKS, (flags, output, permissions) -> {
				output.acceptAfter(Items.DEEPSLATE_COAL_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_COAL_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(Items.DEEPSLATE_DIAMOND_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(Items.DEEPSLATE_EMERALD_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_EMERALD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(Items.DEEPSLATE_IRON_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_IRON_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(Items.DEEPSLATE_LAPIS_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(Items.DEEPSLATE_GOLD_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_GOLD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(Items.DEEPSLATE_COPPER_ORE.getDefaultInstance(), GenerationsOres.CHARGE_STONE_COPPER_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				GenerationsOres.ORES.forEach(b -> output.acceptBefore(Items.NETHER_GOLD_ORE.getDefaultInstance(), b.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
				output.acceptAfter(Items.RAW_GOLD_BLOCK.getDefaultInstance(), GenerationsBlocks.RAW_ALUMINUM_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(Items.ACACIA_LOG.getDefaultInstance(), GenerationsWood.GHOST_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(GenerationsWood.GHOST_LOG.get().asItem().getDefaultInstance(), GenerationsWood.ULTRA_DARK_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(GenerationsWood.ULTRA_DARK_LOG.get().asItem().getDefaultInstance(), GenerationsWood.ULTRA_JUNGLE_LOG.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(Items.JACK_O_LANTERN.getDefaultInstance(), GenerationsBlocks.CURSED_PUMPKIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(GenerationsBlocks.CURSED_PUMPKIN.get().asItem().getDefaultInstance(), GenerationsBlocks.CURSED_CARVED_PUMPKIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				output.acceptAfter(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get().asItem().getDefaultInstance(), GenerationsBlocks.CURSED_JACK_O_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			});

		if(tabs.buildingBlocks)
			CreativeTabRegistry.modify(CreativeModeTabs.BUILDING_BLOCKS, (flags, output, permissions) -> GenerationsWood.WOOD_BLOCKS.forEach(woodBlock -> output.acceptAfter(Items.STONE.getDefaultInstance(), woodBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)));

		CreativeTabRegistry.modify(GenerationsCreativeTabs.TMS, ((flags, output, canUseGameMasterBlocks) -> {
			output.accept(TechnicalMachineItem.Companion.createTm(1, "takedown"));
			output.accept(TechnicalMachineItem.Companion.createTm(2, "charm"));
			output.accept(TechnicalMachineItem.Companion.createTm(3, "faketears"));
			output.accept(TechnicalMachineItem.Companion.createTm(4, "agility"));
			output.accept(TechnicalMachineItem.Companion.createTm(5, "mudslap"));
			output.accept(TechnicalMachineItem.Companion.createTm(6, "scaryface"));
			output.accept(TechnicalMachineItem.Companion.createTm(7, "protect"));
			output.accept(TechnicalMachineItem.Companion.createTm(8, "firefang"));
			output.accept(TechnicalMachineItem.Companion.createTm(9, "thunderfang"));
			output.accept(TechnicalMachineItem.Companion.createTm(10, "icefang"));
			output.accept(TechnicalMachineItem.Companion.createTm(11, "waterpulse"));
			output.accept(TechnicalMachineItem.Companion.createTm(12, "lowkick"));
			output.accept(TechnicalMachineItem.Companion.createTm(13, "acidspray"));
			output.accept(TechnicalMachineItem.Companion.createTm(14, "acrobatics"));
			output.accept(TechnicalMachineItem.Companion.createTm(15, "strugglebug"));
			output.accept(TechnicalMachineItem.Companion.createTm(16, "psybeam"));
			output.accept(TechnicalMachineItem.Companion.createTm(17, "confuseray"));
			output.accept(TechnicalMachineItem.Companion.createTm(18, "thief"));
			output.accept(TechnicalMachineItem.Companion.createTm(19, "disarmingvoice"));
			output.accept(TechnicalMachineItem.Companion.createTm(20, "trailblaze"));
			output.accept(TechnicalMachineItem.Companion.createTm(21, "pounce"));
			output.accept(TechnicalMachineItem.Companion.createTm(22, "chillingwater"));
			output.accept(TechnicalMachineItem.Companion.createTm(23, "chargebeam"));
			output.accept(TechnicalMachineItem.Companion.createTm(24, "firespin"));
			output.accept(TechnicalMachineItem.Companion.createTm(25, "facade"));
			output.accept(TechnicalMachineItem.Companion.createTm(26, "poisontail"));
			output.accept(TechnicalMachineItem.Companion.createTm(27, "aerialace"));
			output.accept(TechnicalMachineItem.Companion.createTm(28, "bulldoze"));
			output.accept(TechnicalMachineItem.Companion.createTm(29, "hex"));
			output.accept(TechnicalMachineItem.Companion.createTm(30, "snarl"));
			output.accept(TechnicalMachineItem.Companion.createTm(31, "metalclaw"));
			output.accept(TechnicalMachineItem.Companion.createTm(32, "switft"));
			output.accept(TechnicalMachineItem.Companion.createTm(33, "magicalleaf"));
			output.accept(TechnicalMachineItem.Companion.createTm(34, "icywind"));
			output.accept(TechnicalMachineItem.Companion.createTm(35, "mudshot"));
			output.accept(TechnicalMachineItem.Companion.createTm(36, "rocktomb"));
			output.accept(TechnicalMachineItem.Companion.createTm(37, "drainingkiss"));
			output.accept(TechnicalMachineItem.Companion.createTm(38, "flamecharge"));
			output.accept(TechnicalMachineItem.Companion.createTm(39, "lowsweep"));
			output.accept(TechnicalMachineItem.Companion.createTm(40, "aircutter"));
			output.accept(TechnicalMachineItem.Companion.createTm(41, "storedpower"));
			output.accept(TechnicalMachineItem.Companion.createTm(42, "nightshade"));
			output.accept(TechnicalMachineItem.Companion.createTm(43, "fling"));
			output.accept(TechnicalMachineItem.Companion.createTm(44, "dragontail"));
			output.accept(TechnicalMachineItem.Companion.createTm(45, "venoshock"));
			output.accept(TechnicalMachineItem.Companion.createTm(46, "avalanche"));
			output.accept(TechnicalMachineItem.Companion.createTm(47, "endure"));
			output.accept(TechnicalMachineItem.Companion.createTm(48, "voltswitch"));
			output.accept(TechnicalMachineItem.Companion.createTm(49, "sunnyday"));
			output.accept(TechnicalMachineItem.Companion.createTm(50, "raindance"));
			output.accept(TechnicalMachineItem.Companion.createTm(51, "sandstorm"));
			output.accept(TechnicalMachineItem.Companion.createTm(52, "snowscape"));
			output.accept(TechnicalMachineItem.Companion.createTm(53, "smartstrike"));
			output.accept(TechnicalMachineItem.Companion.createTm(54, "psyshock"));
			output.accept(TechnicalMachineItem.Companion.createTm(55, "dig"));
			output.accept(TechnicalMachineItem.Companion.createTm(56, "bulletseed"));
			output.accept(TechnicalMachineItem.Companion.createTm(57, "falseswipe"));
			output.accept(TechnicalMachineItem.Companion.createTm(58, "brickbreak"));
			output.accept(TechnicalMachineItem.Companion.createTm(59, "zenheadbutt"));
			output.accept(TechnicalMachineItem.Companion.createTm(60, "uturn"));
			output.accept(TechnicalMachineItem.Companion.createTm(61, "shadowclaw"));
			output.accept(TechnicalMachineItem.Companion.createTm(62, "foulplay"));
			output.accept(TechnicalMachineItem.Companion.createTm(63, "psychicfangs"));
			output.accept(TechnicalMachineItem.Companion.createTm(64, "bulkup"));
			output.accept(TechnicalMachineItem.Companion.createTm(65, "airslash"));
			output.accept(TechnicalMachineItem.Companion.createTm(66, "bodyslam"));
			output.accept(TechnicalMachineItem.Companion.createTm(67, "firepunch"));
			output.accept(TechnicalMachineItem.Companion.createTm(68, "thunderpunch"));
			output.accept(TechnicalMachineItem.Companion.createTm(69, "icepunch"));
			output.accept(TechnicalMachineItem.Companion.createTm(70, "sleeptalk"));
			output.accept(TechnicalMachineItem.Companion.createTm(71, "seedbomb"));
			output.accept(TechnicalMachineItem.Companion.createTm(72, "electroball"));
			output.accept(TechnicalMachineItem.Companion.createTm(73, "drainpunch"));
			output.accept(TechnicalMachineItem.Companion.createTm(74, "reflect"));
			output.accept(TechnicalMachineItem.Companion.createTm(75, "lightscreen"));
			output.accept(TechnicalMachineItem.Companion.createTm(76, "rockblast"));
			output.accept(TechnicalMachineItem.Companion.createTm(77, "waterfall"));
			output.accept(TechnicalMachineItem.Companion.createTm(78, "dragonclaw"));
			output.accept(TechnicalMachineItem.Companion.createTm(79, "dazzlinggleam"));
			output.accept(TechnicalMachineItem.Companion.createTm(80, "metronome"));
			output.accept(TechnicalMachineItem.Companion.createTm(81, "grassknot"));
			output.accept(TechnicalMachineItem.Companion.createTm(82, "thunderwave"));
			output.accept(TechnicalMachineItem.Companion.createTm(83, "poisonjab"));
			output.accept(TechnicalMachineItem.Companion.createTm(84, "stompingtantrum"));
			output.accept(TechnicalMachineItem.Companion.createTm(85, "rest"));
			output.accept(TechnicalMachineItem.Companion.createTm(86, "rockslide"));
			output.accept(TechnicalMachineItem.Companion.createTm(87, "taunt"));
			output.accept(TechnicalMachineItem.Companion.createTm(88, "swordsdance"));
			output.accept(TechnicalMachineItem.Companion.createTm(89, "bodypress"));
			output.accept(TechnicalMachineItem.Companion.createTm(90, "spikes"));
			output.accept(TechnicalMachineItem.Companion.createTm(91, "toxicspikes"));
			output.accept(TechnicalMachineItem.Companion.createTm(92, "imprison"));
			output.accept(TechnicalMachineItem.Companion.createTm(93, "flashcannon"));
			output.accept(TechnicalMachineItem.Companion.createTm(94, "darkpulse"));
			output.accept(TechnicalMachineItem.Companion.createTm(95, "leechlife"));
			output.accept(TechnicalMachineItem.Companion.createTm(96, "eerieimpulse"));
			output.accept(TechnicalMachineItem.Companion.createTm(97, "fly"));
			output.accept(TechnicalMachineItem.Companion.createTm(98, "skillswap"));
			output.accept(TechnicalMachineItem.Companion.createTm(99, "ironhead"));
			output.accept(TechnicalMachineItem.Companion.createTm(100, "dragondance"));
			output.accept(TechnicalMachineItem.Companion.createTm(101, "powergem"));
			output.accept(TechnicalMachineItem.Companion.createTm(102, "gunkshot"));
			output.accept(TechnicalMachineItem.Companion.createTm(103, "substitue"));
			output.accept(TechnicalMachineItem.Companion.createTm(104, "irondefense"));
			output.accept(TechnicalMachineItem.Companion.createTm(105, "xscissor"));
			output.accept(TechnicalMachineItem.Companion.createTm(106, "drillrun"));
			output.accept(TechnicalMachineItem.Companion.createTm(107, "willowisp"));
			output.accept(TechnicalMachineItem.Companion.createTm(108, "crunch"));
			output.accept(TechnicalMachineItem.Companion.createTm(109, "trick"));
			output.accept(TechnicalMachineItem.Companion.createTm(110, "liquidation"));
			output.accept(TechnicalMachineItem.Companion.createTm(111, "gigadrain"));
			output.accept(TechnicalMachineItem.Companion.createTm(112, "aurasphere"));
			output.accept(TechnicalMachineItem.Companion.createTm(113, "tailwind"));
			output.accept(TechnicalMachineItem.Companion.createTm(114, "shadowball"));
			output.accept(TechnicalMachineItem.Companion.createTm(115, "dragonpulse"));
			output.accept(TechnicalMachineItem.Companion.createTm(116, "stealthrock"));
			output.accept(TechnicalMachineItem.Companion.createTm(117, "hypervoice"));
			output.accept(TechnicalMachineItem.Companion.createTm(118, "heatwave"));
			output.accept(TechnicalMachineItem.Companion.createTm(119, "energyball"));
			output.accept(TechnicalMachineItem.Companion.createTm(120, "psychic"));
			output.accept(TechnicalMachineItem.Companion.createTm(121, "heavyslam"));
			output.accept(TechnicalMachineItem.Companion.createTm(122, "encore"));
			output.accept(TechnicalMachineItem.Companion.createTm(123, "surf"));
			output.accept(TechnicalMachineItem.Companion.createTm(124, "icespinner"));
			output.accept(TechnicalMachineItem.Companion.createTm(125, "flamethrower"));
			output.accept(TechnicalMachineItem.Companion.createTm(126, "thunderbolt"));
			output.accept(TechnicalMachineItem.Companion.createTm(127, "playrough"));
			output.accept(TechnicalMachineItem.Companion.createTm(128, "amnesia"));
			output.accept(TechnicalMachineItem.Companion.createTm(129, "calmmind"));
			output.accept(TechnicalMachineItem.Companion.createTm(130, "helpinghand"));
			output.accept(TechnicalMachineItem.Companion.createTm(131, "pollenpuff"));
			output.accept(TechnicalMachineItem.Companion.createTm(132, "batonpass"));
			output.accept(TechnicalMachineItem.Companion.createTm(133, "earthpower"));
			output.accept(TechnicalMachineItem.Companion.createTm(134, "reversal"));
			output.accept(TechnicalMachineItem.Companion.createTm(135, "icebeam"));
			output.accept(TechnicalMachineItem.Companion.createTm(136, "electricterrain"));
			output.accept(TechnicalMachineItem.Companion.createTm(137, "grassterrain"));
			output.accept(TechnicalMachineItem.Companion.createTm(138, "psychicterrain"));
			output.accept(TechnicalMachineItem.Companion.createTm(139, "mistyterrain"));
			output.accept(TechnicalMachineItem.Companion.createTm(140, "nastyplot"));
			output.accept(TechnicalMachineItem.Companion.createTm(141, "fireblast"));
			output.accept(TechnicalMachineItem.Companion.createTm(142, "hydropump"));
			output.accept(TechnicalMachineItem.Companion.createTm(143, "blizzard"));
			output.accept(TechnicalMachineItem.Companion.createTm(144, "firepledge"));
			output.accept(TechnicalMachineItem.Companion.createTm(145, "waterpledge"));
			output.accept(TechnicalMachineItem.Companion.createTm(146, "grasspledge"));
			output.accept(TechnicalMachineItem.Companion.createTm(147, "wildcharge"));
			output.accept(TechnicalMachineItem.Companion.createTm(148, "sludgebomb"));
			output.accept(TechnicalMachineItem.Companion.createTm(149, "earthquake"));
			output.accept(TechnicalMachineItem.Companion.createTm(150, "stoneedge"));
			output.accept(TechnicalMachineItem.Companion.createTm(151, "phantomforce"));
			output.accept(TechnicalMachineItem.Companion.createTm(152, "gigaimpact"));
			output.accept(TechnicalMachineItem.Companion.createTm(153, "blastburn"));
			output.accept(TechnicalMachineItem.Companion.createTm(154, "hydrocannon"));
			output.accept(TechnicalMachineItem.Companion.createTm(155, "frenzyplant"));
			output.accept(TechnicalMachineItem.Companion.createTm(156, "outrage"));
			output.accept(TechnicalMachineItem.Companion.createTm(157, "overheat"));
			output.accept(TechnicalMachineItem.Companion.createTm(158, "focusblast"));
			output.accept(TechnicalMachineItem.Companion.createTm(159, "leafstorm"));
			output.accept(TechnicalMachineItem.Companion.createTm(160, "hurricane"));
			output.accept(TechnicalMachineItem.Companion.createTm(161, "trickroom"));
			output.accept(TechnicalMachineItem.Companion.createTm(162, "bugbuzz"));
			output.accept(TechnicalMachineItem.Companion.createTm(163, "hyperbeam"));
			output.accept(TechnicalMachineItem.Companion.createTm(164, "bravebird"));
			output.accept(TechnicalMachineItem.Companion.createTm(165, "flareblitz"));
			output.accept(TechnicalMachineItem.Companion.createTm(166, "thunder"));
			output.accept(TechnicalMachineItem.Companion.createTm(167, "closecombat"));
			output.accept(TechnicalMachineItem.Companion.createTm(168, "solarbeam"));
			output.accept(TechnicalMachineItem.Companion.createTm(169, "dracometeor"));
			output.accept(TechnicalMachineItem.Companion.createTm(170, "steelbeam"));
			output.accept(TechnicalMachineItem.Companion.createTm(171, "terablast"));
		}));
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
