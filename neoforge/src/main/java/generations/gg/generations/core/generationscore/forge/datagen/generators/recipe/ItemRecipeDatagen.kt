package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import com.cobblemon.mod.common.CobblemonItems
import com.cobblemon.mod.common.CobblemonItems.CELL_BATTERY
import com.cobblemon.mod.common.CobblemonItems.DRAGON_GEM
import com.cobblemon.mod.common.CobblemonItems.FIRE_GEM
import com.cobblemon.mod.common.CobblemonItems.FIRE_STONE
import com.cobblemon.mod.common.CobblemonItems.FLYING_GEM
import com.cobblemon.mod.common.CobblemonItems.SHINY_STONE
import com.cobblemon.mod.common.CobblemonItems.WATER_GEM
import com.cobblemon.mod.common.CobblemonItems.WATER_STONE
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.id
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.world.item.Items
import net.neoforged.neoforge.common.conditions.IConditionBuilder
import java.util.concurrent.CompletableFuture


class ItemRecipeDatagen(arg: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : GenerationsRecipeProvider.Proxied(arg, registries), IConditionBuilder {
    override fun buildRecipes(recipeOutput: RecipeOutput) {
        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.ENIGMA_STONE) {
            define('#', GenerationsItems.ENIGMA_SHARD)
            pattern("##")
            pattern("##")
            unlockedByHolder(GenerationsItems.ENIGMA_SHARD)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.ENIGMA_SHARD) {
            define('#', GenerationsItems.ENIGMA_FRAGMENT)
            pattern("##")
            pattern("##")
            unlockedByHolder(GenerationsItems.ENIGMA_FRAGMENT)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.COPPER_PLATE) {
            define('#', Items.COPPER_INGOT)
            define('X', Items.IRON_NUGGET)
            pattern("X#X")
            pattern("###")
            pattern("X#X")
            unlockedByItem(Items.COPPER_INGOT)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.CAMERA) {
            define('I', Items.IRON_INGOT)
            define('B', CELL_BATTERY)
            define('G', Items.GLASS_PANE)
            define('R', Items.REDSTONE)
            pattern("III")
            pattern("BRG")
            pattern("III")
            unlockedByItem(CELL_BATTERY)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.SNAP_CAMERA) {
            define('I', GenerationsItems.Z_INGOT)
            define('B', CELL_BATTERY)
            define('G', Items.GLASS_PANE)
            define('R', Items.REDSTONE)
            pattern("III")
            pattern("BRG")
            pattern("III")
            unlockedByItem(CELL_BATTERY)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.FILM) {
            requires(Items.INK_SAC, 1)
            requires(Items.PAPER, 1)
            unlockedByItem(Items.PAPER)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.DARK_STONE) {
            define('D', GenerationsItems.DRAGON_SOUL)
            define('O', GenerationsItems.ORB)
            define('G', CobblemonItems.DARK_GEM)
            pattern("DDD")
            pattern("GOG")
            pattern("DDD")
            unlockedByHolder(GenerationsItems.DRAGON_SOUL)
        }
        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.LIGHT_STONE)  {
            define('D', GenerationsItems.DRAGON_SOUL)
            define('O', GenerationsItems.ORB)
            define('G', FIRE_GEM)
            pattern("DDD")
            pattern("GOG")
            pattern("DDD")
            unlockedByHolder(GenerationsItems.DRAGON_SOUL)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.DRAGON_STONE) {
            define('D', GenerationsItems.DRAGON_SOUL)
            define('O', GenerationsItems.ORB)
            define('G', DRAGON_GEM)
            pattern("DDD")
            pattern("GOG")
            pattern("DDD")
            unlockedByHolder(GenerationsItems.DRAGON_SOUL)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.FADED_BLUE_ORB) {
            define('D', WATER_STONE)
            define('O', GenerationsItems.ORB)
            define('G', WATER_GEM)
            pattern("DDD")
            pattern("GOG")
            pattern("DDD")
            unlockedByItem(WATER_STONE)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.FADED_RED_ORB) {
            define('D', FIRE_STONE)
            define('O', GenerationsItems.ORB)
            define('G', FIRE_GEM)
            pattern("DDD")
            pattern("GOG")
            pattern("DDD")
            unlockedByItem(FIRE_STONE)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.FADED_JADE_ORB) {
            define('D', SHINY_STONE)
            define('O', GenerationsItems.ORB)
            define('G', FLYING_GEM)
            pattern("DDD")
            pattern("GOG")
            pattern("DDD")
            unlockedByItem(SHINY_STONE)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.MELTAN_BOX) {
            define('X', GenerationsUtilityBlocks.BOX)
            define('Y', Items.IRON_INGOT)
            pattern("XXX")
            pattern("XYX")
            pattern("XXX")
            unlockedByItem(Items.IRON_INGOT)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.CRYSTAL_OF_EMOTION) {
            requires(GenerationsItems.SHARD_OF_EMOTION, 9)
            unlockedByHolder(GenerationsItems.SHARD_OF_EMOTION)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.CRYSTAL_OF_WILLPOWER) {
            requires(GenerationsItems.SHARD_OF_WILLPOWER, 9)
            unlockedByHolder(GenerationsItems.SHARD_OF_WILLPOWER)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.CRYSTAL_OF_KNOWLEDGE) {
            requires(GenerationsItems.SHARD_OF_KNOWLEDGE, 9)
            unlockedByHolder(GenerationsItems.SHARD_OF_KNOWLEDGE)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.RED_CHAIN) {
            requires(GenerationsItems.CRYSTAL_OF_EMOTION)
            requires(GenerationsItems.CRYSTAL_OF_KNOWLEDGE)
            requires(GenerationsItems.CRYSTAL_OF_WILLPOWER)
            unlockedByHolder(GenerationsItems.CRYSTAL_OF_KNOWLEDGE)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.RUBY_ROD) {
            define('R', GenerationsItems.RUBY)
            define('S', Items.STRING)
            define('O', GenerationsItems.ORB)
            pattern("  R")
            pattern(" RS")
            pattern("R O")
            unlockedByItem(Items.STRING)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.RUSTY_SWORD) {
            define('F', GenerationsItems.RUSTY_FRAGMENT)
            define('O', GenerationsItems.ORB)
            define('S', Items.IRON_SWORD)
            pattern("FFF")
            pattern("FOF")
            pattern("FSF")
            unlockedByItem(Items.IRON_SWORD)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.RUSTY_SHIELD) {
            define('F', GenerationsItems.RUSTY_FRAGMENT)
            define('O', GenerationsItems.ORB)
            define('S', Items.SHIELD)
            pattern("FFF")
            pattern("FOF")
            pattern("FSF")
            unlockedByItem(Items.SHIELD)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.TIME_GLASS) {
            define('O', GenerationsItems.ORB)
            define('C', Items.CLOCK)
            pattern("CCC")
            pattern("COC")
            pattern("CCC")
            unlockedByItem(Items.CLOCK)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.ZYGARDE_CUBE) {
            define('O', GenerationsItems.ORB)
            define('S', Items.SLIME_BALL)
            define('B', GenerationsUtilityBlocks.BOX)
            pattern("SBS")
            pattern("BOB")
            pattern("SBS")
            unlockedByItem(Items.SLIME_BALL)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.MEGA_BRACELET) {
            define('M', GenerationsItems.MEGASTONE_SHARD)
            define('I', Items.IRON_INGOT)
            pattern("MMM")
            pattern("MMM")
            pattern("III")
            unlockedByHolder(GenerationsItems.MEGASTONE_SHARD)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.MEGA_CUFF) {
            define('M', GenerationsItems.MEGASTONE_SHARD)
            define('R', GenerationsItems.RUBY)
            define('B', GenerationsItems.MEGA_BRACELET)
            pattern("RRR")
            pattern("MMM")
            pattern("RBR")
            unlockedByHolder(GenerationsItems.MEGASTONE_SHARD)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.MEGA_RING) {
            define('M', GenerationsItems.MEGASTONE_SHARD)
            define('R', Items.NETHERITE_SCRAP)
            define('B', GenerationsItems.MEGA_BRACELET)
            pattern("RRR")
            pattern("MMM")
            pattern("RBR")
            unlockedByHolder(GenerationsItems.MEGASTONE_SHARD)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.MEGA_CHARM) {
            define('M', GenerationsItems.MEGASTONE_SHARD)
            define('R', GenerationsItems.HEART_SCALE)
            define('B', GenerationsItems.MEGA_BRACELET)
            pattern("RRR")
            pattern("MMM")
            pattern("RBR")
            unlockedByHolder(GenerationsItems.MEGASTONE_SHARD)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.Z_RING) {
            define('Z', GenerationsItems.Z_INGOT)
            define('I', Items.IRON_INGOT)
            define('S', GenerationsItems.SPARKLING_SHARD)
            pattern("ZIZ")
            pattern("ZSZ")
            pattern("ZIZ")
            unlockedByHolder(GenerationsItems.Z_INGOT)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.Z_POWER_RING) {
            define('Z', GenerationsItems.Z_INGOT)
            define('I', Items.NETHERITE_INGOT)
            define('S', GenerationsItems.SPARKLING_SHARD)
            pattern("ZIZ")
            pattern("ZSZ")
            pattern("ZIZ")
            unlockedByHolder(GenerationsItems.Z_INGOT)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.DYNAMAX_BAND) {
            define('Z', GenerationsItems.DYNITE_ORE)
            define('I', Items.IRON_INGOT)
            pattern("ZIZ")
            pattern("ZIZ")
            pattern("ZIZ")
            unlockedByHolder(GenerationsItems.DYNITE_ORE)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.MAX_HONEY) {
            requires(GenerationsItems.MAX_POWDER)
            requires(Items.HONEY_BOTTLE, 8)
            unlockedByItem(Items.HONEY_BOTTLE)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.MAX_MUSHROOMS) {
            requires(GenerationsItems.MAX_POWDER, 9)
            unlockedByHolder(GenerationsItems.MAX_POWDER)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.MAX_POWDER) {
            requires(GenerationsItems.TINY_MUSHROOM, 9)
            unlockedByHolder(GenerationsItems.TINY_MUSHROOM)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.RADIANT_PETAL) {
            requires(GenerationsItems.BLUE_PETAL)
            requires(GenerationsItems.GREEN_PETAL)
            requires(GenerationsItems.ORANGE_PETAL)
            requires(GenerationsItems.PINK_PETAL)
            requires(GenerationsItems.PURPLE_PETAL)
            requires(GenerationsItems.RED_PETAL)
            requires(GenerationsItems.YELLOW_PETAL)
            unlockedByHolder(GenerationsItems.BLUE_PETAL)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.TIME_CAPSULE) {
            define('I', Items.IRON_INGOT)
            define('G', Items.GLASS)
            define('R', GenerationsItems.RUBY)
            pattern("IRI")
            pattern("IGI")
            pattern("III")
            unlockedByItem(Items.GLASS)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.REVEAL_GLASS) {
            define('R', GenerationsItems.RUBY)
            define('S', GenerationsItems.SAPPHIRE)
            define('C', GenerationsItems.CRYSTAL)
            define('G', GenerationsItems.MIRROR)
            pattern("RRR")
            pattern("SGS")
            pattern("CCC")
            unlockedByHolder(GenerationsItems.MIRROR)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.DNA_SPLICERS) {
            define('S', GenerationsItems.SILICON)
            define('G', Items.GOLD_INGOT)
            define('I', Items.IRON_INGOT)
            define('N', Items.NETHERITE_INGOT)
            pattern(" SG")
            pattern("IIS")
            pattern("NI ")
            unlockedByHolder(GenerationsItems.SILICON)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.MELODY_FLUTE) {
            define('S', GenerationsItems.SILICON)
            define('I', Items.IRON_INGOT)
            define('N', Items.NETHERITE_INGOT)
            pattern("S  ")
            pattern(" I ")
            pattern("  N")
            unlockedByHolder(GenerationsItems.SILICON)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.RELIC_SONG) {
            requires(GenerationsItems.SHATTERED_RELIC_SONG_1)
            requires(GenerationsItems.SHATTERED_RELIC_SONG_2)
            requires(GenerationsItems.SHATTERED_RELIC_SONG_3)
            requires(GenerationsItems.SHATTERED_RELIC_SONG_4)
            unlockedByHolder(GenerationsItems.SHATTERED_RELIC_SONG_1)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.ROCK_PEAK_KEY) {
            requires(GenerationsItems.CRUMBLED_ROCK_KEY_1)
            requires(GenerationsItems.CRUMBLED_ROCK_KEY_2)
            requires(GenerationsItems.CRUMBLED_ROCK_KEY_3)
            requires(GenerationsItems.CRUMBLED_ROCK_KEY_4)
            unlockedByHolder(GenerationsItems.CRUMBLED_ROCK_KEY_1)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.ICEBERG_KEY) {
            requires(GenerationsItems.SHATTERED_ICE_KEY_1)
            requires(GenerationsItems.SHATTERED_ICE_KEY_2)
            requires(GenerationsItems.SHATTERED_ICE_KEY_3)
            requires(GenerationsItems.SHATTERED_ICE_KEY_4)
            unlockedByHolder(GenerationsItems.SHATTERED_ICE_KEY_1)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.IRON_KEY) {
            requires(GenerationsItems.RUSTY_IRON_KEY_1)
            requires(GenerationsItems.RUSTY_IRON_KEY_2)
            requires(GenerationsItems.RUSTY_IRON_KEY_3)
            requires(GenerationsItems.RUSTY_IRON_KEY_4)
            unlockedByHolder(GenerationsItems.RUSTY_IRON_KEY_1)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.ELEKI_KEY) {
            requires(GenerationsItems.DISCHARGED_ELEKI_KEY_1)
            requires(GenerationsItems.DISCHARGED_ELEKI_KEY_2)
            requires(GenerationsItems.DISCHARGED_ELEKI_KEY_3)
            requires(GenerationsItems.DISCHARGED_ELEKI_KEY_4)
            unlockedByHolder(GenerationsItems.DISCHARGED_ELEKI_KEY_1)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.DRAGO_KEY) {
            requires(GenerationsItems.FRAGMENTED_DRAGO_KEY_1)
            requires(GenerationsItems.FRAGMENTED_DRAGO_KEY_2)
            requires(GenerationsItems.FRAGMENTED_DRAGO_KEY_3)
            requires(GenerationsItems.FRAGMENTED_DRAGO_KEY_4)
            unlockedByHolder(GenerationsItems.FRAGMENTED_DRAGO_KEY_1)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsShrines.LIGHT_CRYSTAL) {
            requires(GenerationsItems.LIGHT_SOUL, 9)
            unlockedByHolder(GenerationsItems.LIGHT_SOUL)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsShrines.DARK_CRYSTAL) {
            requires(GenerationsItems.DARK_SOUL, 9)
            unlockedByHolder(GenerationsItems.LIGHT_SOUL)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.METEORITE) {
            requires(GenerationsItems.METEORITE_SHARD, 9)
            unlockedByHolder(GenerationsItems.METEORITE_SHARD)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.SPARKLING_STONE) {
            requires(GenerationsItems.SPARKLING_SHARD, 9)
            unlockedByHolder(GenerationsItems.SPARKLING_SHARD)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.SECRET_ARMOR_SCROLL) {
            requires(GenerationsItems.SCROLL_PAGE, 9)
            unlockedByHolder(GenerationsItems.SCROLL_PAGE)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.REINS_OF_UNITY) {
            requires(GenerationsItems.BLACK_MANE_HAIR)
            requires(GenerationsItems.WHITE_MANE_HAIR)
            requires(GenerationsItems.RADIANT_PETAL)
            unlockedByHolder(GenerationsItems.RADIANT_PETAL)
        }

//        Current break
        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.MAGMA_CRYSTAL) {
            requires(GenerationsItems.LAVA_CRYSTAL, 9)
            unlockedByHolder(GenerationsItems.LAVA_CRYSTAL)
        }

        recipeOutput.shapeless(RecipeCategory.MISC, GenerationsItems.KEY_STONE) {
            requires(GenerationsItems.MEGASTONE_SHARD, 9)
            unlockedByHolder(GenerationsItems.MEGASTONE_SHARD)
        }

        recipeOutput.shaped(RecipeCategory.MISC, GenerationsItems.ULTRITE_INGOT, GenerationsItems.ULTRITE_INGOT.id.withSuffix("_craft")) {
            define('X', GenerationsItems.Z_INGOT)
            define('T', GenerationsItems.ULTRITE_REMNANT)
            pattern("XXX")
            pattern("XTT")
            pattern("TT ")
            unlockedByHolder(GenerationsItems.ULTRITE_REMNANT)
        }

        //These are all HeldItems and Recipes are not needed rn
        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.CELL_BATTERY)
                .define('X', Items.REDSTONE)
                .define('#', PokeModItems.ALUMINUM_PLATE)
                .pattern("#X#")
                .pattern("X#X")
                .pattern("#X#")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_PLATE), has(PokeModItems.ALUMINUM_PLATE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.DESTINY_KNOT)
                .define('X', Items.RED_DYE)
                .define('#', Items.STRING)
                .pattern("X##")
                .pattern("X##")
                .pattern("###")
                .unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.DUBIOUS_DISC)
                .define('X', PokeModItems.ALUMINUM_PLATE)
                .define('#', Items.REDSTONE)
                .define('Y', Items.ENDER_EYE)
                .define('Z', PokeModItems.SILICON)
                .pattern("XYX")
                .pattern("ZZZ")
                .pattern("X#X")
                .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.EJECT_BUTTON)
                .define('X', Items.IRON_INGOT)
                .define('#', Items.STONE_BUTTON)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.STONE_BUTTON), has(Items.STONE_BUTTON))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.ELECTIRIZER)
                .define('X', Items.GOLD_INGOT)
                .define('#', Items.REDSTONE_BLOCK)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(Items.REDSTONE_BLOCK), has(Items.REDSTONE_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.EVERSTONE)
                .define('X', Items.STONE)
                .define('#', PokeModItems.HARD_STONE)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy(getHasName(PokeModItems.HARD_STONE), has(PokeModItems.HARD_STONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.EXP_SHARE)
                .define('X', Items.GLOWSTONE)
                .define('#', Items.LAPIS_LAZULI)
                .define('A', PokeModItems.ALUMINUM_INGOT)
                .pattern("XXX")
                .pattern("#A#")
                .pattern("A A")
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI))
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_INGOT), has(PokeModItems.ALUMINUM_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.EXPERT_BELT)
                .define('X', PokeModItems.BLACK_BELT)
                .pattern("X  ")
                .pattern(" X ")
                .pattern("  X")
                .unlockedBy(getHasName(PokeModItems.BLACK_BELT), has(PokeModItems.BLACK_BELT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FLAME_ORB)
                .requires(Items.FIRE_CHARGE)
                .requires(PokeModItems.ORB)
                .unlockedBy(getHasName(Items.FIRE_CHARGE), has(Items.FIRE_CHARGE))
                .unlockedBy(getHasName(PokeModItems.ORB), has(PokeModItems.ORB))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FLOAT_STONE)
                .requires(PokeModItems.AIR_BALLOON)
                .requires(Items.STONE)
                .unlockedBy(getHasName(PokeModItems.AIR_BALLOON), has(PokeModItems.AIR_BALLOON))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.FOCUS_BAND)
                .define('X', Items.WHITE_WOOL)
                .define('#', Items.BLAZE_POWDER)
                .pattern(" XX")
                .pattern("XXX")
                .pattern("###")
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FOCUS_SASH)
                .requires(PokeModItems.FOCUS_BAND)
                .requires(PokeModItems.EXPERT_BELT)
                .unlockedBy(getHasName(PokeModItems.FOCUS_BAND), has(PokeModItems.FOCUS_BAND))
                .unlockedBy(getHasName(PokeModItems.EXPERT_BELT), has(PokeModItems.EXPERT_BELT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.GRIP_CLAW)
                .requires(PokeModItems.RAZOR_CLAW, 2)
                .unlockedBy(getHasName(PokeModItems.RAZOR_CLAW), has(PokeModItems.RAZOR_CLAW))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.HEAT_ROCK)
                .define('X', Items.BLAZE_ROD)
                .define('#', Items.STONE)
                .pattern("X X")
                .pattern(" #X")
                .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.ICY_ROCK)
                .define('X', Items.ICE)
                .define('#', Items.STONE)
                .pattern(" X ")
                .pattern(" # ")
                .pattern("X X")
                .unlockedBy(getHasName(Items.ICE), has(Items.ICE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.LAGGING_TAIL)
                .define('X', PokeModItems.HARD_STONE)
                .pattern("X  ")
                .pattern("XXX")
                .unlockedBy(getHasName(PokeModItems.HARD_STONE), has(PokeModItems.HARD_STONE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.LIFE_ORB)
                .requires(PokeModItems.ORB)
                .requires(Items.NETHER_STAR)
                .unlockedBy(getHasName(PokeModItems.ORB), has(PokeModItems.ORB))
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.LIGHT_CLAY)
                .define('X', Items.CLAY_BALL)
                .define('#', Items.GLOWSTONE_DUST)
                .pattern(" X ")
                .pattern("X#X")
                .pattern(" X ")
                .unlockedBy(getHasName(Items.CLAY_BALL), has(Items.CLAY_BALL))
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MACHO_BRACE)
                .define('X', Items.IRON_INGOT)
                .define('Y', PokeModItems.ALUMINUM_INGOT)
                .define('Z', Items.GREEN_DYE)
                .pattern("ZXY")
                .pattern("XYX")
                .pattern("YXZ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_INGOT), has(PokeModItems.ALUMINUM_INGOT))
                .unlockedBy(getHasName(Items.GREEN_DYE), has(Items.GREEN_DYE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MAGMARIZER)
                .define('X', Items.IRON_INGOT)
                .define('Y', Items.RED_DYE)
                .define('Z', Items.FURNACE)
                .define('#', Items.LAVA_BUCKET)
                .pattern("XXX")
                .pattern("YZY")
                .pattern("X#X")
                .unlockedBy(getHasName(Items.LAVA_BUCKET), has(Items.LAVA_BUCKET))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MAGNET)
                .define('X', PokeModItems.ALUMINUM_INGOT)
                .define('Y', Items.REDSTONE)
                .define('Z', Items.LAPIS_LAZULI)
                .pattern("XXX")
                .pattern("X X")
                .pattern("Y Z")
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_INGOT), has(PokeModItems.ALUMINUM_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.METRONOME)
                .define('X', Items.IRON_INGOT)
                .define('Y', ItemTags.PLANKS)
                .pattern(" X ")
                .pattern("YXY")
                .pattern("YYY")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MIRACLE_SEED)
                .define('X', Items.WHEAT_SEEDS)
                .pattern(" XX")
                .pattern("XXX")
                .pattern("XX ")
                .unlockedBy(getHasName(Items.WHEAT_SEEDS), has(Items.WHEAT_SEEDS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MUSCLE_BAND)
                .define('X', Items.GREEN_WOOL)
                .define('Y', PokeModItems.POWER_HERB)
                .pattern("  X")
                .pattern("XXX")
                .pattern("Y Y")
                .unlockedBy(getHasName(PokeModItems.POWER_HERB), has(PokeModItems.POWER_HERB))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.MYSTIC_WATER)
                .define('X', Items.STRING)
                .define('Y', Items.GLASS_BOTTLE)
                .pattern("XXX")
                .pattern("XXX")
                .pattern(" Y ")
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.NEVER_MELT_ICE)
                .requires(Items.PACKED_ICE, 7)
                .unlockedBy(getHasName(Items.PACKED_ICE), has(Items.PACKED_ICE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.RED_CARD)
                .define('X', Items.PAPER)
                .define('Y', Items.RED_DYE)
                .pattern("XY")
                .pattern("XY")
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.RING_TARGET)
                .define('X', Items.PAPER)
                .define('Y', Items.BLACK_DYE)
                .pattern("XYX")
                .pattern("YXY")
                .pattern("XYX")
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, PokeModItems.ROCKY_HELMET)
                .define('X', PokeModItems.HARD_STONE)
                .define('Y', Items.IRON_HELMET)
                .pattern("XXX")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.HARD_STONE), has(PokeModItems.HARD_STONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SAFETY_GOGGLES)
                .define('X', PokeModItems.ALUMINUM_INGOT)
                .define('Y', Items.LIME_DYE)
                .define('Z', Items.STRING)
                .pattern("YZY")
                .pattern("X X")
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_INGOT), has(PokeModItems.ALUMINUM_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SHELL_BELL)
                .define('X', ItemTags.SAND)
                .define('Y', Items.PRISMARINE_SHARD)
                .pattern("XYY")
                .pattern("XXY")
                .pattern(" XY")
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SILK_SCARF)
                .define('X', Items.WHITE_WOOL)
                .pattern("XXX")
                .pattern("  X")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.SMOKE_BALL)
                .requires(Items.GUNPOWDER)
                .requires(PokeModItems.ORB)
                .unlockedBy(getHasName(PokeModItems.ORB), has(PokeModItems.ORB))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.SNOWBALL)
                .requires(Items.SNOWBALL)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SOFT_SAND)
                .define('X', ItemTags.SAND)
                .define('Y', Items.PAPER)
                .define('Z', Items.STRING)
                .pattern(" YY")
                .pattern("XXY")
                .pattern("XXZ")
                .unlockedBy("has_sand", has(ItemTags.SAND))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SOOTHE_BELL)
                .define('X', PokeModItems.ALUMINUM_PLATE)
                .define('Y', Items.STRING)
                .define('Z', Items.GOLD_NUGGET)
                .pattern("YY ")
                .pattern("XZX")
                .pattern("XXX")
                .unlockedBy(getHasName(PokeModItems.ALUMINUM_PLATE), has(PokeModItems.ALUMINUM_PLATE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SPELL_TAG)
                .define('X', Items.PAPER)
                .define('Y', Items.GRAY_DYE)
                .define('Z', Items.GHAST_TEAR)
                .pattern("YXY")
                .pattern("XZX")
                .pattern("YXY")
                .unlockedBy(getHasName(Items.GHAST_TEAR), has(Items.GHAST_TEAR))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.STICKY_BARB)
                .requires(Items.CACTUS, 4)
                .unlockedBy(getHasName(Items.CACTUS), has(Items.CACTUS))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.TOXIC_ORB)
                .requires(PokeModItems.ORB)
                .requires(PokeModItems.BLACK_SLUDGE)
                .unlockedBy(getHasName(PokeModItems.BLACK_SLUDGE), has(PokeModItems.BLACK_SLUDGE))
                .unlockedBy(getHasName(PokeModItems.ORB), has(PokeModItems.ORB))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.TWISTED_SPOON)
                .define('X', Items.IRON_INGOT)
                .define('Y', Items.ENDER_PEARL)
                .pattern("XXX")
                .pattern("XY ")
                .pattern("X  ")
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.WEAKNESS_POLICY)
                .requires(Items.FEATHER)
                .requires(Items.INK_SAC)
                .requires(Items.PAPER)
                .requires(PokeModItems.RING_TARGET)
                .unlockedBy(getHasName(PokeModItems.RING_TARGET), has(PokeModItems.RING_TARGET))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.WIDE_LENS)
                .define('X', Items.IRON_INGOT)
                .define('Y', Items.GLASS_PANE)
                .pattern("XY")
                .pattern("XY")
                .pattern("X ")
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.BURN_DRIVE)
                .define('X', Items.IRON_BLOCK)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', PokeModItems.FIRE_GEM)
                .pattern("XYX")
                .pattern("YZY")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.FIRE_GEM), has(PokeModItems.FIRE_GEM))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.CHILL_DRIVE)
                .define('X', Items.IRON_BLOCK)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', PokeModItems.ICE_GEM)
                .pattern("XYX")
                .pattern("YZY")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.ICE_GEM), has(PokeModItems.ICE_GEM))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.DOUSE_DRIVE)
                .define('X', Items.IRON_BLOCK)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', PokeModItems.WATER_GEM)
                .pattern("XYX")
                .pattern("YZY")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.WATER_GEM), has(PokeModItems.WATER_GEM))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PokeModItems.SHOCK_DRIVE)
                .define('X', Items.IRON_BLOCK)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', PokeModItems.ELECTRIC_GEM)
                .pattern("XYX")
                .pattern("YZY")
                .pattern("XYX")
                .unlockedBy(getHasName(PokeModItems.ELECTRIC_GEM), has(PokeModItems.ELECTRIC_GEM))
                .save(consumer);


        //Gems
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.BUG_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.SLIME_BALL)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.DARK_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.NETHER_BRICK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.DRAGON_GEM)
                .requires(Items.DIAMOND)
                .requires(PokeModItems.DRAGON_SCALE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ELECTRIC_GEM)
                .requires(Items.DIAMOND)
                .requires(PokeModItems.THUNDER_STONE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FAIRY_GEM)
                .requires(Items.DIAMOND)
                .requires(PokeModItems.SHINY_STONE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FIGHTING_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.BRICK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FIRE_GEM)
                .requires(Items.DIAMOND)
                .requires(PokeModItems.FIRE_STONE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FLYING_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.FEATHER)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.GHOST_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.GHAST_TEAR)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.GRASS_GEM)
                .requires(Items.DIAMOND)
                .requires(PokeModItems.LEAF_STONE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.GROUND_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.BONE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ICE_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.SNOWBALL)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.NORMAL_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.POISON_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.SPIDER_EYE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.PSYCHIC_GEM)
                .requires(Items.DIAMOND)
                .requires(Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ROCK_GEM)
                .requires(Items.DIAMOND)
                .requires(PokeModItems.EVERSTONE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.STEEL_GEM)
                .requires(Items.DIAMOND)
                .requires(PokeModItems.ALUMINUM_INGOT)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.WATER_GEM)
                .requires(Items.DIAMOND)
                .requires(PokeModItems.WATER_STONE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        //Incense Items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.FULL_INCENSE)
                .requires(Items.BLAZE_POWDER ,2)
                .requires(Items.IRON_BLOCK)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.LAX_INCENSE)
                .requires(Items.BLAZE_POWDER ,2)
                .requires(Items.FEATHER)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.LUCK_INCENSE)
                .requires(Items.BLAZE_POWDER ,2)
                .requires(Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ODD_INCENSE)
                .requires(Items.BLAZE_POWDER,2)
                .requires(Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.PURE_INCENSE)
                .requires(Items.BLAZE_POWDER,2)
                .requires(Items.CARROT)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.CARROT), has(Items.CARROT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ROCK_INCENSE)
                .requires(Items.BLAZE_POWDER,2)
                .requires(Items.STONE)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.STONE), has(Items.STONE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.ROSE_INCENSE)
                .requires(Items.BLAZE_POWDER,2)
                .requires(PokeModItems.LEAF_STONE)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(PokeModItems.LEAF_STONE), has(PokeModItems.LEAF_STONE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.SEA_INCENSE)
                .requires(Items.BLAZE_POWDER,2)
                .requires(PokeModItems.WATER_STONE)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(PokeModItems.WATER_STONE), has(PokeModItems.WATER_STONE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PokeModItems.WAVE_INCENSE)
                .requires(Items.BLAZE_POWDER,2)
                .requires(Items.WATER_BUCKET)
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
                .save(consumer);
        */
    }
}