package generations.gg.generations.core.generationscore.world.item.tools;

import com.cobblemon.mod.common.CobblemonItems;
import com.google.common.base.Suppliers;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum GenerationsTiers implements Tier {
    CHARGE_STONE(1, 185, 5.0F, 1.2F, 8, () -> GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock().asItem()),
    VOLCANIC_STONE(1, 185, 5.0F, 1.2F, 8, () -> GenerationsBlocks.VOLCANIC_STONE.get().asItem()),
    AMETHYST(2, 300, 6.5F, 2.0F, 14, () -> Items.AMETHYST_SHARD),
    CRYSTAL(2, 300, 6.5F, 2.0F, 14, GenerationsItems.CRYSTAL),
    DAWN_STONE(3, 1561, 12.0F, 0.0F, 22, () -> CobblemonItems.DAWN_STONE),
    DUSK_STONE(3, 1561, 12.0F, 0.0F, 22, () -> CobblemonItems.DUSK_STONE),
    FIRE_STONE(3, 1561, 8.0F, 3.0F, 10, () -> CobblemonItems.FIRE_STONE),
    ICE_STONE(3, 1561, 12.0F, 0.0F, 22, () -> CobblemonItems.ICE_STONE),
    LEAF_STONE(2, 250, 6.0F, 2.0F, 14, () -> CobblemonItems.LEAF_STONE),
    MOON_STONE(3, 1561, 12.0F, 0.0F, 22, () -> CobblemonItems.MOON_STONE),

    RUBY(2, 300, 6.5F, 2.0F, 14, GenerationsItems.RUBY),

    SAPPHIRE(2, 300, 6.5F, 2.0F, 14, GenerationsItems.SAPPHIRE),
    SILICON(2, 100, 45F, 10F, 30, GenerationsItems.SILICON),
    SUN_STONE(3, 1561, 12.0F, 0.0F, 22, () -> CobblemonItems.SUN_STONE),
    THUNDER_STONE(3, 1561, 12.0F, 0.0F, 22, () -> CobblemonItems.THUNDER_STONE),
    WATER_STONE(3, 1561, 8.0F, 3.0F, 10, () -> CobblemonItems.WATER_STONE);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final com.google.common.base.Supplier<Ingredient> repairIngredient;

    GenerationsTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Item> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(() -> Ingredient.of(repairIngredient.get()));
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public TagKey<Block> getTag() {
        return switch (this) {
            case CHARGE_STONE, VOLCANIC_STONE -> BlockTags.NEEDS_STONE_TOOL;
            case AMETHYST, CRYSTAL, LEAF_STONE, RUBY, SAPPHIRE, SILICON -> BlockTags.NEEDS_IRON_TOOL;
            case DAWN_STONE, DUSK_STONE, FIRE_STONE, ICE_STONE, MOON_STONE, SUN_STONE, THUNDER_STONE, WATER_STONE ->
                    BlockTags.NEEDS_DIAMOND_TOOL;
        };
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
