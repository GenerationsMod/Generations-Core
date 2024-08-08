package generations.gg.generations.core.generationscore.common.compat;

import generations.gg.generations.core.generationscore.common.api.player.PlayerMoney;
import generations.gg.generations.core.generationscore.common.api.player.PlayerMoneyHandler;
import net.impactdev.impactor.api.economy.EconomyService;
import net.impactdev.impactor.api.economy.accounts.Account;
import net.impactdev.impactor.api.economy.transactions.EconomyTransaction;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ImpactorCompat {
    public static void init() {
        PlayerMoneyHandler.setFunction(player -> new ImpactorPlayerMoney(player.getUUID()));
    }

    public record ImpactorPlayerMoney(UUID playerUUID) implements PlayerMoney {
        private CompletableFuture<Account> account() {
            return EconomyService.instance().account(playerUUID);
        }

        @Override
        public CompletableFuture<Boolean> withdraw(BigDecimal amount) {
            return account().thenCompose(account -> CompletableFuture.completedFuture(account.withdraw(amount)).thenApply(EconomyTransaction::successful));
        }

        @Override
        public CompletableFuture<Boolean> deposit(BigDecimal amount) {
            return account().thenCompose(account -> CompletableFuture.completedFuture(account.deposit(amount)).thenApply(EconomyTransaction::successful));
        }

        @Override
        public CompletableFuture<BigDecimal> balance() {
            return account().thenCompose(a -> CompletableFuture.completedFuture(a.balance()));
        }
    }
}
