package generations.gg.generations.core.generationscore.common.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorEffect;
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem;
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorMaterials;
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.DoubleSpeedArmorEffect;
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.EnchantmentArmorEffect;
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.PotionArmorEffect;
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.SpeedModifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GenerationsArmor {
	public static final DeferredRegister<Item> ARMOR = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);
	/**
	 * Armor Sets
	 */

	public static RegistrySupplier<Item> register(String name, Function<Item.Properties, Item> function, ResourceKey<CreativeModeTab> tab) {
		return ARMOR.register(name, () -> function.apply(of().arch$tab(tab)));
	}

	public static RegistrySupplier<Item> register(String name, Function<Item.Properties, Item> function, CreativeModeTab tab) {
		return ARMOR.register(name, () -> function.apply(of().arch$tab(tab)));
	}

	public static final ArmorSet AETHER = ArmorSet.builder("aether", () -> GenerationsArmorMaterials.AETHER)
			.speed(0.5f)
			.build();
	public static final ArmorSet AQUA = ArmorSet.create("aqua", () -> GenerationsArmorMaterials.AQUA);
	public static final ArmorSet FLARE = ArmorSet.create("flare", () -> GenerationsArmorMaterials.FLARE);
	public static final ArmorSet GALACTIC = ArmorSet.builder("galactic", () -> GenerationsArmorMaterials.GALACTIC)
			.speed(0.5f)
			.build();
	public static final ArmorSet ULTRITE = ArmorSet.builder("ultrite", () -> GenerationsArmorMaterials.ULTRITE).build();
	public static final ArmorSet MAGMA = ArmorSet.create("magma", () -> GenerationsArmorMaterials.MAGMA);
	public static final ArmorSet NEO_PLASMA = ArmorSet.create("neo_plasma", () -> GenerationsArmorMaterials.NEO_PLASMA);
	public static final ArmorSet PLASMA = ArmorSet.create("plasma", () -> GenerationsArmorMaterials.PLASMA);
	public static final ArmorSet ROCKET = ArmorSet.create("rocket", () -> GenerationsArmorMaterials.ROCKET);
	public static final ArmorSet SKULL = ArmorSet.builder("skull", () -> GenerationsArmorMaterials.SKULL)
			.speed(0.5f)
			.build();
	public static final ArmorSet ULTRA = ArmorSet.builder("ultra", () -> GenerationsArmorMaterials.ULTRA)
			.potion(MobEffects.MOVEMENT_SPEED, 1)
			.speed(0.25f)
			.build();
	public static final ArmorSet CRYSTALLIZED = ArmorSet.builder("crystallized", () -> GenerationsArmorMaterials.CRYSTAL)
			.potion(MobEffects.MOVEMENT_SPEED, 1)
			.speed(0.1f)
			.build();
	public static final ArmorSet DAWN_STONE = ArmorSet.builder("dawn_stone", () -> GenerationsArmorMaterials.DAWN_STONE)
			.potion(MobEffects.JUMP, 3)
			.speed(0.5f)
			.build();
	public static final ArmorSet DUSK_STONE = ArmorSet.builder("dusk_stone", () -> GenerationsArmorMaterials.DUSK_STONE)
			.potion(MobEffects.SATURATION, 4)
			.speed(0.5f)
			.build();
	public static final ArmorSet FIRE_STONE = ArmorSet.builder("fire_stone", () -> GenerationsArmorMaterials.FIRE_STONE)
			.enchantment(Enchantments.FIRE_PROTECTION, 2)
			.potion(MobEffects.FIRE_RESISTANCE, 1)
			.speed(0.5f)
			.build();
	public static final ArmorSet LEAF_STONE = ArmorSet.builder("leaf_stone", () -> GenerationsArmorMaterials.LEAF_STONE)
			.enchantment(Enchantments.FALL_PROTECTION,3)
			.enchantment(Enchantments.THORNS, 3)
			.speed(0.5f)
			.build();
	public static final ArmorSet ICE_STONE = ArmorSet.builder("ice_stone", () -> GenerationsArmorMaterials.ICE_STONE)
			.enchantment(Enchantments.FROST_WALKER, 2)
			.speed(0.5f)
			.build();
	public static final ArmorSet MOON_STONE = ArmorSet.builder("moon_stone", () -> GenerationsArmorMaterials.MOON_STONE)
			.enchantment(Enchantments.ALL_DAMAGE_PROTECTION, 4)
			.enchantment(Enchantments.PROJECTILE_PROTECTION, 4)
			.speed(0.5f)
			.build();
	public static final ArmorSet SUN_STONE = ArmorSet.builder("sun_stone", () -> GenerationsArmorMaterials.SUN_STONE)
			.enchantment(Enchantments.ALL_DAMAGE_PROTECTION, 4)
			.enchantment(Enchantments.PROJECTILE_PROTECTION, 4)
			.speed(0.5f)
			.build();
	public static final ArmorSet THUNDER_STONE = ArmorSet.builder("thunder_stone", () -> GenerationsArmorMaterials.THUNDER_STONE)
			.potion(MobEffects.DIG_SPEED, 1)
			.speed(0.5f)
			.build();
	public static final ArmorSet WATER_STONE = ArmorSet.builder("water_stone", () -> GenerationsArmorMaterials.WATER_STONE)
			.potion(MobEffects.WATER_BREATHING, 1)
			.enchantment(Enchantments.AQUA_AFFINITY, 2)
			.speed(0.5f).build();

	public static Item.Properties of() {
		return new Item.Properties();
	}

	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Armor");
		ARMOR.register();
	}

	public record ArmorSet(RegistrySupplier<Item> helmet, RegistrySupplier<Item> chestplate, RegistrySupplier<Item> leggings, RegistrySupplier<Item> boots, Supplier<ArmorMaterial> armorMaterial) {
		public static Builder builder(String name, Supplier<ArmorMaterial> armorMaterial) {
			return new Builder(name, armorMaterial);
		}

		public Stream<RegistrySupplier<Item>> stream() {
			return Stream.of(helmet, chestplate, leggings, boots);
		}


		public static ArmorSet create(String name, Supplier<ArmorMaterial> armorMaterial, ArmorEffect... armorEffects) {
			return new ArmorSet(
					register(name + "_helmet", properties -> new GenerationsArmorItem(armorMaterial.get(), ArmorItem.Type.HELMET, properties)),
					register(name + "_chestplate", properties -> new GenerationsArmorItem(armorMaterial.get(), ArmorItem.Type.CHESTPLATE, properties)),
					register(name + "_leggings", properties -> new GenerationsArmorItem(armorMaterial.get(), ArmorItem.Type.LEGGINGS, properties)),
					register(name + "_boots", properties -> new GenerationsArmorItem(armorMaterial.get(), ArmorItem.Type.BOOTS, properties, armorEffects)),
					armorMaterial
			);
		}

		public static RegistrySupplier<Item> register(String name, Function<Item.Properties, GenerationsArmorItem> function) {
			return GenerationsArmor.register(name, function::apply, CreativeModeTabs.COMBAT);
		}

		public static class Builder {
			private final String name;
			private final Supplier<ArmorMaterial> armormaterial;
			private final List<ArmorEffect> effects = new ArrayList<>();

			public Builder(String name, Supplier<ArmorMaterial> armorMaterial) {
				this.name = name;
				this.armormaterial = armorMaterial;
			}

			public Builder enchantment(Enchantment enchantment, int level) {
				effects.add(new EnchantmentArmorEffect(enchantment, level));
				return this;
			}

			public Builder potion(MobEffect potionEffect, int amplifier) {
				effects.add(new PotionArmorEffect(potionEffect, amplifier));
				return this;
			}

			public Builder speed(float speed) {
				return speed(speed, false);
			}

			public Builder speed(float speed, boolean doubleWhenFullSet) {
				effects.add(new SpeedModifier(speed));
				if(doubleWhenFullSet) effects.add(new DoubleSpeedArmorEffect());
				return this;
			}

			public ArmorSet build() {
				return ArmorSet.create(name, armormaterial, effects.toArray(ArmorEffect[]::new));
			}
		}
	}
}
