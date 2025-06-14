package generations.gg.generations.core.generationscore.common.world.recipe

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType

object GenerationsCoreRecipeTypes: PlatformRegistry<RecipeType<*>>() {
    override val registry: Registry<RecipeType<*>> = BuiltInRegistries.RECIPE_TYPE
    override val resourceKey: ResourceKey<Registry<RecipeType<*>>> = Registries.RECIPE_TYPE


    @JvmField
	var RKS = register<RksRecipe>("rks")

    private fun <T : Recipe<*>> register(name: String): RecipeType<T> {
        val id = name.generationsResource()
        return create(id, object : RecipeType<T> {
            override fun toString(): String = id.toString()
        })
    }
}
