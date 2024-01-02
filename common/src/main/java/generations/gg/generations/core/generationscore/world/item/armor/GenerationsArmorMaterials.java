package generations.gg.generations.core.generationscore.world.item.armor;

import com.cobblemon.mod.common.CobblemonItems;
import com.google.common.base.Suppliers;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum GenerationsArmorMaterials implements ArmorMaterial {
    RUNNING("running", 66, new int[]{3, 0, 0, 0}, 22, () -> Items.LEATHER),
    OLD_RUNNING("old_running", 999999, new int[]{2, 0, 0, 0}, 13, () -> Items.LEATHER),
    AETHER("aether", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.SILICON),
    AQUA("aqua", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.SAPPHIRE),
    FLARE("flare", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.RUBY),
    GALACTIC("galactic", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.SILICON),
    MAGMA("magma", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.RUBY),
    NEO_PLASMA("neo_plasma", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.CRYSTAL),
    PLASMA("plasma", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.CRYSTAL),
    ROCKET("rocket", 200, new int[]{3, 6, 7, 3}, 10, () -> Items.AMETHYST_SHARD),
    SKULL("skull", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.SILICON),
    CRYSTAL("crystal", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.CRYSTAL),
    ULTRA("ultra", 200, new int[]{3, 6, 7, 3}, 10, GenerationsItems.Z_INGOT),
    DAWN_STONE("dawn_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.DAWN_STONE),
    DUSK_STONE("dusk_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.DUSK_STONE),
    FIRE_STONE("fire_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.FIRE_STONE),
    ICE_STONE("ice_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.ICE_STONE),
    LEAF_STONE("leaf_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.LEAF_STONE),
    MOON_STONE("moon_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.MOON_STONE),
    SUN_STONE("sun_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.SUN_STONE),
    THUNDER_STONE("thunder_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.THUNDER_STONE),
    WATER_STONE("water_stone", 200, new int[]{3, 6, 7, 3}, 10, () -> CobblemonItems.WATER_STONE);

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    GenerationsArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, Supplier<Item> repairIngredient) {
        this.name = GenerationsCore.MOD_ID + ":" + name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = SoundEvents.ARMOR_EQUIP_IRON;
        this.toughness = (float) 0.0;
        this.knockbackResistance = (float) 0.0;
        this.repairIngredient = Suppliers.memoize(() -> Ingredient.of(repairIngredient.get()));
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_PER_SLOT[type.getSlot().getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.slotProtections[type.getSlot().getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public @NotNull String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
