package generations.gg.generations.core.generationscore.world.recipe

import com.cobblemon.mod.common.brewing.ingredient.CobblemonIngredient
import com.cobblemon.mod.common.util.toJsonArray
import com.google.gson.JsonObject
import generations.gg.generations.core.generationscore.config.SpeciesKey
import generations.gg.generations.core.generationscore.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.world.item.TimeCapsule
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.item.ItemStack
import java.util.function.IntFunction

class PokemonIngredient(val key : SpeciesKey, val strictAspects: Boolean = false) : GenerationsIngredient {

    override fun matches(stack: ItemStack): Boolean = if(stack.`is`(GenerationsItems.TIME_CAPSULE.get())) TimeCapsule.getPokemon(stack).filter { it.species.resourceIdentifier == key.species && if(strictAspects) it.aspects == key.aspects else it.aspects.containsAll(key.aspects) }.isPresent else false
    override fun matchingStacks(): List<ItemStack> = listOf(GenerationsItems.TIME_CAPSULE.get().defaultInstance)
    override fun toJson(obj: JsonObject) {
        super.toJson(obj)
        obj.addProperty("species", key.species.toString())
        obj.add("aspects", key.aspects.toJsonArray())
        obj.addProperty("strictAspects", strictAspects)
    }
}

object PokemonIngredientSerializer : GenerationsIngredientSerializer<PokemonIngredient> {
    override fun parse(buf: FriendlyByteBuf): PokemonIngredient {
        return PokemonIngredient(buf.readSpeciesKey(), buf.readBoolean())
    }

    override fun parse(jsonObject: JsonObject): PokemonIngredient {
        return PokemonIngredient(SpeciesKey(jsonObject.getAsJsonPrimitive("species").asString, jsonObject.getAsJsonArray("aspects").map { it.asString }.let { HashSet(it) }), jsonObject.getAsJsonPrimitive("strictAspects").asBoolean)
    }

    override fun write(buf: FriendlyByteBuf, ingredient: PokemonIngredient) {
        buf.writeSpeciesKey(ingredient.key).writeBoolean(ingredient.strictAspects)
    }
}

private fun FriendlyByteBuf.readSpeciesKey(): SpeciesKey = SpeciesKey(this.readResourceLocation(), this.readCollection({ HashSet(it) }, { it.readUtf() }))
private fun FriendlyByteBuf.writeSpeciesKey(key: SpeciesKey): FriendlyByteBuf = this.writeResourceLocation(key.species).writeCollection(key.aspects) { buf, i -> buf.writeUtf(i) }.let { this }
