package generations.gg.generations.core.generationscore.world.recipe.fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.recipe.fabric.GenerationsIngredidentsImpl;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredient;
import net.fabricmc.fabric.impl.recipe.ingredient.CustomIngredientImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class RksRecipeImpl {
    public static Ingredient fromJson(JsonElement obj) {
        if(obj.isJsonObject() && obj.getAsJsonObject().has("type")) {
            var type = obj.getAsJsonObject().get("type").getAsString();

            var serializer = GenerationsIngredidentsImpl.getSerializer(new ResourceLocation(type));

            if(serializer != null) {
                return serializer.parse(obj.getAsJsonObject()).asMinecraftIngredient();
            }
        }

        return Ingredient.fromJson(obj);
    }
}
