package generations.gg.generations.core.generationscore.common.battle

import com.cobblemon.mod.common.Cobblemon.LOGGER
import com.cobblemon.mod.common.api.battles.model.actor.AIBattleActor
import com.cobblemon.mod.common.battles.MoveActionResponse
import com.cobblemon.mod.common.battles.PassActionResponse
import com.cobblemon.mod.common.battles.ShowdownMoveset
import com.cobblemon.mod.common.exception.IllegalActionChoiceException


object AIBattleActorProxy {
    open fun AIBattleActor.onChoiceRequestedd() {
        try {
            val responses = request!!.iterate(this.activePokemon) { pokemon, active, forceSwitch ->
                val originalResponse = battleAI.choose(pokemon, this.battle, this.getSide(), active, forceSwitch)

                // Only try to apply gimmick to move actions
                if (originalResponse is MoveActionResponse && pokemon.battlePokemon?.effectedPokemon?.persistentData?.getBoolean("terastal_active") == true) {
                    val gimmick = ShowdownMoveset.Gimmick.TERASTALLIZATION
                    return@iterate MoveActionResponse(originalResponse.moveName, originalResponse.targetPnx, gimmick.id)
                }

                return@iterate originalResponse
            }

            setActionResponses(responses)
        } catch (exception: IllegalActionChoiceException) {
            LOGGER.error("AI was unable to choose a move, we're going to need to pass!")
            exception.printStackTrace()
            setActionResponses(request!!.iterate(this.activePokemon) { _, _, _ -> PassActionResponse })
        }
    }

}