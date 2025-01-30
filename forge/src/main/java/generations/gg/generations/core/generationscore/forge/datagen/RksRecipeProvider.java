package generations.gg.generations.core.generationscore.forge.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.common.world.recipe.DamageIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.PokemonItemIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapelessRksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.TimeCapsuleIngredient;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.GenerationsRecipeProvider;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.RksRecipeJsonBuilder;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.ShapedRksRecipeJsonBuilder;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.ShapelessRksRecipeJsonBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Consumer;

import static com.cobblemon.mod.common.CobblemonItems.*;
import static generations.gg.generations.core.generationscore.common.world.item.GenerationsItems.*;

public class RksRecipeProvider extends GenerationsRecipeProvider.Proxied {
    public RksRecipeProvider(PackOutput arg) {
        super(arg);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> exporter) {
        ShapedRksRecipeJsonBuilder.create(LegendKeys.MEWTWO, 70)
                .pattern("XAX")
                .pattern("XBX")
                .pattern("XCX")
                .input('X', MEW_FOSSIL.get())
                .input('A', DNA_SPLICERS.get())
                .input('B', ORB.get())
                .input('C', MEW_DNA_FIBER.get())
                .key(LegendKeys.MEWTWO)
                .criterion("mew_fossil", InventoryChangeTrigger.TriggerInstance.hasItems(MEW_FOSSIL.get()))
                .offerTo(exporter, GenerationsCore.id("mewtwo"));

        ShapedRksRecipeJsonBuilder.create(WONDER_EGG.get())
                .pattern("XXX")
                .pattern("ABC")
                .pattern("ZZZ")
                .input('X', WATER_GEM)
                .input('A', Items.EGG)
                .input('B', ORB.get())
                .input('C', Items.HEART_OF_THE_SEA)
                .input('Z', MYSTIC_WATER)
                .key(LegendKeys.MANAPHY)
                .criterion("heart_of_the_sea", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HEART_OF_THE_SEA))
                .offerTo(exporter, GenerationsCore.id("wonder_egg"));

        ShapedRksRecipeJsonBuilder.create(LegendKeys.TYPE_NULL)
                .pattern("DAE")
                .pattern("FBG")
                .pattern("ZCZ")
                .input('A', Items.NETHERITE_CHESTPLATE)
                .input('B', Items.NETHERITE_LEGGINGS)
                .input('C', Items.NETHERITE_BOOTS)
                .input('D', Items.NETHERITE_AXE)
                .input('E', Items.NETHERITE_HELMET)
                .input('F', Items.TOTEM_OF_UNDYING)
                .input('G', GenerationsItemTags.MEMORY_DRIVES)
                .input('Z', Items.NETHERITE_BLOCK)
                .key(LegendKeys.TYPE_NULL)
                .criterion("netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .offerTo(exporter, GenerationsCore.id("type_null"));

        ShapedRksRecipeJsonBuilder.create(SOUL_HEART.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .input('A', HEART_SCALE.get())
                .input('B', ORB.get())
                .key(LegendKeys.MAGEARNA)
                .criterion("heart_scale", InventoryChangeTrigger.TriggerInstance.hasItems(HEART_SCALE.get()))
                .offerTo(exporter, GenerationsCore.id("soul_heart"));

        ShapedRksRecipeJsonBuilder.create(LegendKeys.MAGEARNA, 70)
                .pattern("CAC")
                .pattern("ABA")
                .pattern("CAC")
                .input('A', Items.NETHERITE_INGOT)
                .input('B', new DamageIngredient(SOUL_HEART.get(), 100))
                .input('C', Items.IRON_INGOT)
                .key(LegendKeys.MAGEARNA)
                .criterion("soul_heart", InventoryChangeTrigger.TriggerInstance.hasItems(SOUL_HEART.get()))
                .offerTo(exporter, GenerationsCore.id("magearna"));

        ShapedRksRecipeJsonBuilder.create(LegendKeys.GENESECT)
                .pattern("SOB")
                .pattern("CND")
                .pattern("RNR")
                .input('S', SHOCK_DRIVE.get())
                .input('N', Items.NETHERITE_HELMET)
                .input('B', BURN_DRIVE.get())
                .input('C', CHILL_DRIVE.get())
                .input('O', ORB.get())
                .input('D', DOUSE_DRIVE.get())
                .input('R', Items.REDSTONE)
                .key(LegendKeys.GENESECT)
                .criterion("orb", InventoryChangeTrigger.TriggerInstance.hasItems(ORB.get()))
                .offerTo(exporter, GenerationsCore.id("genesect"));

        ShapedRksRecipeJsonBuilder.create(SACRED_ASH.get()) //TODO: Add SpeciesKey if needed
                .pattern("ABC")
                .input('A', Items.TOTEM_OF_UNDYING)
                .input('B', Items.COAL)
                .input('C',RAINBOW_WING.get())
                .criterion("rainbow_wing", InventoryChangeTrigger.TriggerInstance.hasItems(RAINBOW_WING.get()))
                .offerTo(exporter, GenerationsCore.id("rainbow_wing"));

        createParadoxPast("walkingwake", "suicune", exporter);
        createParadoxPast("greattusk", "donphan", exporter);
        createParadoxPast("screamtail", "jigglypuff", exporter);
        createParadoxPast("brutebonnet", "amoonguss", exporter);
        createParadoxPast("fluttermane", "misdreavus", exporter);
        createParadoxPast("slitherwing", "volcarona", exporter);
        createParadoxPast("sandyshocks", "magneton", exporter);
        createParadoxPast("roaringmoon", "salamence", exporter);
        createParadoxPast("gougingfire", "entei", exporter);
        createParadoxPast("ragingbolt", "raikou", exporter);
        createParadoxPast("koraidon", "cyclizar", exporter);

        createParadoxFuture("irontreads", "donphan", exporter);
        createParadoxFuture("ironbundle", "delibird", exporter);
        createParadoxFuture("ironhands", "hariyama", exporter);
        createParadoxFuture("ironjugulis", "hydreigon", exporter);
        createParadoxFuture("ironmoth", "volcarona", exporter);
        createParadoxFuture("ironthorns", "tyranitar", exporter);
        createParadoxFuture("_gardevoir", "ironvaliant", "gardevoir", exporter);
        createParadoxFuture("_gallade", "ironvaliant", "gallade", exporter);
        createParadoxFuture("ironleaves", "virizion", exporter);
        createParadoxFuture("ironboulder", "terrakion", exporter);
        createParadoxFuture("ironcrown", "cobalion", exporter);
        createParadoxFuture("miraidon", "cyclizar", exporter);

        createMegaStone(GENGARITE, "gengar", exporter);
        createMegaStone(GARDEVOIRITE, "gardevoir", exporter);
        createMegaStone(AMPHAROSITE, "ampharos", exporter);
        createMegaStone(VENUSAURITE, "venasuar", exporter);
        createMegaStone(CHARIZARDITE_X, "charizard", exporter);
        createMegaStone(BLASTOISINITE, "blastoise", exporter);
        createMegaStone(MEWTWONITE_X, "mewtwo", exporter);
        createMegaStone(MEWTWONITE_Y, "mewtwo", exporter);
        createMegaStone(BLAZIKENITE, "blaziken", exporter);
        createMegaStone(MEDICHAMITE, "medicham", exporter);
        createMegaStone(HOUNDOOMINITE, "houndoom", exporter);
        createMegaStone(AGGRONITE, "aggron", exporter);
        createMegaStone(BANETTITE, "banette", exporter);
        createMegaStone(TYRANITARITE, "tyranitar", exporter);
        createMegaStone(SCIZORITE, "scizor", exporter);
        createMegaStone(PINSIRITE, "pinsir", exporter);
        createMegaStone(AERODACTYLITE, "aerodactyl", exporter);
        createMegaStone(LUCARIONITE, "lucario", exporter);
        createMegaStone(ABOMASITE, "abomasnow", exporter);
        createMegaStone(KANGASKHANITE, "kangaskhan", exporter);
        createMegaStone(GYARADOSITE, "gyarados", exporter);
        createMegaStone(ABSOLITE, "absol", exporter);
        createMegaStone(CHARIZARDITE_Y, "charizard", exporter);
        createMegaStone(ALAKAZITE, "alakazam", exporter);
        createMegaStone(HERACRONITE, "heracros", exporter);
        createMegaStone(MAWILITE, "mawile", exporter);
        createMegaStone(MANECTITE, "manectric", exporter);
        createMegaStone(GARCHOMPITE, "garchomp", exporter);
        createMegaStone(LATIASITE, "latias", exporter);
        createMegaStone(LATIOSITE, "latios", exporter);
        createMegaStone(SWAMPERTITE, "swampert", exporter);
        createMegaStone(SCEPTILITE, "sceptile", exporter);
        createMegaStone(SABLENITE, "sableye", exporter);
        createMegaStone(ALTARIANITE, "altaria", exporter);
        createMegaStone(GALLADITE, "gallade", exporter);
        createMegaStone(AUDINITE, "audino", exporter);
        createMegaStone(METAGROSSITE, "metagross", exporter);
        createMegaStone(SHARPEDONITE, "sharpedo", exporter);
        createMegaStone(STEELIXITE, "steelix", exporter);
        createMegaStone(PIDGEOTITE, "pidgeot", exporter);
        createMegaStone(GLALITITE, "glalie", exporter);
        createMegaStone(DIANCITE, "diancie", exporter);
        createMegaStone(CAMERUPTITE, "camerupt", exporter);
        createMegaStone(LOPUNNNITE, "lopuuny", exporter);
        createMegaStone(SALAMENCITE, "salamence", exporter);
        createMegaStone(BEEDRILLITE, "beedrill", exporter);

        createZCyrstal(BUGINIUM_Z, BUG_GEM, exporter);
        createZCyrstal(DARKINIUM_Z, DARK_GEM, exporter);
        createZCyrstal(DRAGONIUM_Z, DRAGON_GEM, exporter);
        createZCyrstal(ELECTRIUM_Z, ELECTRIC_GEM, exporter);
        createZCyrstal(FAIRIUM_Z, FAIRY_GEM, exporter);
        createZCyrstal(FIGHTINIUM_Z, FIGHTING_GEM, exporter);
        createZCyrstal(FIRIUM_Z, FIRE_GEM, exporter);
        createZCyrstal(FLYINIUM_Z, FLYING_GEM, exporter);
        createZCyrstal(GHOSTIUM_Z, GHOST_GEM, exporter);
        createZCyrstal(GRASSIUM_Z, GRASS_GEM, exporter);
        createZCyrstal(GROUNDIUM_Z, GROUND_GEM, exporter);
        createZCyrstal(ICIUM_Z, ICE_GEM, exporter);
        createZCyrstal(NORMALIUM_Z, NORMAL_GEM, exporter);
        createZCyrstal(POISONIUM_Z, POISON_GEM, exporter);
        createZCyrstal(PSYCHIUM_Z, PSYCHIC_GEM, exporter);
        createZCyrstal(ROCKIUM_Z, ROCK_GEM, exporter);
        createZCyrstal(STEELIUM_Z, STEEL_GEM, exporter);
        createZCyrstal(WATERIUM_Z, WATER_GEM, exporter);

        createZCyrstal(ALORAICHIUM_Z, "raichu", "alolan", exporter);
        createZCyrstal(DECIDIUM_Z, "decidueye", exporter);
        createZCyrstal(EEVIUM_Z, "eevee", exporter);
        createZCyrstal(INCINIUM_Z, "incinium", exporter);
        createZCyrstal(KOMMONIUM_Z, "kommoo", exporter);
        createZCyrstal(LUNALIUM_Z, "lunala", exporter);
        createZCyrstal(LYCANIUM_Z, "lycanrock", exporter);
        createZCyrstal(MARSHADIUM_Z, "marashadow", exporter);
        createZCyrstal(MEWNIUM_Z, "mew", exporter);
        createZCyrstal(MIMIKIUM_Z, "mimikyu", exporter);
        createZCyrstal(PIKANIUM_Z, "pikachu", exporter);
        createZCyrstal(PIKASHUNIUM_Z, "pikachu", "rock-star", exporter);
        createZCyrstal(PIKASHUNIUM_Z, "pikachu", "belle", exporter);
        createZCyrstal(PIKASHUNIUM_Z, "pikachu", "cosplayer", exporter);
        createZCyrstal(PIKASHUNIUM_Z, "pikachu", "pop-star", exporter);
        createZCyrstal(PIKASHUNIUM_Z, "pikachu", "phd", exporter);
        createZCyrstal(PIKASHUNIUM_Z, "pikachu", "libre", exporter);
        createZCyrstal(PIKASHUNIUM_Z, "pikachu", "original", exporter);
        createZCyrstal(PRIMARIUM_Z, "primarina", exporter);
        createZCyrstal(SNORLIUM_Z, "snorlax", exporter);
        createZCyrstal(SOLGANIUM_Z, "solgaleo", exporter);
        createZCyrstal(TAPUNIUM_Z, "tapukoko", true, exporter);
        createZCyrstal(TAPUNIUM_Z, "tapulele", true, exporter);
        createZCyrstal(TAPUNIUM_Z, "tapubulu", true, exporter);
        createZCyrstal(TAPUNIUM_Z, "tapufini", true, exporter);
        createZCyrstal(ULTRANECROZIUM_Z, "necrozma", exporter);

        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_A, "a");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_B, "b");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_C, "c");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_D, "d");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_E, "e");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_F, "f");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_G, "g");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_H, "h");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_I, "i");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_J, "j");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_K, "k");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_L, "l");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_M, "m");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_N, "n");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_O, "o");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_P, "p");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_Q, "q");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_R, "r");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_S, "s");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_T, "t");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_U, "u");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_V, "v");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_W, "w");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_X, "x");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_Y, "y");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_Z, "z");

        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_EXCLAMATION_MARK, "!");
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_QUESTION_MARK, "?");
    }

    private <E> void createZCyrstal(RegistrySupplier<Item> result, String pokemon, String aspects, Consumer<FinishedRecipe> exporter) {
        ShapedRksRecipeJsonBuilder.create(result.get())
                .input('A', Items.NETHERITE_SCRAP)
                .input('B', new TimeCapsuleIngredient(new SpeciesKey(pokemon, Set.of(aspects)), false))
                .input('X', Z_INGOT.get())
                .pattern("XXX")
                .pattern("ABA")
                .pattern("XXX")
                .criterion(Z_INGOT.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(Z_INGOT.get()))
                .offerTo(exporter, result.getId().withSuffix("_" + aspects.replace("-", "_")));
    }

    private void createParadoxPast(String paradoxPokemon, String toBeConverted, Consumer<FinishedRecipe> exporter) {
        createParadox(paradoxPokemon, toBeConverted, exporter, Items.COAL);
    }

    private void createParadoxFuture(String suffix, String paradoxPokemon, String toBeConverted, Consumer<FinishedRecipe> exporter) {
        createParadox(suffix, paradoxPokemon, toBeConverted, exporter, Items.REDSTONE);
    }

    private void createParadoxFuture(String paradoxPokemon, String toBeConverted, Consumer<FinishedRecipe> exporter) {
        createParadox(paradoxPokemon, toBeConverted, exporter, Items.REDSTONE);
    }

    private void createZCyrstal(RegistrySupplier<Item> result, String pokemon, Consumer<FinishedRecipe> exporter) {
        createZCyrstal(result, pokemon, false, exporter);
    }

    private void createZCyrstal(RegistrySupplier<Item> result, String pokemon, boolean multi, Consumer<FinishedRecipe> exporter) {
        ShapedRksRecipeJsonBuilder.create(result.get())
                .input('A', Items.NETHERITE_SCRAP)
                .input('B', new TimeCapsuleIngredient(pokemon, false))
                .input('X', Z_INGOT.get())
                .pattern("XXX")
                .pattern("ABA")
                .pattern("XXX")
                .doesntConsumeTimeCapsules()
                .criterion(BuiltInRegistries.ITEM.getKey(Z_INGOT.get()).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(Z_INGOT.get()))
                .offerTo(exporter, result.getId().withSuffix(multi ? "_" + pokemon : ""));
    }

    private void unownBlock(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<Block> createdBlock, String form){
        ShapelessRksRecipeJsonBuilder.create(createdBlock.get())
                .requires(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock())
                .requires(new PokemonItemIngredient(new ResourceLocation("cobblemon", "unown"), Set.of("glyph-" + form)))
                .criterion(getHasName(GenerationsBlocks.UNOWN_BLOCK_BLANK.get()), has(GenerationsBlocks.UNOWN_BLOCK_BLANK.get()))
                .offerTo(consumer, createdBlock.getId());
    }

    private void createZCyrstal(RegistrySupplier<Item> result, Item item, Consumer<FinishedRecipe> exporter) {
        ShapedRksRecipeJsonBuilder.create(result.get())
                .input('A', Items.NETHERITE_SCRAP)
                .input('B', item)
                .input('X', Z_INGOT.get())
                .pattern("XXX")
                .pattern("ABA")
                .pattern("XXX")
                .criterion(BuiltInRegistries.ITEM.getKey(Z_INGOT.get()).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(Z_INGOT.get()))
                .offerTo(exporter, result.getId());
    }

    private void createMegaStone(RegistrySupplier<Item> result, String pokemon, Consumer<FinishedRecipe> exporter) {
        var phrase = result.getId().getPath();
        var index = phrase.indexOf("_");
        Character special = index != -1 ? phrase.charAt(index + 1) : null;

        var item = Items.AIR;

        if(special != null) {
            switch (special) {
                case 'x' -> {
                    item = Items.COAL;

                }
                case 'y' -> {
                    item = Items.REDSTONE;
                }
            }
        }

        var builder = ShapedRksRecipeJsonBuilder.create(result.get())
                .input('A', KEY_STONE.get())
                .input('B', new TimeCapsuleIngredient(pokemon, false))
                .input('C', Items.NETHERITE_SCRAP);
        if(special != null) builder.input('D', item).pattern(" D ".toUpperCase());
        builder.pattern("ABC")
                .doesntConsumeTimeCapsules()
                .criterion(KEY_STONE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(KEY_STONE.get()))
                .offerTo(exporter, result.getId());
    }

    private void createParadox(String name, String toBeConverted, Consumer<FinishedRecipe> exporter, Item item) {
        createParadox("", name, toBeConverted, exporter, item);
    }

    private void createParadox(String suffix, String name, String toBeConverted, Consumer<FinishedRecipe> exporter, Item item) {
        ShapelessRksRecipeJsonBuilder.create(name, false, true)
                .requires(new TimeCapsuleIngredient(toBeConverted, false))
                .requires(item)
                .criterion(BuiltInRegistries.ITEM.getKey(item).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item))
                .offerTo(exporter, GenerationsCore.id(name +  suffix));
    }

    private void createFossil(RegistrySupplier<Item> item, String name, Consumer<FinishedRecipe> exporter) {
        ShapedRksRecipeJsonBuilder.create(name, false, false)
                .pattern("A")
                .input('A', item.get())
                .criterion(item.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item.get()))
                .offerTo(exporter, GenerationsCore.id(name));
    }

    private void createFossil(RegistrySupplier<Item> item, RegistrySupplier<Item> item2, String name, Consumer<FinishedRecipe> exporter) {
        ShapedRksRecipeJsonBuilder.create(name)
                .pattern("AB")
                .input('A', item.get())
                .input('B', item2.get())
                .criterion(item.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item.get()))
                .offerTo(exporter, GenerationsCore.id(name));
    }
}
