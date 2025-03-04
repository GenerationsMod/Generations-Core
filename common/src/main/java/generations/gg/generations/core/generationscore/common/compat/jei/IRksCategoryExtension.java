package generations.gg.generations.core.generationscore.common.compat.jei;

import com.mojang.blaze3d.platform.InputConstants;
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
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public interface IRksCategoryExtension extends IRecipeCategoryExtension {
    void setRecipe(IRecipeLayoutBuilder var1, ICraftingGridHelper var2, IFocusGroup var3);

    default void onDisplayedIngredientsUpdate(List<IRecipeSlotDrawable> recipeSlots, IFocusGroup focuses) {
    }

    default void createRecipeExtras(IRecipeExtrasBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
    }

    default @Nullable ResourceLocation getRegistryName() {
        return null;
    }

    default int getWidth() {
        return 0;
    }

    default int getHeight() {
        return 0;
    }
}
