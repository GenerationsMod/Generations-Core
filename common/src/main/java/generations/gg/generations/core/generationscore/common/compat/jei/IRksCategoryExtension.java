package generations.gg.generations.core.generationscore.common.compat.jei;

import com.mojang.blaze3d.platform.InputConstants;
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.category.extensions.IRecipeCategoryExtension;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface IRksCategoryExtension<T extends RksRecipe> extends IRecipeCategoryExtension<RecipeHolder<T>> {
    default void setRecipe(RecipeHolder<T> recipeHolder, IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
        this.setRecipe(builder, craftingGridHelper, focuses);
    }

    default void onDisplayedIngredientsUpdate(RecipeHolder<T> recipeHolder, List<IRecipeSlotDrawable> recipeSlots, IFocusGroup focuses) {
    }

    /** @deprecated */
    @Deprecated(
            since = "19.4.1",
            forRemoval = true
    )
    default Optional<ResourceLocation> getRegistryName(RecipeHolder<T> recipeHolder) {
        return Optional.ofNullable(this.getRegistryName()).or(() -> {
            return Optional.of(recipeHolder.id());
        });
    }

    default int getWidth(RecipeHolder<T> recipeHolder) {
        return this.getWidth();
    }

    default int getHeight(RecipeHolder<T> recipeHolder) {
        return this.getHeight();
    }

    /** @deprecated */
    @Deprecated(
            since = "16.0.0",
            forRemoval = true
    )
    default void setRecipe(IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
    }

    /** @deprecated */
    @Deprecated(
            since = "16.0.0",
            forRemoval = true
    )
    default @Nullable ResourceLocation getRegistryName() {
        return null;
    }

    /** @deprecated */
    @Deprecated(
            since = "16.0.0",
            forRemoval = true
    )
    default int getWidth() {
        return 0;
    }

    /** @deprecated */
    @Deprecated(
            since = "16.0.0",
            forRemoval = true
    )
    default int getHeight() {
        return 0;
    }
}
