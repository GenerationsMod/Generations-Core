package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.google.common.collect.Lists;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.*;

public class ShapelessRksRecipeJsonBuilder extends RksRecipeJsonBuilder<ShapelessRksRecipe> {
    private final List<GenerationsIngredient> ingredients = Lists.newArrayList();

    public ShapelessRksRecipeJsonBuilder(RksResult<?> result) {
        super(result);
    }

    public ShapelessRksRecipeJsonBuilder requires(TagKey<Item> tag) {
        return this.requires(new ItemTagIngredient(tag));
    }

    public ShapelessRksRecipeJsonBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public ShapelessRksRecipeJsonBuilder requires(ItemLike item, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.requires(new ItemIngredient(item.asItem().builtInRegistryHolder().key()));
        }

        return this;
    }

    public ShapelessRksRecipeJsonBuilder requires(GenerationsIngredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public ShapelessRksRecipeJsonBuilder requires(GenerationsIngredient ingredient, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    @Override
    protected FinishedRecipe createProvider(ResourceLocation id) {
        return new ShapelessRksRecipeJsonProvider(id, this.output, this.consumesTimeCapsules, this.group == null ? "" : this.group, this.ingredients, this.advancementBuilder, id.withPrefix("recipes/rks/"), experience, processingTime, speciesKey);
    }

    public static class ShapelessRksRecipeJsonProvider extends RksRecipeJsonProvider {
        private List<GenerationsIngredient> ingredients;


        ShapelessRksRecipeJsonProvider(ResourceLocation recipeId, RksResult<?> output, boolean consumesTimeCapsules, String group, List<GenerationsIngredient> ingredients, Advancement.Builder advancementBuilder, ResourceLocation advancementId, float experience, int processingTime, SpeciesKey speciesKey) {
            super(recipeId, output, consumesTimeCapsules, group, advancementBuilder, advancementId, experience, processingTime, speciesKey);
            this.ingredients = ingredients;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return GenerationsCoreRecipeSerializers.SHAPELESS_RKS.get();
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            super.serializeRecipeData(json);

            var jsonArray = new JsonArray();

            this.ingredients.stream().map(GenerationsIngredidents::toJson).forEach(jsonArray::add);

            json.add("ingredients", jsonArray);
        }
    }

    @Override
    protected void validate(ResourceLocation recipeId) {
    }

    public static ShapelessRksRecipeJsonBuilder create(ItemLike output) {
        return new ShapelessRksRecipeJsonBuilder(new ItemResult(output.asItem().getDefaultInstance()));
    }

    public static ShapelessRksRecipeJsonBuilder create(ResourceLocation species, Set<String> aspects, int level) {
        return create(species, aspects, level, true, false);
    }

    public static ShapelessRksRecipeJsonBuilder create(ResourceLocation species, Set<String> aspects, int level, boolean spawnInWorld, boolean usePokemonInCapsule) {
        return new ShapelessRksRecipeJsonBuilder(new PokemonResult(species, aspects, level, spawnInWorld, usePokemonInCapsule));
    }


    public static ShapelessRksRecipeJsonBuilder create(SpeciesKey key) {
        return create(key, 1);
    }

    public static ShapelessRksRecipeJsonBuilder create(SpeciesKey key, int level) {
        return create(key.species(), key.aspects(), level);
    }

    public static ShapelessRksRecipeJsonBuilder create(Pokemon pokemon) {
        return create(pokemon.getSpecies().getResourceIdentifier(), pokemon.getAspects(), pokemon.getLevel());
    }

    public static ShapelessRksRecipeJsonBuilder create(String name) {
        return create(name, true, false);
    }

    public static ShapelessRksRecipeJsonBuilder create(String name, boolean spawnInWorld, boolean usePokemonInCapsule) {
        return create(new ResourceLocation("cobblemon", name), new HashSet<>(), 1, spawnInWorld, usePokemonInCapsule);
    }
}
