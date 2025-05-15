package generations.gg.generations.core.generationscore.common.world.recipe

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType

object GenerationsCoreRecipeTypes {
    private val RECIPES_TYPES: DeferredRegister<RecipeType<*>> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.RECIPE_TYPE)
    @JvmField
	var RKS: RegistrySupplier<RecipeType<RksRecipe>> = register("rks")

    private fun <T : Recipe<*>> register(name: String): RegistrySupplier<RecipeType<T>> {
        val id = id(name)
        return RECIPES_TYPES.register(id) {
            object : RecipeType<T> {
                override fun toString(): String = id.toString()
            }
        }
    }

    fun init() {
        RECIPES_TYPES.register()
    }
}
