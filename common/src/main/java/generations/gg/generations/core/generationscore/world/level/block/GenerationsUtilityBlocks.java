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
	public static final List<RegistrySupplier<DyedBlockItem<PcBlock>>> PC_BLOCKS = new ArrayList<>();
	public static final List<RegistrySupplier<DyedBlockItem<ClockBlock>>> CLOCK_BLOCKS = new ArrayList<>();

	public static final List<RegistrySupplier<BallLootBlock>> BALL_LOOTS = new ArrayList<>();

	/**
	 * Utility Blocks
	 */
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
	public static final RegistrySupplier<PcBlock> PC = registerBlock("pc", () -> new PcBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2f).lightLevel(PcBlock.Companion::lumiance)));
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> WHITE_PC = registerPC("white_pc", DyeColor.WHITE);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> ORANGE_PC = registerPC("orange_pc", DyeColor.ORANGE);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> MAGENTA_PC = registerPC("magenta_pc", DyeColor.MAGENTA);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> LIGHT_BLUE_PC = registerPC("light_blue_pc", DyeColor.LIGHT_BLUE);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> YELLOW_PC = registerPC("yellow_pc", DyeColor.YELLOW);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> LIME_PC = registerPC("lime_pc", DyeColor.LIME);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> PINK_PC = registerPC("pink_pc", DyeColor.PINK);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> GRAY_PC = registerPC("gray_pc", DyeColor.GRAY);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> LIGHT_GRAY_PC = registerPC("light_gray_pc", DyeColor.LIGHT_GRAY);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> CYAN_PC = registerPC("cyan_pc", DyeColor.CYAN);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> PURPLE_PC = registerPC("purple_pc", DyeColor.PURPLE);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> BLUE_PC = registerPC("blue_pc", DyeColor.BLUE);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> BROWN_PC = registerPC("brown_pc", DyeColor.BROWN);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> GREEN_PC = registerPC("green_pc", DyeColor.GREEN);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> RED_PC = registerPC("red_pc", DyeColor.RED);
	public static final RegistrySupplier<DyedBlockItem<PcBlock>> BLACK_PC = registerPC("black_pc", DyeColor.BLACK);

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

	public static final RegistrySupplier<Block> RKS_MACHINE = registerBlockItem("rks_machine", () -> new RksMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

	public static RegistrySupplier<BallLootBlock> BEAST_BALL_LOOT = registerLoot("beast");
	public static RegistrySupplier<BallLootBlock> CHERISH_BALL_LOOT = registerLoot("cherish");
	public static RegistrySupplier<BallLootBlock> DIVE_BALL_LOOT = registerLoot("dive");
	public static RegistrySupplier<BallLootBlock> DREAM_BALL_LOOT = registerLoot("dream");
	public static RegistrySupplier<BallLootBlock> DUSK_BALL_LOOT = registerLoot("dusk");
	public static RegistrySupplier<BallLootBlock> FAST_BALL_LOOT = registerLoot("fast");
	public static RegistrySupplier<BallLootBlock> FRIEND_BALL_LOOT = registerLoot("friend");
	public static RegistrySupplier<BallLootBlock> GIGATON_BALL_LOOT = registerLoot("gigaton");
	public static RegistrySupplier<BallLootBlock> GREAT_BALL_LOOT = registerLoot("great");
	public static RegistrySupplier<BallLootBlock> HEAL_BALL_LOOT = registerLoot("heal");
	public static RegistrySupplier<BallLootBlock> HEAVY_BALL_LOOT = registerLoot("heavy");
	public static RegistrySupplier<BallLootBlock> JET_BALL_LOOT = registerLoot("jet");
	public static RegistrySupplier<BallLootBlock> LEADEN_BALL_LOOT = registerLoot("leaden");
	public static RegistrySupplier<BallLootBlock> LEVEL_BALL_LOOT = registerLoot("level");
	public static RegistrySupplier<BallLootBlock> LOVE_BALL_LOOT = registerLoot("love");
	public static RegistrySupplier<BallLootBlock> LURE_BALL_LOOT = registerLoot("lure");
	public static RegistrySupplier<BallLootBlock> LUXURY_BALL_LOOT = registerLoot("luxury");
	public static RegistrySupplier<BallLootBlock> MASTER_BALL_LOOT = registerLoot("master");
	public static RegistrySupplier<BallLootBlock> MOON_BALL_LOOT = registerLoot("moon");
	public static RegistrySupplier<BallLootBlock> NEST_BALL_LOOT = registerLoot("nest");
	public static RegistrySupplier<BallLootBlock> NET_BALL_LOOT = registerLoot("net");
	public static RegistrySupplier<BallLootBlock> ORIGIN_BALL_LOOT = registerLoot("origin");
	public static RegistrySupplier<BallLootBlock> PARK_BALL_LOOT = registerLoot("park");
	public static RegistrySupplier<BallLootBlock> POKE_BALL_LOOT = registerLoot("poke");
	public static RegistrySupplier<BallLootBlock> PREMIER_BALL_LOOT = registerLoot("premier");
	public static RegistrySupplier<BallLootBlock> QUICK_BALL_LOOT = registerLoot("quick");
	public static RegistrySupplier<BallLootBlock> REPEAT_BALL_LOOT = registerLoot("repeat");
	public static RegistrySupplier<BallLootBlock> SAFARI_BALL_LOOT = registerLoot("safari");
	public static RegistrySupplier<BallLootBlock> SPORT_BALL_LOOT = registerLoot("sport");
	public static RegistrySupplier<BallLootBlock> STRANGE_BALL_LOOT = registerLoot("strange");
	public static RegistrySupplier<BallLootBlock> TIMER_BALL_LOOT = registerLoot("timer");
	public static RegistrySupplier<BallLootBlock> ULTRA_BALL_LOOT = registerLoot("ultra");
	public static RegistrySupplier<BallLootBlock> WING_BALL_LOOT = registerLoot("wing");

	private static RegistrySupplier<BallLootBlock> registerLoot(String name) {
		var block = registerBlockItem(name + "_ball_loot", () -> new BallLootBlock(name, BlockBehaviour.Properties.of().randomTicks().sound(SoundType.METAL).strength(-1.0f, 3600000.0f).noOcclusion()));
		BALL_LOOTS.add(block);
		return block;
	}

	public static final RegistrySupplier<BreederBlock> BREEDER = registerBlock("breeder", () -> new BreederBlock(BlockBehaviour.Properties.of().destroyTime(1.0f).sound(SoundType.WOOD).ignitedByLava()));
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

	private static RegistrySupplier<DyedBlockItem<PcBlock>> registerPC(String name, DyeColor color) {
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
