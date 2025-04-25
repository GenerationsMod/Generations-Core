package generations.gg.generations.core.generationscore.forge.datagen

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsUltraBlockSet
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.BlockDatagen
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.GenerationsBlockStateProvider
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.UltraBlockModelDataGen
import generations.gg.generations.core.generationscore.forge.datagen.generators.items.ItemDatagen
import generations.gg.generations.core.generationscore.forge.datagen.generators.lang.GeneralLang
import generations.gg.generations.core.generationscore.forge.datagen.generators.loot.LootTableDatagen
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.*
import generations.gg.generations.core.generationscore.forge.datagen.generators.tags.TagsDatagen
import generations.gg.generations.core.generationscore.forge.datagen.generators.worldgen.WorldGenProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.DataGenerator
import net.minecraft.data.PackOutput
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.data.event.GatherDataEvent
import java.util.concurrent.CompletableFuture
import java.util.function.Function

/**
 * This class is used to register the data generators for the mod.
 * @see GatherDataEvent
 *
 * @author Joseph T. McQuigg (JT122406)
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = GenerationsCore.MOD_ID)
internal object DataGeneratorsRegister {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        GenerationsBlockSet.generateAllBlockFamilies()
        GenerationsUltraBlockSet.updateUltraBlockFamilies()
        val generator: DataGenerator = event.getGenerator()
        val existingFileHelper: ExistingFileHelper = event.getExistingFileHelper()
        val output = generator.packOutput
        val lookupProvider: CompletableFuture<HolderLookup.Provider> = event.getLookupProvider()
        TagsDatagen.init(generator, output, lookupProvider, existingFileHelper)
        generator.addProvider<GeneralLang>(true, GeneralLang(output, "en_us"))
        generator.addProvider(
            true, GenerationsBlockStateProvider(output, existingFileHelper,
                Function<GenerationsBlockStateProvider, GenerationsBlockStateProvider.Proxied> { provider: GenerationsBlockStateProvider? ->
                    BlockDatagen(
                        provider
                    )
                },
                Function<GenerationsBlockStateProvider, GenerationsBlockStateProvider.Proxied> { provider: GenerationsBlockStateProvider? ->
                    UltraBlockModelDataGen(
                        provider
                    )
                })
        )
        generator.addProvider(true, ItemDatagen(output, existingFileHelper))

        generator.addProvider<GenerationsRecipeProvider>(
            true, GenerationsRecipeProvider(output,
                Function<PackOutput, GenerationsRecipeProvider.Proxied> { arg: PackOutput? -> ItemRecipeDatagen(arg) },
                Function<PackOutput, GenerationsRecipeProvider.Proxied> { output: PackOutput? ->
                    BuildingBlockRecipeDatagen(
                        output
                    )
                },
                Function<PackOutput, GenerationsRecipeProvider.Proxied> { output: PackOutput? ->
                    GenerationsArmorToolRecipeDatagen(
                        output
                    )
                },
                Function<PackOutput, GenerationsRecipeProvider.Proxied> { output: PackOutput? ->
                    MachineDecorationsRecipeDatagen(
                        output
                    )
                },
                Function<PackOutput, GenerationsRecipeProvider.Proxied> { output: PackOutput? -> WoodRecipes(output) },  //PokeBallRecipeDatagen::new,
                Function<PackOutput, GenerationsRecipeProvider.Proxied> { output: PackOutput? ->
                    FurnaceRecipeProvider(
                        output
                    )
                },
                Function<PackOutput, GenerationsRecipeProvider.Proxied> { arg: PackOutput? -> RksRecipeProvider(arg) })
        )
        generator.addProvider<LootTableDatagen>(true, LootTableDatagen(output))

        //        generator.addProvider(true, new GenerationsPokemonModelsProvider(output));

//        generator.addProvider(true, new DialogueDataGen(event.getGenerator().getPackOutput()));
        generator.addProvider(true, WorldGenProvider(output, lookupProvider))
    }
}