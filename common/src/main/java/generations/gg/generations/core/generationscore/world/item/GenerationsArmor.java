package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.armor.GenerationsArmorItem;
import generations.gg.generations.core.generationscore.world.item.armor.GenerationsArmorMaterials;
import generations.gg.generations.core.generationscore.world.item.armor.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.function.Function;

public class GenerationsArmor {
	public static final DeferredRegister<Item> ARMOR = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);
	/**
	 * Armor Sets
	 */
	public static final RegistrySupplier<Item> RUNNING_BOOTS = register("running_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.RUNNING, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.75F)).addArmorEffect(new RunningBootsArmorEffect()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> OLD_RUNNING_BOOTS = register("old_running_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.OLD_RUNNING, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new UnbreakableArmorEffect()).addArmorEffect(new RepairArmorEffect()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> AQUA_HELMET = register("aqua_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.AQUA, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> AQUA_CHESTPLATE = register("aqua_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.AQUA, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> AQUA_LEGGINGS = register("aqua_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.AQUA, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> AQUA_BOOTS = register("aqua_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.AQUA, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> FLARE_HELMET = register("flare_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.FLARE, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> FLARE_CHESTPLATE = register("flare_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.FLARE, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> FLARE_LEGGINGS = register("flare_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.FLARE, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> FLARE_BOOTS = register("flare_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.FLARE, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> GALACTIC_HELMET = register("galactic_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.GALACTIC, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> GALACTIC_CHESTPLATE = register("galactic_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.GALACTIC, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> GALACTIC_LEGGINGS = register("galactic_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.GALACTIC, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> GALACTIC_BOOTS = register("galactic_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.GALACTIC, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> MAGMA_HELMET = register("magma_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.MAGMA, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> MAGMA_CHESTPLATE = register("magma_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.MAGMA, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> MAGMA_LEGGINGS = register("magma_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.MAGMA, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> MAGMA_BOOTS = register("magma_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.MAGMA, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> NEO_PLASMA_HELMET = register("neo_plasma_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.NEO_PLASMA, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> NEO_PLASMA_CHESTPLATE = register("neo_plasma_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.NEO_PLASMA, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> NEO_PLASMA_LEGGINGS = register("neo_plasma_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.NEO_PLASMA, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> NEO_PLASMA_BOOTS = register("neo_plasma_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.NEO_PLASMA, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> PLASMA_HELMET = register("plasma_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.PLASMA, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> PLASMA_CHESTPLATE = register("plasma_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.PLASMA, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> PLASMA_LEGGINGS = register("plasma_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.PLASMA, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> PLASMA_BOOTS = register("plasma_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.PLASMA, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ROCKET_HELMET = register("rocket_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ROCKET, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ROCKET_CHESTPLATE = register("rocket_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ROCKET, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ROCKET_LEGGINGS = register("rocket_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ROCKET, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ROCKET_BOOTS = register("rocket_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ROCKET, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> SKULL_HELMET = register("skull_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.SKULL, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> SKULL_CHESTPLATE = register("skull_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.SKULL, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> SKULL_LEGGINGS = register("skull_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.SKULL, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> SKULL_BOOTS = register("skull_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.SKULL, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> CRYSTAL_HELMET = register("crystal_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.CRYSTAL, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> CRYSTAL_CHESTPLATE = register("crystal_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.CRYSTAL, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> CRYSTAL_LEGGINGS = register("crystal_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.CRYSTAL, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> CRYSTAL_BOOTS = register("crystal_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.CRYSTAL, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.1F)).addArmorEffect(new PotionArmorEffect(MobEffects.MOVEMENT_SPEED, 0)).addArmorEffect(new PotionArmorEffect(MobEffects.JUMP, 0)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ULTRA_HELMET = register("ultra_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ULTRA, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ULTRA_CHESTPLATE = register("ultra_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ULTRA, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ULTRA_LEGGINGS = register("ultra_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ULTRA, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ULTRA_BOOTS = register("ultra_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ULTRA, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.25F)).addArmorEffect(new PotionArmorEffect(MobEffects.MOVEMENT_SPEED, 0)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ALUMINUM_HELMET = register("aluminum_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ALUMINUM, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ALUMINUM_CHESTPLATE = register("aluminum_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ALUMINUM, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ALUMINUM_LEGGINGS = register("aluminum_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ALUMINUM, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ALUMINUM_BOOTS = register("aluminum_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ALUMINUM, ArmorItem.Type.BOOTS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> DAWN_STONE_HELMET = register("dawn_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.DAWN_STONE, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> DAWN_STONE_CHESTPLATE = register("dawn_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.DAWN_STONE, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> DAWN_STONE_LEGGINGS = register("dawn_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.DAWN_STONE, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> DAWN_STONE_BOOTS = register("dawn_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.DAWN_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.JUMP, 3)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> DUSK_STONE_HELMET = register("dusk_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.DUSK_STONE, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> DUSK_STONE_CHESTPLATE = register("dusk_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.DUSK_STONE, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> DUSK_STONE_LEGGINGS = register("dusk_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.DUSK_STONE, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> DUSK_STONE_BOOTS = register("dusk_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.DUSK_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.SATURATION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> FIRE_STONE_HELMET = register("fire_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.FIRE_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> FIRE_STONE_CHESTPLATE = register("fire_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.FIRE_STONE, ArmorItem.Type.CHESTPLATE, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> FIRE_STONE_LEGGINGS = register("fire_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.FIRE_STONE, ArmorItem.Type.LEGGINGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> FIRE_STONE_BOOTS = register("fire_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.FIRE_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FIRE_PROTECTION, 2)).addArmorEffect(new PotionArmorEffect(MobEffects.FIRE_RESISTANCE, 0)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ICE_STONE_HELMET = register("ice_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ICE_STONE, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ICE_STONE_CHESTPLATE = register("ice_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ICE_STONE, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ICE_STONE_LEGGINGS = register("ice_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ICE_STONE, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> ICE_STONE_BOOTS = register("ice_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.ICE_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FROST_WALKER, 2)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> LEAF_STONE_HELMET = register("leaf_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.LEAF_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> LEAF_STONE_CHESTPLATE = register("leaf_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.LEAF_STONE, ArmorItem.Type.CHESTPLATE, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> LEAF_STONE_LEGGINGS = register("leaf_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.LEAF_STONE, ArmorItem.Type.LEGGINGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> LEAF_STONE_BOOTS = register("leaf_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.LEAF_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.THORNS, 3)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.FALL_PROTECTION, 3)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> MOON_STONE_HELMET = register("moon_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.MOON_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> MOON_STONE_CHESTPLATE = register("moon_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.MOON_STONE, ArmorItem.Type.CHESTPLATE, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> MOON_STONE_LEGGINGS = register("moon_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.MOON_STONE, ArmorItem.Type.LEGGINGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> MOON_STONE_BOOTS = register("moon_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.MOON_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.BLAST_PROTECTION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> SUN_STONE_HELMET = register("sun_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.SUN_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> SUN_STONE_CHESTPLATE = register("sun_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.SUN_STONE, ArmorItem.Type.CHESTPLATE, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> SUN_STONE_LEGGINGS = register("sun_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.SUN_STONE, ArmorItem.Type.LEGGINGS, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> SUN_STONE_BOOTS = register("sun_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.SUN_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.ALL_DAMAGE_PROTECTION, 4)).addArmorEffect(new EnchantmentArmorEffect(Enchantments.PROJECTILE_PROTECTION, 4)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> THUNDER_STONE_HELMET = register("thunder_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.THUNDER_STONE, ArmorItem.Type.HELMET, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> THUNDER_STONE_CHESTPLATE = register("thunder_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.THUNDER_STONE, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> THUNDER_STONE_LEGGINGS = register("thunder_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.THUNDER_STONE, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> THUNDER_STONE_BOOTS = register("thunder_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.THUNDER_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new DoubleSpeedArmorEffect()).addArmorEffect(new PotionArmorEffect(MobEffects.DIG_SPEED, 0)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> WATER_STONE_HELMET = register("water_stone_helmet", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.WATER_STONE, ArmorItem.Type.HELMET, of()).addArmorEffect(new EnchantmentArmorEffect(Enchantments.AQUA_AFFINITY, 2)), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> WATER_STONE_CHESTPLATE = register("water_stone_chestplate", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.WATER_STONE, ArmorItem.Type.CHESTPLATE, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> WATER_STONE_LEGGINGS = register("water_stone_leggings", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.WATER_STONE, ArmorItem.Type.LEGGINGS, of()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> WATER_STONE_BOOTS = register("water_stone_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.WATER_STONE, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new PotionArmorEffect(MobEffects.WATER_BREATHING, 0)), CreativeModeTabs.COMBAT);

	public static RegistrySupplier<Item> register(String name, Function<Item.Properties, Item> function, CreativeModeTab tab) {
		return ARMOR.register(name, () -> function.apply(of().arch$tab(tab)));
	}

	public static Item.Properties of() {
		return new Item.Properties();
	}

	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Armor");
		ARMOR.register();
	}

	public static final ArmorSet AETHER = ArmorSet.create("aether", GenerationsArmorMaterials.AETHER);

	public record ArmorSet(RegistrySupplier<Item> helmet, RegistrySupplier<Item> chestplate, RegistrySupplier<Item> leggings, RegistrySupplier<Item> boots) {
		public static ArmorSet create(String name, ArmorMaterial armorMaterial) {
			return new ArmorSet(
					register(name + "_helmet", properties -> new GenerationsArmorItem(armorMaterial, ArmorItem.Type.HELMET, properties), CreativeModeTabs.COMBAT),
					register(name + "_chestplate", properties -> new GenerationsArmorItem(armorMaterial, ArmorItem.Type.CHESTPLATE, properties), CreativeModeTabs.COMBAT),
					register(name + "_leggings", properties -> new GenerationsArmorItem(armorMaterial, ArmorItem.Type.LEGGINGS, properties), CreativeModeTabs.COMBAT),
					register(name + "_boots", properties -> new GenerationsArmorItem(armorMaterial, ArmorItem.Type.BOOTS, properties), CreativeModeTabs.COMBAT)
			);
		}

		public static RegistrySupplier<Item> register(String name, Function<Item.Properties, Item> function, CreativeModeTab tab) {
			return GenerationsArmor.register(name, function, tab);
		}
	}
}
