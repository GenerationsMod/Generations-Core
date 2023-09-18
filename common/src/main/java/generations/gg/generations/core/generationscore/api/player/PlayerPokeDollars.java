package generations.gg.generations.core.generationscore.api.player;

import net.minecraft.world.entity.player.Player;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

/**
 * Manages the player's Pixelmon currency
 */
public class PlayerPokeDollars implements PlayerMoney {
    private final Player player;

    public PlayerPokeDollars(Player player) {
        this.player = player;
    }

//    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
//        PlayerPokeDollars.of(event.getEntity()).sync();
//    }

    public CompletableFuture<BigDecimal> balance() {
        return CompletableFuture.completedFuture(getAccount().getBalance());
    }

    public AccountInfo getAccount() {
        return AccountInfo.get(player);
    }

    public void set(BigDecimal amount) {
        getAccount().setBalance(amount);
    }

    public CompletableFuture<Boolean> deposit(BigDecimal amount) {
        var account = getAccount();
        account.setBalance(account.getBalance().add(amount));
        return CompletableFuture.completedFuture(true); //Do we want an upper limit?
    }

    // It will be up to the individual methods to first check balance before removing more than what the player has
    public CompletableFuture<Boolean> withdraw(BigDecimal amount) {
        var account = getAccount();
        var newBalance = account.getBalance().subtract(amount);

        if(newBalance.compareTo(BigDecimal.ZERO) < 0) return CompletableFuture.completedFuture(false);
        else {
            account.setBalance(newBalance);
            return CompletableFuture.completedFuture(true);
        }
    }
}