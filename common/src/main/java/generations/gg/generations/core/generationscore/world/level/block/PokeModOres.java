package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
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

public class PokeModOres {
	public static final DeferredRegister<Block> ORES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);

	/**
	 * Stone Ores
	 */
	public static final RegistrySupplier<DropExperienceBlock> DAWN_STONE_ORE = registerBlockItem("dawn_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DUSK_STONE_ORE = registerBlockItem("dusk_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> FIRE_STONE_ORE = registerBlockItem("fire_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> ICE_STONE_ORE = registerBlockItem("ice_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> LEAF_STONE_ORE = registerBlockItem("leaf_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> SHINY_STONE_ORE = registerBlockItem("shiny_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> SUN_STONE_ORE = registerBlockItem("sun_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> THUNDER_STONE_ORE = registerBlockItem("thunder_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> WATER_STONE_ORE = registerBlockItem("water_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> MOON_STONE_ORE = registerBlockItem("moon_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_DAWN_STONE_ORE = registerBlockItem("deepslate_dawn_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_DUSK_STONE_ORE = registerBlockItem("deepslate_dusk_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_FIRE_STONE_ORE = registerBlockItem("deepslate_fire_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_ICE_STONE_ORE = registerBlockItem("deepslate_ice_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_LEAF_STONE_ORE = registerBlockItem("deepslate_leaf_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_SHINY_STONE_ORE = registerBlockItem("deepslate_shiny_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_SUN_STONE_ORE = registerBlockItem("deepslate_sun_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_THUNDER_STONE_ORE = registerBlockItem("deepslate_thunder_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_WATER_STONE_ORE = registerBlockItem("deepslate_water_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_MOON_STONE_ORE = registerBlockItem("deepslate_moon_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
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

	/**
	 * Tumblestone Ores
	 */
	public static final RegistrySupplier<DropExperienceBlock> BLACK_TUMBLESTONE_ORE = registerBlockItem("black_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> RARE_TUMBLESTONE_ORE = registerBlockItem("rare_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> SKY_TUMBLESTONE_ORE = registerBlockItem("sky_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> TUMBLESTONE_ORE = registerBlockItem("tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_BLACK_TUMBLESTONE_ORE = registerBlockItem("deepslate_black_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_RARE_TUMBLESTONE_ORE = registerBlockItem("deepslate_rare_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_SKY_TUMBLESTONE_ORE = registerBlockItem("deepslate_sky_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_TUMBLESTONE_ORE = registerBlockItem("deepslate_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_BLACK_TUMBLESTONE_ORE = registerBlockItem("charge_stone_black_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_RARE_TUMBLESTONE_ORE = registerBlockItem("charge_stone_rare_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_SKY_TUMBLESTONE_ORE = registerBlockItem("charge_stone_sky_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_TUMBLESTONE_ORE = registerBlockItem("charge_stone_tumblestone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));


	public static final RegistrySupplier<DropExperienceBlock> MEGASTONE_ORE = registerBlockItem("megastone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_MEGASTONE_ORE = registerBlockItem("deepslate_megastone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_MEGASTONE_ORE = registerBlockItem("charge_stone_megastone_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> METEORITE_ORE = registerBlockItem("meteorite_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_METEORITE_ORE = registerBlockItem("deepslate_meteorite_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_METEORITE_ORE = registerBlockItem("charge_stone_meteorite_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

	/**
	 * Other Ores
	 */
	public static final RegistrySupplier<DropExperienceBlock> ALUMINUM_ORE = registerBlockItem("aluminum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_ALUMINUM_ORE = registerBlockItem("deepslate_aluminum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_ALUMINUM_ORE = registerBlockItem("charge_stone_aluminum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CRYSTAL_ORE = registerBlockItem("crystal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_CRYSTAL_ORE = registerBlockItem("deepslate_crystal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_CRYSTAL_ORE = registerBlockItem("charge_stone_crystal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> RUBY_ORE = registerBlockItem("ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_RUBY_ORE = registerBlockItem("deepslate_ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_RUBY_ORE = registerBlockItem("charge_stone_ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> SAPPHIRE_ORE = registerBlockItem("sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_SAPPHIRE_ORE = registerBlockItem("deepslate_sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_SAPPHIRE_ORE = registerBlockItem("charge_stone_sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> SILICON_ORE = registerBlockItem("silicon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_SILICON_ORE = registerBlockItem("deepslate_silicon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_SILICON_ORE = registerBlockItem("charge_stone_silicon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> Z_CRYSTAL_ORE = registerBlockItem("z_crystal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_Z_CRYSTAL_ORE = registerBlockItem("deepslate_z_crystal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_Z_CRYSTAL_ORE = registerBlockItem("charge_stone_z_crystal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> FOSSIL_ORE = registerBlockItem("fossil_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> DEEPSLATE_FOSSIL_ORE = registerBlockItem("deepslate_fossil_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_FOSSIL_ORE = registerBlockItem("charge_stone_fossil_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));


	// Charge Stone Vanilla Ores
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_COAL_ORE = registerBlockItem("charge_stone_coal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_DIAMOND_ORE = registerBlockItem("charge_stone_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_EMERALD_ORE = registerBlockItem("charge_stone_emerald_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_GOLD_ORE = registerBlockItem("charge_stone_gold_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_IRON_ORE = registerBlockItem("charge_stone_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_COPPER_ORE = registerBlockItem("charge_stone_copper_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_LAPIS_LAZULI_ORE = registerBlockItem("charge_stone_lapis_lazuli_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.LAPIS_ORE)));
	public static final RegistrySupplier<RedStoneOreBlock> CHARGE_STONE_REDSTONE_ORE = registerBlockItem("charge_stone_redstone_ore", () -> new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_ORE)));


	private static void register(String name, Function<Item.Properties, Item> itemSupplier) {
		GenerationsItems.ITEMS.register(name, () -> itemSupplier.apply(new Item.Properties().arch$tab(GenerationsCreativeTabs.NATURAL)));
	}

	private static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
		RegistrySupplier<T> block = ORES.register(name, blockSupplier);
		register(name, properties -> new BlockItem(block.get(), properties));
		return block;
	}


	public static void onInitialize() {
		GenerationsCore.LOGGER.info("Registering PokeMod Ores");
		ORES.register();
	}
}