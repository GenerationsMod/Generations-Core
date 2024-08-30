package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.util.asResource
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class DamageIngredient(var item: Item, var damage: Int) : GenerationsIngredient {
    override val id = ID
    override fun matches(stack: ItemStack): Boolean = stack.`is`(item) && stack.damageValue == damage;

    override fun matchingStacks(): List<ItemStack> = listOf(item.defaultInstance)

    override fun write(json: JsonObject) {
        json.addProperty("item", item)
        json.addProperty("damage", damage)
    }

    override fun write(buf: FriendlyByteBuf) {
        buf.writeItemOnly(item);
        buf.writeVarInt(damage)
    }

    companion object {
        val ID = "damage"
    }
}

private fun JsonObject.addProperty(name: String, item: Item) = this.addProperty(name, item.builtInRegistryHolder().key().location().toString())

object DamageIngredientSerializer : GenerationsIngredientSerializer<DamageIngredient> {
    override fun read(buf: FriendlyByteBuf): DamageIngredient {
        return DamageIngredient(buf.readItemOnly(), buf.readVarInt());
    }

    override fun read(jsonObject: JsonObject): DamageIngredient {
        return DamageIngredient(jsonObject.getItem("item"), jsonObject.get("damage").asInt)
    }
}

private fun FriendlyByteBuf.writeItemOnly(item: Item) {
    this.writeResourceLocation(item.builtInRegistryHolder().key().location())
}

private fun JsonObject.getItem(name: String): Item {
    return this.getAsJsonPrimitive(name).getAsResouceLocation().let { BuiltInRegistries.ITEM.get(it) }
}

private fun JsonPrimitive.getAsResouceLocation(): ResourceLocation {
    return this.asString.asResource()
}

private fun FriendlyByteBuf.readItemOnly(): Item = this.readResourceLocation().let { BuiltInRegistries.ITEM.get(it) }
