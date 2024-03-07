package generations.gg.generations.core.generationscore.world.recipe.forge;

import com.google.gson.JsonElement;
import net.minecraft.world.item.crafting.Ingredient;

public class RksRecipeImpl {
    public static Ingredient fromJson(JsonElement obj) {
        return Ingredient.fromJson(obj);
    }
}
