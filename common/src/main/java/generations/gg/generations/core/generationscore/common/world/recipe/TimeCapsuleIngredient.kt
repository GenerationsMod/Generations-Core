package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.util.toJsonArray
import com.google.gson.JsonObject
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.util.getPokemon
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

class TimeCapsuleIngredient(val key : SpeciesKey, val strictAspects: Boolean = false) : GenerationsIngredient {
    override val id = ID

    constructor(name: String, strictAspects: Boolean = false): this(
        SpeciesKey(
            ResourceLocation("cobblemon", name)
        ), strictAspects)

    override fun matches(stack: ItemStack): Boolean = if(stack.`is`(GenerationsItems.TIME_CAPSULE.get())) stack.getPokemon()?.takeIf { it.species.resourceIdentifier == key.species && if(strictAspects) it.aspects == key.aspects else it.aspects.containsAll(key.aspects) } != null else false
    override fun matchingStacks(): List<ItemStack> = listOf(PokemonSpecies.getByIdentifier(key.species)?.let { PokemonItem.from(it, key.aspects, 1) }?: Items.APPLE.defaultInstance.setHoverName(Component.literal("Missing species: " + key.species)))
    override fun write(json: JsonObject) {
        json.addProperty("species", key.species.toString())
        json.add("aspects", key.aspects.toJsonArray())
        json.addProperty("strictAspects", strictAspects)
    }

    override fun write(buf: FriendlyByteBuf) {
        buf.writeSpeciesKey(key).writeBoolean(strictAspects)
    }

    companion object {
        val ID = "time_capsule"
    }
}

object TimeCapsuleIngredientSerializer : GenerationsIngredientSerializer<TimeCapsuleIngredient> {
    override fun read(buf: FriendlyByteBuf): TimeCapsuleIngredient {
        return TimeCapsuleIngredient(buf.readSpeciesKey(), buf.readBoolean())
    }

    override fun read(jsonObject: JsonObject): TimeCapsuleIngredient {
        return TimeCapsuleIngredient(
            SpeciesKey(
                jsonObject.getAsJsonPrimitive("species").asString,
                jsonObject.getAsJsonArray("aspects").map { it.asString }.let { HashSet(it) }), jsonObject.getAsJsonPrimitive("strictAspects").asBoolean)
    }
}

fun FriendlyByteBuf.readSpeciesKey(): SpeciesKey =
    SpeciesKey(
        this.readResourceLocation(),
        this.readCollection<String, HashSet<String>>(::HashSet, FriendlyByteBuf::readUtf)
    )
fun FriendlyByteBuf.writeSpeciesKey(key: SpeciesKey): FriendlyByteBuf = this.writeResourceLocation(key.species).writeCollection(key.aspects, FriendlyByteBuf::writeUtf).let { this }
