package generations.gg.generations.core.generationscore.common.world.recipe;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class GenerationsCoreRecipeSerializers {
	public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.RECIPE_SERIALIZER);
	public static RegistrySupplier<RecipeSerializer<RksRecipeItem>> RKS_ITEM = register("rks_item", () -> new RksRecipe.Serializer<>(new RksRecipe.RksRecipeConstructor<RksRecipeItem, RksResult.ItemResult>() {
		@Override
		public RksRecipeItem create(ResourceLocation id, String group, int width, int height, NonNullList<GenerationsIngredient> recipeItems, RksResult.ItemResult result, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime, boolean showNotification) {
			return new RksRecipeItem(id, group, width, height, recipeItems, result, consumesTimeCapsules, key, experience, processingTime, showNotification);
		}
	}, RksResultType.ITEM));
	public static RegistrySupplier<RecipeSerializer<RksRecipePokemon>> RKS_POKEMON = register("rks_pokemon", () -> new RksRecipe.Serializer<>(RksRecipePokemon::new, RksResultType.POKEMON));

	public static void init() {
		RECIPE_SERIALIZERS.register();
	}

	public static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> register(String name, Supplier<RecipeSerializer<T>> supplier) {
		return RECIPE_SERIALIZERS.register(name, supplier);
	}

}
