package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.CobblemonItems
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.toJsonArray
import com.google.gson.JsonObject
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack

class PokemonItemIngredient(var species: ResourceLocation, var aspects: Set<String>) : GenerationsIngredient {
    override val id = ID

    override fun matches(stack: ItemStack): Boolean =
        if (stack.`is`(CobblemonItems.POKEMON_MODEL)) {
            (stack.item as PokemonItem).getSpeciesAndAspects(stack)?.let {
                (it.first.resourceIdentifier == species) && (it.second.containsAll(aspects)) } ?: false
        } else false


    override fun matchingStacks(): List<ItemStack> = listOf(PokemonSpecies.getByIdentifier(species)?.let {
        PokemonItem.from(species = it, aspects = aspects)
    } ?: ItemStack.EMPTY)

    override fun write(json: JsonObject) {
        json.addProperty("species", species.toString())
        json.add("aspects", aspects.stream().map { it.toString() }.toList().toJsonArray())
    }

    override fun write(buf: FriendlyByteBuf) {
        buf.writeResourceLocation(species)
        buf.writeCollection(aspects) { t, u -> t.writeUtf(u) }
    }

    companion object {
        val ID = "pokemon_item"
    }

    object PokemonItemIngredientSerializer : GenerationsIngredientSerializer<PokemonItemIngredient> {
        override fun read(buf: FriendlyByteBuf): PokemonItemIngredient {
            val species = buf.readResourceLocation()
            val aspects = buf.readCollection({ mutableSetOf() }, FriendlyByteBuf::readUtf)

            return PokemonItemIngredient(species, aspects)
        }

        override fun read(jsonObject: JsonObject): PokemonItemIngredient {
            val species = jsonObject.getResouceLocation("species")
            val aspects = jsonObject.getAsJsonArray("aspects").map { it.asString }.toMutableSet()

            return PokemonItemIngredient(species, aspects)
        }
    }
}

private fun JsonObject.getResouceLocation(name: String): ResourceLocation {
    return this.get(name).asString.asResource()
}
