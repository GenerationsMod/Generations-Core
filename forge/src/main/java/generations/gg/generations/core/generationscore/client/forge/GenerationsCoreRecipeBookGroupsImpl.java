package generations.gg.generations.core.generationscore.client.forge;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class GenerationsCoreRecipeBookGroupsImpl {
    public static Supplier<RecipeBookCategories> getRecipBookCategories(String name, Supplier<ItemStack> itemStack) {
        return () -> RecipeBookCategories.create(name, itemStack.get());
    }
}
