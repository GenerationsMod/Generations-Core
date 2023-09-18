package generations.gg.generations.core.generationscore.api.player;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtension;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.gson.JsonObject;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class LegendsObtained implements PlayerDataExtension {
    public static String KEY = "legends_obtained";

    private Multiset<String> obtained;

    public LegendsObtained(Multiset<String> obtained) {
        this.obtained = obtained;
    }

    public LegendsObtained() {
        this(HashMultiset.create());
    }

    public int get(String name) {
        return obtained.count(name);
    }

    public boolean accumulate(String name, int amount) {
        var count = obtained.count(name);
        return obtained.setCount(name, count, amount + count);
    }

    public boolean accumulate(String name) {
        return accumulate(name, 1);
    }

    public void clear() {
        obtained.clear();
    }

    public void reset(String name) {
        obtained.setCount(name, 0);
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
        obtained.forEachEntry(json::addProperty);
        return json;
    }

    @NotNull
    @Override
    public PlayerDataExtension deserialize(@NotNull JsonObject jsonObject) {
        var multiset = HashMultiset.<String>create();
        jsonObject.entrySet().forEach(entry -> multiset.setCount(entry.getKey(), entry.getValue().getAsInt()));

        return new LegendsObtained(multiset);
    }

    public static LegendsObtained get(Player player) {
        return (LegendsObtained) Cobblemon.playerData.get(player).getExtraData().computeIfAbsent(KEY, key -> new LegendsObtained());
    }
}
