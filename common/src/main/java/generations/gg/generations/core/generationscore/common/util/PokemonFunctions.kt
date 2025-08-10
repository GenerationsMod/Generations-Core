package generations.gg.generations.core.generationscore.common.util

import com.cobblemon.mod.common.Cobblemon.statProvider
import com.cobblemon.mod.common.api.moves.Moves
import com.cobblemon.mod.common.api.pokemon.feature.*
import com.cobblemon.mod.common.api.pokemon.stats.Stat
import com.cobblemon.mod.common.api.pokemon.stats.Stats
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.api.scheduling.afterOnServer
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.RenderablePokemon
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.server
import generations.gg.generations.core.generationscore.common.world.item.StatueSpawnerItem
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemLore

private val statColorMap = mapOf(
    Stats.HP to "&8",
    Stats.ATTACK to "&c",
    Stats.DEFENCE to "&3",
    Stats.SPECIAL_ATTACK to "&9",
    Stats.SPECIAL_DEFENCE to "&a",
    Stats.SPEED to "&d"
)

fun Pokemon.dembedPokemon(): Pokemon? = if(hasEmbeddedPokemon()) {
    persistentData.getCompound(DataKeys.EMBEDDED_POKEMON).let { Pokemon.loadFromNBT(server()!!.registryAccess(), it) }.also {
        persistentData.remove(DataKeys.EMBEDDED_POKEMON)
        this.anyChangeObservable.emit(this)
    }
} else {
    null
}

fun Pokemon.embedPokemon(pokemon: Pokemon, needsToBeInWorld: Boolean = true): Boolean {
    val removedFromWorld = pokemon.storeCoordinates.get()?.remove() == true

    return if (!needsToBeInWorld || removedFromWorld) {
        this.persistentData.put(DataKeys.EMBEDDED_POKEMON, pokemon.saveToNBT(server()!!.registryAccess()))
        this.anyChangeObservable.emit(this)
        true
    } else {
        false
    }
}

fun Pokemon.removeMove(moveName: String) {

    for(move in moveSet) {
        if(move.template.name == moveName) {
            val index = moveSet.indexOf(move)
            moveSet.setMove(index, null)
        }
    }

    benchedMoves.remove(Moves.getByNameOrDummy(moveName))
}

fun Pokemon.replaceMove(oldMove: String, newMove: String) {
    moveSet.getMovesWithNulls().forEachIndexed { index, move ->
        if (move != null && move.template.name == oldMove) {
            val ppRatio = if (move.maxPp > 0) move.currentPp.toFloat() / move.maxPp else 0f
            val newMoveInstance = Moves.getByNameOrDummy(newMove).create().apply {
                raisedPpStages = move.raisedPpStages
                currentPp = (ppRatio * maxPp).toInt().coerceIn(0, maxPp)
            }
            moveSet.setMove(index, newMoveInstance)
            return
        }
    }
}

fun Pokemon.applyCosmeticFeature(feature: SpeciesFeature) {
    this.persistentData.putString("cosmetic_name", feature.name)
    if(feature is StringSpeciesFeature) {
        feature.apply(this)
    } else {
        (feature as FlagSpeciesFeature).apply(this)
    }
}

fun Pokemon.removeCosmeticFeature() {
    val data = this.persistentData

    if (data.contains("cosmetic_name")) {
        val name = data.getString("cosmetic_name").also { data.remove("cosmetic_name") }
        if (this.species.name == "Necrozma") {
            val feature: StringSpeciesFeature

            if (this.persistentData.getString("prism_fusion") == "dusk") {
                feature = StringSpeciesFeature("prism_fusion", "dusk")
                feature.apply(this)
            } else if (this.persistentData.getString("prism_fusion") == "dawn") {
                feature = StringSpeciesFeature("prism_fusion", "dawn")
                feature.apply(this)
            }
        } else {
            features.removeIf { it.name == name }
        }
    }

    updateAspects()
}

fun Pokemon.hasEmbeddedPokemon(): Boolean {
    return this.persistentData.contains(DataKeys.EMBEDDED_POKEMON)
}

fun ChoiceSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon, value: String = ""): StringSpeciesFeature = this.get(pokemon) ?: StringSpeciesFeature(keys.first(), value).also { pokemon.features.add(it) }
fun FlagSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon, value: Boolean = false): FlagSpeciesFeature = this.get(pokemon) ?: FlagSpeciesFeature(keys.first(), value).also { pokemon.features.add(it) }
fun IntSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon, value: Int = 0): IntSpeciesFeature = this.get(pokemon) ?: IntSpeciesFeature(keys.first(), value).also { pokemon.features.add(it) }

fun ChoiceSpeciesFeatureProvider.cycle(value: String): String {
    val index = choices.indexOf(value)
    return choices.getOrNull(index + 1) ?: ""
}

fun Pokemon.isSpecies(name: String): Boolean = species.resourceIdentifier.path == name

private fun FlagSpeciesFeatureProvider.create(enabled: Boolean): FlagSpeciesFeature = FlagSpeciesFeature(keys.first(), enabled)

//fun FlagSpeciesFeatureProvider.getOrCreate(pokemon: Pokemon): FlagSpeciesFeature = this.get(pokemon) ?: FlagSpeciesFeature(keys.first()).also { pokemon.features.add(it) }

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
    return this.get(0) + this.substring(1).lowercase()
}

fun MutableList<Component>.add(pokemon: Pokemon) {
    add("", pokemon)
}

fun ItemStack.savePokemon(poke: Pokemon) {
    set(GenerationsDataComponents.EMBEDDED_POKEMON.value(), poke)
}

fun ItemStack.removePokemon() {
    remove(GenerationsDataComponents.EMBEDDED_POKEMON.value())
}

fun ItemStack.getRenderablePokemon(): RenderablePokemon? {
    if(item is StatueSpawnerItem) {
        return (item as StatueSpawnerItem).pokemon?.asRenderablePokemon() //TODO: See if this explodes.
    }

    return get(GenerationsDataComponents.EMBEDDED_POKEMON.value())?.asRenderablePokemon()
}

fun ItemStack.getPokemon(): Pokemon? {
    if(item is StatueSpawnerItem) {
        return (item as StatueSpawnerItem).pokemon
    }

    return get(GenerationsDataComponents.EMBEDDED_POKEMON.value())
}

fun Pokemon.fixIVS() {
    println("Name: ${this.species.name}")
    val special = isLegendary() || isUltraBeast() || species.name == "ursaluna-bloodmoon" || species.name in setOf(
        "Gouging Fire",
        "Raging Bolt",
        "Walking Wake",
        "Iron Boulder",
        "Iron Crown",
        "Iron Leaves"
    )
    if (!special) return

    var perfectIvCounter = 0
    ivs.forEach { stat ->
        if (stat.value == 31) perfectIvCounter++
    }

    if (perfectIvCounter >= 3) {
        return
    }

    val indices = (0..5).shuffled().take(3)
    val permaStats: Collection<Stat> = statProvider.ofType(Stat.Type.PERMANENT)

    for ((index, stat) in permaStats.withIndex()) {
        if (indices.contains(index)) {
            this.ivs[stat] = 31
        }
    }
}

fun Pokemon.removeIfBelongs(player: Player): Boolean {
    return belongsTo(player) && storeCoordinates.get()?.remove() == true
}

fun <T:Any> ItemStack.setLore(lore: List<T>?): ItemStack {

    if (lore != null) {
        set(DataComponents.LORE, lore.map { if (it is MutableComponent) it else it.toString().text() }.toList().let { ItemLore(it) })
    } else {
        remove(DataComponents.LORE)
    }
    return this
}