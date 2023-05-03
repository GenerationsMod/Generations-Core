package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.world.item.armor.PokeModArmorItem;
import com.pokemod.pokemod.world.item.armor.PokeModArmorMaterials;
import com.pokemod.pokemod.world.item.armor.effects.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PokeModArmor {
	public static final DeferredRegister<Item> ARMOR = DeferredRegister.create(ForgeRegistries.ITEMS, PokeMod.MOD_ID);
	/**
	 * Armor Sets
	 */
	public static final RegistryObject<Item> RUNNING_BOOTS = ARMOR.register("running_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.RUNNING, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.75F)).addArmorEffect(new RunningBootsArmorEffect()));
	public static final RegistryObject<Item> OLD_RUNNING_BOOTS = ARMOR.register("old_running_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.OLD_RUNNING, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new UnbreakableArmorEffect()).addArmorEffect(new RepairArmorEffect()));
	public static final RegistryObject<Item> AETHER_HELMET = ARMOR.register("aether_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.AETHER, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> AETHER_CHESTPLATE = ARMOR.register("aether_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.AETHER, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> AETHER_LEGGINGS = ARMOR.register("aether_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.AETHER, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> AETHER_BOOTS = ARMOR.register("aether_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.AETHER, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> AQUA_HELMET = ARMOR.register("aqua_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.AQUA, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> AQUA_CHESTPLATE = ARMOR.register("aqua_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.AQUA, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> AQUA_LEGGINGS = ARMOR.register("aqua_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.AQUA, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> AQUA_BOOTS = ARMOR.register("aqua_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.AQUA, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> FLARE_HELMET = ARMOR.register("flare_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.FLARE, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> FLARE_CHESTPLATE = ARMOR.register("flare_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.FLARE, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> FLARE_LEGGINGS = ARMOR.register("flare_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.FLARE, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> FLARE_BOOTS = ARMOR.register("flare_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.FLARE, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> GALACTIC_HELMET = ARMOR.register("galactic_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.GALACTIC, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> GALACTIC_CHESTPLATE = ARMOR.register("galactic_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.GALACTIC, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> GALACTIC_LEGGINGS = ARMOR.register("galactic_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.GALACTIC, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> GALACTIC_BOOTS = ARMOR.register("galactic_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.GALACTIC, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> MAGMA_HELMET = ARMOR.register("magma_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.MAGMA, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> MAGMA_CHESTPLATE = ARMOR.register("magma_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.MAGMA, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> MAGMA_LEGGINGS = ARMOR.register("magma_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.MAGMA, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> MAGMA_BOOTS = ARMOR.register("magma_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.MAGMA, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> NEO_PLASMA_HELMET = ARMOR.register("neo_plasma_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.NEO_PLASMA, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> NEO_PLASMA_CHESTPLATE = ARMOR.register("neo_plasma_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.NEO_PLASMA, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> NEO_PLASMA_LEGGINGS = ARMOR.register("neo_plasma_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.NEO_PLASMA, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> NEO_PLASMA_BOOTS = ARMOR.register("neo_plasma_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.NEO_PLASMA, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> PLASMA_HELMET = ARMOR.register("plasma_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.PLASMA, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> PLASMA_CHESTPLATE = ARMOR.register("plasma_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.PLASMA, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> PLASMA_LEGGINGS = ARMOR.register("plasma_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.PLASMA, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> PLASMA_BOOTS = ARMOR.register("plasma_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.PLASMA, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> ROCKET_HELMET = ARMOR.register("rocket_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.ROCKET, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> ROCKET_CHESTPLATE = ARMOR.register("rocket_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.ROCKET, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> ROCKET_LEGGINGS = ARMOR.register("rocket_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.ROCKET, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> ROCKET_BOOTS = ARMOR.register("rocket_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.ROCKET, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> SKULL_HELMET = ARMOR.register("skull_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.SKULL, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> SKULL_CHESTPLATE = ARMOR.register("skull_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.SKULL, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> SKULL_LEGGINGS = ARMOR.register("skull_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.SKULL, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> SKULL_BOOTS = ARMOR.register("skull_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.SKULL, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> CRYSTAL_HELMET = ARMOR.register("crystal_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.CRYSTAL, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> CRYSTAL_CHESTPLATE = ARMOR.register("crystal_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.CRYSTAL, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> CRYSTAL_LEGGINGS = ARMOR.register("crystal_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.CRYSTAL, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> CRYSTAL_BOOTS = ARMOR.register("crystal_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.CRYSTAL, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.1F)).addArmorEffect(new PotionArmorEffect(MobEffects.MOVEMENT_SPEED, 0)).addArmorEffect(new PotionArmorEffect(MobEffects.JUMP, 0)));
	public static final RegistryObject<Item> ULTRA_HELMET = ARMOR.register("ultra_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.ULTRA, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> ULTRA_CHESTPLATE = ARMOR.register("ultra_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.ULTRA, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> ULTRA_LEGGINGS = ARMOR.register("ultra_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.ULTRA, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> ULTRA_BOOTS = ARMOR.register("ultra_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.ULTRA, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.25F)).addArmorEffect(new PotionArmorEffect(MobEffects.MOVEMENT_SPEED, 0)));
	public static final RegistryObject<Item> ALUMINUM_HELMET = ARMOR.register("aluminum_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.ALUMINUM, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> ALUMINUM_CHESTPLATE = ARMOR.register("aluminum_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.ALUMINUM, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> ALUMINUM_LEGGINGS = ARMOR.register("aluminum_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.ALUMINUM, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> ALUMINUM_BOOTS = ARMOR.register("aluminum_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.ALUMINUM, EquipmentSlot.FEET, of()));
	public static final RegistryObject<Item> DAWN_STONE_HELMET = ARMOR.register("dawn_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.DAWN_STONE, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> DAWN_STONE_CHESTPLATE = ARMOR.register("dawn_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.DAWN_STONE, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> DAWN_STONE_LEGGINGS = ARMOR.register("dawn_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.DAWN_STONE, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> DAWN_STONE_BOOTS = ARMOR.register("dawn_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.DAWN_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.JUMP, 3)));
	public static final RegistryObject<Item> DUSK_STONE_HELMET = ARMOR.register("dusk_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.DUSK_STONE, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> DUSK_STONE_CHESTPLATE = ARMOR.register("dusk_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.DUSK_STONE, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> DUSK_STONE_LEGGINGS = ARMOR.register("dusk_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.DUSK_STONE, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> DUSK_STONE_BOOTS = ARMOR.register("dusk_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.DUSK_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.SATURATION, 4)));
	public static final RegistryObject<Item> FIRE_STONE_HELMET = ARMOR.register("fire_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.FIRE_STONE, EquipmentSlot.HEAD, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)));
	public static final RegistryObject<Item> FIRE_STONE_CHESTPLATE = ARMOR.register("fire_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.FIRE_STONE, EquipmentSlot.CHEST, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)));
	public static final RegistryObject<Item> FIRE_STONE_LEGGINGS = ARMOR.register("fire_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.FIRE_STONE, EquipmentSlot.LEGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)));
	public static final RegistryObject<Item> FIRE_STONE_BOOTS = ARMOR.register("fire_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.FIRE_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)).addArmorEffect(new PotionArmorEffect(MobEffects.FIRE_RESISTANCE, 0)));
	public static final RegistryObject<Item> ICE_STONE_HELMET = ARMOR.register("ice_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.ICE_STONE, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> ICE_STONE_CHESTPLATE = ARMOR.register("ice_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.ICE_STONE, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> ICE_STONE_LEGGINGS = ARMOR.register("ice_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.ICE_STONE, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> ICE_STONE_BOOTS = ARMOR.register("ice_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.ICE_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FROST_WALKER, 2)));
	public static final RegistryObject<Item> LEAF_STONE_HELMET = ARMOR.register("leaf_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.LEAF_STONE, EquipmentSlot.HEAD, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)));
	public static final RegistryObject<Item> LEAF_STONE_CHESTPLATE = ARMOR.register("leaf_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.LEAF_STONE, EquipmentSlot.CHEST, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)));
	public static final RegistryObject<Item> LEAF_STONE_LEGGINGS = ARMOR.register("leaf_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.LEAF_STONE, EquipmentSlot.LEGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)));
	public static final RegistryObject<Item> LEAF_STONE_BOOTS = ARMOR.register("leaf_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.LEAF_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FALL_PROTECTION, 3)));
	public static final RegistryObject<Item> MOON_STONE_HELMET = ARMOR.register("moon_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.MOON_STONE, EquipmentSlot.HEAD, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)));
	public static final RegistryObject<Item> MOON_STONE_CHESTPLATE = ARMOR.register("moon_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.MOON_STONE, EquipmentSlot.CHEST, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)));
	public static final RegistryObject<Item> MOON_STONE_LEGGINGS = ARMOR.register("moon_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.MOON_STONE, EquipmentSlot.LEGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)));
	public static final RegistryObject<Item> MOON_STONE_BOOTS = ARMOR.register("moon_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.MOON_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)));
	public static final RegistryObject<Item> SUN_STONE_HELMET = ARMOR.register("sun_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.SUN_STONE, EquipmentSlot.HEAD, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)));
	public static final RegistryObject<Item> SUN_STONE_CHESTPLATE = ARMOR.register("sun_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.SUN_STONE, EquipmentSlot.CHEST, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)));
	public static final RegistryObject<Item> SUN_STONE_LEGGINGS = ARMOR.register("sun_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.SUN_STONE, EquipmentSlot.LEGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)));
	public static final RegistryObject<Item> SUN_STONE_BOOTS = ARMOR.register("sun_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.SUN_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)));
	public static final RegistryObject<Item> THUNDER_STONE_HELMET = ARMOR.register("thunder_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.THUNDER_STONE, EquipmentSlot.HEAD, of()));
	public static final RegistryObject<Item> THUNDER_STONE_CHESTPLATE = ARMOR.register("thunder_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.THUNDER_STONE, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> THUNDER_STONE_LEGGINGS = ARMOR.register("thunder_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.THUNDER_STONE, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> THUNDER_STONE_BOOTS = ARMOR.register("thunder_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.THUNDER_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new DoubleSpeedArmorEffect()).addArmorEffect(new PotionArmorEffect(MobEffects.DIG_SPEED, 0)));
	public static final RegistryObject<Item> WATER_STONE_HELMET = ARMOR.register("water_stone_helmet", () -> new PokeModArmorItem(PokeModArmorMaterials.WATER_STONE, EquipmentSlot.HEAD, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.AQUA_AFFINITY, 2)));
	public static final RegistryObject<Item> WATER_STONE_CHESTPLATE = ARMOR.register("water_stone_chestplate", () -> new PokeModArmorItem(PokeModArmorMaterials.WATER_STONE, EquipmentSlot.CHEST, of()));
	public static final RegistryObject<Item> WATER_STONE_LEGGINGS = ARMOR.register("water_stone_leggings", () -> new PokeModArmorItem(PokeModArmorMaterials.WATER_STONE, EquipmentSlot.LEGS, of()));
	public static final RegistryObject<Item> WATER_STONE_BOOTS = ARMOR.register("water_stone_boots", () -> new PokeModArmorItem(PokeModArmorMaterials.WATER_STONE, EquipmentSlot.FEET, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.WATER_BREATHING, 0)));




	public static Item.Properties of() {
		return new Item.Properties();
	}

	public static void onInitialize(IEventBus eventBus) {
		PokeMod.LOGGER.info("Registering PokeMod Armor");
		ARMOR.register(eventBus);
	}
}
