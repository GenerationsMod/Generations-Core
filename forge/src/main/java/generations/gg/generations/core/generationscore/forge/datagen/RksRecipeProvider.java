package generations.gg.generations.core.generationscore.forge.datagen;

import com.cobblemon.mod.common.CobblemonItems;
import com.cobblemon.mod.common.pokemon.Species;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.GenerationsRecipeProvider;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.RksRecipeJsonBuilder;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.recipe.PokemonIngredient;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Consumer;

import static generations.gg.generations.core.generationscore.world.item.GenerationsItems.*;
import static generations.gg.generations.core.generationscore.world.item.GenerationsItems.BEEDRILLITE;

public class RksRecipeProvider extends GenerationsRecipeProvider.Proxied {
    public RksRecipeProvider(PackOutput arg) {
        super(arg);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(LegendKeys.MEWTWO, 70)
                .key(LegendKeys.MEWTWO)
                .pattern("XAX")
                .pattern("XBX")
                .pattern("XCX")
                .input('X', MEW_FOSSIL.get())
                .input('A', DNA_SPLICERS.get())
                .input('B', ORB.get())
                .input('C', MEW_DNA_FIBER.get())
                .criterion("mew_fossil", InventoryChangeTrigger.TriggerInstance.hasItems(MEW_FOSSIL.get()))
                .offerTo(exporter, GenerationsCore.id("mewtwo"));

        RksRecipeJsonBuilder.create(WONDER_EGG.get())
                .key(LegendKeys.MANAPHY)
                .pattern("XXX")
                .pattern("ABC")
                .pattern("ZZZ")
                .input('X', WATER_GEM.get())
                .input('A', Items.EGG)
                .input('B', ORB.get())
                .input('C', Items.HEART_OF_THE_SEA)
                .input('Z', CobblemonItems.MYSTIC_WATER)
                .criterion("heart_of_the_sea", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HEART_OF_THE_SEA))
                .offerTo(exporter, GenerationsCore.id("wonder_egg"));

        RksRecipeJsonBuilder.create(LegendKeys.TYPE_NULL)
                .key(LegendKeys.TYPE_NULL)
                .pattern("DAE")
                .pattern("FBG")
                .pattern("ZCZ")
                .input('A', Items.NETHERITE_CHESTPLATE)
                .input('B', Items.NETHERITE_LEGGINGS)
                .input('C', Items.NETHERITE_BOOTS)
                .input('D', Items.NETHERITE_AXE)
                .input('E', Items.NETHERITE_HELMET)
                .input('F', Items.TOTEM_OF_UNDYING)
                .input('G', BUG_MEMORY_DRIVE.get()) //TODO: Replace with memory drive tag
                .input('Z', Items.NETHERITE_BLOCK)
                .criterion("netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .offerTo(exporter, GenerationsCore.id("type_null"));

        RksRecipeJsonBuilder.create(SOUL_HEART.get())
                .key(LegendKeys.MAGEARNA)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .input('A', HEART_SCALE.get())
                .input('B', ORB.get())
                .criterion("heart_scale", InventoryChangeTrigger.TriggerInstance.hasItems(HEART_SCALE.get()))
                .offerTo(exporter, GenerationsCore.id("soul_heart"));


//        RksRecipeJsonBuilder.create(LegendKeys.MAGEARNA.createProperties(70))
//                .key(LegendKeys.TYPE_NULL)
//                .pattern(" A ")
//                .pattern("CBC")
//                .pattern(" A ")
//                .input('A', Items.NETHERITE_INGOT)
//                .input('B', itemStack)
//                .input('C', Items.IRON_INGOT)
//                .criterion("netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
//                .offerTo(exporter, GenerationsCore.id("type_null"));

        createFossil(OLD_AMBER, "aerodactyl", exporter);
        createFossil(HELIX_FOSSIL, "omanyte", exporter);
        createFossil(DOME_FOSSIL, "kabuto", exporter);
        createFossil(ROOT_FOSSIL, "lileep", exporter);
        createFossil(CLAW_FOSSIL, "anorith", exporter);
        createFossil(SKULL_FOSSIL, "cranidos", exporter);
        createFossil(ARMOR_FOSSIL, "shieldon", exporter);
        createFossil(COVER_FOSSIL, "tirtouga", exporter);
        createFossil(PLUME_FOSSIL, "archen", exporter);
        createFossil(JAW_FOSSIL, "tyrunt", exporter);
        createFossil(SAIL_FOSSIL, "amaura", exporter);
        createFossil(DRAKE_FOSSIL, BIRD_FOSSIL, "dracozolt", exporter);
        createFossil(DRAKE_FOSSIL, FISH_FOSSIL, "dracovish", exporter);
        createFossil(DINO_FOSSIL, BIRD_FOSSIL, "arctozolt", exporter);
        createFossil(DINO_FOSSIL, FISH_FOSSIL, "arctovish", exporter);

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

        createParadoxFuture("ironthreads", "donphan", exporter);
        createParadoxFuture("ironbundle", "delibird", exporter);
        createParadoxFuture("ironhands", "hariyama", exporter);
        createParadoxFuture("ironjugulis", "hydreigon", exporter);
        createParadoxFuture("ironmoth", "volcarona", exporter);
        createParadoxFuture("ironthorns", "tyranitar", exporter);
        createParadoxFuture("ironvaliant", "gallade", exporter);
        createParadoxFuture("ironleaves", "virizion", exporter);
        createParadoxFuture("ironboulder", "terrakion", exporter);
        createParadoxFuture("ironcrown", "cobalion", exporter);

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
    }

    private <E> void createZCyrstal(RegistrySupplier<Item> result, String pokemon, String aspects, Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(result.get())
                .input('A', Items.NETHERITE_SCRAP)
                .input('B', new PokemonIngredient(new SpeciesKey(pokemon, Set.of(aspects)), false))
                .input('X', Z_INGOT.get())
                .pattern("XXX")
                .pattern("ABA")
                .pattern("XXX")
                .criterion(BuiltInRegistries.ITEM.getKey(Z_INGOT.get()).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(Z_INGOT.get()))
                .offerTo(exporter, result.getId().withSuffix("_" + aspects.replace("-", "_")));
    }

    private void createParadoxPast(String paradoxPokemon, String toBeConverted, Consumer<FinishedRecipe> exporter) {
        createParadox(paradoxPokemon, toBeConverted, exporter, Items.COAL);
    }

    private void createParadoxFuture(String paradoxPokemon, String toBeConverted, Consumer<FinishedRecipe> exporter) {
        createParadox(paradoxPokemon, toBeConverted, exporter, Items.REDSTONE);
    }

    private void createZCyrstal(RegistrySupplier<Item> result, String pokemon, Consumer<FinishedRecipe> exporter) {
        createZCyrstal(result, pokemon, false, exporter);
    }

    private void createZCyrstal(RegistrySupplier<Item> result, String pokemon, boolean multi, Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(result.get())
                .input('A', Items.NETHERITE_SCRAP)
                .input('B', new PokemonIngredient(pokemon, false))
                .input('X', Z_INGOT.get())
                .pattern("XXX")
                .pattern("ABA")
                .pattern("XXX")
                .doesntConsumeTimeCapsules()
                .criterion(BuiltInRegistries.ITEM.getKey(Z_INGOT.get()).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(Z_INGOT.get()))
                .offerTo(exporter, result.getId().withSuffix(multi ? "_" + pokemon : ""));
    }

    private void createZCyrstal(RegistrySupplier<Item> result, RegistrySupplier<Item> item, Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(result.get())
                .input('A', Items.NETHERITE_SCRAP)
                .input('B', Ingredient.of(item.get()))
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

        var builder = RksRecipeJsonBuilder.create(result.get())
                .input('A', KEY_STONE.get())
                .input('B', new PokemonIngredient(pokemon, false))
                .input('C', Items.NETHERITE_SCRAP);
        if(special != null) builder.input('D', item).pattern(" D ".toUpperCase());
        builder.pattern("ABC")
                .doesntConsumeTimeCapsules()
                .criterion(KEY_STONE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(KEY_STONE.get()))
                .offerTo(exporter, result.getId());
    }



    private void createParadox(String name, String toBeConverted, Consumer<FinishedRecipe> exporter, Item item) {
        RksRecipeJsonBuilder.create(name, false, true)
                .input('A', new PokemonIngredient(toBeConverted, false))
                .input('B', item)
                .pattern("AB")
                .criterion(BuiltInRegistries.ITEM.getKey(item).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item))
                .offerTo(exporter, GenerationsCore.id(name));
    }

    private void createFossil(RegistrySupplier<Item> item, String name, Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(name, false, false)
                .pattern("A")
                .input('A', item.get())
                .criterion(item.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item.get()))
                .offerTo(exporter, GenerationsCore.id(name));
    }

    private void createFossil(RegistrySupplier<Item> item, RegistrySupplier<Item> item2, String name, Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(name)
                .pattern("AB")
                .input('A', item.get())
                .input('B', item2.get())
                .criterion(item.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item.get()))
                .offerTo(exporter, GenerationsCore.id(name));
    }
}
