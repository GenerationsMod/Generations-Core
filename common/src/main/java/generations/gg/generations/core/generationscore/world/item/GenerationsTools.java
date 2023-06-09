package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.tools.*;
import generations.gg.generations.core.generationscore.world.item.tools.effects.*;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class GenerationsTools {
	public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

	/**
	 * Tools
	 */
	public static final ToolSet CHARGE_STONE = ToolSet.create(GenerationsTiers.CHARGE_STONE, 0, 0, 0, 0, 0, 0);

	public static final ToolSet VOLCANIC_STONE = ToolSet.create(GenerationsTiers.CHARGE_STONE, 0, 0, 0, 0, 0, 0);

	public static final ToolSet ALUMINUM = ToolSet.create(GenerationsTiers.CHARGE_STONE, 4, 3, 8, 0, 3, 5);

	public static final ToolSet AMETHYST = ToolSet.create(GenerationsTiers.AMETHYST,
			1,
			2,
			3,
			2,
			2,
			4);

	public static final ToolSet CRYSTAL = ToolSet.create(GenerationsTiers.CRYSTAL,
			1,
			2,
			3,
			-2,
			2,
			4);

	public static  final ToolSet DAWN_STONE = ToolSet.create(GenerationsTiers.DAWN_STONE,
			1,
			2,
			3,
			0,
			2,
			4,
			new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1));

	public static final RegistrySupplier<Item> DUSK_STONE_SHOVEL = register("dusk_stone_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.DUSK_STONE, 1, -3.0F, properties).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> DUSK_STONE_PICKAXE = register("dusk_stone_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.DUSK_STONE, 2, -2.8F, properties).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> DUSK_STONE_AXE = register("dusk_stone_axe", properties -> new GenerationsAxeItem(GenerationsTiers.DUSK_STONE, 3, -3.0F, properties).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> DUSK_STONE_HOE = register("dusk_stone_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.DUSK_STONE, 0, -3.0F, properties).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> DUSK_STONE_HAMMER = register("dusk_stone_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.DUSK_STONE, 2, -3.0F, properties).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> DUSK_STONE_SWORD = register("dusk_stone_sword", properties -> new GenerationsSwordItem(GenerationsTiers.DUSK_STONE, 4, -2.4F, properties).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> FIRE_STONE_SHOVEL = register("fire_stone_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.FIRE_STONE, 1, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> FIRE_STONE_PICKAXE = register("fire_stone_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.FIRE_STONE, 2, -2.8F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> FIRE_STONE_AXE = register("fire_stone_axe", properties -> new GenerationsAxeItem(GenerationsTiers.FIRE_STONE, 3, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> FIRE_STONE_HOE = register("fire_stone_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.FIRE_STONE, -3, 0.0F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> FIRE_STONE_HAMMER = register("fire_stone_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.FIRE_STONE, 2, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> FIRE_STONE_SWORD = register("fire_stone_sword", properties -> new GenerationsSwordItem(GenerationsTiers.FIRE_STONE, 4, -2.4F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> ICE_STONE_SHOVEL = register("ice_stone_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.ICE_STONE, 4, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> ICE_STONE_PICKAXE = register("ice_stone_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.ICE_STONE, 5, -2.8F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> ICE_STONE_AXE = register("ice_stone_axe", properties -> new GenerationsAxeItem(GenerationsTiers.ICE_STONE, 6, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> ICE_STONE_HOE = register("ice_stone_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.ICE_STONE, 0, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> ICE_STONE_HAMMER = register("ice_stone_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.ICE_STONE, 5, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> ICE_STONE_SWORD = register("ice_stone_sword", properties -> new GenerationsSwordItem(GenerationsTiers.ICE_STONE, 7, -2.4F, properties).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> LEAF_STONE_SHOVEL = register("leaf_stone_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.LEAF_STONE, 1, -3.0F, properties).addToolEffect(new BoneMealToolEffect(12)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> LEAF_STONE_PICKAXE = register("leaf_stone_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.LEAF_STONE, 2, -2.8F, properties).addToolEffect(new BoneMealToolEffect(12)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> LEAF_STONE_AXE = register("leaf_stone_axe", properties -> new GenerationsAxeItem(GenerationsTiers.LEAF_STONE, 3, -3.1F, properties).addToolEffect(new BoneMealToolEffect(12)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> LEAF_STONE_HOE = register("leaf_stone_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.LEAF_STONE, -2, -1.0F, properties).addToolEffect(new BoneMealToolEffect(12)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> LEAF_STONE_HAMMER = register("leaf_stone_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.LEAF_STONE, 2, -3.1F, properties).addToolEffect(new BoneMealToolEffect(12)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> LEAF_STONE_SWORD = register("leaf_stone_sword", properties -> new GenerationsSwordItem(GenerationsTiers.LEAF_STONE, 4, -2.4F, properties).addToolEffect(new BoneMealToolEffect(12)), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> MOON_STONE_SHOVEL = register("moon_stone_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.MOON_STONE, 1, -3.0F, properties).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> MOON_STONE_PICKAXE = register("moon_stone_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.MOON_STONE, 2, -2.8F, properties).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> MOON_STONE_AXE = register("moon_stone_axe", properties -> new GenerationsAxeItem(GenerationsTiers.MOON_STONE, 3, -3.0F, properties).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> MOON_STONE_HOE = register("moon_stone_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.MOON_STONE, 0, -3.0F, properties).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> MOON_STONE_HAMMER = register("moon_stone_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.MOON_STONE, 2, -3.0F, properties).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> MOON_STONE_SWORD = register("moon_stone_sword", properties -> new GenerationsSwordItem(GenerationsTiers.MOON_STONE, 4, -2.4F, properties).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> RUBY_SHOVEL = register("ruby_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.RUBY, 1f, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> RUBY_PICKAXE = register("ruby_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.RUBY, 2, -2.8f, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> RUBY_AXE = register("ruby_axe", properties -> new GenerationsAxeItem(GenerationsTiers.RUBY, 3, -3.1F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> RUBY_HOE = register("ruby_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.RUBY, -2, -1.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> RUBY_HAMMER = register("ruby_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.RUBY, 2.0F, -3.1F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> RUBY_SWORD = register("ruby_sword", properties -> new GenerationsSwordItem(GenerationsTiers.RUBY, 4, -2.4F, properties), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> SAPPHIRE_SHOVEL = register("sapphire_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.SAPPHIRE, 1, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SAPPHIRE_PICKAXE = register("sapphire_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.SAPPHIRE, 2, -2.8F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SAPPHIRE_AXE = register("sapphire_axe", properties -> new GenerationsAxeItem(GenerationsTiers.SAPPHIRE, 3, -3.1F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SAPPHIRE_HOE = register("sapphire_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.SAPPHIRE, -2, -1.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SAPPHIRE_HAMMER = register("sapphire_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.SAPPHIRE, 2.0F, -3.1F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SAPPHIRE_SWORD = register("sapphire_sword", properties -> new GenerationsSwordItem(GenerationsTiers.SAPPHIRE, 4, -2.4F, properties), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> SILICON_SHOVEL = register("silicon_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.SILICON, 0, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SILICON_PICKAXE = register("silicon_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.SILICON, 0, -2.8F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SILICON_AXE = register("silicon_axe", properties -> new GenerationsAxeItem(GenerationsTiers.SILICON, 0, -3.1F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SILICON_HOE = register("silicon_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.SILICON, 0, -1.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SILICON_HAMMER = register("silicon_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.SILICON, 6.0F, -3.1F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SILICON_SWORD = register("silicon_sword", properties -> new GenerationsSwordItem(GenerationsTiers.SILICON, 0, -2.4F, properties), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> SUN_STONE_SHOVEL = register("sun_stone_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.SUN_STONE, 1, -3.0F, properties).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SUN_STONE_PICKAXE = register("sun_stone_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.SUN_STONE, 2, -2.8F, properties).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SUN_STONE_AXE = register("sun_stone_axe", properties -> new GenerationsAxeItem(GenerationsTiers.SUN_STONE, 3, -3.0F, properties).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SUN_STONE_HOE = register("sun_stone_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.SUN_STONE, 0, -3.0F, properties).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SUN_STONE_HAMMER = register("sun_stone_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.SUN_STONE, 2, -3.0F, properties).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> SUN_STONE_SWORD = register("sun_stone_sword", properties -> new GenerationsSwordItem(GenerationsTiers.SUN_STONE, 4, -2.4F, properties).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> THUNDER_STONE_SHOVEL = register("thunder_stone_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.THUNDER_STONE, 1, -3.0F, properties).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> THUNDER_STONE_PICKAXE = register("thunder_stone_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.THUNDER_STONE, 2, -2.8F, properties).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> THUNDER_STONE_AXE = register("thunder_stone_axe", properties -> new GenerationsAxeItem(GenerationsTiers.THUNDER_STONE, 3, -3.0F, properties).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> THUNDER_STONE_HOE = register("thunder_stone_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.THUNDER_STONE, 0, -3.0F, properties).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> THUNDER_STONE_HAMMER = register("thunder_stone_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.THUNDER_STONE, 2, -3.0F, properties).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> THUNDER_STONE_SWORD = register("thunder_stone_sword", properties -> new GenerationsSwordItem(GenerationsTiers.THUNDER_STONE, 4, -2.4F, properties).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)), CreativeModeTabs.COMBAT);

	public static final RegistrySupplier<Item> WATER_STONE_SHOVEL = register("water_stone_shovel", properties -> new GenerationsShovelItem(GenerationsTiers.WATER_STONE, 1, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> WATER_STONE_PICKAXE = register("water_stone_pickaxe", properties -> new GenerationsPickaxeItem(GenerationsTiers.WATER_STONE, 2, -2.8F, properties).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> WATER_STONE_AXE = register("water_stone_axe", properties -> new GenerationsAxeItem(GenerationsTiers.WATER_STONE, 3, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> WATER_STONE_HOE = register("water_stone_hoe", properties -> new GenerationsHoeItem(GenerationsTiers.WATER_STONE, -3, 0.0F, properties).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> WATER_STONE_HAMMER = register("water_stone_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.WATER_STONE, 2, -3.0F, properties).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> WATER_STONE_SWORD = register("water_stone_sword", properties -> new GenerationsSwordItem(GenerationsTiers.WATER_STONE, 4, -2.4F, properties).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)), CreativeModeTabs.COMBAT);


	public static final RegistrySupplier<Item> DIAMOND_HAMMER = register("diamond_hammer", properties -> new GenerationsHammerItem(Tiers.DIAMOND, 5.0F, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> GOLDEN_HAMMER = register("golden_hammer", properties -> new GenerationsHammerItem(Tiers.GOLD, 6.0F, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> IRON_HAMMER = register("iron_hammer", properties -> new GenerationsHammerItem(Tiers.IRON, 6.0F, -3.1F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> NETHERITE_HAMMER = register("netherite_hammer", properties -> new GenerationsHammerItem(Tiers.NETHERITE, 5.0F, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> STONE_HAMMER = register("stone_hammer", properties -> new GenerationsHammerItem(Tiers.STONE, 7.0F, -3.2F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
	public static final RegistrySupplier<Item> WOODEN_HAMMER = register("wooden_hammer", properties -> new GenerationsHammerItem(Tiers.WOOD, 6.0F, -3.2F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);

	private static <T extends Item> RegistrySupplier<T> register(String name, Function<Item.Properties, T> function, CreativeModeTab tab) {
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
		public static ToolSet create(Tier tier, int shovelDamage, int pickaxeDamage, int axeDamage, int hoeDamage, int hammerDamage, int swordDamage, ToolEffect... toolEffects) {
			return new ToolSet(register("_shovel", GenerationsShovelItem::new, tier, shovelDamage, -3.0F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register("_pickaxe", GenerationsPickaxeItem::new, tier, pickaxeDamage, -2.8F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register("_axe", GenerationsAxeItem::new, tier, axeDamage, -3.1F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register("_hoe", GenerationsHoeItem::new, tier, hoeDamage, -1.0F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register("_hammer", GenerationsHammerItem::new, tier, hammerDamage, -3.1F, CreativeModeTabs.TOOLS_AND_UTILITIES, toolEffects),
			register("_sword", GenerationsSwordItem::new, tier, swordDamage, -2.4F, CreativeModeTabs.COMBAT, toolEffects));
		}
		public static <T extends Item & ToolEffectHolder<T>> RegistrySupplier<T> register(String name, ToolSupplier<T> supplier, Tier tier, int attackDamage, float attackSpeed, CreativeModeTab tab, ToolEffect... toolEffects) {
			return GenerationsTools.register(tier.toString().toLowerCase() + name, properties -> supplier.create(tier, attackDamage, attackSpeed, properties).addToolEffects(toolEffects), tab);
		}

		public interface ToolSupplier<T extends Item> {
			public T create(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties);
		}
	}
}
