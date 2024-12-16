package generations.gg.generations.core.generationscore.common.world.recipe;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class GenerationsCoreRecipeTypes {
	public static final DeferredRegister<RecipeType<?>> RECIPES_TYPES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.RECIPE_TYPE);
	public static RegistrySupplier<RecipeType<RksRecipe>> RKS = register("rks");

	private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> register(String name) {
		var id = GenerationsCore.id(name);
		return RECIPES_TYPES.register(id, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return id.toString();
            }
        });
	}

    public static void init() {
		RECIPES_TYPES.register();
	}
}
