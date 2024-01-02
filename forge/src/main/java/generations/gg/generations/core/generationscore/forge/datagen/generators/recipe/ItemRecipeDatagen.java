package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ItemRecipeDatagen extends GenerationsRecipeProvider.Proxied implements IConditionBuilder {
    public ItemRecipeDatagen(PackOutput arg) {
        super(arg);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GenerationsItems.ENIGMA_STONE.get())
                .define('#', GenerationsItems.ENIGMA_SHARD.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(GenerationsItems.ENIGMA_SHARD.get()), has(GenerationsItems.ENIGMA_SHARD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GenerationsItems.ENIGMA_SHARD.get())
                .define('#', GenerationsItems.ENIGMA_FRAGMENT.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(GenerationsItems.ENIGMA_FRAGMENT.get()), has(GenerationsItems.ENIGMA_FRAGMENT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GenerationsItems.COPPER_PLATE.get())
                .define('#', Items.COPPER_INGOT)
                .define('X', Items.IRON_NUGGET)
                .pattern("X#X")
                .pattern("###")
                .pattern("X#X")
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //These are all HeldItems and Recipes are not needed rn
        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.CELL_BATTERY.get())
                .define('X', Items.REDSTONE)
                .define('#', PokeModItems.ALUMINUM_PLATE.get())
                .pattern("#X#")
                .pattern("X#X")
                .pattern("#X#")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_PLATE.get()), has(PokeModItems.ALUMINUM_PLATE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.DESTINY_KNOT.get())
                .define('X', Items.RED_DYE)
                .define('#', Items.STRING)
                .pattern("X##")
                .pattern("X##")
                .pattern("###")
                .unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.DUBIOUS_DISC.get())
                .define('X', PokeModItems.ALUMINUM_PLATE.get())
                .define('#', Items.REDSTONE)
                .define('Y', Items.ENDER_EYE)
                .define('Z', PokeModItems.SILICON.get())
                .pattern("XYX")
                .pattern("ZZZ")
                .pattern("X#X")
                .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.EJECT_BUTTON.get())
                .define('X', Items.IRON_INGOT)
                .define('#', Items.STONE_BUTTON)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.STONE_BUTTON), has(Items.STONE_BUTTON))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.ELECTIRIZER.get())
                .define('X', Items.GOLD_INGOT)
                .define('#', Items.REDSTONE_BLOCK)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(Items.REDSTONE_BLOCK), has(Items.REDSTONE_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.EVERSTONE.get())
                .define('X', Items.STONE)
                .define('#', PokeModItems.HARD_STONE.get())
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(PokeModItems.HARD_STONE.get()), has(PokeModItems.HARD_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.EXP_SHARE.get())
                .define('X', Items.GLOWSTONE)
                .define('#', Items.LAPIS_LAZULI)
                .define('A', PokeModItems.ALUMINUM_INGOT.get())
                .pattern("XXX")
                .pattern("#A#")
                .pattern("A A")
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI))
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_INGOT.get()), has(PokeModItems.ALUMINUM_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.EXPERT_BELT.get())
                .define('X', PokeModItems.BLACK_BELT.get())
                .pattern("X  ")
                .pattern(" X ")
                .pattern("  X")
                .unlockedBy(getHasName(PokeModItems.BLACK_BELT.get()), has(PokeModItems.BLACK_BELT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FLAME_ORB.get())
                .requires(Items.FIRE_CHARGE)
                .requires(PokeModItems.ORB.get())
                .unlockedBy(getHasName(Items.FIRE_CHARGE), has(Items.FIRE_CHARGE))
                .unlockedBy(getHasName(PokeModItems.ORB.get()), has(PokeModItems.ORB.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FLOAT_STONE.get())
                .requires(PokeModItems.AIR_BALLOON.get())
                .requires(Items.STONE)
                .unlockedBy(getHasName(PokeModItems.AIR_BALLOON.get()), has(PokeModItems.AIR_BALLOON.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.FOCUS_BAND.get())
                .define('X', Items.WHITE_WOOL)
                .define('#', Items.BLAZE_POWDER)
                .pattern(" XX")
                .pattern("XXX")
                .pattern("###")
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FOCUS_SASH.get())
                .requires(PokeModItems.FOCUS_BAND.get())
                .requires(PokeModItems.EXPERT_BELT.get())
                .unlockedBy(getHasName(PokeModItems.FOCUS_BAND.get()), has(PokeModItems.FOCUS_BAND.get()))
                .unlockedBy(getHasName(PokeModItems.EXPERT_BELT.get()), has(PokeModItems.EXPERT_BELT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.GRIP_CLAW.get())
                .requires(PokeModItems.RAZOR_CLAW.get(), 2)
                .unlockedBy(getHasName(PokeModItems.RAZOR_CLAW.get()), has(PokeModItems.RAZOR_CLAW.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.HEAT_ROCK.get())
                .define('X', Items.BLAZE_ROD)
                .define('#', Items.STONE)
                .pattern("X X")
                .pattern(" #X")
                .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.ICY_ROCK.get())
                .define('X', Items.ICE)
                .define('#', Items.STONE)
                .pattern(" X ")
                .pattern(" # ")
                .pattern("X X")
                .unlockedBy(getHasName(Items.ICE), has(Items.ICE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.LAGGING_TAIL.get())
                .define('X', PokeModItems.HARD_STONE.get())
                .pattern("X  ")
                .pattern("XXX")
                .unlockedBy(getHasName(PokeModItems.HARD_STONE.get()), has(PokeModItems.HARD_STONE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.LIFE_ORB.get())
                .requires(PokeModItems.ORB.get())
                .requires(Items.NETHER_STAR)
                .unlockedBy(getHasName(PokeModItems.ORB.get()), has(PokeModItems.ORB.get()))
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.LIGHT_CLAY.get())
                .define('X', Items.CLAY_BALL)
                .define('#', Items.GLOWSTONE_DUST)
                .pattern(" X ")
                .pattern("X#X")
                .pattern(" X ")
                .unlockedBy(getHasName(Items.CLAY_BALL), has(Items.CLAY_BALL))
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MACHO_BRACE.get())
                .define('X', Items.IRON_INGOT)
                .define('Y', PokeModItems.ALUMINUM_INGOT.get())
                .define('Z', Items.GREEN_DYE)
                .pattern("ZXY")
                .pattern("XYX")
                .pattern("YXZ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_INGOT.get()), has(PokeModItems.ALUMINUM_INGOT.get()))
                .unlockedBy(getHasName(Items.GREEN_DYE), has(Items.GREEN_DYE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MAGMARIZER.get())
                .define('X', Items.IRON_INGOT)
                .define('Y', Items.RED_DYE)
                .define('Z', Items.FURNACE)
                .define('#', Items.LAVA_BUCKET)
                .pattern("XXX")
                .pattern("YZY")
                .pattern("X#X")
                .unlockedBy(getHasName(Items.LAVA_BUCKET), has(Items.LAVA_BUCKET))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MAGNET.get())
                .define('X', PokeModItems.ALUMINUM_INGOT.get())
                .define('Y', Items.REDSTONE)
                .define('Z', Items.LAPIS_LAZULI)
                .pattern("XXX")
                .pattern("X X")
                .pattern("Y Z")
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_INGOT.get()), has(PokeModItems.ALUMINUM_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.METRONOME.get())
                .define('X', Items.IRON_INGOT)
                .define('Y', ItemTags.PLANKS)
                .pattern(" X ")
                .pattern("YXY")
                .pattern("YYY")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MIRACLE_SEED.get())
                .define('X', Items.WHEAT_SEEDS)
                .pattern(" XX")
                .pattern("XXX")
                .pattern("XX ")
                .unlockedBy(getHasName(Items.WHEAT_SEEDS), has(Items.WHEAT_SEEDS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MUSCLE_BAND.get())
                .define('X', Items.GREEN_WOOL)
                .define('Y', PokeModItems.POWER_HERB.get())
                .pattern("  X")
                .pattern("XXX")
                .pattern("Y Y")
                .unlockedBy(getHasName(PokeModItems.POWER_HERB.get()), has(PokeModItems.POWER_HERB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MYSTIC_WATER.get())
                .define('X', Items.STRING)
                .define('Y', Items.GLASS_BOTTLE)
                .pattern("XXX")
                .pattern("XXX")
                .pattern(" Y ")
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.NEVER_MELT_ICE.get())
                .requires(Items.PACKED_ICE, 7)
                .unlockedBy(getHasName(Items.PACKED_ICE), has(Items.PACKED_ICE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.RED_CARD.get())
                .define('X', Items.PAPER)
                .define('Y', Items.RED_DYE)
                .pattern("XY")
                .pattern("XY")
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.RING_TARGET.get())
                .define('X', Items.PAPER)
                .define('Y', Items.BLACK_DYE)
                .pattern("XYX")
                .pattern("YXY")
                .pattern("XYX")
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, PokeModItems.ROCKY_HELMET.get())
                .define('X', PokeModItems.HARD_STONE.get())
                .define('Y', Items.IRON_HELMET)
                .pattern("XXX")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.HARD_STONE.get()), has(PokeModItems.HARD_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SAFETY_GOGGLES.get())
                .define('X', PokeModItems.ALUMINUM_INGOT.get())
                .define('Y', Items.LIME_DYE)
                .define('Z', Items.STRING)
                .pattern("YZY")
                .pattern("X X")
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_INGOT.get()), has(PokeModItems.ALUMINUM_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SHELL_BELL.get())
                .define('X', ItemTags.SAND)
                .define('Y', Items.PRISMARINE_SHARD)
                .pattern("XYY")
                .pattern("XXY")
                .pattern(" XY")
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SILK_SCARF.get())
                .define('X', Items.WHITE_WOOL)
                .pattern("XXX")
                .pattern("  X")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.SMOKE_BALL.get())
                .requires(Items.GUNPOWDER)
                .requires(PokeModItems.ORB.get())
                .unlockedBy(getHasName(PokeModItems.ORB.get()), has(PokeModItems.ORB.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.SNOWBALL.get())
                .requires(Items.SNOWBALL)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SOFT_SAND.get())
                .define('X', ItemTags.SAND)
                .define('Y', Items.PAPER)
                .define('Z', Items.STRING)
                .pattern(" YY")
                .pattern("XXY")
                .pattern("XXZ")
                .unlockedBy("has_sand", has(ItemTags.SAND))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SOOTHE_BELL.get())
                .define('X', PokeModItems.ALUMINUM_PLATE.get())
                .define('Y', Items.STRING)
                .define('Z', Items.GOLD_NUGGET)
                .pattern("YY ")
                .pattern("XZX")
                .pattern("XXX")
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_PLATE.get()), has(PokeModItems.ALUMINUM_PLATE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SPELL_TAG.get())
                .define('X', Items.PAPER)
                .define('Y', Items.GRAY_DYE)
                .define('Z', Items.GHAST_TEAR)
                .pattern("YXY")
                .pattern("XZX")
                .pattern("YXY")
                .unlockedBy(getHasName(Items.GHAST_TEAR), has(Items.GHAST_TEAR))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.STICKY_BARB.get())
                .requires(Items.CACTUS, 4)
                .unlockedBy(getHasName(Items.CACTUS), has(Items.CACTUS))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.TOXIC_ORB.get())
                .requires(PokeModItems.ORB.get())
                .requires(PokeModItems.BLACK_SLUDGE.get())
                .unlockedBy(getHasName(PokeModItems.BLACK_SLUDGE.get()), has(PokeModItems.BLACK_SLUDGE.get()))
                .unlockedBy(getHasName(PokeModItems.ORB.get()), has(PokeModItems.ORB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.TWISTED_SPOON.get())
                .define('X', Items.IRON_INGOT)
                .define('Y', Items.ENDER_PEARL)
                .pattern("XXX")
                .pattern("XY ")
                .pattern("X  ")
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.WEAKNESS_POLICY.get())
                .requires(Items.FEATHER)
                .requires(Items.INK_SAC)
                .requires(Items.PAPER)
                .requires(PokeModItems.RING_TARGET.get())
                .unlockedBy(getHasName(PokeModItems.RING_TARGET.get()), has(PokeModItems.RING_TARGET.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.WIDE_LENS.get())
                .define('X', Items.IRON_INGOT)
                .define('Y', Items.GLASS_PANE)
                .pattern("XY")
                .pattern("XY")
                .pattern("X ")
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.BURN_DRIVE.get())
                .define('X', Items.IRON_BLOCK)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', PokeModItems.FIRE_GEM.get())
                .pattern("XYX")
                .pattern("YZY")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.FIRE_GEM.get()), has(PokeModItems.FIRE_GEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.CHILL_DRIVE.get())
                .define('X', Items.IRON_BLOCK)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', PokeModItems.ICE_GEM.get())
                .pattern("XYX")
                .pattern("YZY")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.ICE_GEM.get()), has(PokeModItems.ICE_GEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.DOUSE_DRIVE.get())
                .define('X', Items.IRON_BLOCK)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', PokeModItems.WATER_GEM.get())
                .pattern("XYX")
                .pattern("YZY")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.WATER_GEM.get()), has(PokeModItems.WATER_GEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SHOCK_DRIVE.get())
                .define('X', Items.IRON_BLOCK)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', PokeModItems.ELECTRIC_GEM.get())
                .pattern("XYX")
                .pattern("YZY")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.ELECTRIC_GEM.get()), has(PokeModItems.ELECTRIC_GEM.get()))
                .save(consumer);


        //Gems
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.BUG_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.SLIME_BALL)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.DARK_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.NETHER_BRICK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.DRAGON_GEM.get())
                .requires(Items.DIAMOND)
                .requires(PokeModItems.DRAGON_SCALE.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ELECTRIC_GEM.get())
                .requires(Items.DIAMOND)
                .requires(PokeModItems.THUNDER_STONE.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FAIRY_GEM.get())
                .requires(Items.DIAMOND)
                .requires(PokeModItems.SHINY_STONE.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FIGHTING_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.BRICK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FIRE_GEM.get())
                .requires(Items.DIAMOND)
                .requires(PokeModItems.FIRE_STONE.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FLYING_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.FEATHER)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.GHOST_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.GHAST_TEAR)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.GRASS_GEM.get())
                .requires(Items.DIAMOND)
                .requires(PokeModItems.LEAF_STONE.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.GROUND_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.BONE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ICE_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.SNOWBALL)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.NORMAL_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.POISON_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.SPIDER_EYE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.PSYCHIC_GEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ROCK_GEM.get())
                .requires(Items.DIAMOND)
                .requires(PokeModItems.EVERSTONE.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.STEEL_GEM.get())
                .requires(Items.DIAMOND)
                .requires(PokeModItems.ALUMINUM_INGOT.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.WATER_GEM.get())
                .requires(Items.DIAMOND)
                .requires(PokeModItems.WATER_STONE.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        //Incense Items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FULL_INCENSE.get())
                .requires(Items.BLAZE_POWDER ,2)
                .requires(Items.IRON_BLOCK)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.LAX_INCENSE.get())
                .requires(Items.BLAZE_POWDER ,2)
                .requires(Items.FEATHER)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.LUCK_INCENSE.get())
                .requires(Items.BLAZE_POWDER ,2)
                .requires(Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ODD_INCENSE.get())
                .requires(Items.BLAZE_POWDER,2)
                .requires(Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.PURE_INCENSE.get())
                .requires(Items.BLAZE_POWDER,2)
                .requires(Items.CARROT)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.CARROT), has(Items.CARROT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ROCK_INCENSE.get())
                .requires(Items.BLAZE_POWDER,2)
                .requires(Items.STONE)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.STONE), has(Items.STONE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ROSE_INCENSE.get())
                .requires(Items.BLAZE_POWDER,2)
                .requires(PokeModItems.LEAF_STONE.get())
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(PokeModItems.LEAF_STONE.get()), has(PokeModItems.LEAF_STONE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.SEA_INCENSE.get())
                .requires(Items.BLAZE_POWDER,2)
                .requires(PokeModItems.WATER_STONE.get())
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(PokeModItems.WATER_STONE.get()), has(PokeModItems.WATER_STONE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.WAVE_INCENSE.get())
                .requires(Items.BLAZE_POWDER,2)
                .requires(Items.WATER_BUCKET)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
                .save(consumer);
        */
    }
}
