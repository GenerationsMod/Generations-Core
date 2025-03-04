package generations.gg.generations.core.generationscore.common.world.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.JsonOps;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShapedRksRecipe extends RksRecipe {
    public final int width;
    public final int height;

    public ShapedRksRecipe(ResourceLocation id, String group, int width, int height, NonNullList<GenerationsIngredient> recipeItems, RksResult<?> result, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime, boolean showNotification) {
        super(id, group, result, recipeItems, consumesTimeCapsules, key, experience, processingTime, showNotification);

        this.width = width;
        this.height = height;
    }

    public static NonNullList<GenerationsIngredient> dissolvePattern(String[] pattern, Map<String, GenerationsIngredient> keys, int patternWidth, int patternHeight) {
        NonNullList<GenerationsIngredient> nonNullList = NonNullList.withSize(patternWidth * patternHeight, GenerationsIngredient.EmptyIngredient.INSTANCE);
        HashSet<String> set = Sets.newHashSet(keys.keySet());
        set.remove(" ");
        for (int i = 0; i < pattern.length; ++i) {
            for (int j = 0; j < pattern[i].length(); ++j) {
                String string = pattern[i].substring(j, j + 1);
                GenerationsIngredient ingredient = keys.get(string);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + string + "' but it's not defined in the key");
                }
                set.remove(string);
                nonNullList.set(j + patternWidth * i, ingredient);
            }
        }
        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        }
        return nonNullList;
    }

    @VisibleForTesting
    public static String[] shrink(String ... toShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;
        for (int m = 0; m < toShrink.length; ++m) {
            String string = toShrink[m];
            i = Math.min(i, firstNonSpace(string));
            int n = lastNonSpace(string);
            j = Math.max(j, n);
            if (n < 0) {
                if (k == m) {
                    ++k;
                }
                ++l;
                continue;
            }
            l = 0;
        }
        if (toShrink.length == l) {
            return new String[0];
        }
        String[] strings = new String[toShrink.length - l - k];
        for (int o = 0; o < strings.length; ++o) {
            strings[o] = toShrink[o + k].substring(i, j + 1);
        }
        return strings;
    }

    private static int firstNonSpace(String entry) {
        int i = 0;
        while (i < entry.length() && entry.charAt(i) == ' ') {
            ++i;
        }
        return i;
    }

    private static int lastNonSpace(String entry) {
        int i;
        for (i = entry.length() - 1; i >= 0 && entry.charAt(i) == ' '; --i) {
        }
        return i;
    }

    public static String[] patternFromJson(JsonArray patternArray) {
        String[] strings = new String[patternArray.size()];
        if (strings.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
        }
        if (strings.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        }
        for (int i = 0; i < strings.length; ++i) {
            String string = GsonHelper.convertToString(patternArray.get(i), "pattern[" + i + "]");
            if (string.length() > 3) {
                throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
            }
            if (i > 0 && strings[0].length() != string.length()) {
                throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
            }
            strings[i] = string;
        }
        return strings;
    }

    /**
     * Returns a key json object as a Java HashMap.
     */
    public static Map<String, GenerationsIngredient> keyFromJson(JsonObject keyEntry) {
        HashMap<String, GenerationsIngredient> map = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : keyEntry.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }
            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }
            map.put(entry.getKey(), GenerationsIngredidents.fromJson(entry.getValue().getAsJsonObject()));
        }
        map.put(" ", GenerationsIngredient.EmptyIngredient.INSTANCE);
        return map;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return GenerationsCoreRecipeSerializers.SHAPED_RKS.get();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.width && height >= this.height;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@NotNull RksMachineBlockEntity inv, @NotNull Level level) {
        for (int i = 0; i <= 3 - this.width; ++i) {
            for (int j = 0; j <= 3 - this.height; ++j) {
                if (this.matches(inv, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean matches(RksMachineBlockEntity craftingInventory, int width, int height) {
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                int k = x - width;
                int l = y - height;
                GenerationsIngredient ingredient = GenerationsIngredient.EmptyIngredient.INSTANCE;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    ingredient = this.recipeItems.get(k + l * this.width);
                }

                if (!ingredient.matches(craftingInventory.getItem(x + y * 3 + 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public boolean isIncomplete() {
        NonNullList<GenerationsIngredient> nonNullList = this.recipeItems;
        return nonNullList.isEmpty() || nonNullList.stream().filter(ingredient -> !ingredient.isEmpty()).anyMatch(GenerationsIngredient::isEmpty);
    }

    public record Serializer() implements RecipeSerializer<ShapedRksRecipe> {

        @Override
        public @NotNull ShapedRksRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            String string = GsonHelper.getAsString(json, "group", "");
            Map<String, GenerationsIngredient> map = keyFromJson(GsonHelper.getAsJsonObject(json, "inputs"));
            String[] strings = shrink(patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            NonNullList<GenerationsIngredient> nonNullList = dissolvePattern(strings, map, i, j);

            var result = RksResult.CODEC.decode(JsonOps.INSTANCE, json.get("result")).getOrThrow(false, System.out::println).getFirst();

            var speciesKey = json.has("speciesKey") ? SpeciesKey.fromString(json.getAsJsonPrimitive("speciesKey").getAsString()) : null;

            float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int processingTime = GsonHelper.getAsInt(json, "processingTime", 200);

            boolean bl = GsonHelper.getAsBoolean(json, "show_notification", true);
            boolean consumesTimeCapsules = GsonHelper.getAsBoolean(json, "consumesTimeCapsules", true);
            return new ShapedRksRecipe(recipeId, string, i, j, nonNullList, result, consumesTimeCapsules, speciesKey, experience, processingTime, bl);
        }

        @Override
        public @NotNull ShapedRksRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            var width = buffer.readVarInt();
            var height = buffer.readVarInt();
            var group = buffer.readUtf();
            var result = RksResultType.RKS_RESULT.get(buffer.readResourceLocation()).fromBuffer().apply(buffer);
            var recipeItems = buffer.readCollection(NonNullList::createWithCapacity, GenerationsIngredidents::fromNetwork);
            var consumesTimeCapsules = buffer.readBoolean();
            var key = buffer.readNullable(TimeCapsuleIngredientKt::readSpeciesKey);
            var experience = buffer.readFloat();
            var processingTime = buffer.readInt();
            var showNotification = buffer.readBoolean();

            return new ShapedRksRecipe(recipeId, group, width, height, recipeItems, result, consumesTimeCapsules, key, experience, processingTime, showNotification);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ShapedRksRecipe recipe) {
            buffer.writeVarInt(recipe.width);
            buffer.writeVarInt(recipe.height);
            buffer.writeUtf(recipe.group);
            recipe.result.toBuffer(buffer);
            buffer.writeCollection(recipe.recipeItems, GenerationsIngredidents::toNetwork);
            buffer.writeBoolean(recipe.consumesTimeCapsules);
            buffer.writeNullable(recipe.key, TimeCapsuleIngredientKt::writeSpeciesKey);
            buffer.writeFloat(recipe.experience());
            buffer.writeInt(recipe.processingTime());
            buffer.writeBoolean(recipe.showNotification);
        }
    }
}
