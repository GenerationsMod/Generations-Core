package generations.gg.generations.core.generationscore.world.item.armor;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public enum GenerationsArmorMaterials implements ArmorMaterial {
    RUNNING(GenerationsCore.MOD_ID + ":running", 66, new int[]{3, 0, 0, 0}, 22, Ingredient.EMPTY),
    OLD_RUNNING(GenerationsCore.MOD_ID + ":old_running", 999999, new int[]{2, 0, 0, 0}, 13, Ingredient.EMPTY),
    AETHER(GenerationsCore.MOD_ID + ":aether", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.SILICON.get())),
    AQUA(GenerationsCore.MOD_ID + ":aqua", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.SAPPHIRE.get())),
    FLARE(GenerationsCore.MOD_ID + ":flare", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.FIRE_STONE.get())),
    GALACTIC(GenerationsCore.MOD_ID + ":galactic", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.SILICON.get())),
    MAGMA(GenerationsCore.MOD_ID + ":magma", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.RUBY.get())),
    NEO_PLASMA(GenerationsCore.MOD_ID + ":neo_plasma", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.CRYSTAL.get())),
    PLASMA(GenerationsCore.MOD_ID + ":plasma", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.CRYSTAL.get())),
    ROCKET(GenerationsCore.MOD_ID + ":rocket", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(Items.AMETHYST_SHARD)),
    SKULL(GenerationsCore.MOD_ID + ":skull", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.SILICON.get())),
    CRYSTAL(GenerationsCore.MOD_ID + ":crystal", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.CRYSTAL.get())),
    ULTRA(GenerationsCore.MOD_ID + ":ultra", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.Z_INGOT.get())),
    ALUMINUM(GenerationsCore.MOD_ID + ":aluminum", 15, new int[]{2, 5, 6, 2}, 8, Ingredient.of(GenerationsItems.ALUMINUM_INGOT.get())),
    DAWN_STONE(GenerationsCore.MOD_ID + ":dawn_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.DAWN_STONE.get())),
    DUSK_STONE(GenerationsCore.MOD_ID + ":dusk_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.DUSK_STONE.get())),
    FIRE_STONE(GenerationsCore.MOD_ID + ":fire_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.FIRE_STONE.get())),
    ICE_STONE(GenerationsCore.MOD_ID + ":ice_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.ICE_STONE.get())),
    LEAF_STONE(GenerationsCore.MOD_ID + ":leaf_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.LEAF_STONE.get())),
    MOON_STONE(GenerationsCore.MOD_ID + ":moon_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.MOON_STONE.get())),
    SUN_STONE(GenerationsCore.MOD_ID + ":sun_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.SUN_STONE.get())),
    THUNDER_STONE(GenerationsCore.MOD_ID + ":thunder_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.THUNDER_STONE.get())),
    WATER_STONE(GenerationsCore.MOD_ID + ":water_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(GenerationsItems.WATER_STONE.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;

    GenerationsArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, Ingredient repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = SoundEvents.ARMOR_EQUIP_IRON;
        this.toughness = (float) 0.0;
        this.knockbackResistance = (float) 0.0;
        this.repairIngredient = repairIngredient;
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
        return this.repairIngredient;
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
