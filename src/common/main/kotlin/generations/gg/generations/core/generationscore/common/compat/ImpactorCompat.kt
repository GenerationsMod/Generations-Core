package generations.gg.generations.core.generationscore.common.compat

import generations.gg.generations.core.generationscore.common.api.player.PlayerMoney
import generations.gg.generations.core.generationscore.common.api.player.PlayerMoneyHandler.setFunction
import net.impactdev.impactor.api.economy.EconomyService
import net.impactdev.impactor.api.economy.accounts.Account
import net.impactdev.impactor.api.economy.transactions.EconomyTransaction
import net.minecraft.world.entity.player.Player
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.CompletableFuture

object ImpactorCompat {
    fun init() {
        setFunction { player: Player -> ImpactorPlayerMoney(player.uuid) }
    }

    @JvmRecord
    data class ImpactorPlayerMoney(val playerUUID: UUID) : PlayerMoney {
        private fun account(): CompletableFuture<Account> {
            return EconomyService.instance().account(playerUUID)
        }

        override fun withdraw(amount: BigDecimal): CompletableFuture<Boolean> {
            return account().thenCompose { account: Account ->
                CompletableFuture.completedFuture(
                    account.withdraw(amount)
                ).thenApply { obj: EconomyTransaction -> obj.successful() }
            }
        }

        override fun deposit(amount: BigDecimal): CompletableFuture<Boolean> {
            return account().thenCompose { account: Account ->
                CompletableFuture.completedFuture(
                    account.deposit(amount)
                ).thenApply { obj: EconomyTransaction -> obj.successful() }
            }
        }

        override fun balance(): CompletableFuture<BigDecimal> {
            return account().thenCompose { a: Account ->
                CompletableFuture.completedFuture(
                    a.balance()
                )
            }
        }
    }
}
