package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.tools.*;
import generations.gg.generations.core.generationscore.world.item.tools.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;

public class GenerationsTools {
	public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

	/**
	 * Tools
	 */
	public static final RegistrySupplier<Item> CHARGE_STONE_SHOVEL = TOOLS.register("charge_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.CHARGE_STONE, -3.0F, of()));
	public static final RegistrySupplier<Item> CHARGE_STONE_PICKAXE = TOOLS.register("charge_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.CHARGE_STONE, -2.8F, of()));
	public static final RegistrySupplier<Item> CHARGE_STONE_AXE = TOOLS.register("charge_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.CHARGE_STONE, -3.1F, of()));
	public static final RegistrySupplier<Item> CHARGE_STONE_HOE = TOOLS.register("charge_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.CHARGE_STONE, -1.0F, of()));
	public static final RegistrySupplier<Item> CHARGE_STONE_HAMMER = TOOLS.register("charge_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.CHARGE_STONE, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> CHARGE_STONE_SWORD = TOOLS.register("charge_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.CHARGE_STONE, -2.4F, of()));

	public static final RegistrySupplier<Item> VOLCANIC_STONE_SHOVEL = TOOLS.register("volcanic_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.VOLCANIC_STONE, -3.0F, of()));
	public static final RegistrySupplier<Item> VOLCANIC_STONE_PICKAXE = TOOLS.register("volcanic_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.VOLCANIC_STONE, -2.8F, of()));
	public static final RegistrySupplier<Item> VOLCANIC_STONE_AXE = TOOLS.register("volcanic_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.VOLCANIC_STONE, -3.1F, of()));
	public static final RegistrySupplier<Item> VOLCANIC_STONE_HOE = TOOLS.register("volcanic_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.VOLCANIC_STONE, -1.0F, of()));
	public static final RegistrySupplier<Item> VOLCANIC_STONE_HAMMER = TOOLS.register("volcanic_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.VOLCANIC_STONE, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> VOLCANIC_STONE_SWORD = TOOLS.register("volcanic_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.VOLCANIC_STONE, -2.4F, of()));

	public static final RegistrySupplier<Item> ALUMINUM_SHOVEL = TOOLS.register("aluminum_shovel", () -> new GenerationsShovelItem(GenerationsTiers.ALUMINUM, -3.0F, of()));
	public static final RegistrySupplier<Item> ALUMINUM_PICKAXE = TOOLS.register("aluminum_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.ALUMINUM, -2.8F, of()));
	public static final RegistrySupplier<Item> ALUMINUM_AXE = TOOLS.register("aluminum_axe", () -> new GenerationsAxeItem(GenerationsTiers.ALUMINUM, -3.1F, of()));
	public static final RegistrySupplier<Item> ALUMINUM_HOE = TOOLS.register("aluminum_hoe", () -> new GenerationsHoeItem(GenerationsTiers.ALUMINUM, -1.0F, of()));
	public static final RegistrySupplier<Item> ALUMINUM_HAMMER = TOOLS.register("aluminum_hammer", () -> new GenerationsHammerItem(GenerationsTiers.ALUMINUM, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> ALUMINUM_SWORD = TOOLS.register("aluminum_sword", () -> new GenerationsSwordItem(GenerationsTiers.ALUMINUM, -2.4F, of()));

	public static final RegistrySupplier<Item> AMETHYST_SHOVEL = TOOLS.register("amethyst_shovel", () -> new GenerationsShovelItem(GenerationsTiers.AMETHYST, -3.0F, of()));
	public static final RegistrySupplier<Item> AMETHYST_PICKAXE = TOOLS.register("amethyst_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.AMETHYST, -2.8F, of()));
	public static final RegistrySupplier<Item> AMETHYST_AXE = TOOLS.register("amethyst_axe", () -> new GenerationsAxeItem(GenerationsTiers.AMETHYST, -3.1F, of()));
	public static final RegistrySupplier<Item> AMETHYST_HOE = TOOLS.register("amethyst_hoe", () -> new GenerationsHoeItem(GenerationsTiers.AMETHYST, -1.0F, of()));
	public static final RegistrySupplier<Item> AMETHYST_HAMMER = TOOLS.register("amethyst_hammer", () -> new GenerationsHammerItem(GenerationsTiers.AMETHYST, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> AMETHYST_SWORD = TOOLS.register("amethyst_sword", () -> new GenerationsSwordItem(GenerationsTiers.AMETHYST, -2.4F, of()));

	public static final RegistrySupplier<Item> CRYSTAL_SHOVEL = TOOLS.register("crystal_shovel", () -> new GenerationsShovelItem(GenerationsTiers.CRYSTAL, -3.0F, of()));
	public static final RegistrySupplier<Item> CRYSTAL_PICKAXE = TOOLS.register("crystal_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.CRYSTAL, -2.8F, of()));
	public static final RegistrySupplier<Item> CRYSTAL_AXE = TOOLS.register("crystal_axe", () -> new GenerationsAxeItem(GenerationsTiers.CRYSTAL, -3.1F, of()));
	public static final RegistrySupplier<Item> CRYSTAL_HOE = TOOLS.register("crystal_hoe", () -> new GenerationsHoeItem(GenerationsTiers.CRYSTAL, -1.0F, of()));
	public static final RegistrySupplier<Item> CRYSTAL_HAMMER = TOOLS.register("crystal_hammer", () -> new GenerationsHammerItem(GenerationsTiers.CRYSTAL, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> CRYSTAL_SWORD = TOOLS.register("crystal_sword", () -> new GenerationsSwordItem(GenerationsTiers.CRYSTAL, -2.4F, of()));

	public static final RegistrySupplier<Item> DAWN_STONE_SHOVEL = TOOLS.register("dawn_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.DAWN_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DAWN_STONE_PICKAXE = TOOLS.register("dawn_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.DAWN_STONE, -2.8F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DAWN_STONE_AXE = TOOLS.register("dawn_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.DAWN_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DAWN_STONE_HOE = TOOLS.register("dawn_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.DAWN_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DAWN_STONE_HAMMER = TOOLS.register("dawn_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.DAWN_STONE, 6.0F, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DAWN_STONE_SWORD = TOOLS.register("dawn_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.DAWN_STONE, -2.4F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));

	public static final RegistrySupplier<Item> DUSK_STONE_SHOVEL = TOOLS.register("dusk_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.DUSK_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DUSK_STONE_PICKAXE = TOOLS.register("dusk_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.DUSK_STONE, -2.8F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DUSK_STONE_AXE = TOOLS.register("dusk_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.DUSK_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DUSK_STONE_HOE = TOOLS.register("dusk_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.DUSK_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DUSK_STONE_HAMMER = TOOLS.register("dusk_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.DUSK_STONE, 6.0F, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistrySupplier<Item> DUSK_STONE_SWORD = TOOLS.register("dusk_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.DUSK_STONE, -2.4F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));

	public static final RegistrySupplier<Item> FIRE_STONE_SHOVEL = TOOLS.register("fire_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.FIRE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> FIRE_STONE_PICKAXE = TOOLS.register("fire_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.FIRE_STONE, -2.8F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> FIRE_STONE_AXE = TOOLS.register("fire_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.FIRE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> FIRE_STONE_HOE = TOOLS.register("fire_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.FIRE_STONE, 0.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> FIRE_STONE_HAMMER = TOOLS.register("fire_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.FIRE_STONE, 5.0F, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> FIRE_STONE_SWORD = TOOLS.register("fire_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.FIRE_STONE, -2.4F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));

	public static final RegistrySupplier<Item> ICE_STONE_SHOVEL = TOOLS.register("ice_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.ICE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistrySupplier<Item> ICE_STONE_PICKAXE = TOOLS.register("ice_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.ICE_STONE, -2.8F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistrySupplier<Item> ICE_STONE_AXE = TOOLS.register("ice_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.ICE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistrySupplier<Item> ICE_STONE_HOE = TOOLS.register("ice_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.ICE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistrySupplier<Item> ICE_STONE_HAMMER = TOOLS.register("ice_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.ICE_STONE, 6.0F, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistrySupplier<Item> ICE_STONE_SWORD = TOOLS.register("ice_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.ICE_STONE, -2.4F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));

	public static final RegistrySupplier<Item> LEAF_STONE_SHOVEL = TOOLS.register("leaf_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.LEAF_STONE, -3.0F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistrySupplier<Item> LEAF_STONE_PICKAXE = TOOLS.register("leaf_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.LEAF_STONE, -2.8F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistrySupplier<Item> LEAF_STONE_AXE = TOOLS.register("leaf_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.LEAF_STONE, -3.1F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistrySupplier<Item> LEAF_STONE_HOE = TOOLS.register("leaf_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.LEAF_STONE, -1.0F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistrySupplier<Item> LEAF_STONE_HAMMER = TOOLS.register("leaf_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.LEAF_STONE, 6.0F, -3.1F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistrySupplier<Item> LEAF_STONE_SWORD = TOOLS.register("leaf_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.LEAF_STONE, -2.4F, of()).addToolEffect(new BoneMealToolEffect(12)));

	public static final RegistrySupplier<Item> MOON_STONE_SHOVEL = TOOLS.register("moon_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.MOON_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistrySupplier<Item> MOON_STONE_PICKAXE = TOOLS.register("moon_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.MOON_STONE, -2.8F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistrySupplier<Item> MOON_STONE_AXE = TOOLS.register("moon_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.MOON_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistrySupplier<Item> MOON_STONE_HOE = TOOLS.register("moon_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.MOON_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistrySupplier<Item> MOON_STONE_HAMMER = TOOLS.register("moon_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.MOON_STONE, 6.0F, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistrySupplier<Item> MOON_STONE_SWORD = TOOLS.register("moon_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.MOON_STONE, -2.4F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));

	public static final RegistrySupplier<Item> RUBY_SHOVEL = TOOLS.register("ruby_shovel", () -> new GenerationsShovelItem(GenerationsTiers.RUBY, -3.0F, of()));
	public static final RegistrySupplier<Item> RUBY_PICKAXE = TOOLS.register("ruby_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.RUBY, -2.8F, of()));
	public static final RegistrySupplier<Item> RUBY_AXE = TOOLS.register("ruby_axe", () -> new GenerationsAxeItem(GenerationsTiers.RUBY, -3.1F, of()));
	public static final RegistrySupplier<Item> RUBY_HOE = TOOLS.register("ruby_hoe", () -> new GenerationsHoeItem(GenerationsTiers.RUBY, -1.0F, of()));
	public static final RegistrySupplier<Item> RUBY_HAMMER = TOOLS.register("ruby_hammer", () -> new GenerationsHammerItem(GenerationsTiers.RUBY, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> RUBY_SWORD = TOOLS.register("ruby_sword", () -> new GenerationsSwordItem(GenerationsTiers.RUBY, -2.4F, of()));

	public static final RegistrySupplier<Item> SAPPHIRE_SHOVEL = TOOLS.register("sapphire_shovel", () -> new GenerationsShovelItem(GenerationsTiers.SAPPHIRE, -3.0F, of()));
	public static final RegistrySupplier<Item> SAPPHIRE_PICKAXE = TOOLS.register("sapphire_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.SAPPHIRE, -2.8F, of()));
	public static final RegistrySupplier<Item> SAPPHIRE_AXE = TOOLS.register("sapphire_axe", () -> new GenerationsAxeItem(GenerationsTiers.SAPPHIRE, -3.1F, of()));
	public static final RegistrySupplier<Item> SAPPHIRE_HOE = TOOLS.register("sapphire_hoe", () -> new GenerationsHoeItem(GenerationsTiers.SAPPHIRE, -1.0F, of()));
	public static final RegistrySupplier<Item> SAPPHIRE_HAMMER = TOOLS.register("sapphire_hammer", () -> new GenerationsHammerItem(GenerationsTiers.SAPPHIRE, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> SAPPHIRE_SWORD = TOOLS.register("sapphire_sword", () -> new GenerationsSwordItem(GenerationsTiers.SAPPHIRE, -2.4F, of()));

	public static final RegistrySupplier<Item> SILICON_SHOVEL = TOOLS.register("silicon_shovel", () -> new GenerationsShovelItem(GenerationsTiers.SILICON, -3.0F, of()));
	public static final RegistrySupplier<Item> SILICON_PICKAXE = TOOLS.register("silicon_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.SILICON, -2.8F, of()));
	public static final RegistrySupplier<Item> SILICON_AXE = TOOLS.register("silicon_axe", () -> new GenerationsAxeItem(GenerationsTiers.SILICON, -3.1F, of()));
	public static final RegistrySupplier<Item> SILICON_HOE = TOOLS.register("silicon_hoe", () -> new GenerationsHoeItem(GenerationsTiers.SILICON, -1.0F, of()));
	public static final RegistrySupplier<Item> SILICON_HAMMER = TOOLS.register("silicon_hammer", () -> new GenerationsHammerItem(GenerationsTiers.SILICON, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> SILICON_SWORD = TOOLS.register("silicon_sword", () -> new GenerationsSwordItem(GenerationsTiers.SILICON, -2.4F, of()));

	public static final RegistrySupplier<Item> SUN_STONE_SHOVEL = TOOLS.register("sun_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.SUN_STONE, -3.0F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistrySupplier<Item> SUN_STONE_PICKAXE = TOOLS.register("sun_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.SUN_STONE, -2.8F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistrySupplier<Item> SUN_STONE_AXE = TOOLS.register("sun_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.SUN_STONE, -3.0F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistrySupplier<Item> SUN_STONE_HOE = TOOLS.register("sun_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.SUN_STONE, -3.0F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistrySupplier<Item> SUN_STONE_HAMMER = TOOLS.register("sun_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.SUN_STONE, 6.0F, -3.0F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistrySupplier<Item> SUN_STONE_SWORD = TOOLS.register("sun_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.SUN_STONE, -2.4F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));

	public static final RegistrySupplier<Item> THUNDER_STONE_SHOVEL = TOOLS.register("thunder_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.THUNDER_STONE, -3.0F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistrySupplier<Item> THUNDER_STONE_PICKAXE = TOOLS.register("thunder_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.THUNDER_STONE, -2.8F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistrySupplier<Item> THUNDER_STONE_AXE = TOOLS.register("thunder_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.THUNDER_STONE, -3.0F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistrySupplier<Item> THUNDER_STONE_HOE = TOOLS.register("thunder_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.THUNDER_STONE, -3.0F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistrySupplier<Item> THUNDER_STONE_HAMMER = TOOLS.register("thunder_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.THUNDER_STONE, 6.0F, -3.0F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistrySupplier<Item> THUNDER_STONE_SWORD = TOOLS.register("thunder_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.THUNDER_STONE, -2.4F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));

	public static final RegistrySupplier<Item> WATER_STONE_SHOVEL = TOOLS.register("water_stone_shovel", () -> new GenerationsShovelItem(GenerationsTiers.WATER_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> WATER_STONE_PICKAXE = TOOLS.register("water_stone_pickaxe", () -> new GenerationsPickaxeItem(GenerationsTiers.WATER_STONE, -2.8F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> WATER_STONE_AXE = TOOLS.register("water_stone_axe", () -> new GenerationsAxeItem(GenerationsTiers.WATER_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> WATER_STONE_HOE = TOOLS.register("water_stone_hoe", () -> new GenerationsHoeItem(GenerationsTiers.WATER_STONE, 0.0F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> WATER_STONE_HAMMER = TOOLS.register("water_stone_hammer", () -> new GenerationsHammerItem(GenerationsTiers.WATER_STONE, 5.0F, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistrySupplier<Item> WATER_STONE_SWORD = TOOLS.register("water_stone_sword", () -> new GenerationsSwordItem(GenerationsTiers.WATER_STONE, -2.4F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));


	public static final RegistrySupplier<Item> DIAMOND_HAMMER = TOOLS.register("diamond_hammer", () -> new GenerationsHammerItem(Tiers.DIAMOND, 5.0F, -3.0F, of()));
	public static final RegistrySupplier<Item> GOLDEN_HAMMER = TOOLS.register("golden_hammer", () -> new GenerationsHammerItem(Tiers.GOLD, 6.0F, -3.0F, of()));
	public static final RegistrySupplier<Item> IRON_HAMMER = TOOLS.register("iron_hammer", () -> new GenerationsHammerItem(Tiers.IRON, 6.0F, -3.1F, of()));
	public static final RegistrySupplier<Item> NETHERITE_HAMMER = TOOLS.register("netherite_hammer", () -> new GenerationsHammerItem(Tiers.NETHERITE, 5.0F, -3.0F, of()));
	public static final RegistrySupplier<Item> STONE_HAMMER = TOOLS.register("stone_hammer", () -> new GenerationsHammerItem(Tiers.STONE, 7.0F, -3.2F, of()));
	public static final RegistrySupplier<Item> WOODEN_HAMMER = TOOLS.register("wooden_hammer", () -> new GenerationsHammerItem(Tiers.WOOD, 6.0F, -3.2F, of()));



	public static Item.Properties of() {
		return new Item.Properties();
	}

	public static void onInitialize() {
		GenerationsCore.LOGGER.info("Registering PokeMod Tools");
		TOOLS.register();
	}
}
