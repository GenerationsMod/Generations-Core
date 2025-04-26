package generations.gg.generations.core.generationscore.common.world.item.armor;

import com.cobblemon.mod.common.CobblemonItems;
import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class GenerationsArmorMaterials {
    public static final RegistrySupplier<ArmorMaterial> AETHER = register("aether", 15, new int[] { 2, 5, 6, 2 }, 9, GenerationsItems.SILICON);
    public static final RegistrySupplier<ArmorMaterial> AQUA = register("saphire", 15, new int[] { 2, 5, 6, 2 }, 9, GenerationsItems.SAPPHIRE);
    public static final RegistrySupplier<ArmorMaterial> FLARE = register("flare", 15, new int[] { 2, 5, 6, 2 }, 9, GenerationsItems.RUBY);
    public static final RegistrySupplier<ArmorMaterial> GALACTIC = register("galactic", 15, new int[] { 2, 5, 6, 2 }, 9, GenerationsItems.SILICON);
    public static final RegistrySupplier<ArmorMaterial> MAGMA = register("ruby", 15, new int[] { 2, 5, 6, 2 }, 19, GenerationsItems.RUBY);
    public static final RegistrySupplier<ArmorMaterial> NEO_PLASMA = register("neo_plasma", 15, new int[] { 2, 5, 6, 2 }, 9, GenerationsItems.CRYSTAL);
    public static final RegistrySupplier<ArmorMaterial> PLASMA = register("plasma", 15, new int[] { 2, 5, 6, 2 }, 9, GenerationsItems.CRYSTAL);
    public static final RegistrySupplier<ArmorMaterial> ROCKET = register("rocket", 15, new int[] { 2, 5, 6, 2 }, 9, () -> Items.AMETHYST_SHARD);
    public static final RegistrySupplier<ArmorMaterial> SKULL = register("skull", 15, new int[] { 2, 5, 6, 2 }, 9, GenerationsItems.SILICON);
    public static final RegistrySupplier<ArmorMaterial> CRYSTAL = register("crystal", 15, new int[] { 2, 5, 6, 2 }, 9, GenerationsItems.CRYSTAL);

    public static final RegistrySupplier<ArmorMaterial> ULTRA = register("ultra", 33, new int[] { 3, 6, 7, 3 }, 10, GenerationsItems.Z_INGOT);
    public static final RegistrySupplier<ArmorMaterial> DAWN_STONE = register("dawn_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.DAWN_STONE, 2.0f, 0.0f);
    public static final RegistrySupplier<ArmorMaterial> DUSK_STONE = register("dusk_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.DUSK_STONE, 2.0f, 0.0f);
    public static final RegistrySupplier<ArmorMaterial> FIRE_STONE = register("fire_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.FIRE_STONE, 2.0f, 0.0f);
    public static final RegistrySupplier<ArmorMaterial> ICE_STONE = register("ice_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.ICE_STONE, 2.0f, 0.0f);
    public static final RegistrySupplier<ArmorMaterial> LEAF_STONE = register("leaf_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.LEAF_STONE, 2.0f, 0.0f);
    public static final RegistrySupplier<ArmorMaterial> MOON_STONE = register("moon_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.MOON_STONE, 2.0f, 0.0f);
    public static final RegistrySupplier<ArmorMaterial> SUN_STONE = register("sun_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.SUN_STONE, 2.0f, 0.0f);
    public static final RegistrySupplier<ArmorMaterial> THUNDER_STONE = register("thunder_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.THUNDER_STONE, 2.0f, 0.0f);
    public static final RegistrySupplier<ArmorMaterial> WATER_STONE = register("water_stone", 33, new int[] { 3, 6, 8, 3 }, 10, () -> CobblemonItems.WATER_STONE, 2.0f, 0.0f);

    public static final RegistrySupplier<ArmorMaterial> ULTRITE = register("ultrite", 42, new int[]{3, 6, 8, 3}, 20, GenerationsItems.ULTRITE_INGOT, 4.0f, 0.2f);

    public static final DeferredRegister<ArmorMaterial> REGISTER = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ARMOR_MATERIAL);

    public static RegistrySupplier<ArmorMaterial> register(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, Supplier<Item> repairIngredient, float toughness, float knockbackResistance) {
        return REGISTER.register(name, () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, slotProtections[0]);
            map.put(ArmorItem.Type.LEGGINGS, slotProtections[1]);
            map.put(ArmorItem.Type.CHESTPLATE, slotProtections[2]);
            map.put(ArmorItem.Type.HELMET, slotProtections[3]);
        }), enchantmentValue, SoundEvents.ARMOR_EQUIP_IRON, Suppliers.memoize(() -> Ingredient.of(repairIngredient.get())), List.of(), toughness, knockbackResistance));
    }

    public static RegistrySupplier<ArmorMaterial> register(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, Supplier<Item> repairIngredient) {
        return register(name, durabilityMultiplier, slotProtections, enchantmentValue, repairIngredient, 0.0f, 0.0f);
    }

    public static void init() {
        REGISTER.register();
    }
}
