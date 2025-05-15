package generations.gg.generations.core.generationscore.common.api.player

import net.minecraft.world.entity.player.Player
import java.util.function.Function

object PlayerMoneyHandler {
    val MONEY_CACHE: MutableMap<Player, PlayerMoney> = HashMap()


    private var function =
        Function<Player, PlayerMoney> { player: Player ->
            PlayerPokeDollars(
                player
            )
        }

    fun setFunction(function: Function<Player, PlayerMoney>) {
        PlayerMoneyHandler.function = function
    }

    @JvmStatic
    fun of(player: Player): PlayerMoney {
        return MONEY_CACHE.computeIfAbsent(player, function)
    }
}
