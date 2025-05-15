package generations.gg.generations.core.generationscore.common.world.item.tools;

import com.cobblemon.mod.common.CobblemonItems;
import com.google.common.base.Suppliers;
import generations.gg.generations.core.generationscore.common.tags.GenerationsBlockTags;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks;
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
    CHARGE_STONE(GenerationsBlockTags.INCORRECT_FOR_CHARGE_STONE_TOOL, 1, 131, 5.0F, 1.0F, 5, () -> GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock().asItem()),
    VOLCANIC_STONE(GenerationsBlockTags.INCORRECT_FOR_VOLCANIC_STONE_TOOL, 1, 131, 4.0F, 1.0F, 5, () -> GenerationsBlocks.VOLCANIC_STONE.get().asItem()),

    AMETHYST(GenerationsBlockTags.INCORRECT_FOR_AMETHYST_TOOL, 2, 250, 6f, 2f, 14, () -> Items.AMETHYST_SHARD),
    CRYSTAL(GenerationsBlockTags.INCORRECT_FOR_CRYSTAL_TOOL, 2, 250, 6f, 2f, 14, GenerationsItems.CRYSTAL),
    SAPPHIRE(GenerationsBlockTags.INCORRECT_FOR_SAPPHIRE_TOOL, 2, 250, 6f, 2f, 14, GenerationsItems.SAPPHIRE),
    SILICON(GenerationsBlockTags.INCORRECT_FOR_SILICON_TOOL, 2, 250, 6f, 2f, 14, GenerationsItems.SILICON),
    RUBY(GenerationsBlockTags.INCORRECT_FOR_RUBY_TOOL, 2, 250, 6f, 2f, 14, GenerationsItems.RUBY),

    DAWN_STONE(GenerationsBlockTags.INCORRECT_FOR_DAWN_STONE_TOOL, 3, 1561, 12f, 3f, 10, () -> CobblemonItems.DAWN_STONE),
    DUSK_STONE(GenerationsBlockTags.INCORRECT_FOR_DUSK_STONE_TOOL, 3, 1561, 12f, 3f, 10, () -> CobblemonItems.DUSK_STONE),
    FIRE_STONE(GenerationsBlockTags.INCORRECT_FOR_FIRE_STONE_TOOL, 3, 1561, 12f, 3f, 10, () -> CobblemonItems.FIRE_STONE),
    ICE_STONE(GenerationsBlockTags.INCORRECT_FOR_ICE_STONE_TOOL, 3, 1561, 12f, 3f, 10, () -> CobblemonItems.ICE_STONE),
    LEAF_STONE(GenerationsBlockTags.INCORRECT_FOR_LEAF_STONE_TOOL, 2, 1561, 12f, 3f, 10, () -> CobblemonItems.LEAF_STONE),
    MOON_STONE(GenerationsBlockTags.INCORRECT_FOR_MOON_STONE_TOOL, 3, 1561, 12f, 3f, 10, () -> CobblemonItems.MOON_STONE),
    SUN_STONE(GenerationsBlockTags.INCORRECT_FOR_SUN_STONE_TOOL, 3, 1561, 12f, 3f, 10, () -> CobblemonItems.SUN_STONE),
    THUNDER_STONE(GenerationsBlockTags.INCORRECT_FOR_THUNDER_STONE_TOOL, 3, 1561, 12f, 3f, 10, () -> CobblemonItems.THUNDER_STONE),
    WATER_STONE(GenerationsBlockTags.INCORRECT_FOR_WATER_STONE_TOOL, 3, 1561, 12f, 3f, 10, () -> CobblemonItems.WATER_STONE),

    ULTRITE(GenerationsBlockTags.INCORRECT_FOR_ULTRITE_TOOL, 5, 2640, 10.0F, 5.0F, 20, GenerationsItems.ULTRITE_INGOT);

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final com.google.common.base.Supplier<Ingredient> repairIngredient;

    GenerationsTiers(TagKey<Block> incorrectBlocksForDrops, int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Item> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
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

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
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
            case DAWN_STONE, DUSK_STONE, FIRE_STONE, ICE_STONE, MOON_STONE, SUN_STONE, THUNDER_STONE, WATER_STONE, ULTRITE ->
                    BlockTags.NEEDS_DIAMOND_TOOL;
        };
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
