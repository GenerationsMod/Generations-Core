package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsOreSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

public class GenerationsOres {
	public static final DeferredRegister<Block> ORES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);

	/**
	 * Stone Ores (Charge Stone Variants) Temporarly disabled till ready to use in chargestone cave modules

	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_DAWN_STONE_ORE = registerBlockItem("charge_stone_dawn_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_DUSK_STONE_ORE = registerBlockItem("charge_stone_dusk_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_FIRE_STONE_ORE = registerBlockItem("charge_stone_fire_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_ICE_STONE_ORE = registerBlockItem("charge_stone_ice_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_LEAF_STONE_ORE = registerBlockItem("charge_stone_leaf_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_SHINY_STONE_ORE = registerBlockItem("charge_stone_shiny_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_SUN_STONE_ORE = registerBlockItem("charge_stone_sun_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_THUNDER_STONE_ORE = registerBlockItem("charge_stone_thunder_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_WATER_STONE_ORE = registerBlockItem("charge_stone_water_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_MOON_STONE_ORE = registerBlockItem("charge_stone_moon_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	 */

	/**
	 * Tumblestone Ores
	 */
	public static final GenerationsOreSet TUMBLESTONE_ORE_SET = new GenerationsOreSet("tumblestone_ore");
	public static final GenerationsOreSet BLACK_TUMBLESTONE_ORE_SET = new GenerationsOreSet("black_tumblestone_ore");
	public static final GenerationsOreSet RARE_TUMBLESTONE_ORE_SET = new GenerationsOreSet("rare_tumblestone_ore");
	public static final GenerationsOreSet SKY_TUMBLESTONE_ORE_SET = new GenerationsOreSet("sky_tumblestone_ore");


	public static final GenerationsOreSet MEGASTONE_ORE_SET = new GenerationsOreSet("megastone_ore");
	public static final GenerationsOreSet METEORITE_ORE_SET = new GenerationsOreSet("meteorite_ore");


	/**
	 * Other Ores
	 */
	public static final GenerationsOreSet ALUMINUM_ORE_SET = new GenerationsOreSet("aluminum_ore");
	public static final GenerationsOreSet CRYSTAL_ORE_SET = new GenerationsOreSet("crystal_ore");
	public static final GenerationsOreSet RUBY_ORE_SET = new GenerationsOreSet("ruby_ore");
	public static final GenerationsOreSet SAPPHIRE_ORE_SET = new GenerationsOreSet("sapphire_ore");
	public static final GenerationsOreSet SILICON_ORE_SET = new GenerationsOreSet("silicon_ore");
	public static final GenerationsOreSet Z_CRYSTAL_ORE_SET = new GenerationsOreSet("z_crystal_ore");
	public static final GenerationsOreSet FOSSIL_ORE_SET = new GenerationsOreSet("fossil_ore");



	// Charge Stone Vanilla Ores
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_COAL_ORE = registerOreBlockItem("charge_stone_coal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).dropsLike(Blocks.COAL_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_DIAMOND_ORE = registerOreBlockItem("charge_stone_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).dropsLike(Blocks.DIAMOND_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_EMERALD_ORE = registerOreBlockItem("charge_stone_emerald_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).dropsLike(Blocks.EMERALD_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_GOLD_ORE = registerOreBlockItem("charge_stone_gold_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).dropsLike(Blocks.GOLD_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_IRON_ORE = registerOreBlockItem("charge_stone_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_COPPER_ORE = registerOreBlockItem("charge_stone_copper_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE).dropsLike(Blocks.COPPER_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_LAPIS_LAZULI_ORE = registerOreBlockItem("charge_stone_lapis_lazuli_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.LAPIS_ORE).dropsLike(Blocks.LAPIS_ORE)));
	public static final RegistrySupplier<RedStoneOreBlock> CHARGE_STONE_REDSTONE_ORE = registerOreBlockItem("charge_stone_redstone_ore", () -> new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_ORE).dropsLike(Blocks.REDSTONE_ORE)));


	private static void register(String name, Function<Item.Properties, Item> itemSupplier) {
		GenerationsItems.ITEMS.register(name, () -> itemSupplier.apply(new Item.Properties().arch$tab(GenerationsCreativeTabs.NATURAL)));
	}

	public static <T extends Block> RegistrySupplier<T> registerOreBlockItem(String name, Supplier<T> blockSupplier) {
		RegistrySupplier<T> block = ORES.register(name, blockSupplier);
		register(name, properties -> new BlockItem(block.get(), properties));
		return block;
	}

	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Ores");
		ORES.register();
	}
}