package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.tools.*;
import generations.gg.generations.core.generationscore.world.item.tools.effects.*;
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
	public static final ToolSet CHARGE_STONE = ToolSet.create("charge_stone", () -> GenerationsTiers.CHARGE_STONE,
			0,
			0,
			0,
			0,
			0,
			0);

	public static final ToolSet VOLCANIC_STONE = ToolSet.create("volcanic_stone", () -> GenerationsTiers.CHARGE_STONE,
			0,
			0,
			0,
			0,
			0,
			0);

	public static final ToolSet AMETHYST = ToolSet.create("amethyst", () -> GenerationsTiers.AMETHYST,
			1,
			2,
			3,
			2,
			2,
			4);

	public static final ToolSet CRYSTAL = ToolSet.create("crystal", () -> GenerationsTiers.CRYSTAL,
			1,
			2,
			3,
			-2,
			2,
			4);

	public static  final ToolSet DAWN_STONE = ToolSet.create("dawn_stone", () -> GenerationsTiers.DAWN_STONE,
			1,
			2,
			3,
			0,
			2,
			4,
			new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1));

	public static final ToolSet DUSK_STONE = ToolSet.create("dusk_stone", () -> GenerationsTiers.DUSK_STONE,
			1,
			2,
			3,
			0,
			2,
			4,
			new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1));

	public static final ToolSet FIRE_STONE = ToolSet.create("fire_stone", () -> GenerationsTiers.FIRE_STONE,
			1,
			2,
			3,
			-3,
			2,
			4,
			new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1));

	public static final ToolSet ICE_STONE = ToolSet.create("ice_stone", () -> GenerationsTiers.ICE_STONE,
			4,
			5,
			6,
			0,
			5,
			7,
			new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1));

	public static final ToolSet LEAF_STONE = ToolSet.create("leaf_stone", () -> GenerationsTiers.LEAF_STONE,
			1,
			2,
			3,
			-2,
			2,
			4,
			new BoneMealToolEffect(12));

	public static final ToolSet MOON_STONE = ToolSet.create("moon_stone", () -> GenerationsTiers.MOON_STONE,
			1,
			2,
			3,
			0,
			2,
			4,
			new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1));

	public static final ToolSet RUBY = ToolSet.create("ruby", () -> GenerationsTiers.RUBY,
			1,
			2,
			3,
			-2,
			2,
			4);

	public static final ToolSet SAPPHIRE = ToolSet.create("sapphire", () -> GenerationsTiers.SAPPHIRE,
			1,
			2,
			3,
			-2,
			2,
			4);

	public static final ToolSet SILICON = ToolSet.create("silicon", () -> GenerationsTiers.SILICON,
			0,
			0,
			0,
			0,
			6,
			0);

	public static final ToolSet SUN_STONE = ToolSet.create("sun_stone", () -> GenerationsTiers.SUN_STONE,
			1,
			2,
			3,
			0,
			2,
			4,
			new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)); //TODO: Replace with temp light source derived from tinker's construct's light source

	public static final ToolSet THUNDER_STONE = ToolSet.create("thunder_stone", () -> GenerationsTiers.THUNDER_STONE,
			1,
			2,
			3,
			0,
			2,
			4,
			new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1));

	public static final ToolSet WATER_STONE = ToolSet.create("water_stone", () -> GenerationsTiers.WATER_STONE,
			1,
			2,
			3,
			-3,
			2,
			4,
			new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1));

	public static final RegistrySupplier<Item> DIAMOND_HAMMER = register("diamond_hammer", properties -> new GenerationsHammerItem(Tiers.DIAMOND, 5.0F, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> GOLDEN_HAMMER = register("golden_hammer", properties -> new GenerationsHammerItem(Tiers.GOLD, 6.0F, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> IRON_HAMMER = register("iron_hammer", properties -> new GenerationsHammerItem(Tiers.IRON, 6.0F, -3.1F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> NETHERITE_HAMMER = register("netherite_hammer", properties -> new GenerationsHammerItem(Tiers.NETHERITE, 5.0F, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> STONE_HAMMER = register("stone_hammer", properties -> new GenerationsHammerItem(Tiers.STONE, 7.0F, -3.2F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> WOODEN_HAMMER = register("wooden_hammer", properties -> new GenerationsHammerItem(Tiers.WOOD, 6.0F, -3.2F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);

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
		public static ToolSet create(String name, Supplier<Tier> tier, int shovelDamage, int pickaxeDamage, int axeDamage, int hoeDamage, int hammerDamage, int swordDamage, ToolEffect... toolEffects) {
			return new ToolSet(
			register(name + "_shovel", GenerationsShovelItem::new, tier, shovelDamage, -3.0F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register(name + "_pickaxe", GenerationsPickaxeItem::new, tier, pickaxeDamage, -2.8F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register(name + "_axe", GenerationsAxeItem::new, tier, axeDamage, -3.1F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register(name + "_hoe", GenerationsHoeItem::new, tier, hoeDamage, -1.0F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register(name + "_hammer", GenerationsHammerItem::new, tier, hammerDamage, -3.1F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register(name + "_sword", GenerationsSwordItem::new, tier, swordDamage, -2.4F, CreativeModeTabs.COMBAT, toolEffects));
		}
		private static <T extends Item & ToolEffectHolder<T>> RegistrySupplier<T> register(String name, ToolSupplier<T> supplier, Supplier<Tier> tier, int attackDamage, float attackSpeed, ResourceKey<CreativeModeTab> tab, ToolEffect... toolEffects) {
			return GenerationsTools.register(name, properties -> supplier.create(tier.get(), attackDamage, attackSpeed, properties).addToolEffects(toolEffects), tab);
		}

		private interface ToolSupplier<T extends Item> {
			T create(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties);
		}
	}
}
