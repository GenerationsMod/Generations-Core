package generations.gg.generations.core.generationscore.world.recipe

import com.google.gson.JsonObject
import generations.gg.generations.core.generationscore.recipe.GenerationsIngredidents
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient

//Copy of Cobblemon's CobblemonIngredient class due to it being sealed.
interface GenerationsIngredient {

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

    fun toJson(obj: JsonObject) {
        obj.addProperty("type", GenerationsIngredidents.getId(this).toString())
    }

    val isSimple: Boolean
        get() = true

    fun asMinecraftIngredient(): Ingredient = GenerationsIngredidents.convert(this);
}

interface GenerationsIngredientSerializer<T : GenerationsIngredient> {
    fun parse(buf: FriendlyByteBuf): T
    fun parse(jsonObject: JsonObject): T
    fun write(buf: FriendlyByteBuf, ingredient: T)
}