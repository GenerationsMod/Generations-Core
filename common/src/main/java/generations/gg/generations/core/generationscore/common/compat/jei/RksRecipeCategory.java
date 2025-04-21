package generations.gg.generations.core.generationscore.common.compat.jei;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsCoreRecipeTypes;
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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

public class RksRecipeCategory extends AbstractRecipeCategory<RecipeHolder<RksRecipe>> implements IExtendableRksRecipeCategory {
    public static final int width = 116;
    public static final int height = 54;

    public static final Supplier<RecipeType<RecipeHolder<RksRecipe>>> RKS_MACHINE = RecipeType.createFromDeferredVanilla(GenerationsCoreRecipeTypes.RKS::get);
    private final IGuiHelper helper;
    private final ICraftingGridHelper craftingGridHelper;
    private final RksExtensionHelper extendableHelper = new RksExtensionHelper();

    public RksRecipeCategory(IGuiHelper helper) {
        super(
                RKS_MACHINE.get(),
                Component.translatable("gui.recipe_viewer.category.rks_machine"),
                helper.createDrawableItemLike(GenerationsUtilityBlocks.RKS_MACHINE.get()),
                width,
                height
        );

        this.helper = helper;
        this.craftingGridHelper = helper.createCraftingGridHelper();

        extendableHelper.addRecipeExtension(ShapedRksRecipe.class, new RksRecipeExtension<>());
        extendableHelper.addRecipeExtension(ShapelessRksRecipe.class, new RksRecipeExtension<>());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<RksRecipe> holder, IFocusGroup group) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(holder);
        recipeExtension.setRecipe(holder, builder, craftingGridHelper, group);
    }

    @Override
    public void onDisplayedIngredientsUpdate(RecipeHolder<RksRecipe> holder, List<IRecipeSlotDrawable> slots, IFocusGroup group) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(holder);
        recipeExtension.onDisplayedIngredientsUpdate(holder, slots, group);
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<RksRecipe> holder, IFocusGroup group) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(holder);
        recipeExtension.createRecipeExtras(holder, builder, craftingGridHelper, group);
    }


    @SuppressWarnings("removal")
    @Override
    public void draw(RecipeHolder<RksRecipe> holder, IRecipeSlotsView view, GuiGraphics graphics, double mouseX, double mouseY) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(holder);
        int recipeWidth = this.getWidth();
        int recipeHeight = this.getHeight();
        recipeExtension.drawInfo(recipeWidth, recipeHeight, graphics, mouseX, mouseY);

        IDrawableStatic recipeArrow = helper.getRecipeArrow();
        recipeArrow.draw(graphics, 61, (height - recipeArrow.getHeight()) / 2);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, RecipeHolder<RksRecipe> holder, IRecipeSlotsView view, double mouseX, double mouseY) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(holder);
        recipeExtension.getTooltip(tooltip, holder, mouseX, mouseY);
    }

    @SuppressWarnings("removal")
    @Override
    public List<Component> getTooltipStrings(RecipeHolder<RksRecipe> holder, IRecipeSlotsView view, double mouseX, double mouseY) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(holder);

        return recipeExtension.getTooltipStrings(holder, mouseX, mouseY);
    }

    @SuppressWarnings("removal")
    @Override
    public boolean handleInput(RecipeHolder<RksRecipe> holder, double mouseX, double mouseY, InputConstants.Key input) {
        var recipeExtension = this.extendableHelper.getRecipeExtension(holder);
        return recipeExtension.handleInput(mouseX, mouseY, input);
    }

    @Override
    public boolean isHandled(RecipeHolder<RksRecipe> holder) {
        return this.extendableHelper.getOptionalRecipeExtension(holder)
                .isPresent();
    }

    @Override
    public <R extends RksRecipe> void addExtension(Class<? extends R> clazz, IRksCategoryExtension<R> extension) {
        extendableHelper.addRecipeExtension(clazz, extension);
    }

    @SuppressWarnings("removal")
    @Override
    public @Nullable ResourceLocation getRegistryName(RecipeHolder<RksRecipe> holder) {
        return this.extendableHelper.getOptionalRecipeExtension(holder)
                .flatMap(extension -> extension.getRegistryName(holder))
                .orElseGet(holder::id);
    }
}