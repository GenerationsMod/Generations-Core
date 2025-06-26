package generations.gg.generations.core.generationscore.common.compat.rei;

import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.RksResult;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapedRksRecipe;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Collections;
import java.util.Optional;

public class DefaultRksMachineeShapedDisplay extends DefaultRksMachineRecipeDisplay<RecipeHolder<ShapedRksRecipe>> {
    public DefaultRksMachineeShapedDisplay(RecipeHolder<ShapedRksRecipe> rksRecipe) {
        super(
                rksRecipe.value().getRksIngredients().stream().map(a -> EntryIngredients.ofItemStacks(a.matchingStacks())).toList(),
                Collections.singletonList(EntryIngredients.of(rksRecipe.value().getResultItem(BasicDisplay.registryAccess()))),
                Optional.of(rksRecipe),
                rksRecipe.value().getProcessingTime()
        );
    }

    @Override
    public int getWidth() {
        return recipe.get().value().getPattern().getWidth();
    }

    @Override
    public int getHeight() {
        return recipe.get().value().getPattern().getWidth();
    }
}
