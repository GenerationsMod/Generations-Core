package generations.gg.generations.core.generationscore.common.compat.rei;

import generations.gg.generations.core.generationscore.common.world.recipe.ShapelessRksRecipe;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Collections;
import java.util.Optional;

public class DefaultRksShapelessDisplay extends DefaultRksMachineRecipeDisplay<RecipeHolder<ShapelessRksRecipe>> {
    public DefaultRksShapelessDisplay(RecipeHolder<ShapelessRksRecipe> rksRecipe) {
        super(
                rksRecipe.value().getRksIngredients().stream().map(a -> EntryIngredients.ofItemStacks(a.matchingStacks())).toList(),
                Collections.singletonList(EntryIngredients.of(rksRecipe.value().getResultItem(BasicDisplay.registryAccess()))),
                Optional.of(rksRecipe),
                rksRecipe.value().getProcessingTime()
        );
    }

    public int getWidth() {
        return this.getInputEntries().size() > 4 ? 3 : 2;
    }

    public int getHeight() {
        return this.getInputEntries().size() > 4 ? 3 : 2;
    }


    public int getInputWidth(int craftingWidth, int craftingHeight) {
        return craftingWidth * craftingHeight <= this.getInputEntries().size() ? craftingWidth : Math.min(this.getInputEntries().size(), 3);
    }

    public int getInputHeight(int craftingWidth, int craftingHeight) {
        return (int)Math.ceil((double)this.getInputEntries().size() / (double)this.getInputWidth(craftingWidth, craftingHeight));
    }

    public boolean isShapeless() {
        return true;
    }
}
