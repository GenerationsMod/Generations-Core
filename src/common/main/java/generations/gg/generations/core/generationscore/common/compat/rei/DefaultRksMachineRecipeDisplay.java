package generations.gg.generations.core.generationscore.common.compat.rei;

import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapedRksRecipe;
import generations.gg.generations.core.generationscore.common.world.recipe.ShapelessRksRecipe;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.InputIngredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class DefaultRksMachineRecipeDisplay<C extends RecipeHolder<? extends RksRecipe>> extends BasicDisplay implements SimpleGridMenuDisplay {
    protected Optional<C> recipe;
    private final int weavingTime;

    public DefaultRksMachineRecipeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<C> recipe, int weavingTime) {
        this(inputs, outputs, recipe.map(a -> a.id()), recipe, weavingTime);
    }

    public DefaultRksMachineRecipeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location, Optional<C> recipe, int weavingTime) {
        super(inputs, outputs, location);
        this.recipe = recipe;
        this.weavingTime = weavingTime;
    }

    @Nullable
    public static DefaultRksMachineRecipeDisplay<?> of(RecipeHolder<?> recipe) {
        if (recipe.value() instanceof ShapelessRksRecipe) {
            return new DefaultRksShapelessDisplay((RecipeHolder<ShapelessRksRecipe>) recipe);
        } else if (recipe.value() instanceof ShapedRksRecipe) {
            return new DefaultRksMachineeShapedDisplay((RecipeHolder<ShapedRksRecipe>) recipe);
        } /*else if (!recipe.isSpecial()) {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            for (CraftingRecipeSizeProvider<?> pair : SIZE_PROVIDER) {
                CraftingRecipeSizeProvider.Size size = ((CraftingRecipeSizeProvider<Recipe<?>>) pair).getSize(recipe);

                if (size != null) {
                    return new DefaultCustomShapedDisplay(recipe, EntryIngredients.ofIngredients(recipe.getIngredients()),
                            Collections.singletonList(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))),
                            size.getWidth(), size.getHeight());
                }
            }

            return new DefaultCustomDisplay(recipe, EntryIngredients.ofIngredients(recipe.getIngredients()),
                    Collections.singletonList(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))));
        }*/

        return null;
    }


    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ReiCompatClient.RKS_MACHINE;
    }

    public <T extends AbstractContainerMenu> List<EntryIngredient> getOrganisedInputEntries(int menuWidth, int menuHeight) {
        List<EntryIngredient> list = new ArrayList<>(menuWidth * menuHeight);
        for (int i = 0; i < menuWidth * menuHeight; i++) {
            list.add(EntryIngredient.empty());
        }
        for (int i = 0; i < getInputEntries().size(); i++) {
            list.set(getSlotWithSize(this, i, menuWidth), getInputEntries().get(i));
        }
        return list;
    }

    public boolean isShapeless() {
        return false;
    }

    public static int getSlotWithSize(DefaultRksMachineRecipeDisplay<?> display, int index, int craftingGridWidth) {
        return getSlotWithSize(display.getInputWidth(craftingGridWidth, 3), index, craftingGridWidth);
    }

    public static int getSlotWithSize(int recipeWidth, int index, int craftingGridWidth) {
        int x = index % recipeWidth;
        int y = (index - x) / recipeWidth;
        return craftingGridWidth * y + x;
    }

    @Override
    public List<InputIngredient<EntryStack<?>>> getInputIngredients(@Nullable AbstractContainerMenu menu, @Nullable Player player) {
        return getInputIngredients(3, 3);
    }

    public List<InputIngredient<EntryStack<?>>> getInputIngredients(int craftingWidth, int craftingHeight) {
        int inputWidth = getInputWidth(craftingWidth, craftingHeight);
        int inputHeight = getInputHeight(craftingWidth, craftingHeight);

        Map<IntIntPair, InputIngredient<EntryStack<?>>> grid = new HashMap<>();

        List<EntryIngredient> inputEntries = getInputEntries();
        for (int i = 0; i < inputEntries.size(); i++) {
            EntryIngredient stacks = inputEntries.get(i);
            if (stacks.isEmpty()) {
                continue;
            }
            int index = getSlotWithSize(inputWidth, i, craftingWidth);
            int x = i % inputWidth;
            int y = i / inputWidth;
            grid.put(new IntIntImmutablePair(x, y), InputIngredient.of(index, 3 * y + x, stacks));
        }

        List<InputIngredient<EntryStack<?>>> list = new ArrayList<>(craftingWidth * craftingHeight);
        for (int i = 0, n = craftingWidth * craftingHeight; i < n; i++) {
            list.add(InputIngredient.empty(i));
        }

        for (int x = 0; x < craftingWidth; x++) {
            for (int y = 0; y < craftingHeight; y++) {
                InputIngredient<EntryStack<?>> ingredient = grid.get(new IntIntImmutablePair(x, y));
                if (ingredient != null) {
                    int index = craftingWidth * y + x;
                    list.set(index, ingredient);
                }
            }
        }

        return list;
    }

    public int getWeavingTime() {
        return weavingTime;
    }
}