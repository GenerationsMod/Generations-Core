package generations.gg.generations.core.generationscore.api.player;

import com.cobblemon.mod.common.Cobblemon;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class Caught extends PlayerDataExtension {
    public static String KEY = "caught";

    private final Multiset<SpeciesKey> obtained;

    public Caught(Multiset<SpeciesKey> obtained) {
        this.obtained = obtained;
    }

    public Caught() {
        this(HashMultiset.create());
    }

    public int get(SpeciesKey name) {
        return obtained.contains(name) ? obtained.count(name) : -1;
    }

    public boolean accumulate(SpeciesKey name, int amount) {
        var count = obtained.count(name);

        return obtained.setCount(name, count, amount + count);
    }

    public boolean accumulate(SpeciesKey name) {
        return accumulate(name, 1);
    }

    public void clear() {
        obtained.clear();
    }

    public void reset(SpeciesKey name) {
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
        var json = super.serialize();
        obtained.elementSet().forEach(key -> json.addProperty(key.toString(), obtained.count(key)));
        return json;
    }

    @NotNull
    @Override
    public PlayerDataExtension deserialize(@NotNull JsonObject jsonObject) {
        var multiset = HashMultiset.<SpeciesKey>create();
        jsonObject.entrySet().stream().filter(a -> !a.getKey().equals(PlayerDataExtension.Companion.getNAME_KEY())).forEach(entry -> multiset.setCount(SpeciesKey.fromString(entry.getKey()), entry.getValue().getAsInt()));

        return new Caught(multiset);
    }

    public static Caught get(Player player) {
        return (Caught) Cobblemon.playerData.get(player).getExtraData().computeIfAbsent(KEY, key -> new Caught());
    }
}
