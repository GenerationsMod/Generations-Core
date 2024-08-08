package generations.gg.generations.core.generationscore.fabric.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredientSerializer;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredient;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerationsIngredientsFabric implements GenerationsIngredidents {
    private static final Map<Class<? extends GenerationsIngredient>, GenerationsFabricSerializer<? extends GenerationsIngredient>> MAP = new HashMap<>();
    private static final Map<Class<? extends GenerationsIngredient>, ResourceLocation> ID_MAP = new HashMap<>();
    private static final Map<ResourceLocation, GenerationsFabricSerializer<? extends GenerationsIngredient>> MAP_ID = new HashMap<>();
    public <T extends GenerationsIngredient> void register(String name, Class<T> clazz, GenerationsIngredientSerializer<T> serializer) {
        var id = GenerationsCore.id(name);
        var customSerializer = new GenerationsIngredientsFabric.GenerationsFabricSerializer<>(id, serializer);
        CustomIngredientSerializer.register(customSerializer);
        MAP.put(clazz, customSerializer);
        ID_MAP.put(clazz, id);
        MAP_ID.put(id, customSerializer);
    }

    public ResourceLocation getId(GenerationsIngredient ingredient) {
        return ID_MAP.get(ingredient.getClass());
    }

    @NotNull
    public Ingredient convert(@NotNull GenerationsIngredient generationsIngredient) {
        return new GenerationsFabricIngredigent<>(generationsIngredient).toVanilla();
    }

    @Override
    public Ingredient fromJson(JsonElement obj) {
        if(obj.isJsonObject() && obj.getAsJsonObject().has("type")) {
            var type = obj.getAsJsonObject().get("type").getAsString();

            var serializer = GenerationsIngredientsFabric.getSerializer(new ResourceLocation(type));

            if(serializer != null) {
                return serializer.parse(obj.getAsJsonObject()).asMinecraftIngredient();
            }
        }

        return Ingredient.fromJson(obj);
    }

    public static GenerationsIngredientSerializer<?> getSerializer(ResourceLocation resourceLocation) {
        return MAP_ID.get(resourceLocation).serializer();
    }

    public record GenerationsFabricIngredigent<T extends GenerationsIngredient>(T ingredient) implements CustomIngredient {

        @Override
        public boolean test(ItemStack stack) {
            return ingredient.matches(stack);
        }

        @Override
        public List<ItemStack> getMatchingStacks() {
            return ingredient.matchingStacks();
        }

        @Override
        public boolean requiresTesting() {
            return ingredient.isSimple();
        }

        @Override
        public CustomIngredientSerializer<?> getSerializer() {
            return MAP.get(ingredient.getClass());
        }
    }

    public record GenerationsFabricSerializer<V extends GenerationsIngredient>(ResourceLocation id, GenerationsIngredientSerializer<V> serializer) implements CustomIngredientSerializer<GenerationsFabricIngredigent<V>> {

        @Override
        public ResourceLocation getIdentifier() {
            return id;
        }

        @Override
        public GenerationsFabricIngredigent<V> read(JsonObject json) {
            var ingredient = serializer.parse(json);
            return new GenerationsFabricIngredigent<>(ingredient);
        }

        @Override
        public void write(JsonObject json, GenerationsFabricIngredigent<V> ingredient) {
            ingredient.ingredient.toJson(json);
        }

        @Override
        public GenerationsFabricIngredigent<V> read(FriendlyByteBuf buf) {
            return new GenerationsFabricIngredigent<>(serializer.parse(buf));
        }

        @Override
        public void write(FriendlyByteBuf buf, GenerationsFabricIngredigent<V> ingredient) {
            serializer.write(buf, ingredient.ingredient);
        }
    }
}
