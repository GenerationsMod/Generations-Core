package generations.gg.generations.core.generationscore.world.level.block;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.shrines.*;
import com.pokemod.pokemod.world.item.PokeModItems;
import com.pokemod.pokemod.world.item.creativetab.PokeModCreativeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class PokeModShrines {
	public static final DeferredRegister<Block> SHRINES = DeferredRegister.create(ForgeRegistries.BLOCKS, PokeMod.MOD_ID);


	public static final BlockBehaviour.Properties SHRINE_PROPERTIES = BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(-1.0F, 3600000.8F).noLootTable().isValidSpawn(Blocks::never).lightLevel(value -> 5);
	/**
	 * Shrine Blocks
	 */
	public static final RegistryObject<Block> FIERY_SHRINE = registerBlockItem("fiery_shrine", () -> new BirdShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.MOLTRES_SHRINE, PokeMod.id("moltres"), PokeModItems.FIERY_WING, PokeModItems.SINISTER_WING));
	public static final RegistryObject<Block> FROZEN_SHRINE = registerBlockItem("frozen_shrine", () -> new BirdShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.ARTICUNO_SHRINE, PokeMod.id("articuno"), PokeModItems.ICY_WING, PokeModItems.ELEGANT_WING));
	public static final RegistryObject<Block> STATIC_SHRINE = registerBlockItem("static_shrine", () -> new BirdShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.ZAPDOS_SHRINE, PokeMod.id("zapdos"), PokeModItems.STATIC_WING, PokeModItems.BELLIGERENT_WING));
	public static final RegistryObject<Block> LUGIA_SHRINE = registerBlockItem("lugia_shrine", () -> new BirdShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.LUGIA_SHRINE, PokeMod.id("lugia"), PokeModItems.SILVER_WING));
	public static final RegistryObject<Block> CRYSTAL_BELL = registerBlockItem("crystal_bell", () -> new BirdShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.LUGIA_SHRINE, PokeMod.id("ho_oh"), PokeModItems.RAINBOW_WING)); //TODO: Use Crystal bell Model
	public static final RegistryObject<Block> GROUDON_SHRINE = registerBlockItem("groudon_shrine", () -> new WeatherTrioShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.GROUDON_SHRINE, PokeMod.id("groudon"), PokeModItems.FADED_RED_ORB));
	public static final RegistryObject<Block> KYOGRE_SHRINE = registerBlockItem("kyogre_shrine", () -> new WeatherTrioShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.KYOGRE_SHRINE, PokeMod.id("kyogre"), PokeModItems.FADED_BLUE_ORB));
	public static final RegistryObject<Block> RAYQUAZA_SHRINE = registerBlockItem("rayquaza_shrine", () -> new WeatherTrioShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.RAYQUAZA_SHRINE, PokeMod.id("rayquaza"), PokeModItems.JADE_ORB));
	public static final RegistryObject<Block> TIMESPACE_ALTAR = registerBlockItem("timespace_altar", () -> new TimespaceAltarBlock(SHRINE_PROPERTIES));
	public static final RegistryObject<Block> ABUNDANT_SHRINE = registerBlockItem("abundant_shrine", () -> new AbundantShrineBlock(SHRINE_PROPERTIES));
	public static final RegistryObject<Block> CELESTIAL_ALTAR = registerBlockItem("celestial_altar", () -> new CelestialAltarBlock(SHRINE_PROPERTIES));
	public static final RegistryObject<Block> LUNAR_SHRINE = registerBlockItem("lunar_shrine", () -> new LunarShrineBlock(SHRINE_PROPERTIES));
	public static final RegistryObject<Block> MELOETTA_MUSIC_BOX = registerBlockItem("meloetta_music_box", () -> new MeloettaMusicBoxBlock(SHRINE_PROPERTIES));
	public static final RegistryObject<Block> REGICE_SHRINE = registerBlockItem("regice_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.REGICE_SHRINE, PokeMod.id("regice")));
	public static final RegistryObject<Block> REGIDRAGO_SHRINE = registerBlockItem("regidrago_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.REGIDRAGO_SHRINE, PokeMod.id("regidrago")));
	public static final RegistryObject<Block> REGIELEKI_SHRINE = registerBlockItem("regieleki_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.REGIELEKI_SHRINE, PokeMod.id("regieleki")));
	public static final RegistryObject<Block> REGIROCK_SHRINE = registerBlockItem("regirock_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.REGIROCK_SHRINE, PokeMod.id("regirock")));
	public static final RegistryObject<Block> REGISTEEL_SHRINE = registerBlockItem("registeel_shrine", () -> new RegiShrineBlock(SHRINE_PROPERTIES, PokeModBlockEntityModels.REGISTEEL_SHRINE, PokeMod.id("registeel")));
	public static final RegistryObject<Block> REGIGIGAS_SHRINE = registerBlockItem("regigigas_shrine", () -> new RegigigasShrineBlock(SHRINE_PROPERTIES));
	public static final RegistryObject<Block> TAO_TRIO_SHRINE = registerBlockItem("tao_trio_shrine", () -> new TaoTrioShrineBlock(SHRINE_PROPERTIES));
	public static final RegistryObject<Block> TAPU_SHRINE = registerBlockItem("tapu_shrine", () -> new TapuShrineBlock(SHRINE_PROPERTIES));

	private static void register(String name, Supplier<Item> itemSupplier) {
		PokeModCreativeTabs.CREATIVE_TAB_ENTRIES.computeIfAbsent("shrines", a -> new ArrayList<>()).add(PokeModItems.BLOCKITEMS.register(name, itemSupplier));
	}

	private static <T extends Block> RegistryObject<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
		RegistryObject<T> block = SHRINES.register(name, blockSupplier);
		register(name, () -> new BlockItem(block.get(), new Item.Properties()));
		return block;
	}

	public static void onInitialize(IEventBus eventBus) {
		PokeMod.LOGGER.info("Registering PokeMod Shrines");
		SHRINES.register(eventBus);
	}

}
