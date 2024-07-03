package generations.gg.generations.core.generationscore.world.recipe

import com.cobblemon.mod.common.CobblemonItems
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.toJsonArray
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import generations.gg.generations.core.generationscore.world.item.GenerationsItems
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack

class PokemonItemIngredient(var species: ResourceLocation, var aspects: Set<String>) : GenerationsIngredient {
    override fun matches(stack: ItemStack): Boolean =
        if (stack.`is`(CobblemonItems.POKEMON_MODEL)) {
            (stack.item as PokemonItem).getSpeciesAndAspects(stack)?.let {
                (it.first.resourceIdentifier == species) && (it.second.containsAll(aspects)) } ?: false
        } else false


    override fun matchingStacks(): List<ItemStack> = listOf(PokemonSpecies.getByIdentifier(species)?.let {
        PokemonItem.from(species = it, aspects = aspects)
    } ?: ItemStack.EMPTY)

    override fun toJson(obj: JsonObject) {
        super.toJson(obj)
        obj.addProperty("species", species.toString())
        obj.add("aspects", aspects.stream().map { it.toString() }.toList().toJsonArray())
    }

    object PokemonItemIngredientSerializer : GenerationsIngredientSerializer<PokemonItemIngredient> {
        override fun parse(buf: FriendlyByteBuf): PokemonItemIngredient {
            val species = buf.readResourceLocation()
            val aspects = buf.readCollection({ mutableSetOf() }, { it.readUtf() })

            return PokemonItemIngredient(species, aspects)
        }

        override fun parse(jsonObject: JsonObject): PokemonItemIngredient {
            val species = jsonObject.getResouceLocation("species")
            val aspects = jsonObject.getAsJsonArray("aspects").map { it.asString }.toMutableSet()

            println("RAWRF!")

            return PokemonItemIngredient(species, aspects)
        }

        override fun write(buf: FriendlyByteBuf, ingredient: PokemonItemIngredient) {
            buf.writeResourceLocation(ingredient.species)
            buf.writeCollection(ingredient.aspects) { t, u -> t.writeUtf(u) }
        }
    }
}

private fun JsonObject.getResouceLocation(name: String): ResourceLocation {
    return this.get(name).asString.asResource()
}
