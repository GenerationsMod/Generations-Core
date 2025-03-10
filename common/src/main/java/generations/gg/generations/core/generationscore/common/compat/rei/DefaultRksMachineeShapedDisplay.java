package generations.gg.generations.core.generationscore.common.compat.rei;

import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.RksResult;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapedRksRecipe;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.Optional;

public class DefaultRksMachineeShapedDisplay extends DefaultRksMachineRecipeDisplay<ShapedRksRecipe> {
    public DefaultRksMachineeShapedDisplay(ShapedRksRecipe rksRecipe) {
        super(
                rksRecipe.recipeItems.stream().map(a -> EntryIngredients.ofItemStacks(a.matchingStacks())).toList(),
                Collections.singletonList(EntryIngredients.of(rksRecipe.getResultItem(BasicDisplay.registryAccess()))),
                Optional.of(rksRecipe),
                rksRecipe.processingTime()
        );
    }

    @Override
    public int getWidth() {
        return recipe.get().getWidth();
    }

    @Override
    public int getHeight() {
        return recipe.get().getWidth();
    }
}
