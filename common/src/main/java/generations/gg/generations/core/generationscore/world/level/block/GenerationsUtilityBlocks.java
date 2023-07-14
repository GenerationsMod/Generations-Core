package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericBlastFurnaceBlock;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericFurnaceBlock;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericSmokerBlock;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class GenerationsUtilityBlocks {
	public static final DeferredRegister<Block> UTILITY_BLOCKS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
	public static final List<RegistrySupplier<DyedBlockItem<HealerBlock>>> HEALER_BLOCKS = new ArrayList<>();
	public static final List<RegistrySupplier<DyedBlockItem<PCBlock>>> PC_BLOCKS = new ArrayList<>();
	public static final List<RegistrySupplier<DyedBlockItem<ClockBlock>>> CLOCK_BLOCKS = new ArrayList<>();

	/**
	 * Utility Blocks
	 */
	public static final RegistrySupplier<Block> BOX = registerBlockItem("box", () -> new BoxBlock(BlockBehaviour.Properties.of().destroyTime(0.5f).sound(SoundType.WOOD).ignitedByLava()));
	public static final RegistrySupplier<Block> COOKING_POT = registerBlockItem("cooking_pot", () -> new CookingPotBlock(BlockBehaviour.Properties.of().strength(2.5f).randomTicks().noOcclusion()));

	public static final RegistrySupplier<HealerBlock> HEALER = registerBlock("healer", () -> new HealerBlock(BlockBehaviour.Properties.of().strength(2.5f)));
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> WHITE_HEALER = registerHealer("white_healer", DyeColor.WHITE);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> RED_HEALER = registerHealer("red_healer", DyeColor.RED);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> ORANGE_HEALER = registerHealer("orange_healer", DyeColor.ORANGE);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> PINK_HEALER = registerHealer("pink_healer", DyeColor.PINK);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> YELLOW_HEALER = registerHealer("yellow_healer", DyeColor.YELLOW);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> LIME_HEALER = registerHealer("lime_healer", DyeColor.LIME);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> GREEN_HEALER = registerHealer("green_healer", DyeColor.GREEN);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> LIGHT_BLUE_HEALER = registerHealer("light_blue_healer", DyeColor.LIGHT_BLUE);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> CYAN_HEALER = registerHealer("cyan_healer", DyeColor.CYAN);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> BLUE_HEALER = registerHealer("blue_healer", DyeColor.BLUE);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> MAGENTA_HEALER = registerHealer("magenta_healer", DyeColor.MAGENTA);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> PURPLE_HEALER = registerHealer("purple_healer", DyeColor.PURPLE);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> BROWN_HEALER = registerHealer("brown_healer", DyeColor.BROWN);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> GRAY_HEALER = registerHealer("gray_healer", DyeColor.GRAY);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> LIGHT_GRAY_HEALER = registerHealer("light_gray_healer", DyeColor.LIGHT_GRAY);
	public static final RegistrySupplier<DyedBlockItem<HealerBlock>> BLACK_HEALER = registerHealer("black_healer", DyeColor.BLACK);

	//PC Blocks
	public static final RegistrySupplier<PCBlock> PC = registerBlock("pc", () -> new PCBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2f)));
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> WHITE_PC = registerPC("white_pc", DyeColor.WHITE);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> ORANGE_PC = registerPC("orange_pc", DyeColor.ORANGE);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> MAGENTA_PC = registerPC("magenta_pc", DyeColor.MAGENTA);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> LIGHT_BLUE_PC = registerPC("light_blue_pc", DyeColor.LIGHT_BLUE);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> YELLOW_PC = registerPC("yellow_pc", DyeColor.YELLOW);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> LIME_PC = registerPC("lime_pc", DyeColor.LIME);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> PINK_PC = registerPC("pink_pc", DyeColor.PINK);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> GRAY_PC = registerPC("gray_pc", DyeColor.GRAY);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> LIGHT_GRAY_PC = registerPC("light_gray_pc", DyeColor.LIGHT_GRAY);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> CYAN_PC = registerPC("cyan_pc", DyeColor.CYAN);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> PURPLE_PC = registerPC("purple_pc", DyeColor.PURPLE);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> BLUE_PC = registerPC("blue_pc", DyeColor.BLUE);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> BROWN_PC = registerPC("brown_pc", DyeColor.BROWN);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> GREEN_PC = registerPC("green_pc", DyeColor.GREEN);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> RED_PC = registerPC("red_pc", DyeColor.RED);
	public static final RegistrySupplier<DyedBlockItem<PCBlock>> BLACK_PC = registerPC("black_pc", DyeColor.BLACK);

	public static final RegistrySupplier<ClockBlock> CLOCK = registerBlock("clock", () -> new ClockBlock(BlockBehaviour.Properties.of().strength(2f)));
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> WHITE_CLOCK = registerClock("white_clock", DyeColor.WHITE);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> ORANGE_CLOCK = registerClock("orange_clock", DyeColor.ORANGE);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> MAGENTA_CLOCK = registerClock("magenta_clock", DyeColor.MAGENTA);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> LIGHT_BLUE_CLOCK = registerClock("light_blue_clock", DyeColor.LIGHT_BLUE);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> YELLOW_CLOCK = registerClock("yellow_clock", DyeColor.YELLOW);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> LIME_CLOCK = registerClock("lime_clock", DyeColor.LIME);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> PINK_CLOCK = registerClock("pink_clock", DyeColor.PINK);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> GRAY_CLOCK = registerClock("gray_clock", DyeColor.GRAY);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> LIGHT_GRAY_CLOCK = registerClock("light_gray_clock", DyeColor.LIGHT_GRAY);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> CYAN_CLOCK = registerClock("cyan_clock", DyeColor.CYAN);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> PURPLE_CLOCK = registerClock("purple_clock", DyeColor.PURPLE);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> BLUE_CLOCK = registerClock("blue_clock", DyeColor.BLUE);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> BROWN_CLOCK = registerClock("brown_clock", DyeColor.BROWN);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> GREEN_CLOCK = registerClock("green_clock", DyeColor.GREEN);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> RED_CLOCK = registerClock("red_clock", DyeColor.RED);
	public static final RegistrySupplier<DyedBlockItem<ClockBlock>> BLACK_CLOCK = registerClock("black_clock", DyeColor.BLACK);

	public static final RegistrySupplier<Block> TRASH_CAN = registerBlockItem("trash_can", () -> new TrashCanBlock(BlockBehaviour.Properties.of().destroyTime(1.0f).sound(SoundType.METAL)));

	public static RegistrySupplier<PokeLootBlock> BEAST_LOOT = registerLoot("beast");
	public static RegistrySupplier<PokeLootBlock> CHERISH_LOOT = registerLoot("cherish");
	public static RegistrySupplier<PokeLootBlock> DIVE_LOOT = registerLoot("dive");
	public static RegistrySupplier<PokeLootBlock> DREAM_LOOT = registerLoot("dream");
	public static RegistrySupplier<PokeLootBlock> DUSK_LOOT = registerLoot("dusk");
	public static RegistrySupplier<PokeLootBlock> FAST_LOOT = registerLoot("fast");
	public static RegistrySupplier<PokeLootBlock> FRIEND_LOOT = registerLoot("friend");
	public static RegistrySupplier<PokeLootBlock> GIGATON_LOOT = registerLoot("gigaton");
	public static RegistrySupplier<PokeLootBlock> GREAT_LOOT = registerLoot("great");
	public static RegistrySupplier<PokeLootBlock> HEAL_LOOT = registerLoot("heal");
	public static RegistrySupplier<PokeLootBlock> HEAVY_LOOT = registerLoot("heavy");
	public static RegistrySupplier<PokeLootBlock> JET_LOOT = registerLoot("jet");
	public static RegistrySupplier<PokeLootBlock> LEADEN_LOOT = registerLoot("leaden");
	public static RegistrySupplier<PokeLootBlock> LEVEL_LOOT = registerLoot("level");
	public static RegistrySupplier<PokeLootBlock> LOVE_LOOT = registerLoot("love");
	public static RegistrySupplier<PokeLootBlock> LURE_LOOT = registerLoot("lure");
	public static RegistrySupplier<PokeLootBlock> LUXURY_LOOT = registerLoot("luxury");
	public static RegistrySupplier<PokeLootBlock> MASTER_LOOT = registerLoot("master");
	public static RegistrySupplier<PokeLootBlock> MOON_LOOT = registerLoot("moon");
	public static RegistrySupplier<PokeLootBlock> NEST_LOOT = registerLoot("nest");
	public static RegistrySupplier<PokeLootBlock> NET_LOOT = registerLoot("net");
	public static RegistrySupplier<PokeLootBlock> ORIGIN_LOOT = registerLoot("origin");
	public static RegistrySupplier<PokeLootBlock> PARK_LOOT = registerLoot("park");
	public static RegistrySupplier<PokeLootBlock> POKE_LOOT = registerLoot("poke");
	public static RegistrySupplier<PokeLootBlock> PREMIER_LOOT = registerLoot("premier");
	public static RegistrySupplier<PokeLootBlock> QUICK_LOOT = registerLoot("quick");
	public static RegistrySupplier<PokeLootBlock> REPEAT_LOOT = registerLoot("repeat");
	public static RegistrySupplier<PokeLootBlock> SAFARI_LOOT = registerLoot("safari");
	public static RegistrySupplier<PokeLootBlock> SPORT_LOOT = registerLoot("sport");
	public static RegistrySupplier<PokeLootBlock> STRANGE_LOOT = registerLoot("strange");
	public static RegistrySupplier<PokeLootBlock> TIMER_LOOT = registerLoot("timer");
	public static RegistrySupplier<PokeLootBlock> ULTRA_LOOT = registerLoot("ultra");
	public static RegistrySupplier<PokeLootBlock> WING_LOOT = registerLoot("wing");

	private static RegistrySupplier<PokeLootBlock> registerLoot(String name) {
		return registerBlockItem(name + "_loot", () -> new PokeLootBlock(name, BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(-1.0f, 3600000.0f)));
	}

	public static final RegistrySupplier<BreederBlock> BREEDER = registerBlockItem("breeder", () -> new BreederBlock(BlockBehaviour.Properties.of().destroyTime(1.0f).sound(SoundType.WOOD).ignitedByLava()));
	public static final RegistrySupplier<GenericFurnaceBlock> CHARGE_STONE_FURNACE = registerBlockItem("charge_stone_furnace", () -> new GenericFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.FURNACE)));
	public static final RegistrySupplier<GenericBlastFurnaceBlock> CHARGE_STONE_BLAST_FURNACE = registerBlockItem("charge_stone_blast_furnace", () -> new GenericBlastFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.BLAST_FURNACE)));
	public static final RegistrySupplier<GenericSmokerBlock> CHARGE_STONE_SMOKER = registerBlockItem("charge_stone_smoker", () -> new GenericSmokerBlock(BlockBehaviour.Properties.copy(Blocks.SMOKER)));
	public static final RegistrySupplier<GenericFurnaceBlock> VOLCANIC_STONE_FURNACE = registerBlockItem("volcanic_stone_furnace", () -> new GenericFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.FURNACE)));
	public static final RegistrySupplier<GenericBlastFurnaceBlock> VOLCANIC_STONE_BLAST_FURNACE = registerBlockItem("volcanic_stone_blast_furnace", () -> new GenericBlastFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.BLAST_FURNACE)));
	public static final RegistrySupplier<GenericSmokerBlock> VOLCANIC_STONE_SMOKER = registerBlockItem("volcanic_stone_smoker", () -> new GenericSmokerBlock(BlockBehaviour.Properties.copy(Blocks.SMOKER)));

	private static <T extends BlockItem> RegistrySupplier<T> register(String name, Function<Item.Properties, T> itemSupplier) {
		return GenerationsItems.ITEMS.register(name, () -> itemSupplier.apply(new Item.Properties().arch$tab(GenerationsCreativeTabs.UTILITY)));
	}

    private static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
		RegistrySupplier<T> block = UTILITY_BLOCKS.register(name, blockSupplier);
		register(name, properties -> new BlockItem(block.get(), properties));
		return block;
	}

	private static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> blockSupplier) {
		return UTILITY_BLOCKS.register(name, blockSupplier);
	}

	private static RegistrySupplier<DyedBlockItem<HealerBlock>> registerHealer(String name, DyeColor color) {
		var item = register(name, properties -> new DyedBlockItem<>(HEALER.get(), color, properties));
		HEALER_BLOCKS.add(item);
		return item;
	}

	private static RegistrySupplier<DyedBlockItem<PCBlock>> registerPC(String name, DyeColor color) {
		var item = register(name, properties -> new DyedBlockItem<>(PC.get(), color, properties));
		PC_BLOCKS.add(item);
		return item;
	}

	private static RegistrySupplier<DyedBlockItem<ClockBlock>> registerClock(String name, DyeColor color) {
		var item = register(name, properties -> new DyedBlockItem<>(CLOCK.get(), color, properties));
		CLOCK_BLOCKS.add(item);
		return item;
	}


	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Utility Blocks");
		UTILITY_BLOCKS.register();
	}
}
