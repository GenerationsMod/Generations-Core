package generations.gg.generations.core.generationscore.common.compat.jei;

import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapedRksRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.library.util.RecipeUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RksRecipeExtension<T extends RksRecipe> implements IRksCategoryExtension {
    private final T recipe;

    public RksRecipeExtension(T recipe) {
        this.recipe = recipe;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
        List<List<ItemStack>> inputs = new ArrayList<>();
        for (GenerationsIngredient ingredient : recipe.recipeItems) {
            List<ItemStack> items = ingredient.matchingStacks();
            inputs.add(items);
        }
        ItemStack resultItem = RecipeUtil.getResultItem(recipe);

        int width = getWidth();
        int height = getHeight();
        craftingGridHelper.createAndSetOutputs(builder, List.of(resultItem));
        craftingGridHelper.createAndSetInputs(builder, inputs, width, height);
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return recipe.getId();
    }

    @Override
    public int getWidth() {
        if(recipe instanceof ShapedRksRecipe rksRecipe) return rksRecipe.getWidth();
        return 0;
    }

    @Override
    public int getHeight() {
        if(recipe instanceof ShapedRksRecipe rksRecipe) return rksRecipe.getHeight();
        return 0;
    }
}
