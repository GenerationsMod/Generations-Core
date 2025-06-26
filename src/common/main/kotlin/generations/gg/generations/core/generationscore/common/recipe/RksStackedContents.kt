package generations.gg.generations.core.generationscore.common.recipe

import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient
import net.minecraft.world.item.ItemStack

/**
 * Combined evaluator and inventory tracker for RksRecipe input resolution.
 * Replaces both vanilla StackedContents and RecipePicker cleanly.
 */
class RksStackedContents {

    private val contents: MutableMap<ItemStack, Int> = mutableMapOf()

    /**
     * Normalize ItemStack for storage and comparison.
     * Default: copies and sets count to 1.
     */
    protected fun normalize(stack: ItemStack): ItemStack {
        val copy = stack.copy()
        copy.count = 1
        return copy
    }

    /**
     * Inserts an ItemStack into the contents tracker.
     */
    fun account(stack: ItemStack) {
        if (stack.isEmpty) return
        val key = normalize(stack)
        contents[key] = contents.getOrDefault(key, 0) + stack.count
    }

    /**
     * Clears all tracked content.
     */
    fun clear() {
        contents.clear()
    }

    /**
     * Returns true if the tracker is empty.
     */
    fun isEmpty(): Boolean = contents.isEmpty()

    /**
     * Returns an immutable snapshot of the current contents.
     */
    fun snapshot(): Map<ItemStack, Int> = contents.toMap()

    /**
     * Returns all contents as full ItemStacks with correct counts.
     */
    fun expandedStacks(): List<ItemStack> {
        return contents.entries.map { (stack, count) ->
            val copy = stack.copy()
            copy.count = count
            copy
        }
    }

    /**
     * Returns total number of matching items for the given ingredient.
     */
    fun countMatching(ingredient: GenerationsIngredient): Int {
        return contents.entries
            .filter { ingredient.matches(it.key) }
            .sumOf { it.value }
    }

    /**
     * Checks if the given ingredient can be satisfied with [amount] items.
     */
    fun canSatisfy(ingredient: GenerationsIngredient, amount: Int): Boolean {
        return countMatching(ingredient) >= amount
    }

    /**
     * Attempts to consume [amount] items matching [ingredient].
     * Returns true if successful.
     */
    fun consume(ingredient: GenerationsIngredient, amount: Int): Boolean {
        if (!canSatisfy(ingredient, amount)) return false

        var remaining = amount
        val matched = contents.entries
            .filter { ingredient.matches(it.key) }
            .sortedByDescending { it.value }

        for ((key, count) in matched) {
            if (remaining <= 0) break
            val taken = minOf(count, remaining)
            val newCount = count - taken
            remaining -= taken
            if (newCount == 0) contents.remove(key)
            else contents[key] = newCount
        }

        return true
    }

    /**
     * Attempts to consume ingredients for one craft of [ingredients].
     * Returns true if all ingredients were satisfied and consumed.
     */
    fun tryCraftOnce(ingredients: List<GenerationsIngredient>): Boolean {
        return tryCraft(ingredients, 1)
    }

    /**
     * Attempts to consume ingredients for [amount] crafts of [ingredients].
     * Returns true if successful.
     */
    fun tryCraft(ingredients: List<GenerationsIngredient>, amount: Int = 1): Boolean {
        if (amount <= 0) return true
        if (!ingredients.all { canSatisfy(it, amount) }) return false
        return ingredients.all { consume(it, amount) }
    }

    /**
     * Computes how many times [ingredients] can be crafted from current contents.
     */
    fun maxCraftable(ingredients: List<GenerationsIngredient>): Int {
        return ingredients.minOfOrNull { countMatching(it) } ?: 0
    }

    /**
     * Crafts up to [limit] times from [ingredients], consuming contents.
     * Returns number of times successfully crafted.
     */
    fun tryCraftMax(ingredients: List<GenerationsIngredient>, limit: Int): Int {
        val max = maxCraftable(ingredients).coerceAtMost(limit)
        return if (max > 0 && tryCraft(ingredients, max)) max else 0
    }

    /**
     * Simulates inputs for [amount] crafts of [ingredients] without consuming.
     * Returns null if unsatisfiable.
     */
    fun simulateInputs(ingredients: List<GenerationsIngredient>, amount: Int): List<ItemStack>? {
        if (!ingredients.all { canSatisfy(it, amount) }) return null

        val simulated = contents.toMutableMap()
        val result = mutableListOf<ItemStack>()

        for (ingredient in ingredients) {
            var remaining = amount
            val matches = simulated.entries.filter { ingredient.matches(it.key) }

            for ((stack, count) in matches) {
                if (remaining <= 0) break

                val taken = minOf(count, remaining)
                val copy = stack.copy()
                copy.count = taken
                result.add(copy)

                val newCount = count - taken
                remaining -= taken

                if (newCount == 0) simulated.remove(stack)
                else simulated[stack] = newCount
            }

            if (remaining > 0) return null
        }

        return result
    }
}
