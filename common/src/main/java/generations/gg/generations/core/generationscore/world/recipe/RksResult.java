package generations.gg.generations.core.generationscore.world.recipe;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.item.PokemonItem;
import com.cobblemon.mod.common.pokemon.Species;
import com.google.common.collect.Streams;
import com.google.gson.*;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public interface RksResult {
    void toJson(JsonObject object);

    String type();

    static JsonObject toJson(RksResult result) {
        var object = new JsonObject();
        object.addProperty("type", result.type());
        result.toJson(object);
        return object;
    }

    static RksResult fromJson(JsonObject object) {
        return switch (object.getAsJsonPrimitive("type").getAsString()) {
            case "item" -> ItemResult.fromJson(object);
            case "pokemon" -> PokemonResult.fromJson(object);
            default -> throw new JsonParseException("Type " + object.getAsJsonPrimitive("type").getAsString() + " isn't valid.");
        };
    }

    record ItemResult(Item item) implements RksResult {
        @Override
        public void toJson(JsonObject object) {
            object.addProperty("data", BuiltInRegistries.ITEM.getKey(item).toString());
        }

        public static ItemResult fromJson(JsonObject object) {
            return new ItemResult(BuiltInRegistries.ITEM.get(new ResourceLocation(object.getAsJsonPrimitive("data").getAsString())));
        }

        @Override
        public String type() {
            return "item";
        }
    }
    record PokemonResult(ResourceLocation species, Set<String> aspects, int level) implements RksResult {
        @Override
        public void toJson(JsonObject object) {
            var data = new JsonObject();
            data.addProperty("species", species.toString());

            var aspects = new JsonArray();

            aspects().stream().map(JsonPrimitive::new).forEach(aspects::add);

            data.add("aspects", aspects);
            data.addProperty("level", level);
            object.add("data", data);
        }

        @Override
        public String type() {
            return "pokemon";
        }

        public static PokemonResult fromJson(JsonObject object) {
            var data = object.getAsJsonObject("data");
            var species = new ResourceLocation(data.getAsJsonPrimitive("species").getAsString());
            var aspects = data.has("aspects") ? Streams.stream(data.getAsJsonArray("aspects")).map(JsonElement::getAsString).collect(Collectors.toSet()) : Collections.<String>emptySet();
            var level = data.has("level") ? data.getAsJsonPrimitive("level").getAsInt() : 1;
            return new PokemonResult(species, aspects, level);
        }
    }
}
