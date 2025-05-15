package generations.gg.generations.core.generationscore.common.api.player

import net.minecraft.world.entity.player.Player
import java.math.BigDecimal
import java.util.concurrent.CompletableFuture

/**
 * Manages the player's Pixelmon currency
 */
class PlayerPokeDollars(private val player: Player) : PlayerMoney {
    override fun balance(): CompletableFuture<BigDecimal> {
        return CompletableFuture.completedFuture(account.balance)
    }

    val account: AccountInfo
        get() = AccountInfo.get(player)

    fun set(amount: BigDecimal) {
        account.balance = amount
    }

    override fun deposit(amount: BigDecimal): CompletableFuture<Boolean> {
        val account = account
        account.balance = account.balance.add(amount)
        return CompletableFuture.completedFuture(true) //Do we want an upper limit?
    }

    // It will be up to the individual methods to first check balance before removing more than what the player has
    override fun withdraw(amount: BigDecimal): CompletableFuture<Boolean> {
        val account = account
        val newBalance = account.balance.subtract(amount)

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) return CompletableFuture.completedFuture(false)
        else {
            account.balance = newBalance
            return CompletableFuture.completedFuture(true)
        }
    }
}