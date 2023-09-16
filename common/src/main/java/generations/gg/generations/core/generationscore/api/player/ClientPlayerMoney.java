package generations.gg.generations.core.generationscore.api.player;

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
    public int balance() {
        return balance;
    }
}
