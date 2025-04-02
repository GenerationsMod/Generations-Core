package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.world.recipe.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShapedRksRecipeJsonBuilder extends RksRecipeJsonBuilder<ShapedRksRecipe> {
    private final List<String> pattern = Lists.newArrayList();
    private final Map<Character, GenerationsIngredient> inputs = Maps.newLinkedHashMap();

    public ShapedRksRecipeJsonBuilder(RksResult<?> result) {
        super(result);
    }

    public ShapedRksRecipeJsonBuilder input(Character c, TagKey<Item> tag) {
        return this.input(c, new ItemTagIngredient(tag));
    }

    public ShapedRksRecipeJsonBuilder input(Character c, ItemLike itemProvider) {
        return this.input(c, new ItemIngredient(itemProvider.asItem().builtInRegistryHolder().key()));
    }

    //TODO: Verify this doens't mess me up.
    public ShapedRksRecipeJsonBuilder input(Character c, GenerationsIngredient ingredient) {
        if (this.inputs.containsKey(c)) {
            throw new IllegalArgumentException("Symbol '" + c + "' is already defined!");
        } else if (c == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.inputs.put(c, ingredient);
            return this;
        }
    }


    public ShapedRksRecipeJsonBuilder pattern(String patternStr) {
        if (!this.pattern.isEmpty() && patternStr.length() != this.pattern.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.pattern.add(patternStr);
            return this;
        }
    }

    @Override
    protected FinishedRecipe createProvider(ResourceLocation id) {
        return new ShapedRksRecipeJsonProvider(id, this.output, this.consumesTimeCapsules, this.group == null ? "" : this.group, this.pattern, this.inputs, this.advancementBuilder, id.withPrefix("recipes/rks/"), experience, processingTime, speciesKey);
    }

    public static class ShapedRksRecipeJsonProvider extends RksRecipeJsonProvider {
        private final List<String> pattern;
        private final Map<Character, GenerationsIngredient> inputs;

        ShapedRksRecipeJsonProvider(ResourceLocation recipeId, RksResult<?> output, boolean consumesTimeCapsules, String group, List<String> pattern, Map<Character, GenerationsIngredient> inputs, Advancement.Builder advancementBuilder, ResourceLocation advancementId, float experience, int processingTime, SpeciesKey speciesKey) {
            super(recipeId, output, consumesTimeCapsules, group, advancementBuilder, advancementId, experience, processingTime, speciesKey);
            this.pattern = pattern;
            this.inputs = inputs;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return GenerationsCoreRecipeSerializers.SHAPED_RKS.get();
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            super.serializeRecipeData(json);

            JsonArray jsonArray = new JsonArray();

            for (String string : this.pattern) {
                jsonArray.add(string);
            }

            json.add("pattern", jsonArray);
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<Character, GenerationsIngredient> characterIngredientEntry : this.inputs.entrySet()) {
                jsonObject.add(String.valueOf(characterIngredientEntry.getKey()), GenerationsIngredidents.toJson(characterIngredientEntry.getValue()));
            }

            json.add("inputs", jsonObject);
        }
    }

    @Override
    protected void validate(ResourceLocation recipeId) {
        if (this.pattern.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for shaped recipe " + recipeId + "!");
        } else {
            Set<Character> set = Sets.newHashSet(this.inputs.keySet());
            set.remove(' ');

            for (String string : this.pattern) {
                for (int i = 0; i < string.length(); ++i) {
                    char c = string.charAt(i);
                    if (!this.inputs.containsKey(c) && c != ' ') {
                        throw new IllegalStateException("Pattern in recipe " + recipeId + " uses undefined symbol '" + c + "'");
                    }

                    set.remove(c);
                }
            }

            if (!set.isEmpty()) {
                throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + recipeId);
            } /*else if (this.pattern.size() == 1 && this.pattern.get(0).length() == 1) {
				throw new IllegalStateException("Shaped recipe " + recipeId + " only takes in a single item - should it be a shapeless recipe instead?");
			}*/ else if (this.advancementBuilder.getCriteria().isEmpty()) {
                throw new IllegalStateException("No way of obtaining recipe " + recipeId);
            }
        }

    }

    public static ShapedRksRecipeJsonBuilder create(ItemLike output) {
        return new ShapedRksRecipeJsonBuilder(new ItemResult(output.asItem().getDefaultInstance()));
    }

    public static ShapedRksRecipeJsonBuilder create(ResourceLocation species, Set<String> aspects, int level) {
        return create(species, aspects, level, true, false);
    }

    public static ShapedRksRecipeJsonBuilder create(ResourceLocation species, Set<String> aspects, int level, boolean spawnInWorld, boolean usePokemonInCapsule) {
        return new ShapedRksRecipeJsonBuilder(new PokemonResult(species, aspects, level, spawnInWorld, usePokemonInCapsule));
    }


    public static ShapedRksRecipeJsonBuilder create(SpeciesKey key) {
        return create(key, 1);
    }

    public static ShapedRksRecipeJsonBuilder create(SpeciesKey key, int level) {
        return create(key.species(), key.aspects(), level);
    }

    public static ShapedRksRecipeJsonBuilder create(Pokemon pokemon) {
        return create(pokemon.getSpecies().getResourceIdentifier(), pokemon.getAspects(), pokemon.getLevel());
    }

    public static ShapedRksRecipeJsonBuilder create(String name) {
        return create(name, true, false);
    }

    public static ShapedRksRecipeJsonBuilder create(String name, boolean spawnInWorld, boolean usePokemonInCapsule) {
        return create(new ResourceLocation("cobblemon", name), new HashSet<>(), 1, spawnInWorld, usePokemonInCapsule);
    }
}
