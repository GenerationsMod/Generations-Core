package generations.gg.generations.core.generationscore.common.compat.jei;

import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapedRksRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.library.plugins.vanilla.crafting.JeiShapedRecipe;
import mezz.jei.library.util.RecipeUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RksRecipeExtension<T extends RksRecipe> implements IRksCategoryExtension<T> {
    @Override
    public void setRecipe(RecipeHolder<T> recipeHolder, IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
        RksRecipe recipe = recipeHolder.value();
        ItemStack resultItem = RecipeUtil.getResultItem(recipe);

        int width = getWidth(recipeHolder);
        int height = getHeight(recipeHolder);
        craftingGridHelper.createAndSetOutputs(builder, List.of(resultItem));
        craftingGridHelper.createAndSetIngredients(builder, recipe.getIngredients(), width, height);
    }

    @SuppressWarnings("removal")
    @Override
    public Optional<ResourceLocation> getRegistryName(RecipeHolder<T> recipeHolder) {
        return Optional.of(recipeHolder.id());
    }

    @Override
    public int getWidth(RecipeHolder<T> recipeHolder) {
        T recipe = recipeHolder.value();
        if (recipe instanceof ShapedRksRecipe shapedRecipe) {
            return shapedRecipe.getWidth();
        }

        return 0;
    }

    @Override
    public int getHeight(RecipeHolder<T> recipeHolder) {
        T recipe = recipeHolder.value();
        if (recipe instanceof ShapedRksRecipe shapedRecipe) {
            return shapedRecipe.getHeight();
        }
        return 0;
    }

    @Override
    public boolean isHandled(RecipeHolder<T> recipe) {
        return !recipe.value().isSpecial();
    }
}
