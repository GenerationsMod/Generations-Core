package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.util.asResource
import com.google.gson.JsonObject
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class ItemIngredient(val itemKey: ResourceKey<Item>) : GenerationsIngredient {
    override val id = ID
    override fun matches(stack: ItemStack): Boolean {
        println("${stack.item.asItem().builtInRegistryHolder().key().location()} == ${itemKey.location()} equals ${stack.item.asItem().builtInRegistryHolder().key().location().equals(itemKey.location())}")
        return stack.`is` { it.`is`(itemKey) }
    }

    override fun matchingStacks(): List<ItemStack> {
       return listOf(BuiltInRegistries.ITEM.get(itemKey)!!.defaultInstance)
    }

    override fun write(json: JsonObject) {
        json.addProperty("key", itemKey.location().toString())
    }

    override fun write(buf: FriendlyByteBuf) {
        buf.writeResourceKey(itemKey)
    }

    companion object {
        val ID = "item"
    }
}

object ItemIngredientSerializer : GenerationsIngredientSerializer<ItemIngredient> {
    override fun read(buf: FriendlyByteBuf): ItemIngredient {
        return ItemIngredient(buf.readResourceKey(Registries.ITEM))
    }

    override fun read(jsonObject: JsonObject): ItemIngredient {
        return ItemIngredient(ResourceKey.create(Registries.ITEM, jsonObject.getAsJsonPrimitive("key").asString.asResource()))
    }
}