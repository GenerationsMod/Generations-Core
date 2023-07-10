package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.shrines.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class GenerationsShrines {
	public static final DeferredRegister<Block> SHRINES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);


	public static final BlockBehaviour.Properties SHRINE_PROPERTIES = BlockBehaviour.Properties.of()/*of(Material.HEAVY_METAL) TODO: Verify we have all properties*/.strength(-1.0F, 3600000.8F).noLootTable().isValidSpawn(Blocks::never).lightLevel(value -> 5);
	/**
	 * Shrine Blocks
	 */
	public static final RegistrySupplier<Block> FIERY_SHRINE = registerBlockItem("fiery_shrine", () -> new BirdShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.MOLTRES_SHRINE, GenerationsCore.id("moltres"), GenerationsItems.FIERY_WING, GenerationsItems.SINISTER_WING));
	public static final RegistrySupplier<Block> FROZEN_SHRINE = registerBlockItem("frozen_shrine", () -> new BirdShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.ARTICUNO_SHRINE, GenerationsCore.id("articuno"), GenerationsItems.ICY_WING, GenerationsItems.ELEGANT_WING));
	public static final RegistrySupplier<Block> STATIC_SHRINE = registerBlockItem("static_shrine", () -> new BirdShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.ZAPDOS_SHRINE, GenerationsCore.id("zapdos"), GenerationsItems.STATIC_WING, GenerationsItems.BELLIGERENT_WING));
	public static final RegistrySupplier<Block> LUGIA_SHRINE = registerBlockItem("lugia_shrine", () -> new BirdShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.LUGIA_SHRINE, GenerationsCore.id("lugia"), GenerationsItems.SILVER_WING));
	public static final RegistrySupplier<Block> CRYSTAL_BELL = registerBlockItem("crystal_bell", () -> new BirdShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.LUGIA_SHRINE, GenerationsCore.id("ho_oh"), GenerationsItems.RAINBOW_WING)); //TODO: Use Crystal bell Model
	public static final RegistrySupplier<Block> GROUDON_SHRINE = registerBlockItem("groudon_shrine", () -> new WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.GROUDON_SHRINE, GenerationsCore.id("groudon"), GenerationsItems.FADED_RED_ORB));
	public static final RegistrySupplier<Block> KYOGRE_SHRINE = registerBlockItem("kyogre_shrine", () -> new WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.KYOGRE_SHRINE, GenerationsCore.id("kyogre"), GenerationsItems.FADED_BLUE_ORB));
	public static final RegistrySupplier<Block> RAYQUAZA_SHRINE = registerBlockItem("rayquaza_shrine", () -> new WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.RAYQUAZA_SHRINE, GenerationsCore.id("rayquaza"), GenerationsItems.JADE_ORB));
	public static final RegistrySupplier<Block> TIMESPACE_ALTAR = registerBlockItem("timespace_altar", () -> new TimespaceAltarBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> ABUNDANT_SHRINE = registerBlockItem("abundant_shrine", () -> new AbundantShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> CELESTIAL_ALTAR = registerBlockItem("celestial_altar", () -> new CelestialAltarBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> LUNAR_SHRINE = registerBlockItem("lunar_shrine", () -> new LunarShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> MELOETTA_MUSIC_BOX = registerBlockItem("meloetta_music_box", () -> new MeloettaMusicBoxBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> REGICE_SHRINE = registerBlockItem("regice_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGICE_SHRINE, GenerationsCore.id("regice")));
	public static final RegistrySupplier<Block> REGIDRAGO_SHRINE = registerBlockItem("regidrago_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIDRAGO_SHRINE, GenerationsCore.id("regidrago")));
	public static final RegistrySupplier<Block> REGIELEKI_SHRINE = registerBlockItem("regieleki_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIELEKI_SHRINE, GenerationsCore.id("regieleki")));
	public static final RegistrySupplier<Block> REGIROCK_SHRINE = registerBlockItem("regirock_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIROCK_SHRINE, GenerationsCore.id("regirock")));
	public static final RegistrySupplier<Block> REGISTEEL_SHRINE = registerBlockItem("registeel_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGISTEEL_SHRINE, GenerationsCore.id("registeel")));
	public static final RegistrySupplier<Block> REGIGIGAS_SHRINE = registerBlockItem("regigigas_shrine", () -> new RegigigasShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> TAO_TRIO_SHRINE = registerBlockItem("tao_trio_shrine", () -> new TaoTrioShrineBlock(SHRINE_PROPERTIES));
	public static final RegistrySupplier<Block> TAPU_SHRINE = registerBlockItem("tapu_shrine", () -> new TapuShrineBlock(SHRINE_PROPERTIES));

	private static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
		RegistrySupplier<T> block = SHRINES.register(name, blockSupplier);
		GenerationsItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().arch$tab(GenerationsCreativeTabs.SHRINES)));
		return block;
	}

	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Shrines");
		SHRINES.register();
	}

}
