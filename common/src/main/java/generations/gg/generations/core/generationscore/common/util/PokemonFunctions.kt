package generations.gg.generations.core.generationscore.common.util

import com.cobblemon.mod.common.api.pokemon.feature.*
import com.cobblemon.mod.common.api.properties.CustomPokemonProperty
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Pokemon.Companion.loadFromNBT
import generations.gg.generations.core.generationscore.common.world.item.StatueSpawnerItem
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
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
    add(padding + "Level ${pokemon.level} | ${pokemon.gender}")
    add(padding + "${pokemon.nature.name} ${pokemon.mintedNature?.let { "(${it.name})" } ?: ""} | Ability: ${pokemon.ability.name}")
    add(padding + "Aspects: ${pokemon.aspects}")
    add(padding + "Ball: ${pokemon.caughtBall.name.toLanguageKey()}")
    add(padding + "Moves:")
    add(padding + "${pokemon.moveSet[0]?.displayName?.string ?: "n/a"} | ${pokemon.moveSet[1]?.displayName?.string ?: "n/a"}")
    add(padding + "${pokemon.moveSet[2]?.displayName?.string ?: "n/a"} | ${pokemon.moveSet[3]?.displayName?.string ?: "n/a"}")
}

fun MutableList<Component>.add(pokemon: Pokemon) {
    add("", pokemon)
}

fun ItemStack.savePokemon(poke: Pokemon) = this.getOrCreateTag().put("pokemon", poke.saveToNBT(CompoundTag()))

fun ItemStack.removePokemon() = this.getOrCreateTag().remove("pokemon")

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

