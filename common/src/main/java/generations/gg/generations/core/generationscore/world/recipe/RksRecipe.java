package generations.gg.generations.core.generationscore.world.recipe;

import com.cobblemon.mod.common.item.PokemonItem;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RksRecipe implements Recipe<Container> {
    private final int width;
    final int height;
    final NonNullList<Ingredient> recipeItems;
    final RksResult result;

    final SpeciesKey key;
    private final ResourceLocation id;
    final String group;
    final boolean showNotification;
    private final float experience;
    private final int processingTime;

    public RksRecipe(ResourceLocation id, String group, int width, int height, NonNullList<Ingredient> recipeItems, RksResult result, SpeciesKey key, float experience, int processingTime, boolean showNotification) {
        this.id = id;
        this.group = group;
        this.width = width;
        this.height = height;
        this.recipeItems = recipeItems;
        this.result = result;
        this.key = key;
        this.experience = experience;
        this.processingTime = processingTime;
        this.showNotification = showNotification;
    }

    public RksRecipe(ResourceLocation id, String group, int width, int height, NonNullList<Ingredient> recipeItems, RksResult result, float experience, int processingTime) {
        this(id, group, width, height, recipeItems, result, null, experience, processingTime, true);
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return GenerationsCoreRecipeSerializers.RKS.get();
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public RecipeType<?> getType() {
        return GenerationsCoreRecipeTypes.RKS.get();
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        if(result instanceof RksResult.ItemResult result) return result.item().getDefaultInstance();
        else if(result instanceof RksResult.PokemonResult result) {
            return PokemonItem.from(result.properties());
        }
        else return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.width && height >= this.height;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(Container inv, Level level) {
        for (int i = 0; i <= 3 - this.width; ++i) {
            for (int j = 0; j <= 3 - this.height; ++j) {
                if (this.matches(inv, i, j, true)) {
                    return true;
                }
                if (!this.matches(inv, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean matches(Container craftingInventory, int width, int height, boolean mirrored) {
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                int k = x - width;
                int l = y - height;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    ingredient = mirrored ? this.recipeItems.get(this.width - k - 1 + l * this.width) : this.recipeItems.get(k + l * this.width);
                }
                if (ingredient.test(craftingInventory.getItem(x + y * 3 + 1))) continue;
                return false;
            }
        }
        return true;
    }

    public NonNullList<ItemStack> getRemainingItems(Container container) {
        NonNullList<ItemStack> nonNullList = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);
        for (int i = 1; i < nonNullList.size(); ++i) {
            Item item = container.getItem(i).getItem();
            if (!item.hasCraftingRemainingItem()) continue;
            nonNullList.set(i, new ItemStack(item.getCraftingRemainingItem()));
        }
        return nonNullList;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return this.getResultItem(registryAccess).copy();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public static NonNullList<Ingredient> dissolvePattern(String[] pattern, Map<String, Ingredient> keys, int patternWidth, int patternHeight) {
        NonNullList<Ingredient> nonNullList = NonNullList.withSize(patternWidth * patternHeight, Ingredient.EMPTY);
        HashSet<String> set = Sets.newHashSet(keys.keySet());
        set.remove(" ");
        for (int i = 0; i < pattern.length; ++i) {
            for (int j = 0; j < pattern[i].length(); ++j) {
                String string = pattern[i].substring(j, j + 1);
                Ingredient ingredient = keys.get(string);
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

    @Override
    public boolean isIncomplete() {
        NonNullList<Ingredient> nonNullList = this.getIngredients();
        return nonNullList.isEmpty() || nonNullList.stream().filter(ingredient -> !ingredient.isEmpty()).anyMatch(ingredient -> ingredient.getItems().length == 0);
    }

    private static int firstNonSpace(String entry) {
        int i;
        for (i = 0; i < entry.length() && entry.charAt(i) == ' '; ++i) {
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
    public static Map<String, Ingredient> keyFromJson(JsonObject keyEntry) {
        HashMap<String, Ingredient> map = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : keyEntry.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }
            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }
            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
        }
        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    public int processingTime() {
        return processingTime;
    }

    public float experience() {
        return experience;
    }

    public boolean isPokemonResult() {
        return result instanceof RksResult.PokemonResult;
    }

    public RksResult getResult() {
        return result;
    }

    public static class Serializer implements RecipeSerializer<RksRecipe> {
        @Override
        public @NotNull RksRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            String string = GsonHelper.getAsString(json, "group", "");
            Map<String, Ingredient> map = keyFromJson(GsonHelper.getAsJsonObject(json, "key"));
            String[] strings = shrink(patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            NonNullList<Ingredient> nonNullList = dissolvePattern(strings, map, i, j);
            var result = RksResult.fromJson(GsonHelper.getAsJsonObject(json, "result"));



            var speciesKey = json.has("speciesKey") ? SpeciesKey.fromString(json.getAsJsonPrimitive("speciesKey").getAsString()) : null;

            float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int processingTime = GsonHelper.getAsInt(json, "processingTime", 200);

            boolean bl = GsonHelper.getAsBoolean(json, "show_notification", true);
            return new RksRecipe(recipeId, string, i, j, nonNullList, result, speciesKey, experience, processingTime, bl);
        }

        @Override
        public @NotNull RksRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int i = buffer.readVarInt();
            int j = buffer.readVarInt();
            String string = buffer.readUtf();
            NonNullList<Ingredient> nonNullList = NonNullList.withSize(i * j, Ingredient.EMPTY);
            nonNullList.replaceAll(ignored -> Ingredient.fromNetwork(buffer));

            var result = buffer.readBoolean() ? new RksResult.ItemResult(BuiltInRegistries.ITEM.get(buffer.readResourceLocation())) : new RksResult.PokemonResult(GenerationsUtils.parseProperties(buffer.readUtf()));

            var speciesKey = buffer.readNullable(buf -> SpeciesKey.fromString(buf.readUtf()));

            float experience = buffer.readFloat();
            int weavingTime = buffer.readInt();
            boolean bl = buffer.readBoolean();
            return new RksRecipe(recipeId, string, i, j, nonNullList, result, speciesKey, experience, weavingTime, bl);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, RksRecipe recipe) {
            buffer.writeVarInt(recipe.width);
            buffer.writeVarInt(recipe.height);
            buffer.writeUtf(recipe.group);
            recipe.recipeItems.forEach(ingredient -> ingredient.toNetwork(buffer));
            var isItem = recipe.result instanceof RksResult.ItemResult;
            buffer.writeBoolean(isItem);

            if(isItem) buffer.writeResourceLocation(BuiltInRegistries.ITEM.getKey((((RksResult.ItemResult) recipe.result).item())));
            else buffer.writeUtf(((RksResult.PokemonResult) recipe.result).properties().asString(" "));

            buffer.writeNullable(recipe.key, (buf, speciesKey) -> buf.writeUtf(speciesKey.toString()));

            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.processingTime);
            buffer.writeBoolean(recipe.showNotification);
        }
    }
}

