package com.pokemod.pokemod.world.item.armor;

import com.pokemod.pokemod.world.item.PokeModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public enum PokeModArmorMaterials implements ArmorMaterial {
    RUNNING("pokemod:running", 66, new int[]{3, 0, 0, 0}, 22, Ingredient.EMPTY),
    OLD_RUNNING("pokemod:old_running", 999999, new int[]{2, 0, 0, 0}, 13, Ingredient.EMPTY),
    AETHER("pokemod:aether", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.SILICON.get())),
    AQUA("pokemod:aqua", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.SAPPHIRE.get())),
    FLARE("pokemod:flare", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.FIRE_STONE.get())),
    GALACTIC("pokemod:galactic", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.SILICON.get())),
    MAGMA("pokemod:magma", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.RUBY.get())),
    NEO_PLASMA("pokemod:neo_plasma", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.CRYSTAL.get())),
    PLASMA("pokemod:plasma", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.CRYSTAL.get())),
    ROCKET("pokemod:rocket", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(Items.AMETHYST_SHARD)),
    SKULL("pokemod:skull", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.SILICON.get())),
    CRYSTAL("pokemod:crystal", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.CRYSTAL.get())),
    ULTRA("pokemod:ultra", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.Z_INGOT.get())),
    ALUMINUM("pokemod:aluminum", 15, new int[]{2, 5, 6, 2}, 8, Ingredient.of(PokeModItems.ALUMINUM_INGOT.get())),
    DAWN_STONE("pokemod:dawn_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.DAWN_STONE.get())),
    DUSK_STONE("pokemod:dusk_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.DUSK_STONE.get())),
    FIRE_STONE("pokemod:fire_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.FIRE_STONE.get())),
    ICE_STONE("pokemod:ice_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.ICE_STONE.get())),
    LEAF_STONE("pokemod:leaf_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.LEAF_STONE.get())),
    MOON_STONE("pokemod:moon_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.MOON_STONE.get())),
    SUN_STONE("pokemod:sun_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.SUN_STONE.get())),
    THUNDER_STONE("pokemod:thunder_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.THUNDER_STONE.get())),
    WATER_STONE("pokemod:water_stone", 200, new int[]{3, 6, 7, 3}, 10, Ingredient.of(PokeModItems.WATER_STONE.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;

    PokeModArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, Ingredient repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = SoundEvents.ARMOR_EQUIP_IRON;
        this.toughness = (float) 0.0;
        this.knockbackResistance = (float) 0.0;
        this.repairIngredient = repairIngredient;
    }

    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.slotProtections[slot.getIndex()];
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
