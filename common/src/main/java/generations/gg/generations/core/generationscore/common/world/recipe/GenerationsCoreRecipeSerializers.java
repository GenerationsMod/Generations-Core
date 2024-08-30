package generations.gg.generations.core.generationscore.common.world.recipe;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class GenerationsCoreRecipeSerializers {
	public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.RECIPE_SERIALIZER);
	public static RegistrySupplier<RecipeSerializer<RksRecipeItem>> RKS_ITEM = register("rks_item", () -> new RksRecipe.Serializer<>(RksRecipeItem::new, RksResultType.ITEM));
	public static RegistrySupplier<RecipeSerializer<RksRecipePokemon>> RKS_POKEMON = register("rks_pokemon", () -> new RksRecipe.Serializer<>(RksRecipePokemon::new, RksResultType.POKEMON));

	public static void init() {
		RECIPE_SERIALIZERS.register();
	}

	public static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> register(String name, Supplier<RecipeSerializer<T>> supplier) {
		return RECIPE_SERIALIZERS.register(name, supplier);
	}

}
