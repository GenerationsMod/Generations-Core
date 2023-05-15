package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.armor.GenerationsArmorItem;
import generations.gg.generations.core.generationscore.world.item.armor.GenerationsArmorMaterials;
import generations.gg.generations.core.generationscore.world.item.armor.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;

public class GenerationsArmor {
	public static final dev.architectury.registry.registries.DeferredRegister<Item> ARMOR = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);
	/**
	 * Armor Sets
	 */
	public static final RegistrySupplier<Item> RUNNING_BOOTS = ARMOR.register("running_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.RUNNING, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.75F)).addArmorEffect(new RunningBootsArmorEffect()));
	public static final RegistrySupplier<Item> OLD_RUNNING_BOOTS = ARMOR.register("old_running_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.OLD_RUNNING, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new UnbreakableArmorEffect()).addArmorEffect(new RepairArmorEffect()));
	public static final RegistrySupplier<Item> AETHER_HELMET = ARMOR.register("aether_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.AETHER, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> AETHER_CHESTPLATE = ARMOR.register("aether_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.AETHER, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> AETHER_LEGGINGS = ARMOR.register("aether_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.AETHER, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> AETHER_BOOTS = ARMOR.register("aether_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.AETHER, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> AQUA_HELMET = ARMOR.register("aqua_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.AQUA, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> AQUA_CHESTPLATE = ARMOR.register("aqua_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.AQUA, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> AQUA_LEGGINGS = ARMOR.register("aqua_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.AQUA, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> AQUA_BOOTS = ARMOR.register("aqua_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.AQUA, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> FLARE_HELMET = ARMOR.register("flare_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.FLARE, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> FLARE_CHESTPLATE = ARMOR.register("flare_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.FLARE, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> FLARE_LEGGINGS = ARMOR.register("flare_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.FLARE, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> FLARE_BOOTS = ARMOR.register("flare_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.FLARE, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> GALACTIC_HELMET = ARMOR.register("galactic_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.GALACTIC, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> GALACTIC_CHESTPLATE = ARMOR.register("galactic_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.GALACTIC, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> GALACTIC_LEGGINGS = ARMOR.register("galactic_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.GALACTIC, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> GALACTIC_BOOTS = ARMOR.register("galactic_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.GALACTIC, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> MAGMA_HELMET = ARMOR.register("magma_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.MAGMA, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> MAGMA_CHESTPLATE = ARMOR.register("magma_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.MAGMA, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> MAGMA_LEGGINGS = ARMOR.register("magma_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.MAGMA, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> MAGMA_BOOTS = ARMOR.register("magma_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.MAGMA, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> NEO_PLASMA_HELMET = ARMOR.register("neo_plasma_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.NEO_PLASMA, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> NEO_PLASMA_CHESTPLATE = ARMOR.register("neo_plasma_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.NEO_PLASMA, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> NEO_PLASMA_LEGGINGS = ARMOR.register("neo_plasma_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.NEO_PLASMA, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> NEO_PLASMA_BOOTS = ARMOR.register("neo_plasma_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.NEO_PLASMA, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> PLASMA_HELMET = ARMOR.register("plasma_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.PLASMA, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> PLASMA_CHESTPLATE = ARMOR.register("plasma_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.PLASMA, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> PLASMA_LEGGINGS = ARMOR.register("plasma_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.PLASMA, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> PLASMA_BOOTS = ARMOR.register("plasma_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.PLASMA, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> ROCKET_HELMET = ARMOR.register("rocket_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ROCKET, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> ROCKET_CHESTPLATE = ARMOR.register("rocket_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ROCKET, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> ROCKET_LEGGINGS = ARMOR.register("rocket_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ROCKET, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> ROCKET_BOOTS = ARMOR.register("rocket_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ROCKET, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> SKULL_HELMET = ARMOR.register("skull_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.SKULL, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> SKULL_CHESTPLATE = ARMOR.register("skull_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.SKULL, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> SKULL_LEGGINGS = ARMOR.register("skull_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.SKULL, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> SKULL_BOOTS = ARMOR.register("skull_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.SKULL, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> CRYSTAL_HELMET = ARMOR.register("crystal_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.CRYSTAL, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> CRYSTAL_CHESTPLATE = ARMOR.register("crystal_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.CRYSTAL, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> CRYSTAL_LEGGINGS = ARMOR.register("crystal_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.CRYSTAL, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> CRYSTAL_BOOTS = ARMOR.register("crystal_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.CRYSTAL, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.1F)).addArmorEffect(new PotionArmorEffect(MobEffects.MOVEMENT_SPEED, 0)).addArmorEffect(new PotionArmorEffect(MobEffects.JUMP, 0)));
	public static final RegistrySupplier<Item> ULTRA_HELMET = ARMOR.register("ultra_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ULTRA, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> ULTRA_CHESTPLATE = ARMOR.register("ultra_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ULTRA, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> ULTRA_LEGGINGS = ARMOR.register("ultra_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ULTRA, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> ULTRA_BOOTS = ARMOR.register("ultra_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ULTRA, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.25F)).addArmorEffect(new PotionArmorEffect(MobEffects.MOVEMENT_SPEED, 0)));
	public static final RegistrySupplier<Item> ALUMINUM_HELMET = ARMOR.register("aluminum_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ALUMINUM, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> ALUMINUM_CHESTPLATE = ARMOR.register("aluminum_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ALUMINUM, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> ALUMINUM_LEGGINGS = ARMOR.register("aluminum_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ALUMINUM, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> ALUMINUM_BOOTS = ARMOR.register("aluminum_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ALUMINUM, ArmorItem.Type.BOOTS, of()));
	public static final RegistrySupplier<Item> DAWN_STONE_HELMET = ARMOR.register("dawn_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.DAWN_STONE, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> DAWN_STONE_CHESTPLATE = ARMOR.register("dawn_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.DAWN_STONE, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> DAWN_STONE_LEGGINGS = ARMOR.register("dawn_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.DAWN_STONE, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> DAWN_STONE_BOOTS = ARMOR.register("dawn_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.DAWN_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.JUMP, 3)));
	public static final RegistrySupplier<Item> DUSK_STONE_HELMET = ARMOR.register("dusk_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.DUSK_STONE, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> DUSK_STONE_CHESTPLATE = ARMOR.register("dusk_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.DUSK_STONE, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> DUSK_STONE_LEGGINGS = ARMOR.register("dusk_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.DUSK_STONE, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> DUSK_STONE_BOOTS = ARMOR.register("dusk_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.DUSK_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.SATURATION, 4)));
	public static final RegistrySupplier<Item> FIRE_STONE_HELMET = ARMOR.register("fire_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.FIRE_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)));
	public static final RegistrySupplier<Item> FIRE_STONE_CHESTPLATE = ARMOR.register("fire_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.FIRE_STONE, ArmorItem.Type.CHESTPLATE, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)));
	public static final RegistrySupplier<Item> FIRE_STONE_LEGGINGS = ARMOR.register("fire_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.FIRE_STONE, ArmorItem.Type.LEGGINGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)));
	public static final RegistrySupplier<Item> FIRE_STONE_BOOTS = ARMOR.register("fire_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.FIRE_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)).addArmorEffect(new PotionArmorEffect(MobEffects.FIRE_RESISTANCE, 0)));
	public static final RegistrySupplier<Item> ICE_STONE_HELMET = ARMOR.register("ice_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ICE_STONE, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> ICE_STONE_CHESTPLATE = ARMOR.register("ice_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ICE_STONE, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> ICE_STONE_LEGGINGS = ARMOR.register("ice_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ICE_STONE, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> ICE_STONE_BOOTS = ARMOR.register("ice_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.ICE_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FROST_WALKER, 2)));
	public static final RegistrySupplier<Item> LEAF_STONE_HELMET = ARMOR.register("leaf_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.LEAF_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)));
	public static final RegistrySupplier<Item> LEAF_STONE_CHESTPLATE = ARMOR.register("leaf_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.LEAF_STONE, ArmorItem.Type.CHESTPLATE, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)));
	public static final RegistrySupplier<Item> LEAF_STONE_LEGGINGS = ARMOR.register("leaf_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.LEAF_STONE, ArmorItem.Type.LEGGINGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)));
	public static final RegistrySupplier<Item> LEAF_STONE_BOOTS = ARMOR.register("leaf_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.LEAF_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FALL_PROTECTION, 3)));
	public static final RegistrySupplier<Item> MOON_STONE_HELMET = ARMOR.register("moon_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.MOON_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)));
	public static final RegistrySupplier<Item> MOON_STONE_CHESTPLATE = ARMOR.register("moon_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.MOON_STONE, ArmorItem.Type.CHESTPLATE, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)));
	public static final RegistrySupplier<Item> MOON_STONE_LEGGINGS = ARMOR.register("moon_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.MOON_STONE, ArmorItem.Type.LEGGINGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)));
	public static final RegistrySupplier<Item> MOON_STONE_BOOTS = ARMOR.register("moon_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.MOON_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)));
	public static final RegistrySupplier<Item> SUN_STONE_HELMET = ARMOR.register("sun_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.SUN_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)));
	public static final RegistrySupplier<Item> SUN_STONE_CHESTPLATE = ARMOR.register("sun_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.SUN_STONE, ArmorItem.Type.CHESTPLATE, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)));
	public static final RegistrySupplier<Item> SUN_STONE_LEGGINGS = ARMOR.register("sun_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.SUN_STONE, ArmorItem.Type.LEGGINGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)));
	public static final RegistrySupplier<Item> SUN_STONE_BOOTS = ARMOR.register("sun_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.SUN_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)));
	public static final RegistrySupplier<Item> THUNDER_STONE_HELMET = ARMOR.register("thunder_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.THUNDER_STONE, ArmorItem.Type.HELMET, of()));
	public static final RegistrySupplier<Item> THUNDER_STONE_CHESTPLATE = ARMOR.register("thunder_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.THUNDER_STONE, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> THUNDER_STONE_LEGGINGS = ARMOR.register("thunder_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.THUNDER_STONE, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> THUNDER_STONE_BOOTS = ARMOR.register("thunder_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.THUNDER_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new DoubleSpeedArmorEffect()).addArmorEffect(new PotionArmorEffect(MobEffects.DIG_SPEED, 0)));
	public static final RegistrySupplier<Item> WATER_STONE_HELMET = ARMOR.register("water_stone_helmet", () -> new GenerationsArmorItem(GenerationsArmorMaterials.WATER_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.AQUA_AFFINITY, 2)));
	public static final RegistrySupplier<Item> WATER_STONE_CHESTPLATE = ARMOR.register("water_stone_chestplate", () -> new GenerationsArmorItem(GenerationsArmorMaterials.WATER_STONE, ArmorItem.Type.CHESTPLATE, of()));
	public static final RegistrySupplier<Item> WATER_STONE_LEGGINGS = ARMOR.register("water_stone_leggings", () -> new GenerationsArmorItem(GenerationsArmorMaterials.WATER_STONE, ArmorItem.Type.LEGGINGS, of()));
	public static final RegistrySupplier<Item> WATER_STONE_BOOTS = ARMOR.register("water_stone_boots", () -> new GenerationsArmorItem(GenerationsArmorMaterials.WATER_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.WATER_BREATHING, 0)));


	public static Item.Properties of() {
		return new Item.Properties();
	}

	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Armor");
		ARMOR.register();
	}
}
