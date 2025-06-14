package generations.gg.generations.core.generationscore.common.event

import com.cobblemon.mod.common.api.events.pokemon.HeldItemEvent
import com.cobblemon.mod.common.api.moves.BenchedMove
import com.cobblemon.mod.common.api.moves.Moves
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.pokemon.Pokemon
import generations.gg.generations.core.generationscore.common.util.removeMove
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item

object HeldItemFormeChange {
    fun ogerMaskChange(post: HeldItemEvent.Post) {
        val pokemon = post.pokemon

        if (pokemon.species.name != "Ogerpon") return

        val player: ServerPlayer? = pokemon.getOwnerPlayer()

        when {
            post.received.`is`(GenerationsItems.CORNERSTONE_MASK.get()) -> {
                StringSpeciesFeature("ogre_mask", "cornerstone").apply(pokemon)
                pokemon.teraType = TeraTypes.ROCK
                player?.sendSystemMessage("Ogerpon's Tera Type has been set to Rock".text())
            }

            post.received.`is`(GenerationsItems.HEARTHFLAME_MASK.get()) -> {
                StringSpeciesFeature("ogre_mask", "hearthflame").apply(pokemon)
                pokemon.teraType = TeraTypes.FIRE
                player?.sendSystemMessage("Ogerpon's Tera Type has been set to Fire".text())
            }

            post.received.`is`(GenerationsItems.WELLSPRING_MASK.get()) -> {
                StringSpeciesFeature("ogre_mask", "wellspring").apply(pokemon)
                pokemon.teraType = TeraTypes.WATER
                player?.sendSystemMessage("Ogerpon's Tera Type has been set to Water".text())
            }

            setOf(
                GenerationsItems.CORNERSTONE_MASK.get(),
                GenerationsItems.HEARTHFLAME_MASK.get(),
                GenerationsItems.WELLSPRING_MASK.get()
            ).contains(post.returned.item) -> {
                StringSpeciesFeature("ogre_mask", "teal").apply(pokemon)
                pokemon.teraType = TeraTypes.GRASS
                player?.sendSystemMessage("Ogerpon's Tera Type has been set to Grass".text())
            }
        }
    }

    fun doggoChange(post: HeldItemEvent.Post) {
        val pokemon = post.pokemon

        if (pokemon.species.name != "Zacian" && pokemon.species.name != "Zamazenta" ) return

        val player: ServerPlayer? = pokemon.getOwnerPlayer()

        if (pokemon.species.name == "Zacian") {
            when {
                post.received.`is`(GenerationsItems.CROWNED_SWORD.get()) -> {
                    pokemon.exchangeMove(Moves.getByNameOrDummy("ironhead"), Moves.getByNameOrDummy("behemothblade"))
                    pokemon.removeMove("ironhead")
                }

                post.returned.`is`(GenerationsItems.CROWNED_SWORD.get()) -> {
                    pokemon.exchangeMove(Moves.getByNameOrDummy("behemothblade"), Moves.getByNameOrDummy("ironhead"))
                    pokemon.removeMove("behemothblade")
                }
            }
        }
        if (pokemon.species.name == "Zamazenta") {
            when {
                post.received.`is`(GenerationsItems.CROWNED_SHIELD.get()) -> {
                    pokemon.exchangeMove(Moves.getByNameOrDummy("ironhead"), Moves.getByNameOrDummy("behemothbash"))
                    pokemon.removeMove("ironhead")
                }

                post.returned.`is`(GenerationsItems.CROWNED_SHIELD.get()) -> {
                    pokemon.exchangeMove(Moves.getByNameOrDummy("behemothbash"), Moves.getByNameOrDummy("ironhead"))
                    pokemon.removeMove("behemothbash")
                }
            }
        }




    }
}
