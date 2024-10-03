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
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.util.PokemonFunctionsKt;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.item.TimeCapsule;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class RksRecipe<T extends RksResult<T>> implements Recipe<RksMachineBlockEntity> {
    public final int width;
    public final int height;
    public final NonNullList<GenerationsIngredient> recipeItems;
    public final T result;

    private final ResourceLocation id;
    public final SpeciesKey key;
    public final String group;

    public final boolean consumesTimeCapsules;
    public final float experience;
    public final int processingTime;

    public final boolean showNotification;

    public RksRecipe(ResourceLocation id, String group, int width, int height, NonNullList<GenerationsIngredient> recipeItems, T result, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime, boolean showNotification) {
        this.id = id;
        this.group = group;
        this.width = width;
        this.height = height;
        this.recipeItems = recipeItems;
        this.result = result;
        this.consumesTimeCapsules = consumesTimeCapsules;
        this.key = key;
        this.experience = experience;
        this.processingTime = processingTime;
        this.showNotification = showNotification;
    }


    public RksRecipe(ResourceLocation id, String group, int width, int height, NonNullList<GenerationsIngredient> recipeItems, T result, boolean consumesTimeCapsules, float experience, int processingTime) {
        this(id, group, width, height, recipeItems, result, consumesTimeCapsules, null, experience, processingTime, true);
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public abstract @NotNull RecipeSerializer<?> getSerializer();

    @Override
    public @NotNull String getGroup() {
        return this.group;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        try {
            return result.getStack();
        } catch (Exception e) {
            GenerationsCore.LOGGER.warn("Result for RKS machine recipe " + id + " is missing.");
            return ItemStack.EMPTY;
        }
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return NonNullList.create();
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

    public @NotNull NonNullList<ItemStack> getRemainingItems(RksMachineBlockEntity container) {

        NonNullList<ItemStack> nonNullList = NonNullList.withSize(9, ItemStack.EMPTY);
        for (int i = 0; i < nonNullList.size(); ++i) {
            ItemStack itemStack = container.inventory.get(i).copy();
            var item = itemStack.getItem();
            if (itemStack.is(GenerationsItems.TIME_CAPSULE.get())) {
                container.pokemon = Optional.ofNullable(PokemonFunctionsKt.getPokemon(itemStack));
                if (!consumesTimeCapsules) nonNullList.set(i, itemStack.copy());
            } else if (item.hasCraftingRemainingItem())
                nonNullList.set(i, new ItemStack(item.getCraftingRemainingItem()));
        }
        return nonNullList;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RksMachineBlockEntity container, @NotNull RegistryAccess registryAccess) {
        return this.getResultItem(registryAccess).copy();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
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

    @Override
    public boolean isIncomplete() {
        NonNullList<GenerationsIngredient> nonNullList = this.recipeItems;
        return nonNullList.isEmpty() || nonNullList.stream().filter(ingredient -> !ingredient.isEmpty()).anyMatch(ingredient -> ingredient.isEmpty());
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

    public int processingTime() {
        return processingTime;
    }

    public float experience() {
        return experience;
    }

    public boolean isPokemonResult() {
        return result.isPokemon();
    }

    public RksResult<?> getResult() {
        return result;
    }

    public void process(Player player, RksMachineBlockEntity rksMachineBlockEntity, ItemStack stack) {
        result.process(player, rksMachineBlockEntity, stack);
    }

    public record Serializer<T extends RksRecipe<V>, V extends RksResult<V>, K extends RksResultType<V>>(RksRecipeConstructor<T, V> constructor, RegistrySupplier<K> type) implements RecipeSerializer<T> {

        @Override
        public @NotNull T fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            String string = GsonHelper.getAsString(json, "group", "");
            Map<String, GenerationsIngredient> map = keyFromJson(GsonHelper.getAsJsonObject(json, "key"));
            String[] strings = shrink(patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            NonNullList<GenerationsIngredient> nonNullList = dissolvePattern(strings, map, i, j);

            var result = type().get().codec().decode(JsonOps.INSTANCE, json.get("result")).getOrThrow(false, System.out::println).getFirst();

            var speciesKey = json.has("speciesKey") ? SpeciesKey.fromString(json.getAsJsonPrimitive("speciesKey").getAsString()) : null;

            float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int processingTime = GsonHelper.getAsInt(json, "processingTime", 200);

            boolean bl = GsonHelper.getAsBoolean(json, "show_notification", true);
            boolean consumesTimeCapsules = GsonHelper.getAsBoolean(json, "consumesTimeCapsules", true);
            return constructor.create(recipeId, string, i, j, nonNullList, result, consumesTimeCapsules, speciesKey, experience, processingTime, bl);
        }

        @Override
        public @NotNull T fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int i = buffer.readVarInt();
            int j = buffer.readVarInt();
            String string = buffer.readUtf();
            NonNullList<GenerationsIngredient> nonNullList = NonNullList.withSize(i * j, GenerationsIngredient.EmptyIngredient.INSTANCE);
            nonNullList.replaceAll(ignored -> GenerationsIngredidents.fromNetwork(buffer));


            var result = type.get().fromBuffer().apply(buffer);

            var consumesTimeCapsules = buffer.readBoolean();

            var speciesKey = buffer.readNullable(buf -> {
                var location = buf.readResourceLocation();
                var aspects = buf.readNullable((FriendlyByteBuf.Reader<Set<String>>) buf1 -> buf1.readCollection(HashSet::new, FriendlyByteBuf::readUtf));

                return new SpeciesKey(location, aspects);
            });

            float experience = buffer.readFloat();
            int weavingTime = buffer.readInt();
            boolean bl = buffer.readBoolean();
            var recipe = constructor.create(recipeId, string, i, j, nonNullList, result, consumesTimeCapsules, speciesKey, experience, weavingTime, bl);

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, T recipe) {
            buffer.writeVarInt(recipe.width);
            buffer.writeVarInt(recipe.height);
            buffer.writeUtf(recipe.group);

            for (GenerationsIngredient generationsIngredient : recipe.recipeItems) {
                GenerationsIngredidents.toNetwork(buffer, generationsIngredient);
            }

            type.get().toBuffer().accept(buffer, recipe.result);

            buffer.writeBoolean(recipe.consumesTimeCapsules);

            buffer.writeNullable(recipe.key, (buf, speciesKey) -> {
                buffer.writeResourceLocation(speciesKey.species());
                buffer.writeNullable(speciesKey.aspects(), (buf1, strings) -> buf1.writeCollection(strings, FriendlyByteBuf::writeUtf));
            });

            buffer.writeFloat(recipe.experience());
            buffer.writeInt(recipe.processingTime());
            buffer.writeBoolean(recipe.showNotification);
        }
    }

    @FunctionalInterface
    public interface RksRecipeConstructor<V extends RksRecipe<T>, T extends RksResult<T>> {
        V create(ResourceLocation id, String group, int width, int height, NonNullList<GenerationsIngredient> recipeItems, T result, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime, boolean showNotification);
    }


}

