package generations.gg.generations.core.generationscore.forge.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredientSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GenerationsIngredientsForge implements GenerationsIngredidents {
    private static final Map<Class<? extends GenerationsIngredient>, GenerationsIngredientsForge.GenerationsFabricSerializer<? extends GenerationsIngredient>> MAP = new HashMap<>();

    public <T extends GenerationsIngredient> void register(String name, Class<T> clazz, GenerationsIngredientSerializer<T> serializer) {
        var id = GenerationsCore.id(name);
        var customSerializer = new GenerationsIngredientsForge.GenerationsFabricSerializer<>(serializer);
        CraftingHelper.register(id, customSerializer);
        MAP.put(clazz, customSerializer);
    }

    public ResourceLocation getId(GenerationsIngredient ingredient) {
        return CraftingHelper.getID(MAP.get(ingredient.getClass()));
    }

    public @NotNull Ingredient convert(@NotNull GenerationsIngredient generationsIngredient) {
        return new GenerationsIngredientsForge.GenerationsForgeIngredigent<>(generationsIngredient);
    }

    @Override
    public Ingredient fromJson(JsonElement value) {
        return Ingredient.fromJson(value);
    }

    public static final class GenerationsForgeIngredigent<T extends GenerationsIngredient> extends AbstractIngredient {
        private final T ingredient;
        private ItemStack[] itemStacks;

        public GenerationsForgeIngredigent(T ingredient) {
            this.ingredient = ingredient;
        }

        @Override
        public boolean test(ItemStack stack) {
            return ingredient.matches(stack);
        }

        @Override
        public boolean isSimple() {
            return ingredient.isSimple();
        }

        @Override
        public @NotNull IIngredientSerializer<? extends Ingredient> getSerializer() {
            return MAP.get(ingredient.getClass());
        }

        @Override
        public JsonElement toJson() {
            var json = new JsonObject();
            ingredient.toJson(json);
            return json;
        }

        public ItemStack[] getItems() {
            if (this.itemStacks == null) {
                this.itemStacks = (ItemStack[]) ingredient.matchingStacks().stream().distinct().toArray(ItemStack[]::new);
            }
            return this.itemStacks;
        }
    }

    public record GenerationsFabricSerializer<V extends GenerationsIngredient>(GenerationsIngredientSerializer<V> serializer) implements IIngredientSerializer<GenerationsForgeIngredigent<V>> {

        @Override
        public GenerationsForgeIngredigent<V> parse(FriendlyByteBuf arg) {
            var ingredient = serializer.parse(arg);
            return new GenerationsForgeIngredigent<>(ingredient);
        }

        @Override
        public GenerationsForgeIngredigent<V> parse(JsonObject jsonObject) {
            var ingredient = serializer.parse(jsonObject);
            return new GenerationsForgeIngredigent<>(ingredient);
        }

        @Override
        public void write(FriendlyByteBuf arg, GenerationsForgeIngredigent<V> arg2) {
            serializer.write(arg, arg2.ingredient);
        }
    }
}
