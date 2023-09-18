package generations.gg.generations.core.generationscore.api.player;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtension;
import com.google.gson.JsonObject;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class AccountInfo implements PlayerDataExtension {
    public static String KEY = "account_info";
    private BigDecimal balance;

    public AccountInfo(BigDecimal balance) {
        this.balance = balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
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
        return new AccountInfo(jsonObject.getAsJsonPrimitive("balance").getAsBigDecimal());
    }

    public static AccountInfo get(Player player) {
        return (AccountInfo) Cobblemon.playerData.get(player).getExtraData().computeIfAbsent(KEY, key -> new AccountInfo(BigDecimal.valueOf(1000000)));
    }
}
