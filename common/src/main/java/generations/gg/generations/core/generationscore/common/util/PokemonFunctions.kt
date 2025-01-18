package generations.gg.generations.core.generationscore.common.util

import com.cobblemon.mod.common.api.net.Encodable
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.pokemon.feature.*
import com.cobblemon.mod.common.api.pokemon.stats.Stat
import com.cobblemon.mod.common.api.pokemon.stats.Stats
import com.cobblemon.mod.common.api.properties.CustomPokemonProperty
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.api.storage.InvalidSpeciesException
import com.cobblemon.mod.common.api.text.plus
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.pokemon.*
import com.cobblemon.mod.common.pokemon.Pokemon.Companion.loadFromNBT
import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.toNbtList
import generations.gg.generations.core.generationscore.common.world.item.StatueSpawnerItem
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.TextColor
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

private val statColorMap = mapOf(
    Stats.HP to "&8",
    Stats.ATTACK to "&c",
    Stats.DEFENCE to "&3",
    Stats.SPECIAL_ATTACK to "&9",
    Stats.SPECIAL_DEFENCE to "&a",
    Stats.SPEED to "&d"
)

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
fun FlagSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon, value: Boolean = false): FlagSpeciesFeature = this.get(pokemon) ?: FlagSpeciesFeature(keys.first(), value)
fun IntSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon, value: Int = 0): IntSpeciesFeature = this.get(pokemon) ?: IntSpeciesFeature(keys.first(), value)

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
    add(padding + "&6Stats (${Stats.HP.color()}Hp&7/${Stats.ATTACK.color()}Atk&7/${Stats.DEFENCE.color()}Def&7/${Stats.SPECIAL_ATTACK.color()}SpAtk&7/${Stats.SPECIAL_DEFENCE.color()}SpDef&7/${Stats.SPEED.color()}Speed&6):")
    add(padding + "&eIVs: ${Stats.HP.color()}${pokemon.ivs[Stats.HP] ?: 0}&7/${Stats.ATTACK.color()}${pokemon.ivs[Stats.ATTACK] ?: 0}&7/${Stats.DEFENCE.color()}${pokemon.ivs[Stats.DEFENCE] ?: 0}&7/${Stats.SPECIAL_ATTACK.color()}${pokemon.ivs[Stats.SPECIAL_ATTACK] ?: 0}&7/${Stats.SPECIAL_DEFENCE.color()}${pokemon.ivs[Stats.SPECIAL_DEFENCE] ?: 0}&7/${Stats.SPEED.color()}${pokemon.ivs[Stats.SPEED] ?: 0}")
    add(padding + "&eEVs: ${Stats.HP.color()}${pokemon.evs[Stats.HP] ?: 0}&7/${Stats.ATTACK.color()}${pokemon.evs[Stats.ATTACK] ?: 0}&7/${Stats.DEFENCE.color()}${pokemon.evs[Stats.DEFENCE] ?: 0}&7/${Stats.SPECIAL_ATTACK.color()}${pokemon.evs[Stats.SPECIAL_ATTACK] ?: 0}&7/${Stats.SPECIAL_DEFENCE.color()}${pokemon.evs[Stats.SPECIAL_DEFENCE] ?: 0}&7/${Stats.SPEED.color()}${pokemon.evs[Stats.SPEED] ?: 0}")
}

private fun Stat.color(): String {
    return statColorMap.getOrDefault(this, "")
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

fun <T:Any> ItemStack.setLore(lore: List<T>?): ItemStack {
    val compoundtag = getOrCreateTagElement(ItemStack.TAG_DISPLAY)
    if (lore != null) {
        lore.map { if (it is MutableComponent) it else it.toString().text() }.map { Component.Serializer.toJson(it) }.map { StringTag.valueOf(it) }
            .toCollection(ListTag()).let { compoundtag.put(ItemStack.TAG_LORE, it) }
    } else {
        compoundtag.remove(ItemStack.TAG_LORE)
    }
    return this
}