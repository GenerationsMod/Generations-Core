package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.world.item.tools.*;
import com.pokemod.pokemod.world.item.tools.effects.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PokeModTools {
	public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(ForgeRegistries.ITEMS, PokeMod.MOD_ID);

	/**
	 * Tools
	 */
	public static final RegistryObject<Item> CHARGE_STONE_SHOVEL = TOOLS.register("charge_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.CHARGE_STONE, -3.0F, of()));
	public static final RegistryObject<Item> CHARGE_STONE_PICKAXE = TOOLS.register("charge_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.CHARGE_STONE, -2.8F, of()));
	public static final RegistryObject<Item> CHARGE_STONE_AXE = TOOLS.register("charge_stone_axe", () -> new PokeModAxeItem(PokeModTiers.CHARGE_STONE, -3.1F, of()));
	public static final RegistryObject<Item> CHARGE_STONE_HOE = TOOLS.register("charge_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.CHARGE_STONE, -1.0F, of()));
	public static final RegistryObject<Item> CHARGE_STONE_HAMMER = TOOLS.register("charge_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.CHARGE_STONE, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> CHARGE_STONE_SWORD = TOOLS.register("charge_stone_sword", () -> new PokeModSwordItem(PokeModTiers.CHARGE_STONE, -2.4F, of()));

	public static final RegistryObject<Item> VOLCANIC_STONE_SHOVEL = TOOLS.register("volcanic_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.VOLCANIC_STONE, -3.0F, of()));
	public static final RegistryObject<Item> VOLCANIC_STONE_PICKAXE = TOOLS.register("volcanic_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.VOLCANIC_STONE, -2.8F, of()));
	public static final RegistryObject<Item> VOLCANIC_STONE_AXE = TOOLS.register("volcanic_stone_axe", () -> new PokeModAxeItem(PokeModTiers.VOLCANIC_STONE, -3.1F, of()));
	public static final RegistryObject<Item> VOLCANIC_STONE_HOE = TOOLS.register("volcanic_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.VOLCANIC_STONE, -1.0F, of()));
	public static final RegistryObject<Item> VOLCANIC_STONE_HAMMER = TOOLS.register("volcanic_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.VOLCANIC_STONE, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> VOLCANIC_STONE_SWORD = TOOLS.register("volcanic_stone_sword", () -> new PokeModSwordItem(PokeModTiers.VOLCANIC_STONE, -2.4F, of()));

	public static final RegistryObject<Item> ALUMINUM_SHOVEL = TOOLS.register("aluminum_shovel", () -> new PokeModShovelItem(PokeModTiers.ALUMINUM, -3.0F, of()));
	public static final RegistryObject<Item> ALUMINUM_PICKAXE = TOOLS.register("aluminum_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.ALUMINUM, -2.8F, of()));
	public static final RegistryObject<Item> ALUMINUM_AXE = TOOLS.register("aluminum_axe", () -> new PokeModAxeItem(PokeModTiers.ALUMINUM, -3.1F, of()));
	public static final RegistryObject<Item> ALUMINUM_HOE = TOOLS.register("aluminum_hoe", () -> new PokeModHoeItem(PokeModTiers.ALUMINUM, -1.0F, of()));
	public static final RegistryObject<Item> ALUMINUM_HAMMER = TOOLS.register("aluminum_hammer", () -> new PokeModHammerItem(PokeModTiers.ALUMINUM, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> ALUMINUM_SWORD = TOOLS.register("aluminum_sword", () -> new PokeModSwordItem(PokeModTiers.ALUMINUM, -2.4F, of()));

	public static final RegistryObject<Item> AMETHYST_SHOVEL = TOOLS.register("amethyst_shovel", () -> new PokeModShovelItem(PokeModTiers.AMETHYST, -3.0F, of()));
	public static final RegistryObject<Item> AMETHYST_PICKAXE = TOOLS.register("amethyst_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.AMETHYST, -2.8F, of()));
	public static final RegistryObject<Item> AMETHYST_AXE = TOOLS.register("amethyst_axe", () -> new PokeModAxeItem(PokeModTiers.AMETHYST, -3.1F, of()));
	public static final RegistryObject<Item> AMETHYST_HOE = TOOLS.register("amethyst_hoe", () -> new PokeModHoeItem(PokeModTiers.AMETHYST, -1.0F, of()));
	public static final RegistryObject<Item> AMETHYST_HAMMER = TOOLS.register("amethyst_hammer", () -> new PokeModHammerItem(PokeModTiers.AMETHYST, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> AMETHYST_SWORD = TOOLS.register("amethyst_sword", () -> new PokeModSwordItem(PokeModTiers.AMETHYST, -2.4F, of()));

	public static final RegistryObject<Item> CRYSTAL_SHOVEL = TOOLS.register("crystal_shovel", () -> new PokeModShovelItem(PokeModTiers.CRYSTAL, -3.0F, of()));
	public static final RegistryObject<Item> CRYSTAL_PICKAXE = TOOLS.register("crystal_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.CRYSTAL, -2.8F, of()));
	public static final RegistryObject<Item> CRYSTAL_AXE = TOOLS.register("crystal_axe", () -> new PokeModAxeItem(PokeModTiers.CRYSTAL, -3.1F, of()));
	public static final RegistryObject<Item> CRYSTAL_HOE = TOOLS.register("crystal_hoe", () -> new PokeModHoeItem(PokeModTiers.CRYSTAL, -1.0F, of()));
	public static final RegistryObject<Item> CRYSTAL_HAMMER = TOOLS.register("crystal_hammer", () -> new PokeModHammerItem(PokeModTiers.CRYSTAL, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> CRYSTAL_SWORD = TOOLS.register("crystal_sword", () -> new PokeModSwordItem(PokeModTiers.CRYSTAL, -2.4F, of()));

	public static final RegistryObject<Item> DAWN_STONE_SHOVEL = TOOLS.register("dawn_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.DAWN_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistryObject<Item> DAWN_STONE_PICKAXE = TOOLS.register("dawn_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.DAWN_STONE, -2.8F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistryObject<Item> DAWN_STONE_AXE = TOOLS.register("dawn_stone_axe", () -> new PokeModAxeItem(PokeModTiers.DAWN_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistryObject<Item> DAWN_STONE_HOE = TOOLS.register("dawn_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.DAWN_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistryObject<Item> DAWN_STONE_HAMMER = TOOLS.register("dawn_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.DAWN_STONE, 6.0F, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));
	public static final RegistryObject<Item> DAWN_STONE_SWORD = TOOLS.register("dawn_stone_sword", () -> new PokeModSwordItem(PokeModTiers.DAWN_STONE, -2.4F, of()).addToolEffect(new PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1)));

	public static final RegistryObject<Item> DUSK_STONE_SHOVEL = TOOLS.register("dusk_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.DUSK_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistryObject<Item> DUSK_STONE_PICKAXE = TOOLS.register("dusk_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.DUSK_STONE, -2.8F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistryObject<Item> DUSK_STONE_AXE = TOOLS.register("dusk_stone_axe", () -> new PokeModAxeItem(PokeModTiers.DUSK_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistryObject<Item> DUSK_STONE_HOE = TOOLS.register("dusk_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.DUSK_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistryObject<Item> DUSK_STONE_HAMMER = TOOLS.register("dusk_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.DUSK_STONE, 6.0F, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));
	public static final RegistryObject<Item> DUSK_STONE_SWORD = TOOLS.register("dusk_stone_sword", () -> new PokeModSwordItem(PokeModTiers.DUSK_STONE, -2.4F, of()).addToolEffect(new PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1)));

	public static final RegistryObject<Item> FIRE_STONE_SHOVEL = TOOLS.register("fire_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.FIRE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> FIRE_STONE_PICKAXE = TOOLS.register("fire_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.FIRE_STONE, -2.8F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> FIRE_STONE_AXE = TOOLS.register("fire_stone_axe", () -> new PokeModAxeItem(PokeModTiers.FIRE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> FIRE_STONE_HOE = TOOLS.register("fire_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.FIRE_STONE, 0.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> FIRE_STONE_HAMMER = TOOLS.register("fire_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.FIRE_STONE, 5.0F, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> FIRE_STONE_SWORD = TOOLS.register("fire_stone_sword", () -> new PokeModSwordItem(PokeModTiers.FIRE_STONE, -2.4F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1)));

	public static final RegistryObject<Item> ICE_STONE_SHOVEL = TOOLS.register("ice_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.ICE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistryObject<Item> ICE_STONE_PICKAXE = TOOLS.register("ice_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.ICE_STONE, -2.8F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistryObject<Item> ICE_STONE_AXE = TOOLS.register("ice_stone_axe", () -> new PokeModAxeItem(PokeModTiers.ICE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistryObject<Item> ICE_STONE_HOE = TOOLS.register("ice_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.ICE_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistryObject<Item> ICE_STONE_HAMMER = TOOLS.register("ice_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.ICE_STONE, 6.0F, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));
	public static final RegistryObject<Item> ICE_STONE_SWORD = TOOLS.register("ice_stone_sword", () -> new PokeModSwordItem(PokeModTiers.ICE_STONE, -2.4F, of()).addToolEffect(new TransformToolEffect(Blocks.WATER, Blocks.ICE, 1)).addToolEffect(new TransformToolEffect(Blocks.ICE, Blocks.PACKED_ICE, 3)).addToolEffect(new TransformToolEffect(Blocks.PACKED_ICE, Blocks.BLUE_ICE, 9)));

	public static final RegistryObject<Item> LEAF_STONE_SHOVEL = TOOLS.register("leaf_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.LEAF_STONE, -3.0F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistryObject<Item> LEAF_STONE_PICKAXE = TOOLS.register("leaf_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.LEAF_STONE, -2.8F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistryObject<Item> LEAF_STONE_AXE = TOOLS.register("leaf_stone_axe", () -> new PokeModAxeItem(PokeModTiers.LEAF_STONE, -3.1F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistryObject<Item> LEAF_STONE_HOE = TOOLS.register("leaf_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.LEAF_STONE, -1.0F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistryObject<Item> LEAF_STONE_HAMMER = TOOLS.register("leaf_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.LEAF_STONE, 6.0F, -3.1F, of()).addToolEffect(new BoneMealToolEffect(12)));
	public static final RegistryObject<Item> LEAF_STONE_SWORD = TOOLS.register("leaf_stone_sword", () -> new PokeModSwordItem(PokeModTiers.LEAF_STONE, -2.4F, of()).addToolEffect(new BoneMealToolEffect(12)));

	public static final RegistryObject<Item> MOON_STONE_SHOVEL = TOOLS.register("moon_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.MOON_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistryObject<Item> MOON_STONE_PICKAXE = TOOLS.register("moon_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.MOON_STONE, -2.8F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistryObject<Item> MOON_STONE_AXE = TOOLS.register("moon_stone_axe", () -> new PokeModAxeItem(PokeModTiers.MOON_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistryObject<Item> MOON_STONE_HOE = TOOLS.register("moon_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.MOON_STONE, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistryObject<Item> MOON_STONE_HAMMER = TOOLS.register("moon_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.MOON_STONE, 6.0F, -3.0F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));
	public static final RegistryObject<Item> MOON_STONE_SWORD = TOOLS.register("moon_stone_sword", () -> new PokeModSwordItem(PokeModTiers.MOON_STONE, -2.4F, of()).addToolEffect(new PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1)));

	public static final RegistryObject<Item> RUBY_SHOVEL = TOOLS.register("ruby_shovel", () -> new PokeModShovelItem(PokeModTiers.RUBY, -3.0F, of()));
	public static final RegistryObject<Item> RUBY_PICKAXE = TOOLS.register("ruby_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.RUBY, -2.8F, of()));
	public static final RegistryObject<Item> RUBY_AXE = TOOLS.register("ruby_axe", () -> new PokeModAxeItem(PokeModTiers.RUBY, -3.1F, of()));
	public static final RegistryObject<Item> RUBY_HOE = TOOLS.register("ruby_hoe", () -> new PokeModHoeItem(PokeModTiers.RUBY, -1.0F, of()));
	public static final RegistryObject<Item> RUBY_HAMMER = TOOLS.register("ruby_hammer", () -> new PokeModHammerItem(PokeModTiers.RUBY, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> RUBY_SWORD = TOOLS.register("ruby_sword", () -> new PokeModSwordItem(PokeModTiers.RUBY, -2.4F, of()));

	public static final RegistryObject<Item> SAPPHIRE_SHOVEL = TOOLS.register("sapphire_shovel", () -> new PokeModShovelItem(PokeModTiers.SAPPHIRE, -3.0F, of()));
	public static final RegistryObject<Item> SAPPHIRE_PICKAXE = TOOLS.register("sapphire_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.SAPPHIRE, -2.8F, of()));
	public static final RegistryObject<Item> SAPPHIRE_AXE = TOOLS.register("sapphire_axe", () -> new PokeModAxeItem(PokeModTiers.SAPPHIRE, -3.1F, of()));
	public static final RegistryObject<Item> SAPPHIRE_HOE = TOOLS.register("sapphire_hoe", () -> new PokeModHoeItem(PokeModTiers.SAPPHIRE, -1.0F, of()));
	public static final RegistryObject<Item> SAPPHIRE_HAMMER = TOOLS.register("sapphire_hammer", () -> new PokeModHammerItem(PokeModTiers.SAPPHIRE, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> SAPPHIRE_SWORD = TOOLS.register("sapphire_sword", () -> new PokeModSwordItem(PokeModTiers.SAPPHIRE, -2.4F, of()));

	public static final RegistryObject<Item> SILICON_SHOVEL = TOOLS.register("silicon_shovel", () -> new PokeModShovelItem(PokeModTiers.SILICON, -3.0F, of()));
	public static final RegistryObject<Item> SILICON_PICKAXE = TOOLS.register("silicon_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.SILICON, -2.8F, of()));
	public static final RegistryObject<Item> SILICON_AXE = TOOLS.register("silicon_axe", () -> new PokeModAxeItem(PokeModTiers.SILICON, -3.1F, of()));
	public static final RegistryObject<Item> SILICON_HOE = TOOLS.register("silicon_hoe", () -> new PokeModHoeItem(PokeModTiers.SILICON, -1.0F, of()));
	public static final RegistryObject<Item> SILICON_HAMMER = TOOLS.register("silicon_hammer", () -> new PokeModHammerItem(PokeModTiers.SILICON, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> SILICON_SWORD = TOOLS.register("silicon_sword", () -> new PokeModSwordItem(PokeModTiers.SILICON, -2.4F, of()));

	public static final RegistryObject<Item> SUN_STONE_SHOVEL = TOOLS.register("sun_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.SUN_STONE, -3.0F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistryObject<Item> SUN_STONE_PICKAXE = TOOLS.register("sun_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.SUN_STONE, -2.8F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistryObject<Item> SUN_STONE_AXE = TOOLS.register("sun_stone_axe", () -> new PokeModAxeItem(PokeModTiers.SUN_STONE, -3.0F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistryObject<Item> SUN_STONE_HOE = TOOLS.register("sun_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.SUN_STONE, -3.0F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistryObject<Item> SUN_STONE_HAMMER = TOOLS.register("sun_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.SUN_STONE, 6.0F, -3.0F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));
	public static final RegistryObject<Item> SUN_STONE_SWORD = TOOLS.register("sun_stone_sword", () -> new PokeModSwordItem(PokeModTiers.SUN_STONE, -2.4F, of()).addToolEffect(new PlaceItemToolEffect((BlockItem) Items.TORCH, 5)));

	public static final RegistryObject<Item> THUNDER_STONE_SHOVEL = TOOLS.register("thunder_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.THUNDER_STONE, -3.0F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistryObject<Item> THUNDER_STONE_PICKAXE = TOOLS.register("thunder_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.THUNDER_STONE, -2.8F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistryObject<Item> THUNDER_STONE_AXE = TOOLS.register("thunder_stone_axe", () -> new PokeModAxeItem(PokeModTiers.THUNDER_STONE, -3.0F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistryObject<Item> THUNDER_STONE_HOE = TOOLS.register("thunder_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.THUNDER_STONE, -3.0F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistryObject<Item> THUNDER_STONE_HAMMER = TOOLS.register("thunder_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.THUNDER_STONE, 6.0F, -3.0F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));
	public static final RegistryObject<Item> THUNDER_STONE_SWORD = TOOLS.register("thunder_stone_sword", () -> new PokeModSwordItem(PokeModTiers.THUNDER_STONE, -2.4F, of()).addToolEffect(new EnchantmentToolEffect(Enchantments.BLOCK_EFFICIENCY, 3, 1)));

	public static final RegistryObject<Item> WATER_STONE_SHOVEL = TOOLS.register("water_stone_shovel", () -> new PokeModShovelItem(PokeModTiers.WATER_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> WATER_STONE_PICKAXE = TOOLS.register("water_stone_pickaxe", () -> new PokeModPickaxeItem(PokeModTiers.WATER_STONE, -2.8F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> WATER_STONE_AXE = TOOLS.register("water_stone_axe", () -> new PokeModAxeItem(PokeModTiers.WATER_STONE, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> WATER_STONE_HOE = TOOLS.register("water_stone_hoe", () -> new PokeModHoeItem(PokeModTiers.WATER_STONE, 0.0F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> WATER_STONE_HAMMER = TOOLS.register("water_stone_hammer", () -> new PokeModHammerItem(PokeModTiers.WATER_STONE, 5.0F, -3.0F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));
	public static final RegistryObject<Item> WATER_STONE_SWORD = TOOLS.register("water_stone_sword", () -> new PokeModSwordItem(PokeModTiers.WATER_STONE, -2.4F, of()).addToolEffect(new TransformToolEffect(Blocks.LAVA, Blocks.OBSIDIAN, 1)));


	public static final RegistryObject<Item> DIAMOND_HAMMER = TOOLS.register("diamond_hammer", () -> new PokeModHammerItem(Tiers.DIAMOND, 5.0F, -3.0F, of()));
	public static final RegistryObject<Item> GOLDEN_HAMMER = TOOLS.register("golden_hammer", () -> new PokeModHammerItem(Tiers.GOLD, 6.0F, -3.0F, of()));
	public static final RegistryObject<Item> IRON_HAMMER = TOOLS.register("iron_hammer", () -> new PokeModHammerItem(Tiers.IRON, 6.0F, -3.1F, of()));
	public static final RegistryObject<Item> NETHERITE_HAMMER = TOOLS.register("netherite_hammer", () -> new PokeModHammerItem(Tiers.NETHERITE, 5.0F, -3.0F, of()));
	public static final RegistryObject<Item> STONE_HAMMER = TOOLS.register("stone_hammer", () -> new PokeModHammerItem(Tiers.STONE, 7.0F, -3.2F, of()));
	public static final RegistryObject<Item> WOODEN_HAMMER = TOOLS.register("wooden_hammer", () -> new PokeModHammerItem(Tiers.WOOD, 6.0F, -3.2F, of()));



	public static Item.Properties of() {
		return new Item.Properties();
	}

	public static void onInitialize(IEventBus eventBus) {
		PokeMod.LOGGER.info("Registering PokeMod Tools");
		TOOLS.register(eventBus);
	}
}
