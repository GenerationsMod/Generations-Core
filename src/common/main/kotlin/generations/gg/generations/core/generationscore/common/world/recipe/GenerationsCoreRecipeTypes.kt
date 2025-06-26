package generations.gg.generations.core.generationscore.common.world.recipe

import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType

object GenerationsCoreRecipeTypes: PlatformRegistry<RecipeType<*>>(Registries.RECIPE_TYPE, BuiltInRegistries.RECIPE_TYPE) {

    @JvmField
	var RKS = register<RksRecipe>("rks")

    private fun <T : Recipe<*>> register(name: String): Holder<RecipeType<*>> {
        val id = name.generationsResource()
        return create(name, {
            object : RecipeType<T> {
                override fun toString(): String = id.toString()
            }
        })
    }
}
