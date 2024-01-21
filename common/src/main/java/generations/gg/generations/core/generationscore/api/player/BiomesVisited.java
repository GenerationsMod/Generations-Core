package generations.gg.generations.core.generationscore.api.player;

import com.cobblemon.mod.common.Cobblemon;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static generations.gg.generations.core.generationscore.api.player.ClientPlayerMoney.balance;

public class BiomesVisited extends PlayerDataExtension {
    public static String KEY = "biomes_visited";
    private final Set<ResourceKey<Biome>> biomes;


    public BiomesVisited() {
        biomes = new HashSet<>();
    }

    public BiomesVisited(Set<ResourceKey<Biome>> biomes) {
        this.biomes = biomes;
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
        var json = super.serialize();
        var biomes = new JsonArray();
        this.biomes.stream().map(ResourceKey::location).map(ResourceLocation::toString).forEach(biomes::add);
        json.add("biomes", biomes);
        return json;
    }

    @NotNull
    @Override
    public PlayerDataExtension deserialize(@NotNull JsonObject jsonObject) {
        var biomes = jsonObject.getAsJsonArray("biomes").asList().stream().map(JsonElement::getAsString).map(ResourceLocation::new).map(a -> ResourceKey.create(Registries.BIOME, a)).collect(Collectors.toCollection(HashSet::new));
        return new BiomesVisited(biomes);
    }

    public static BiomesVisited get(Player player) {
        return (BiomesVisited) Cobblemon.playerData.get(player).getExtraData().computeIfAbsent(KEY, key -> new BiomesVisited());
    }

    public boolean hasVisited(ResourceKey<Biome> biome) {
        return biomes.contains(biome);
    }

    public int numberOfVisitedBiomes() {
        return biomes.size();
    }

    public void add(ResourceKey<Biome> biome) {
        biomes.add(biome);
    }
}
