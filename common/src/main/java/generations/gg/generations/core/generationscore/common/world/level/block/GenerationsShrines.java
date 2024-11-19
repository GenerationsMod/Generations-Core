package generations.gg.generations.core.generationscore.common.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.item.BlockItemWithLang;
import generations.gg.generations.core.generationscore.common.world.item.DarkCrystalItem;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.FieryShrineBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.FrozenShrineBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.HoohShrineBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.StaticShrineBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class GenerationsShrines {
	public static final DeferredRegister<Block> SHRINES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);


	public static final BlockBehaviour.Properties SHRINE_PROPERTIES = BlockBehaviour.Properties.of()/*of(Material.HEAVY_METAL) TODO: Verify we have all properties*/.strength(-1.0F, 3600000.8F).noLootTable().isValidSpawn(Blocks::never).lightLevel(value -> 5);
	public static final BlockBehaviour.Properties BOTTLE_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).isValidSpawn(Blocks::never);

	/**
	 * Shrine Blocks
	 */
	public static final RegistrySupplier<Block> FIERY_SHRINE = registerBlockItem("fiery_shrine", () -> new FieryShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> FROZEN_SHRINE = registerBlockItem("frozen_shrine", () -> new FrozenShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> STATIC_SHRINE = registerBlockItem("static_shrine", () -> new StaticShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> LUGIA_SHRINE = registerBlockItem("lugia_shrine", () -> new LugiaShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> HO_OH_SHRINE = registerBlockItem("ho_oh_shrine", () -> new HoohShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> GROUDON_SHRINE = registerBlockItem("groudon_shrine", () -> new WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.GROUDON_SHRINE, LegendKeys.GROUDON, GenerationsItems.FADED_RED_ORB));
	public static final RegistrySupplier<Block> KYOGRE_SHRINE = registerBlockItem("kyogre_shrine", () -> new WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.KYOGRE_SHRINE, LegendKeys.KYOGRE, GenerationsItems.FADED_BLUE_ORB));
	public static final RegistrySupplier<Block> TIMESPACE_ALTAR = registerBlockItem("timespace_altar", () -> new TimespaceAltarBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> ABUNDANT_SHRINE = registerBlockItem("abundant_shrine", () -> new AbundantShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> CELESTIAL_ALTAR = registerBlockItem("celestial_altar", () -> new CelestialAltarBlock(SHRINE_PROPERTIES), (block, properties) -> new BlockItemWithLang(block, properties) {
			@Override
			protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
				if (context.getLevel().dimensionType().natural() && super.placeBlock(context, state)) return true;
				else {
					context.getPlayer().displayClientMessage(Component.literal("You can not place the celestial altar in an unnatural place!"), true);
					return false;
				}
			}
	});
	public static final RegistrySupplier<Block> LUNAR_SHRINE = registerBlockItem("lunar_shrine", () -> new LunarShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> LIGHT_CRYSTAL = registerBlockItem("light_crystal", () -> new LunarCystalBlock(BlockBehaviour.Properties.of(), GenerationsBlockEntityModels.LIGHT_CRYSTAL));
	public static final RegistrySupplier<Block> DARK_CRYSTAL = registerBlockItem("dark_crystal", () -> new LunarCystalBlock(BlockBehaviour.Properties.of(), GenerationsBlockEntityModels.DARK_CRYSTAL), DarkCrystalItem::new);

	public static final RegistrySupplier<Block> MELOETTA_MUSIC_BOX = registerBlockItem("meloetta_music_box", () -> new MeloettaMusicBoxBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> REGICE_SHRINE = registerBlockItem("regice_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGICE_SHRINE, LegendKeys.REGICE));
	public static final RegistrySupplier<Block> REGIDRAGO_SHRINE = registerBlockItem("regidrago_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIDRAGO_SHRINE, LegendKeys.REGIDRAGO));
	public static final RegistrySupplier<Block> REGIELEKI_SHRINE = registerBlockItem("regieleki_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIELEKI_SHRINE, LegendKeys.REGIELEKI));
	public static final RegistrySupplier<Block> REGIROCK_SHRINE = registerBlockItem("regirock_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIROCK_SHRINE, LegendKeys.REGIROCK));
	public static final RegistrySupplier<Block> REGISTEEL_SHRINE = registerBlockItem("registeel_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGISTEEL_SHRINE, LegendKeys.REGISTEEL));
	public static final RegistrySupplier<Block> REGIGIGAS_SHRINE = registerBlockItem("regigigas_shrine", () -> new RegigigasShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> TAO_TRIO_SHRINE = registerBlockItem("tao_trio_shrine", () -> new TaoTrioShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> TAPU_SHRINE = registerBlockItem("tapu_shrine", () -> new TapuShrineBlock(SHRINE_PROPERTIES));

	public static final RegistrySupplier<PrisonBottleStemBlock> PRISON_BOTTLE_STEM = registerBlockItem("prison_bottle_stem", () -> new PrisonBottleStemBlock(BOTTLE_PROPERTIES), BlockItemWithLang::new, GenerationsItems.LEGENDARY_ITEMS);
	public static final RegistrySupplier<PrisonBottleBlock> PRISON_BOTTLE = registerBlockItem("prison_bottle", () -> new PrisonBottleBlock(BOTTLE_PROPERTIES), (block, properties) -> new FormChangingBlockItem(block, properties, "unbound", null), GenerationsItems.LEGENDARY_ITEMS);


	private static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier, BiFunction<Block, Item.Properties, Item> function, DeferredRegister<Item> register) {
		RegistrySupplier<T> block = GenerationsUtils.registerBlock(SHRINES, name, blockSupplier);
		register.register(name, () -> function.apply(block.get(), new Item.Properties()));
		return block;
	}


	private static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
		return registerBlockItem(name, blockSupplier, BlockItemWithLang::new);
	}


	private static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier, BiFunction<Block, Item.Properties, Item> function) {
		return registerBlockItem(name, blockSupplier, function, GenerationsItems.ITEMS);
	}


	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Shrines");
		SHRINES.register();
	}

}
