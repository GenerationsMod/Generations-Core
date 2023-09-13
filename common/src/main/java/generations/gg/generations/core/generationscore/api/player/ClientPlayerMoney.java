package generations.gg.generations.core.generationscore.api.player;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientPlayerMoney implements PlayerMoney {
    public static final PlayerMoney INSTANCE = new ClientPlayerMoney();
    public static int balance = 0;

    @Override
    public boolean withdraw(int amount) {
        return false;
    }

    @Override
    public boolean deposit(int amount) {
        return false;
    }

    @Override
    public Player player() {
        return Minecraft.getInstance().player;
    }

    @Override
    public int balance() {
        return balance;
    }
}
