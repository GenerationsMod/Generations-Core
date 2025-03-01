package generations.gg.generations.core.generationscore.common.recipe;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredientSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class GenerationsIngredidents {
    private static BiMap<String, GenerationsIngredientSerializer<?>> SERIALIZER = HashBiMap.create();

    public static <T extends GenerationsIngredient> void register(String id, GenerationsIngredientSerializer<T> serializer) {
        SERIALIZER.put(id, serializer);
    }

    public static GenerationsIngredientSerializer<?> getSerializer(String id) {
        System.out.println("Trying to get rks ingredient serializer with id " + id);
        System.out.println("The returned value is " + SERIALIZER.getOrDefault(id, null));

        return SERIALIZER.get(id);
    }

    public static GenerationsIngredient fromJson(JsonObject value) {
        var id = value.get("type").getAsString();
        return getSerializer(id).read(value);
    }

    public static JsonObject toJson(GenerationsIngredient value) {
        var obj = new JsonObject();
        obj.addProperty("type", value.getId());
        value.write(obj);
        return obj;
    }

    public static void toNetwork(FriendlyByteBuf buf, GenerationsIngredient ingredient) {
        buf.writeUtf(ingredient.getId());
        ingredient.write(buf);
    }

    public static GenerationsIngredient fromNetwork(FriendlyByteBuf buffer) {
        var id = buffer.readUtf();
        var ingredient = getSerializer(id).read(buffer);

        return ingredient;
    }
}
