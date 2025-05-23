package generations.gg.generations.core.generationscore.forge.datagen

import com.cobblemon.mod.common.CobblemonItems
import com.cobblemon.mod.common.CobblemonItems.MYSTIC_WATER
import com.cobblemon.mod.common.CobblemonItems.WATER_GEM
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems.DNA_SPLICERS
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems.MEW_FOSSIL
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.recipe.DamageIngredient
import generations.gg.generations.core.generationscore.common.world.recipe.PokemonItemIngredient
import generations.gg.generations.core.generationscore.common.world.recipe.TimeCapsuleIngredient
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.GenerationsRecipeProvider
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.ShapedRksRecipeJsonBuilder
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.ShapelessRksRecipeJsonBuilder
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.ShapelessRksRecipeJsonBuilder.Companion.create
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import java.util.*
import java.util.Set
import java.util.concurrent.CompletableFuture

class RksRecipeProvider(arg: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : GenerationsRecipeProvider.Proxied(arg, registries) {
    override fun buildRecipes(exporter: RecipeOutput) {
        ShapedRksRecipeJsonBuilder.create(LegendKeys.MEWTWO, 70)
            .pattern("XAX")
            .pattern("XBX")
            .pattern("XCX")
            .input('X', MEW_FOSSIL.get())
            .input('A', DNA_SPLICERS.get())
            .input('B', GenerationsItems.ORB.get())
            .input('C', GenerationsItems.MEW_DNA_FIBER.get())
            .key(LegendKeys.MEWTWO)
            .criterion("mew_fossil", InventoryChangeTrigger.TriggerInstance.hasItems(MEW_FOSSIL.get()))
            .save(exporter, id("mewtwo"))

        ShapedRksRecipeJsonBuilder.create(GenerationsItems.WONDER_EGG.get())
            .pattern("XXX")
            .pattern("ABC")
            .pattern("ZZZ")
            .input('X', WATER_GEM)
            .input('A', Items.EGG)
            .input('B', GenerationsItems.ORB.get())
            .input('C', Items.HEART_OF_THE_SEA)
            .input('Z', MYSTIC_WATER)
            .key(LegendKeys.MANAPHY)
            .criterion("heart_of_the_sea", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HEART_OF_THE_SEA))
            .save(exporter, id("wonder_egg"))

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
            .save(exporter, id("type_null"))

        ShapedRksRecipeJsonBuilder.create(GenerationsItems.SOUL_HEART.get())
            .pattern(" A ")
            .pattern("ABA")
            .pattern(" A ")
            .input('A', GenerationsItems.HEART_SCALE.get())
            .input('B', GenerationsItems.ORB.get())
            .key(LegendKeys.MAGEARNA)
            .criterion("heart_scale", InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.HEART_SCALE.get()))
            .save(exporter, id("soul_heart"))

        ShapedRksRecipeJsonBuilder.create(GenerationsItems.SOUL_HEART.get())
            .pattern("A A")
            .pattern(" B ")
            .pattern("A A")
            .input('A', GenerationsItems.HEART_SCALE.get())
            .input('B', GenerationsItems.ORB.get())
            .key(LegendKeys.MAGEARNA)
            .criterion("heart_scale", InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.HEART_SCALE.get()))
            .save(exporter, id("soul_heart_alt"))

        ShapedRksRecipeJsonBuilder.create(LegendKeys.MAGEARNA, 70)
            .pattern("CAC")
            .pattern("ABA")
            .pattern("CAC")
            .input('A', Items.NETHERITE_INGOT)
            .input('B', DamageIngredient(GenerationsItems.SOUL_HEART.get(), 100))
            .input('C', Items.IRON_INGOT)
            .key(LegendKeys.MAGEARNA)
            .criterion("soul_heart", InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.SOUL_HEART.get()))
            .save(exporter, id("magearna"))

        ShapedRksRecipeJsonBuilder.create(LegendKeys.GENESECT)
            .pattern("SOB")
            .pattern("CND")
            .pattern("RNR")
            .input('S', GenerationsItems.SHOCK_DRIVE.get())
            .input('N', Items.NETHERITE_HELMET)
            .input('B', GenerationsItems.BURN_DRIVE.get())
            .input('C', GenerationsItems.CHILL_DRIVE.get())
            .input('O', GenerationsItems.ORB.get())
            .input('D', GenerationsItems.DOUSE_DRIVE.get())
            .input('R', Items.REDSTONE)
            .key(LegendKeys.GENESECT)
            .criterion("orb", InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.ORB.get()))
            .save(exporter, id("genesect"))

        ShapedRksRecipeJsonBuilder.create(GenerationsItems.SACRED_ASH.get()) //TODO: Add SpeciesKey if needed
            .pattern("ABC")
            .input('A', Items.TOTEM_OF_UNDYING)
            .input('B', Items.COAL)
            .input('C', GenerationsItems.RAINBOW_WING.get())
            .criterion("rainbow_wing", InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.RAINBOW_WING.get()))
            .save(exporter, id("rainbow_wing"))

        createParadoxPast("walkingwake", "suicune", exporter)
        createParadoxPast("greattusk", "donphan", exporter)
        createParadoxPast("screamtail", "jigglypuff", exporter)
        createParadoxPast("brutebonnet", "amoonguss", exporter)
        createParadoxPast("fluttermane", "misdreavus", exporter)
        createParadoxPast("slitherwing", "volcarona", exporter)
        createParadoxPast("sandyshocks", "magneton", exporter)
        createParadoxPast("roaringmoon", "salamence", exporter)
        createParadoxPast("gougingfire", "entei", exporter)
        createParadoxPast("ragingbolt", "raikou", exporter)
        createParadoxPast("koraidon", "cyclizar", exporter)

        createParadoxFuture("irontreads", "donphan", exporter)
        createParadoxFuture("ironbundle", "delibird", exporter)
        createParadoxFuture("ironhands", "hariyama", exporter)
        createParadoxFuture("ironjugulis", "hydreigon", exporter)
        createParadoxFuture("ironmoth", "volcarona", exporter)
        createParadoxFuture("ironthorns", "tyranitar", exporter)
        createParadoxFuture("_gardevoir", "ironvaliant", "gardevoir", exporter)
        createParadoxFuture("_gallade", "ironvaliant", "gallade", exporter)
        createParadoxFuture("ironleaves", "virizion", exporter)
        createParadoxFuture("ironboulder", "terrakion", exporter)
        createParadoxFuture("ironcrown", "cobalion", exporter)
        createParadoxFuture("miraidon", "cyclizar", exporter)

        createMegaStone(GenerationsItems.GENGARITE, "gengar", exporter)
        createMegaStone(GenerationsItems.GARDEVOIRITE, "gardevoir", exporter)
        createMegaStone(GenerationsItems.AMPHAROSITE, "ampharos", exporter)
        createMegaStone(GenerationsItems.VENUSAURITE, "venusaur", exporter)
        createMegaStone(GenerationsItems.CHARIZARDITE_X, "charizard", exporter)
        createMegaStone(GenerationsItems.BLASTOISINITE, "blastoise", exporter)
        createMegaStone(GenerationsItems.MEWTWONITE_X, "mewtwo", exporter)
        createMegaStone(GenerationsItems.MEWTWONITE_Y, "mewtwo", exporter)
        createMegaStone(GenerationsItems.BLAZIKENITE, "blaziken", exporter)
        createMegaStone(GenerationsItems.MEDICHAMITE, "medicham", exporter)
        createMegaStone(GenerationsItems.HOUNDOOMINITE, "houndoom", exporter)
        createMegaStone(GenerationsItems.AGGRONITE, "aggron", exporter)
        createMegaStone(GenerationsItems.BANETTITE, "banette", exporter)
        createMegaStone(GenerationsItems.TYRANITARITE, "tyranitar", exporter)
        createMegaStone(GenerationsItems.SCIZORITE, "scizor", exporter)
        createMegaStone(GenerationsItems.PINSIRITE, "pinsir", exporter)
        createMegaStone(GenerationsItems.AERODACTYLITE, "aerodactyl", exporter)
        createMegaStone(GenerationsItems.LUCARIONITE, "lucario", exporter)
        createMegaStone(GenerationsItems.ABOMASITE, "abomasnow", exporter)
        createMegaStone(GenerationsItems.KANGASKHANITE, "kangaskhan", exporter)
        createMegaStone(GenerationsItems.GYARADOSITE, "gyarados", exporter)
        createMegaStone(GenerationsItems.ABSOLITE, "absol", exporter)
        createMegaStone(GenerationsItems.CHARIZARDITE_Y, "charizard", exporter)
        createMegaStone(GenerationsItems.ALAKAZITE, "alakazam", exporter)
        createMegaStone(GenerationsItems.HERACRONITE, "heracross", exporter)
        createMegaStone(GenerationsItems.MAWILITE, "mawile", exporter)
        createMegaStone(GenerationsItems.MANECTITE, "manectric", exporter)
        createMegaStone(GenerationsItems.GARCHOMPITE, "garchomp", exporter)
        createMegaStone(GenerationsItems.LATIASITE, "latias", exporter)
        createMegaStone(GenerationsItems.LATIOSITE, "latios", exporter)
        createMegaStone(GenerationsItems.SWAMPERTITE, "swampert", exporter)
        createMegaStone(GenerationsItems.SCEPTILITE, "sceptile", exporter)
        createMegaStone(GenerationsItems.SABLENITE, "sableye", exporter)
        createMegaStone(GenerationsItems.ALTARIANITE, "altaria", exporter)
        createMegaStone(GenerationsItems.GALLADITE, "gallade", exporter)
        createMegaStone(GenerationsItems.AUDINITE, "audino", exporter)
        createMegaStone(GenerationsItems.METAGROSSITE, "metagross", exporter)
        createMegaStone(GenerationsItems.SHARPEDONITE, "sharpedo", exporter)
        createMegaStone(GenerationsItems.STEELIXITE, "steelix", exporter)
        createMegaStone(GenerationsItems.PIDGEOTITE, "pidgeot", exporter)
        createMegaStone(GenerationsItems.GLALITITE, "glalie", exporter)
        createMegaStone(GenerationsItems.DIANCITE, "diancie", exporter)
        createMegaStone(GenerationsItems.CAMERUPTITE, "camerupt", exporter)
        createMegaStone(GenerationsItems.LOPUNNNITE, "lopunny", exporter)
        createMegaStone(GenerationsItems.SALAMENCITE, "salamence", exporter)
        createMegaStone(GenerationsItems.BEEDRILLITE, "beedrill", exporter)
        createMegaStone(GenerationsItems.SLOWBRONITE, "slowbro", exporter)

        createZCyrstal(GenerationsItems.BUGINIUM_Z, CobblemonItems.BUG_GEM, exporter)
        createZCyrstal(GenerationsItems.DARKINIUM_Z, CobblemonItems.DARK_GEM, exporter)
        createZCyrstal(GenerationsItems.DRAGONIUM_Z, CobblemonItems.DRAGON_GEM, exporter)
        createZCyrstal(GenerationsItems.ELECTRIUM_Z, CobblemonItems.ELECTRIC_GEM, exporter)
        createZCyrstal(GenerationsItems.FAIRIUM_Z, CobblemonItems.FAIRY_GEM, exporter)
        createZCyrstal(GenerationsItems.FIGHTINIUM_Z, CobblemonItems.FIGHTING_GEM, exporter)
        createZCyrstal(GenerationsItems.FIRIUM_Z, CobblemonItems.FIRE_GEM, exporter)
        createZCyrstal(GenerationsItems.FLYINIUM_Z, CobblemonItems.FLYING_GEM, exporter)
        createZCyrstal(GenerationsItems.GHOSTIUM_Z, CobblemonItems.GHOST_GEM, exporter)
        createZCyrstal(GenerationsItems.GRASSIUM_Z, CobblemonItems.GRASS_GEM, exporter)
        createZCyrstal(GenerationsItems.GROUNDIUM_Z, CobblemonItems.GROUND_GEM, exporter)
        createZCyrstal(GenerationsItems.ICIUM_Z, CobblemonItems.ICE_GEM, exporter)
        createZCyrstal(GenerationsItems.NORMALIUM_Z, CobblemonItems.NORMAL_GEM, exporter)
        createZCyrstal(GenerationsItems.POISONIUM_Z, CobblemonItems.POISON_GEM, exporter)
        createZCyrstal(GenerationsItems.PSYCHIUM_Z, CobblemonItems.PSYCHIC_GEM, exporter)
        createZCyrstal(GenerationsItems.ROCKIUM_Z, CobblemonItems.ROCK_GEM, exporter)
        createZCyrstal(GenerationsItems.STEELIUM_Z, CobblemonItems.STEEL_GEM, exporter)
        createZCyrstal(GenerationsItems.WATERIUM_Z, WATER_GEM, exporter)

        createZCyrstal<Any>(GenerationsItems.ALORAICHIUM_Z, "raichu", "alolan", exporter)
        createZCyrstal(GenerationsItems.DECIDIUM_Z, "decidueye", exporter)
        createZCyrstal(GenerationsItems.EEVIUM_Z, "eevee", exporter)
        createZCyrstal(GenerationsItems.INCINIUM_Z, "incineroar", exporter)
        createZCyrstal(GenerationsItems.KOMMONIUM_Z, "kommoo", exporter)
        createZCyrstal(GenerationsItems.LUNALIUM_Z, "lunala", exporter)
        createZCyrstal(GenerationsItems.LYCANIUM_Z, "lycanroc", exporter)
        createZCyrstal(GenerationsItems.MARSHADIUM_Z, "marshadow", exporter)
        createZCyrstal(GenerationsItems.MEWNIUM_Z, "mew", exporter)
        createZCyrstal(GenerationsItems.MIMIKIUM_Z, "mimikyu", exporter)
        createZCyrstal(GenerationsItems.PIKANIUM_Z, "pikachu", exporter)
        createZCyrstal<Any>(GenerationsItems.PIKASHUNIUM_Z, "pikachu", "rock-star", exporter)
        createZCyrstal<Any>(GenerationsItems.PIKASHUNIUM_Z, "pikachu", "belle", exporter)
        createZCyrstal<Any>(GenerationsItems.PIKASHUNIUM_Z, "pikachu", "cosplayer", exporter)
        createZCyrstal<Any>(GenerationsItems.PIKASHUNIUM_Z, "pikachu", "pop-star", exporter)
        createZCyrstal<Any>(GenerationsItems.PIKASHUNIUM_Z, "pikachu", "phd", exporter)
        createZCyrstal<Any>(GenerationsItems.PIKASHUNIUM_Z, "pikachu", "libre", exporter)
        createZCyrstal<Any>(GenerationsItems.PIKASHUNIUM_Z, "pikachu", "original", exporter)
        createZCyrstal(GenerationsItems.PRIMARIUM_Z, "primarina", exporter)
        createZCyrstal(GenerationsItems.SNORLIUM_Z, "snorlax", exporter)
        createZCyrstal(GenerationsItems.SOLGANIUM_Z, "solgaleo", exporter)
        createZCyrstal(GenerationsItems.TAPUNIUM_Z, "tapukoko", true, exporter)
        createZCyrstal(GenerationsItems.TAPUNIUM_Z, "tapulele", true, exporter)
        createZCyrstal(GenerationsItems.TAPUNIUM_Z, "tapubulu", true, exporter)
        createZCyrstal(GenerationsItems.TAPUNIUM_Z, "tapufini", true, exporter)
        createZCyrstal(GenerationsItems.ULTRANECROZIUM_Z, "necrozma", exporter)

        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_A, "a")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_B, "b")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_C, "c")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_D, "d")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_E, "e")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_F, "f")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_G, "g")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_H, "h")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_I, "i")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_J, "j")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_K, "k")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_L, "l")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_M, "m")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_N, "n")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_O, "o")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_P, "p")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_Q, "q")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_R, "r")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_S, "s")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_T, "t")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_U, "u")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_V, "v")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_W, "w")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_X, "x")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_Y, "y")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_Z, "z")

        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_EXCLAMATION_MARK, "exclamation")
        unownBlock(exporter, GenerationsBlocks.UNOWN_BLOCK_QUESTION_MARK, "questionmark")
    }

    private fun <E> createZCyrstal(
        result: RegistrySupplier<Item>,
        pokemon: String,
        aspects: String,
        exporter: RecipeOutput
    ) {
        ShapedRksRecipeJsonBuilder.create(result.get())
            .input('A', Items.NETHERITE_SCRAP)
            .input(
                'B',
                TimeCapsuleIngredient(
                    SpeciesKey(
                        ResourceLocation.fromNamespaceAndPath("cobblemon", pokemon),
                        Set.of<String>(aspects)
                    ), false
                )
            )
            .input('X', GenerationsItems.Z_INGOT.get())
            .pattern("XXX")
            .pattern("ABA")
            .pattern("XXX")
            .criterion(GenerationsItems.Z_INGOT.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.Z_INGOT.get()))
            .save(exporter, result.id.withSuffix("_" + aspects.replace("-", "_")))
    }

    private fun createParadoxPast(paradoxPokemon: String, toBeConverted: String, exporter: RecipeOutput) {
        createParadox(paradoxPokemon, toBeConverted, exporter, Items.COAL)
    }

    private fun createParadoxFuture(
        suffix: String,
        paradoxPokemon: String,
        toBeConverted: String,
        exporter: RecipeOutput
    ) {
        createParadox(suffix, paradoxPokemon, toBeConverted, exporter, Items.REDSTONE)
    }

    private fun createParadoxFuture(paradoxPokemon: String, toBeConverted: String, exporter: RecipeOutput) {
        createParadox(paradoxPokemon, toBeConverted, exporter, Items.REDSTONE)
    }

    private fun createZCyrstal(result: RegistrySupplier<Item>, pokemon: String, exporter: RecipeOutput) {
        createZCyrstal(result, pokemon, false, exporter)
    }

    private fun createZCyrstal(
        result: RegistrySupplier<Item>,
        pokemon: String,
        multi: Boolean,
        exporter: RecipeOutput
    ) {
        ShapedRksRecipeJsonBuilder.create(result.get())
            .input('A', Items.NETHERITE_SCRAP)
            .input('B', TimeCapsuleIngredient(pokemon, false))
            .input('X', GenerationsItems.Z_INGOT.get())
            .pattern("XXX")
            .pattern("ABA")
            .pattern("XXX")
            .doesntConsumeTimeCapsules()
            .criterion(
                BuiltInRegistries.ITEM.getKey(GenerationsItems.Z_INGOT.get()).path,
                InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.Z_INGOT.get())
            )
            .save(exporter, result.id.withSuffix(if (multi) "_$pokemon" else ""))
    }

    private fun unownBlock(consumer: RecipeOutput, createdBlock: RegistrySupplier<out Block>, form: String) {
        create(createdBlock.get())
            .requires(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock())
            .requires(PokemonItemIngredient(ResourceLocation.fromNamespaceAndPath("cobblemon", "unown").optional(), Set.of(form).optional()))
            .criterion(
                getHasName(GenerationsBlocks.UNOWN_BLOCK_BLANK.get()),
                has(GenerationsBlocks.UNOWN_BLOCK_BLANK.get())
            )
            .save(consumer, createdBlock.id)
    }

    private fun createZCyrstal(result: RegistrySupplier<Item>, item: Item, exporter: RecipeOutput) {
        ShapedRksRecipeJsonBuilder.create(result.get())
            .input('A', Items.NETHERITE_SCRAP)
            .input('B', item)
            .input('X', GenerationsItems.Z_INGOT.get())
            .pattern("XXX")
            .pattern("ABA")
            .pattern("XXX")
            .criterion(
                BuiltInRegistries.ITEM.getKey(GenerationsItems.Z_INGOT.get()).path,
                InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.Z_INGOT.get())
            )
            .save(exporter, result.id)
    }

    private fun createMegaStone(result: RegistrySupplier<Item>, pokemon: String, exporter: RecipeOutput) {
        val phrase = result.id.path
        val index = phrase.indexOf("_")
        val special = if (index != -1) phrase[index + 1] else null

        var item = Items.AIR

        if (special != null) {
            when (special) {
                'x' -> {
                    item = Items.COAL
                }

                'y' -> {
                    item = Items.REDSTONE
                }
            }
        }

        val builder: ShapedRksRecipeJsonBuilder = ShapedRksRecipeJsonBuilder.create(result.get())
            .input('A', GenerationsItems.KEY_STONE.get())
            .input('B', TimeCapsuleIngredient(pokemon, false))
            .input('C', Items.NETHERITE_SCRAP)
        if (special != null) builder.input('D', item).pattern(" D ".uppercase(Locale.getDefault()))
        builder.pattern("ABC")
            .doesntConsumeTimeCapsules()
            .criterion(GenerationsItems.KEY_STONE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.KEY_STONE.get()))
            .save(exporter, result.id)
    }

    private fun createParadox(name: String, toBeConverted: String, exporter: RecipeOutput, item: Item) {
        createParadox("", name, toBeConverted, exporter, item)
    }

    private fun createParadox(suffix: String, name: String, toBeConverted: String, exporter: RecipeOutput, item: Item) {
        ShapelessRksRecipeJsonBuilder.create(name, false, true)
            .requires(TimeCapsuleIngredient(toBeConverted, false))
            .requires(item)
            .criterion(BuiltInRegistries.ITEM.getKey(item).path, InventoryChangeTrigger.TriggerInstance.hasItems(item))
            .save(exporter, id(name + suffix))
    }

    private fun createFossil(item: RegistrySupplier<Item>, name: String, exporter: RecipeOutput) {
        ShapedRksRecipeJsonBuilder.create(name, false, false)
            .pattern("A")
            .input('A', item.get())
            .criterion(item.id.path, InventoryChangeTrigger.TriggerInstance.hasItems(item.get()))
            .save(exporter, id(name))
    }

    private fun createFossil(
        item: RegistrySupplier<Item>,
        item2: RegistrySupplier<Item>,
        name: String,
        exporter: RecipeOutput
    ) {
        ShapedRksRecipeJsonBuilder.create(name)
            .pattern("AB")
            .input('A', item.get())
            .input('B', item2.get())
            .criterion(item.id.path, InventoryChangeTrigger.TriggerInstance.hasItems(item.get()))
            .save(exporter, id(name))
    }
}

fun <T: Any> T.optional(): Optional<T> = Optional.of(this)
fun <T: Any> T?.nullableOptional(): Optional<T> = Optional.ofNullable(this)