package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.util.asResource
import com.google.gson.JsonObject
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class ItemTagIngredient(val itemKey: TagKey<Item>) : GenerationsIngredient {
    override val id = ID
    override fun matches(stack: ItemStack): Boolean {
        return stack.`is` { it.`is`(itemKey) }
    }

    override fun matchingStacks(): List<ItemStack> {
        return BuiltInRegistries.ITEM.getTagOrEmpty(itemKey).map { it.value() }.map { it.defaultInstance }.toList()
    }

    override fun write(json: JsonObject) {
        json.addProperty("key", itemKey.location().toString())
    }

    override fun write(buf: FriendlyByteBuf) {
        buf.writeResourceLocation(itemKey.location)
    }

    companion object {
        val ID = "item_tag"
    }
}

object ItemTagIngredientSerializer : GenerationsIngredientSerializer<ItemTagIngredient> {
    override fun read(buf: FriendlyByteBuf): ItemTagIngredient {
        return ItemTagIngredient(TagKey.create(Registries.ITEM, buf.readResourceLocation()))
    }

    override fun read(jsonObject: JsonObject): ItemTagIngredient {
        return ItemTagIngredient(TagKey.create(Registries.ITEM, jsonObject.getAsJsonPrimitive("key").asString.asResource()))
    }
}