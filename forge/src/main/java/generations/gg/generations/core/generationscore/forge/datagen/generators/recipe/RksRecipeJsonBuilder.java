package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsCoreRecipeSerializers;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient;
import generations.gg.generations.core.generationscore.common.world.recipe.RksResult;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.CraftingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class RksRecipeJsonBuilder extends CraftingRecipeBuilder {
	private final RksResult output;

	private boolean consumesTimeCapsules = true;
	private final List<String> pattern = Lists.newArrayList();
	private final Map<Character, Ingredient> inputs = Maps.newLinkedHashMap();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

	@Nullable
	private String group;

	private float experience = 1.0f;
	private int processingTime = 240;

	@Nullable
	private SpeciesKey speciesKey;

	public RksRecipeJsonBuilder(ItemLike output) {
		this.output = new RksResult.ItemResult(output.asItem().getDefaultInstance());
	}

	public RksRecipeJsonBuilder(ResourceLocation species, Set<String> aspects, int level) {
		this(species, aspects, level, true, false);
	}

	public RksRecipeJsonBuilder(ResourceLocation species, Set<String> aspects, int level, boolean spawnInWorld, boolean usePokemonInCapsule) {
		this.output = new RksResult.PokemonResult(species, aspects, level, spawnInWorld, usePokemonInCapsule);
	}


	public static RksRecipeJsonBuilder create(SpeciesKey key) {
		return create(key, 1);
	}

	public static RksRecipeJsonBuilder create(ResourceLocation species, Set<String> aspects, int level) {
		return new RksRecipeJsonBuilder(species, aspects, level);
	}

	public static RksRecipeJsonBuilder create(SpeciesKey key, int level) {
		return new RksRecipeJsonBuilder(key.species(), key.aspects(), level);
	}

	public static RksRecipeJsonBuilder create(Pokemon pokemon) {
		return new RksRecipeJsonBuilder(pokemon.getSpecies().getResourceIdentifier(), pokemon.getAspects(), pokemon.getLevel());
	}

	public static RksRecipeJsonBuilder create(ItemLike output) {
		return new RksRecipeJsonBuilder(output);
	}

	public static RksRecipeJsonBuilder create(String name) {
		return create(name, true, false);
	}

	public static RksRecipeJsonBuilder create(String name, boolean spawnInWorld, boolean usePokemonInCapsule) {
		return new RksRecipeJsonBuilder(new ResourceLocation("cobblemon", name), new HashSet<>(), 1, spawnInWorld, usePokemonInCapsule);
	}


	public RksRecipeJsonBuilder key(SpeciesKey key) {
		this.speciesKey = key;
		return this;
	}

	public RksRecipeJsonBuilder input(Character c, TagKey<Item> tag) {
		return this.input(c, Ingredient.of(tag));
	}

	public RksRecipeJsonBuilder input(Character c, ItemLike itemProvider) {
		return this.input(c, Ingredient.of(itemProvider));
	}

	public RksRecipeJsonBuilder input(Character c, Ingredient ingredient) {
		if (this.inputs.containsKey(c)) {
			throw new IllegalArgumentException("Symbol '" + c + "' is already defined!");
		} else if (c == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.inputs.put(c, ingredient);
			return this;
		}
	}

	public <T extends GenerationsIngredient> RksRecipeJsonBuilder input(Character c, GenerationsIngredient ingredient) {
		if (this.inputs.containsKey(c)) {
			throw new IllegalArgumentException("Symbol '" + c + "' is already defined!");
		} else if (c == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.inputs.put(c, ingredient.asMinecraftIngredient());
			return this;
		}
	}


	public RksRecipeJsonBuilder pattern(String patternStr) {
		if (!this.pattern.isEmpty() && patternStr.length() != this.pattern.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.pattern.add(patternStr);
			return this;
		}
	}

	public RksRecipeJsonBuilder criterion(String string, CriterionTriggerInstance criterionConditions) {
		this.advancementBuilder.addCriterion(string, criterionConditions);
		return this;
	}

	public RksRecipeJsonBuilder group(@Nullable String string) {
		this.group = string;
		return this;
	}

	public RksRecipeJsonBuilder experience(float experience) {
		this.experience = experience;
		return this;
	}

	public RksRecipeJsonBuilder processingTime(int weavingTime) {
		this.processingTime = weavingTime;
		return this;
	}

	public RksResult getOutputResult() {
		return this.output;
	}

	public void offerTo(Consumer<FinishedRecipe> exporter, ResourceLocation recipeId) {
		recipeId = recipeId.withPrefix("rks/");

		this.validate(recipeId);
		this.advancementBuilder.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(RequirementsStrategy.OR);

		exporter.accept(new RksRecipeJsonProvider(recipeId, this.output, this.consumesTimeCapsules, this.group == null ? "" : this.group, this.pattern, this.inputs, this.advancementBuilder, recipeId.withPrefix("recipes/rks/"), experience, processingTime, speciesKey));
	}

	private void validate(ResourceLocation recipeId) {
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

	public RksRecipeJsonBuilder doesntConsumeTimeCapsules() {
		this.consumesTimeCapsules = false;
		return this;
	}

	static class RksRecipeJsonProvider implements FinishedRecipe {
		private final ResourceLocation recipeId;
		private final RksResult<?> output;
		private final boolean consumesTimeCapsules;
		private final String group;
		private final List<String> pattern;
		private final Map<Character, Ingredient> inputs;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;
		private final float experience;
		private final int processingTime;
		private final SpeciesKey speciesKey;

		public RksRecipeJsonProvider(ResourceLocation recipeId, RksResult<?> output, boolean consumesTimeCapsules, String group, List<String> pattern, Map<Character, Ingredient> inputs, Advancement.Builder advancementBuilder, ResourceLocation advancementId, float experience, int processingTime, SpeciesKey speciesKey) {
			this.recipeId = recipeId;
			this.output = output;
			this.consumesTimeCapsules = consumesTimeCapsules;
			this.group = group;
			this.pattern = pattern;
			this.inputs = inputs;
			this.advancementBuilder = advancementBuilder;
			this.advancementId = advancementId;
			this.experience = experience;
			this.processingTime = processingTime;
			this.speciesKey = speciesKey;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			if (!this.group.isEmpty()) {
				json.addProperty("group", this.group);
			}

			JsonArray jsonArray = new JsonArray();

			for (String string : this.pattern) {
				jsonArray.add(string);
			}

			json.add("pattern", jsonArray);
			JsonObject jsonObject = new JsonObject();

			for (Map.Entry<Character, Ingredient> characterIngredientEntry : this.inputs.entrySet()) {
				jsonObject.add(String.valueOf(characterIngredientEntry.getKey()), characterIngredientEntry.getValue().toJson());
			}

			json.add("key", jsonObject);

			json.add("result", RksResult.CODEC.encodeStart(JsonOps.INSTANCE, output).getOrThrow(false, System.out::println));

			json.addProperty("experience", experience);
			json.addProperty("processingTime", processingTime);
			if(speciesKey != null) json.addProperty("speciesKey", speciesKey.toString());
			json.addProperty("consumesTimeCapsules", consumesTimeCapsules);
		}

		@Override
		public RecipeSerializer<?> getType() {
			return GenerationsCoreRecipeSerializers.RKS.get();
		}

		public ResourceLocation getId() {
			return this.recipeId;
		}

		@Nullable
		@Override
		public JsonObject serializeAdvancement() {
			return this.advancementBuilder.serializeToJson();
		}

		@Nullable
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}
	}
}
