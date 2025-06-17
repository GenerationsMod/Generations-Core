package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.recipe.*
import generations.gg.generations.core.generationscore.forge.datagen.nullableOptional
import net.minecraft.core.Holder
import net.minecraft.core.NonNullList
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike

class ShapelessRksRecipeJsonBuilder(result: RksResult<*>) : RksRecipeJsonBuilder<ShapelessRksRecipe, Unit>(result) {
    private val ingredients: NonNullList<GenerationsIngredient> = NonNullList.create()

    fun requires(tag: TagKey<Item>): ShapelessRksRecipeJsonBuilder {
        return this.requires(ItemTagIngredient(tag))
    }

    fun requires(item: ItemLike): ShapelessRksRecipeJsonBuilder {
        return this.requires(item, 1)
    }

    fun requires(item: ItemLike, quantity: Int): ShapelessRksRecipeJsonBuilder {
        for (i in 0 until quantity) {
            this.requires(
                ItemIngredient(
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

    override fun create(id: ResourceLocation, addition: Unit): ShapelessRksRecipe {
        return ShapelessRksRecipe(
            this.group ?: "",
            this.result,
            this.consumesTimeCapsules,
            speciesKey.nullableOptional(),
            experience, processingTime,
            false,
            this.ingredients
        )
    }

    override fun validate(recipeId: ResourceLocation) = Unit

    companion object {
        fun <T: ItemLike> create(output: Holder<T>): ShapelessRksRecipeJsonBuilder = create(output.value().asItem())

        fun create(output: ItemLike): ShapelessRksRecipeJsonBuilder {
            return ShapelessRksRecipeJsonBuilder(
                ItemResult(
                    output.asItem().defaultInstance
                )
            )
        }

        @JvmOverloads
        fun create(
            species: ResourceLocation,
            aspects: Set<String>,
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
                ResourceLocation.fromNamespaceAndPath("cobblemon", name),
                java.util.HashSet(),
                1,
                spawnInWorld,
                usePokemonInCapsule
            )
        }
    }
}
