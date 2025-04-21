package generations.gg.generations.core.generationscore.common.compat.jei;

import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;

public interface IExtendableRksRecipeCategory {
    <R extends RksRecipe> void addExtension(Class<? extends R> clazz, IRksCategoryExtension<R> var2);
}
