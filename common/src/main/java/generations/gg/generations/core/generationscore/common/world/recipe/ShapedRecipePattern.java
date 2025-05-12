package generations.gg.generations.core.generationscore.common.world.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.recipe.RksInput;
import it.unimi.dsi.fastutil.chars.CharArraySet;
import it.unimi.dsi.fastutil.chars.CharSet;

import java.util.*;
import java.util.function.Function;
import net.minecraft.Util;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.StreamEncoder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;

public final class ShapedRecipePattern {
    private static final int MAX_SIZE = 3;
    public static final MapCodec<ShapedRecipePattern> MAP_CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedRecipePattern> STREAM_CODEC;
    private final int width;
    private final int height;
    private final NonNullList<GenerationsIngredient> ingredients;
    private final Optional<Data> data;
    private final int ingredientCount;
    private final boolean symmetrical;

    public ShapedRecipePattern(int width, int height, NonNullList<GenerationsIngredient> ingredients, Optional<Data> data) {
        this.width = width;
        this.height = height;
        this.ingredients = ingredients;
        this.data = data;
        int i = 0;

        for (GenerationsIngredient ingredient : ingredients) {
            if (!ingredient.isEmpty()) {
                ++i;
            }
        }

        this.ingredientCount = i;
        this.symmetrical = Util.isSymmetrical(width, height, ingredients);
    }

    public static ShapedRecipePattern of(Map<Character, GenerationsIngredient> key, String... pattern) {
        return of(key, List.of(pattern));
    }

    public static ShapedRecipePattern of(Map<Character, GenerationsIngredient> key, List<String> pattern) {
        Data data = new Data(key, pattern);
        return unpack(data).getOrThrow();
    }

    private static DataResult<ShapedRecipePattern> unpack(Data data) {
        String[] strings = shrink(data.pattern);
        int i = strings[0].length();
        int j = strings.length;
        NonNullList<GenerationsIngredient> nonNullList = NonNullList.withSize(i * j, EmptyIngredient.INSTANCE);
        CharSet charSet = new CharArraySet(data.key.keySet());

        for(int k = 0; k < strings.length; ++k) {
            String string = strings[k];

            for(int l = 0; l < string.length(); ++l) {
                char c = string.charAt(l);
                GenerationsIngredient ingredient = c == ' ' ? EmptyIngredient.INSTANCE: (GenerationsIngredient )data.key.get(c);
                if (ingredient == null) {
                    return DataResult.error(() -> {
                        return "Pattern references symbol '" + c + "' but it's not defined in the key";
                    });
                }

                charSet.remove(c);
                nonNullList.set(l + i * k, ingredient);
            }
        }

        if (!charSet.isEmpty()) {
            return DataResult.error(() -> {
                return "Key defines symbols that aren't used in pattern: " + String.valueOf(charSet);
            });
        } else {
            return DataResult.success(new ShapedRecipePattern(i, j, nonNullList, Optional.of(data)));
        }
    }

    @VisibleForTesting
    static String[] shrink(List<String> pattern) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for(int m = 0; m < pattern.size(); ++m) {
            String string = (String)pattern.get(m);
            i = Math.min(i, firstNonSpace(string));
            int n = lastNonSpace(string);
            j = Math.max(j, n);
            if (n < 0) {
                if (k == m) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (pattern.size() == l) {
            return new String[0];
        } else {
            String[] strings = new String[pattern.size() - l - k];

            for(int o = 0; o < strings.length; ++o) {
                strings[o] = ((String)pattern.get(o + k)).substring(i, j + 1);
            }

            return strings;
        }
    }

    private static int firstNonSpace(String row) {
        int i;
        for(i = 0; i < row.length() && row.charAt(i) == ' '; ++i) {
        }

        return i;
    }

    private static int lastNonSpace(String row) {
        int i;
        for(i = row.length() - 1; i >= 0 && row.charAt(i) == ' '; --i) {
        }

        return i;
    }

    public boolean matches(RksInput input) {
        if (input.ingredientCount() != this.ingredientCount) {
            return false;
        } else {
            if (input.width() == this.width && input.height() == this.height) {
                if (!this.symmetrical && this.matches(input, true)) {
                    return true;
                }

                if (this.matches(input, false)) {
                    return true;
                }
            }

            return false;
        }
    }

    private boolean matches(RksInput input, boolean symmetrical) {
        for(int i = 0; i < this.height; ++i) {
            for(int j = 0; j < this.width; ++j) {
                GenerationsIngredient ingredient;
                if (symmetrical) {
                    ingredient = this.ingredients.get(this.width - j - 1 + i * this.width);
                } else {
                    ingredient = this.ingredients.get(j + i * this.width);
                }

                ItemStack itemStack = input.getItem(j, i);
                if (!ingredient.matches(itemStack)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void toNetwork(RegistryFriendlyByteBuf buffer) {
        buffer.writeVarInt(this.width);
        buffer.writeVarInt(this.height);

        buffer.writeVarInt(ingredients.size());

        ingredients.forEach(ingredient -> GenerationsIngredidents.STREAM_CODEC.encode(buffer, ingredient));
    }

    private static ShapedRecipePattern fromNetwork(RegistryFriendlyByteBuf buffer) {
        int i = buffer.readVarInt();
        int j = buffer.readVarInt();
        NonNullList<GenerationsIngredient> nonNullList = NonNullList.withSize(i * j, EmptyIngredient.INSTANCE);
        nonNullList.replaceAll((ingredient) -> GenerationsIngredidents.STREAM_CODEC.decode(buffer));
        return new ShapedRecipePattern(i, j, nonNullList, Optional.empty());
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public NonNullList<GenerationsIngredient> ingredients() {
        return this.ingredients;
    }

    static {
        MAP_CODEC = ShapedRecipePattern.Data.MAP_CODEC.flatXmap(ShapedRecipePattern::unpack, (shapedRecipePattern) -> {
            return shapedRecipePattern.data.map(DataResult::success).orElseGet(() -> {
                return DataResult.error(() -> {
                    return "Cannot encode unpacked recipe";
                });
            });
        });
        STREAM_CODEC = StreamCodec.ofMember(ShapedRecipePattern::toNetwork, ShapedRecipePattern::fromNetwork);
    }

    public static record Data(Map<Character, GenerationsIngredient> key, List<String> pattern) {
        private static final Codec<List<String>> PATTERN_CODEC;
        private static final Codec<Character> SYMBOL_CODEC;
        public static final MapCodec<Data> MAP_CODEC;

        static {
            PATTERN_CODEC = Codec.STRING.listOf().comapFlatMap((list) -> {
                if (list.size() > 3) {
                    return DataResult.error(() -> "Invalid pattern: too many rows, 3 is maximum");
                } else if (list.isEmpty()) {
                    return DataResult.error(() -> "Invalid pattern: empty pattern not allowed");
                } else {
                    int width = list.getFirst().length();

                    for (String row : list) {
                        if (row.length() > 3) {
                            return DataResult.error(() -> "Invalid pattern: too many columns, 3 is maximum");
                        }
                        if (row.length() != width) {
                            return DataResult.error(() -> "Invalid pattern: each row must be the same width");
                        }
                    }

                    return DataResult.success(list);
                }
            }, Function.identity());
            SYMBOL_CODEC = Codec.STRING.comapFlatMap((string) -> {
                if (string.length() != 1) {
                    return DataResult.error(() -> "Invalid key entry: '" + string + "' is an invalid symbol (must be 1 character only).");
                } else {
                    return " ".equals(string) ? DataResult.error(() -> "Invalid key entry: ' ' is a reserved symbol.") : DataResult.success(string.charAt(0));
                }
            }, String::valueOf);
            MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(ExtraCodecs.strictUnboundedMap(SYMBOL_CODEC, GenerationsIngredidents.CODEC.validate(ingredient -> ingredient.getType().equals(GenerationsIngredidents.INSTANCE.getEMPTY()) ? DataResult.error(() -> "Ingrident can't be empty") : DataResult.success(ingredient))).fieldOf("key").forGetter(Data::key), PATTERN_CODEC.fieldOf("pattern").forGetter(Data::pattern)).apply(instance, Data::new));
        }
    }
}
