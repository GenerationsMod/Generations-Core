package generations.gg.generations.core.generationscore.common.util

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.BATTLE_TRANSFORM_ITEMS
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.ItemStack

fun PokemonEntity.canChangeForm(): Pair<Boolean, String> {
    val pokemon = this.pokemon
    var canFormChange = false
    var aspect = ""
    if (BATTLE_TRANSFORM_ITEMS.contains(pokemon.heldItem().item.builtInRegistryHolder()) || pokemon.species.name == "Rayquaza") {
        val itemStack = pokemon.heldItem()
        val item = itemStack.item
        val key = BuiltInRegistries.ITEM.getKey(item)
        val itemName = key.path
        val pokeName = pokemon.species.name.lowercase()

        if (itemName.take(5) == pokeName.take(5) || canItMega(pokemon, itemName)) {
            canFormChange = true
            if (pokeName == "charizard" || pokeName == "mewtwo") {
                if (itemName.endsWith("x")) {
                    aspect = "mega_x"
                } else {
                    aspect = "mega_y"
                }
            } else if (pokeName == "necrozma") {
                aspect = "ultra"
            } else {
                aspect = "mega"
            }
        } else if ((itemName == "blue_orb" && pokeName == "kyogre") || (itemName == "red_orb" && pokeName == "groudon")) {
            canFormChange = true
            if (pokeName == "kyogre") {
                aspect = "primalkyogre"
            } else {
                aspect = "primalgroudon"
            }
        }
    }
    return Pair(canFormChange, aspect)
}

fun canItMega(pokemon: Pokemon, itemName: String): Boolean {
    if (pokemon.species.name == "Necrozma" && (pokemon.form.name == "Dusk-Mane" || pokemon.form.name == "Dawn-Wings" || pokemon.form.name == "Ultra") && itemName == "ultranecrozium_z") return true
    pokemon.moveSet.forEach { move ->
        if (pokemon.species.name == "Rayquaza" && move.template.name == "dragonascent") return true
    }
    return false
}