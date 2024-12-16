package generations.gg.generations.core.generationscore.common.world.recipe;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

import java.util.function.Supplier;

public class GenerationsCoreRecipeSerializers {
	public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.RECIPE_SERIALIZER);
	public static RegistrySupplier<RecipeSerializer<ShapedRksRecipe>> SHAPED_RKS = register("shaped_rks", ShapedRksRecipe.Serializer::new);
	public static RegistrySupplier<RecipeSerializer<ShapelessRksRecipe>> SHAPELESS_RKS = register("shapeless_rks", ShapelessRksRecipe.Serializer::new);

	public static void init() {
		RECIPE_SERIALIZERS.register();
	}

	public static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> register(String name, Supplier<RecipeSerializer<T>> supplier) {
		return RECIPE_SERIALIZERS.register(name, supplier);
	}

}
