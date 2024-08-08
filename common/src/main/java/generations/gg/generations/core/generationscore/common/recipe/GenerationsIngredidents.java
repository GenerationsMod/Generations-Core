package generations.gg.generations.core.generationscore.common.recipe;

import com.google.gson.JsonElement;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredientSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public interface GenerationsIngredidents {
    <T extends GenerationsIngredient> void register(String name, Class<T> clazz, GenerationsIngredientSerializer<T> serializer);


    ResourceLocation getId(GenerationsIngredient ingredient);

    @NotNull Ingredient convert(@NotNull GenerationsIngredient generationsIngredient);

    Ingredient fromJson(JsonElement value);
}
