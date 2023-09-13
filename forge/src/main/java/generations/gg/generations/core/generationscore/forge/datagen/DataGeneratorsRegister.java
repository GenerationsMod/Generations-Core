package generations.gg.generations.core.generationscore.forge.datagen;

import generations.gg.generations.core.generationscore.GenerationsCore;
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

import java.util.Set;

/**
 * This class is used to register the data generators for the mod.
 * @see GatherDataEvent
 * @author J.T. McQuigg (JT122406)
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

//        generator.addProvider(true, new GenerationsPokemonModelsProvider(output));

//        generator.addProvider(true, new DialogueDataGen(event.getGenerator().getPackOutput()));
    }
}