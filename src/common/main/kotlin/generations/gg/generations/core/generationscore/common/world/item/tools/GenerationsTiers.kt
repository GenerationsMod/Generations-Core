package generations.gg.generations.core.generationscore.common.world.item.tools

import com.cobblemon.mod.common.CobblemonItems
import com.google.common.base.Suppliers
import generations.gg.generations.core.generationscore.common.tags.GenerationsBlockTags
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Items
import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Block

enum class GenerationsTiers(
    private val incorrectBlocksForDrops: TagKey<Block>,
    val level: Int,
    private val uses: Int,
    private val speed: Float,
    private val damage: Float,
    private val enchantmentValue: Int,
    ingredientSupplier: () -> Ingredient
) : Tier {
    CHARGE_STONE(GenerationsBlockTags.INCORRECT_FOR_CHARGE_STONE_TOOL, 1, 131, 5.0f, 1.0f, 5, { Ingredient.of(GenerationsBlocks.CHARGE_STONE_SET.baseBlock) }),
    VOLCANIC_STONE(GenerationsBlockTags.INCORRECT_FOR_VOLCANIC_STONE_TOOL, 1, 131, 4.0f, 1.0f, 5, { Ingredient.of(GenerationsBlocks.VOLCANIC_STONE.value()) }),

    AMETHYST(GenerationsBlockTags.INCORRECT_FOR_AMETHYST_TOOL, 2, 250, 6f, 2f, 14, { Ingredient.of(Items.AMETHYST_SHARD) }),
    CRYSTAL(GenerationsBlockTags.INCORRECT_FOR_CRYSTAL_TOOL, 2, 250, 6f, 2f, 14, { Ingredient.of(GenerationsItems.CRYSTAL.value()) }),
    SAPPHIRE(GenerationsBlockTags.INCORRECT_FOR_SAPPHIRE_TOOL, 2, 250, 6f, 2f, 14, { Ingredient.of(GenerationsItems.SAPPHIRE.value()) }),
    SILICON(GenerationsBlockTags.INCORRECT_FOR_SILICON_TOOL, 2, 250, 6f, 2f, 14, { Ingredient.of(GenerationsItems.SILICON.value()) }),
    RUBY(GenerationsBlockTags.INCORRECT_FOR_RUBY_TOOL, 2, 250, 6f, 2f, 14, { Ingredient.of(GenerationsItems.RUBY.value()) }),

    DAWN_STONE(GenerationsBlockTags.INCORRECT_FOR_DAWN_STONE_TOOL, 3, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.DAWN_STONE) }),
    DUSK_STONE(GenerationsBlockTags.INCORRECT_FOR_DUSK_STONE_TOOL, 3, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.DUSK_STONE) }),
    FIRE_STONE(GenerationsBlockTags.INCORRECT_FOR_FIRE_STONE_TOOL, 3, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.FIRE_STONE) }),
    ICE_STONE(GenerationsBlockTags.INCORRECT_FOR_ICE_STONE_TOOL, 3, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.ICE_STONE) }),
    LEAF_STONE(GenerationsBlockTags.INCORRECT_FOR_LEAF_STONE_TOOL, 2, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.LEAF_STONE) }),
    MOON_STONE(GenerationsBlockTags.INCORRECT_FOR_MOON_STONE_TOOL, 3, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.MOON_STONE) }),
    SUN_STONE(GenerationsBlockTags.INCORRECT_FOR_SUN_STONE_TOOL, 3, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.SUN_STONE) }),
    THUNDER_STONE(GenerationsBlockTags.INCORRECT_FOR_THUNDER_STONE_TOOL, 3, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.THUNDER_STONE) }),
    WATER_STONE(GenerationsBlockTags.INCORRECT_FOR_WATER_STONE_TOOL, 3, 1561, 12f, 3f, 10, { Ingredient.of(CobblemonItems.WATER_STONE) }),
    ULTRITE(GenerationsBlockTags.INCORRECT_FOR_ULTRITE_TOOL, 5, 2640, 10.0f, 5.0f, 20, { Ingredient.of(GenerationsItems.ULTRITE_INGOT.value()) });

    private val repairIngredient = Suppliers.memoize(ingredientSupplier)

    override fun getUses(): Int {
        return this.uses
    }

    override fun getSpeed(): Float {
        return this.speed
    }

    override fun getAttackDamageBonus(): Float {
        return this.damage
    }

    override fun getIncorrectBlocksForDrops(): TagKey<Block> {
        return incorrectBlocksForDrops
    }

    override fun getEnchantmentValue(): Int {
        return this.enchantmentValue
    }

    override fun getRepairIngredient(): Ingredient = repairIngredient.get()

    val tag: TagKey<Block>
        get() = when (this) {
            CHARGE_STONE, VOLCANIC_STONE -> BlockTags.NEEDS_STONE_TOOL
            AMETHYST, CRYSTAL, LEAF_STONE, RUBY, SAPPHIRE, SILICON -> BlockTags.NEEDS_IRON_TOOL
            DAWN_STONE, DUSK_STONE, FIRE_STONE, ICE_STONE, MOON_STONE, SUN_STONE, THUNDER_STONE, WATER_STONE, ULTRITE -> BlockTags.NEEDS_DIAMOND_TOOL
        }

    override fun toString(): String {
        return super.toString()
    }
}
