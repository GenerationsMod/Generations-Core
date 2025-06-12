package generations.gg.generations.core.generationscore.common.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.item.tools.*;
import generations.gg.generations.core.generationscore.common.world.item.tools.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Function;
import java.util.function.Supplier;

public class GenerationsTools {
	public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

	/**
	 * Tools
	 */
	public static final ToolSet CHARGE_STONE = ToolSet.create("charge_stone", () -> GenerationsTiers.CHARGE_STONE);

	public static final ToolSet VOLCANIC_STONE = ToolSet.create("volcanic_stone", () -> GenerationsTiers.CHARGE_STONE);

	public static final ToolSet AMETHYST = ToolSet.create("amethyst", () -> GenerationsTiers.AMETHYST);

	public static final ToolSet CRYSTAL = ToolSet.create("crystal", () -> GenerationsTiers.CRYSTAL);

	public static  final ToolSet DAWN_STONE = ToolSet.create("dawn_stone", () -> GenerationsTiers.DAWN_STONE,
			new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1));

	public static final ToolSet DUSK_STONE = ToolSet.create("dusk_stone", () -> GenerationsTiers.DUSK_STONE,
			new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1));

	public static final ToolSet FIRE_STONE = ToolSet.create("fire_stone", () -> GenerationsTiers.FIRE_STONE,
			true,
			new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1));

	public static final ToolSet ICE_STONE = ToolSet.create("ice_stone", () -> GenerationsTiers.ICE_STONE,
			new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1));

	public static final ToolSet LEAF_STONE = ToolSet.create("leaf_stone", () -> GenerationsTiers.LEAF_STONE,
			new BoneMealToolEffect(12));

	public static final ToolSet MOON_STONE = ToolSet.create("moon_stone", () -> GenerationsTiers.MOON_STONE,
			new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1));

	public static final ToolSet RUBY = ToolSet.create("ruby", () -> GenerationsTiers.RUBY);

	public static final ToolSet SAPPHIRE = ToolSet.create("sapphire", () -> GenerationsTiers.SAPPHIRE);

	public static final ToolSet SILICON = ToolSet.create("silicon", () -> GenerationsTiers.SILICON);

	public static final ToolSet SUN_STONE = ToolSet.create("sun_stone", () -> GenerationsTiers.SUN_STONE,
			true,
			new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)); //TODO: Replace with temp light source derived from tinker's construct's light source

	public static final ToolSet THUNDER_STONE = ToolSet.create("thunder_stone", () -> GenerationsTiers.THUNDER_STONE,
			new EnchantmentToolEffect(Enchantments.EFFICIENCY, 3, 1));

	public static final ToolSet WATER_STONE = ToolSet.create("water_stone", () -> GenerationsTiers.WATER_STONE,
			new EnchantmentToolEffect(Enchantments.EFFICIENCY, 3, 1));

	public static final ToolSet ULTRITE = ToolSet.create("ultrite", () -> GenerationsTiers.ULTRITE, true);

	public static final RegistrySupplier<Item> DIAMOND_HAMMER = register("diamond_hammer", properties -> new GenerationsHammerItem(Tiers.DIAMOND, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> GOLDEN_HAMMER = register("golden_hammer", properties -> new GenerationsHammerItem(Tiers.GOLD, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> IRON_HAMMER = register("iron_hammer", properties -> new GenerationsHammerItem(Tiers.IRON, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> NETHERITE_HAMMER = register("netherite_hammer", properties -> new GenerationsHammerItem(Tiers.NETHERITE, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
//	public static final RegistrySupplier<Item> ULTRITE_HAMMER = register("ultrite_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.ULTRITE, 4.0F, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> STONE_HAMMER = register("stone_hammer", properties -> new GenerationsHammerItem(Tiers.STONE, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> WOODEN_HAMMER = register("wooden_hammer", properties -> new GenerationsHammerItem(Tiers.WOOD, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);

	private static <T extends Item> RegistrySupplier<T> register(String name, Function<Item.Properties, T> function, ResourceKey<CreativeModeTab> tab) {
		return TOOLS.register(name, () -> function.apply(of().arch$tab(tab)));
	}


	public static Item.Properties of() {
		return new Item.Properties();
	}

	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Tools");
		TOOLS.register();
	}

	public record ToolSet(RegistrySupplier<GenerationsShovelItem> shovel, RegistrySupplier<GenerationsPickaxeItem> pickaxe, RegistrySupplier<GenerationsAxeItem> axe, RegistrySupplier<GenerationsHoeItem> hoe, RegistrySupplier<GenerationsHammerItem> hammer, RegistrySupplier<GenerationsSwordItem> sword) {
		public static ToolSet create(String name, Supplier<Tier> tier, ToolEffect... toolEffects) {
			return create(name, tier, false, toolEffects);
		}

		public static ToolSet create(String name, Supplier<Tier> tier, boolean fireProof, ToolEffect... toolEffects) {
			return new ToolSet(
					register(name + "_shovel", GenerationsShovelItem::new, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, toolEffects),
					register(name + "_pickaxe", GenerationsPickaxeItem::new, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, toolEffects),
					register(name + "_axe", GenerationsAxeItem::new, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, toolEffects),
					register(name + "_hoe", GenerationsHoeItem::new, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, toolEffects),
					register(name + "_hammer", GenerationsHammerItem::new, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, toolEffects),
					register(name + "_sword", GenerationsSwordItem::new, tier, CreativeModeTabs.COMBAT, fireProof, toolEffects));
		}
		private static <T extends Item & ToolEffectHolder<T>> RegistrySupplier<T> register(String name, ToolSupplier<T> supplier, Supplier<Tier> tier, ResourceKey<CreativeModeTab> tab, boolean fireProof, ToolEffect... toolEffects) {
			return GenerationsTools.register(name, properties -> {
				if(fireProof) properties.fireResistant();
				return supplier.create(tier.get(), properties).addToolEffects(toolEffects);
			}, tab);
		}

		private interface ToolSupplier<T extends Item> {
			T create(Tier tier, Item.Properties properties);
		}
	}
}
