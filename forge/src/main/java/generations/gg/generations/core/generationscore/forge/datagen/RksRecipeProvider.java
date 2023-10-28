package generations.gg.generations.core.generationscore.forge.datagen;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.GenerationsRecipeProvider;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.RksRecipeJsonBuilder;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RksRecipeProvider extends GenerationsRecipeProvider.Proxied {
    public RksRecipeProvider(PackOutput arg) {
        super(arg);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(LegendKeys.MEWTWO)
                .key(LegendKeys.MEWTWO)
                .pattern("XAX")
                .pattern("XBX")
                .pattern("XCX")
                .input('X', GenerationsItems.MEW_FOSSIL.get())
                .input('A', GenerationsItems.DNA_SPLICERS.get())
                .input('B', GenerationsItems.ORB.get())
                .input('C', GenerationsItems.MEW_DNA_FIBER.get())
                .criterion("mew_fossil", InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.MEW_FOSSIL.get())).offerTo(exporter, GenerationsCore.id("mewtwo"));

    }
}
