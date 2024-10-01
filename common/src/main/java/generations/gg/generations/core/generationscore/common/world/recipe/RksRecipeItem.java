package generations.gg.generations.core.generationscore.common.world.recipe;

import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public class RksRecipeItem extends RksRecipe<ItemResult> {

    public RksRecipeItem(ResourceLocation id, String group, int width, int height, NonNullList<GenerationsIngredient> recipeItems, ItemResult result, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime, boolean showNotification) {
        super(id, group, width, height, recipeItems, result, consumesTimeCapsules, key, experience, processingTime, showNotification);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return GenerationsCoreRecipeSerializers.RKS_ITEM.get();
    }

    @Override
    public RecipeType<?> getType() {
        return GenerationsCoreRecipeTypes.RKS_ITEM.get();
    }
}
