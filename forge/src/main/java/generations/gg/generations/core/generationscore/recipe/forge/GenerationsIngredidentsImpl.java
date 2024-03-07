package generations.gg.generations.core.generationscore.recipe.forge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.recipe.GenerationsIngredient;
import generations.gg.generations.core.generationscore.world.recipe.GenerationsIngredientSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GenerationsIngredidentsImpl {
    private static final Map<Class<? extends GenerationsIngredient>, GenerationsFabricSerializer<? extends GenerationsIngredient>> MAP = new HashMap<>();

    public static <T extends GenerationsIngredient> void register(String name, Class<T> clazz, GenerationsIngredientSerializer<T> serializer) {
        var id = GenerationsCore.id(name);
        var customSerializer = new GenerationsIngredidentsImpl.GenerationsFabricSerializer<>(serializer);
        CraftingHelper.register(id, customSerializer);
        MAP.put(clazz, customSerializer);
    }

    public static ResourceLocation getId(GenerationsIngredient ingredient) {
        return CraftingHelper.getID(MAP.get(ingredient.getClass()));
    }

    @NotNull
    public static Ingredient convert(@NotNull GenerationsIngredient generationsIngredient) {
        return new GenerationsForgeIngredigent<>(generationsIngredient);
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
