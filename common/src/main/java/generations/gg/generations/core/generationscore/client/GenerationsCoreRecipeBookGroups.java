package generations.gg.generations.core.generationscore.client;

import com.google.common.base.Suppliers;
import dev.architectury.injectables.annotations.ExpectPlatform;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Supplier;

public class GenerationsCoreRecipeBookGroups {
    public static final Supplier<RecipeBookCategories> RKS_GENERAL = Suppliers.memoize(() -> getRecipBookCategories("RKS_GENERAL", () -> GenerationsUtilityBlocks.RKS_MACHINE.get().asItem().getDefaultInstance()).get());
    public static final Supplier<RecipeBookCategories> RKS_SEARCH = Suppliers.memoize(() -> getRecipBookCategories("RKS_SEARCH", Items.COMPASS::getDefaultInstance).get());

    public static final Supplier<List<RecipeBookCategories>> RKS_CATEGORIES = Suppliers.memoize(() -> List.of(RKS_GENERAL.get()));


    @ExpectPlatform
    private static Supplier<RecipeBookCategories> getRecipBookCategories(String name, Supplier<ItemStack> itemStack) {
        throw new RuntimeException();
    }

    public static void init() {
    }
}
