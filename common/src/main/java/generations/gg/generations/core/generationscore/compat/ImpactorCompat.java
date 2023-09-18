package generations.gg.generations.core.generationscore.compat;

import generations.gg.generations.core.generationscore.api.player.PlayerMoney;
import generations.gg.generations.core.generationscore.api.player.PlayerMoneyHandler;
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
            return account().thenCompose(account -> account.withdrawAsync(amount).thenApply(EconomyTransaction::successful));
        }

        @Override
        public CompletableFuture<Boolean> deposit(BigDecimal amount) {
            return account().thenCompose(account -> account.depositAsync(amount).thenApply(EconomyTransaction::successful));
        }

        @Override
        public CompletableFuture<BigDecimal> balance() {
            return account().thenCompose(Account::balanceAsync);
        }
    }
}
