package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient
import generations.gg.generations.core.generationscore.common.world.recipe.ItemTagIngredient
import generations.gg.generations.core.generationscore.common.world.recipe.RksResult
import generations.gg.generations.core.generationscore.common.world.recipe.ShapelessRksRecipe
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike

class ShapelessRksRecipeJsonBuilder(result: RksResult<*>) : RksRecipeJsonBuilder<ShapelessRksRecipe, Unit>(result) {
    private val ingredients: MutableList<GenerationsIngredient> =
        com.google.common.collect.Lists.newArrayList<GenerationsIngredient>()

    fun requires(tag: TagKey<Item>): ShapelessRksRecipeJsonBuilder {
        return this.requires(ItemTagIngredient(tag))
    }

    fun requires(item: net.minecraft.world.level.ItemLike): ShapelessRksRecipeJsonBuilder {
        return this.requires(item, 1)
    }

    fun requires(item: ItemLike, quantity: Int): ShapelessRksRecipeJsonBuilder {
        for (i in 0 until quantity) {
            this.requires(
                generations.gg.generations.core.generationscore.common.world.recipe.ItemIngredient(
                    item.asItem().builtInRegistryHolder().key()
                )
            )
        }

        return this
    }

    fun requires(ingredient: GenerationsIngredient, quantity: Int = 1): ShapelessRksRecipeJsonBuilder {
        for (i in 0 until quantity) {
            ingredients.add(ingredient)
        }

        return this
    }

    override fun create(id: ResourceLocation?, addition: Unit): ShapelessRksRecipe {
        TODO("Not yet implemented")
    }

    override fun createProvider(id: net.minecraft.resources.ResourceLocation, addition: Any): ShapelessRksRecipe {
        return ShapelessRksRecipeJsonProvider(
            id,
            this.result,
            this.consumesTimeCapsules, if (this.group == null) "" else this.group,
            this.ingredients,
            this.advancementBuilder, id.withPrefix("recipes/rks/"), experience, processingTime, speciesKey
        )
    }

    override fun validate(recipeId: net.minecraft.resources.ResourceLocation?): Any? {
        return null
    }

    companion object {
        @JvmStatic
        fun create(output: net.minecraft.world.level.ItemLike): ShapelessRksRecipeJsonBuilder {
            return ShapelessRksRecipeJsonBuilder(
                generations.gg.generations.core.generationscore.common.world.recipe.ItemResult(
                    output.asItem().defaultInstance
                )
            )
        }

        @JvmOverloads
        fun create(
            species: net.minecraft.resources.ResourceLocation,
            aspects: Set<String?>,
            level: Int,
            spawnInWorld: Boolean = true,
            usePokemonInCapsule: Boolean = false
        ): ShapelessRksRecipeJsonBuilder {
            return ShapelessRksRecipeJsonBuilder(
                PokemonResult(
                    species,
                    aspects,
                    level,
                    spawnInWorld,
                    usePokemonInCapsule
                )
            )
        }

        @JvmOverloads
        fun create(key: SpeciesKey, level: Int = 1): ShapelessRksRecipeJsonBuilder {
            return create(key.species, key.aspects, level)
        }

        fun create(pokemon: com.cobblemon.mod.common.pokemon.Pokemon): ShapelessRksRecipeJsonBuilder {
            return create(pokemon.species.resourceIdentifier, pokemon.aspects, pokemon.level)
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            name: String,
            spawnInWorld: Boolean = true,
            usePokemonInCapsule: Boolean = false
        ): ShapelessRksRecipeJsonBuilder {
            return create(
                net.minecraft.resources.ResourceLocation("cobblemon", name),
                java.util.HashSet(),
                1,
                spawnInWorld,
                usePokemonInCapsule
            )
        }
    }
}
