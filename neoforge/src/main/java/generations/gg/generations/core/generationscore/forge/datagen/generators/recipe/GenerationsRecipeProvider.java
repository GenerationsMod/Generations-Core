package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerationsRecipeProvider extends RecipeProvider {
    List<Proxied> providers;
    @SafeVarargs
    public GenerationsRecipeProvider(PackOutput output, Function<PackOutput, Proxied>... providers) {
        super(output);
        this.providers = Stream.of(providers).map(provider -> provider.apply(output)).collect(Collectors.toList());
    }



    @Override
    protected void buildRecipes(@NotNull RecipeOutput consumer) {
        providers.forEach(provider -> provider.buildRecipes(consumer));
    }

    public static abstract class Proxied extends RecipeProvider {

        public Proxied(PackOutput arg) {
            super(arg);
        }

        @Override
        abstract protected void buildRecipes(@NotNull RecipeOutput consumer);
    }
}
