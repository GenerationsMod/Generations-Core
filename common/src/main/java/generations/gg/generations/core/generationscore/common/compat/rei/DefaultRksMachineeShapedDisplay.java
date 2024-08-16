package generations.gg.generations.core.generationscore.common.compat.rei;

import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.Optional;

public class DefaultRksMachineeShapedDisplay extends DefaultRksMachineRecipeDisplay<RksRecipe> {
    public DefaultRksMachineeShapedDisplay(RksRecipe rksRecipe) {
        super(
                EntryIngredients.ofIngredients(rksRecipe.getIngredients()),
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
