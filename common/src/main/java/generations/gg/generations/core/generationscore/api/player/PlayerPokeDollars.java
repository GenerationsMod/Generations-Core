package generations.gg.generations.core.generationscore.api.player;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.shop.S2CSyncPlayerMoneyPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

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

    public int balance() {
        return getAccount().getBalance();
    }

    public AccountInfo getAccount() {
        return AccountInfo.get(player);
    }

    public void set(int amount) {
        getAccount().setBalance(amount);
    }

    public boolean deposit(int amount) {
        var account = getAccount();
        account.setBalance(account.getBalance() + amount);
        return true; //Do we want an upper limit?
    }

    // It will be up to the individual methods to first check balance before removing more than what the player has
    public boolean withdraw(int amount) {
        var account = getAccount();
        var newBalance = account.getBalance() - amount;

        if(newBalance < 0) return false;
        else {
            account.setBalance(newBalance);
            return true;
        }
    }
}