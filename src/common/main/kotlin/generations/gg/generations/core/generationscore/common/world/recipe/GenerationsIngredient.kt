package generations.gg.generations.core.generationscore.common.world.recipe

import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import net.minecraft.world.item.ItemStack

//Copy of Cobblemon's CobblemonIngredient class due to it being sealed.
interface GenerationsIngredient {
    val isEmpty: Boolean
        get() = false
    val id: String

    val type: GenerationsIngredientType<*>

    /**
     * Tests if a given [stack] is valid for this ingredient.
     *
     * @param stack The [ItemStack] being tested.
     * @return If the given [stack] is valid for this ingredient.
     */
    fun matches(stack: ItemStack): Boolean

    /**
     * Resolves list of [ItemStack]s that is used by the vanilla Ingredient implementation when a platform requires wrapping.
     *
     * @return A list of [ItemStack]s
     */
    fun matchingStacks(): List<ItemStack>

}

