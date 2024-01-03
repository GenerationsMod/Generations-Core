package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.armor.GenerationsArmorItem;
import generations.gg.generations.core.generationscore.world.item.armor.GenerationsArmorMaterials;
import generations.gg.generations.core.generationscore.world.item.armor.effects.RepairArmorEffect;
import generations.gg.generations.core.generationscore.world.item.armor.effects.RunningBootsArmorEffect;
import generations.gg.generations.core.generationscore.world.item.armor.effects.SpeedModifier;
import generations.gg.generations.core.generationscore.world.item.armor.effects.UnbreakableArmorEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

import java.util.function.Function;
import java.util.function.Supplier;

public class GenerationsArmor {
	public static final DeferredRegister<Item> ARMOR = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);
	/**
	 * Armor Sets
	 */
	public static final RegistrySupplier<Item> RUNNING_BOOTS = register("running_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.RUNNING, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.75F)).addArmorEffect(new RunningBootsArmorEffect()), CreativeModeTabs.COMBAT);
	public static final RegistrySupplier<Item> OLD_RUNNING_BOOTS = register("old_running_boots", properties -> new GenerationsArmorItem(GenerationsArmorMaterials.OLD_RUNNING, ArmorItem.Type.BOOTS, of()).addCustomAttributeModifier(new SpeedModifier(0.5F)).addArmorEffect(new UnbreakableArmorEffect()).addArmorEffect(new RepairArmorEffect()), CreativeModeTabs.COMBAT);

	public static RegistrySupplier<Item> register(String name, Function<Item.Properties, Item> function, ResourceKey<CreativeModeTab> tab) {
		return ARMOR.register(name, () -> function.apply(of().arch$tab(tab)));
	}

	public static RegistrySupplier<Item> register(String name, Function<Item.Properties, Item> function, CreativeModeTab tab) {
		return ARMOR.register(name, () -> function.apply(of().arch$tab(tab)));
	}

	public static final ArmorSet AETHER = ArmorSet.create("aether", () -> GenerationsArmorMaterials.AETHER);
	public static final ArmorSet AQUA = ArmorSet.create("aqua", () -> GenerationsArmorMaterials.AQUA);
	public static final ArmorSet FLARE = ArmorSet.create("flare", () -> GenerationsArmorMaterials.FLARE);
	public static final ArmorSet GALACTIC = ArmorSet.create("galactic", () -> GenerationsArmorMaterials.GALACTIC);
	public static final ArmorSet MAGMA = ArmorSet.create("magma", () -> GenerationsArmorMaterials.MAGMA);
	public static final ArmorSet NEO_PLASMA = ArmorSet.create("neo_plasma", () -> GenerationsArmorMaterials.NEO_PLASMA);
	public static final ArmorSet PLASMA = ArmorSet.create("plasma", () -> GenerationsArmorMaterials.PLASMA);
	public static final ArmorSet ROCKET = ArmorSet.create("rocket", () -> GenerationsArmorMaterials.ROCKET);
	public static final ArmorSet SKULL = ArmorSet.create("skull", () -> GenerationsArmorMaterials.SKULL);
	public static final ArmorSet ULTRA = ArmorSet.create("ultra", () -> GenerationsArmorMaterials.ULTRA);
	public static final ArmorSet CRYSTALLIZED = ArmorSet.create("crystallized", () -> GenerationsArmorMaterials.CRYSTAL);
	public static final ArmorSet DAWN_STONE = ArmorSet.create("dawn_stone", () -> GenerationsArmorMaterials.DAWN_STONE);
	public static final ArmorSet DUSK_STONE = ArmorSet.create("dusk_stone", () -> GenerationsArmorMaterials.DUSK_STONE);
	public static final ArmorSet FIRE_STONE = ArmorSet.create("fire_stone", () -> GenerationsArmorMaterials.FIRE_STONE);
	public static final ArmorSet LEAF_STONE = ArmorSet.create("leaf_stone", () -> GenerationsArmorMaterials.LEAF_STONE);
	public static final ArmorSet ICE_STONE = ArmorSet.create("ice_stone", () -> GenerationsArmorMaterials.ICE_STONE);
	public static final ArmorSet MOON_STONE = ArmorSet.create("moon_stone", () -> GenerationsArmorMaterials.MOON_STONE);
	public static final ArmorSet SUN_STONE = ArmorSet.create("sun_stone", () -> GenerationsArmorMaterials.SUN_STONE);
	public static final ArmorSet THUNDER_STONE = ArmorSet.create("thunder_stone", () -> GenerationsArmorMaterials.THUNDER_STONE);
	public static final ArmorSet WATER_STONE = ArmorSet.create("water_stone", () -> GenerationsArmorMaterials.WATER_STONE);


	public record ArmorSet(RegistrySupplier<Item> helmet, RegistrySupplier<Item> chestplate, RegistrySupplier<Item> leggings, RegistrySupplier<Item> boots, Supplier<ArmorMaterial> armorMaterial) {
		public static ArmorSet create(String name, Supplier<ArmorMaterial> armorMaterial) {
			return new ArmorSet(
					register(name + "_helmet", properties -> new GenerationsArmorItem(armorMaterial.get(), ArmorItem.Type.HELMET, properties)),
					register(name + "_chestplate", properties -> new GenerationsArmorItem(armorMaterial.get(), ArmorItem.Type.CHESTPLATE, properties)),
					register(name + "_leggings", properties -> new GenerationsArmorItem(armorMaterial.get(), ArmorItem.Type.LEGGINGS, properties)),
					register(name + "_boots", properties -> new GenerationsArmorItem(armorMaterial.get(), ArmorItem.Type.BOOTS, properties)),
					armorMaterial
			);
		}

		public static RegistrySupplier<Item> register(String name, Function<Item.Properties, Item> function) {
			return GenerationsArmor.register(name, function, CreativeModeTabs.COMBAT);
		}
	}

	public static Item.Properties of() {
		return new Item.Properties();
	}

	public static void init() {
		GenerationsCore.LOGGER.info("Registering Generations Armor");
		ARMOR.register();
	}
}
