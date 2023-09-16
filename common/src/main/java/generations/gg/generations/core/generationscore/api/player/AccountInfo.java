package generations.gg.generations.core.generationscore.api.player;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtension;
import com.google.gson.JsonObject;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class AccountInfo implements PlayerDataExtension {
    public static String KEY = "account_info";
    private int balance;

    public AccountInfo(int balance) {
        this.balance = balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    @NotNull
    @Override
    public String name() {
        return KEY;
    }

    @NotNull
    @Override
    public JsonObject serialize() {
        var json = new JsonObject();
        json.addProperty("balance", balance);
        return json;
    }

    @NotNull
    @Override
    public PlayerDataExtension deserialize(@NotNull JsonObject jsonObject) {
        return new AccountInfo(jsonObject.getAsJsonPrimitive("balance").getAsInt());
    }

    public static AccountInfo get(Player player) {
        return (AccountInfo) Cobblemon.playerData.get(player).getExtraData().computeIfAbsent(KEY, key -> new AccountInfo(100000));
    }
}
