package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import com.cobblemon.mod.common.pokemon.Pokemon
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.common.collect.Sets
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.recipe.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike

class ShapedRksRecipeJsonBuilder(result: RksResult<*>) : RksRecipeJsonBuilder<ShapedRksRecipe, ShapedRecipePattern>(result) {
    private val pattern: MutableList<String> = Lists.newArrayList()

    private val inputs: MutableMap<Char, GenerationsIngredient> = Maps.newLinkedHashMap()

    fun input(c: Char, tag: TagKey<Item>): ShapedRksRecipeJsonBuilder {
        return this.input(c, ItemTagIngredient(tag))
    }

    fun input(c: Char, itemProvider: ItemLike): ShapedRksRecipeJsonBuilder {
        return this.input(c, ItemIngredient(itemProvider.asItem().builtInRegistryHolder().key()))
    }

    //TODO: Verify this doens't mess me up.
    fun input(c: Char, ingredient: GenerationsIngredient): ShapedRksRecipeJsonBuilder {
        require(!inputs.containsKey(c)) { "Symbol '$c' is already defined!" }
        require(c != ' ') { "Symbol ' ' (whitespace) is reserved and cannot be defined" }
        inputs[c] = ingredient
        return this
    }


    fun pattern(patternStr: String): ShapedRksRecipeJsonBuilder {
        require(!(pattern.isNotEmpty() && patternStr.length != pattern[0].length)) { "Pattern must be the same width on every line!" }
        pattern.add(patternStr)
        return this
    }

    override fun create(id: ResourceLocation, pattern: ShapedRecipePattern): ShapedRksRecipe {
        return ShapedRksRecipe(this.group ?: "", this.result, this.consumesTimeCapsules, speciesKey, experience, processingTime, false, pattern
        )
    }

    override fun validate(recipeId: ResourceLocation): ShapedRecipePattern {
        check(!pattern.isEmpty()) { "No pattern is defined for shaped recipe $recipeId!" }
        val set: MutableSet<Char> = Sets.newHashSet(
            inputs.keys
        )
        set.remove(' ')

        for (string in this.pattern) {
            for (i in 0 until string.length) {
                val c = string[i]
                check(!(!inputs.containsKey(c) && c != ' ')) { "Pattern in recipe $recipeId uses undefined symbol '$c'" }

                set.remove(c)
            }
        }

        check(set.isEmpty()) { "Ingredients are defined but not used in pattern for recipe $recipeId" }
        check(!criteria.isEmpty()) { "No way of obtaining recipe $recipeId" }

        return ShapedRecipePattern.of(inputs, pattern)
    }

    companion object {
        @JvmStatic
        fun create(output: ItemLike): ShapedRksRecipeJsonBuilder {
            return ShapedRksRecipeJsonBuilder(ItemResult(output.asItem().defaultInstance))
        }

        @JvmOverloads
        fun create(
            species: ResourceLocation,
            aspects: Set<String>,
            level: Int,
            spawnInWorld: Boolean = true,
            usePokemonInCapsule: Boolean = false
        ): ShapedRksRecipeJsonBuilder {
            return ShapedRksRecipeJsonBuilder(PokemonResult(species, aspects, level, spawnInWorld, usePokemonInCapsule))
        }

        @JvmStatic
        @JvmOverloads
        fun create(key: SpeciesKey, level: Int = 1): ShapedRksRecipeJsonBuilder {
            return create(key.species, key.aspects!!, level)
        }

        fun create(pokemon: Pokemon): ShapedRksRecipeJsonBuilder {
            return create(pokemon.species.resourceIdentifier, pokemon.aspects, pokemon.level)
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            name: String,
            spawnInWorld: Boolean = true,
            usePokemonInCapsule: Boolean = false
        ): ShapedRksRecipeJsonBuilder {
            return create(
                ResourceLocation.fromNamespaceAndPath("cobblemon", name),
                HashSet(),
                1,
                spawnInWorld,
                usePokemonInCapsule
            )
        }
    }
}
