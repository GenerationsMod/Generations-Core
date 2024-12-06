package generations.gg.generations.core.generationscore.common.util

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.pokemon.feature.*
import com.cobblemon.mod.common.api.properties.CustomPokemonProperty
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.api.storage.InvalidSpeciesException
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.pokemon.Gender
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Pokemon.Companion.loadFromNBT
import com.cobblemon.mod.common.pokemon.RenderablePokemon
import com.cobblemon.mod.common.pokemon.Species
import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.toNbtList
import generations.gg.generations.core.generationscore.common.world.item.StatueSpawnerItem
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.StringTag
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

fun Pokemon.dembedPokemon(): Pokemon? = if(hasEmbeddedPokemon()) {
    persistentData.getCompound(DataKeys.EMBEDDED_POKEMON).let { Pokemon.loadFromNBT(it) }.also {
        persistentData.remove(DataKeys.EMBEDDED_POKEMON)
        this.anyChangeObservable.emit(this)
    }
} else {
    null
}

fun Pokemon.embedPokemon(pokemon: Pokemon, needsToBeInWorld: Boolean = true): Boolean {
    val removedFromWorld = pokemon.storeCoordinates.get()?.remove() == true

    return if (!needsToBeInWorld || removedFromWorld) {
        this.persistentData.put(DataKeys.EMBEDDED_POKEMON, pokemon.saveToNBT(CompoundTag()))
        this.anyChangeObservable.emit(this)
        true
    } else {
        false
    }
}

fun Pokemon.hasEmbeddedPokemon(): Boolean {
    return this.persistentData.contains(DataKeys.EMBEDDED_POKEMON)
}

fun ChoiceSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon, value: String = ""): StringSpeciesFeature = this.get(pokemon) ?: StringSpeciesFeature(keys.first(), value)

fun ChoiceSpeciesFeatureProvider.cycle(value: String): String {
    val index = choices.indexOf(value)
    return choices.getOrNull(index + 1) ?: ""
}

fun Pokemon.isSpecies(name: String): Boolean = species.resourceIdentifier.path == name

private fun FlagSpeciesFeatureProvider.create(enabled: Boolean): FlagSpeciesFeature = FlagSpeciesFeature(keys.first(), enabled)

fun FlagSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon): FlagSpeciesFeature = this.get(pokemon) ?: FlagSpeciesFeature(keys.first()).also { pokemon.features.add(it) }

inline fun <reified R : CustomPokemonPropertyType<*>> Pokemon.getProviderOrNull(id: String): R? {
    return SpeciesFeatures.getFeaturesFor(species).filterIsInstance<R>().firstOrNull { it.keys.contains(id) }
}

fun MutableList<Component>.add(value: String) = add(value.text())

fun MutableList<Component>.add(padding: String, pokemon: Pokemon) {
    add(padding + "&6Level &e${pokemon.level} &7| &6Gender: &e${pokemon.gender.name.properCase()}")
    add(padding + "&6Nature: &e${pokemon.nature.displayName.asTranslated().string}${pokemon.mintedNature?.let { " (${it.displayName.asTranslated().string})" } ?: ""} &7| &6Ability: &e${pokemon.ability.displayName.asTranslated().string}")
    add(padding + "&6Form: &e${pokemon.form.name}")
    add(padding + "&6Ball: &e${("item." + pokemon.caughtBall.name.toLanguageKey()).asTranslated().string}")
    add(padding + "&6Moves:")
    add(padding + "&e${pokemon.moveSet[0]?.displayName?.string ?: "n/a"} &7| &e${pokemon.moveSet[1]?.displayName?.string ?: "n/a"}")
    add(padding + "&e${pokemon.moveSet[2]?.displayName?.string ?: "n/a"} &7| &e${pokemon.moveSet[3]?.displayName?.string ?: "n/a"}")
}

private fun String.properCase(): String {
    return this.get(0) + this.substring(1).toLowerCase()
}

fun MutableList<Component>.add(pokemon: Pokemon) {
    add("", pokemon)
}

fun ItemStack.savePokemon(poke: Pokemon) {
    var tag = this.getOrCreateTag()

    var clientPokemon = CompoundTag()

    clientPokemon.putString("Species", poke.species.resourceIdentifier.toString())
    clientPokemon.put("Aspects", poke.aspects.map { StringTag.valueOf(it) }.toNbtList())
    tag.put(DataKeys.CLIENT_POKEMON_DATA, clientPokemon)
    tag.put("pokemon", poke.saveToNBT(CompoundTag()))
}

fun ItemStack.removePokemon() {
    var tag = this.tag ?: return
    tag.remove("pokemon")
    tag.remove(DataKeys.CLIENT_POKEMON_DATA)
}

fun ItemStack.getRenderablePokemon(): RenderablePokemon? {
    if(item is StatueSpawnerItem) {
        return (item as StatueSpawnerItem).pokemon?.asRenderablePokemon() //TODO: See if this explodes.
    }

    return if (hasTag() && tag!!.contains(DataKeys.CLIENT_POKEMON_DATA)) {
        var nbt = getTagElement(DataKeys.CLIENT_POKEMON_DATA)!!

        var species = PokemonSpecies.getByIdentifier(nbt.getString("Species").asResource()) ?: return null

        var aspects = nbt.getList("Aspects", Tag.TAG_STRING.toInt()).mapNotNull { it as? StringTag }.map { it.asString }.toCollection(HashSet())

        return RenderablePokemon(species, aspects)
    } else {
        null
    }
}

fun ItemStack.getPokemon(): Pokemon? {
    if(item is StatueSpawnerItem) {
        return (item as StatueSpawnerItem).pokemon
    }

    return if (hasTag() && tag!!.contains("pokemon")) {
        loadFromNBT(getTagElement("pokemon")!!)
    } else {
        null
    }
}


fun Pokemon.removeIfBelongs(player: Player): Boolean {
    return belongsTo(player) && storeCoordinates.get()?.remove() == true
}

