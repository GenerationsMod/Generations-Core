package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.world.recipe.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.CraftingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public abstract class RksRecipeJsonBuilder<V extends RksRecipe> extends CraftingRecipeBuilder {
	protected final RksResult<?> output;

	protected boolean consumesTimeCapsules = true;
	protected final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

	@Nullable
	protected String group;

	protected float experience = 1.0f;
	protected int processingTime = 240;

	@Nullable
	protected SpeciesKey speciesKey;

	public RksRecipeJsonBuilder(RksResult<?> result) {
		this.output = result;
	}


	public RksRecipeJsonBuilder<V> key(SpeciesKey key) {
		this.speciesKey = key;
		return this;
	}

	public RksRecipeJsonBuilder<V> criterion(String string, CriterionTriggerInstance criterionConditions) {
		this.advancementBuilder.addCriterion(string, criterionConditions);
		return this;
	}

	public RksRecipeJsonBuilder<V> group(@Nullable String string) {
		this.group = string;
		return this;
	}

	public RksRecipeJsonBuilder<V> experience(float experience) {
		this.experience = experience;
		return this;
	}

	public RksRecipeJsonBuilder<V> processingTime(int weavingTime) {
		this.processingTime = weavingTime;
		return  this;
	}

	public RksResult<?> getOutputResult() {
		return this.output;
	}

	public void offerTo(Consumer<FinishedRecipe> exporter, ResourceLocation recipeId) {
		recipeId = recipeId.withPrefix("rks/");

		this.validate(recipeId);
		this.advancementBuilder.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(RequirementsStrategy.OR);

		exporter.accept(createProvider(recipeId));
	}

	protected abstract FinishedRecipe createProvider(ResourceLocation id);

	protected abstract void validate(ResourceLocation recipeId);

	public RksRecipeJsonBuilder<V> doesntConsumeTimeCapsules() {
		this.consumesTimeCapsules = false;
		return this;
	}

	static abstract class RksRecipeJsonProvider implements FinishedRecipe {
		private final ResourceLocation recipeId;
		private final RksResult<?> output;
		private final boolean consumesTimeCapsules;
		private final String group;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;
		private final float experience;
		private final int processingTime;
		private final SpeciesKey speciesKey;

		public RksRecipeJsonProvider(ResourceLocation recipeId, RksResult<?> output, boolean consumesTimeCapsules, String group, Advancement.Builder advancementBuilder, ResourceLocation advancementId, float experience, int processingTime, SpeciesKey speciesKey) {
			this.recipeId = recipeId;
			this.output = output;
			this.consumesTimeCapsules = consumesTimeCapsules;
			this.group = group;
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

			if(speciesKey != null) json.addProperty("speciesKey", speciesKey.toString());

			json.add("result", RksResult.CODEC.encodeStart(JsonOps.INSTANCE, output).result().get());

			json.addProperty("experience", experience);
			json.addProperty("processingTime", processingTime);
			if(speciesKey != null) json.addProperty("speciesKey", speciesKey.toString());
			json.addProperty("consumesTimeCapsules", consumesTimeCapsules);
		}

		@Override
		public abstract RecipeSerializer<?> getType();

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
