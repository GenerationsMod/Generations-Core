package generations.gg.generations.core.generationscore.common.world.recipe

import com.google.gson.JsonObject
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.item.ItemStack

//Copy of Cobblemon's CobblemonIngredient class due to it being sealed.
interface GenerationsIngredient {
    val id: String

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

    fun write(json: JsonObject)
    fun write(buf: FriendlyByteBuf)

    object EmptyIngredient : GenerationsIngredient {
        override val id: String = "empty"

        override fun matches(stack: ItemStack): Boolean = stack.isEmpty

        override fun matchingStacks(): List<ItemStack> = listOf(ItemStack.EMPTY)

        override fun write(json: JsonObject) {}
        override fun write(buf: FriendlyByteBuf) {
        }
    }
}

interface GenerationsIngredientSerializer<T : GenerationsIngredient> {
    fun read(buf: FriendlyByteBuf): T
    fun read(jsonObject: JsonObject): T
}

