package generations.gg.generations.core.generationscore.common.compat.jei;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapedRksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapelessRksRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.category.extensions.IExtendableRecipeCategory;
import mezz.jei.library.recipes.ExtendableRecipeCategoryHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class RksRecipeCategory extends AbstractRecipeCategory<RksRecipe> implements IExtendableRecipeCategory<RksRecipe, IRksCategoryExtension> {
    public static final int width = 116;
    public static final int height = 54;

    public static final RecipeType<RksRecipe> RKS_MACHINE = new RecipeType<>(GenerationsCore.id("rks"), RksRecipe.class);
    private final IGuiHelper helper;
    private final ICraftingGridHelper craftingGridHelper;
    private final ExtendableRecipeCategoryHelper<RksRecipe, IRksCategoryExtension> extendableHelper = new ExtendableRecipeCategoryHelper<>(RksRecipe.class);

    public RksRecipeCategory(IGuiHelper helper) {
        super(
                RKS_MACHINE,
                Component.translatable("gui.recipe_viewer.category.rks_machine"),
                helper.createDrawableItemLike(GenerationsUtilityBlocks.RKS_MACHINE.getOrNull()),
                width,
                height
        );

        this.helper = helper;
        this.craftingGridHelper = helper.createCraftingGridHelper();

        extendableHelper.addRecipeExtensionFactory(ShapedRksRecipe.class, null, RksRecipeExtension::new);
        extendableHelper.addRecipeExtensionFactory(ShapelessRksRecipe.class, null, RksRecipeExtension::new);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RksRecipe recipe, IFocusGroup focuses) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(recipe);
        recipeExtension.setRecipe(builder, craftingGridHelper, focuses);
    }

    @Override
    public void onDisplayedIngredientsUpdate(RksRecipe recipe, List<IRecipeSlotDrawable> recipeSlots, IFocusGroup focuses) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(recipe);
        recipeExtension.onDisplayedIngredientsUpdate(recipeSlots, focuses);
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RksRecipe recipe, IFocusGroup focuses) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(recipe);
        recipeExtension.createRecipeExtras(builder, craftingGridHelper, focuses);
    }

    @Override
    public void draw(RksRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRksCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        int recipeWidth = this.getWidth();
        int recipeHeight = this.getHeight();
        extension.drawInfo(recipeWidth, recipeHeight, guiGraphics, mouseX, mouseY);

        IDrawableStatic recipeArrow = helper.getRecipeArrow();
        recipeArrow.draw(guiGraphics, 61, (height - recipeArrow.getHeight()) / 2);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, RksRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        IRksCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        extension.getTooltip(tooltip, mouseX, mouseY);
    }

    @SuppressWarnings({"removal"})
    @Override
    public List<Component> getTooltipStrings(RksRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        IRksCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        return extension.getTooltipStrings(mouseX, mouseY);
    }

    @SuppressWarnings({"removal"})
    @Override
    public boolean handleInput(RksRecipe recipe, double mouseX, double mouseY, InputConstants.Key input) {
        IRksCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        return extension.handleInput(mouseX, mouseY, input);
    }

    @Override
    public boolean isHandled(RksRecipe recipe) {
        return this.extendableHelper.getOptionalRecipeExtension(recipe)
                .isPresent();
    }

    @Override
    public <R extends RksRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Function<R, ? extends IRksCategoryExtension> extensionFactory) {
        extendableHelper.addRecipeExtensionFactory(recipeClass, null, extensionFactory);
    }

    @Override
    public <R extends RksRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Predicate<R> extensionFilter, Function<R, ? extends IRksCategoryExtension> extensionFactory) {
        extendableHelper.addRecipeExtensionFactory(recipeClass, extensionFilter, extensionFactory);
    }

    @Override
    public ResourceLocation getRegistryName(RksRecipe recipe) {
        return this.extendableHelper.getOptionalRecipeExtension(recipe)
                .flatMap(extension -> Optional.ofNullable(extension.getRegistryName()))
                .orElseGet(recipe::getId);
    }
}