package generations.gg.generations.core.generationscore.forge.datagen;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.forge.datagen.cobblemon.PokemonModelsProvider;
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.BlockDatagen;
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.GenerationsBlockStateProvider;
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.UltraBlockModelDataGen;
import generations.gg.generations.core.generationscore.forge.datagen.generators.items.ItemDatagen;
import generations.gg.generations.core.generationscore.forge.datagen.generators.lang.GeneralLang;
import generations.gg.generations.core.generationscore.forge.datagen.generators.loot.LootTableDatagen;
import generations.gg.generations.core.generationscore.forge.datagen.generators.ores.OreGenDatagen;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.*;
import generations.gg.generations.core.generationscore.forge.datagen.generators.tags.TagsDatagen;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.function.TriFunction;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * This class is used to register the data generators for the mod.
 * @see GatherDataEvent
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GenerationsCore.MOD_ID)
public class DataGeneratorsRegister {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        OreGenDatagen.onInitialize(event.getLookupProvider());
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        TagsDatagen.init(generator, output, event.getLookupProvider(), existingFileHelper);
        generator.addProvider(true, new GeneralLang(output, "en_us"));
        generator.addProvider(true, new GenerationsBlockStateProvider(output, existingFileHelper, BlockDatagen::new, UltraBlockModelDataGen::new));
        generator.addProvider(true, new ItemDatagen(output, existingFileHelper));

        generator.addProvider(true, new GenerationsRecipeProvider(output,
                BuildingBlockRecipeDatagen::new,
                //ItemRecipeDatagen::new,
                GenerationsArmorToolRecipeDatagen::new,
                MachineDecorationsRecipeDatagen::new,
                WoodRecipes::new,
                //PokeBallRecipeDatagen::new,
                FurnaceRecipeProvider::new));
        generator.addProvider(true, new LootTableDatagen(output));
        generator.addProvider(true, new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), Set.of(GenerationsCore.MOD_ID)));

        generator.addProvider(true, new PokemonModelsProvider(output) {
            @Override
            public void build(BiConsumer<Resolver, List<PoserBuilder>> provider) {
                mega(provider, "absol");
                base(provider, "accelgor");
                mega(provider, "aerodactyl");
                mega(provider, "aggron");
                base(provider, "aipom");
                mega(provider, "alakazam");
                base(provider, "alomomola"); //TODO: Add swim
                mega(provider, "altaria");
                base(provider, "amaura");
                base(provider, "ambipom");
                base(provider, "amoonguss");
                mega(provider, "ampharos");
                base(provider, "anorith");
                gigantamax(provider, "appletun");
                base(provider, "applin");
                base(provider, "arbok");
                hisuian(provider, "arcanine");
                elemental(provider, "arceus");
                base(provider, "archen");
                base(provider, "archeops");
                base(provider, "arctovish");
                base(provider, "arctozolt");
                base(provider, "ariados");
                base(provider, "armaldo");
                base(provider, "aromatisse");
                base(provider, "aron");
                base(provider, "arrokuda", true);
                mega(provider, "audino");
                base(provider, "aurorus");
                hisuian(provider, "avalugg");
                base(provider, "axew");
                base(provider, "azelf");
                base(provider, "azumarill");
                base(provider, "azurill", true);
            }

            private void elemental(BiConsumer<Resolver, List<PoserBuilder>> provider, String name) {
                var id = GenerationsCore.id(name);
                var resolver = simple(GenerationsCore.id(name), 1);
                Stream.of("normal",
                        "fire",
                        "water",
                        "grass",
                        "electric",
                        "ice",
                        "fighting",
                        "poison",
                        "ground",
                        "flying",
                        "psychic",
                        "bug",
                        "rock",
                        "ghost",
                        "dragon",
                        "dark",
                        "steel",
                        "fairy").forEach(type -> {
                            resolver.variation(base().aspect(type).variant(type).poser(id).model(id));
                            resolver.variation(shiny().aspect(type).variant("shiny-" + type).poser(id).model(id));
                });

                provider.accept(resolver, resolver(name, false));
            }

            private void gigantamax(BiConsumer<Resolver, List<PoserBuilder>> provider, String name) {
                form(provider, "gigantamax", name, true);
            }

            private void hisuian(BiConsumer<Resolver, List<PoserBuilder>> provider, String name) {
                form(provider, "hisuian", name);
            }

            private void base(BiConsumer<Resolver, List<PoserBuilder>> provider, String name) {
                base(provider, name, false);
            }

            private void base(BiConsumer<Resolver, List<PoserBuilder>> provider, String name, boolean idleOnly) {
                provider.accept(base(name), resolver(name, idleOnly));
            }

            private void form(BiConsumer<Resolver, List<PoserBuilder>> provider, String form, String name) {
                form(provider, form, name, false);
            }

            private void form(BiConsumer<Resolver, List<PoserBuilder>> provider, String form, String name, boolean idleOnly) {
                provider.accept(form(form, name, idleOnly), formResolver(form, name, idleOnly));
            }

            private void mega(BiConsumer<Resolver, List<PoserBuilder>> provider, String name) {
                form(provider, "mega", name);
            }

            public static Resolver base(String name) {
                return simple(GenerationsCore.id(name), 1)
                        .variation(base().variant("normal"))
                        .variation(shiny().variant("shiny"));
            }

            private Resolver form(String form, String name, boolean idleOnly) {
                TriFunction<String, Boolean, Boolean, Resolver.ModelAssetVariationBuilder> function = (name1, shiny, mega) -> {

                    var builder = shiny ? shiny() : base();
                    var id = GenerationsCore.id(name1 + (mega ? "-" + form : ""));

                    if(!shiny) {
                        builder.poser(id).model(id);
                    }

                    if(mega) {
                        builder.aspect(form);
                    }

                    return builder.variant(shiny ? "shiny" : "normal");
                };

                return simple(GenerationsCore.id(name), 1)
                        .variation(function.apply(name, false, false))
                        .variation(function.apply(name, true, false))
                        .variation(function.apply(name, false, true))
                        .variation(function.apply(name, true, true));
            }

            private List<PoserBuilder> formResolver(String form, String name, boolean idleOnly) {
                return List.of(gensPoser(name, false),
                gensPoser(name + "-" + form, idleOnly));
            }

            private List<PoserBuilder> resolver(String name, boolean idleOnly) {
                return List.of(gensPoser(name, idleOnly));
            }

            public PoserBuilder gensPoser(String name, boolean idleOnly) {
                var poser = poser(GenerationsCore.id(name)).standing();
                if(!idleOnly) poser.walking();
                return poser;
            }
        });

//        generator.addProvider(true, new DialogueDataGen(event.getGenerator().getPackOutput()));
    }
}