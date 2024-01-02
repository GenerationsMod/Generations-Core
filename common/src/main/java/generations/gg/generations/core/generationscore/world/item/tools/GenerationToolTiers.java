package generations.gg.generations.core.generationscore.world.item.tools;

import com.cobblemon.mod.common.CobblemonItems;
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

public enum GenerationToolTiers implements Tier {
    CHARGE_STONE(1, 185, 5.0F, 1.2F, 8, GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock().asItem()),
    VOLCANIC_STONE(1, 185, 5.0F, 1.2F, 8, GenerationsBlocks.VOLCANIC_STONE.get().asItem()),
    AMETHYST(2, 300, 6.5F, 2.0F, 14, Items.AMETHYST_SHARD),
    CRYSTAL(2, 300, 6.5F, 2.0F, 14, GenerationsItems.CRYSTAL.get()),
    DAWN_STONE(3, 1561, 12.0F, 0.0F, 22, CobblemonItems.DAWN_STONE.asItem()),
    DUSK_STONE(3, 1561, 12.0F, 0.0F, 22, CobblemonItems.DUSK_STONE.asItem()),
    FIRE_STONE(3, 1561, 8.0F, 3.0F, 10, CobblemonItems.FIRE_STONE.asItem()),
    ICE_STONE(3, 1561, 12.0F, 0.0F, 22, CobblemonItems.ICE_STONE.asItem()),
    LEAF_STONE(2, 250, 6.0F, 2.0F, 14, CobblemonItems.LEAF_STONE.asItem()),
    MOON_STONE(3, 1561, 12.0F, 0.0F, 22, CobblemonItems.MOON_STONE.asItem()),

    RUBY(2, 300, 6.5F, 2.0F, 14, GenerationsItems.RUBY.get()),

    SAPPHIRE(2, 300, 6.5F, 2.0F, 14, GenerationsItems.SAPPHIRE.get()),
    SILICON(2, 100, 45F, 10F, 30, GenerationsItems.SILICON.get()),
    SUN_STONE(3, 1561, 12.0F, 0.0F, 22, CobblemonItems.SUN_STONE.asItem()),
    THUNDER_STONE(3, 1561, 12.0F, 0.0F, 22, CobblemonItems.THUNDER_STONE.asItem()),
    WATER_STONE(3, 1561, 8.0F, 3.0F, 10, CobblemonItems.WATER_STONE.asItem());

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;

    GenerationToolTiers(int level, int uses, float speed, float damage, int enchantmentValue, Item repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Ingredient.of(repairIngredient);
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
        return this.repairIngredient;
    }

    public TagKey<Block> getTag() {
        return switch (this) {
            case CHARGE_STONE, VOLCANIC_STONE -> BlockTags.NEEDS_STONE_TOOL;
            case AMETHYST, CRYSTAL, LEAF_STONE, RUBY, SAPPHIRE, SILICON -> BlockTags.NEEDS_IRON_TOOL;
            case DAWN_STONE, DUSK_STONE, FIRE_STONE, ICE_STONE, MOON_STONE, SUN_STONE, THUNDER_STONE, WATER_STONE ->
                    BlockTags.NEEDS_DIAMOND_TOOL;
        };
    }
}
