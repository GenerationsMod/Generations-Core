package generations.gg.generations.core.generationscore.common.world.recipe

import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import net.minecraft.world.item.ItemStack

object EmptyIngredient : GenerationsIngredient {
    override val id: String = "empty"
    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.EMPTY.get()

    override val isEmpty: Boolean
        get() = true

    override fun matches(stack: ItemStack): Boolean = stack.isEmpty

    override fun matchingStacks(): List<ItemStack> = listOf(ItemStack.EMPTY)
}